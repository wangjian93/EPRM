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
    	    <title>首页</title>
        <jsp:include page="common/fileList.jsp"/>
        <script src="scripts/test.js" type="text/javascript"></script>
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
                                <a>报表</a>
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
                                        <span class="caption-subject font-blue-sharp bold uppercase"><span id="equipmentGroupName"></span>厂务设备妥善率报表</span>
                                    </div>
                                    <div class="actions">
                                		   <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;"></a>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                		<div style="padding-left:10px;">
                                		   <span>时间:</span>
                                        <select id="year">
                                        		<option value="2017">2017</option>
                                		   		<option value="2016">2016</option>
                                		   		<option value="2015">2015</option>
                                		   		<option value="2014">2014</option>
                                		   		<option value="2013">2013</option>
                                		   		<option value="2012">2012</option>
                                		   		<option value="1011">2011</option>
                                		   		<option value="2010">2010</option>
                                		   		<option value="2009">2009</option>
                                		   		<option value="2008">2008</option>
                                		   		<option value="2007">2007</option>
                                		   		<option value="2006">2006</option>
                                        </select>
                                		   <span>年</span>
                                		   <select id="month">
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
                                		   <span>课别:</span>
                                		   <select id="deptClass" onchange="selectEquipmentGroup()">
                                		   		<option value="all">---All---</option>
                                		   		<option value="1">模组厂务课</option>
                                		   		<option value="2">仪电课</option>
                                		   		<option value="3">气化课</option>
                                		   		<option value="4">机械空调课</option>
                                		   		<option value="5">设施课</option>
                                		   		<option value="6">水务课</option>
                                		   </select>
                                		   <span>&nbsp;&nbsp;</span>
                                		   <span>系统:</span>
                                		   <select id="equipmentGroup">
                                		   		<option value="all">---All---</option>
                                		   		
                                		   </select>
                                		   <button onclick="selectData()">查找</button>
                                		</div>
                                		<div class="table-scrollable">
	                                		<table style="text-align:center;cursor:pointer;" class="table table-bordered table-hover table-striped  flip-content">
	                                        <thead   class="bg-grey bg-font-grey">
	                                        		<tr>
	                                        			<th style="text-align:center;" rowspan="2">课别</th>
	                                            		<th style="text-align:center;" rowspan="2">系统</th>
	                                            		<th style="text-align:center;" rowspan="2">SPEC</th>
	                                            		<th style="text-align:center;" colspan="31">日期</th>
	                                            </tr>
	                                            <tr style="text-align:center;">
		                                            <th>1日</th>
		                                            <th>2日</th>
		                                            <th>3日</th>
		                                            <th>4日</th>
		                                            <th>5日</th>
		                                            <th>6日</th>
		                                            <th>7日</th>
		                                            <th>8日</th>
		                                            <th>9日</th>
		                                            <th>10日</th>
		                                            <th>11日</th>
		                                            <th>12日</th>
		                                            <th>13日</th>
		                                            <th>14日</th>
		                                            <th>15日</th>
		                                            <th>16日</th>
		                                            <th>17日</th>
		                                            <th>18日</th>
		                                            <th>19日</th>
		                                            <th>20日</th>
		                                            <th>21日</th>
		                                            <th>22日</th>
		                                            <th>23日</th>
		                                            <th>24日</th>
		                                            <th>25日</th>
		                                            <th>26日</th>
		                                            <th>27日</th>
		                                            <th>28日</th>
		                                            <th>29日</th>
		                                            <th>30日</th>
		                                            <th>31日</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id="reportTable">
	                                        		
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
    </body>
</html>