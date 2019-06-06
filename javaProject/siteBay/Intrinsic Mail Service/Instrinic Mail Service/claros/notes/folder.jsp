<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<script language="javascript">
function validate() {
	var form = document.forms['frmSave'];
	var name = trim(form.folderName.value);
	if (name.length == 0) {
		alert("Please enter a folder name");
		form.folderName.focus();
		return false;
	}
}
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <%@ include file="/includes/page_top.jsp" %>
  <tr>
    <td>&nbsp;</td>
    <%@ include file="/includes/navbar.jsp" %>
    <td valign="top">
	  <br/>
      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="/claros/images/spacer.gif" width="10"></td>
          <td valign="top">
			<html:form action="/saveFolder" method="post" styleId="frmSave" onsubmit="return(validate())" focus="folderName">
			<html:hidden property="id"/>

	        <br/>
	        <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
		      <!-- Begin You are here -->
		      <tr>
		        <td width="1%"><img src="/claros/images/spacer.gif" width="3" height="1"></td>
		        <td><img src="/claros/images/spacer.gif" width="600" height="1"></td>
		        </tr>
		      <tr>
		        <td>&nbsp;</td>
		        <td><table width="99%" height="20" border="0" cellpadding="3" cellspacing="3" align="left">
		          <tr>
		            <td>
	            		<img src="/claros/images/grey_bullet.gif" width="5" height="12">
		            	<span class="tableheadertext youarehere">NOTES - EDIT FOLDER</span>
		            </td>
		            <!--
		            <td align="right" nowrap>
		            	<table border="0" cellspacing="0" cellpadding="0">
		            		<tr><td align="center">
		            			<html:image src="/claros/images/save.gif"></html:image><br>Save
		            		</td>
		            		</tr>
		            	</table>
		            </td>
		            -->
		          </tr>
		        </table></td>
		      </tr>
		      <!-- End You are here -->
			  <tr>
          		<td width="1%"><img src="/claros/images/spacer.gif" width="10"></td>
	            <td valign="top" width="100%">
				  <br/>
				  <br/>
				  <br/>
				  <br/>
				  <html:hidden property="id"/>
				  <strong>Folder:</strong>&nbsp;<html:text property="folderName" size="25" maxlength="255"/>
				  <html:submit>Save</html:submit>
			  	</td>
			  </tr>
		    </table>

			</html:form>
		  </td>
		</tr>
	  </table>
    </td>
  </tr>
</table>
</body>
</html>
