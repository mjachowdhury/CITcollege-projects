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
					<A HREF="AddOffer.do">Add Offer</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="GetOffer.do">View Offers</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER" HEIGHT="5%">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="AllBusType.do">Send Messages</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="GetTrip.do">View Messages</A></B></FONT>
				</TD>
			</TR>
			
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="ViewTrip.do">Book Ticket</A></B></FONT>
				</TD>
			</TR>
			
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="ViewHalts.do">Block Ticket</A></B></FONT>
				</TD>
			</TR>
			
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="PendingAgent.do">Add Customers</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="ViewPermanentAgent.do">View Customers</A> </FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="addadmin.jsp">Request For  License</A></B></FONT>
				</TD>
			</TR>
			
			</TABLE>
	</BODY>
</HTML>
