
<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<HTML> 
	<HEAD>
		<TITLE>Bluewega Travels User Login Form</TITLE>
	</HEAD>
	<BODY>
		<CENTER>
			<FONT COLOR="GREEN">
			<html:messages id="msgs" message="true"><%=msgs%></html:messages>
			</FONT>
		</CENTER>
		<TABLE WIDTH="100%" HEIGHT="100%" BORDER="0">
			<TR>
				<TD>
					<TABLE WIDTH="90%" BORDER="0" ALIGN="CENTER">
						<TR>
							<TD ALIGN="LEFT" COLSPAN="3">
								<FONT FACE="VERDANA" SIZE="+1" COLOR="#00366C"><B>User Login</B></FONT><BR><HR WIDTH="100%" SIZE="2" COLOR="#00366C">
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER" VALIGN="TOP"><BR><BR>
					<TABLE WIDTH="63%" ALIGN="LEFT" BORDER="0">
						<TR>
							<TD>
							<html:form action="Userlogin">
							<TABLE WIDTH="63%" ALIGN="RIGHT" BORDER="0" CELLPADDING="3">
								<TR>
									<TD COLSPAN=3 ALIGN=CENTER>
										<FONT COLOR="#640032" FACE="VERDANA" SIZE="2">Enter your ID and Password to Login</FONT><BR><BR>
									</TD>
								</TR>
								<TR>
									<TD ALIGN="RIGHT" WIDTH="36%">
										<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
										<B><bean:message key="name"/> :</B>
										</FONT>
									</TD>
									<TD>
										<html:text property="name"/><html:errors property="name"/>
									</TD>
								</TR>
								<TR>
									<TD ALIGN="RIGHT" WIDTH="36%">
										<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
										<B><bean:message key="pass"/> :</B>
										</FONT>
									</TD>
									<TD>
										<html:password property="pass"/><html:errors property="pass"/>
									</TD>
								</TR>
								<TR>
									<TD COLSPAN="3" ALIGN="CENTER"><BR>
										<html:submit>&nbsp;&nbsp;Login&nbsp;&nbsp;</html:submit>
										&nbsp;&nbsp;&nbsp;
										<html:reset>&nbsp;Reset&nbsp;</html:reset>
										&nbsp;&nbsp;&nbsp;
										<html:cancel>Cancel</html:cancel>
									</TD>
								</TR>
								<TR>
									<TD COLSPAN="3" ALIGN="CENTER"><BR><BR>
<FONT FACE=VERDANA SIZE=2 COLOR="#051B53">Fresh to Bluewega Travels?&nbsp;&nbsp;<A HREF="UserRegistration.jsp" TITLE="Registration Form" onMouseOver="window.status=('Registration Form'); return true;" onMouseOut="window.status=(' '); return true;"><FONT FACE=VERDANA SIZE=2><bean:message key="userreg"/></FONT></A></FONT><BR><FONT FACE=VERDANA SIZE=2 COLOR="#051B53">Forgot Password?&nbsp;&nbsp;<A HREF="UserForgotPwd.jsp" TITLE="Forgot Password" onMouseOver="window.status=('Forgot Password'); return true;" onMouseOut="window.status=(' '); return true;"><FONT FACE=VERDANA SIZE=2><bean:message key="forpwd"/></FONT></A></FONT>
									</TD>
								</TR>
							</TABLE>
							</html:form>
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>