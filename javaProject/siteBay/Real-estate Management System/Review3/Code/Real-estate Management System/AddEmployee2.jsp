  <HTML>
  <HEAD>
  	<LINK href="styles.css" type="text/css" rel="stylesheet">

    <TITLE> Add Employee </TITLE>
	
	<SCRIPT LANGUAGE="JavaScript">
	<!--
	history.go(+1);
	function check(){
	var EmpName = document.AddEmpForm.EMPName.value;
	var EMPNO = document.AddEmpForm.EMPNO.value;
	var EmpEmailID = document.AddEmpForm.EmpEmailID.value;
	var PrimarySkillset = document.AddEmpForm.PrimarySkillset.value;
	var Remarks = document.AddEmpForm.Remarks.value;
	var CLocIndex= document.AddEmpForm.CurrentLocation.options.selectedIndex;
	var CurrentLocation=document.AddEmpForm.CurrentLocation.options[CLocIndex].value;

	var JDY=document.AddEmpForm.JoiningDateYYYY.value;
	var JDM=document.AddEmpForm.JoiningDateMM.value;
	var JDD=document.AddEmpForm.JoiningDateDD.value;

	var curDate = new Date();

      var day = curDate.getDate();
	  var mon=curDate.getMonth();
	  var year=curDate.getYear();


	if(EmpName==""){
		alert("Employee name is mandatory");
		document.AddEmpForm.EMPName.focus();
		return false;
	}
	else{
		var ed=EmpName;
		var pattern = /^([a-zA-Z]{1,30})$/;
		if(!(pattern.test(ed))){
			alert("Please enter valid Name");
			document.AddEmpForm.EMPName.value="";
			document.AddEmpForm.EMPName.focus();
		return false;
		}
	}
	if(EMPNO==""){
		alert("Employee number is mandatory");
		document.AddEmpForm.EMPNO.focus();
		return false;
	}
	else{
	  	var ed=EMPNO;
		var pattern = /^([0-9]{1,6})$/;
		if(!(pattern.test(ed))){ 
			alert("Please enter numbers only");
			document.AddEmpForm.EMPNO.value="";
			document.AddEmpForm.EMPNO.focus();
	        return false;
		}

	}
	if(EmpEmailID==""){
		document.AddEmpForm.EmpEmailID.value="Name@Company.com";
	}
	else
	{
	  	var ed=EmpEmailID;
		var pattern = /^[a-zA-Z0-9\_\.]+\@[a-zA-Z\.]+\.([a-z]{2,4})$/;
		if(!(pattern.test(ed))) {
			alert("Please enter valid email ID");
			document.AddEmpForm.EmpEmailID.value="";
			document.AddEmpForm.EmpEmailID.focus();
			return false;
		}
	}
	if(CLocIndex==0||CurrentLocation=="NA"){
		alert("Current Location is mandatory");
		document.AddEmpForm.CurrentLocation.focus();
		return false;
	}
	if(EmpEmailID==""||PrimarySkillset==""||Remarks==""){
		alert("Empty fields are loaded with defaults");
	}
	if(JDY==""||JDM==""||JDD==""){
		alert("Feild(s) left empty...loaded with defaults");
		document.AddEmpForm.JoiningDateYYYY.value=year;
		document.AddEmpForm.JoiningDateMM.value=(mon+1);
		document.AddEmpForm.JoiningDateDD.value=day;
	}
	if(PrimarySkillset==""){
		document.AddEmpForm.PrimarySkillset.value="NA";
	}
	if(Remarks==""){
		document.AddEmpForm.Remarks.value="Added on "+new Date();
	}
	return true;
	}

	//-->
	</SCRIPT>
  </HEAD>
  <body Class=SC>
	<!-- To display Menu --Start -->
	<script language="JavaScript1.2" src="coolfunctions.js"></script>
	<script language="JavaScript1.2" src="coolmenus.js"></script>
	<!-- To display Menu --End --><br><br><br>
<%
int Auth = ((Integer)session.getAttribute("auth")).intValue();
if(Auth!=0){
	%><HR><H3 align=center>You are not authorized to access this page..</H3><HR><%
   return;
}
%>
  <FORM NAME="AddEmpForm" onSubmit="return check()" action="AddEmployee.jsp">
   <CENTER>

   <TABLE BORDER="0" CELLSPACING="2"  CELLPADDING="2" >
	   <TR class=row_title  ALIGN="center">
	   <TH COLSPAN="2"> Add new Employee </TH>
	   </TR>
	   <TR class=row_odd >
	   <TD>EMPName *</TD>
	   <TD><INPUT TYPE="TEXT" NAME="EMPName" SIZE="30" ></TD>
	   </TR>
	   <TR class=row_even >
	   <TD>EMPNO *</TD>
	   <TD><INPUT TYPE="TEXT" NAME="EMPNO" SIZE="30"></TD>
	   </TR>

	   <TR class=row_odd >
	   <TD>MailID *</TD>
	   <TD><INPUT TYPE="TEXT" NAME="EmpEmailID" SIZE="30"></TD>
	   </TR>

	   <TR class=row_even >
	   <TD>CurrentLocation*</TD>
	   <TD><select name="CurrentLocation">
				<option value="Bangalore">-----</option>
				<option value="Bangalore">Bangalore</option>	   
				<option value="Hyderabad">Hyderabad</option>
				<option value="Pune">Pune</option>
			</select></TD>
	   </TR>

	   <TR class=row_odd >
	   <TD>JoiningDate </TD>
	   <TD><INPUT TYPE="TEXT" NAME="JoiningDateYYYY"  SIZE="4" MAXLENGTH=4>-
   	   <INPUT TYPE="TEXT" NAME="JoiningDateMM"  SIZE="2" MAXLENGTH=2>-
   	   <INPUT TYPE="TEXT" NAME="JoiningDateDD"  SIZE="2" MAXLENGTH=2>(YYYY-MM-DD)</TD>
	   </TR>
		<TR class=row_even >
			<TD>Role</TD>
			<TD>
				<select name="Role">
					<option value="NA">-----</option>
					<option>General Manager</option>	   
					<option>Accounts Manager</option>
					<option>Sales Manager</option>	   
					<option>Sales Representative</option>
					<option>Accountant</option>	   
					<option>Accounts Assistant</option>
					<option>Clerk</option>	   
					<option>Receptionist</option>	   
					<option>Security</option>	   
				</select>
			</TD>

		</TR>
		<TR class=row_odd >
			<TD>Qualification</TD>
			<TD><INPUT TYPE="TEXT" NAME="PrimarySkillset"  SIZE="30"></TD>
		</TR>
		<TR class=row_even >
			<TD>Remarks</TD>
			<TD><INPUT TYPE="TEXT" NAME="Remarks"   SIZE="30"></TD>
		</TR>
		<TR class=row_odd  ALIGN="CENTER">
			<TD><INPUT TYPE="SUBMIT" VALUE="Add" ></TD>
			<TD><INPUT TYPE="RESET" VALUE="Reset"></TD>
		</TR>

   </TABLE>
   </CENTER>
   </FORM>
   </BODY> 
   </HTML>
