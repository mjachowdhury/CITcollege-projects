<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.es.dto.*" %>
<%@page import="com.dts.em.dao.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>


<%
  PatientDTO pdto = new PatientDTO();
  pdto.setPid(Integer.parseInt(request.getParameter("id")));
  pdto.setFirstname(request.getParameter("firstname"));
  pdto.setLastname(request.getParameter("lastname"));
  pdto.setDob(request.getParameter("dob"));
  pdto.setLocation(request.getParameter("location"));
  pdto.setCity(request.getParameter("city"));
  pdto.setState(request.getParameter("state"));
  pdto.setAddress(request.getParameter("address"));
  pdto.setPhone(request.getParameter("phone"));
  pdto.setHeight(request.getParameter("height"));
  pdto.setWeight(request.getParameter("weight"));
  pdto.setJdate(request.getParameter("jdate"));
  pdto.setType(request.getParameter("type"));
  boolean flag = new PatientDAO().updatePatients(pdto);

  String pathstring = "";

		if(((String)session.getAttribute("role")).equals("admin"))
          pathstring="adminhome.jsp";
		 else
			 pathstring="userhome.jsp";

        String target = pathstring+"?status=Patient Updation Failed";
         if(flag)
            target = pathstring+"?status=Patient Updataion Success";
         else  
            target = pathstring+"?status=Patient Updation Failed"; 

        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request,response); 
  %>
</body>
</html>
