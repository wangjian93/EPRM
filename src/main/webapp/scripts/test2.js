$(function(){
	initDate();
	menuInit();
	//setDate();
	setReportTableData();
	$('select').selectpicker({size: 6});
});
function setDate(){
	var myDate = new Date();        
	$("#year").val(myDate.getFullYear());//获取完整的年份(4位,1970-????)
	$("#month").val(myDate.getMonth()+1);//获取当前月份(0-11,0代表1月)
}

function setReportTableData(){
	var year = $("#year").val();
	var month = $("#month").val();
	var deptClass = $("#deptClass").val();
	var equipmentGroup = $("#equipmentGroup").val();
	var ifCompleted = $("input[name='ifCompleted']:checked").val();

	getReportTableData(year,month,deptClass,equipmentGroup,ifCompleted);
}

function getReportTableData(year,month,deptClass,equipmentGroup,ifCompleted){
	$.ajax({
		url:'getAbnormalData.do',
		data:{
			"year":year,
			"month":month,
			"deptClass":deptClass,
			"equipmentGroup":equipmentGroup,
            "ifCompleted":ifCompleted
		},
		type:'POST',
		dataType:'json',
		success:function(data){
			reloadReportTableData(data);
		},
		error:function(data){
			alert("服务器连接失败，请重新登录再试");
		}
	});
}

function reloadReportTableData(data){
	$("#abnormalTable").empty();
	for(var i=0; i<data.length; i++){
		var tr = $("<tr></tr>");
		var td_dates = $("<td></td>");
		td_dates.html(data[i].dates);
		
		var td_deptClass = $("<td></td>");
		var deptClassName =  data[i].deptClassName.split("/");
		td_deptClass.html(deptClassName[0]);
		var td_equipmentGroup = $("<td></td>");
		td_equipmentGroup.html(data[i].equipmentGroupName);
		
		var td_equipmentName = $("<td></td>");
		td_equipmentName.html(data[i].equipmentName);
		var td_sipecification = $("<td></td>");
		td_sipecification.html(data[i].sipecification);
		var td_solutions = $("<td></td>");
		td_solutions.html(data[i].solutions);
		var td_expectedTime = $("<td></td>");
		td_expectedTime.html(data[i].expectedTime);
		var td_actualTime = $("<td></td>");
		td_actualTime.html(data[i].actualTime);
		var td_ifCompleted = $("<td></td>");
		td_ifCompleted.html(data[i].ifCompleted=="1"?"是":"否");
		var td_engineer = $("<td></td>");
		td_engineer.html(data[i].engineer);
		var td_memo = $("<td></td>");
        td_memo.html(data[i].memo);
        var td_action = $("<td></td>");
		var a = $("<a></a>");
		$(a).addClass("btn btn-outline btn-circle dark btn-xs black");
		$(a).attr("href","javascript:;");
		$(a).attr("abnormalID",data[i].id);
		var ii = $("<i></i>");
		$(ii).addClass("fa fa-trash-o");
		a.append(ii).append(" 修改");
		$(a).click(function(){
			var abnormalID = $(this).attr("abnormalID");
			modifyShowModal(abnormalID);
		});
        td_action.append(a);

        var td_delay = $("<td></td>");
        if(data[i].ifCompleted=="1") {
            td_delay.html("-");
		} else {
            var currentTime = getNowFormatDate();
            var expectedTime = data[i].expectedTime;
            if(compareDate(expectedTime, currentTime)) {
                td_delay.html("-");
            } else {
                var delayTime = getDays(currentTime, expectedTime);
                td_delay.html(delayTime);
				$(td_delay).css({"color":"red"});
			}

		}

		tr.append(td_dates).append(td_deptClass).append(td_equipmentGroup).append(td_equipmentName).append(td_sipecification).append(td_solutions)
			.append(td_expectedTime).append(td_actualTime).append(td_ifCompleted).append(td_memo)
			.append(td_engineer).append(td_delay).append(td_action);
		$("#abnormalTable").append(tr);
	}	
}
function selectData(){
	setReportTableData();
}
function selectEquipmentGroup(){
	var deptClass = $("#deptClass").val();
	if(deptClass=="0"){
		$("#equipmentGroup").empty();
		var optionAll = $("<option></option>");
		$(optionAll).attr("value","0");
		$(optionAll).html("---All---");
		$("#equipmentGroup").append(optionAll);
	}else{
		$.ajax({
			url:'getEquipmentGroupByDepClass.do',
			data:{
				"deptClassID":deptClass
			},
			type:'POST',
			dataType:'json',
			success:function(data){
				var equipmentGroup = data[i];
				$("#equipmentGroup").empty();
				var optionAll = $("<option></option>");
				$(optionAll).attr("value","0");
				$(optionAll).html("---All---");
				$("#equipmentGroup").append(optionAll);
				for(var i=0;i<data.length; i++){
					var equipmentGroup = data[i];
					var option = $("<option></option>");
					$(option).attr("value",equipmentGroup.equipmentGroupID);
					$(option).html(equipmentGroup.equipmentGroupName);
					$("#equipmentGroup").append(option);
				}
				$('select').selectpicker('render');
				$('select').selectpicker('refresh');
			},
			error:function(data){
				alert("服务器连接失败，请重新登录再试");
			}
		});
	}
	
}

function initDate(){
	$('#datetimepicker5').datetimepicker({  
		minView: "month", //选择日期后，不会再跳转去选择时分秒 
	    language:  'zh-CN',
	    format: 'yyyy-mm-dd',
	    todayBtn:  1,
	    autoclose: 1,
        pickerPosition: "bottom-left"
    });
	$('#datetimepicker6').datetimepicker({  
		minView: "month", //选择日期后，不会再跳转去选择时分秒 
	    language:  'zh-CN',
	    format: 'yyyy-mm-dd',
	    todayBtn:  1,
	    autoclose: 1,
        pickerPosition: "bottom-left"
    });
}


