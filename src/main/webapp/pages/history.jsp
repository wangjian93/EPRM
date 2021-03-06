 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
 <%@ page import="com.ivo.model.equipment.EquipmentGroup" %>
 
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
    	    <title>历史记录</title>
        <jsp:include page="common/fileList.jsp"/>
        <script src="scripts/history.js" type="text/javascript"></script>
        <script src="scripts/abnormal.js" type="text/javascript"></script>
        <style type="text/css">
        		.bootstrap-select > .dropdown-toggle{
        			width:70%;
        		}
        		.table > tbody > tr > td{
        			padding: 4px;
        		}
        		.table td{
        			font-size: 12px;
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
                                <a href="index.html">历史记录</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span>${depOfClassName}</span>
                            </li>
                        </ul>
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <%-- <h3 class="page-title">
                    		${trackingNumber}
                    		<small>请各夜班同事在每日下班前将（前一天）８：00-８：00（当日））设备运转状况填写完毕</small>
                    		<input name="trackingNumber" value="${trackingNumber}" style="display:none"/>
                    	</h3> --%>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12 ">
                        		<div class="tabbable-line boxless tabbable-reversed">
                                <ul class="nav nav-tabs">
                                		<%
                                		List<Map<String, Object>> equipmentGroupList = (List<Map<String, Object>>)request.getAttribute("equipmentGroupList2");
                                		for(int i=0; i<equipmentGroupList.size(); i++){
											Map<String, Object> equipmentGroup = equipmentGroupList.get(i);
                                		%>
                                    <li >

										<%
											String mark = (String) equipmentGroup.get("mark");
											if( mark.equals("true")) { %>
											<a  data-toggle="tab" onclick="selectTabbableLine(this)"
												style="color: red;"
											   equipmentGroup="<%=equipmentGroup.get("equipmentGroupID") %>">
												<%=equipmentGroup.get("equipmentGroupName")%>
											</a>
										<% } else { %>
											<a data-toggle="tab" onclick="selectTabbableLine(this)"
											   equipmentGroup="<%=equipmentGroup.get("equipmentGroupID") %>">
												<%=equipmentGroup.get("equipmentGroupName")%>
											</a>
										<% } %>
									</li>
                                    <%} %>
                                    
                                </ul>
                            </div>
                            <!-- BEGIN SAMPLE FORM PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-social-dribbble font-blue-sharp"></i>
                                        <span class="caption-subject font-blue-sharp bold uppercase"><span id="equipmentGroupName"></span>设备妥善性月记录</span>
                                    </div>
                                    <div class="actions">
                                    	   <span id="equipmentGroupID" style="display:none;">${equipmentGroupID}</span>
                                        <span>时间:</span>
                                        <span id="yearS" style="display:none;">${year}</span>
                                        <select id="year" onchange="dateChange()">
                                        </select>
                                		   <span>年</span>
                                		   <span id="monthS" style="display:none;">${month}</span>
                                		   <select id="month" onchange="dateChange()">
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
                                		   <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;"></a>
                                		   	
                                    </div>
                                </div>
                                <div class="portlet-body">
                                		<!-- BEGIN INTERACTIVE CHART PORTLET-->
                            			<!-- <div class="portlet light portlet-fit bordered">
                                
                                			<div class="portlet-body"> -->
		                                    <div id="chart_2" class="chart"> </div>
		                                <!-- </div>
		                            </div> -->
                            		   <!-- END INTERACTIVE CHART PORTLET-->
                            		   <br>
                            		   <div class="table-scrollable">
	                                    <table id="history" style="text-align:center;" class="table table-bordered table-striped  flip-content">
	                                        <thead class="flip-content">
	                                        		<tr>
	                                            		<th style="text-align:center;" rowspan="2">设备编号</th>
	                                            		<th style="text-align:center;" colspan="31">日期</th>
	                                            </tr>
	                                            <tr style="text-align:center;">
	                                            <th>1</th>
	                                            <th>2</th>
	                                            <th>3</th>
	                                            <th>4</th>
	                                            <th>5</th>
	                                            <th>6</th>
	                                            <th>7</th>
	                                            <th>8</th>
	                                            <th>9</th>
	                                            <th>10</th>
	                                            <th>11</th>
	                                            <th>12</th>
	                                            <th>13</th>
	                                            <th>14</th>
	                                            <th>15</th>
	                                            <th>16</th>
	                                            <th>17</th>
	                                            <th>18</th>
	                                            <th>19</th>
	                                            <th>20</th>
	                                            <th>21</th>
	                                            <th>22</th>
	                                            <th>23</th>
	                                            <th>24</th>
	                                            <th>25</th>
	                                            <th>26</th>
	                                            <th>27</th>
	                                            <th>28</th>
	                                            <th>29</th>
	                                            <th>30</th>
	                                            <th>31</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id="history2">
	                                        		
	                                        </tbody>
	                                    </table>
	                            		</div>
                                </div>
                            </div>
                            <!-- END SAMPLE FORM PORTLET-->
                            <!-- BEGIN SAMPLE FORM PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-bubble font-green-sharp"></i>
                                        <span class="caption-subject font-green-sharp bold uppercase">异常记录</span>
                                        <small>
											<span id="month2" hidden></span><%--月份----%>
											<span id="equipmentGroupName2"></span>设备异常记录
										</small>
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
                                        <a class="btn btn-outline dark" data-toggle="modal" href="#responsive"> View Demo </a>
                                    </div> -->
                                    <div class="actions">
                                        <!-- <a class="btn btn-circle btn-default btn-sm" href="javascript:;" onclick="addShowModal()">
                                            <i class="fa fa-plus"></i> Add </a> -->
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