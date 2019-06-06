 
<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<HTML> 
	<HEAD>
		<TITLE>Bluewega Travels</TITLE>
	</HEAD>
	<BODY>
		<TABLE WIDTH="100%" HEIGHT="100%" BORDER="0">
			<TR>
				<TD>
					<TABLE WIDTH="90%" BORDER="0" ALIGN="CENTER">
						<TR>
							<TD ALIGN="LEFT" COLSPAN="3">
								<FONT FACE="VERDANA" SIZE="+1" COLOR="#00366C"><B>Add a Bus</B></FONT> <BR><HR WIDTH="100%" SIZE="2" COLOR="#00366C">
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER" VALIGN="TOP"><BR><BR>
					<html:form action="AddBusType">
					<TABLE WIDTH="63%" ALIGN="CENTER" BORDER="0" CELLPADDING="3">
						<TR>
							<TD ALIGN="RIGHT" WIDTH="32%">
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
								<B><bean:message key="tname"/> :</B>
								</FONT>
							</TD>
							<TD>
								<html:text property="tname"/><html:errors property="tname"/>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="32%">
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
								<B><bean:message key="capacity"/> :</B>
								</FONT>
							</TD>
							<TD>
								<html:text property="capacity"/><html:errors property="capacity"/>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="32%">
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
								<B><bean:message key="desc"/> :</B>
								</FONT>
							</TD>
							<TD>
								<html:text property="desc"/><html:errors property="desc"/>
							</TD>
						</TR>
						<TR>
							<TD COLSPAN="2" ALIGN="LEFT"><BR>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:submit>&nbsp;&nbsp;Submit&nbsp;&nbsp;
								</html:submit>
								&nbsp;&nbsp;&nbsp;
								<html:reset>&nbsp;Reset&nbsp;</html:reset>
								&nbsp;&nbsp;&nbsp;
								<html:cancel>Cancel</html:cancel>
							</TD>
						</TR>
					</TABLE>
					</html:form>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>