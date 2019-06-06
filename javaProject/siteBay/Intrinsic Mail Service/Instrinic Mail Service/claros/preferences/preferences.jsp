<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
<script language="javascript" type="text/javascript" src="/claros/js/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript" src="/claros/js/preferences.js"></script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <%@ include file="/includes/page_top.jsp" %>
  <tr>
    <td>&nbsp;</td>
    <%@ include file="/includes/navbar.jsp" %>
    <td valign="top">
	  <html:form action="/prefSave" method="post" styleId="prefForm">
		<br/>
	    <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
	      <!-- Begin You are here -->
	      <tr>
	        <td width="1%"><img src="/claros/images/spacer.gif" width="3" height="1"></td>
	        <td><img src="/claros/images/spacer.gif" width="600" height="1"></td>
	        </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td>
	        
	        <table width="99%" height="20" border="0" cellpadding="3" cellspacing="3" align="left">
	          <tr>
	            <td nowrap width="96%">
	            	<img src="/claros/images/grey_bullet.gif" width="5" height="12">
	            	<span class="tableheadertext youarehere">PREFERENCES : </span>
	            </td>
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
	        <td>&nbsp;</td>
		    <td>
			  <!-- BEGIN CONTENT -->
	
		      <table width="99%" border="0" align="left" cellpadding="0" cellspacing="1">
		        <tr>
		          <td valign="top" width="99%">
					<strong>Email Preferences : </strong>
					<table border="0"  cellspacing="0"  cellpadding="3"  width="100%">
						<tr class="tablelines">
							<td width="23%">Full Name : </td>
							<td width="77%">
								<html-el:text property="fullname" size="60"/>
							</td>
						</tr>
						<tr>
							<td>From Address : </td>
							<td>
								<html-el:text property="fromAddress" size="60"/>
							</td>
						</tr>
						<tr class="tablelines">
							<td>Reply To Address : </td>
							<td>
								<html-el:text property="replyToAddress" size="60"/>
							</td>
						</tr>
						<tr>
							<td>Preferred Mail Format (sending): </td>
							<td>
								<html-el:radio property="mailFormat" value="html" /> HTML Mail
								<html-el:radio property="mailFormat" value="text" /> Plain Text Mail
							</td>
						</tr>
						<tr class="tablelines">
							<td>Signature : </td>
							<td>
							  <table border="0" cellspacing="10" cellpadding="0">
								<tr>
								  <td>
								  	<html-el:textarea property="signature" rows="6" cols="40"/>
								  </td>
								  <td nowrap="nowrap">
								  	<html:radio property="signaturePos" value="top" />Insert at top<br>
								  	<html:radio property="signaturePos" value="bottom" />Append at bottom<br>
								  </td>
								</tr>
							  </table>
							</td>
						</tr>
						<tr>
							<td>Perform Spam Analysis : </td>
							<td>
								<html-el:radio property="spamAnalysis" value="yes" /> Yes
								<html-el:radio property="spamAnalysis" value="no" /> No
							</td>
						</tr>
						<tr class="tablelines">
							<td>Spam Sensitivity : </td>
							<td>
								<html:select property="spamUserAccepts">
									<html:option value="0.9">Very Light Mode</html:option>
									<html:option value="0.8">Light Mode</html:option>
									<html:option value="0.6">Moderate</html:option>
									<html:option value="0.2">Heavy Mode</html:option>
									<html:option value="0.1">Paranoid!</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td>Do Not Perform Spam Analysis On My Contacts : </td>
							<td>
								<html:checkbox property="safeContacts" value="yes"/>
							</td>
						</tr>
						<tr class="tablelines">
							<td>Delete Fetched : </td>
							<td>
								<html-el:radio property="deleteFetched" value="yes" /> Yes
								<html-el:radio property="deleteFetched" value="no" /> No
							</td>
						</tr>
						<tr>
							<td>Save Sent Mail : </td>
							<td>
								<html-el:radio property="saveSent" value="yes" /> Yes
								<html-el:radio property="saveSent" value="no" /> No
							</td>
						</tr>
					</table>
		
					<br/>
					<br/>
		
					<strong>Contacts Preferences : </strong>
					<table border="0"  cellspacing="0"  cellpadding="3"  width="100%">
						<tr class="tablelines">
							<td width="23%">Prefered Name Display Type : </td>
							<td width="77%">
								<html-el:radio property="displayType" value="nameFirst" />First Middle Last<br>
								<html-el:radio property="displayType" value="surnameFirst" />Last, First Middle<br>
							</td>
						</tr>
					</table>
					<br/>
					<br/>
					
					<strong>Calendar Preferences : </strong>
					<table border="0"  cellspacing="0"  cellpadding="3"  width="100%">
						<tr class="tablelines">
							<td width="23%">User Timezone : </td>
							<td width="77%">
								<html:select property="timeZone" style="width:300px">
									<html:optionsCollection name="timeZones"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td>Calendar Display Type : </td>
							<td>
								<html:radio property="calendarHour" value="12"/>12 Hour Display
								<html:radio property="calendarHour" value="24"/>24 Hour Display
							</td>
						</tr>
						<tr class="tablelines">
							<td>Day Starts At : </td>
							<td>
								<html:select property="dayStarts">
									<html:option value="00">00:00</html:option>
									<html:option value="01">01:00</html:option>
									<html:option value="02">02:00</html:option>
									<html:option value="03">03:00</html:option>
									<html:option value="04">04:00</html:option>
									<html:option value="05">05:00</html:option>
									<html:option value="06">06:00</html:option>
									<html:option value="07">07:00</html:option>
									<html:option value="08">08:00</html:option>
									<html:option value="09">09:00</html:option>
									<html:option value="10">10:00</html:option>
									<html:option value="11">11:00</html:option>
									<html:option value="12">12:00</html:option>
									<html:option value="13">13:00</html:option>
									<html:option value="14">14:00</html:option>
									<html:option value="15">15:00</html:option>
									<html:option value="16">16:00</html:option>
									<html:option value="17">17:00</html:option>
									<html:option value="18">18:00</html:option>
									<html:option value="19">19:00</html:option>
									<html:option value="20">20:00</html:option>
									<html:option value="21">21:00</html:option>
									<html:option value="22">22:00</html:option>
									<html:option value="23">23:00</html:option>
								</html:select>
							</td>
						</tr>
					</table>
				  </td>
				</tr>
			  </table>

			  &nbsp;<br>
			  &nbsp;<br>
			  &nbsp;<br>
			  &nbsp;<br>
			  &nbsp;<br>
			  &nbsp;<br>
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



