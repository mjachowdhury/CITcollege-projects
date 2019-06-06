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
	            	<span class="tableheadertext youarehere">EMAIL - COMPOSE : </span>
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
	
		  <tr>
	        <td>&nbsp;</td>
		    <td>
			  <!-- BEGIN CONTENT -->
	
	
		      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
		        <tr>
		          <td valign="top" width="99%">

			          <table width="100%" border="0" cellspacing="3" cellpadding="0">
			          <tr>
			            <td align="center">

					    	<br/>
					    	<br/>
					    	<br/>
					    	<c:choose>
					    		<c:when test="${sent == null && invalid == null && fail == null}">
					    			<span class="youarehere">
					      				<p><strong>Message sent successfully.</strong></p>
					      			</span>
					    		</c:when>
					    		<c:otherwise>
					    			<span class="youarehere">
					    				<p><strong>E-mail could not delivered to some recepients. Please see the report below.</strong></p>
					    			</span>
					    			<strong>Messages successfully sent to</strong><br>
					    			<c:if test="${sent == null}">None available</c:if>
					    			<c:forEach items="${sent}" var="item"><c:out value="${item}"/><br></c:forEach><br>&nbsp;<br>

					    			<strong>Invalid Addresses</strong><br>
					    			<c:if test="${invalid == null}">None available</c:if>
					    			<c:forEach items="${invalid}" var="item"><c:out value="${item}"/><br></c:forEach><br>&nbsp;<br>

					    			<strong>Messages could not be delivered to for some other reason </strong><br>
					    			<c:if test="${fail == null}">None available</c:if>
					    			<c:forEach items="${fail}" var="item"><c:out value="${item}"/><br></c:forEach><br>&nbsp;<br>
					    		</c:otherwise>
					    	</c:choose>

					    	<br/>
					    	<br/>

						</td>
					  </tr>
					  </table>
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
