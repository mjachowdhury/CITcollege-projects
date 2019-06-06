<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<HTML>
	<BODY>
		<TABLE BORDER="0" WIDTH="100%" HEIGHT="100%">
			<TR>
				<TD COLSPAN="2" BGCOLOR="#C6D5EA" NOWRAP>
					<tiles:insert attribute="header"/>
				</TD>
			</TR>
			<TR>
				<TD WIDTH="20%" BGCOLOR="#DBDBDB" NOWRAP>
					<tiles:insert attribute="menu"/>
				</TD>
				<TD WIDTH="80%" HEIGHT="81%" BGCOLOR="#F3E3E3" NOWRAP><!--#E4ECF6-->
					<tiles:insert attribute="body"/>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN="2" HEIGHT="5%" BGCOLOR="#C6D5EA" NOWRAP>
					<tiles:insert attribute="footer"/>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>