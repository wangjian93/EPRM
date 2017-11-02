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
			
			
			              visitors = [
			                    [1, 60],
			                    [2, 50],
			                    [3, 50],
			                    [4, 50],
			                    [5, 50],
			                    [6, 50],
			                    [7, 50],
			                    [8, 50],
			                    [9, 50],
			                    [10, 50],
			                    [11, 50],
			                    [12, 50],
			                    [13, 50],
			                    [14, 50],
			                    [15, 50],
			                    [16, 50],
			                    [17, 50],
			                    [18, 50],
			                    [19, 50],
			                    [20, 50],
			                    [21, 50],
			                    [22, 50],
			                    [23, 50],
			                    [24, 50],
			                    [25, 50],
			                    [26, 50],
			                    [27, 50],
			                    [28, 50],
			                    [29, 50],
			                    [30, 50],
			                    [31, 50]
			                ];
			  			

		  plot.setData([{
                data: pageviews,
                label: "妥善率",
                lines: {
                    lineWidth: 2,
                },
                shadowSize: 0

            }, {
                data: visitors,
                label: "标准",
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
}
function dateChange(){
	$("#yearS").html($("#year").val());
	$("#monthS").html($("#month").val());
	$("#month2").html($("#month").val());
	var year = $("#year").val();
	var month = $("#month").val();
	var li = $("ul.nav-tabs").children("li").filter(".active");
	var a = $(li).children("a");
	var equipmentGroup = $(a).attr("equipmentGroup");
	getCheckDataDetail(year, month, equipmentGroup);
	loadAbnormalTable(year,month,'',equipmentGroup,'');
}