<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<HTML>
	<BODY>
		<TABLE WIDTH="100%" HEIGHT="100%" BORDER="0">
			<TR>
				<TD VALIGN="TOP">
					<TABLE WIDTH="90%" BORDER="0" ALIGN="CENTER">
						<TR>
							<TD ALIGN="LEFT"><BR>
								<FONT FACE="VERDANA" SIZE="+1" COLOR="#00366C"><B>Forgot Password</B></FONT>
								<HR WIDTH="100%" SIZE="2" COLOR="#00366C">
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER" VALIGN="TOP">
				<html:form action="newpassA">
					<TABLE WIDTH="90%" BORDER="0" ALIGN="CENTER" BGCOLOR="#F3E3E3" CELLPADDING="4">
						<TR>
							<TD COLSPAN="2"><P ALIGN="JUSTIFY">
								<FONT FACE="VERDANA" SIZE="2">This process is meant for the security of your account, Answer a few questions to get a New Password.</FONT>
							</TD>
						</TR>
						<TR>
							<TD COLSPAN="2"><P ALIGN="JUSTIFY">
								<FONT FACE="VERDANA" SIZE="2"><FONT COLOR=RED> Note:</FONT> This information should be the same as that provided when you created your Bluewega account previouslly.</FONT><BR><BR>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="36%">
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2"><B> <bean:message key="login"/> :</B>
								</FONT>
							</TD>
							<TD>
								<html:text property="login"/><FONT COLOR=RED SIZE="2">*</FONT><html:errors property="login"/>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="36%">
								<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B> <bean:message key="secque"/> : </B></FONT>
							</TD>
							<TD>
								<html:select property="secque">
									<html:option value="0">[Select a Question]</html:option>
									<html:option value="1">What is your nick name?</html:option>
									<html:option value="2">What is your pet's name?</html:option>
									<html:option value="3">What was the name of your first school?</html:option>
									<html:option value="4">Who was your childhood hero?</html:option>
									<html:option value="5">What is the exact time of Birth?</html:option>
									<html:option value="6">What is your favourite past-time?</html:option>
									<html:option value="7">What is your favourite food?</html:option>
									<html:option value="8">What is your all-time favourite sports team?</html:option>
									<html:option value="9">Who is your favourite Teacher?</html:option>
									<html:option value="10">Which is your favourite Movie?</html:option>
									<html:option value="11">What is your father's middle name?</html:option>
									<html:option value="12">What was your high school mascot?</html:option>
									<html:option value="13">What make was your first car or bike?</html:option>
									<html:option value="14">Where did you first meet your spouse?</html:option>
								</html:select><FONT COLOR=RED SIZE="2">*</FONT>
								<html:errors property="secque"/>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="36%"> <FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="secans" /> : </B></FONT>
							</TD>
							<TD>
								<html:text property="secans"/><FONT COLOR=RED SIZE="2">*</FONT><html:errors property="secans"/>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="36%"> <FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B>New <bean:message key="pass"/> : </B></FONT>
							</TD>
							<TD>
								<html:password property="pass"/><FONT COLOR=RED SIZE="2">*</FONT><html:errors property="pass"/>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="36%"><FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="rtpass"/> : </B></FONT>
							</TD>
							<TD>
								<html:password property="rtpass"/><FONT COLOR=RED SIZE="2">*</FONT><html:errors property="rtpass"/>
							</TD>
						</TR>
						<TR>
							<TD COLSPAN="2" ALIGN="CENTER"><BR>
								<TABLE BORDER="0" WIDTH="54%">
									<TR>
										<TD WIDTH="9%"></TD>
										<TD ALIGN="LEFT">
											<html:submit>&nbsp;&nbsp;Login&nbsp;&nbsp;
											</html:submit>
											&nbsp;&nbsp;&nbsp;
											<html:reset>&nbsp;Reset&nbsp;</html:reset>
											&nbsp;&nbsp;&nbsp;
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
<!--	<TABLE WIDTH="100%" HEIGHT="30" BORDER="0">
		<TR>
			<TD WIDTH="2%">
			</TD>
			<TD>
				<FONT FACE="VERDANA" SIZE="+1" COLOR="#00366C"><B>Forgot Password</B> </FONT>
			</TD>
			<TD WIDTH="2%" ALIGN="RIGHT">
				<FONT COLOR="DARKBLUE" SIZE="1"><B>[</B></FONT>
			</TD>
			<TD WIDTH="4%" ALIGN="RIGHT">
				<A HREF="Userlogin.jsp" TITLE="To get Login Form" onMouseOver="window.status=('To get Login Form'); return true;" onMouseOut="window.status=(' '); return true;"><FONT FACE="VERDANA" SIZE="1" COLOR="#00366C"><B>Login</B></FONT></A>
			</TD>
			<TD WIDTH="2%" ALIGN="LEFT">
				<FONT COLOR="DARKBLUE" SIZE="1"><B>]</B></FONT>
			</TD>
		</TR>
	</TABLE>
	<HR WIDTH="99%" SIZE="2" COLOR="#00366C">-->
	</BODY>
</HTML>