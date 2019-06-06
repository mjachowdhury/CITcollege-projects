<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<link rel="stylesheet" href="/claros/css/compose.css" />
<script type="text/javascript" src="/claros/js/compose.js"></script>
<script language="Javascript">
var session = '<c:out value="${sessionId}"/>';
var htmlEmail = '<c:out value="${HtmlMail}"/>';
</script>
<c:if test="${HtmlMail == 'true'}">
	<script language="javascript" type="text/javascript" src="/claros/js/tiny_mce/tiny_mce.js"></script>
	<script language="javascript">
		tinyMCE.init({
			mode : "textareas",
			theme : "advanced",
			plugins : "emotions, iespell",
			theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_buttons1 : "fontsizeselect, bold, italic, underline, strikethrough, separator, justifyleft, justifycenter, justifyright, justifyfull, separator, bullist, numlist, separator, outdent, indent, separator, link, image, forecolor, backcolor, charmap, separator, emotions, iespell, code",
			theme_advanced_buttons2 : "",
			theme_advanced_buttons3 : "",
			relative_urls : "false",
			content_css : "../css/editor.css",
			verify_html : "false"
		});
	</script>
</c:if>
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
	            <td nowrap width="97%">
	            	<img src="/claros/images/grey_bullet.gif" width="5" height="12">
	            	<span class="tableheadertext youarehere">EMAIL - COMPOSE : </span>
	            </td>
	            <td align="right" nowrap>
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html:link href="#" onclick="mySubmit(1);"><img src="/claros/images/send.gif" width="24" height="24" border="0"><br>Send</html:link>
	            		</td>
	            		</tr>
	            	</table> 
	            </td>
            	<td><img src="/claros/images/spacer.gif" width="2"></td>
	            <td align="right" nowrap>
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html:link href="#" onclick="mySubmit(2);"><img src="/claros/images/attachments.gif" width="24" height="24" border="0"><br>Attachments</html:link>
	            		</td>
	            		</tr>
	            	</table>
	            </td>
	            <!--
            	<td><img src="/claros/images/spacer.gif" width="2"></td>
	            <td align="right" nowrap>
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html:link href="#" onclick="window.open('/claros/contacts/searchContact.do', 'searchContact', 'toolbar=false, status=true, width=600, height=400')"><img src="/claros/images/search.gif" width="24" height="24" border="0"><br>Add Contact</html:link>
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
				<table width="99%" border="0" cellspacing="1" cellpadding="0">
                  <tr>
                    <td width="99%" valign="top">
                      <table width="100%" border="0" cellspacing="3" cellpadding="3" align="center">
						  <!-- Begin Compose Table -->
						  <tr>
						  	<td>
					          <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" id="table">
					            <tr>
					              <td valign="top" bgcolor="#DFE4E8">
					              	<table width="100%" align="center" cellpadding="0" cellspacing="1" class="table">
						                <tr bgcolor="#FFFFFF">
						                  <td width="100%" bgcolor="#EEEEEE">
											<html:form action="/composeAction" method="post" styleId="myForm" focus="to">
											<html:hidden property="action" value=""/>
											<html:hidden property="from" value=""/>
											<html:hidden property="htmlEmail" value="false" />
											<table border="0"  cellspacing="0"  cellpadding="0"  width="100%">
												<tbody class="tableBody" >
													<tr>
														<td id="mytd">From: </td>
														<td id="mytd"><c:out value="${FromAddress}"/></td>
													</tr>
													<tr>
														<td>To:</td>
														<td><html:text property="to" size="80" onfocus="selectAdr(this)" styleClass="normalAdr"/></td>
													</tr>
													<tr>
														<td>Cc: </td>
														<td><html:text property="cc" size="80" onfocus="selectAdr(this)" styleClass="normalAdr"/></td>
													</tr>
													<tr>
														<td>Bcc: </td>
														<td><html:text property="bcc" size="80" onfocus="selectAdr(this)" styleClass="normalAdr"/></td>
													</tr>
													<tr>
														<td>Subject: </td>
														<td><html:text property="subject" size="80" styleClass="normalAdr"/></td>
													</tr>
												</tbody>
											</table>
											<html:textarea styleId="messageBody" rows="25" cols="60" style="width:98%" property="messageBody"></html:textarea>
											<br>
											<c:if test="${attachments != null}">
												<strong>Attachments:</strong>
												<hr size="1"/>
												<c:forEach items="${attachments}" var="item" varStatus="i">
												<c:out value="${i.index + 1}"/>. <c:out value="${item.fileName}" /> - <c:out value="${item.contentType}" /> - <c:out value="${item.sizeReadable}" /><br>
												</c:forEach>
											</c:if>
											</html:form>
						                  </td>
						                </tr>
						              </table>
						            </td>
						          </tr>
						       	</table>
						  	</td>
						  </tr>
						  <!-- End Compose Table -->
						</table>
                    </td>
                    <td width="1%" valign="top"><img src="/claros/images/spacer.gif" height="4" width="120"><table width="100%" border="0" cellspacing="0" cellpadding="1" align="right">
						  <tr>
						    <td><table width="100%" border="0" cellpadding="1" cellspacing="2" bgcolor="#FFFFFF">
						      <tr>
						        <td align="left" bgcolor="#3399CC"><strong class="contactheader">Contacts</strong></td>
						      </tr>
						      <tr>
						        <td align="left">
						        	Search: <input type="text" style="width:65px" onkeyup="search(this);">
						        </td>
						      </tr>
						      <tr>
						        <td><hr></td>
						      </tr>
						      <tr>
						        <td>
						            <table width="90%" border="0" cellspacing="0" cellpadding="2" style="cursor:hand;" id="contactlist">
						              <tr>
						                <td><div align="justify">Please type in some text(name, middle name, surname, email address) in the search textbox for to query for a contact. </div></td>
						              </tr>
						            </table>
						        </td>
						      </tr>
						      <tr>
						        <td><hr></td>
						      </tr>
						    </table></td>
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
<script language="javascript">
	if (htmlEmail == 'true') {
		document.forms["myForm"].htmlEmail.value = "true";
	}
</script>
