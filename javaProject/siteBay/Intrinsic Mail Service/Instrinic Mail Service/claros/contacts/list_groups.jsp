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
		            <td>
	            		<img src="/claros/images/grey_bullet.gif" width="5" height="12">
		            	<span class="tableheadertext youarehere">CONTACTS - GROUPS</span>
		            </td>
		            <td align="right" nowrap>
		            	<table border="0" cellspacing="0" cellpadding="0">
	            		<tr>
	            		  <td align="center" nowrap><a href="#" onClick="fastsearch()"><img src="/claros/images/search.gif" width="24" height="24" border="0"><br>
	        		      	Quick Search </a>
	        		      </td>
	            		  <td align="center" nowrap><img src="../images/spacer.gif" width="12" height="1"></td>
	            		<td align="center" nowrap>
	            			<html:link page="/newGroup.do"><img src="/claros/images/new_note.gif" width="24" height="24" border="0"><br>New Group</html:link>
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






					<html:form action="/deleteGroup" method="post" styleId="myForm">
		              <table width="98%" cellpadding=0 cellspacing=1 class="table" id="table">
		                  <tr class="tableheader">
		                    <td width="1"><input type="checkbox" onclick="toggleAll(document.forms['myForm'])" id="nope"></td>
		                    <td class="tableheadertext">&nbsp;SHORT NAME</td>
		                    <td class="tableheadertext">&nbsp;DESCRIPTION</td>
		                  </tr>
		
						  <c:choose>
						  <c:when test="${groups != null}">
						  	  <bean:size id="size" name="groups"/>
						  	  <c:if test="${size == 0}">
							  	<tr>
							  		<td colspan="3" align="center"><br/><span class="youarehere"><strong>No groups found.</strong></span><br/>&nbsp;<br/></td>
							  	</td>
						  	  </c:if>
		
							  <page:start value="${groups}" pageId="${p}" itemsPerPage="20"/>
							  <c:forEach items="${groups}" var="item" varStatus="stat">
								<c:choose>
									<c:when test="${stat.index % 2 == 0}">
										<tr class="tablelines">
									</c:when>
									<c:otherwise> 
										<tr>
									</c:otherwise>
								</c:choose>
								<td width="5px"><html-el:checkbox property="group" value="${item.id}"/></td>
								<td>
									<html-el:link page="/showGroup.do?id=${item.id}"><c:out value="${item.shortName}"/></html-el:link>
								</td>
								<td>
									<c:out value="${item.memberCount}"/> members.
								</td>
								</tr>
							  </c:forEach>
						  </c:when>
						  <c:otherwise>
							<td colspan="3" align="center"><br/><span class="youarehere"><strong>No groups found.</strong></span><br/>&nbsp;<br/></td>
						  </c:otherwise>
						  </c:choose>
		
		                  <tr class="tableheader" >
		                    <td colspan="3"><html:submit styleClass="text_fields">Delete Selected</html:submit></td>
		                  </tr>
		              </table>
					  <page:index path="/claros/contacts/listGroups.do"/>
		              </html:form>



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
