<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>E-Acquistion Add Staff Page</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<script language="javascript">
function validate()

{
  if(document.forms[0].empname.value=="")       /*for name*/
   {
     alert("The NAME cannot be left blank");
     document.forms[0].empname.focus();
      return false;
    }
   

if(document.forms[0].empname.value!="")       /*for   name*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].empname.value;
str_len=str.length;

for(var i=0;i<str_len;i++)
{
k=str.charCodeAt(i);
if( ((k>96)&&(k<123))||((k>64)&&(k<91))||(k==32)  )
{
  m=1;
}
else 
{
 m=2;
break;
}
}
if(m==2)
{
alert("Plz Fill the NAME with ONLY CHARACTERS");
document.forms[0].empname.focus();
return false;
 }
}
if(document.forms[0].jdate.value=="")       /*for Joining Date*/
   {
     alert("The joining date cannot be left blank");
     document.forms[0].jdate.focus();
      return false;
    }

if(document.forms[0].designation.value=="")       /*for Designation*/
   {
     alert("The designation cannot be left blank");
     document.forms[0].designation.focus();
      return false;
    }
	
if(document.forms[0].designation.value!="")       /*for   Designation*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].designation.value;
str_len=str.length;

for(var i=0;i<str_len;i++)
{
k=str.charCodeAt(i);
if( ((k>96)&&(k<123))||((k>64)&&(k<91))||(k==32)  )
{
  m=1;
}
else 
{
 m=2;
break;
}
}
if(m==2)
{
alert("Plz Fill the Designation with ONLY CHARACTERS");
document.forms[0].designation.focus();
return false;
 }
}

if(document.forms[0].salary.value=="")       /*for salary*/
   {
     alert("The salary cannot be left blank");
     document.forms[0].salary.focus();
      return false;
    }
	
if(document.forms[0].salary.value!="")       /*for salary*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].salary.value;
str_len=str.length;

for(var i=0;i<str_len;i++)
{
k=str.charCodeAt(i);
if( ((k>47)&&(k<58))||(k==32)  )
{
  m=1;
}
else 
{

 m=2;
break;
}
}
if(m==2)
{
alert("please fill the salary with ONLY NUMERIC");
document.forms[0].salary.focus();
return false;
 }
}
if(document.forms[0].accno.value=="")       /*for Account No*/
   {
     alert("The Account No cannot be left blank");
     document.forms[0].accno.focus();
      return false;
    }
	
if(document.forms[0].accno.value!="")       /*for Account No*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].accno.value;
str_len=str.length;

for(var i=0;i<str_len;i++)
{
k=str.charCodeAt(i);
if( ((k>47)&&(k<58))||(k==32)  )
{
  m=1;
}
else 
{

 m=2;
break;
}
}
if(m==2)
{
alert("please fill the account number with ONLY NUMERIC");
document.forms[0].accno.focus();
return false;
 }
}
if(document.forms[0].contactaddress.value=="")       /*for contactaddress*/
   {
     alert("The contact address cannot be left blank");
     document.forms[0].contactaddress.focus();
      return false;
    }

if(document.forms[0].phoneno.value=="")       /*for Phone No*/
   {
     alert("The phone No cannot be left blank");
     document.forms[0].phoneno.focus();
      return false;
    }
	
if(document.forms[0].phoneno.value!="")       /*for phone No*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].phoneno.value;
str_len=str.length;

for(var i=0;i<str_len;i++)
{
k=str.charCodeAt(i);
if( ((k>47)&&(k<58))||(k==32)  )
{
  m=1;
}
else 
{

 m=2;
break;
}
}
if(m==2)
{
alert("please fill the phone number with ONLY NUMERIC");
document.forms[0].phoneno.focus();
return false;
 }
}
	
}
</script>
  <script type="text/javascript" src="scripts/general.js"> </script>
   <script type="text/javascript" src="scripts/ts_picker.js"> </script>
<body>
<!-- start header -->
<div id="logo">
	<h1><jsp:include page="header.html"/></h1>
	</div>
<div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/> 
	</ul>
</div>
<!-- end header -->
<!-- start page -->
<div id="page">
  <div id="page-bg">
		<!-- start content -->
	<!-- end content -->
	<!-- start sidebar -->
<div id="latest-post">
<ul>
				
					<h2 align="center">AddStaff Form</h2>
				
				
				   <form action="AddStaffAction.jsp" method="post" name="staff" onSubmit="return validate()">

<table border="0" align="center">
 <!--   <tr>
    <td class="tiny style3">Employee ID </td>
    <td><input type="text" name="empid" class="textfield"/>
    </td>
  </tr>-->
  <tr>
    <td><span class="style3">Emp Name</span></td>
    <td><input type="text" name="empname" class="textfield"/>
    </td>
  </tr>
  <tr>
    <td><span class="style3">Joining Date </span></td>
    <td><input type="text" name="jdate" readonly="readonly" />
      <a href="javascript:show_calendar('document.staff.jdate', document.staff.jdate.value);"> <img src="images/cal.gif" alt="a" width="18" height="18" border="0"/></a> </td>
  </tr>
  <tr>
    <td class="style3">Designation</td>
    <td><input type="text" name="designation" class="textfield"/></td>
  </tr>
  <tr>
    <td class="style3">Salary</td>
    <td><input type="text" name="salary" class="textfield"/></td>
  </tr>

  <tr>
    <td class="style3">Account No</td>
    <td><input type="text" name="accno" class="textfield"/></td>
  </tr>
  <tr>
    <td class="style3">Contact Address</td>
    <td><input type="text" name="contactaddress" class="textfield"/></td>
  </tr>
  <tr>
    <td class="style3">Phone No</td>
    <td><input type="text" name="phoneno" class="textfield"/></td>
  </tr>
  
    <tr>
    <td height="11" colspan="2"></td>
    </tr>
  <tr>
    <td colspan="2"><div align="center">
      <input name="Input" type="submit" value="Add Staff" />
    </div></td>
  </tr>
</table>
</form>
				
				
</ul>
		</div>
    <div style="clear: both;">&nbsp;</div>
	</div>
</div>
<!-- end page -->
<div id="footer">
			<p id="legal">
				<a href="#">Privacy Policy</a> |
				<a href="#">Terms of Use</a>
			</p>
		</div>
</body>
</html>
