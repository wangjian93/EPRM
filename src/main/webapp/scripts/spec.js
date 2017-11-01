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
	$("#spec").empty();
	for(var i=0; i<data.length; i++){
		var spec = data[i];
		var tr = $("<tr></tr>");
		var td0 =$("<td></td>");
		td0.html(i+1);
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
		$(a).attr("equipmentGroup",spec.equipmentGroup);
		
		var ii = $("<i></i>");
		$(ii).addClass("fa fa-trash-o");
		a.append(ii).append(" 修改");
		td5.append(a);
		$(a).click(function(){
			var equipmentGroupID = $(this).attr("equipmentGroup");
			showModal(equipmentGroupID);
		});
		tr.append(td0).append(td1).append(td2).append(td3).append(td5);
		$("#spec").append(tr);
	}
}

function showModal(equipmentGroupID){
	if(equipmentGroupID!=""){
		$.ajax({
		    url:'getEquipmentGroupAndDepClass.do',
		    type:'POST',
		    data:{
		    		"equipmentGroupID" : equipmentGroupID
		    },
		    dataType:'JSON',
		    success:function(data){
		    		$("input[name='equipmentGroupID']").val(data.equipmentGroupID);
		    		$("input[name='equipmentGroupName']").val(data.equipmentGroupName);
		    		$("input[name='depClassID']").val(data.depClassID);
		    		$("input[name='depClassName']").val(data.depClassName);
		    		$("input[name='equipmentName']").val("");
		    		$("#updateSpec").modal('show');
		    },
		    error:function(data){
		    		alert("Spec修改失败，未能与服务器连接");
		    }
		});
		
	}else{
		alert("请先选择要添加设备所属课别和所属设备系统");
	}
}

function updateSpec(){
	var equipmentGroupID = $("input[name='equipmentGroupID']").val();
	var depClassID = $("input[name='depClassID']").val();
	var spec =  $("input[name='spec']").val();
	if(!checkRate(spec)){
		alert("请输入数字");
		return;
	}
	if(spec>1 || spec<0){
		alert("输入的值不再范围内（spec值须在0到1之间）");
		return;
	}
	$.ajax({
	    url:'updateSpec.do',
	    type:'POST',
	    data:{
	    		"equipmentGroupID" : equipmentGroupID,
	    		"depClassID" : depClassID,
	    		"spec" : spec
	    },
	    dataType:'JSON',
	    success:function(data){
	    	$("#updateSpec").modal('hide');
	    	setSpecTable();
	    },
	    error:function(data){
	    		alert("Spec值修改失败，未能与服务器连接");
	    }
	});
}

//判断字符串是否为数字
function checkRate(value)
{
     var re = /^[0-9]+.?[0-9]*$/;   //判断字符串是否为数字     //判断正整数 /^[1-9]+[0-9]*]*$/  
     if (!re.test(value))
    {
        return false;
     }else{
    	 return true;
     }
}