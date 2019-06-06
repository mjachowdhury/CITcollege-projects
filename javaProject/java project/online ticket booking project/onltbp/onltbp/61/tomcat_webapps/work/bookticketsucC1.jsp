


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<br><center>Reserved Succesfully! </center><br>

<center>
  <br>  BusNumber    :     <bean:write name="bform" property="busno"/><br>
  
  <br>  ticket id    :    <bean:write name="bform" property="tickid"/><br>
  
 <br> Date of journey:    <bean:write name="bform" property="date"/><br>

  <br>  UserName      :    <bean:write name="bform" property="username1"/><br>

  <br> No.of.tickets   :   <bean:write name="bform" property="not"/><br>
</center>  
  
  
  <a href="Userlogin.do">Back</a>
   
  


