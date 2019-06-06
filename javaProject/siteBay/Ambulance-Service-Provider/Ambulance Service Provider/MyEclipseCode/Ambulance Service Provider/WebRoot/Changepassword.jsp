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
<body>

	<h1><jsp:include page="header.html"/></h1>
	

<div id="menu">
	<ul>
		<%
      if(request.getParameter("role").equals("admin"))
      {%>
     <jsp:include page="adminoptions.html"/>  
     <%}else{ %>
     <jsp:include page="useroptions.jsp"/>
     <% } %>  
	</ul>
</div>
<hr />
<div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Change Password
      <div class="sub3">
         
             
                  <form action="ChangepasswordAction.jsp" method="post" name="changepassword">
                    <table width="200" border="0" align="center">
                      <tr>
                        <td><div align="center" class="style25">
					  
					  <% if(request.getParameter("status")!=null)
					  {%>
					  <%=request.getParameter("status")%>
					  <%}%>
					  </div></td>
                      </tr>
                      <tr>
                        <td><table width="296" border="0" align="center">
                          <tr>
                            <td width="1" height="57" valign="top"></td>
                            
                            <td width="10"></td>
                          </tr>
                          <tr>
                            <td align="right"></td>
                            <td><table width="261" border="0" align="center">
                              <tr>
                                <td width="106" class="style23">User Name</td>
                                <td width="145"><label>
                                  <input type="text" name="username" emsg="Username is required"/>
                                </label></td>
                              </tr>
                              <tr>
                                <td class="style23">Old Password</td>
                                <td><label>
                                  <input type="password" name="oldpassword" emsg="Password is required"/>
                                </label></td>
                              </tr>
                              <tr>
                                <td class="style23">New Password </td>
                                <td><label>
                                  <input type="password" name="newpassword" emsg="Password is required"/>
                                </label></td>
                              </tr>
                              <tr>
                                <td colspan="2"><label>
                                    <div align="center">
                                      <input type="submit" name="Submit" value="Change"/>
                                      &nbsp;
                                      <input type="reset" name="Submit2" value="Reset"/>
                                    </div>
                                  </label></td>
                              </tr>
                            </table></td>
                            <td>&nbsp;</td>
                          </tr>
                         
                        </table></td>
                      </tr>
                    </table>
                    </form>			
                
        
</BODY>
</html>
  
   

      

