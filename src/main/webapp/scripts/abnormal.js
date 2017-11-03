
function reloadAbnormalTabl(){
	loadAbnormalTable(year,month,day,equipmentGroup,equipmentID)
}
function loadAbnormalTable(year,month,day,equipmentGroup,equipmentID){
	$.ajax({
		url:'getAbnormalData.do',
		type:'post',
		dataType:'json',
		data:{
			"year":year,
			"month":month,
			"day":day,
			"equipmentGroup":equipmentGroup,
			"equipmentID":equipmentID
		},
		success:function(data){
			setAbnormalTable(data);
		},
		error:function(data){
			alert("获取异常记录信息失败，无法与服务器连接，请稍后再试!");
		}
	});
}
function setAbnormalTable(data){
	$("#abnormal tbody").empty();
	for(var i=0; i<data.length; i++){
		var tr = $("<tr></tr>");
		var td_dates = $("<td></td>");
		td_dates.html(data[i].dates);
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
		var td_memo = $("<td></td>");
		var td_action = $("<td></td>");
		
		var a = $("<a></a>");
		$(a).addClass("btn btn-outline btn-circle dark btn-sm black");
		$(a).attr("href","javascript:;");
		$(a).attr("abnormalID",data[i].id);
		var ii = $("<i></i>");
		$(ii).addClass("fa fa-trash-o");
		a.append(ii).append(" 修改");
		td_action.append(a);
		$(a).click(function(){
			var abnormalID = $(this).attr("abnormalID");
			modifyShowModal(abnormalID);
		});
		
		td_memo.html(data[i].memo);
		tr.append(td_dates).append(td_equipmentName).append(td_sipecification).append(td_solutions)
			.append(td_expectedTime).append(td_actualTime).append(td_ifCompleted).append(td_memo)
			.append(td_action);
		$("#abnormal tbody").append(tr);
	}
}
function submitAbnormal(){
	var trackingNumber = $("input[name='trackingNumber'").val();
	var dates = $("input[name='dates_a']").val();
	var sipecification = $("textarea[name='sipecification_a'").val();
	var solutions = $("textarea[name='solutions_a'").val();
	var expectedTime = $("input[name='expectedTime_a'").val();
	var actualTime = $("input[name='actualTime_a'").val();
	var ifCompleted = $("select[name='ifCompleted_a'").val();
	var memo = $("textarea[name='memo_a']").val();
	var equipmentID = $("select[name='equipmentID_a'").val();
	$.ajax({
		url:'submitAbnormal.do',
		type:'post',
		dataType:'json',
		data:{
			"trackingNumber" : trackingNumber,
			"dates" :dates,
			"sipecification" :sipecification,
			"solutions" :solutions,
			"expectedTime":expectedTime,
			"actualTime" :actualTime,
			"ifCompleted" :ifCompleted,
			"memo" :memo,
			"equipmentID":equipmentID
		},
		success:function(data){
			if(data.success=="true"){
				alert("提交成功");
				$("#responsive").modal('hide');
				var year = $("input[name='year']").val();
				var month = $("input[name='month']").val();
				var equipmentGroup = $("input[name='equipmentGroup']").val();
				var day = "";
				var equipmentID = "";
				loadAbnormalTable(year,month,day,equipmentGroup,equipmentID);
			}else{
				alert("数据提交失败");
			}
		},
		error:function(data){
			alert("数据提交失败，请重新登录提交！");
		}
	});
}

function setEquipment(){
	var equipmentGroup = $("input[name='equipmentGroup']").val();
	$.ajax({
		url:'getEquipmentByGroup.do',
		type:'post',
		dataType:'json',
		data:{
			equipmentGroup:equipmentGroup
		},
		success:function(data){
			setSelect(data);
		},
		error:function(data){
			alert("获取设备编号失败，请重新登录再试!");
		}
	});
}
function setSelect(data){
	$("select[name='equipmentID_a'").empty();
	$("select[name='equipmentID_b'").empty();
	for(var i=0; i<data.length; i++){
		var equipmentID = data[i].equipmentID;
		var equipmentName = data[i].equipmentName;
		var option = $("<option></option>");
		option.html(equipmentName);
		option.attr("value",equipmentID);
		$("select[name='equipmentID_a'").append(option);
	}
	for(var i=0; i<data.length; i++){
		var equipmentID = data[i].equipmentID;
		var equipmentName = data[i].equipmentName;
		var option = $("<option></option>");
		option.html(equipmentName);
		option.attr("value",equipmentID);
		$("select[name='equipmentID_b'").append(option);
	}
	$('select').selectpicker('render');
    $('select').selectpicker('refresh');
}

function addShowModal(){
	setEquipment();
	$("#responsive").modal('show');
}

function modifyShowModal(abnormalID){
	setEquipment();
	$.ajax({
		url:'getAbnormalByID.do',
		type:'post',
		dataType:'json',
		data:{
			"abnormalID":abnormalID
		},
		success:function(data){
			var abnormal = data.abnormal;
			$("input[name='abnormalID_b'").val(abnormal.id);
			$("input[name='dates_b']").val(abnormal.dates);
			$("textarea[name='sipecification_b'").val(abnormal.sipecification);
			$("textarea[name='solutions_b'").val(abnormal.solutions);
			$("input[name='expectedTime_b'").val(abnormal.expectedTime);
			$("input[name='actualTime_b'").val(abnormal.actualTime);
			$("select[name='ifCompleted_b'").selectpicker('val',abnormal.ifCompleted);
			$("textarea[name='memo_b']").val(abnormal.memo);
			$("select[name='equipmentID_b'").selectpicker('val',abnormal.equipmentID_b);
		},
		error:function(data){
			alert("获取设备编号失败，请重新登录再试!");
		}
	});
	$("#responsive2").modal('show');
}

function modifyAbnormal(){
	var abnormalID = $("input[name='abnormalID_b'").val();
	var dates = $("input[name='dates_b']").val();
	var sipecification = $("textarea[name='sipecification_b'").val();
	var solutions = $("textarea[name='solutions_b'").val();
	var expectedTime = $("input[name='expectedTime_b'").val();
	var actualTime = $("input[name='actualTime_b'").val();
	var ifCompleted = $("select[name='ifCompleted_b'").val();
	var memo = $("textarea[name='memo_b']").val();
	var equipmentID = $("select[name='equipmentID_b'").val();
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
			"equipmentID":equipmentID
		},
		success:function(data){
			if(data.success=="true"){
				alert("修改成功");
				$("#responsive2").modal('hide');
				var year = $("input[name='year']").val();
				var month = $("input[name='month']").val();
				var equipmentGroup = $("input[name='equipmentGroup']").val();
				var day = "";
				var equipmentID = "";
				loadAbnormalTable(year,month,day,equipmentGroup,equipmentID);
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
	var abnormalID = $("input[name='abnormalID_b'").val();
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
				var year = $("input[name='year']").val();
				var month = $("input[name='month']").val();
				var equipmentGroup = $("input[name='equipmentGroup']").val();
				var day = "";
				var equipmentID = "";
				loadAbnormalTable(year,month,day,equipmentGroup,equipmentID);
			}else{
				alert(data.message);
			}
		},
		error:function(data){
			alert("数据提交失败，请重新登录提交！");
		}
	});
}