
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert page="Layout.jsp">
<tiles:put name="header" value="Header.jsp"/>
<tiles:put name="menu" value="menu2.jsp"/>
<tiles:put name="body" value="maila1.jsp"/>
<tiles:put name="footer" value="Footer.jsp"/>
</tiles:insert>