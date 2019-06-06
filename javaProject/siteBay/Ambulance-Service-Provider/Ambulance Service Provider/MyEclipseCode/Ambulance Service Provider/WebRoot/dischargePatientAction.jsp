<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.es.dto.*" %>
<%@page import="com.dts.em.fun.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

<%
  PatientDischargeDTO pddto = new PatientDischargeDTO();
   
  pddto.setPid(Integer.parseInt(request.getParameter("id")));
  pddto.setState(request.getParameter("state"));
  pddto.setDischargeDate(request.getParameter("dischargedate"));
  
  boolean flag = new HospitalizedStatus().registerDischargeInfo(pddto);


 String pathstring = "";

		if(((String)session.getAttribute("role")).equals("admin"))
          pathstring="adminhome.jsp";
		 else
			 pathstring="userhome.jsp";


        String target = pathstring+"?status=Patient Discharge Failed";
         if(flag)
            target = pathstring+"?status=Patient Discharge Seccessfully";
         else  
            target = pathstring+"?status=Patient Discharge Failed"; 



        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request,response);
%>
</body>
</html>
