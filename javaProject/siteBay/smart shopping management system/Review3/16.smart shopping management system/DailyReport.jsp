<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.dae.dao.GenerateBillDAO"%>
<%@page import="com.dts.core.util.CoreHash"%>
<%@page import="com.dts.dae.model.GenerateBillBean"%>
<%@page import="java.util.*"%>
<%@page import="com.dts.core.util.DateWrapper"%>
<%
  GenerateBillDAO gbdao = new GenerateBillDAO();
  GenerateBillBean gbbean=new GenerateBillBean();
  String reportDate = request.getParameter("date");
  //  Date todaydate=new Date(); 
  System.out.println("-------------------- reportDate:"+reportDate);
  String date=(reportDate==null?DateWrapper.parseDate(new Date()):DateWrapper.parseDate(reportDate));
  gbbean.setDate(date);
  System.out.println("-------------------- date:"+date);
  CoreHash ch = gbdao.getDailyReport(gbbean);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Daily Reports Page</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<script type="text/javascript" src="scripts/general.js"> </script>
<script type="text/javascript" src="scripts/ts_picker.js"> </script>
<script language="JavaScript">
function onDateChanged()
{
		alert("hkh");
		var date=document.form1.date.value;
		alert(date);
		document.getElementById("form1").action ="DailyReport.jsp?date="+date;
		document.form1.submit();
}
</script>
<body>
<!-- start header -->
<div id="logo">
	<h1><jsp:include page="header.html"/></h1>
	</div>
<div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/> 
	</ul>
</div>
<!-- end header -->
<!-- start page -->
<div id="page">
  <div id="page-bg">
	<!-- 	<div id="latest-post">
		  <h1>Welcome to Our Website!</h1>
	  </div>  -->
		<!-- start content -->
	<!-- end content -->
	<!-- start sidebar -->
<div id="latest-post">
	  <ul>	
		<form id="form1" name="form1" method="post" action="GenerateBillAction.jsp">
            <fieldset>
        	<legend>Daily Report</legend>
            <table align="center" width="350" border="1">
            <tr>
    <td colspan="2"><input type="text" name="date" onblur="onDateChanged();"/>
      <a href="javascript:show_calendar('document.form1.date', document.form1.date.value);"> <img src="images/cal.gif" alt="a" width="18" height="18" border="0"/></a> 
      <input name="report" type="button" value="Show Report" onclick="onDateChanged()"/>
      </td>
  </tr>
            <tr>
            <th>CustomerName</th>
            <th>TotalBought</th>
            </tr>
           <%
            Enumeration enumeration = ch.elements();
	        while(enumeration.hasMoreElements()) 
		    {
		    	 gbbean = (GenerateBillBean)enumeration.nextElement();
		   	%>	
            <tr>
	   		<td><%=gbbean.getCustomername()%></td>
			<td><%=gbbean.getGrandtotal()%></td><%} %>
	        <tr><td colspan="4"><input name="print" type="button" value="Print Report" onclick="javascript:window.print();"/>
	        </td>
	        </tr>
           </table>
           </fieldset>
                  </form>
					<div align="center"></div>
	  </ul>
		</div>
    <div style="clear: both;">&nbsp;</div>
	</div>
  <div align="center"></div>
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
