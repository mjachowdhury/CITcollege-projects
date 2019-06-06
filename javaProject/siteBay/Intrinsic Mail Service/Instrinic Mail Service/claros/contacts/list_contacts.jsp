<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<style type="text/css">
<!--
.fastsearchdiv {
	height: 29px;
	width: 500px;
	position: relative;
	background-position: right;
}
-->
</style>
<script language="javascript">
function fastsearch() {
	var f = document.getElementById('fastsrc');
	if (f.style.display == 'none') {
		f.style.display='';
	} else {
		f.style.display='none';
	}
}

function validateQuickSearch() {
	var form = document.forms['qsFrm'];
	var qs = trim(form.quickSearch.value);
	if (qs.length < 1) {
		alert("Please enter a criteria of at least 1(one) letter. It can be any part of an home/work address, company name,  phone number, e-mail address, name etc...");
		form.quickSearch.focus();
		return false;
	}
	return true;
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
      <table width="99%" border="0" align="left" cellpadding="0" cellspacing="0">
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
            		<span class="tableheadertext youarehere">CONTACTS - 
            			<c:choose>
            				<c:when test="${search != null}">
            					SEARCH RESULTS
            				</c:when>
            				<c:otherwise>
            					<c:out value="${letter}"/>
            				</c:otherwise>
            			</c:choose>
            		</span>
	            </td>
	            <td align="right" width="1%">
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr>
	            		  <td align="center" nowrap><a href="#" onclick="fastsearch()"><img src="/claros/images/search.gif" width="24" height="24" border="0"><br>
	        		      	Quick Search </a>
	        		      </td>
	            		  <td align="center" nowrap><img src="../images/spacer.gif" width="12" height="1"></td>
	            		<td align="center" nowrap>
	            			<html:link href="#" onclick="window.open('/claros/contacts/newContact.do','','scrollbars=no,status=yes, width=607, height=427, toolbar=no')"><img src="/claros/images/new_note.gif" width="24" height="24" border="0"><br>New Contact</html:link>
	            		</td>
	            		  <td align="center" nowrap><img src="../images/spacer.gif" width="12" height="1"></td>
	            		<td align="center" nowrap>
	            			<html:link page="/listGroups.do"><img src="/claros/images/groups.gif" width="24" height="24" border="0"><br>My Groups</html:link>
	            		</td>
	            		  <td align="center" nowrap><img src="../images/spacer.gif" width="12" height="1"></td>
	            		<td align="center" nowrap>
	            			<html:link page="/importEntry.do"><img src="/claros/images/import.gif" width="24" height="24" border="0"><br>Import/Export</html:link>
	            		</td>
	            		</tr>
	            	</table>
	            </td>
	          </tr>
	        </table></td>
      </tr>
      <!-- End You are here -->
		<tr>
			<td colspan="2" align="right">
    			<c:choose>
    				<c:when test="${search != null}">
						<div class="fastsearchdiv" id="fastsrc">
    				</c:when>
    				<c:otherwise>
						<div class="fastsearchdiv" style="display:none" id="fastsrc">
    				</c:otherwise>
    			</c:choose>
					<html:form action="/searchContact" method="post" onsubmit="return(validateQuickSearch())" styleId="qsFrm">
						Enter your search term: <html:text property="quickSearch"/><html:submit>Search</html:submit>
					</html:form>
				</div>			
			</td>
		</tr>

        <tr>
          <td width="1%"><img src="/claros/images/spacer.gif" width="10"></td>
          <td valign="top">
			<html:form action="/deleteContact" method="post" styleId="myForm">
			<html-el:hidden property="letter" value="${letter}"/>
              <table width="98%" cellpadding=0 cellspacing=1 class="table" id="table">
                  <tr bgcolor="#F2F2F2">
                  	<td colspan="6" style="font-size:11px; height:25px" align="center" class="youarehere">
					  <c:forEach items="${letters}" var="tmp">
					  	 <c:choose>
					  	 	<c:when test="${letter == tmp}">
					     		<strong><c:out value="${tmp}"/></strong>&nbsp;
					  	 	</c:when>
					  	 	<c:otherwise>
					     		<html-el:link page="/listContacts.do?l=${tmp}"><c:out value="${tmp}"/></html-el:link>&nbsp;
					  	 	</c:otherwise>
					  	 </c:choose>
					  </c:forEach>
                  	  <strong>
					  	 <c:choose>
					  	 	<c:when test="${letter == 'ALL'}">
					     		<strong>ALL</strong>&nbsp;
					  	 	</c:when>
					  	 	<c:otherwise>
                  	  			<strong><html-el:link page="/listContacts.do?l=ALL" style="color:#666666;">ALL</html-el:link>&nbsp;</strong>
					  	 	</c:otherwise>
					  	 </c:choose>
					  </strong>
                  	</td>
                  </tr>
                  <tr class="tableheader">
                    <td width="1"><input type="checkbox" onclick="toggleAll(document.forms['myForm'])" id="nope"></td>
                    <td class="tableheadertext">&nbsp;NAME</td>
                    <td class="tableheadertext">&nbsp;E-MAIL</td>
                    <td class="tableheadertext">&nbsp;GSM NO</td>
                    <td class="tableheadertext">&nbsp;WORK PHONE</td>
                    <td class="tableheadertext">&nbsp;COMPANY</td>
                  </tr>

				  
				  <c:choose>
				  <c:when test="${contacts != null}">
				  	  <bean:size id="size" name="contacts"/>
				  	  <c:if test="${size == 0}">
					  	<tr>
					  		<td colspan="6" align="center"><br/><span class="youarehere"><strong>No such entry found</strong></span><br/>&nbsp;<br/></td>
					  	</td>
				  	  </c:if>

					  <page:start value="${contacts}" pageId="${p}" itemsPerPage="30"/>
					  <c:forEach items="${contacts}" var="item" varStatus="stat">
						<c:choose>
							<c:when test="${stat.index % 2 == 0}">
								<tr class="tablelines">
							</c:when>
							<c:otherwise> 
								<tr>
							</c:otherwise>
						</c:choose>
						<td width="5px"><html-el:checkbox property="contact" value="${item.id}"/></td>
						<td>
							<html-el:link href="#" onclick="window.open('/claros/contacts/showContact.do?id=${item.id}','','scrollbars=no,status=yes, width=607, height=427, toolbar=no')">
								<c:choose>
									<c:when test="${nameFirst == 'true'}">
										<c:out value="${item.firstName}"/>
										<c:if test="${item.middleName != null && item.middleName != ''}">
											<c:out value="${item.middleName}"/>
										</c:if>
										<c:out value="${item.lastName}"/>
									</c:when>
									<c:otherwise>
										<c:out value="${item.lastName}"/>, 
										<c:out value="${item.firstName}"/>
										<c:if test="${item.middleName != null && item.middleName != ''}">
											<c:out value="${item.middleName}"/>
										</c:if>
									</c:otherwise>
								</c:choose>
							</html-el:link>
						</td>
						<td><html-el:link href="/claros/webmail/composeEntry.do?action=contact&id=${item.id}"><c:out value="${item.emailPrimary}"/></html-el:link></td>
						<td><c:out value="${item.gsmNoPrimary}"/></td>
						<td><c:out value="${item.workPhone}"/></td>
						<td><c:out value="${item.workCompany}"/></td>
						</tr>
					  </c:forEach>
				  </c:when>
				  <c:otherwise>
					<td colspan="6" align="center"><br/><span class="youarehere"><strong>No such entry found</strong></span><br/>&nbsp;<br/></td>
				  </c:otherwise>
				  </c:choose>

                  <tr class="tableheader" >
                    <td colspan="6"><html:submit styleClass="text_fields">Delete Selected</html:submit></td>
                  </tr>
              </table>

			  <page:index path="/claros/contacts/listContacts.do"/>
              </html:form>


		  </td>
		</tr>
	  </table>


    </td>
  </tr>
</table>
</body>
</html>
