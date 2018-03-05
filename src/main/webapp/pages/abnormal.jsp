 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    
    <!-- BEGIN HEAD -->
    <head>
    	    <title>设备异常记录</title>
        <jsp:include page="common/fileList.jsp"/>
        <script src="scripts/test2.js" type="text/javascript"></script>
        
        <style type="text/css">
        	.table > thead > tr > th {
    			vertical-align: middle;
    			}
    		.table > tbody > tr > td {
    			vertical-align: middle;
    			}
    		.table > thead > tr > th{
        			padding: 4px;
        		}
    		.table > tbody > tr > td{
        			padding: 4px;
        		}
        	.table td{
        			font-size: 12px;
        	}
        	.table thead tr th{
        			font-size: 12px;
        	}
			.btn {
				padding: 2px 2px 2px 2px;
			}
			.input-small {
				width: 110px !important;
			}
			.checkbox-inline {
				padding-left: 0px !important;
			}
        </style>
    </head>
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <!-- BEGIN HEADER -->
        <jsp:include page="common/topNav.jsp"/>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <jsp:include page="common/leftNav.jsp"/>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                    <!-- BEGIN THEME PANEL -->
                    <jsp:include page="common/themePanel.jsp"/>
                    <!-- END THEME PANEL -->
                    <!-- BEGIN PAGE BAR -->
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <a>首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                             <li>
                                <a>设备异常记录</a>
                                <i class="fa fa-circle"></i>
                            </li>
                        </ul>
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">
                    		
                    	</h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12 ">
                            <!-- BEGIN SAMPLE FORM PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-layers font-blue-sharp"></i>
                                        <span class="caption-subject font-blue-sharp bold uppercase"><span id="equipmentGroupName"></span>设备异常记录</span>
                                    </div>
                                    <div class="actions">
                                		   <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;"></a>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                		<div style="padding-left:10px;">
                                		   <span>时间:</span>
                                        <select id="year" class="input-small">
												<option value="0">---All---</option>
                                        		<option value="1015">2015</option>
                                		   		<option value="2016">2016</option>
                                        		<option value="2017">2017</option>
                                        		<option value="2018">2018</option>
                                		   		<option value="2019">2019</option>
                                		   		<option value="2020">2020</option>
                                		   		<option value="2021">2021</option>
                                		   		<option value="2022">2022</option>
                                        		<option value="2023">2023</option>
                                		   		<option value="2024">2024</option>
                                		   		<option value="2025">2025</option>
                                		   		<option value="2026">2026</option>
                                		   		<option value="2027">2027</option>
                                        </select>
                                		   <span>年</span>
                                		   <select id="month" class="input-small">
											   	<option value="0">---All---</option>
                                		   		<option value="1">1</option>
                                		   		<option value="2">2</option>
                                		   		<option value="3">3</option>
                                		   		<option value="4">4</option>
                                		   		<option value="5">5</option>
                                		   		<option value="6">6</option>
                                		   		<option value="7">7</option>
                                		   		<option value="8">8</option>
                                		   		<option value="9">9</option>
                                		   		<option value="10">10</option>
                                		   		<option value="11">11</option>
                                		   		<option value="12">12</option>
                                        </select>
                                		   <span>月</span>
                                		   <span>&nbsp;&nbsp;</span>
                                		    <span>&nbsp;&nbsp;</span>
                                		   <span>课别:</span>
                                		   <select id="deptClass" onchange="selectEquipmentGroup()" class="input-small">
                                		   		<option value="0">---All---</option>
                                		   		<option value="1">模组厂务课</option>
                                		   		<option value="2">仪电课</option>
                                		   		<option value="3">气化课</option>
                                		   		<option value="4">机械空调课</option>
                                		   		<option value="5">设施课</option>
                                		   		<option value="6">水务课</option>
                                		   </select>
                                		   <span>&nbsp;&nbsp;</span>
                                		    <span>&nbsp;&nbsp;</span>
                                		   <span>系统:</span>
                                		   <select id="equipmentGroup" class="input-small">
                                		   		<option value="0">---All---</option>
                                		   </select>
											<span>&nbsp;&nbsp;</span>
											<span>&nbsp;&nbsp;</span>

											<span>是否完成:</span>
											<label class="checkbox-inline">
												<input type="radio" name="ifCompleted" value="0" checked> 否
											</label>
											<label class="checkbox-inline">
												<input type="radio" name="ifCompleted" value="1" > 是
											</label>


											<button type="button" class="btn btn-primary" onclick="selectData()">查找</button>
                                		</div>
                                		<div class="table-scrollable">
	                                		<table style="text-align:center;cursor:pointer;" class="table table-bordered table-hover table-striped  flip-content">
	                                        <thead   class="bg-grey bg-font-grey">
	                                        		<tr style="text-align:center;" >
	                                        			<th style="text-align:center;width:8%;">日期</th>
	                                        			<th style="text-align:center;width:8%;">课</th>
	                                        			<th style="text-align:center;width:8%;">系统</th>
	                                            		<th style="text-align:center;width:8%;">设备编号</th>
	                                            		<th style="text-align:center;width:15%;">异常状况详述</th>
	                                            		<th style="text-align:center;width:15%;">拟定解决方案</th>
	                                            		<th style="text-align:center;width:6%;">预计完成时间</th>
	                                            		<th style="text-align:center;width:6%;">实际完成时间</th>
	                                            		<th style="text-align:center;width:6%;">是否完成</th>
	                                            		<th style="text-align:center;width:8%;">备注</th>
	                                            		<th style="text-align:center;width:6%;">工程师</th>
	                                            		<th style="text-align:center;width:6%;">修改</th>
                                            		</tr>
	                                        </thead>
	                                        <tbody id="abnormalTable">
	                                        		
	                                        </tbody>
	                                    </table>
                                    </div>  	  
                                </div>
                            </div>
                            <!-- END SAMPLE FORM PORTLET-->
                        </div>
                    </div>
                  
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
            <%-- <jsp:include page="common/quick.jsp"/> --%>
            <!-- END QUICK SIDEBAR -->
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <jsp:include page="common/footer.jsp"/>
        <!-- END FOOTER -->

		<!-- 模态框 -->
		<div id="responsive" class="modal fade" tabindex="-1" data-width="760">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">添加异常记录</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">日期<span class="required" aria-required="true">*</span> ：</label>
							<div class="col-md-4">
								<div class='input-group date' id='datetimepicker1'>
									<input type='text' class="form-control" readonly name="dates_a"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">设备编号 <span class="required" aria-required="true">*</span> ：</label>
							<div class="col-md-5">
								<select class="form-control" name="equipmentID_a">

								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">异常详述<span class="required" aria-required="true">*</span> ：</label>
							<div class="col-md-9">
								<textarea class="form-control" rows="3" name="sipecification_a"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								工程师<span class="required" aria-required="true">*</span> :
							</label>
							<div class="col-md-5">
								<input class="form-control" name="engineer_a" onclick="empTree()"></input>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">解决方案：</label>
							<div class="col-md-9">
								<textarea class="form-control" rows="3" name="solutions_a"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">预计完成时间：</label>
							<div class="col-md-4">
								<div class='input-group date' id='datetimepicker2'>
									<input type='text' class="form-control" readonly name="expectedTime_a"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">是否完成：</label>
							<div class="col-md-2">
								<select class="form-control" name="ifCompleted_a">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
								<!-- <input class="form-control"  type="text" name="ifCompleted_b"> -->
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">实际完成时间：</label>
							<div class="col-md-4">
								<div class='input-group date' id='datetimepicker3'>
									<input type='text' class="form-control" readonly name="actualTime_a"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">备注：</label>
							<div class="col-md-9">
								<textarea class="form-control" rows="3" name="memo_a"></textarea>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
				<button type="button" class="btn green" onclick="submitAbnormal()">保存</button>
			</div>
		</div>

		<!-- 模态框 -->
		<div id="responsive2" class="modal fade" tabindex="-1" data-width="760">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">修改异常记录</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<input type="text" name="abnormalID_b" hidden/>
					<input type="text" name="equipmentID" hidden/>
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">日期：</label>
							<div class="col-md-4">
								<div class='input-group date' id='datetimepicker4'>
									<input type='text' class="form-control" readonly name="dates_b"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">设备编号：</label>
							<div class="col-md-5">
								<input class="form-control" name="equipmentID_b" readonly></input>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">异常详述：</label>
							<div class="col-md-9">
								<textarea class="form-control" readonly rows="3" name="sipecification_b"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">工程师：</label>
							<div class="col-md-4">
								<input class="form-control" readonly name="engineer_b" onclick="empTree()"></input>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">解决方案：</label>
							<div class="col-md-9">
								<textarea class="form-control" rows="3" name="solutions_b"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">预计完成时间：</label>
							<div class="col-md-4">
								<div class='input-group date' id='datetimepicker5'>
									<input type='text' class="form-control" name="expectedTime_b"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">是否完成：</label>
							<div class="col-md-2">
								<select class="form-control" name="ifCompleted_b">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
								<!-- <input class="form-control"  type="text" name="ifCompleted_b"> -->
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">实际完成时间：</label>
							<div class="col-md-4">
								<div class='input-group date' id='datetimepicker6'>
									<input type='text' class="form-control" name="actualTime_b"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">备注：</label>
							<div class="col-md-9">
								<textarea class="form-control" rows="3" name="memo_b"></textarea>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
				<button type="button" class="btn green" onclick="deleteAbnormal()">删除</button>
				<button type="button" class="btn green" onclick="modifyAbnormal()">保存</button>
			</div>
		</div>

		<div id="responsive3" class="modal fade" tabindex="-1" data-width="300">
			<div class="portlet light bordered">
				<!--人员组织树-->
				<div id="memo"></div>
				<div id="treeArea"></div>
			</div>
		</div>

	</body>
</html>