<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<link rel="stylesheet" type="text/css" media="all" href="/claros/includes/calendar/calendar-system.css" title="system" />
<script type="text/javascript" src="/claros/includes/calendar/calendar.js"></script>
<script type="text/javascript" src="/claros/includes/calendar/lang/calendar-en.js"></script>
<script type="text/javascript" src="/claros/includes/calendar/calendar-setup.js"></script>
<script language="javascript">
function validate() {
	var form = document.forms['eventFrm'];
	var recordDate = trim(form.recordDate.value);
	var description = trim(form.description.value);
	
	if (recordDate.length == 0) {
		alert("Please select a date/time for this event.");
		return false;
	}
	
	if (description.length == 0) {
		alert("Please enter a description for this event. It is a required field.");
		form.description.focus();
		return false;
	}
	return true;
}

function submitFrm() {
	if (validate()) {
		document.forms['eventFrm'].submit();
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
      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="/claros/images/spacer.gif" width="10"></td>
          <td valign="top">
			<html:form action="/eventActions" method="post" styleId="eventFrm">
			<html:hidden property="id"/>
			<html:hidden property="delete" value=""/>
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
		            <td width="99%">
	            		<img src="/claros/images/grey_bullet.gif" width="5" height="12">
		            	<span class="tableheadertext youarehere">:: CALENDAR - EDIT EVENT</span>
		            </td>
		            <c:if test="${EventFormBean.id != null}">
			            <td align="right" width="1%">
			            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
			            		<tr><td align="center" nowrap>
			            			<html:image src="/claros/images/delete.gif" onclick="document.eventFrm.delete.value = 'delete';"/><br>Delete
			            		</td>
			            		</tr>
			            	</table>
			            </td>
		            	<td><img src="/claros/images/spacer.gif" width="3"></td>
		            </c:if>
		            <td align="right" width="1%">
		            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
		            		<tr><td align="center" nowrap>
	            				<html:link href="#" onclick="return(submitFrm());"><img src="/claros/images/save.gif" width="24" height="24" border="0"><br>Save</html:link>
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
	            <td valign="top" width="100%">
				  <br/>


					<table border="0"  cellspacing="0"  cellpadding="0"  width="100%"  class="tableRegion" >
						<tr class="tablelines">
							<td><strong>Date: </strong></td>
							<td>
								<input type="text" value="<c:out value="${EventFormBean.recordDate}"/>" id="recordDate" name="recordDate" readonly="true">
								<img src="/claros/images/trigger.gif" width="18" height="18" id="imgRecordDate" style="cursor: pointer;"/>
							</td>
						</tr>
						<tr>
							<td>Event Type</td>
							<td>
								<html:select property="category">
									<html:option value="Anniversary">Anniversary</html:option>
									<html:option value="Appointment">Appointment</html:option>
									<html:option value="Bill Payment">Bill Payment</html:option>
									<html:option value="Birthday">Birthday</html:option>
									<html:option value="Breakfast">Breakfast</html:option>
									<html:option value="Call">Call</html:option>
									<html:option value="Chat">Chat</html:option>
									<html:option value="Class">Class</html:option>
									<html:option value="Club Event">Club Event</html:option>
									<html:option value="Concert">Concert</html:option>
									<html:option value="Dinner">Dinner</html:option>
									<html:option value="Graduation">Graduation</html:option>
									<html:option value="Happy Hour">Happy Hour</html:option>
									<html:option value="Holiday">Holiday</html:option>
									<html:option value="Interview">Interview</html:option>
									<html:option value="Lunch">Lunch</html:option>
									<html:option value="Meeting">Meeting</html:option>
									<html:option value="Movie">Movie</html:option>
									<html:option value="Net Event">Net Event</html:option>
									<html:option value="Other">Other</html:option>
									<html:option value="Party">Party</html:option>
									<html:option value="Performance">Performance</html:option>
									<html:option value="Reunion">Reunion</html:option>
									<html:option value="Sports Event">Sports Event</html:option>
									<html:option value="Travel">Travel</html:option>
									<html:option value="TV Show">TV Show</html:option>
									<html:option value="Vacation">Vacation</html:option>
									<html:option value="Wedding">Wedding</html:option>
								</html:select>
							</td>
						</tr>
						<tr class="tablelines">
							<td><strong>Repeating: </strong></td>
							<td>
								<html:select property="repeatType">
									<html:option value="1">No Repeat</html:option>
									<html:option value="2">Every Month</html:option>
									<html:option value="3">Every Year</html:option>
									<html:option value="4">Every Week</html:option>
									<html:option value="5">Every Two Weeks</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td><strong>Notes: </strong></td>
							<td>
								<html:textarea property="description" cols="40" rows="10"></html:textarea>
							</td>
						</tr>
						<html:hidden property="reminderDays" value="-1"/>
						<!--
						<tr class="even">
							<td><strong>Reminder: </strong></td>
							<td>
								<html:select property="reminderDays">
									<html:option value="-1">No Reminder</html:option>
									<html:option value="0">On Time</html:option>
									<html:option value="300">5 minutes</html:option>
									<html:option value="900">15 minutes</html:option>
									<html:option value="1800">30 minutes</html:option>
									<html:option value="3600">1 hour</html:option>
									<html:option value="7200">2 hours</html:option>
									<html:option value="10800">3 hours</html:option>
									<html:option value="21600">6 hours</html:option>
									<html:option value="43200">12 hours</html:option>
									<html:option value="86400">1 day</html:option>
									<html:option value="172800">2 days</html:option>
									<html:option value="259200">3 days</html:option>
									<html:option value="345600">4 days</html:option>
									<html:option value="432000">5 days</html:option>
									<html:option value="518400">6 days</html:option>
									<html:option value="604800">7 days</html:option>
									<html:option value="691200">8 days</html:option>
									<html:option value="777600">9 days</html:option>
									<html:option value="864000">10 days</html:option>
									<html:option value="950400">11 days</html:option>
									<html:option value="1036800">12 days</html:option>
									<html:option value="1123200">13 days</html:option>
									<html:option value="1209600">14 days</html:option>
								</html:select>
							</td>
						</tr>
						-->
						<tr class="tablelines">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>



			  	</td>
			  </tr>
		    </table>

			</html:form>
		  </td>
		</tr>
	  </table>
    </td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript">
    Calendar.setup({
        inputField     :    "recordDate",
        ifFormat       :    "%Y-%m-%d %H:%M",
        button         :    "imgRecordDate",
        showsTime      :    true,
        timeFormat     :    "24",
        singleClick    :    true
    });
</script>
