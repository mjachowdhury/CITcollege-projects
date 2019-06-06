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
    if(document.recoverpassword.username.value=="")
    {
        alert("Enter the User name");
    	return false;
    }
    if(document.recoverpassword.sanswer.value=="")
    {
	    alert("Enter the answer name");
	    return false;
    }
 	return true;   
 }   
   </script> 
<body>

	<h1><jsp:include page="header.html"/></h1>
	

<div id="menu">
	<ul>
		<jsp:include page="generaloptions.html"/>  
	</ul>
</div>
<hr />
<div id="latest-post" class="post">
	<br/>
  <h1 align  = 'center' class="title">Welcome to recover your password</h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Recover Password
      <div class="sub3">
          <form name="recoverpassword" id="form1" method="post" action="RecoverPasswordAction.jsp" onsubmit="return validatePage()" >
<table width="388" border="0" align="center">
                              <tr>
                                <td class="style23">Login Name </td>
                                <td><label>
                                  <input type="text" name="username"/>
                                </label></td>
                              </tr>
                              <tr>
                                <td class="style23"><label>Secret Question</label></td>
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
                              <tr class="style23">
                                <td colspan="2"><label>
                                  <input type="checkbox" name="ch" value="1" onClick="check()"/>
                                  Own Question </label></td>
                              </tr>
                              <tr>
                                <td class="style23">Own Question </td>
                                <td><label>
                                  <input type="text" name="ownquest"/>
                                </label></td>
                              </tr>
                              <tr>
                                <td class="style23">Secret Answer </td>
                                <td><input name="sanswer" type="text"></td>
                              </tr>	
                              <tr>
                                <td colspan="2"><div align="center">
                                  <input name="Input" type="submit" value="Recover"/>
                                  &nbsp;
                                  <input name="Input" type="reset" value="Clear"/>
                                </div>
                                    <div align="center"></div></td>
                              </tr>
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
