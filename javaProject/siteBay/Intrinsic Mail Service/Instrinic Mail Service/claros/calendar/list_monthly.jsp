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
			            	<span class="tableheadertext youarehere">CALENDAR - MONTHLY :</span> (<fmt:formatDate value="${dateDisplayed}" pattern="MMMMM yyyy"/>)
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
					  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="tablesm">
	                    <tr>
	                      <td bgcolor="#DFE4E8"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
	                          <tr>
	                            <td width="33%"><div align="center"><html:link page="/listDaily.do" styleClass="texttahoma12black">Daily Program</html:link></div></td>
	                            <td width="33%"><div align="center"><html:link page="/listWeekly.do" styleClass="texttahoma12black">Weekly Program</html:link></div></td>
	                            <td width="33%" bgcolor="#397BBE">
	                            	<table width="100%" border="0" cellspasing="1" cellpadding="0">
	                            		<tr>
	                            			<td align="left">
	                            				<html-el:link page="/listMonthly.do?t=${prevTime}" title="Previous Month"><span class="texttahoma12whitebold">&lt;</span></html-el:link>
	                            			</td>
	                            			<td align="center">
	                            				<div align="center" class="texttahoma12whitebold">Monthly Program</div>
	                            			</td>
	                            			<td align="right">
	                            				<html-el:link page="/listMonthly.do?t=${nextTime}" title="Next Month"><span class="texttahoma12whitebold">&gt;</span></html-el:link>
	                            			</td>
	                            		</tr>
	                            	</table>
	                            </td>
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
	                    <tr>
	                      <td height="45" bgcolor="#F2F2F2"><table width="98%"  border="0" align="center" cellpadding="2" cellspacing="0" class="tablesm">
	                          <tr bgcolor="#397BBE" class="texttahoma11white">
	                            <td width="9%" class="texttahoma12black">&nbsp;</td>
	                            <td width="13%" bgcolor="#397BBE"><div align="center" class="texttahoma12whitebold">MONDAY</div></td>
	                            <td width="13%" bgcolor="#397BBE"><div align="center" class="texttahoma12whitebold">TUESDAY</div></td>
	                            <td width="13%" bgcolor="#397BBE"><div align="center" class="texttahoma12whitebold">WEDNESDAY</div></td>
	                            <td width="13%" bgcolor="#397BBE"><div align="center" class="texttahoma12whitebold">THURSDAY</div></td>
	                            <td width="13%" bgcolor="#397BBE"><div align="center" class="texttahoma12whitebold">FRIDAY</div></td>
	                            <td width="13%" bgcolor="#397BBE"><div align="center" class="texttahoma12whitebold">SATURDAY</div></td>
	                            <td width="13%" bgcolor="#397BBE"><div align="center" class="texttahoma12whitebold">SUNDAY</div></td>
	                          </tr>


							  <c:forEach items="${monthlyContent}" var="week" varStatus="stat">
								<c:choose>
									<c:when test="${stat.index % 2 == 0}">
										<c:set var="bgcolor" value="#DFE4E8"/>
									</c:when>
									<c:otherwise> 
										<c:set var="bgcolor" value="#F2F2F2"/>
									</c:otherwise>
								</c:choose>
								<tr bgcolor="<c:out value="${bgcolor}"/>">
								<td height="45" class="texttahoma12black"><div align="center">Week <c:out value="${week.weekInYear}"/></div></td>
									<c:forEach items="${week.days}" var="day">
		                              <td bgcolor="<c:out value="${bgcolor}"/>"><table width="99%" height="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="texttahoma10">
		                                <tr>
		                                  <td valign="top" width="14%"><fmt:formatDate value="${day.date}" pattern="d"/></td>
		                                </tr>
		                                <tr>
		                                  <td valign="top" height="45" width="14%">
											<c:forEach items="${day.appointments}" var="app" varStatus="appStatus">
												<img src="/claros/images/grey_bullet.gif" width="5" height="12" border="0">&nbsp;<html-el:link page="/showEvent.do?eid=${app.id}"><str:truncateNicely upper="10" appendToEnd="..."><c:out value="${app.description}"/></str:truncateNicely></html-el:link><br/>
											</c:forEach>
		                                  </td>
		                                </tr>
		                                </table></td>
									</c:forEach>
								</td>
							  </c:forEach>
	                      </table></td>
	                    </tr>
	                    <tr>
	                      <td bgcolor="#397BBE">&nbsp;</td>
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
