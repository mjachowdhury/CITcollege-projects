<%@page import="java.util.*" %>
<%@page import="com.dts.em.fun.*" %>
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
<script language="JavaScript" type="text/JavaScript">

function valedatePage()
{
	
    if(document.ambulanceform.ano.value==-1)
    {
       alert("select Ambulance No");
       return false;
    }
    if(document.ambulanceform.adrivers.value==-1)
    {
       alert("select Driver ");
       return false;
    }
    if(document.ambulanceform.ahelpers.value==-1)
    {
       alert("Select Helper");	
       return false;
    }
    if(document.ambulanceform.adoctors.value ==-1)
    {
       alert("Select Doctor");	
       return false;
    }
    return true;
}


</script>
</head>
<body>
<%

String temp = request.getParameter("id");
AmbulanceStatus as=new AmbulanceStatus();
ArrayList ambulanceList = as.availableAmbulances();
ArrayList driversList = as.availableDrivers(); 
ArrayList helpersList = as.availableHelpers();
ArrayList doctorsList = as.availableDoctors(); 
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
    <div class="sub4">Ambulance Form
      <div class="sub3">
          <form name="ambulanceform" id="form1" method="post" action="sendingambulanceAction.jsp" onsubmit="return valedatePage()">
            <table width="245" border="0">
              <tr>
              <td>Available Ambulances</td>
              <td><select name="ano">
              <%
              if(ambulanceList!=null && ambulanceList.size()>0)
              {
                for(int i=0;i<ambulanceList.size();i=i+2)
                {
              %>
              <option value=<%=ambulanceList.get(i).toString() %>><%=ambulanceList.get(i+1).toString() %></option>
              <%}
              }else{
              %>
              <option value="-1">No Ambulances</option>
              <%} %>
              </select>
              </td>
              </tr>
              
              <tr>
              <td>Available Drivers</td>
              <td><select name="adrivers">
              <%
              if(driversList!=null && driversList.size()>0)
              {
                for(int i=0;i<driversList.size();i=i+2)
                {
              %>
              <option value=<%=driversList.get(i).toString() %>><%=driversList.get(i+1).toString() %></option>
              <%}
              }else{
              %>
              <option value="-1">No Drivers</option>
              <%} %>
              </select>
              </td>
              </tr>
              
              
              <tr>
              <td>Available Helpers</td>
              <td><select name="ahelpers">
              <%
              if(helpersList!=null && helpersList.size()>0)
              {
                for(int i=0;i<helpersList.size();i=i+2)
                {
              %>
              <option value=<%=helpersList.get(i).toString() %>><%=helpersList.get(i+1).toString() %></option>
              <%}
              }else{
              %>
              <option value="-1">No Helpers</option>
              <%} %>
              </select>
              </td>
              </tr>
               
               <tr>
              <td>Available Doctors</td>
              <td><select name="adoctors">
              <%
              if(doctorsList!=null && doctorsList.size()>0)
              {
                for(int i=0;i<doctorsList.size();i=i+2)
                {
              %>
              <option value=<%=doctorsList.get(i).toString() %>><%=doctorsList.get(i+1).toString() %></option>
              <%}
              }else{
              %>
              <option value="-1">No Doctors</option>
              <%} %>
              </select>
              </td>
              </tr>
              <tr><td><input type="hidden" name="pid" value=<%=temp%> /></td></tr>
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
