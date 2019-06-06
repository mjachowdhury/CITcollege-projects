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
            <td nowrap width="96%">
            	<img src="/claros/images/grey_bullet.gif" width="5" height="12">
            	<span class="tableheadertext youarehere">
            		E-MAIL - <str:upperCase><c:out value="${folderName}"/></str:upperCase> : 
            	</span> 
            	<c:choose>
            		<c:when test="${totalMessages == 1}">
            			(<c:out value="${totalMessages}"/> Message)
            		</c:when>
            		<c:otherwise>
            			(<c:out value="${totalMessages}"/> Messages)
            		</c:otherwise>
            	</c:choose>
            </td>
            <td align="right" width="1%">
            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
            		<tr><td align="center" nowrap>
            			<html:link page="/mailbox.do?fid="><img src="/claros/images/download.gif" width="24" height="24" border="0"><br>Check E-mail</html:link>
            		</td>
            		</tr>
            	</table>
            </td>
            <td><img src="/claros/images/spacer.gif" width="2"></td>
            <td align="right" width="1%">
            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
            		<tr><td align="center" nowrap>
            			<html:link page="/composeEntry.do?action=new"><img src="/claros/images/new_note.gif" width="24" height="24" border="0"><br>New E-mail</html:link>
            		</td>
            		</tr>
            	</table>
            </td>
            <td><img src="/claros/images/spacer.gif" width="2"></td>
            <td align="right" width="1%">
            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
            		<tr><td align="center" nowrap>
            			<html:link page="/foldersEntry.do"><img src="/claros/images/folder.gif" width="24" height="24" border="0"><br>Folders</html:link>
            		</td>
            		</tr>
            	</table>
            </td>
            <td><img src="/claros/images/spacer.gif" width="2"></td>
            <td align="right" width="1%">
            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
            		<tr><td align="center" nowrap>
            			<html:link href="/claros/filters/listFilters.do"><img src="/claros/images/filters.gif" width="24" height="24" border="0"><br>Filters</html:link>
            		</td>
            		</tr>
            	</table>
            </td>
          </tr>
        </table></td>
      </tr>
      <!-- End You are here -->
      
      <c:choose>
      	<c:when test="${totalMessages == 0}">
      	  <tr>
      	  	<td>&nbsp;</td>
      		<td align="center">&nbsp;<br/>&nbsp;<br/><strong class="youarehere">There are no messages in this folder.</strong></td>
      	  </tr>
      	</c:when>
      	<c:otherwise>
		  <!-- Begin Content Table -->
	      <tr>
	        <td>&nbsp;</td>
	        <td>
			  <html:form action="/mailboxActions" method="post">
	          <table width="100%" border="0" cellspacing="3" cellpadding="0">
	          <tr>
	            <td><table width="100%" align="center" cellpadding="0" cellspacing="1" class="table" id="table">
	              <tr class="tableheader">
	                <td width="20"><input type="checkbox" onclick="toggleAll(document.forms[0])" id="nope"></td>
	                <td width="17" class="tableheadertext">&nbsp;</td>
	                <c:choose>
	                	<c:when test="${isSentItems == true}">
	                		<td width="197" class="tableheadertext">&nbsp;TO</td>
	                	</c:when>
	                	<c:otherwise>
	                		<td width="197" class="tableheadertext">&nbsp;FROM</td>
	                	</c:otherwise>
	                </c:choose>
	                <td width="373" class="tableheadertext">&nbsp;SUBJECT</td>
	                <td width="132" class="tableheadertext">&nbsp;SENT DATE </td>
	                <td width="56" class="tableheadertext">&nbsp;SIZE</td>
	              </tr>
	              <!-- End Content Table Headers -->

				  <page:start value="${headers}" pageId="${p}" itemsPerPage="20"/>
				  <c:forEach items="${headers}" var="item" varStatus="stat">
					<c:choose>
						<c:when test="${stat.index % 2 == 0}">
							<tr class="tablelines">
						</c:when>
						<c:otherwise>
							<tr>
						</c:otherwise>
					</c:choose>
					<td width="5px"><html-el:checkbox property="msg" value="${item.messageId}"/></td>
					<td>
						<c:if test="${item.multipart == 'true'}">
							<img src="/claros/images/attachment.gif" width="17" height="17">
						</c:if>
					</td>
					<td>
						<c:if test="${item.unread == 'true'}"><strong></c:if>

						<html-el:link page="/readMail.do?msg=${item.messageId}">
		                <c:choose>
		                	<c:when test="${isSentItems == true}">
		                		<c:out value="${item.toShown}"/>
		                	</c:when>
		                	<c:otherwise>
		                		<c:out value="${item.fromShown}"/>
		                	</c:otherwise>
		                </c:choose>
						</html-el:link>
						<c:if test="${item.unread == 'true'}"></strong></c:if>
					</td>
					<td>
						<c:if test="${item.unread == 'true'}"><strong></c:if>
						<html-el:link page="/readMail.do?msg=${item.messageId}"><c:out value="${item.subject}" default="No Subject"/></html-el:link>
						<c:if test="${item.unread == 'true'}"></strong></c:if>
					</td>
					<td><c:out value="${item.dateShown}"/></td>
					<td><c:out value="${item.sizeShown}"/></td>
				    </tr>
				  </c:forEach>
	
	
				  <!-- Begin Content Table Footer -->
	              <tr class="tableheader">
	                <td height="3" colspan="6" class="tableheadertext">
	                <html:submit property="delete" styleClass="text_fields">delete selected</html:submit>
	                &nbsp;&nbsp;&nbsp;
	                Move Selected To : <img src="/claros/images/spacer.gif" width="1" height="20">
						<html:select property="moveFolder" styleClass="text_fields">
							<c:forEach items="${otherFolders}" var="fold">
								<html-el:option value="${fold.id}"><c:out value="${fold.folderName}"/></html-el:option>
							</c:forEach>
						</html:select>
						<html:submit property="move" styleClass="text_fields">ok</html:submit>
	                    <img src="/claros/images/spacer.gif" width="1" height="20">
	                </td>
	              </tr>
				  <!-- End Content Table Footer -->
	            </table>
	            <br/>
	            <page:index path="/claros/webmail/mailbox.do"/>
	            </td>
	          </tr>
	        </table>
	        </html:form>
	        </td>
	      </tr>
	      <!-- Content Table -->
      	
      	</c:otherwise>
      </c:choose>
      
    </table>
    </td>
  </tr>
</table>
</body>
</html>
