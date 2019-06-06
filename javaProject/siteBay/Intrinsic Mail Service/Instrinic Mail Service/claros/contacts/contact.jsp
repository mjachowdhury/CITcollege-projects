<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_popup.jsp" %>
<link href="/claros/css/contact.css" rel="stylesheet" type="text/css">
<script language="Javascript">
var nameFirst = <c:out value="${nameFirst}"/>;
</script>
<script src="/claros/js/contact.js" type="text/javascript" language="javascript"></script>
<link rel="stylesheet" type="text/css" media="all" href="/claros/includes/calendar/calendar-system.css" title="system" />
<script type="text/javascript" src="/claros/includes/calendar/calendar.js"></script>
<script type="text/javascript" src="/claros/includes/calendar/lang/calendar-en.js"></script>
<script type="text/javascript" src="/claros/includes/calendar/calendar-setup.js"></script>
</head>
<body bgcolor="#E9E9E9" onload="nameTitle()">
  <html:form action="/saveContact" method="post" styleId="contactFrm">
  <html:hidden property="id"/>
  <table width="560" border="0" cellspacing="1" cellpadding="0">
    <tr>
		<td><table width="100%" border="0" cellpadding="3" cellspacing="1">
          <tr>
            <td valign="middle"><table width="100%" border="0" cellspacing="3" cellpadding="3">
                <tr>
                  <td width="4%"><img src="/claros/images/ico_contact.gif" width="24" height="26" /></td>
                  <td width="76%" class="contact_title_table_text" height="26" id="name">&nbsp;</td>
                  <td width="20%" nowrap="nowrap">
                  	<div align="center"><a href="#" onclick="submitFrm()"><img src="/claros/images/save_16.gif" border="0" width="16" height="16"><br>Save Contact</a></div>
                  </td>
                </tr>
            </table></td>
          </tr>
        </table>
		</td>
	</tr>
	<tr>
		<td>
		<br/>
		<tab:tabview width="586">
			<tab:tab text="General" width="90">
				<table width="564"  border="0" cellspacing="1" cellpadding="1" align="center">
			        <tr bgcolor="#F4F4F4">
			          <td>&nbsp;</td>
			          <td>&nbsp;</td>
			          <td>&nbsp;</td>
			          <td>&nbsp;</td>
			        </tr>
			        <tr>
			          <td width="101" align="left" scope="row">First Name : </td>
			          <td width="180"><html:text property="firstName" onkeyup="nameTitle()" maxlength="100"/></td>
			          <td width="101" align="left" >Prim. Gsm No : </td>
			          <td width="180"><html:text property="gsmNoPrimary" maxlength="30"/></td>
			        </tr>
			        <tr>
			          <td align="left" bgcolor="#F4F4F4" scope="row">Middle Name : </td>
			          <td bgcolor="#F4F4F4"><html:text property="middleName" onkeyup="nameTitle()" maxlength="100"/></td>
			          <td align="left">Sec. Gsm No : </td>
			          <td bgcolor="#F4F4F4"><html:text property="gsmNoAlternative" maxlength="30"/></td>
			        </tr>
			        <tr>
			          <td align="left" scope="row">Last Name : </td>
			          <td><html:text property="lastName" onkeyup="nameTitle()" maxlength="100"/></td>
			          <td align="left">Prim. E-mail : </td>
			          <td><html:text property="emailPrimary" maxlength="255"/></td>
			        </tr>
			        <tr bgcolor="#F4F4F4">
			          <td align="left" bgcolor="#F4F4F4" scope="row">Nickname : </td>
			          <td bgcolor="#F4F4F4"><html:text property="nickName" maxlength="50"/></td>
			          <td align="left">Sec. E-mail : </td>
			          <td bgcolor="#F4F4F4"><html:text property="emailAlternative" maxlength="255"/></td>
			        </tr>
			        <tr>
			          <td align="left" scope="row">Spouse's Name : </td>
			          <td><html:text property="spouseName" maxlength="255"/></td>
			          <td align="left">Web Page : </td>
			          <td><html:text property="webPage" maxlength="255"/></td>
			        </tr>
			        <tr bgcolor="#F4F4F4">
			          <td align="left" nowrap="NOWRAP" bgcolor="#F4F4F4" scope="row">Birthday : </td>
			          <td bgcolor="#F4F4F4">
			          	<input type="text" name="birthday" id="birthday" autocomplete="off" readonly="true" value="<c:out value="${ContactFormBean.birthDay}"/>">
						<img src="/claros/images/trigger.gif" width="18" height="18" id="imgBirthday" style="cursor: pointer;"/>
			          </td>
			          <td align="left" nowrap="NOWRAP" scope="row">Sex : </td>
			          <td bgcolor="#F4F4F4"><html:select property="sex">
								<html:option value="">Please Select</html:option>
								<html:option value="M">Male</html:option>
								<html:option value="F">Female</html:option>
							</html:select></td>
			        </tr>
			        <tr>
			          <td align="left" nowrap="NOWRAP" scope="row">Anniversary : </td>
			          <td>
			          	<input type="text" name="anniversary" id="anniversary" autocomplete="off" readonly="true" value="<c:out value="${ContactFormBean.anniversary}"/>">
						<img src="/claros/images/trigger.gif" width="18" height="18" id="imgAnniversary" style="cursor: pointer;"/>
			          </td>
			          <td align="left" nowrap="NOWRAP" scope="row">Title : </td>
			          <td><html:text property="title" maxlength="50"/></td>
			        </tr>
			        <tr align="left">
			          <td colspan="4" nowrap="NOWRAP" bgcolor="#F4F4F4" scope="row">Notes : </td>
			        </tr>
			        <tr align="left">
			          <td colspan="4" nowrap="NOWRAP" bgcolor="#F4F4F4" scope="row">
			          	<html:textarea property="personalNote" cols="80" rows="5" styleClass="contacttextarea"/>
			          </td>
			        </tr>
			        <!--
			        <tr bgcolor="#F4F4F4">
			          <td width="50" colspan="4" align="left" nowrap="NOWRAP" bgcolor="#F4F4F4" class="contactstatus" scope="row">&nbsp;</td>
			        </tr>
			        -->
			        <tr>
			          <td colspan="4">&nbsp;</td>
			        </tr>
			        <tr>
			          <td colspan="4">&nbsp;</td>
			        </tr>
			        <tr>
			          <td colspan="4">&nbsp;</td>
			        </tr>
			    </table>
			</tab:tab>
			<tab:tab text="Home" width="90">
				<div style="background-color:#F4F4F4">
				<table width="564"  border="0" cellspacing="1" cellpadding="1" align="center">
			        <tr bgcolor="#F4F4F4">
			          <td>&nbsp;</td>
			          <td>&nbsp;</td>
			        </tr>
			        <tr bgcolor="#F4F4F4">
			          <td width="101" align="left" nowrap>City : </td>
			          <td width="465"><html:text property="homeCity" size="25" maxlength="255"/></td>
			        </tr>
			        <tr>
			          <td align="left" nowrap>Province : </td>
			          <td><html:text property="homeProvince" size="25" maxlength="255"/></td>
			        </tr>
			        <tr bgcolor="#F4F4F4">
			          <td align="left" nowrap>Zip : </td>
			          <td><html:text property="homeZip" size="13" maxlength="5"/></td>
			        </tr>
			        <tr>
			          <td align="left" nowrap>Country : </td>
			          <td><html:text property="homeCountry" size="25" maxlength="100"/></td>
			        </tr>
			        <tr bgcolor="#F4F4F4">
			          <td align="left" nowrap>Phone : </td>
			          <td><html:text property="homePhone" size="15" maxlength="30"/></td>
			        </tr>
			        <tr>
			          <td align="left" nowrap>Fax : </td>
			          <td><html:text property="homeFaks" size="15" maxlength="30"/></td>
			        </tr>
			        <tr align="left">
			          <td colspan="2" nowrap="NOWRAP" bgcolor="#F4F4F4" scope="row">
			          	Address : 
			          </td>
			        </tr>
			        <tr align="left">
			          <td colspan="2" nowrap="NOWRAP" bgcolor="#F4F4F4" scope="row">
			          	<html:textarea property="homeAddress" cols="80" rows="5" styleClass="contacttextarea"/>
			          </td>
			        </tr>
			        <tr>
			          <td colspan="2">&nbsp;</td>
			        </tr>
			        <tr>
			          <td colspan="2">&nbsp;</td>
			        </tr>
			        <tr>
			          <td colspan="2">&nbsp;</td>
			        </tr>
			        <tr>
			          <td colspan="2">&nbsp;</td>
			        </tr>
				</table>
			</tab:tab>
			<tab:tab text="Work" width="90">
				<table width="564"  border="0" cellspacing="1" cellpadding="1" align="center">
			        <tr bgcolor="#F4F4F4">
			          <td>&nbsp;</td>
			          <td>&nbsp;</td>
			          <td>&nbsp;</td>
			          <td>&nbsp;</td>
			        </tr>
			        <tr>
			          <td width="101" align="left" scope="row">Company : </td>
			          <td width="180"><html:text property="workCompany" maxlength="100"/></td>
			          <td width="101" align="left">Job Title : </td>
			          <td width="180"><html:text property="workJobTitle" maxlength="100"/></td>
			        </tr>
			        <tr>
			          <td align="left">Office : </td>
			          <td><html:text property="workOffice" maxlength="100"/></td>
			          <td align="left">Profession : </td>
			          <td><html:text property="workProfession" maxlength="100"/></td>
			        </tr>
			        <tr>
			          <td align="left" nowrap="nowrap">Manager's Name : </td>
			          <td><html:text property="workManagerName" maxlength="255"/></td>
			          <td align="left" nowrap="nowrap">Assistant's Name : </td>
			          <td><html:text property="workAssistantName" maxlength="255"/></td>
			        </tr>
			        <tr>
			          <td align="left">Department : </td>
			          <td><html:text property="workDepartment" maxlength="100"/></td>
			          <td align="left">City : </td>
			          <td><html:text property="workCity" maxlength="255"/></td>
			        </tr>
			        <tr>
			          <td align="left">Province : </td>
			          <td><html:text property="workProvince" maxlength="255"/></td>
			          <td align="left">Zip : </td>
			          <td><html:text property="workZip" maxlength="5"/></td>
			        </tr>
			        <tr>
			          <td align="left">Country : </td>
			          <td><html:text property="workCountry" maxlength="100"/></td>
			          <td align="left">Phone : </td>
			          <td><html:text property="workPhone" maxlength="30"/></td>
			        </tr>
			        <tr>
			          <td align="left">Fax : </td>
			          <td><html:text property="workFaks" maxlength="30"/></td>
			          <td align="left">&nbsp;</td>
			          <td>&nbsp;</td>
			        </tr>
					<tr>
					  <td colspan="4" align="left">Address : </td>
					</tr>
			        <tr align="left">
			          <td colspan="4">
			          	<html:textarea property="workAddress" cols="80" rows="5" styleClass="contacttextarea"/>
			          </td>
			        </tr>
			        <tr>
			          <td colspan="4">&nbsp;</td>
			        </tr>
			        <tr>
			          <td colspan="4">&nbsp;</td>
			        </tr>
			        <tr>
			          <td colspan="4">&nbsp;</td>
			        </tr>
				</table>
			</tab:tab>
		  </tab:tabview>

		</td>
	</tr>
   </table>

</html:form>
</body>
</html>

<script type="text/javascript">
    Calendar.setup({
        inputField     :    "birthday",
        ifFormat       :    "%Y-%m-%d",
        button         :    "imgBirthday",
        showsTime      :    false,
        singleClick    :    true
    });

    Calendar.setup({
        inputField     :    "anniversary",
        ifFormat       :    "%Y-%m-%d",
        button         :    "imgAnniversary",
        showsTime      :    false,
        singleClick    :    true
    });
    
    
</script>
