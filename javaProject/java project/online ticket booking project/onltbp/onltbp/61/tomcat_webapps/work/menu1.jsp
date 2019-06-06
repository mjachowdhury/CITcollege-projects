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
					<A HREF="addadmin.jsp"> </A></B></FONT>
				</TD> 
							</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="GetBusType.do">Add New Bus</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="ViewBus.do">Bus Services</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER" HEIGHT="5%">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="AllBusType.do">Bus Types</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="GetTrip.do">Add New Trip</A></B></FONT>
				</TD>
			</TR>
			
			
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="PendingAgent.do"></A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="ViewPermanentAgent.do">View Agents</A> </FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="send.do">Send Messages</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="viewMail.do">View Messages</A></B></FONT>
				</TD>
			</TR>
			
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="offer_form.jsp">Add Offer</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="GetOffer.do">View Offers</A></B></FONT>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="2"><B>
					<A HREF="ViewAdmin.do"></A></B></FONT>
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
