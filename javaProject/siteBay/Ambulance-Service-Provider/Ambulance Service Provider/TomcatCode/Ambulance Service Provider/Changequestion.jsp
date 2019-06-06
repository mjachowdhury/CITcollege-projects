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
    <div class="sub4">Change Question
      <div class="sub3">
<form action="ChangequestionAction.jsp" method="post" name="changequestion">
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
                        <td><table width="296" border="0">
                          <tr>
                            <td width="1" height="57" valign="top"></td>
                            <td width="300"><div align="center" class="style24">C h a n g e &nbsp;Q u e s t i o n </div></td>
                            <td width="10"></td>
                          </tr>
                          <tr>
                            <td align="right"></td>
                            <td><table width="380" border="0" align="center">
                              <tr>
                                <td width="101" class="style23">Login Name </td>
                                <td width="269"><label>
                                  <input type="text" name="username"/>
                                </label></td>
                              </tr>
                              <tr>
                                <td class="style23">Password </td>
                                <td><label>
                                  <input type="password" name="password"/>
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
                              <!--  <input type="checkbox" name="ch" value="1" onClick="check1()"/>
                                  Own Question </label></td>
                              </tr>
                              <tr>
                                <td class="style23">Own Question </td>
                                <script>
        
                            </script>
                                <td><label>
                                  <input type="text" name="ownquest" disabled/>
                                </label></td>-->
                              </tr>
                              <tr>
                                <td height="38" class="style23">Secret Answer </td>
                                <td><input name="sanswer" type="text"></td>
                              </tr>
                              <tr>
                                <td colspan="2"><div align="center">
                                  <input name="Input" type="submit" value="Change"/>
                                </div>
                                    <div align="center"></div></td>
                              </tr>
                            </table></td>
                            <td>&nbsp;</td>
                          </tr>
                          
                        </table></td>
                      </tr>
                    </table>
                    </form>
</BODY></HTML>
  
   

      

