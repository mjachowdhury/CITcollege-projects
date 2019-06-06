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
        <tr>
          <td width="1%"><img src="/claros/images/spacer.gif" width="10"></td>
          <td valign="top">
			  <table width="98%" align="center" cellpadding="0" cellspacing="0">
			      <!-- Begin You are here -->
			      <tr>
			        <td width="1%"><img src="/claros/images/spacer.gif" width="3" height="1"></td>
			        <td><img src="/claros/images/spacer.gif" width="600" height="1"></td>
			        </tr>
			      <tr>
			        <td>&nbsp;</td>
			        <td><table width="99%" height="20" border="0" cellpadding="3" cellspacing="3" align="left">
			          <tr>
			            <td nowrap width="99%">
	            			<img src="/claros/images/grey_bullet.gif" width="5" height="12">
			            	<span class="tableheadertext youarehere">CALENDAR - WEEKLY :</span> (<fmt:formatDate value="${dateDisplayedFrom}" pattern="dd MMM yyyy"/> - <fmt:formatDate value="${dateDisplayedTo}" pattern="dd MMM yyyy"/>)
			            </td>
			            <td align="right" nowrap width="1%">
			            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
			            		<tr>
				            		<td align="center" nowrap>
				            			<html:link page="/newEvent.do"><img src="/claros/images/add.gif" width="24" height="24" border="0"><br>New Event</html:link>
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

					  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" class="tablesm">
	                    <tr>
	                      <td bgcolor="#DFE4E8"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
	                          <tr>
	                            <td width="33%"><div align="center"><html:link page="/listDaily.do" styleClass="texttahoma12black">Daily Program</html:link></div></td>
	                            <td width="33%" bgcolor="#397BBE">
	                            	<table width="100%" border="0" cellspasing="1" cellpadding="0">
	                            		<tr>
	                            			<td align="left">
	                            				<html-el:link page="/listWeekly.do?t=${prevTime}" title="Previous Week"><span class="texttahoma12whitebold">&lt;</span></html-el:link>
	                            			</td>
	                            			<td align="center">
	                            				<div align="center" class="texttahoma12whitebold">Weekly Program</div>
	                            			</td>
	                            			<td align="right">
	                            				<html-el:link page="/listWeekly.do?t=${nextTime}" title="Next Week"><span class="texttahoma12whitebold">&gt;</span></html-el:link>
	                            			</td>
	                            		</tr>
	                            	</table>
	                            </td>
	                            <td width="33%"><div align="center"><html:link page="/listMonthly.do" styleClass="texttahoma12black">Monthly Program</html:link></div></td>
	                          </tr>
	                      </table></td>
	                    </tr>
	                    <tr>
	                      <td height="2" bgcolor="#FFFFFF"><span class="texttahoma12black"><img src="/claros/images/bos.gif" width="2" height="2"></span></td>
	                    </tr>
	                    <tr>
	                      <td bgcolor="#CCCCCC"><span class="texttahoma12black"><img src="/claros/images/bos.gif" width="1" height="1"></span></td>
	                    </tr>
	                    <tr>
	                      <td bgcolor="#397BBE"><span class="texttahoma12black"><img src="/claros/images/bos.gif" width="8" height="8"></span></td>
	                    </tr>
	
						<c:forEach items="${weeklyContent}" var="item" varStatus="stat">
		                    <tr>
							  <c:choose>
							    <c:when test="${stat.index % 2 == 0}">
								  <td height="45" bgcolor="#F2F2F2">
								</c:when>
								<c:otherwise> 
								  <td height="45" bgcolor="#DFE4E8">
								</c:otherwise>
							  </c:choose>
								<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" height="100%">
		                          <tr>
		                            <td width="35" class="texttahoma11"><span class="texttahoma12black"><fmt:formatDate value="${item.date}" pattern="EEE"/></span></td>
		                            <td width="20">&nbsp;</td>
		                            <td colspan="2">
										<c:forEach items="${item.appointments}" var="app">
											<html-el:link page="/showEvent.do?eid=${app.id}">
												<img src="/claros/images/grey_bullet.gif" width="5" height="12" border="0">&nbsp;<c:out value="${app.description}"/>
											</html-el:link>
										</c:forEach>
		                            </td>
		                          </tr>
		                          <tr>
		                            <td class="texttahoma11blue"><fmt:formatDate value="${item.date}" pattern="dd/MM"/></td>
		                            <td>&nbsp;</td>
		                          </tr>
		                      	</table>
		                      </td>
		                    </tr>
						</c:forEach>
	
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

