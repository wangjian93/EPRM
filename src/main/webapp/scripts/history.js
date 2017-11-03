$(function(){
	menuInit();
	setDate();
	initDate();
	initTabbableLine();
});

function initDate(){
	$('#datetimepicker1').datetimepicker({  
		minView: "month", //选择日期后，不会再跳转去选择时分秒 
	    language:  'zh-CN',
	    format: 'yyyy-mm-dd',
	    todayBtn:  1,
	    autoclose: 1,
        pickerPosition: "bottom-left"
    }); 
	$('#datetimepicker2').datetimepicker({  
		minView: "month", //选择日期后，不会再跳转去选择时分秒 
	    language:  'zh-CN',
	    format: 'yyyy-mm-dd',
	    todayBtn:  1,
	    autoclose: 1,
        pickerPosition: "bottom-left"
    });
	$('#datetimepicker3').datetimepicker({  
		minView: "month", //选择日期后，不会再跳转去选择时分秒 
	    language:  'zh-CN',
	    format: 'yyyy-mm-dd',
	    todayBtn:  1,
	    autoclose: 1,
        pickerPosition: "bottom-left"
    });
	$('#datetimepicker4').datetimepicker({  
		minView: "month", //选择日期后，不会再跳转去选择时分秒 
	    language:  'zh-CN',
	    format: 'yyyy-mm-dd',
	    todayBtn:  1,
	    autoclose: 1,
        pickerPosition: "bottom-left"
    });
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

//charts图使用
jQuery(document).ready(function() {    
    ChartsFlotcharts.init();
    ChartsFlotcharts.initCharts();
    ChartsFlotcharts.initPieCharts();
    ChartsFlotcharts.initBarCharts();
    ChartsFlotcharts.initAxisLabelsPlugin();
});
function getCheckDataDetail(year, month, equipmentGroup){
	$.ajax({
		url:'getCheckDataDetail.do',
		data:{
			"year":year,
			"month":month,
			"equipmentGroup":equipmentGroup
		},
		dataType:'json',
		type:'post',
		success:function(data){
			var checkFormMonth = data.checkFormMonth;
			var spec = data.spec;
			var pageviews = new Array();
			for(var i=1; i<32; i++){
				var day = "day"+i;
				var properRate = parseFloat(checkFormMonth[day])*100;
				var arrayObj = new Array();
				arrayObj.push(i);
				if(properRate==0)
					arrayObj.push();
				else
					arrayObj.push(properRate);
				pageviews.push(arrayObj);
			}
			var visitors = new Array();
			for(var i=1; i<32; i++){
				var visitorsObj = new Array();
				visitorsObj.push(i);
				visitorsObj.push(spec*100);
				visitors.push(visitorsObj);
			}
			
		  plot.setData([{
                data: pageviews,
                label: "妥善率",
                lines: {
                    lineWidth: 2,
                },
                shadowSize: 0

            }, {
                data: visitors,
                label: "Spec标准",
                lines: {
                    lineWidth: 1,
                },
                shadowSize: 0
            }]);
			plot.draw();
			reloadHistoryTable(data);
		},
		error:function(data){
			alert("数据获取失败，请重试");
		}
	});
}
function reloadHistoryTable(data){
	$("#history2").empty();
	var checkDetail = data.checkDetail;
	var checkFormMonth = data.checkFormMonth;
	for(var i=0; i<checkDetail.length; i++){
		var tr = $("<tr></tr>");
		for(var j=0; j<32; j++){
			var td = $("<td></td>");
			if(j==0){
				td.html(checkDetail[i].equipmentName);
			}else{
				var day = "day"+j;
				var check = checkDetail[i][day];
				td.html(check);
				if(check=="R")
					td.attr("style","background-color:#FF5151");
				if(check=="S")
					td.attr("style","background-color:#53FF53");
				if(check=="SB")
					td.attr("style","background-color:#7AFEC6");
				if(check=="AB")
					td.attr("style","background-color:yellow");
			}
			tr.append(td);
		};
		$("#history2").append(tr);
	}
	var tr = $("<tr></tr>");
	for(var j=0; j<32; j++){
		var td = $("<td></td>");
		if(j==0){
			td.html("妥善率");
		}else{
			var day = "day"+j;
			var properRate = (checkFormMonth[day]*100).toFixed(0);
			td.html(properRate+"%");
		}
		tr.append(td);
	}
	$("#history2").append(tr);
}

function selectTabbableLine(element){
	var equipmentGroup = $(element).attr("equipmentGroup");
	$("#equipmentGroupName").html($(element).html());
	$("#equipmentGroupName2").html($(element).html());
	var year = $("#year").val();
	var month = $("#month").val();
	$("#month2").html(month);
	$("input[name='equipmentGroup']").val(equipmentGroup);
	$("input[name='year']").val(year);
	$("input[name='month']").val(month);
	getCheckDataDetail(year, month, equipmentGroup);
	loadAbnormalTable(year,month,'',equipmentGroup,'');
}

function initTabbableLine(){
	var equipmentGroupID = $("#equipmentGroupID").html();
	if(!equipmentGroupID==""){
		$("a[data-toggle='tab'][equipmentgroup='"+equipmentGroupID+"']").click();
	}else{
		var li = $("ul.nav-tabs").children("li").get(0);
		$(li).children("a").click();
	}

}

function setDate(){
	var yearS = $("#yearS").html();
	var year = parseInt(yearS);
	var monthS = $("#monthS").html();
	var  obj=document.getElementById("year");
	for (var i =0; i<12; i++){
		obj.add( new  Option( ""+year , ""+year));
		year--;
	}
	$("#year").val(yearS);
	$("#month").val(monthS);
	$("input[name='year']").val(year);
	$("input[name='month']").val(month);
}
function dateChange(){
	$("#yearS").html($("#year").val());
	$("#monthS").html($("#month").val());
	$("#month2").html($("#month").val());
	$("input[name='year']").val($("#year").val());
	$("input[name='month']").val($("#month").val());
	var year = $("#year").val();
	var month = $("#month").val();
	var li = $("ul.nav-tabs").children("li").filter(".active");
	var a = $(li).children("a");
	var equipmentGroup = $(a).attr("equipmentGroup");
	getCheckDataDetail(year, month, equipmentGroup);
	loadAbnormalTable(year,month,'',equipmentGroup,'');
}