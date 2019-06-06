<%@ page contentType="text/html; charset=utf-8"%>
<%  
	response.setHeader("Expires","-1");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Cache-control","no-cache");
%>
<%@ include file="/includes/taglibs.jsp" %>
<c:if test="${showDefaultCss == 'true'}">
	<style type="text/css">
	body {font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10pt}
	</style>
</c:if>
<c:out value="${email.bodyText}" escapeXml="false"/>
