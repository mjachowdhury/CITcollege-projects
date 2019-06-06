<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<BODY>
		<TABLE ALIGN="CENTER" WIDTH="45%" HEIGHT="99%" BORDER="0">
			<TR>
				<TD ALIGN="CENTER">
					<FONT FACE="VERDANA" SIZE="+2" COLOR="GREEN">Welcome, <%= session.getAttribute("agent") %></FONT>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>