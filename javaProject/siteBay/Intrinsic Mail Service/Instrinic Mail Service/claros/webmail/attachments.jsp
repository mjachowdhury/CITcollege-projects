<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<script type="text/javascript" src="/claros/js/attachments.js"></script>
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
	        <td>&nbsp;</td>
		    <td>
			  <!-- BEGIN CONTENT -->
	
	
		      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
		        <tr>
		          <td valign="top" width="99%">
			          <table width="100%" border="0" cellspacing="3" cellpadding="0">
			          <tr>
			            <td>
			            	<br/>
			            	<br/>
							<strong>Add New Attachment:</strong>
							<html:form action="/attachUpload" method="post" enctype="multipart/form-data" onsubmit="return validate();" styleId="attForm" focus="attachFile">
								<html:file property="attachFile" size="45" />
								<html:submit>Add</html:submit>
							</html:form>
							<hr size="1">
							
							<c:if test="${attachments != null}">
								<strong>Attachments:</strong><br>
								<c:forEach items="${attachments}" var="item" varStatus="stat">
									<c:out value="${item.fileName}" /> - <c:out value="${item.contentType}" /> - <c:out value="${item.sizeReadable}" /> ||| <html-el:link page="/attachDelete.do?item=${stat.index}">delete</html-el:link><br>
								</c:forEach>
								<hr size="1">
							</c:if>
				  	  		<strong>&lt;&lt;&nbsp;<html:link page="/composeEntry.do">Continue Message</html:link></strong>
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
