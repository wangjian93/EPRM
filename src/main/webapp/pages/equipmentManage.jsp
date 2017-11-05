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
    	    <title>设备管理</title>
        <jsp:include page="common/fileList.jsp"/>
        <script src="scripts/equipmentManage.js" type="text/javascript"></script>
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
                                <a>系统管理</a>
                                <i class="fa fa-circle"></i>
                            </li>
                             <li>
                                <a>设备管理</a>
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
                            <div class="portlet light ">
                                                <!-- PROJECT HEAD -->
                                                <div class="portlet-title">
                                                    <div class="caption">
                                                        <i class="icon-bar-chart font-green-sharp hide"></i>
                                                        <span class="caption-helper">GlobalEx Tasks:</span> &nbsp;
                                                        <span class="caption-subject font-green-sharp bold uppercase">Tune Website</span>
                                                    </div>
                                                    
                                                </div>
                                                <!-- end PROJECT HEAD -->
                                                <div class="portlet-body">
                                                    <div class="row">
                                                        <div class="col-md-3 col-sm-2">
                                                        	   <div class="container-fluid">
														        <div id="tree"></div>
														    </div>
                                                        </div>
                                                        <div class="todo-tasklist-devider"> </div>
                                                        <div class="col-md-7 col-sm-8">
                                                            <div class="portlet box green">
						                                        <div class="portlet-title">
						                                            <div class="caption">
						                                                <i class="fa fa-cogs"></i>Responsive Flip Scroll Tables 
						                                            </div>
						                                            <div class="actions">
                                        <a class="btn btn-circle btn-default btn-sm" href="javascript:;" onclick="showModal()" >
                                            <i class="fa fa-plus"></i> 设备添加 </a>
                                        <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;"></a>
                                    </div>
						                                            
						                                        </div>
						                                        <div class="portlet-body flip-scroll">
						                                            <table class="table table-bordered table-striped table-condensed flip-content">
						                                                <thead class="flip-content">
						                                                    <tr>
						                                                        <th width="2%">  </th>
						                                                        <th> 课别 </th>
						                                                        <th class="numeric"> 系统 </th>
						                                                        <th class="numeric"> 设备名称 </th>
						                                                        
						                                                        <th class="numeric"> 操作 </th>
						                                                        
						                                                    </tr>
						                                                </thead>
						                                                <tbody id="equipment">
						                                                    
						                                                </tbody>
						                                            </table>
						                                        </div>
                                                            </div>
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
        
        <!-- 模态框 -->
                                    <div id="responsive" class="modal fade" tabindex="-1" data-width="460">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                            <h4 class="modal-title">添加设备</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form class="form-horizontal" role="form">
                                            		<div class="form-body">
                                            			<div class="form-group">
                                                			<label class="col-md-3 control-label">所属课别：</label>
                                                			<div class="col-md-5">
																<input class="form-control input-sm" type="text" name="depClassName" placeholder="输入设备编号" readonly>
																<input name="depClassID" type="text" value="" hidden/>						
                                                			</div>
                                            			</div>
                                            			<div class="form-group">
                                                			<label class="col-md-3 control-label">所属系统：</label>
                                                			<div class="col-md-5">
																<input class="form-control input-sm" type="text" name="equipmentGroupName" placeholder="输入设备编号" readonly>						
                                                				<input name="equipmentGroupID" type="text" value="" hidden/>
                                                			</div>
                                            			</div>
                                           				<div class="form-group">
                                                			<label class="col-md-3 control-label">设备编号：</label>
                                                			<div class="col-md-5">
																<input class="form-control input-sm" type="text" name="equipmentName" placeholder="输入设备编号">						
                                                			</div>
                                            			</div>
                                            		</div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
                                            <button type="button" class="btn green" onclick="addEquipment()">保存</button>
                                        </div>
                                    </div>  
    </body>
</html>