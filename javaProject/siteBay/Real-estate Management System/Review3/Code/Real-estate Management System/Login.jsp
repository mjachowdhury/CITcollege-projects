
<HTML>

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*" %>
<head>
<SCRIPT LANGUAGE="JavaScript">
<!--
history.go(+1);

//-->
</SCRIPT>
	<LINK href="styles.css" type="text/css" rel="stylesheet">

</head>
<BODY CLASS=SC onload="document.LOGIN.uid.focus();">
<FONT FACE="monotype corsiva" size=6 color="#ff8000">WelCome In OnlinePropertySale.com</FONT>
<center>
<BR><BR>
<FONT FACE="Century Gothic">


<!--Declaration of varaibles-->

<%! String errormsg ;%>
<%! String disluserid ;%>
<%! String dislpwd ;%>

    

<% 
   /*Retreiving user id and password*/

    disluserid = request.getParameter("uid");
    if(disluserid == null)
    disluserid = "";
    dislpwd = request.getParameter("pwd");
    if(dislpwd == null)
    dislpwd = "";

%>

<!--Retrieve the error from request and display on screen-->

<%  errormsg = request.getParameter("error") ;
    //System.out.println("errormsg = "+errormsg);
    if (errormsg == null)
    {
        //System.out.println("Error messages was null so clearing it..");
        errormsg = " ";
    }
    if(errormsg.equals("blankfields"))
    {
      %>
       <FONT COLOR="Red">
       Both the fields are Mandatory.Please fill up both fields.<BR><BR>
      <%
    }  
    if(errormsg.equals("invalid"))
    {
      %>
        <FONT COLOR="Red">
        Invalid Username and/or Password. Please re-enter. <BR><BR>
        <%
    } 
%>

<%-- ***** LOGIN SCREEN ***** --%>


<FORM NAME="LOGIN" ACTION="Validate.jsp" METHOD="post" >

<table align="center" background="Images/i11.jpg">
<tr>
<td align="cercen"><img src="Images/i1.jpg" width=600 height=150></img></td>
</tr>
<tr>
<td align="center"> 
<FONT COLOR="#ff0080" size=4>
<b>Enter your userid and password to login</b></FONT>
&nbsp;&nbsp;&nbsp;&nbsp;
<table align="center" border=5 width=200 height=100>
<tr>
<td> <FONT COLOR="blue" size=3><b>Userid :</b></FONT>  </td>
<td><input TYPE=text id = "usr" name=uid size="8" maxlength="8"> </td></tr>
<tr><td><FONT COLOR="blue" size=3><b>Password :</b></FONT></td><td> <input TYPE=password name=pwd size="8"  maxlength="8"></td></tr>
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<BR>
<tr>
<td><INPUT TYPE=submit name=submit value="Sign-in"></td>
<td><INPUT TYPE=reset name=resett value="Reset" ></td>
</tr> 

</table>
</td></tr>
</table>
<br><br><br>
<marquee><FONT COLOR="red" size=4><b>Welcome in BackFortyDense.com Firm search for the required data easily and processes at a real estate agency perform various operations using the interfaces provided by the system .........</b></FONT></marquee>
</BODY>
</HTML>




