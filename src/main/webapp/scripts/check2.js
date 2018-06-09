
$(function(){
    initDate();
    menuInit();
    getCheckForm();
    initAbnormalTable();
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

    for(var i=0; i<checkFormItem.length; i++){
        var equipmentName = checkFormItem[i].equipmentName;
        var equipmentID = checkFormItem[i].equipmentID;
        var checkResult = checkFormItem[i].checkResult;
        var checkResultStatus;
        if(checkResult =="AB" || checkResult == "SB") {
            checkResultStatus = checkResult;
        } else {
            checkResultStatus = "R";
        }
        var checkResultNumber;
        if(checkResult =="AB" || checkResult == "SB") {
            checkResultNumber = 0;
        } else {
            checkResultNumber = checkResult;
        }

        var div = $("<div class='col-md-4 form-group'></div>");
        var label = $("<div><label class='control-label'>" + equipmentName + "</label></div>");

        var equipmentIDa = equipmentID + "a";
        var equipmentIDb = equipmentID + "b";

        var disabled = "";
        if(checkResult=="AB" || checkResult=="SB") {
            disabled = "disabled";
        }
        var inputGroup = $("<div class='input-group'>\n" +
            "            <div class='input-group-btn'>\n" +
            "                <button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'>\n" +
            "                    <span id='" + equipmentIDa + "'>"+checkResultStatus+"</span>\n"+
            "                    <span class='caret'></span>\n" +
            "                    <span class='sr-only'>Toggle Dropdown</span>\n" +
            "                </button>\n" +
            "                <ul class='dropdown-menu pull-right' role='menu'>\n" +
            "                    <li><a onclick=\"selectStatus('R','"+equipmentID+"')\">Running</a></li>\n" +
            "                    <li><a onclick=\"selectStatus('SB','"+equipmentID+"')\">Standby</a></li>\n" +
            "                    <li><a onclick=\"selectStatus('AB','"+equipmentID+"')\">AbNormal</a></li>\n" +
            "                </ul>\n" +
            "            </div>\n" +
            "            <input " + disabled + " onchange=\"changeRate('"+equipmentID+"')\" id='"
                            + equipmentIDb + "' type='text' class='form-control demo5' value='" + checkResultNumber + "'>\n" +
            "        </div>" +
            "        <input hidden type='text' id='" + equipmentID + "' name='" + equipmentID + "' value='" + checkResult + "'>");

        div.append(label).append(inputGroup);
        $("#checkForm").append(div);
    }

    var max = (100/checkFormItem.length).toFixed(2);
    $(".demo5").TouchSpin({
        min: 0,
        max: max,
        step: 0.01,
        decimals: 2,
        boostat: 5,
        maxbootstedstep: 10,
        postfix: '%'
    });
    getProperRate();
}
function selectStatus(status, equipmentID) {
    $("#"+equipmentID+ "a").html(status);
    if(status =="AB" || status == "SB") {
        $("#" + equipmentID).val(status);
        $("#" + equipmentID+ "b").attr('disabled', true);
    } else {
        $("#" + equipmentID).val(0.00);
        $("#" + equipmentID+ "b").attr('disabled', false);
    }
    $("#" + equipmentID+ "b").val(0.00);
    getProperRate();
}
function changeRate(equipmentID){
    var value= $("#"+equipmentID+"b").val();
    var status = $("#" + equipmentID + "a").html();
    if(status !="AB" && status != "SB") {
        $("#" + equipmentID).val(value);
    }
    getProperRate();
}
function getProperRate(){
    var all=0;
    $(".demo5").each(function(){
            var result = $(this).val();
            all = all + parseFloat(result);
        });
    var properRate = (all/100).toFixed(2);
    $("#properRate").html(all.toFixed(0)+"%");
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
