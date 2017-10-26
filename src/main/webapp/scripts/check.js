var str = "[{'value':'R','name':'Runing'},{'value':'S','name':'Stop'},{'value':'SB','name':'Standby'},{'value':'AB','name':'AbNormal'}]"
var checkOption = eval("("+str+")");
$(function(){
	initDate();
	menuInit();
//	getCheckOption();
	getCheckForm();
	initAbnormalTable();
	setEquipment();
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
}

function getCheckForm(){
	$.ajax({
	    url:'getCheckForm.do',
	    type:'POST',
	    data:{
	    		"trackingNumber":$("input[name='trackingNumber']").val()
	    },
	    dataType:'JSON',
	    success:function(data){
	    		setCheckForm(data);
	    },
	    error:function(data){
	    		alert("设备获取失败，未能与服务器连接");
	    }
	});
}

function setCheckForm(data){
	if(data.length==0){
		
	}
	var checkFormItem = data[0].checkFormItem;
	var select = $("<select></select>");
	select.addClass("bs-select form-control input-small");
	select.attr("data-style","btn-success");
	for(var j=0; j<checkOption.length; j++){
		var option = $("<option></option>");
		option.html(checkOption[j].name);
		option.attr("value",checkOption[j].value);
		select.append(option);
	}
	
	for(var i=0; i<checkFormItem.length; i++){
		var div = $("<div></div>");
		div.addClass("col-md-3 form-group");
		var label = $("<label></label>");
		label.addClass("control-label");
		label.html(checkFormItem[i].equipmentName+" :");
		var lablediv = $("<div></div>");
		lablediv.append(label);
		var sel = select.clone();
		sel.attr("name",checkFormItem[i].equipmentID);
		sel.val(checkFormItem[i].checkResult);
		var str = checkFormItem[i].checkResult
		if(str=="R"){
			$(sel).attr("data-style","btn-danger");
			$(sel).siblings('button').attr("class","btn dropdown-toggle btn-danger");
		}else if(str=="S"){
			$(sel).attr("data-style","btn-success");
			$(sel).siblings('button').attr("class","btn dropdown-toggle btn-success");
		}else if(str=="SB"){
			$(sel).attr("data-style","btn-info");
			$(sel).siblings('button').attr("class","btn dropdown-toggle btn-info");
		}else if(str=="AB"){
			$(sel).attr("data-style","btn-warning");
			$(sel).siblings('button').attr("class","btn dropdown-toggle btn-warning");
		}else{
			$(sel).attr("data-style","btn-danger");
			$(sel).siblings('button').attr("class","btn dropdown-toggle btn-danger");
		}
		sel.change(function(){selectClick(this);});
		div.append(lablediv).append(sel);
		$("#checkForm").append(div);
	}
	
	$('select').selectpicker('render');
    $('select').selectpicker('refresh');
    getProperRate();
}
function getCheckOption(){
	$.ajax({
	    url:'getCheckOption.do',
	    type:'POST',
	    data:{},
	    dataType:'json',
	    success:function(data){
	    		caheckOption = data;
	    },
	    error:function(data){
	    		alert("下拉框选项获取失败，未能与服务器连接");
	    }
	});
}
function selectClick(select){
	var str =  select.value;
	
	if(str=="R"){
		$(select).attr("data-style","btn-danger");
		$(select).siblings('button').attr("class","btn dropdown-toggle btn-danger");
	}else if(str=="S"){
		$(select).attr("data-style","btn-success");
		$(select).siblings('button').attr("class","btn dropdown-toggle btn-success");
	}else if(str=="SB"){
		$(select).attr("data-style","btn-info");
		$(select).siblings('button').attr("class","btn dropdown-toggle btn-info");
	}else{
		$(select).attr("data-style","btn-warning");
		$(select).siblings('button').attr("class","btn dropdown-toggle btn-warning");
	}
	getProperRate();
}
function getProperRate(){
	var all=0;
	var R=0;
	var S=0;
	var SB=0;
	var AB=0;
	$("select").each(function(){
		var result = $(this).val();
	    if(result=="R"){
	    		R++;
	    		all++;
	    }else if(result=="S"){
	    		S++;
	    		all++;
	    }else if(result=="SB"){
	    		SB++;
	    		all++;
	    }else if(result=="AB"){
	    		AB++;
	    		all++;
	    }
	  });
	var properRate =(1- AB/all).toFixed(2);
	$("#R").html(R);
	$("#S").html(S);
	$("#SB").html(SB);
	$("#AB").html(AB);
	$("#properRate").html(properRate);
	$("input[name='properRate']").attr("value",properRate);
}
function initAbnormalTable(){
	var year = $("input[name='year']").val();
	var month = $("input[name='month']").val();
	var equipmentGroup = $("input[name='equipmentGroup']").val();
	var day = "";
	var equipmentID = "";
	loadAbnormalTable(year,month,day,equipmentGroup,equipmentID);
}