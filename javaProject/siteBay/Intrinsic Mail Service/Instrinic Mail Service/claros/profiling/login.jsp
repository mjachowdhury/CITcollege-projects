<%@ page contentType="text/html; charset=UTF-8" session="false" %>
<%@ include file="/includes/top_base.jsp" %>
<link href="/claros/css/login.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.bg {
	background-image: url(/claros/images/gradient.gif);
	background-repeat: repeat-y;
}
.border {
	border: 1px solid #999999;
}
-->
</style>
<script language="javascript">
function validate() {
	var form = document.forms['loginForm'];
	var username = trim(form.username.value);
	var password = trim(form.password.value);

	if (username.length == 0) {
		alert("Please enter your username");
		form.username.focus();
		return false;
	}

	if (password.length == 0) {
		alert("Please enter your password");
		form.password.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>
<html:form action="/loginEnd" method="post" styleId="loginForm" onsubmit="return(validate())">
<table height="100%" align="center" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="100%" valign="top">
        <br>
		<br>
		<br>
        <br>
		<br>
		<br>
	    <table width="534" height="286" border="0" cellpadding="0" cellspacing="0">
	      <tr><td><img src="/claros/images/logointro.gif" width="147" height="54"></td>
	      </tr>
	      <tr>
	        <td height="284"><table width="534" height="284" border="0" cellpadding="0" cellspacing="0" class="border">
	          <tr>
	            <td width="7" bgcolor="#1E407E">&nbsp;</td>
	            <td width="1" bgcolor="#2D609F"><img src="/claros/images/bos.gif" width="1" height="1"></td>
	            <td width="7" bgcolor="#154E94">&nbsp;</td>
	            <td width="1" bgcolor="#276EAF"><img src="/claros/images/bos.gif" width="1" height="1"></td>
	            <td width="7" bgcolor="#0E5DA6">&nbsp;</td>
	            <td width="1" bgcolor="#1F73C0"><img src="/claros/images/bos.gif" width="1" height="1"></td>
	            <td width="7" bgcolor="#0663B9">&nbsp;</td>
	            <td valign="bottom" class="bg"><table width="480" border="0" align="center" cellpadding="0" cellspacing="0">
	                <tr>
	                  <td class="texttahoma11">&nbsp;</td>
	                  <td class="texttahoma10">&nbsp;</td>
	                  <td width="155" class="texttahoma11blue">
						  <c:if test="${loginInvalid == 'true'}">
						  	<font color="red">Login invalid</font>
						  </c:if>
						  <c:if test="${sessionExpired == 'true'}">
						  	<font color="red">Session expired, login again</font>
						  </c:if>
						  <c:if test="${sessionExpired != 'true' && loginInvalid != 'true'}">
						  	L O G I N
						  </c:if>
	                  </td>
	                </tr>
	                <tr>
	                  <td width="325" class="texttahoma11"><div align="right"></div></td>
	                  <td width="10" class="texttahoma10"><div align="center"></div></td>
	                  <td width="155"><div align="left"></div></td>
	                </tr>
	                <tr>
	                  <td width="325" class="texttahoma11"><div align="right">Username </div></td>
	                  <td width="10" class="texttahoma10"><div align="center">:</div></td>
	                  <td width="155"><div align="left">
	                  	<html:text property="username" styleClass="text_fields" size="30" />
	                  </div></td>
	                </tr>
	                <tr>
	                  <td width="325" class="texttahoma11"><div align="right"></div></td>
	                  <td width="10" class="texttahoma10"><div align="center"></div></td>
	                  <td width="155" height="7"><div align="left"></div></td>
	                </tr>
	                <tr>
	                  <td width="325" class="texttahoma11"><div align="right">Password</div></td>
	                  <td width="10" class="texttahoma10"><div align="center">:</div></td>
	                  <td width="155"><div align="left">
	                  	  <html:password property="password" styleClass="text_fields" size="30" redisplay="false"/>
	                  </div></td>
	                </tr>
	                <tr>
	                  <td width="325" class="texttahoma11"><div align="right"></div></td>
	                  <td width="10" class="texttahoma10"><div align="center"></div></td>
	                  <td width="155" height="7"><div align="left"></div></td>
	                </tr>
	                <tr>
	                  <td width="325" class="texttahoma11"><div align="right">Server</div></td>
	                  <td width="10" class="texttahoma10"><div align="center">:</div></td>
	                  <td width="155"><div align="left">
			        	<html:select property="server" styleClass="text_fields">
			        		<c:forEach items="${connectionProfiles}" var="item">
			        			<html-el:option value="${item.key}"><c:out value="${item.value.shortName}"/></html-el:option>
			        		</c:forEach>
			        	</html:select>
	                  </div></td>
	                </tr>
	                <tr>
	                  <td class="texttahoma11">&nbsp;</td>
	                  <td class="texttahoma10">&nbsp;</td>
	                  <td width="155">&nbsp;</td>
	                </tr>
	                <tr>
	                  <td class="texttahoma11">&nbsp;</td>
	                  <td class="texttahoma10">&nbsp;</td>
	                  <td width="155"><html:submit styleClass="buttons">Login</html:submit></td>
	                </tr>
	                <tr>
	                  <td class="texttahoma11">&nbsp;</td>
	                  <td class="texttahoma10">&nbsp;</td>
	                  <td>&nbsp;</td>
	                </tr>
	            </table></td>
	            </tr>
	        </table></td>
	      </tr>
	    </table>
	    <div align="center" style="font-size: 9px;font-family: Geneva, Arial, Helvetica, sans-serif;"><p><br><br></p></div>
	</td>
  </tr>
</table>
</html:form>
</body>
</html>
