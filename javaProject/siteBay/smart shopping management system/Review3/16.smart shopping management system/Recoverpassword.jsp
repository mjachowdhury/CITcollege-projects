<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>E-Acquistion recover password Page</title>
		</head>
<script>
function validate()
{
if(document.forms[0].username.value=="")       /*for username*/
{
alert("the User NAME cannot be left blank");
document.forms[0].username.focus();
return false;
}
if(document.forms[0].username.value!="")       /*for   username*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].username.value;
str_len=str.length;

for(var i=0;i<str_len;i++)
{
k=str.charCodeAt(i);
if( ((k>96)&&(k<123))||((k>64)&&(k<91))||(k==32)  )
{
  m=1;
}
else 
{
 m=2;
break;
}
}
if(m==2)
{
alert("please fill the user name with ONLY CHARACTERS");
document.forms[0].username.focus();
return false;
 }
}
</script>

	<body>
		<!-- start header -->
		<!--<div id="logo">
			<h1><jsp:include page="header.html" /></h1>
		</div>
		<div id="menu">
			<ul>
				<jsp:include page="generaloptions.html" />
			</ul>
		</div>
		<!-- end header -->
		<!-- start page -->
		<div id="page">
			<div id="page-bg">
				<!-- end content -->
				<!-- start sidebar -->
				<div id="sidebar1">
						<center>	<h2>
								RecoverPassword
							</h2>
						</center>
								<FORM action="RecoverPasswordAction.jsp" method="post" name="register">
			<TABLE border="0" align="center">

				<TR>
					<TD>
						<TABLE border="0" align="center">

							<TR>
								<TD align="right"></TD>
								<TD>
									<TABLE border="0" align="center">
										<TR>
											<TD colspan=2>
												Login Name
											</TD>
											<TD>
												<INPUT type="text" name="username"/>
											</TD>
										</TR>
										<TR>
											<TD colspan=2>
												Secret Question
											</TD>
											<TD colspan=2>
												<SELECT name="squest">
													<OPTION value="1">
														What is your favorite pastime?
													</OPTION>
													<OPTION value="2">
														Who was your childhood hero?
													</OPTION>
													<OPTION value="3">
														What was the name of your first school?
													</OPTION>
													<OPTION value="4">
														Where did you meet your spouse?
													</OPTION>
													<OPTION value="5">
														What is your favorite sports team?
													</OPTION>
													<OPTION value="6">
														What is your father's middle name?
													</OPTION>
													<OPTION value="7">
														What was your high school mascot?
													</OPTION>
													<OPTION value="8">
														What make was your first car or bike?
													</OPTION>
													<OPTION value="9">
														What is your pet's name?
													</OPTION>
												</SELECT>
											</TD>
										</TR>
										<TR>
											<TD colspan=2>
												Secret Answer
											</TD>
											<TD>
												<INPUT name="sanswer" type="text"/>
											</TD>
										</TR>
										<TR>
											<TD colspan="2">
												<DIV align="center">
													<INPUT name="Input" type="submit" value="Recover" onclick="return validate()"/>
													&nbsp;</td></DIV>
													<TD colspan="2">
											
											<DIV align="center">
											   <INPUT name="Input" type="reset" value="Clear"/>
												</td></DIV>
											
										</TR>
									</TABLE>
								</TD>
								<TD>
									&nbsp;
								</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
							</form>
						
				</div>
				<div style="clear: both;">
					&nbsp;
				</div>
			</div>
		</div>
		<!-- end page -->
		<!--<div id="footer">
			<p id="legal">
				<a href="#">Privacy Policy</a> |
				<a href="#">Terms of Use</a>
			</p>
		</div>-->
	</body>
</html>

