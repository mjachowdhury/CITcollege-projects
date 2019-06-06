<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<script language="javascript">
function validate() {
	var form = document.forms['groupFrm'];
	var members = form.members;
	var name = form.groupName;
	
	if (trim(name.value) == '') {
		alert("Please enter a short name for this group. You can use this group name at the compose e-mail screen to send an email to multiple people.");
		name.focus();
		return false;
	}
	if (members.options.length == 0) {
		alert("Please add at least one contact from my contacts to the group members list box");
		return false;
	}
	return true;
}

function submitFrm() {
	if (validate()) {
		for (i=0; i<document.forms['groupFrm'].members.options.length; i++) {
			var opt = document.forms['groupFrm'].members.options[i];
			if (!opt.selected) {
				opt.selected = true;
			}
		}
	
		document.forms['groupFrm'].submit();
	}
}

function move(source, dest) {

	// add the selected ones.
	for (i=0;i<source.options.length;i++) {
		var opt = source.options[i];
		if (opt.selected) {
			var optn = document.createElement("OPTION");
			optn.text = opt.text;
			optn.value = opt.value;
			dest.options.add(optn);
		}
	}

	// remove the transferred ones	
	for(i=source.options.length-1; i>=0; i--) {
		if(source.options[i].selected) {
			source.remove(i);
		}
	}
}
</script>

<style type="text/css">
<!--
.listContact {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	width: 200px;
	border: 1px solid;
}
.btnContact {
	height: 22px;
	width: 100px;
}
.headerContacts {
	font-size: 11px;
	color: #3333FF;
	font-weight: bold;
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
		            	<span class="tableheadertext youarehere">CONTACTS - GROUP</span>
		            </td>
		            <td align="right" nowrap>
		            	<table border="0" cellspacing="0" cellpadding="0">
	            		<tr>
	            		  <td align="center" nowrap><img src="../images/spacer.gif" width="12" height="1"></td>
	            		<td align="center" nowrap>
	            			<html:link page="/listGroups.do"><img src="/claros/images/groups.gif" width="24" height="24" border="0"><br>My Groups</html:link>
	            		</td>
	            		  <td align="center" nowrap><img src="../images/spacer.gif" width="12" height="1"></td>
	            		<td align="center" nowrap>
	            			<html:link href="#" onclick="submitFrm();"><img src="/claros/images/save.gif" width="24" height="24" border="0"><br>Save Group</html:link>
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
	            <td valign="top">

				  <html:form action="/saveGroup" method="post" styleId="groupFrm">
				    <html:hidden property="id"/>
					<table width="98%" border="0" cellspacing="3" cellpadding="3">
					  <tr>
					    <td width="5%" nowrap="nowrap"><strong>Group Name : </strong></td>
					    <td width="95%"><html:text property="groupName" maxlength="20" size="50"/></td>
					  </tr>
					  <tr>
					    <td>&nbsp;</td>
					    <td><span style="font-size: 9px;">Use group name to easily send a message to many e-mail addresses at once.</span></td>
					  </tr>
					  <tr>
					    <td colspan="2"><table width="450" border="0" cellspacing="3" cellpadding="3">
					      <tr>
					        <td width="200" nowrap="nowrap"><span class="headerContacts">My Contacts :</span><br>
					        	<select name="myContacts" size="12" class="listContact" multiple>
					        	  <c:forEach items="${contacts}" var="item">
					        	  	<option value="<c:out value="${item.value}"/>"><c:out value="${item.label}"/></option>
					        	  </c:forEach>
					        	</select>
					        </td>
					        <td width="50" nowrap="nowrap">
					          <input onclick="move(document.forms['groupFrm'].myContacts, document.forms['groupFrm'].members);" name="Submit2" type="button" class="btnContact" value="Add --&gt;" >
					          <br>
					          <input onclick="move(document.forms['groupFrm'].members, document.forms['groupFrm'].myContacts);" name="Submit" type="button" class="btnContact" value="&lt;-- Remove"></td>
					        <td width="200" nowrap="nowrap"><span class="headerContacts">Group Members :</span><br>
					        <html:select property="members" size="12" styleClass="listContact" multiple="true">
					        	<html:optionsCollection name="members"/>
					        </html:select>
					        </td>
					      </tr>
					    </table></td>
					    </tr>
					</table>

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
