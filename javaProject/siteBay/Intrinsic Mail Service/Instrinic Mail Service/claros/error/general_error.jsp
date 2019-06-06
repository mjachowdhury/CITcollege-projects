<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <%@ include file="/includes/page_top.jsp" %>
  <tr>
    <td>&nbsp;</td>
    <%@ include file="/includes/navbar.jsp" %>
    <td valign="top">
	  <br/>
	  <br/>
      <table width="90%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="/claros/images/spacer.gif" width="10"></td>
          <td valign="top">
          	<br/>
          	<br/>
          	<br/>
			An exception is catched. Help us make this software better. Please report the error in the support forum at Claros In Touch <a href="http://www.claros.org/support.html" target="_blank">official website</a> with the following details...
			<pre>
			<c:out value="${myexception.systemMessage}"></c:out>
			</pre>
		  </td>
		</tr>
	  </table>
    </td>
  </tr>
</table>
</body>
</html>
