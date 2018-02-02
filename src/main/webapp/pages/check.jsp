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
    	    <title>每日记录</title>
        <jsp:include page="common/fileList.jsp"/>
        <script src="scripts/check.js" type="text/javascript"></script>
        	<script src="scripts/abnormal.js" type="text/javascript"></script>
        <style type="text/css">
        		.bootstrap-select{
        			width:70%;
        		}
        		.bootstrap-select.btn-group {
    				min-width: 70%;
    			}
        </style>
    </head>
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white ">
    		<div style="display:none;">
        		<input name="year" value="${year }"/>
        		<input name="month" value="${month }"/>
        		<input name="day" value="${day }"/>
        		<input name="equipmentGroup" value="${equipmentGroup }"/>
        		<input name="trackingNumber" value="${trackingNumber}" style="display:none"/>
        </div> 
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
                                <a href="index.html">今日任务</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span>${depOfClassName}</span>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                            		<span>${equipmentGroupName}</span>
                            </li>
                        </ul>
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">
                    		${trackingNumber}
                    		<small>请各夜班同事在每日下班前将（前一天）８：00-８：00（当日））设备运转状况填写完毕</small>
                    	</h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12 ">
                            <!-- BEGIN SAMPLE FORM PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-social-dribbble font-blue-sharp"></i>
                                        <span class="caption-subject font-blue-sharp bold uppercase">${equipmentGroupName}设备妥善性日记录</span>
                                    </div>
                                    <div class="actions" style="color:#5C9BD1;">
                                        时间${year}年${month}月${day}日
                                    </div>
                                </div>
                                <div class="portlet-body form">
                                    <form role="form" action="saveCheckForm.do" method="post">
                                        <div class="form-body">
                                        		<div id="checkForm" class="row">
                                        			
                                            </div>
                                        </div>
                                        <div class="form-actions bg-default bg-font-default" style="padding-left: 10px;">
                                        		<div >
                                        			<label>妥善率:<span id="properRate"></span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        			<div class="hidden">
                                        				<input name="properRate"  id="properRate" />
                                        				<input name="trackingNumber" value="${trackingNumber}" />
                                        			</div> 
                                        			<label>R:<span id="R"></span></label>&nbsp;&nbsp;&nbsp;&nbsp;
                                        			<label>S:<span id="S"></span></label>&nbsp;&nbsp;&nbsp;&nbsp;
                                        			<label>SB:<span id="SB"></span></label>&nbsp;&nbsp;&nbsp;&nbsp;
                                        			<label>AB:<span id="AB"></span></label>
                                        			<!-- <label><span class="btn btn-xs btn-danger pull-right">R:</span> <span id="R"></span>&nbsp;&nbsp;</label>
                                        			<label><span class="btn btn-xs btn-danger pull-right">S:</span> <span id="S"></span>&nbsp;&nbsp;</label>
                                        			<label><span class="btn btn-xs btn-danger pull-right">SB:</span> <span id="SB"></span>&nbsp;&nbsp;</label>
                                        			<label><span class="btn btn-xs btn-danger pull-right">AB:</span> <span id="AB"></span>&nbsp;&nbsp;</label> -->
                                        		</div>
                                        		<div>
                                      			<button type="submit" class="btn blue">提交</button>
                                        		</div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- END SAMPLE FORM PORTLET-->
                            <!-- BEGIN SAMPLE FORM PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-bubble font-green-sharp"></i>
                                        <span class="caption-subject font-green-sharp bold uppercase">异常记录</span>
                                        <small>${equipmentGroupName}设备${month}月份异常记录</small>
                                    </div>
                                    <!-- <div class="actions">
                                        <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                            <i class="icon-cloud-upload"></i>
                                        </a>
                                        <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                            <i class="icon-wrench"></i>
                                        </a>
                                        <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                            <i class="icon-trash"></i>
                                        </a>
                                        <a class="btn btn-outline dark" data-toggle="modal" href="#responsive" onclick="setEquipment()" > 添加 </a>
                                    </div> -->
                                    <div class="actions">
                                        <a class="btn btn-circle btn-default btn-sm" href="javascript:;" onclick="addShowModal()">
                                            <i class="fa fa-plus"></i> Add </a>
                                        <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;"></a>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <table id="abnormal" class="table table-bordered table-striped table-condensed flip-content">
                                        <thead class="flip-content">
                                            <tr>
                                            		<th>日期</th>
                                            		<th>设备编号</th>
                                            		<th>异常状况详述</th>
                                            		<th>拟定解决方案</th>
                                            		<th>预计完成时间</th>
                                            		<th>实际完成时间</th>
                                            		<th>是否完成</th>
                                            		<th>备注</th>
                                            		<th>工程师</th>
                                            		<th>修改</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        		
                                        </tbody>
                                		</table>
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
									<input type='text' class="form-control"  name="dates_a"/>
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
									<input type='text' class="form-control"  name="expectedTime_a"/>
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
									<input type='text' class="form-control"  name="actualTime_a"/>
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
                             	<input class="form-control" readonly name="equipmentID_b"></input>
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
									<input class="form-control" readonly name="engineer_b"></input>
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
									<input type='text' class="form-control"  name="expectedTime_b"/>
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
									<input type='text' class="form-control"  name="actualTime_b"/>
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