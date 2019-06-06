<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<script language="javascript">
function validate() {
	var form = document.forms['frmSave'];
	var keyword = trim(form.keyword.value);
	if (keyword.length == 0) {
		alert("Please enter a keyword");
		form.keyword.focus();
		return false;
	}
	
	var action = trim(form.action.value);
	var dest = form.destination.value;
	if (action == 'action.move' && dest == '0') {
		alert("Please enter a destination folder for the matching messages.");
		form.destination.focus();
		return false;
	}
	return true;
}

function submitFrm() {
	if (validate()) {
		document.forms['frmSave'].submit();
	}
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
		<html:form action="/saveFilter" method="post" styleId="frmSave" focus="keyword">
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
	            <td nowrap width="98%">
	            	<img src="/claros/images/grey_bullet.gif" width="5" height="12">
	            	<span class="tableheadertext youarehere">EMAIL - EDIT FILTER : </span>
	            </td>
	            <td align="right" width="1%">
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html:link href="#" onclick="submitFrm()"><img src="/claros/images/save.gif" width="24" height="24" border="0"><br>Save</html:link>
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
	
	
		      <table width="99%" border="0" align="left" cellpadding="0" cellspacing="0">
		        <tr>
		          <td valign="top" width="100%">
					<br/>
		
					<table border="0" cellspacing="0" cellpadding="3"  width="100%">
						<tr class="tablelines">
							<td width="15%">Message Portion:</td>
							<td width="85%" align="left" >
								<html:select property="portion" style="width:150px">
									<html:optionsCollection name="portions"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td>Condition: </td>
							<td>
								<html:select property="condition" style="width:150px">
									<html:optionsCollection name="conditions"/>
								</html:select>
							</td>
						</tr>
						<tr class="tablelines">
							<td>Keyword: </td>
							<td>
								<html:text property="keyword" size="30" maxlength="255"/>
							</td>
						</tr>
						<tr>
							<td>Action: </td>
							<td>
								<html:select property="action" style="width:150px"> 
									<html:optionsCollection name="actions" style="width:150px"/>
								</html:select>
							</td>
						</tr>
						<tr class="tablelines">
							<td>Destination: </td>
							<td>
								<html:select property="destination" style="width:150px">
									<html:option value="0">-</html:option>
									<c:forEach items="${folders}" var="item">
										<html-el:option value="${item.id}"><c:out value="${item.folderName}"/></html-el:option>
									</c:forEach>
								</html:select>
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
		</html:form>

    </td>
  </tr>
</table>
</body>
</html>
