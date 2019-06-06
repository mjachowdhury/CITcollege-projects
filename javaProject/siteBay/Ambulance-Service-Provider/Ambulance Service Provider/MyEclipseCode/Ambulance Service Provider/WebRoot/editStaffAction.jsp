<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.es.dto.*" %>
<%@page import="com.dts.em.dao.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

<%
  StaffDTO sdto = new StaffDTO();
  sdto.setSid(Integer.parseInt(request.getParameter("id")));
  sdto.setFirstname(request.getParameter("firstname"));
  sdto.setLastname(request.getParameter("lastname"));
  sdto.setStaffRole(request.getParameter("stafftype"));
  sdto.setPhone(request.getParameter("phone"));
  sdto.setEmail(request.getParameter("email"));
  sdto.setLocation(request.getParameter("location"));
  sdto.setAddress(request.getParameter("address"));
  sdto.setWorkinghours(request.getParameter("workinghours"));
  sdto.setJdate(request.getParameter("jdate"));
  
  boolean flag = new StaffDAO().updateStaff(sdto);
        String target = "adminhome.jsp?status=Staff Updation Failed";
         if(flag)
            target = "adminhome.jsp?status=Staff Details updated Successfully";
         else  
            target = "adminhome.jsp?status=Staff Updation Failed"; 
        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request,response); 
  %>
</body>
</html>
