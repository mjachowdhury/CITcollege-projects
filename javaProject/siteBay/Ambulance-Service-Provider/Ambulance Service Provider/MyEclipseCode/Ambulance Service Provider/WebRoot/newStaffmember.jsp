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
    if(document.newstaffform.firstname.value=="")
    {
        alert("Enter the First name");
    	return false;
    }
    if(document.newstaffform.lastname.value=="")
    {
	    alert("Enter the Last name");
	    return false;
    }
    
    if(document.newstaffform.jdate.value=="")
    {
	    alert("Select the patient joining date");
	    return false;
    } 
    return true;
}
</script>
<body>
<script type="text/javascript" src="scripts/general.js"> </script>
<script type="text/javascript" src="scripts/ts_picker.js"> </script>

	<h1><jsp:include page="header.html"/></h1>
	

<div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/>  
	</ul>
</div>
<hr />
<div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">New Staff member Form
      <div class="sub3">
          <form name="newstaffform" id="form1" method="post" action="newStaffAction.jsp" onsubmit="return validatePage()">
            
            <table width="245" border="0">
        	<tr>
        	<td>First Name</td>
        	<td><input type="text" name="firstname"></input></td>
        	</tr>
        	<tr>
        	<td>Last Name</td>
        	<td><input type="text" name="lastname"></input></td>
        	</tr>
        	<tr>
        	<td>Role of the Staff Member</td>
        	<td><select name="stafftype">
        	<option value="Doctor">Doctor</option>
        	<option value="Helper">Helper</option>
        	<option value="Driver">Driver</option>
        	</select></td>
        	</tr><tr>
        	<td>Phone</td>
        	<td><input type="text" name="phone"></input></td>
        	</tr><tr>
        	<td>Email Id</td>
        	<td><input type="text" name="email"></input></td>
        	</tr>
        	<tr>
        	<td>Location</td>
        	<td><input type="text" name="location"></input></td>
        	</tr>
        	<tr>
        	<td>Address</td>
        	<td><textarea name="address"></textarea></td>
        	</tr>
        	<tr>
        	<td>Working Hours</td>
        	<td><input type="text" name="workinghours"></input></td>
        	</tr>
        	<tr>
			<td>Date of joining</td>
			<td>
				<input type="text" name="jdate" readonly/>
				<a href="javascript:show_calendar('document.newstaffform.jdate',document.newstaffform.jdate.value);">
				<img src="images/cal.gif" width="18" height="18" border="0"/></a>
			</td>
        	</tr>
        	<tr><td><input type="submit" value="Submit"/></td></tr>	      
            </table>
          </form>
      </div>
    </div>
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
