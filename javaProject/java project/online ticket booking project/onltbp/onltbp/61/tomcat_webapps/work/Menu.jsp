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
	<TABLE BORDER="0" ALIGN="CENTER" WIDTH="18%" HEIGHT="100%">
		<TR>
			<TD ALIGN="CENTER" VALIGN="TOP"><BR><BR><BR>
				<TABLE BORDER="0">
					<TR>
						<TD ALIGN="CENTER" CELLPADDING="5">
							<FONT FACE="VERDANA" SIZE="+1">
							<A HREF="Adminlogin.jsp" TITLE="To get Administrator Login Page" onMouseOver="window.status=('To get Administrator Login Page'); return true;" onMouseOut="window.status=(' '); return true;">Administrator</A></FONT><BR><BR>
						</TD>
					<TR>
						<TD ALIGN="CENTER" VALIGN="MIDDLE" CELLPADDING="5">
							<FONT FACE="VERDANA" SIZE="+1">
							<A HREF="AgentLogin.jsp" TITLE="To get Agent Login Page" onMouseOver="window.status=('To get Agent Login Page'); return true;" onMouseOut="window.status=(' '); return true;">Agent</A></FONT><BR><BR>
						</TD>
					<TR>
						<TD ALIGN="CENTER" CELLPADDING="5">
							<FONT FACE="VERDANA" SIZE="+1">
							<A HREF="Userlogin.jsp" TITLE="To get User Login Page" onMouseOver="window.status=('To get User Login Page'); return true;" onMouseOut="window.status=(' '); return true;">User</A></FONT><BR><BR>
						</TD>
					<TR>
						<TD ALIGN="CENTER" CELLPADDING="5">
							<FONT FACE="VERDANA" SIZE="+1">
							<A HREF="AboutUs.jsp" TITLE="About Us Page" onMouseOver="window.status=('About Us Page'); return true;" onMouseOut="window.status=(' '); return true;">About Us</A></FONT>
						</TD>
					</TR>
				</TABLE>
			</TD>
		</TR>
	</TABLE>
</BODY>
</HTML>