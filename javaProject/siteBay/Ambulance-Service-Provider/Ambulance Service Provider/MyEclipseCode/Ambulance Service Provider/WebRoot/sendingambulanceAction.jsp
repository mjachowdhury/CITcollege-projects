<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.es.dto.*" %>
<%@page import="com.dts.em.fun.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

<%
  AmbulanceStatusDTO asdto = new AmbulanceStatusDTO();
  
  
  asdto.setPid(Integer.parseInt(request.getParameter("pid")));
  asdto.setAid(Integer.parseInt(request.getParameter("ano"))); 
  asdto.setDid(Integer.parseInt(request.getParameter("adrivers")));
  asdto.setHid(Integer.parseInt(request.getParameter("ahelpers")));
  asdto.setDocid(Integer.parseInt(request.getParameter("adoctors")));
  
  boolean flag = new AmbulanceStatus().registerAbulanceSatus(asdto);


 String pathstring = "";

		if(((String)session.getAttribute("role")).equals("admin"))
          pathstring="adminhome.jsp";
		 else
			 pathstring="userhome.jsp";




        String target =  pathstring+"?status=Ambulance Sending Failed";
         if(flag)
            target = pathstring+"?status=Ambualance Sent Seccessfully";
         else  
            target = pathstring+"?status=Ambulance Sending Failed"; 

        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request,response); 
  %>
</body>
</html>
