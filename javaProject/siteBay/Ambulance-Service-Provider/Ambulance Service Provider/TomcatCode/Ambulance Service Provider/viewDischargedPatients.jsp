<%@page import="java.util.ArrayList"%>
<%@page import="com.dts.em.dao.PatientDAO"%>
<%@page import="com.dts.core.util.DateWrapper"%>
<%@page import="com.dts.em.fun.HospitalizedStatus"%>
<%@page import="com.dts.es.dto.PatientDischargeDTO"%>
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
<script type="text/javascript" src="scripts/ts_picker.js"></script>
<script type="text/javascript">

function validatePage()
{
	if(document.patientreport.fdate.value == "")
	{	
		alert("select From date");
		return false;	
	}
	if(document.patientreport.todate.value=="")
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
				PatientDischargeDTO pddto =null;
				ArrayList al = null;
				ArrayList ald= null;
				if(request.getParameter("fdate")!=null && request.getParameter("todate")!=null)
				{
					  String fromdate =	DateWrapper.parseDate(request.getParameter("fdate"));
					  String todate =	DateWrapper.parseDate(request.getParameter("todate"));
					//  al = new PatientDAO().getPatientRePorts(fromdate,todate);	
					  ald = new HospitalizedStatus().getDischargeReports(fromdate,todate);
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
    <div class="sub4">Discharged Patient Report
      <div class="sub3">
          <form name="patientreport" id="form1" method="post" action="viewDischargedPatients.jsp" onsubmit="return validatePage()">
            <br />
            <table width="245" border="0">
              <tr><td>		
				From Date
				</td>
				<td>
				<input type="text" name="fdate" readonly/>
				<a href="javascript:show_calendar('document.patientreport.fdate',document.patientreport.fdate.value);">
				<img src="images/cal.gif" width="18" height="18" border="0" />
				</a>	
				</td></tr>
             	<tr><td>		
				To date
				</td>
				<td>
				<input type="text" name="todate" readonly/>
				<a href="javascript:show_calendar('document.patientreport.todate',document.patientreport.todate.value);">
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
              if(ald!=null && ald.size() > 0)
              {
              reports = reports+"<table border=1>"+
            "<tr>"+  
            "<th>Name of Patient</th>"+
            "<th>Discharge Date</th>"+
            "<th>State of patient</th>"+
            "</tr>"; 
              %><p align="right">Export to : <a href="ExportXLS">XLS</a> 
            <table border=1>
            <tr>  
            <th>Name of Patient</th>
            <th>Discharge Date</th>
            <th>State of patient</th>
            </tr>
            <%
            for(int i=0;i<ald.size();i++)
            {
            	pddto = (PatientDischargeDTO)ald.get(i);
            	reports =reports+"<tr>"+
            "<td>"+new PatientDAO().getFirstLastName(""+pddto.getPid())+"</td>"+
            "<td>"+pddto.getDischargeDate()+"</td>"+
	    	"<td>"+pddto.getState()+"</td>"+
	    	"</tr>";	
            %>
            <tr>
            <td><%=new PatientDAO().getFirstLastName(""+pddto.getPid())%></td>
            <td><%=pddto.getDischargeDate()%></td>
	    	<td><%=pddto.getState()%></td>
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
