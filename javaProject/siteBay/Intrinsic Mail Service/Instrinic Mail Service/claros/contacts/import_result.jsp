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
		            	<span class="tableheadertext youarehere">CONTACTS - IMPORT</span>
		            </td>
		            <td align="right" nowrap>
		            	<table border="0" cellspacing="0" cellpadding="0">
	            		<tr>
	            		  <td align="center" nowrap><a href="#" onClick="fastsearch()"><img src="/claros/images/search.gif" width="24" height="24" border="0"><br>
	        		      	Quick Search </a>
	        		      </td>
	            		  <td align="center" nowrap><img src="../images/spacer.gif" width="12" height="1"></td>
	            		<td align="center" nowrap>
	            			<html:link page="/listGroups.do"><img src="/claros/images/groups.gif" width="24" height="24" border="0"><br>My Groups</html:link>
	            		</td>
	            		  <td align="center" nowrap><img src="../images/spacer.gif" width="12" height="1"></td>
	            		<td align="center" nowrap>
	            			<html:link href="#" onclick="window.open('/claros/contacts/newContact.do','','scrollbars=no,status=yes, width=607, height=427, toolbar=no')"><img src="/claros/images/new_note.gif" width="24" height="24" border="0"><br>New Contact</html:link>
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
	            <td valign="top" width="97%" align="center">
			    	<br/>
			    	<br/>
			    	<br/>
	    			<span class="youarehere"><p><strong>
				    	<c:choose>
				    		<c:when test="${err == null}">
				      			Successfully imported all(<c:out value="${success}"/>) contacts.
				    		</c:when>
				    		<c:otherwise>
				    			Successfully imported <c:out value="${success}"/> contacts but <c:out value="${fail}"/> of them have failed.
				    		</c:otherwise>
				    	</c:choose>
	      			</strong></p></span>
			    	<br/>
			    	<br/>
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
