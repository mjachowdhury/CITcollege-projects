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
    if(document.register.fname.value=="")
    {
        alert("Enter the First name");
    	return false;
    }
    if(document.register.lname.value=="")
    {
	    alert("Enter the Last name");
	    return false;
    }
    
    if(document.register.bdate.value=="")
    {
	    alert("Select the  birth date");
	    return false;
    } 
    if(document.register.loginname.value=="")
    {
	    alert("Enter the Login name");
	    return false;
    }
    if(document.register.password.value=="")
    {
	    alert("Enter the password");
	    return false;
    }
    if(document.register.sanswer.value=="")
    {
	    alert("Enter the Answer");
	    return false;
    }
    return true;
}
</script>
<script type="text/javascript" src="scripts/general.js"> </script>
<script type="text/javascript" src="scripts/ts_picker.js"> </script>
<body>

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
    <div class="sub4">Register Form
      <div class="sub3">
          <form name="register" id="form1" method="post" action="RegisterAction.jsp" onsubmit="return validatePage()">
          <table width="481" border="0" align="center">
                        <tr>
                          <td width="7" height="57" valign="top">&nbsp;</td>
                          
                          <td width="10">&nbsp;</td>
                        </tr>
                        <tr>
                          <td align="right">&nbsp;</td>
                          <td><table width="407" border="0" align="center">
                              <tr>
                                <td width="128"><span class="style17">First Name </span></td>
                                <td width="269"><label>
                                  <input type="text" name="fname"/>
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style17">Last Name </span></td>
                                <td><label>
                                  <input type="text" name="lname"/>
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style17">Birth Date </span></td>
                                <td><label>
                                  <input type="text" name="bdate" readonly><a href="javascript:show_calendar('document.register.bdate', document.register.bdate.value);">
								<img src="images/cal.gif" width="18" height="18" border="0"/></a>
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style17">City</span></td>
                                <td><label>
                                  <select name="city">
                                    <option>Hyderabad</option>
                                    <option>Mumbai</option>
                                  </select>
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style17">State</span></td>
                                <td><label>
                                  <select name="state">
                                    <option>Andhra pradesh</option>
                                    <option>Maharashtra</option>
                                  </select>
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style17">Country</span></td>
                                <td><label>
                                  <select name="country">
                                    <option>India</option>
                                  </select>
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style17">Login Name </span></td>
                                <td><label>
                                  <input type="text" name="loginname"/>
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style17">Password</span></td>
                                <td><label>
                                  <input name="password" type="password" id="password"/>
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style17">
                                  <label>Secret Question</label>
                                </span></td>
                                <td><select name="squest">
                                    <option value="1">What is your favorite pastime?</option>
                                    <option value="2">Who was your childhood hero?</option>
                                    <option value="3">What was the name of your first school?</option>
                                    <option value="4">Where did you meet your spouse?</option>
                                    <option value="5">What is your favorite sports team?</option>
                                    <option value="6">What is your father's middle name?</option>
                                    <option value="7">What was your high school mascot?</option>
                                    <option value="8">What make was your first car or bike?</option>
                                    <option value="9">What is your pet's name?</option>
                                </select></td>
                              </tr>
                              <tr>
                                <td colspan="2"><span class="style17"> </span><span class="style18">
                                  <label>
                                  <input type="checkbox" name="ch" value="1" onclick="check()"/>
                                  <span class="style20">Own Question</span> </label>
                                </span><span class="style17"> </span></td>
                              </tr>
                              <tr>
                                <td><span class="style17">Own Question </span></td>
                                <td><label>
                                  <input type="text" name="ownquest" disabled/>
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style17">Secret Answer </span></td>
                                <td><input name="sanswer" type="text"/></td>
                              </tr>
                              <tr>
                                <td colspan="2"><div align="center">
                                    <input name="Input" type="submit" value="Register"/>
                                </div></td>
                              </tr>
                          </table>
                     
      
    
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  
  <p></p>
		<p>&nbsp;</p>
  <div>
	
</div>
<div id="recent-posts">
	<h2>&nbsp;</h2>

  
</div>


<div id="footer">
	
</div>
</body>
</html>
