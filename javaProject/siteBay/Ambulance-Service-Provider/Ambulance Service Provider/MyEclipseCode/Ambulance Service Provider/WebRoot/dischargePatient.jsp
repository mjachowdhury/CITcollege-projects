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
<script type="text/javascript" src="scripts/general.js"> </script>
<script type="text/javascript" src="scripts/ts_picker.js"> </script>
</head>
<body>
<%
 String temp=request.getParameter("id");	
%>

	<h1><jsp:include page="header.html"/></h1>
	

<div id="menu">
	<ul>
	<%

		if((session.getAttribute("role")).equals("admin"))
		{%>
			<jsp:include page="adminoptions.html"/>  
        <%}
		else
		{
			%>
				<jsp:include page="useroptions.jsp"/>  
			<%
		}%></ul>
</div>
<hr />
<div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Discharge Patient
      <div class="sub3">
          <form name="dischargePatient" id="form1" method="post" action="dischargePatientAction.jsp">
          
            <table width="245" border="0">
			<tr>
				<td>State Of Patient</td>
				<td>
				<select name="state">
				<option value="Recovered">Recovered</option>
				<option value="Died">Died</option>
				</select>
				</td>
			</tr>
			<tr>
			<td>Date&nbsp;of&nbsp;Discharge</td>
			<td><input type="text" name="dischargedate" readonly/>
			<a href="javascript:show_calendar('document.dischargePatient.dischargedate',document.dischargePatient.dischargedate.value);">
			<img src="images/cal.gif" width="18" height="18"/></a>
			</td>	
			</tr>
			<tr><td><input type="hidden" name="id" value="<%=temp %>"  /></td></tr>			
			<tr><td><input type="submit" value="Submit" /></td></tr>
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