function modifyShowModal(abnormalID){
	$.ajax({
		url:'getAbnormalByID.do',
		type:'post',
		dataType:'json',
		data:{
			"abnormalID":abnormalID
		},
		success:function(data){
			var abnormal = data.abnormal;
			$("input[name='abnormalID_b']").val(abnormal.id);
			$("input[name='equipmentID']").val(abnormal.equipmentID_fk);
			$("input[name='dates_b']").val(abnormal.dates);
			$("textarea[name='sipecification_b']").val(abnormal.sipecification);
			$("textarea[name='solutions_b']").val(abnormal.solutions);
			$("input[name='expectedTime_b']").val(abnormal.expectedTime);
			$("input[name='actualTime_b']").val(abnormal.actualTime);
			$("textarea[name='memo_b']").val(abnormal.memo);
			$("input[name='equipmentID_b']").val(abnormal.equipmentName);
			$("input[name='engineer_b']").val(abnormal.engineer);
			$("select[name='ifCompleted_b']").selectpicker('val',abnormal.ifCompleted);
            //如果已经填写预计完成时间，将禁止再次修改
            if(abnormal.expectedTime != "") {
                $("input[name='expectedTime_b']").attr("readonly","readonly");
            } else {
                $("input[name='expectedTime_b']").removeAttr("readonly");
			}
		},
		error:function(data){
			alert("获取设备编号失败，请重新登录再试!");
		}
	});
	$("#responsive2").modal('show');
}

function modifyAbnormal(){
	var abnormalID = $("input[name='abnormalID_b']").val();
	var dates = $("input[name='dates_b']").val();
	var sipecification = $("textarea[name='sipecification_b']").val();
	var solutions = $("textarea[name='solutions_b']").val();
	var expectedTime = $("input[name='expectedTime_b']").val();
	var actualTime = $("input[name='actualTime_b']").val();
	var ifCompleted = $("select[name='ifCompleted_b']").val();
	var memo = $("textarea[name='memo_b']").val();
	var equipmentID = $("input[name='equipmentID']").val();
	var engineer = $("input[name='engineer_b']").val();
	if(ifCompleted=="1") {
		if(actualTime==""){
			alert("请填写实际完成时间");
			return;
		}
	}
	if(actualTime != "") {
		if(ifCompleted=="0") {
			alert("实际完成时间已确认，请选择已完成")
			return;
		}
	}
	$.ajax({
		url:'modifyAbnormal.do',
		type:'post',
		dataType:'json',
		data:{
			"abnormalID" : abnormalID,
			"dates" :dates,
			"sipecification" :sipecification,
			"solutions" :solutions,
			"expectedTime":expectedTime,
			"actualTime" :actualTime,
			"ifCompleted" :ifCompleted,
			"memo" :memo,
			"equipmentID":equipmentID,
			"engineer":engineer
		},
		success:function(data){
			if(data.success=="true"){
				$("#responsive2").modal('hide');
				setReportTableData();
				if(ifCompleted=="1"){
					alert("该设备的异常已处理完成,请确认该设备是否已从异常状态切换回正常状态,如果没有请完成修改后及时提交");
				}
			}else{
				alert("数据提交失败");
			}
		},
		error:function(data){
			alert("数据提交失败，请重新登录提交！");
		}
	});
}

function deleteAbnormal(){
	var abnormalID = $("input[name='abnormalID_b']").val();
	$.ajax({
		url:'modifyAbnormal.do',
		type:'post',
		dataType:'json',
		data:{
			"abnormalID" : abnormalID,
			"validFlag":0
		},
		success:function(data){
			if(data.success=="true"){
				alert("删除成功");
				$("#responsive2").modal('hide');
				setReportTableData();
			}else{
				alert(data.message);
			}
		},
		error:function(data){
			alert("数据提交失败，请重新登录提交！");
		}
	});
}

/**人员组织树**/
function empTree() {
    XQuery.XFactory.init();
    var tree = XQuery.make({
        width : "250px",
        height : "400px",
        xtype : "XTree",
        root : "10000000",
        url : "http://myivo.ivo.com.cn/org/" + "/org/emp",
        crossdomain : true,
        autoParam : [ "id" ],
        nodeClick : function(tree, node) {
            $("input[name='engineer_b']").val(node.id + " " + node.name);
            $("#responsive3").modal('hide');
        }
    });
    var html = tree.compile();
    $("#memo").html("");
    $("#treeArea").html(html);
    tree.init();
    $("#responsive3").modal('show');
}

//计算天数差的函数，通用
function getDays(date1 , date2){
    var date1Str = date1.split("-");//将日期字符串分隔为数组,数组元素分别为年.月.日
    //根据年 . 月 . 日的值创建Date对象
    var date1Obj = new Date(date1Str[0],(date1Str[1]-1),date1Str[2]);
    var date2Str = date2.split("-");
    var date2Obj = new Date(date2Str[0],(date2Str[1]-1),date2Str[2]);
    var t1 = date1Obj.getTime();
    var t2 = date2Obj.getTime();
    var dateTime = 1000*60*60*24; //每一天的毫秒数
    var minusDays = Math.floor(((t2-t1)/dateTime));//计算出两个日期的天数差
    var days = Math.abs(minusDays);//取绝对值
    return days;
}

//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
//比较两个日期的大小
function compareDate(date1,date2){
    var oDate1 = new Date(date1);
    var oDate2 = new Date(date2);
    if(oDate1.getTime() > oDate2.getTime()){
        return true;
    } else {
        return false;
    }
}