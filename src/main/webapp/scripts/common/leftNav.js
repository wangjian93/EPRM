
function menuInit(){
	getMenu();
	
}

function getMenu(){
	$.ajax({
		url:'initMenu.do',
	    type:'POST', 
	    data:{
	    },
	    dataType:'json', 
	    success:function(data){
	    		setMenu(data);
	    },
	    error:function(data){
	        alert('服务器menu数据获取错误');
	    }
	});
}

function setMenu(data){
	for(var depOfClass in data){
		var eqGroup = data[depOfClass];
		var item = SetNow(depOfClass,eqGroup);
		$("#now").after(item);
		var item2 = SetHistory(depOfClass,eqGroup);
		$("#history").after(item2);
	}
	setActive();
	setMenuClick();
}
function setActive(){
	
	if(module=="first" || module==null){
		$("li[module='first']").addClass("active open");
		$("li[module='first']").children("a").children("span:last-child").addClass("selected");
	}else if(module=="now"){
		$("li[groupid='"+groupID+"']").addClass("active");
		$("li[groupid='"+groupID+"']").parent().parent().addClass("active open");
		$("li[groupid='"+groupID+"']").parent().siblings('a')
					.children("span:last-child").addClass("open");
		$("li[groupid='"+groupID+"']").parent().siblings('a')
			.children("span:last-child").prepend($("<span class='selected'></span>"));
	}else if(module=="history"){
		$("li[depClassID='"+depClassID+"']").addClass("active open");
		$("li[depClassID='"+depClassID+"']").children("a").children("span:last-child").addClass("selected");
	}else if(module=="equipmentManage"){
		$("li[module='equipmentManage']").addClass("active open");
		$("li[module='equipmentManage']").children("a").children("span:last-child").addClass("selected");
	}else if(module=="spec"){
		$("li[module='spec']").addClass("active open");
		$("li[module='spec']").children("a").children("span:last-child").addClass("selected");
	}else{
		$("li[module='first']").addClass("active open");
		$("li[module='first']").children("a").children("span:last-child").addClass("selected");
	}
	
}
function SetHistory(depOfClass, eqGroup){
	var item = $("<li></li>");
	item.addClass("nav-item");
	item.attr("module","history");
	if(eqGroup[0]!=null){
		item.attr("depClassID",eqGroup[0].class_fk);
	}
    var a = $("<a></a>");
    a.addClass("nav-link nav-toggle");
    var i = $("<i></i>");
    i.addClass("icon-diamond");
    var span = $("<span></span>");
    span.addClass("title");
    span.html(depOfClass);
    var span2 = $("<span></span>");
    a.append(i).append(span).append(span2);
    item.append(a);
    return item;
}

function SetNow(depOfClass,eqGroup){
	var item = $("<li></li>");
	item.addClass("nav-item");
    var a = $("<a></a>");
    a.addClass("nav-link nav-toggle");
    var i = $("<i></i>");
    i.addClass("icon-diamond");
    var span = $("<span></span>");
    span.addClass("title");
    span.html(depOfClass);
    var span2 = $("<span></span>");
    span2.addClass("arrow");
    a.append(i).append(span).append(span2);
    item.append(a);
    var sub = $("<ul></ul>");
    	sub.addClass("sub-menu");
    for(var i=0; i<eqGroup.length;i++){
    		var li = SetSubNav(eqGroup[i].equipmentGroupName, eqGroup[i].equipmentGroupID, eqGroup[i].properRate);
    		sub.append(li); 
    }
    item.append(sub);
    return item;
}
function SetSubNav(groupName, groupID, properRate){ 
    var li = $("<li></li>");
    li.addClass("nav-item menu");
    li.attr("module","now");
    li.attr("groupID",groupID);
    li.attr("groupName",groupName);
    var a = $("<a></a>");
    a.addClass("nav-link");
    var span = $("<span></span>");
    span.addClass="title";
    span.html(groupName);
    var span2 = $("<span></span>");
    if(properRate==0 || properRate=="0"){
    		span2.addClass("badge badge-danger");
        span2.html("未完成");
    }else{
    		console.log("properRate:"+properRate);
    		console.log("properRate:"+properRate*100+"%");
    		span2.addClass("badge badge-success");
        span2.html((properRate*100).toFixed(0)+"%");
    }
    a.append(span).append(span2);
    li.append(a);
    return li;
}

function setMenuClick(){
	$(".menu").bind("click",function(event){
		menuClick(this); 
		event.stopPropagation(); 
	});
	$("li[module='first']").bind("click",function(event){
		menuClick(this); 
		event.stopPropagation(); 
	});
	
	$("li[module='history']").bind("click",function(event){
		menuClick(this); 
		event.stopPropagation(); 
	});
	
	$("li[module='equipmentManage']").bind("click",function(event){
		menuClick(this); 
		event.stopPropagation(); 
	});
	
	$("li[module='spec']").bind("click",function(event){
		menuClick(this); 
		event.stopPropagation(); 
	});
}
function menuClick(element){
	var module = $(element).attr("module");
	var groupID = $(element).attr("groupid");
	var depClassID = $(element).attr("depClassID");
	window.location.href="goCheck.do?module="+module
	+"&groupID="+groupID+"&depClassID="+depClassID;
}