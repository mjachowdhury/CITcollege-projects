<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<tiles:insert page="/Layout.jsp">
<tiles:put name="header" value="/Header.jsp"/>
<tiles:put name="footer" value="/Footer.jsp"/>
<tiles:put name="menu" value="/menu1.jsp"/>
<tiles:put name="body" value="/sendmailbody.jsp"/>
</tiles:insert>