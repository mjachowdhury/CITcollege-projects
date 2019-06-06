<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>E-Acquistion</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<script>
function validate()
{
if(document.forms[0].fname.value=="")       /*for First name*/
{
alert("The First Name cannot be left blank");
document.forms[0].fname.focus();
return false;
}

if(document.forms[0].fname.value!="")       /*for first name*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].fname.value;
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
alert("please fill the First Name with ONLY CHARACTERS");
document.forms[0].fname.focus();
return false;
 }
}

if(document.forms[0].lname.value=="")       /*for Last name*/
{
alert("The Last Name cannot be left blank");
document.forms[0].lname.focus();
return false;
}

if(document.forms[0].lname.value!="")       /*for last name*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].lname.value;
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
alert("please fill the last Name with ONLY CHARACTERS");
document.forms[0].lname.focus();
return false;
 }
}
if(document.forms[0].bdate.value=="")         /*for Birth Date*/
{
alert("the birth date cannot be left blank");
document.forms[0].bdate.focus();
return false;
}
if(document.forms[0].hno.value=="")     /*for House Number*/
{
alert("the House number cannot be left blank");
document.forms[0].hno.focus();
return false;
}

if(document.forms[0].hno.value!="")       /*for  House Number*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].hno.value;
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
alert("please fill the house number with ONLY NUMERIC");
document.forms[0].hno.focus();
return false;
 }
}

if(document.forms[0].street.value=="")     /*for Street*/
{
alert("the street cannot be left blank");
document.forms[0].street.focus();
return false;
}
if(document.forms[0].pincode.value=="")        /*for pincode*/
{
alert("the pincode  cannot be left blank");
document.forms[0].pincode.focus();
return false;
}
if(document.forms[0].pincode.value!="")       /*for  pincode*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].pincode.value;
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
alert("please fill the pincode with ONLY NUMERIC");
document.forms[0].pincode.focus();
return false;
 }
}
if(document.forms[0].phoneno.value=="")       /*for Contact number*/
{
alert("the Contact number cannot be left blank");
document.forms[0].phoneno.focus();
return false;
}
if(document.forms[0].phoneno.value!="")       /*for  Contact number*/
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
alert("please fill the Contact number with ONLY NUMERIC");
document.forms[0].phoneno.focus();
return false;
 }
}
if(document.forms[0].email.value=="")       /*for Email id*/
{
alert("the Email id cannot be left blank");
document.forms[0].email.focus();
return false;
}

/* For Email Id Vlidation*/

var email=document.getElementById("email").value;
var emailpattern=/^[a-zA-Z0-9._-]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$/;
if(emailpattern.test(email))
	{
	// alert("Email Id:"+email);
	 return true;
	}
else
	{
	 alert("Email Id is not valid");
	 return false;
	
	}
/* For Login Name*/
if(document.forms[0].loginname.value=="")       /*for Login Name*/
{
alert("The Login Name cannot be left blank");
document.forms[0].loginname.focus();
return false;
}

if(document.forms[0].loginname.value!="")       /*for Login Name*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].loginname.value;
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
alert("please fill the Login Name with ONLY CHARACTERS");
document.forms[0].loginname.focus();
return false;
 }
}
if(document.forms[0].password.value=="")       /*for Password*/
{
alert("The password cannot be left blank");
document.forms[0].password.focus();
return false;
}

