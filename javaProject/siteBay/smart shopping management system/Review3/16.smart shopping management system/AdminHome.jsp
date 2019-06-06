<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title> E-Acquistion AdminHome Page</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<!-- start header -->
<div id="logo">
	<h1><jsp:include page="header.html"/></h1>
	</div>
 <div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/> 
	</ul>
</div> 
<!-- end header -->
<!-- start page -->
<div id="page">
  <div id="page-bg">
		<div id="latest-post">
		  <h1>Welcome to Our Website!</h1>
	  </div>
		<!-- start content -->
	<!-- end content -->
	<!-- start sidebar -->
<div id="sidebar">
			<ul>
				 <%
					String user=(String)session.getAttribute("user");
				 %>
					<h2>Welcome <%=user%></h2>
				
				
				  <form id="form1" method="post" action="">
				    <table width="238" border="0" align="center">
                      <tr>
                        welcome to your E-Acquistion homepage
                      </tr>
                    </table>
                  </form>
				
				
					</ul>
		</div>
	  <div style="clear: both;">&nbsp;</div>
	</div>
</div>
<!-- end page -->
<div id="footer">
			<p id="legal">
				<a href="#">Privacy Policy</a> 
				<a href="#">Terms of Use</a>
			</p>
		</div>
</body>
</html>
