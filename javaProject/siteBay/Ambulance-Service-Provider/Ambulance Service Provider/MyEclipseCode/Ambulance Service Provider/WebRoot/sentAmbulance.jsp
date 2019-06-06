<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.em.dao.* " %>
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
<% 
String temp = request.getParameter("id");
 
 %>

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
    <div class="sub4">Ambulance Status
      <div class="sub3">
          <form id="form1" method="post" action="">
            
            <table width="245" border="0">
              <tr>
              <td>
              <h3><b>Ambulance Already Sent  for the Patient :<%= new PatientDAO().getFirstLastName(temp) %></b></h3>
              </td>
              </tr>
              <tr><td><input type="button" value ="Release Ambulance" onclick="javascript:location.href='releaseAmbulance.jsp?id=<%=temp%>'"/></td>
              <td><input type="button" value ="Back" onclick="history.go(-1)" /></td></tr>
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
