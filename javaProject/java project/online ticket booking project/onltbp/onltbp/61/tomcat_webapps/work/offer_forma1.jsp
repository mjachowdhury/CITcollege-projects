 
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
								<FONT FACE="VERDANA" SIZE="+1" COLOR="#00366C"><B>Add an Offer</B></FONT> <BR><HR WIDTH="100%" SIZE="2" COLOR="#00366C">
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER" VALIGN="TOP"><BR><BR>
					<html:form action="AddOfferA">
					<TABLE WIDTH="63%" ALIGN="CENTER" BORDER="0" CELLPADDING="3">
						<TR>
							<TD ALIGN="RIGHT" WIDTH="27%">
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
								<B><bean:message key="offername"/> :</B>
								</FONT>
							</TD>
							<TD>
								<html:text property="offername"/><html:errors property="offername"/>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="27%">
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
								<B><bean:message key="startdate"/> :</B>
								</FONT>
							</TD>
							<TD>
								<html:text property="startdate"/><FONT FACE="VERDANA" SIZE="1" COLOR="#00366C">(dd-mm-yy)</FONT><html:errors property="startdate"/>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="27%">
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
								<B><bean:message key="enddate"/> :</B>
								</FONT>
							</TD>
							<TD>
								<html:text property="enddate"/><FONT FACE="VERDANA" SIZE="1" COLOR="#00366C">(dd-mm-yy)</FONT><html:errors property="enddate"/>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="27%">
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
								<B><bean:message key="applfor"/> :</B>
								</FONT>
							</TD>
							<TD>
								<html:text property="applfor"/><html:errors property="applfor"/>
							</TD>
						</TR>
						<TR>
							<TD ALIGN="RIGHT" WIDTH="27%">
								<FONT FACE="VERDANA" COLOR="#004300" SIZE="2">
								<B><bean:message key="descr"/> :</B>
								</FONT>
							</TD>
							<TD>
								<html:text property="descr"/><html:errors property="descr"/>
							</TD>
						</TR>
						<TR>
							<TD COLSPAN="2" ALIGN="LEFT"><BR>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;
								<html:submit value="  Add  ">&nbsp;&nbsp;Login&nbsp;&nbsp;
								</html:submit>
								&nbsp;&nbsp;&nbsp;
								<html:reset>&nbsp;Reset&nbsp;</html:reset>
								&nbsp;&nbsp;&nbsp;
															</TD>
						</TR>
					</TABLE>
					</html:form>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>