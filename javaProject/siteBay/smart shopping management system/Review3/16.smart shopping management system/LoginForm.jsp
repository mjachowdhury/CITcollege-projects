<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
	String password = null;
	password=request.getParameter("status");
	if(password==null)
	{
		password="family";
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title> Online Marketing Login Page</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link href="default.css" rel="stylesheet" type="text/css"
			media="screen" />
	</head>
	<body>
		<!-- start header -->
		<div id="logo">
			<h1><jsp:include page="header.html" /></h1>
		</div>
	<!-- 	<div id="menu">
			<ul>
				<jsp:include page="generaloptions.html" />
			</ul>
		</div> -->
		<!-- end header -->
		<!-- start page -->
		<div id="page">
			<div id="page-bg">
				<div id="latest-post">
					<h1>
						Welcome to Our Online Marketing
					</h1>
				</div>
				<!-- start content -->
				<div id="sidebar">
			<div class="post">
						<h2 class="title">
							<a href="#">About Online Marketing</a>						</h2>
						<div class="entry">
							<p>
								Enjoy Shopping with your <b><%=password%></b></p>
						</div>
						<p class="links">&nbsp;						</p>
					</div>
				</div>
				<!-- end content -->
				<!-- start sidebar -->
				<div id="content">
					<ul>
					<h2>
						Login
					</h2>
						<form action="LoginAction.jsp" method="post" name="register">
								<table width="200" border="0" align="center">
									<tr>
										<td><table border="0"><tr><td height="186" valign="baseline"><div align="center">
										  <table height="168" border="0">
                                            <tr>
                                              <td width="67" height="120" align="right"></td>
                                              <td width="212"><table border="0" align="center">
                                                  <tr>
                                                    <td> Username </td>
                                                    <td><input type="text" name="username"/>                                                    </td>
                                                  </tr>
                                                  <tr>
                                                    <td> Password </td>
                                                    <td><input type="password" name="password"/>                                                    </td>
                                                  </tr>
                                                  <tr>
                                                    <td colspan="2"><div align="center">
                                                        <input type="submit" name="Submit" value="Sign In"/>
                                                        <input name="Input2" type="reset" value="Clear"/>
                                                    </div></td>
                                                  </tr>
                                              </table></td>
                                              <td width="6">&nbsp;</td>
                                            </tr>
                                            <tr>
                                              <td>&nbsp;</td>
                                              <td valign="baseline"><div align="center"> <a href="Recoverpassword.jsp">Forgot Password !
                                                !......</a> </div></td>
                                              <td>&nbsp;</td>
                                            </tr>
                                            <tr><td>&nbsp;</td>
                                              <td valign="baseline"><div align="center"> <a href="Registerform.jsp">  signup!!......</a> </div></td>
                                            <td>&nbsp;</td><td>&nbsp;</td></tr>
                                          </table>
										  <a href="Registerform.jsp"></a></div>
													</td>
												</tr>
										</table>
									  </td>
									</tr>
								</table>
							</form>
						
					</ul>
				</div>
<div style="clear: both;">
					&nbsp;
				</div>
			</div>
		</div>
		<!-- end page -->
		<div id="footer">
			<p id="legal">
				<a href="#">Privacy Policy</a> |
				<a href="#">Terms of Use</a>
			</p>
		</div>
	</body>
</html>

