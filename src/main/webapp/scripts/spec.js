$(function(){
	
	menuInit();
	setSpecTable();
});

function setSpecTable(){
	getSpec();
}

function getSpec(){
	$.ajax({
		url:'getSpec.do',
		data:{
			
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
	for(var i=0; i<data.length; i++){
		var spec = data[i];
		var tr = $("<tr></tr>");
		var td0 =$("<td></td>");
		td0.html(i);
		var td1 = $("<td></td>");
		td1.html(spec.depClassName);
		var td2 = $("<td></td>");
		td2.html(spec.equipmentGroupName);
		var td3 = $("<td></td>");
		td3.html(spec.spec);
		var td4 = $("<td></td>");
		td4.html("");
		var td5 = $("<td></td>");
		var a = $("<a></a>");
		
		$(a).addClass("btn btn-outline btn-circle dark btn-sm black");
		$(a).attr("href","javascript:;");
////		$(a).attr("value",equipmentID);
		var ii = $("<i></i>");
		$(ii).addClass("fa fa-trash-o");
		a.append(ii).append(" 修改");
		td5.append(a);
////		$(a).click(function(){
////			deleteEquipment(this);
////		});
		tr.append(td0).append(td1).append(td2).append(td3).append(td5);
		$("#spec").append(tr);
	}
}