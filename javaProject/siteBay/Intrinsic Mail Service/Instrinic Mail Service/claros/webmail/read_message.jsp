<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<script language="Javascript">
	function goOn(id) {
		frames["dumpMsg"].location.href = '/claros/webmail/showPart.do?partid=' + id;
	}
	function openAddContact() {
		var addr = escape('<c:out value="${email.baseHeader.fromShown}"/>');
		window.open('/claros/contacts/newContact.do?pre=' + addr, '','scrollbars=no,status=yes, width=607, height=427, toolbar=no')	
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
	            <td nowrap width="95%">
	            	<img src="/claros/images/grey_bullet.gif" width="5" height="12">
	            	<span class="tableheadertext youarehere">
	            		E-MAIL - READ MESSAGE : 
	            	</span> 
	            </td>
	            <td align="right" width="1%">
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html:link page="/composeEntry.do?action=reply"><img src="/claros/images/reply.gif" width="24" height="24" border="0"><br>Reply</html:link>
	            		</td>
	            		</tr>
	            	</table>
	            </td>
	            <td align="right" width="1%">
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html:link page="/composeEntry.do?action=reply_all"><img src="/claros/images/reply.gif" width="24" height="24" border="0"><br>Reply All</html:link>
	            		</td>
	            		</tr>
	            	</table>
	            </td>
	            <td><img src="/claros/images/spacer.gif" width="2"></td>
	            <td align="right" width="1%">
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html:link page="/composeEntry.do?action=forward"><img src="/claros/images/forward.gif" width="24" height="24" border="0"><br>Forward</html:link>
	            		</td>
	            		</tr>
	            	</table>
	            </td>
	            <td><img src="/claros/images/spacer.gif" width="2"></td>
	            <td align="right" width="1%">
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html-el:link page="/deleteMsgs.do?msg=${email.msgId}"><img src="/claros/images/delete.gif" width="24" height="24" border="0"><br>Delete</html-el:link>
	            		</td>
	            		</tr>
	            	</table>
	            </td>
	            <td><img src="/claros/images/spacer.gif" width="2"></td>
	            <td align="right" width="1%">
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html-el:link href="#" onclick="openAddContact()"><img src="/claros/images/contact.gif" width="24" height="24" border="0"><br>Save Sender</html-el:link>
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
	
				<table width="100%" border="0" cellspacing="3" cellpadding="3">
				  <tr>
				  	<td>
			          <table width="100%" border="0" align="left" cellpadding="0" cellspacing="1" class="table" id="table">
			            <tr>
			              <td valign="top" bgcolor="#F2F2F2">
			              	<table width="100%" align="left" cellpadding="0" cellspacing="1">
				                <tr bgcolor="#FFFFFF">
				                  <td width="100%" bgcolor="#F2F2F2">
	
									<table border="0"  cellspacing="0"  cellpadding="0"  width="100%">
										<tbody>
											<tr>
												<td id="mytd" width="60"><strong>From:</strong> </td>
												<td><c:out value="${email.baseHeader.fromShown}" default="Unspecified"/></td>
											</tr>
											<tr>
												<td id="mytd"><strong>To:</strong> </td>
												<td><c:out value="${email.baseHeader.toShown}" default="Unspecified"/></td>
											</tr>
											<c:if test="${email.baseHeader.ccShown != null && email.baseHeader.ccShown != ''}">
												<tr>
													<td id="mytd"><strong>Cc:</strong> </td>
													<td><c:out value="${email.baseHeader.ccShown}"/></td>
												</tr>
											</c:if>
											<tr>
												<td id="mytd"><strong>Date:</strong> </td>
												<td><c:out value="${email.baseHeader.dateShown}" default="N/A"/></td>
											</tr>
											<tr>
												<td id="mytd"><strong>Subject:</strong> </td>
												<td><c:out value="${email.baseHeader.subject}" default="No Subject"/></td>
											</tr>
											<c:if test="${email.parts != null}">
												<tr>
													<td id="mytd"><strong>Parts:</strong> </td>
													<td>
														<c:forEach items="${email.parts}" var="part" varStatus="stat">
															<c:if test="${stat.index != 0 && stat.index % 6 == 0}"><br></c:if>
															<a href="#" style="color:#0066CC;text-decoration:none;" onclick="goOn('<c:out value="${part.id}"/>')"><img src="/claros/images/putinfolder.gif" border="0" alt="<c:out value="${part.fileName}" /> - <c:out value="${part.contentType}"/>">&nbsp;<c:out value="${part.fileName}" /></a>&nbsp;&nbsp;&nbsp;
														</c:forEach>
													</td>
												</tr>
											</c:if>								
										</tbody>
									</table>
									<iframe name="dumpMsg" frameborder='1' src='showPart.do' scrolling='auto' width='100%' height='500'>
				                  </td>
				                </tr>
				              </table>
				            </td>
				          </tr>
				      </table>
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


