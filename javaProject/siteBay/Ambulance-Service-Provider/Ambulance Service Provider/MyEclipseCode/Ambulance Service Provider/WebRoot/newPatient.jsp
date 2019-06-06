<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
<!--
.style1 {color: #000000}
.style3 {color: #000000; font-weight: bold; }
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
-->
</style>
</head>
<script language="javascript">
function validatePage()
{
    if(document.newpatientaction.firstname.value=="")
    {
        alert("Enter the patient First name");
    	return false;
    }
    if(document.newpatientaction.lastname.value=="")
    {
	    alert("Enter the patient Last name");
	    return false;
    }
    if(document.newpatientaction.dob.value=="")
    {
	    alert("Enter the patient Date of birth");
	    return false;
    }
    if(document.newpatientaction.location.value=="")
    {
	    alert("Enter the patient location");
	    return false;
    }
    if(document.newpatientaction.city.value=="")
    {
	    alert("Enter the patient City");
	    return false;
    }
    if(document.newpatientaction.state.value=="")
    {
	    alert("Enter the patient State");
	    return false;
    }
    /* if(document.newpatientaction.height.value=="")
    {
	    alert("Enter the patient height");
	    return false;
    }
    if(document.newpatientaction.weight.value=="")
    {
	    alert("Enter the patient weight");
	    return false;
    }
    */
    if(document.newpatientaction.jdate.value=="")
    {
	    alert("Select the patient joining date");
	    return false;
    } 
    return true;
}
</script>
<body>
<script type="text/javascript" src="scripts/general.js"> </script>
<script type="text/javascript" src="scripts/ts_picker.js"></script>

	<h1><jsp:include page="header.html"/></h1>
	

<div id="menu">
	<ul>
		<jsp:include page="useroptions.jsp"/>  
	</ul>
</div>
<hr />
<div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Patient Registration Form
      <div class="sub3">
		<form name="newpatientaction" action="newPatientAction.jsp" method=post onsubmit="return validatePage()" >
			<table>
			<tr> 
			<td>First Name</td><td><input type="text" maxlength="25" size="20" name="firstname"/></td>
			</tr>
			<tr>
			<td>Last Name</td><td><input type="text" maxlength="25" size="20" name="lastname"/></td>
			</tr>	
			<tr>
			<td>Date of Birth</td><td> <input type="text" name="dob" readonly/>
			<a href="javascript:show_calendar('document.newpatientaction.dob',document.newpatientaction.dob.value);">
			<img src="images/cal.gif" width="18" height="18" border="0"/></a>
			</td>	
			</tr>
			<TR><td height="20">Location</td>
			<td height="20">
			<input type="text" maxlength="25" size="20" name="location"/>
			</td>
			</TR>
			<tr>
				<td>City</td>
				<td><input type="text" maxlength="25" size="20" name="city"/></td>
			</tr>
			<tr><td>State</td><td>
			<input type="text" maxlength="25" size="20" name="state"/>
			</td></tr>
				
				<tr>
				<td>Address</td>
				<td><textarea  cols="20" rows="2" name="address"></textarea>
				</td>
				</tr>
				<tr>
				<td>Phone Number</td>
				<td><input type="text" name="phone" value=""/>
				</td>
				</tr>
				<tr>
				<td>Patient Height</td>
				<td><input type="text" name="height" value=""/>
				</td>
				</tr>
				<tr>
				<td>Patient Weight</td>
				<td><input type="text" name="weight" value=""/>
				</td>
				</tr>
				<tr><td>		
				Date Of joining
				</td>
				<td>
				<input type="text" name="jdate" readonly/>
				<a href="javascript:show_calendar('document.newpatientaction.jdate',document.newpatientaction.jdate.value);">
				<img src="images/cal.gif" width="18" height="18" border="0" />
				</a>	
				</td></tr>	
				<tr>
				<td>Type of Communication</td>
				<td>
				<select name="type">
				<option value="Phone">Phone</option>
				<option value="SMS">SMS</option>
			<!--	<option value="Email">Email</opiton> -->
				</select>
				</td>
				</tr>
				
			<tr>
			<td><input type="submit" value="Submit"/></td>
			<td>
			<input type="reset" value="Clear"/>
			<td>
			</tr>
			</table>
		</form>
</div>
    <div>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  </div>
  <p></p>
		<p>&nbsp;</p>
  </div>
</div>
<div id="recent-posts">
	<h2>&nbsp;</h2>
</div>
<div id="footer">

</div>
</body>
</html>
