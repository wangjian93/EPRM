<%@page pageEncoding="UTF-8"%>
		<meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" /> -->
        <link href="styles/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="styles/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="styles/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="styles/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
        <link href="styles/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="styles/global/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
        <link href="styles/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
        <link href="styles/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
        <link href="styles/global/plugins/jquery-nestable/jquery.nestable.css" rel="stylesheet" type="text/css" />
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="styles/global/css/components-rounded.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="styles/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="styles/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="styles/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <!-- <link href="styles/layouts/layout/css/themes/grey.min.css" rel="stylesheet" type="text/css" id="style_color"/> -->
        <!-- <link href="styles/layouts/layout/css/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color"/> -->
        
        <link href="styles/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" />
        
        
        <!--[if lt IE 9]>
		<script src="styles/global/plugins/respond.min.js"></script>
		<script src="styles/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="styles/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="styles/global/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
        <script src="styles/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
        
        <script src="styles/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/flot/jquery.flot.pie.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/flot/jquery.flot.stack.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/flot/jquery.flot.crosshair.min.js" type="text/javascript"></script>
        <script src="styles/global/plugins/flot/jquery.flot.axislabels.js" type="text/javascript"></script>
        <script src="styles/global/plugins/jquery-nestable/jquery.nestable.js" type="text/javascript"></script>
        
        <link href="styles/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />  
		<script src="styles/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script> 
		<script src="styles/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>  
        
        <script src="styles/global/plugins/bootstrap-treeview.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="styles/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="styles/pages/scripts/components-bootstrap-select.min.js" type="text/javascript"></script>
        <script src="styles/pages/scripts/charts-flotcharts.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="styles/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="styles/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="styles/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        
        <!-- BEGIN NAV INIT -->
        	<script src="scripts/common/leftNav.js" type="text/javascript"></script>
        <!-- END NAC INIT -->
        <script type="text/javascript">
        		<%
        			String module = (String)request.getAttribute("module");
        			String groupID=(String)request.getAttribute("groupID");
        			String depClassID = (String)request.getAttribute("depClassID");
        		%>
        		var module = "<%=module%>";
        		var groupID = "<%=groupID%>";
        		var depClassID = "<%=depClassID%>";
        </script>

        <!--公司人员组织树-->
        <!-- xtree -->
<%--<script type="text/javascript" src="styles/jquery/jquery-1.9.1.min.js"></script>--%>
<script type="text/javascript" src="styles/jquery/ext/jquery.json-2.4.min.js"></script>
<%--<script type="text/javascript" src="styles/jquery/ui/jquery-ui.min.js"></script>--%>
<%--<link rel="stylesheet" type="text/css" href="styles/jquery/ui/css/jquery-ui.min.css" />--%>
<link rel="stylesheet" type="text/css" href="styles/jquery/ext/css/clear.css" />
<script type="text/javascript" src="styles/jquery/xquery.js"></script>
<script type="text/javascript" src="styles/jquery/ext/xgrid.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery/ext/css/xgrid.css" />
<script type="text/javascript" src="styles/jquery/ext/xpanel.js"></script>
<script type="text/javascript" src="styles/jquery/ext/xwindow.js"></script>
<script type="text/javascript" src="styles/jquery/ext/ajaxform.js"></script>
<script type="text/javascript" src="styles/jquery/ext/pop.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery/ext/css/pop.css" />
<script type="text/javascript" src="styles/jquery/ext/message.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery/ext/css/message.css" />
<!-- xtree -->
<script type="text/javascript" src="styles/jquery/ext/tree/ztree/jquery.ztree.core-3.5_001.min.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery/ext/tree/ztree/css/zTreeStyle.css" />
<script type="text/javascript" src="styles/jquery/ext/tree/xtree.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery/ext/tree/css/ztree-ex.css" />
<link rel="stylesheet" type="text/css" href="styles/jquery/ext/tree/css/xtree.css" />


<link href="styles/jquery.bootstrap-touchspin.css" rel="stylesheet">
<script src="styles/jquery.bootstrap-touchspin.js"></script>
