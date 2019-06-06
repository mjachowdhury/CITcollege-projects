<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<script language="javascript">
function confirmDelete() {
	return confirm("Are you sure you want to completely delete all notes in this folder?");
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
			<html:form action="/deleteFolder" method="post">
			  <table width="99%" cellpadding="0" cellspacing="0">
			      <!-- Begin You are here -->
			      <tr>
			        <td width="1%"><img src="/claros/images/spacer.gif" width="3" height="1"></td>
			        <td><img src="/claros/images/spacer.gif" width="600" height="1"></td>
			        </tr>
			      <tr>
			        <td>&nbsp;</td>
			        <td><table width="99%" height="20" border="0" cellpadding="3" cellspacing="3" align="left">
			          <tr>
			            <td nowrap width="99%">
							<img src="/claros/images/grey_bullet.gif" width="5" height="12">
			            	<span class="tableheadertext youarehere">NOTES - MANAGE FOLDERS :</span>
			            </td>
			            <td align="right" nowrap width="1%">
			            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
			            		<tr><td align="center" nowrap>
			            			<html:link page="/newFolder.do"><img src="/claros/images/add.gif" width="24" height="24" border="0"><br>New Folder</html:link>
			            		</td>
			            		</tr>
			            	</table>
			            </td>
			          </tr>
			        </table></td>
			      </tr>
			      <!-- End You are here -->
			      <tr>
			        <td>&nbsp;</td>
			      	<td>
			      		<table width="100%" border="0" align="left" cellpadding="0" cellspacing="1" id="table" class="table">
			                <tr class="tableheader">
			                  <td width="1%"><img src="/claros/images/spacer.gif" width="5"></td>
			                  <td width="84%" class="tableheadertext">FOLDERS</td>
			                  <td width="16%" class="tableheadertext" align="center">&nbsp;EMPTY FOLDER </td>
			                </tr>
			                
				  			<page:start value="${notesFolders}" pageId="${p}" itemsPerPage="15"/>
			                
							<c:forEach items="${notesFolders}" var="item" varStatus="stat">
							<c:choose>
								<c:when test="${stat.index % 2 == 0}">
									<tr class="tablelines">
								</c:when>
								<c:otherwise> 
									<tr>
								</c:otherwise>
							</c:choose>
								<td width="5px"><html-el:checkbox property="folder" value="${item.id}"/></td>
								<td><html-el:link page="/showFolder.do?fid=${item.id}"><c:out value="${item.folderName}"/></html-el:link></td>
			                  	<td class="tabletext10non" align="center">
			                  		<html-el:link page="/emptyFolder.do?folderId=${item.id}" onclick="return(confirmDelete())"><img src="/claros/images/deleteico.gif" width="16" height="16" border="0" style="text-decoration: none"></html-el:link>
			                  	</td>
							</tr>
							</c:forEach>
			
			                <tr class="tablelines">
			                  <td>&nbsp;</td>
			                  <td>
								<html:submit styleClass="text_fields">Delete Selected</html:submit>
			                  </td>
			                  <td>&nbsp;</td>
			                </tr>
			      		</table>


			      	</td>
			      </tr>

              </table>
			  <br/>
			  <page:index path="/claros/notes/folders.do"/>
			</html:form>

		  </td>
		</tr>
	  </table>
    </td>
  </tr>
</table>
</body>
</html>
