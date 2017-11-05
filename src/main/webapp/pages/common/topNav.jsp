<%@page pageEncoding="UTF-8"%>
		<%
			String userName = (String) session.getAttribute("userName");
			String userID = (String)session.getAttribute("userID");
			String department = (String) session.getAttribute("department");
		%>
		<div class="page-header navbar navbar-fixed-top">
            <!-- BEGIN HEADER INNER -->
            <div class="page-header-inner ">
                <!-- BEGIN LOGO -->
                <div class="page-logo">
                    <a href="index.html">
                        <img src="styles/layouts/layout/img/logo.png" alt="logo" class="logo-default" /> </a>
                    <div class="menu-toggler sidebar-toggler"> </div>
                </div>
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <div class="top-menu">
                    <ul class="nav navbar-nav pull-right">
                        <!-- BEGIN USER LOGIN DROPDOWN -->
                        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                        <li class="dropdown dropdown-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <i class="icon-user"></i>
                                <span class="username username-hide-on-mobile"> <span id="userID">[<%=userID%>]</span><%=userName%> <%=department %></span>
                            </a>
                        </li>
                        <li class="dropdown dropdown-user">
                            <a href="logout">
                                <i class="icon-key"></i>
                                <span class="username username-hide-on-mobile">退出</span>
                            </a>
                        </li>
                       <li class="dropdown dropdown-user">
                        	   <a id="theme" href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <i class="icon-settings"></i>
                            </a>
                        </li>
                        <script type="text/javascript">
                        		$(function(){
                        			var themeShow=true;
                        			$("#theme").click(function(){
	                        			 if(themeShow){
	                        			 	$(".toggler").hide(); 
	                        			 	$(".toggler-close").show(); 
	                        				$(".theme-panel > .theme-options").show();
	                        				themeShow=false;
	                        			 }else{
	                        			 	$(".toggler").show();
	                        			 	$(".toggler-close").hide(); 
	                        				$(".theme-panel > .theme-options").hide();
	                        				themeShow=true;
	                        			 }
                        			});
                        		});
                        </script>
                        <!-- END USER LOGIN DROPDOWN -->
                    </ul>
                </div>
                <!-- END TOP NAVIGATION MENU -->
            </div>
            <!-- END HEADER INNER -->
        </div>