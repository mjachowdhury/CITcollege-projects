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
	            <td nowrap width="98%">
	            	<img src="/claros/images/grey_bullet.gif" width="5" height="12">
	            	<span class="tableheadertext youarehere">EMAIL - FILTERS : </span>
	            </td>
	            <td align="right" width="1%">
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html:link page="/newFilter.do"><img src="/claros/images/new_note.gif" width="24" height="24" border="0"><br>New Filter</html:link>
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
			  <!-- BEGIN CONTENT -->
				<html:form action="/deleteFilter" method="post">
				  <table width="99%" cellpadding="0" cellspacing="1" id="table" class="table">
	                <tr class="tableheader">
	                  <td width="1%"><input type="checkbox" onclick="toggleAll(document.forms[0])" id="nope"></td>
	                  <td class="tableheadertext">MESSAGE PORTION</td>
	                  <td class="tableheadertext">CONDITION</td>
	                  <td class="tableheadertext">KEYWORD</td>
	                  <td class="tableheadertext">ACTION</td>
	                  <td class="tableheadertext">DESTINATION</td>
	                </tr>
	                
	                <page:start value="${mailFilters}" itemsPerPage="20" pageId="${p}"/>
					<c:forEach items="${mailFilters}" var="item" varStatus="stat">
					<c:choose>
						<c:when test="${stat.index % 2 == 0}">
							<tr class="tablelines">
						</c:when>
						<c:otherwise> 
							<tr>
						</c:otherwise>
					</c:choose>
						<td width="5px"><html-el:checkbox property="filters" value="${item.id}"/></td>
						<td><html-el:link page="/showFilter.do?jid=${item.id}"><c:out value="${item.portionName}"/></html-el:link></td>
						<td><html-el:link page="/showFilter.do?jid=${item.id}"><c:out value="${item.conditionName}"/></html-el:link></td>
						<td><html-el:link page="/showFilter.do?jid=${item.id}"><c:out value="${item.keyword}"/></html-el:link></td>
						<td><html-el:link page="/showFilter.do?jid=${item.id}"><c:out value="${item.actionName}"/></html-el:link></td>
						<td><html-el:link page="/showFilter.do?jid=${item.id}"><c:out value="${item.destinationName}"/></html-el:link></td>
					</tr>
					</c:forEach>
	                <tr class="tableheader">
	                  <td>&nbsp;</td>
	                  <td class="tableheadertext">
						<html:submit styleClass="text_fields">Delete Selected</html:submit>
	                  </td>
	                  <td>&nbsp;</td>
	                  <td>&nbsp;</td>
	                  <td>&nbsp;</td>
	                  <td>&nbsp;</td>
	                </tr>
	              </table>
				  <br/>
				  <page:index path="/claros/filters/listFilters.do"/>
				</html:form>
	

			  <!-- END CONTENT -->
			</td>
		  </tr>
		</table>		  


    </td>
  </tr>
</table>
</body>
</html>
