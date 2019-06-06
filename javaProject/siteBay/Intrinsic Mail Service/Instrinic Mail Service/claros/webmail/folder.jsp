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
	      <!-- Begin You are here -->
	      <tr>
	        <td width="1%"><img src="/claros/images/spacer.gif" width="3" height="1"></td>
	        <td><img src="/claros/images/spacer.gif" width="600" height="1"></td>
	        </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td><table width="99%" height="20" border="0" cellpadding="3" cellspacing="3" align="left">
	          <tr>
	            <td nowrap width="98%">
	            	<img src="/claros/images/grey_bullet.gif" width="5" height="12">
	            	<span class="tableheadertext youarehere">EMAIL - EDIT FOLDER : </span>
	            </td>
	          </tr>
	        </table></td>
	      </tr>
	      <!-- End You are here -->
	
		  <tr>
	        <td>&nbsp;</td>
		    <td>
			  <!-- BEGIN CONTENT -->
	
	
		      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
		        <tr>
		          <td valign="top" width="99%">

					<html:form action="/saveFolder" method="post" focus="folderName" onsubmit="return(validate())" styleId="frmSave">
			          <table width="100%" border="0" cellspacing="3" cellpadding="0">
			          <tr>
			            <td>
							&nbsp;<br/>
							&nbsp;<br/>
							&nbsp;<br/>
							&nbsp;<br/>
							<html:hidden property="id"/>
							<strong>Folder:</strong> <html:text property="folderName" maxlength="100"/>
							<html:submit>Save</html:submit>

						</td>
					  </tr>
					  </table>
					</html:form>

				  </td>
				</tr>
			  </table>
			  <!-- END CONTENT -->
			</td>
		  </tr>
		</table>		  


    </td>
  </tr>
</table>
</body>
</html>
