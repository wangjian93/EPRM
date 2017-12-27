<%@page pageEncoding="UTF-8"%>
			<div class="page-sidebar-wrapper">
                <!-- BEGIN SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div class="page-sidebar navbar-collapse collapse">
                    <!-- BEGIN SIDEBAR MENU -->
                    <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
                    <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
                    <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
                    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                    <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
                    <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                    <ul class="page-sidebar-menu  page-header-fixed page-sidebar-menu-closed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
                        <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
                        <li class="sidebar-toggler-wrapper hide">
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                            <div class="sidebar-toggler"> </div>
                            <!-- END SIDEBAR TOGGLER BUTTON -->
                        </li>
                        <li module="first" class="nav-item start">
                            <a href="javascript:;" class="nav-link nav-toggle">
                                <i class="icon-home"></i>
                                <span class="title">报表</span>
                                <span class="selected"></span>
                            </a>
                            
                        </li>
                        <li module="abnormal" class="nav-item">
                        		<a href="javascript:;" class="nav-link nav-toggle">
                                <i class="icon-layers"></i>
                                <span class="title">设备异常报表</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                        <li class="heading " id="now">
                            <h3 class="uppercase">昨日设备妥善率状况</h3>
                        </li>
                        <li class="heading " id="history">
                            <h3 class="uppercase">历史数据记录</h3>
                        </li>
                        <li class="heading " id="sys">
                            <h3 class="uppercase">系统管理</h3>                   
                        </li>
                        <li module="equipmentManage" class="nav-item">
                        		<a class="nav-link nav-toggle">
                        			<i class="icon-wrench"></i>
                        			<span class="title">设备管理</span>
                        			<span></span>
                        		</a>
                        	</li>
                        	<li module="spec" class="nav-item">
                        		<a class="nav-link nav-toggle">
                        			<i class="icon-paper-plane"></i>
                        			<span class="title">SPEC管理</span>
                        			<span></span>
                        		</a>
                        	</li>
                    </ul>
                    <!-- END SIDEBAR MENU -->
                    <!-- END SIDEBAR MENU -->
                </div>
                <!-- END SIDEBAR -->
            </div>