<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<style type="text/css">
<!--
.overflow {
	overflow: auto;
	height: 110px;
	width: 98,5%;
	border: 1px solid #E6E6E6;
	padding: 5px;
}
-->
</style>
<script language="javascript" type="text/javascript" src="/claros/js/tiny_mce/tiny_mce.js"></script>
<script language="javascript">
tinyMCE.init({
	mode : "textareas",
	theme : "advanced",
	plugins : "table, advimage,advlink,emotions,iespell,insertdatetime,preview,zoom, searchreplace,print,contextmenu",
	theme_advanced_buttons1_add : "fontselect,fontsizeselect",
	theme_advanced_buttons2_add : "separator,insertdate,inserttime,preview,zoom,separator,forecolor,backcolor",
	theme_advanced_buttons2_add_before: "cut,copy,paste,separator,search,replace,separator",
	theme_advanced_buttons3_add_before : "tablecontrols,separator",
	theme_advanced_buttons3_add : "emotions, iespell, print",
	theme_advanced_toolbar_location : "top",
	theme_advanced_toolbar_align : "left",
	theme_advanced_path_location : "bottom",
	plugin_insertdate_dateFormat : "%Y-%m-%d",
	plugin_insertdate_timeFormat : "%H:%M:%S",
	content_css : "../css/editor.css",
	extended_valid_elements : "a[name|href|target|title|onclick],img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name],hr[class|width|size|noshade],font[face|size|color|style],span[class|align|style]"
});
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
      <table width="99%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="/claros/images/spacer.gif" width="10"></td>
          <td valign="top">
			<html:form action="/saveNote" method="post">
			<html:hidden property="id"/>
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
		            	<span class="tableheadertext youarehere">:: NOTES - EDIT NOTE</span>
		            </td>
		            <td align="right" nowrap>
		            	<table border="0" cellspacing="0" cellpadding="0">
		            		<tr><td align="center">
		            			<html:link href="#" onclick="document.forms[0].submit();"><img src="/claros/images/save.gif" border="0" width="24" height="24"><br>Save</html:link>
		            		</td>
		            		</tr>
		            	</table>
		            </td>
		          </tr>
		        </table></td>
		      </tr>
		      <!-- End You are here -->
			  <tr>
          		<td width="1%"><img src="/claros/images/spacer.gif" width="10"></td>
	            <td valign="top" width="100%">
				  <br/>

					<table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
						<td>
							<div align="right">
								<strong class="youarehere">Please Select Folder: </strong>
								<html:select property="folder" styleClass="text_fields">
									<html:optionsCollection name="folders"/>
								</html:select>&nbsp;&nbsp;&nbsp;
							</div>
							<table width="100%" border="0" cellspacing="0" cellpadding="1" align="left">
								<tr>
									<td width="96%" bgcolor="#F2F2F2" align="left">
										<html:textarea styleId="note" rows="32" cols="150" style="width:100%" property="note"></html:textarea>
									</td>
								</tr>
							</table>
						</td>
					  </tr>
					</table>


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
