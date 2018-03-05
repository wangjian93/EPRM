var equipmentGroupID="";
function getEquipment(equipmentGroupID,depClassID,equipmentID){
	$.ajax({
	    url:'getEquipment.do',
	    type:'POST',
	    data:{
	    		"equipmentGroupID" : equipmentGroupID,
	    		"depClassID" : depClassID,
	    		"equipmentID" : equipmentID,
	    },
	    dataType:'JSON',
	    success:function(data){
	    		var total = data.total;
	    		reloadEquipmentTable(data.depCalss);
	    },
	    error:function(data){
	    		alert("设备获取失败，未能与服务器连接");
	    }
	});
}

function reloadEquipmentTable(depClass){
	$("#equipment").empty();
	for(var i=0; i<depClass.length; i++){
		var depClassID = depClass[i].depClassID;
		var depClassName = depClass[i].depClassName;
		var equipmentGroup = depClass[i].equipmentGroup;
		
		for(var j=0; j<equipmentGroup.length; j++){
			var equipmentGroupName = equipmentGroup[j].equipmentGroupName;
			var equipmentGroupID = equipmentGroup[j].equipmentGroupID;
			var equipment = equipmentGroup[j].equipment;

			for(var k=0; k<equipment.length; k++){
				var equipmentID = equipment[k].equipmentID;
				var	equipmentName = equipment[k].equipmentName;
				
				var tr = $("<tr></tr>");
				var td0 = $("<td></td>");
				td0.html(k+1);
				
				var td1 = $("<td></td>");
				td1.html(depClassName);
				//$(td).attr("mark", depClassID);
				
				var td2 = $("<td></td>");
				td2.html(equipmentGroupName);
				
				var td3 = $("<td></td>");
				td3.html(equipmentName);
				
				var td4 = $("<td></td>");
				
				var td5 = $("<td></td>");
				var a = $("<a></a>");
				
				$(a).addClass("btn btn-outline btn-circle dark btn-xs black");
				$(a).attr("href","javascript:;");
				$(a).attr("value",equipmentID);
				var i =$("<i></i>");
				$(i).addClass("fa fa-trash-o");
				a.append(i).append(" 删除");
				td5.append(a);
				$(a).click(function(){
					deleteEquipment(this);
				});
				tr.append(td0).append(td1).append(td2).append(td3)
					.append(td5);
				$("#equipment").append(tr);
			}
		}
	}
}

function setEquipmentTable(equipmentGroupID,depClassID,equipmentID){
	getEquipment(equipmentGroupID,depClassID,equipmentID);
}

jQuery(document).ready(function() {
   menuInit();
   //setEquipmentTable("","","");
   $('#tree').treeview({data: getTree()});
   $('#tree').treeview('collapseAll', { silent: true });
   customDblClickFun();
});

function getTree() {
	var tree;
	$.ajax({
	    url:'getTree.do',
	    type:'POST',
	    data:{},
	    dataType:'JSON',
	    async: false,  
	    success:function(data){
	    		tree = data;
	    },
	    error:function(data){
	    		alert("未能与服务器连接,请重新登录再试");
	    }
	});
    return tree;
}

//最后一次触发节点Id
var lastSelectedNodeId = null;
//最后一次触发时间
var lastSelectTime = null;

//自定义业务方法
function customBusiness(data){
	equipmentGroupID = data.id;
	setEquipmentTable(equipmentGroupID,"","","");
}

function clickNode(event, data) {
//    if (lastSelectedNodeId && lastSelectTime) {
//        var time = new Date().getTime();
//        var t = time - lastSelectTime;
//        if (lastSelectedNodeId == data.nodeId && t < 300) {
            customBusiness(data);
//        }
//    }
//    lastSelectedNodeId = data.nodeId;
//    lastSelectTime = new Date().getTime();
}
//自定义双击事件
function customDblClickFun(){
    //节点选中时触发
    $('#tree').on('nodeSelected', function(event, data) {
        clickNode(event, data);
    });
    //节点取消选中时触发
    $('#tree').on('nodeUnselected', function(event, data) {
        //clickNode(event, data);
    	equipmentGroupID = "";
    	setEquipmentTable("0","","","");
    });
}

function deleteEquipment(e){
	var equipmentID = $(e).attr("value");
	$.ajax({
	    url:'deleteEquipment.do',
	    type:'POST',
	    data:{
	    		"equipmentID" : equipmentID,
	    },
	    dataType:'JSON',
	    success:function(data){
	    		alert("移除成功");
	    		setEquipmentTable(equipmentGroupID,"","","");
	    		
	    },
	    error:function(data){
	    		alert("设备获取失败，未能与服务器连接");
	    }
	});
}

function showModal(){
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
		    		$("#responsive").modal('show');
		    },
		    error:function(data){
		    		alert("设备获取失败，未能与服务器连接");
		    }
		});
		
	}else{
		alert("请先选择要添加设备所属课别和所属设备系统");
	}
}

function addEquipment(){
	$("#responsive").modal('hide');
	$.ajax({
	    url:'addEquipment.do',
	    type:'POST',
	    data:{
	    		"equipmentGroupID" : $("input[name='equipmentGroupID']").val(),
	    		"depClassID" : $("input[name='depClassID']").val(),
	    		"equipmentName" : $("input[name='equipmentName']").val()
	    },
	    dataType:'JSON',
	    success:function(data){
	    	alert("设备添加成功");
	    	setEquipmentTable(equipmentGroupID,"","","");
	    },
	    error:function(data){
	    		alert("设备添加失败，未能与服务器连接");
	    }
	});
}