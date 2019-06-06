
<%@page import="com.dts.es.dto.PatientDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dts.em.dao.PatientDAO"%>
<%@page import="com.dts.core.util.DateWrapper"%>
<%@page import="com.dts.em.dao.StaffDAO"%>
<%@page import="com.dts.es.dto.StaffDTO"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

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
<script type="text/javascript" src="scripts/ts_picker.js"></script>
<script type="text/javascript">

function validatePage()
{
	if(document.staffreport.fdate.value == "")
	{	
		alert("select From date");
		return false;	
	}
	if(document.staffreport.todate.value=="")
	{	
		alert("select TO date");
		return false;	
	}
	return true; 	
}
 
</script>
</head>
<body>
<%
				String temp ="";
				StaffDTO sdto =null;
				ArrayList al = null;
				if(request.getParameter("fdate")!=null && request.getParameter("todate")!=null)
				{
					  String fromdate =	DateWrapper.parseDate(request.getParameter("fdate"));
					  String todate =	DateWrapper.parseDate(request.getParameter("todate"));
					  al = new StaffDAO().getStaffRePorts(fromdate,todate);	
				}
 %> 

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
    <div class="sub4">Staff Report
      <div class="sub3">
          <form name="staffreport" id="form1" method="post" action="viewStaffReport.jsp" onsubmit="return validatePage()">
            <br />
            <table width="245" border="0">
              <tr><td>		
				From Date
				</td>
				<td>
				<input type="text" name="fdate" readonly/>
				<a href="javascript:show_calendar('document.staffreport.fdate',document.staffreport.fdate.value);">
				<img src="images/cal.gif" width="18" height="18" border="0" />
				</a>	
				</td></tr>
             	<tr><td>		
				To date
				</td>
				<td>
				<input type="text" name="todate" readonly/>
				<a href="javascript:show_calendar('document.staffreport.todate',document.staffreport.todate.value);">
				<img src="images/cal.gif" width="18" height="18" border="0" />
				</a>	
				</td></tr>
              	<tr>
                <td height="45" colspan="2"><div align="center">
                    <input type="submit" name="button" id="button" value="Generate Report" />
                </div></td>
              </tr>
              </table>
              <%String reports ="";
              if(request.getParameter("fdate")!=null && request.getParameter("todate")!=null)
              {
              if(al!=null && al.size() > 0){
               reports = reports+"<table width='245' border='1'>"+
		    "<tr>"+
            "<th>First Name</th>"+
            "<th>Last Name</th>"+
            "<th>Role of Staff Member</th>"+
            "<th>Phone</th>"+
            "<th>Email</th>"+
            "<th>Location</th>"+
            "<th>Address</th>"+
            "<th>Working Hours</th>"+
            "<th>Joining Date</th>"+
            "</tr>";
               %>
              <p align="right">Export to : <a href="ExportXLS">XLS</a> | PDF
              
            <table border=1>
           <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Role of Staff Member</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Location</th>
            <th>Address</th>
            <th>Working Hours</th>
            <th>Joining Date</th>
             </tr>
            <%
            for(int i=0;i<al.size();i++)
            {
            	sdto = (StaffDTO)al.get(i);
            	reports = reports +"<tr>"+
	   		"<td>"+sdto.getFirstname()+"</td>"+
	    	"<td>"+sdto.getLastname()+"</td>"+
	    	"<td>"+sdto.getStaffRole()+"</td>"+
	    	"<td>"+sdto.getPhone()+"</td>"+
	    	"<td>"+sdto.getEmail()+"</td>"+
	    	"<td>"+sdto.getLocation()+"</td>"+
	    	"<td>"+sdto.getAddress()+"</td>"+
	    	"<td>"+sdto.getWorkinghours()+"</td>"+
	    	"<td>"+sdto.getJdate1()+"</td>"+
	    	"</tr>";	
            %>
            <tr>
	   		<td><%=sdto.getFirstname()%></td>
	    	<td><%=sdto.getLastname()%></td>
	    	<td><%=sdto.getStaffRole()%></td>
	    	<td><%=sdto.getPhone() %></td>
	    	<td><%=sdto.getEmail() %></td>
	    	<td><%=sdto.getLocation()%></td>
	    	<td><%=sdto.getAddress()%></td>
	    	<td><%=sdto.getWorkinghours() %></td>
	    	<td><%=sdto.getJdate1() %></td>
	    	
	    	</tr>
            <% 	
            }
            session.setAttribute("Report", reports);	
            %>  
              <%}else { %>
              	<tr><td colspan=2>No Records Found</td></tr>
              <%}} %>
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
