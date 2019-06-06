<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.es.dto.*" %>
<%@page import="com.dts.em.fun.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

<%
  HospitalizedStatusDTO hdto = new HospitalizedStatusDTO();
  
//out.print("Hello..........."+request.getParameter("role").equals("admin"));
  hdto.setPid(Integer.parseInt(request.getParameter("pid")));
  hdto.setDocid(Integer.parseInt(request.getParameter("adoctors")));
  hdto.setHid(Integer.parseInt(request.getParameter("ahelpers")));
  hdto.setRoomNo(request.getParameter("roomno"));
  hdto.setDiscease(request.getParameter("discease"));
  hdto.setMedicine(request.getParameter("medicine"));


 String pathstring = "";

		if(((String)session.getAttribute("role")).equals("admin"))
          pathstring="adminhome.jsp";
		 else
			 pathstring="userhome.jsp";


        boolean flag = new HospitalizedStatus().registerHospitalizedStatus(hdto);

		String target = pathstring+"?status=Patient Admission Failed";
         if(flag)
            target = pathstring+"?status=Patient Admission  Success";
         else  
            target = pathstring+"?status=Patient Admission Failed"; 


  

        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request,response); 
  %>
</body>
</html>
