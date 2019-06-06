<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE> Administrator Login Form </TITLE>
		<STYLE>
		a
			{
			text-decoration : none;
			color : darkblue;
			}
		A:visited {
			
		}
		A:active {
			TEXT-DECORATION: underline
		}
		A:hover {
			COLOR:red; TEXT-DECORATION: underline
		}
		</STYLE>
	</HEAD>
	<BODY>
		<BR><BR>
		<TABLE ALIGN="CENTER" WIDTH="100%" HEIGHT="45%" BORDER="0" CELLSPACING="2" CELLPADDING="3">
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="GetOfferC.do" TITLE="To view All Offers" >View Offers</A></FONT><BR>
						
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER" HEIGHT="5%">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="ViewMailC.do">View Messages</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="sendMcag.do">Send Messages To Agent</A></B></FONT>
				</TD>
			</TR>
			
			
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="sendMcad.do">Send Messages To Administrator</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="ViewBusC.do">View Bus Services</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="ViewTimings.do">View Timings</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="srcdescC.do"> Book Ticket</A></B></FONT>
				</TD>
				</tr>
				<tr>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="blockC.do"> Block Ticket</A></B></FONT>
				</TD>
				
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="Logout.do">Logout</A></B></FONT>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>
