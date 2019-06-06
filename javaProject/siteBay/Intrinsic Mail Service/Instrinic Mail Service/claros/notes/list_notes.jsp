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
		            	<span class="tableheadertext youarehere">NOTES - <str:upperCase><c:out value="${folderName}"/></str:upperCase></span>
		            </td>
		            <td align="right" width="1%">
		            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
		            		<tr><td align="center" nowrap>
		            			<html:link page="/folders.do"><img src="/claros/images/folder.gif" width="24" height="24" border="0"><br>Edit Folders</html:link>
		            		</td>
		            		</tr>
		            	</table>
		            </td>
		            <td><img src="/claros/images/spacer.gif" width="3"></td>
		            <td align="right" width="1%">
		            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
		            		<tr><td align="center" nowrap>
		            			<html:link page="/newNote.do"><img src="/claros/images/new_note.gif" width="24" height="24" border="0"><br>New Note</html:link>
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
				  	<c:if test="${notes != null}">
				  		<bean-el:size id="size" collection="${notes}"/>
				  		<c:if test="${size == 0}">
							<div align="center">&nbsp;<br/>&nbsp;<br/><strong class="youarehere">There are no items in this folder.</strong></div>
				  		</c:if>
				  	</c:if>
					<c:forEach items="${notes}" var="item">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr>
							<td>
								<table width="98%" border="0" cellspacing="0" cellpadding="1">
									<tr>
										<td align="right" width="100%">
											<html-el:link href="#" onclick="document.getElementById('div${item.id}').style.height='360px'">Large Size</html-el:link> | 
											<html-el:link href="#" onclick="document.getElementById('div${item.id}').style.height='110px'">Default Size</html-el:link> | 
											<html-el:link href="#" onclick="document.getElementById('div${item.id}').style.height='75px'">Small Size</html-el:link> | 
											<html-el:link page="/showNote.do?nid=${item.id}">Edit</html-el:link> | 
											<html-el:link page="/deleteNote.do?note=${item.id}&fid=${folderId}">Delete</html-el:link> | 
											<html-el:link href="/claros/webmail/emailNote.do?noteId=${item.id}">Send As E-Mail</html-el:link> |
										</td>
									</tr>
									<tr>
										<td width="100%">
											<div class="overflow" id="div<c:out value="${item.id}"/>"><c:out value="${item.note}" escapeXml="false"/></div>
										</td>
									</tr>
								</table>
							</td>
						  </tr>
						</table>
						<br>&nbsp;<br>
					</c:forEach>


			  
			  	</td>
			  </tr>
		    </table>




		  </td>
		</tr>
	  </table>
    </td>
  </tr>
</table>
</body>
</html>
