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
                    <%-- <jsp:include page="common/themePanel.jsp"/> --%>
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
                                		List<EquipmentGroup> equipmentGroupList = (List<EquipmentGroup>)request.getAttribute("equipmentGroupList");
                                		for(int i=0; i<equipmentGroupList.size(); i++){
                                			EquipmentGroup equipmentGroup = equipmentGroupList.get(i);
                                		%>
                                    <li >
                                        <a data-toggle="tab" onclick="selectTabbableLine(this)" equipmentGroup="<%=equipmentGroup.getEquipmentGroupID() %>"><%=equipmentGroup.getEquipmentGroupName()%></a>
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
                                        <small><span id="month2"></span>月份--<span id="equipmentGroupName2"></span>设备异常记录</small>
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
                                        <!-- <a class="btn btn-circle btn-default btn-sm" data-toggle="modal" href="#responsive" >
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
                                            </tr>
                                        </thead>
                                        <tbody>
                                        		<!-- <tr>
                                        			<td>2017-9-13</td>
                                        			<td>开立冰机1#</td>
                                        			<td>故障</td>
                                        			<td>待维修</td>
                                        			<td>2017-10-1</td>
                                        			<td>2017-9-20</td>
                                        			<td>是</td>
                                        			<td></td>
                                        		</tr>
                                        		<tr>
                                        			<td>2017-9-13</td>
                                        			<td>开立冰机1#</td>
                                        			<td>故障</td>
                                        			<td>待维修</td>
                                        			<td>2017-10-1</td>
                                        			<td>2017-9-20</td>
                                        			<td>是</td>
                                        			<td></td>
                                        		</tr>
                                        		<tr>
                                        			<td>2017-9-13</td>
                                        			<td>开立冰机1#</td>
                                        			<td>故障</td>
                                        			<td>待维修</td>
                                        			<td>2017-10-1</td>
                                        			<td>2017-9-20</td>
                                        			<td>是</td>
                                        			<td></td>
                                        		</tr>
                                        		<tr>
                                        			<td>2017-9-13</td>
                                        			<td>开立冰机1#</td>
                                        			<td>故障</td>
                                        			<td>待维修</td>
                                        			<td>2017-10-1</td>
                                        			<td>2017-9-20</td>
                                        			<td>是</td>
                                        			<td></td>
                                        		</tr>
                                        		<tr>
                                        			<td>2017-9-13</td>
                                        			<td>开立冰机1#</td>
                                        			<td>故障</td>
                                        			<td>待维修</td>
                                        			<td>2017-10-1</td>
                                        			<td>2017-9-20</td>
                                        			<td>是</td>
                                        			<td></td>
                                        		</tr> -->
                                        </tbody>
                                		</table>
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
		                                                <label class="col-md-3 control-label">日期：</label>
		                                                <div class="col-md-2">
		                                                    <input class="form-control" name="dates_a" type="text"> 
		                                                </div>
		                                            </div>
                                           			<div class="form-group">
                                                			<label class="col-md-3 control-label">设备编号：</label>
                                                			<div class="col-md-2">
                                                    			<select class="form-control" name="equipmentID_a">
		                                                        <option>Option 1</option>
		                                                        <option>Option 2</option>
		                                                        <option>Option 3</option>
		                                                        <option>Option 4</option>
		                                                        <option>Option 5</option>
		                                                    </select>
                                                			</div>
                                            			</div>
                                            			<div class="form-group">
		                                                <label class="col-md-3 control-label">异常详述：</label>
		                                                <div class="col-md-9">
		                                                    <textarea class="form-control" rows="3" name="sipecification_a"></textarea>
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
		                                                <div class="col-md-2">
		                                                    <input class="form-control"  type="text" name="expectedTime_a"> 
		                                                </div>
		                                            </div>
		                                            <div class="form-group">
		                                                <label class="col-md-3 control-label">实际完成时间：</label>
		                                                <div class="col-md-2">
		                                                    <input class="form-control"  type="text" name="actualTime_a"> 
		                                                </div>
		                                            </div>
		                                            <div class="form-group">
		                                                <label class="col-md-3 control-label">是否完成：</label>
		                                                <div class="col-md-2">
		                                                    <input class="form-control"  type="text" name="ifCompleted_a"> 
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
    </body>
</html>