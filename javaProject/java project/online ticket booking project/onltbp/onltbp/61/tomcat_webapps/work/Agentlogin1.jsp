 
<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<HTML> 
	<HEAD>
		<TITLE>Bluewega Travels Admin Login Form</TITLE>
	</HEAD>
	<BODY>
		<TABLE WIDTH="100%" HEIGHT="100%" BORDER="0">
			<TR>
				<TD>
					<TABLE WIDTH="90%" BORDER="0" ALIGN="CENTER">
						<TR>
							<TD ALIGN="LEFT" COLSPAN="3">
								<FONT FACE="VERDANA" SIZE="+1" COLOR="#00366C"><B>Agent Login</B></FONT> <BR><HR WIDTH="100%" SIZE="2" COLOR="#00366C">
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER" VALIGN="TOP"><BR><BR>
					<html:form action="Agentlogin">
					<TABLE WIDTH="81%" ALIGN="CENTER" BORDER="1" CELLPADDING="3">
						<TR>
							<TD COLSPAN="3" ALIGN="LEFT">
								<TABLE WIDTH="52%" BORDER="0">
									<TR>
										<TD ALIGN="RIGHT">
											<FONT COLOR="#640032" FACE="VERDANA" SIZE="2">Enter your ID and Password to Login</FONT><BR>
										</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
						
						<TR>
							<TD COLSPAN="3" ALIGN="LEFT" VALIGN="MIDDLE" HEIGHT="9%">
								<TABLE WIDTH="49%" BORDER="0">
									<TR>
										<TD ALIGN="RIGHT">
											<FONT COLOR="RED" SIZE="2"><html:messages id="msgs" message="true" property="upstar"><%=msgs%></html:messages></FONT>&nbsp;<FONT COLOR="#00366C" FACE="VERDANA" SIZE="1"><html:messages id="msgs" message="true" property="uperror"><%=msgs%></html:messages></FONT>&nbsp;<FONT COLOR="RED" SIZE="2"><html:messages id="msgs" message="true" property="upstar"><%=msgs%></html:messages></FONT>
										</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
						
						<TR>
							<TD ALIGN="RIGHT" WIDTH="27%" nowrap>
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
								<B><bean:message key="name"/> :</B>
								</FONT>
							</TD>
							<TD ALIGN="LEFT" WIDTH="27%">
								<html:text property="name"/>
							</TD>
							<TD ALIGN="LEFT" NOWRAP><FONT COLOR="#00366C" FACE="VERDANA" SIZE="1"><html:errors property="name"/></FONT></TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="27%">
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
								<B><bean:message key="pass"/> :</B>
								</FONT>
							</TD>
							<TD ALIGN="LEFT" WIDTH="27%">
								<html:password property="pass"/>
							</TD>
							<TD ALIGN="LEFT" NOWRAP><FONT COLOR="#00366C" FACE="VERDANA" SIZE="1"><html:errors property="pass"/></FONT></TD>
						</TR>
						<TR>
							<TD COLSPAN="3" ALIGN="LEFT"><BR>
<!--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:submit>&nbsp;&nbsp;Login&nbsp;&nbsp;</html:submit>
								&nbsp;&nbsp;&nbsp;
								<html:reset>&nbsp;Reset&nbsp;</html:reset>
								&nbsp;&nbsp;&nbsp;
								<html:cancel>Cancel</html:cancel>-->
								<TABLE WIDTH="51%" BORDER="0">
								<TR>
									<TD ALIGN="RIGHT">
										<html:submit>&nbsp;&nbsp;Login&nbsp;&nbsp;</html:submit>&nbsp;&nbsp;&nbsp;
										<html:reset>&nbsp;Reset&nbsp;</html:reset>&nbsp;&nbsp;&nbsp;
										<html:cancel>Cancel</html:cancel><BR><BR>
									</TD>
								</TR>
							</TABLE>
								
							</TD>
						</TR>
						<TR>
						<TD COLSPAN="3" ALIGN="LEFT" HEIGHT="18%">
							<TABLE WIDTH="50%" BORDER="0">
								<TR>
									<TD ALIGN="CENTER">
<FONT FACE=VERDANA SIZE=2 COLOR="#051B53">Fresh to Bluewega Travels?&nbsp;&nbsp;<A HREF="AgentRegistration.jsp" TITLE="Registration Form" onMouseOver="window.status=('Registration Form'); return true;" onMouseOut="window.status=(' '); return true;"><FONT FACE=VERDANA SIZE=2><bean:message key="userreg"/></FONT></A></FONT><BR><FONT FACE=VERDANA SIZE=2 COLOR="#051B53">Forgot Password?&nbsp;&nbsp;<A HREF="AgentForgotPwd.jsp" TITLE="Forgot Password" onMouseOver="window.status=('Forgot Password'); return true;" onMouseOut="window.status=(' '); return true;"><FONT FACE=VERDANA SIZE=2><bean:message key="forpwd"/></FONT></A></FONT>

									</TD>
								</TR>
							</TABLE>
						</TD>
						</TR>
					</TABLE>
					</html:form>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>