if(document.forms[0].sanswer.value=="")       /*for secret answer*/
{
alert("The Secret answer cannot be left blank");
document.forms[0].sanswer.focus();
return false;
}
if(document.forms[0].creditcardno.value=="")       /*for Credit number*/
{
alert("the Credit card number cannot be left blank");
document.forms[0].creditcardno.focus();
return false;
}
if(document.forms[0].creditcardno.value!="")       /*for  Credit number*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].creditcardno.value;
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
alert("please fill the Credit card number with ONLY NUMERIC");
document.forms[0].creditcardno.focus();
return false;
 }
}

return true;

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
		<jsp:include page="generaloptions.html"/> 
	</ul>
</div>
<!-- end header -->
<!-- start page -->
<div id="page">
  <div id="page-bg">
		<!--<div id="latest-post">
		  <h1>Welcome to Our Website!</h1>
	  </div>-->
		<!-- start content -->
	<!-- end content -->
	<!-- start sidebar -->
<div id="latest-post">
<ul>
				
					<h2 align="center">Registration</h2>
				
				
				 <form action="RegisterAction.jsp" method="post" name="register" onSubmit="return validate()">

<table border="0" align="center">
  <tr>
    <td class="tiny style3">First Name </td>
    <td><input type="text" name="fname" class="textfield"/>
    </td>
  </tr>
  <tr>
    <td><span class="style3">Last Name </span></td>
    <td><input type="text" name="lname" class="textfield"/>
    </td>
  </tr>
  <tr>
    <td><span class="style3">Birth Date </span></td>
    <td><input type="text" name="bdate" readonly="readonly" />
      <a href="javascript:show_calendar('document.register.bdate', document.register.bdate.value);"> <img src="images/cal.gif" alt="a" width="18" height="18" border="0"/></a> </td>
  </tr>
  <tr>
    <td class="style3">House No</td>
    <td><input type="text" name="hno" class="textfield"/></td>
  </tr>
  <tr>
    <td class="style3">Street</td>
    <td><input type="text" name="street" class="textfield"/></td>
  </tr>
  <tr>
    <td><span class="style3">City</span></td>
    <td><select name="city">
      <option>Hyderabad</option>
      <option>Mumbai</option>
	   <option>Banglore</option>
	    <option>Chenai</option>
		 <option>Simla</option>
		  <option>Delhi</option>
    </select>
    </td>
  </tr>
  <tr>
    <td><span class="style3">State</span></td>
    <td><select name="state">
      <option>Andhra pradesh</option>
      <option>Maharashtra</option>
      <option>Karnatakha</option>
      <option>tamilanadu</option>
      <option>utharapradesh</option>
      <option>Delhi</option>
    </select>
    </td>
  </tr>
  <tr>
    <td><span class="style3">Country</span></td>
    <td><select name="country">
      <option>India</option>
      <option>China</option>
       <option>U.S</option>
     <option>U.K</option>
    </select>
    </td>
  </tr>
  <tr>
    <td class="style3">Pincode</td>
    <td><input type="text" name="pincode" class="textfield"/></td>
  </tr>
  <tr>
    <td class="style3">Contact No</td>
    <td><input type="text" name="phoneno" class="textfield"/></td>
  </tr>
  <tr>
    <td class="style3">Email</td>
    <td><input type="text" name="email" class="textfield" /></td>
  </tr>
  <tr>
    <td><span class="style3">Login Name</span></td>
    <td><input type="text" name="loginname" />
    </td>
  </tr>
  <tr>
    <td><span class="style3">Password</span></td>
    <td><input name="password" type="password" id="password" />
    </td>
  </tr>
  <tr>
    <td><span class="style3"> Secret Question </span></td>
    <td><select name="squest">
      <option value="1">What is your favorite pastime?</option>
      <option value="2">Who your childhood hero?</option>
      <option value="3">What is the name of your first school?</option>
      <option value="4">Where did you meet your spouse?</option>
      <option value="5">What is your favorite sports team?</option>
      <option value="6">What is your father's middle name?</option>
      <option value="7">What was your high school mascot?</option>
      <option value="8">What make was your first car or bike?</option>
      <option value="9">What is your pet's name?</option>
    </select></td>
  </tr>
  <tr>
    <td><span class="style3">Secret Answer</span></td>
    <td><input name="sanswer" type="text" /></td>
  </tr>
  <tr>
    <td height="11" >Creditcard Number</td>
    <td><input name="creditcardno" type="text" /></td>
  </tr>
    <tr>
    <td height="11" colspan="2"></td>
    </tr>
  <tr>
    <td colspan="2"><div align="center">
      <input name="Input" type="submit" value="Register" onClick="return validate();"/>
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
