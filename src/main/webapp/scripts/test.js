$(function(){
  
	menuInit();
	setDate();
	setReportTableData();
 
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
	
	if(deptClass=="all") deptClass="";
	if(equipmentGroup=="all") equipmentGroup="";
	getReportTableData(year,month,deptClass,equipmentGroup);
}

function getReportTableData(year,month,deptClass,equipmentGroup){
	$.ajax({
		url:'getReportData.do',
		data:{
			"year":year,
			"month":month,
			"deptClass":deptClass,
			"equipmentGroup":equipmentGroup
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
	$("#reportTable").empty();
	for(var i=0; i<data.length; i++){
		var className = data[i].className;
		var spec = data[i].spec;
		var reportTableData = data[i].checkFormMonth;
		for(var k=0; k<reportTableData.length; k++){
			var tr = $("<tr></tr>");
			$(tr).attr("equipmentGroup",reportTableData[k].equipmentGroupID);
			$(tr).attr("depClassID",reportTableData[k].depOfclassID);
			if(k==0){
				var td1 = $("<td></td>");
				td1.html(className);
				$(td1).attr("rowspan",reportTableData.length);
				tr.append(td1);
			}
			var td2 = $("<td></td>");
			td2.html(reportTableData[k].equipmentGroupName);
			var td3 = $("<td></td>");
			td3.html(reportTableData[k].spec*100+"%");
			tr.append(td2);
			tr.append(td3);
			for(var j=1; j<32; j++){
				var td = $("<td></td>");
				var day = "day"+j;
				//防止0.56 ，0.57，0.58时失精度
				var properRate = (reportTableData[k][day]*100).toFixed(0);  
				if(properRate==0){
					td.html("");
				}else{
					td.html(properRate+"%");
				}
				if(properRate<reportTableData[k].spec*100){
					//$(td).css({"background-color":"red"});
					$(td).addClass("font-red-flamingo");
				}
				tr.append(td);
			}
			$(tr).click(function(){
				var equipmentGroup = $(this).attr("equipmentGroup");
				var depClassID = $(this).attr("depClassID");
				var year = $("#year").val();
				var month = $("#month").val();
				window.location.href="historyView.do?equipmentGroup="+equipmentGroup
					+"&year="+year+"&month="+month+"&depClassID="+depClassID; 
			});
			$("#reportTable").append(tr);
		}
	}	
}
function selectData(){
	setReportTableData();
}
function selectEquipmentGroup(){
	var deptClass = $("#deptClass").val();
	if(deptClass=="all"){
		$("#equipmentGroup").empty();
		var optionAll = $("<option></option>");
		$(optionAll).attr("value","all");
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
				$(optionAll).attr("value","all");
				$(optionAll).html("---All---");
				$("#equipmentGroup").append(optionAll);
				for(var i=0;i<data.length; i++){
					var equipmentGroup = data[i];
					var option = $("<option></option>");
					$(option).attr("value",equipmentGroup.equipmentGroupID);
					$(option).html(equipmentGroup.equipmentGroupName);
					$("#equipmentGroup").append(option);
				}
			},
			error:function(data){
				alert("服务器连接失败，请重新登录再试");
			}
		});
	}
	
}