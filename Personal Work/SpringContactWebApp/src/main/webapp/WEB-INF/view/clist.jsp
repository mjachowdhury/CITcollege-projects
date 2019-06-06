 <%-- 
    Document   : User Contact List
    Created on : 06-Aug-2017, 18:19:44
    Author     : Mohammed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %> <!--this is tag library-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Contact List - Contact Application</title>
        <s:url var="url_css" value="/static/css/style.css"/>
        <link href="${url_css}" rel="stylesheet" type="text/css"/>
    </head>
    <s:url var="url_bg" value="/static/images/background_image.jpg"/>
    
    <body background="${url_bg}">
        
         <table border ="1" width ="80%" align="center">
             <tr>
                 <td height="80px">
                     <%-- Header --%>
                     <jsp:include page="include/header.jsp"/>
                   
                 </td>
             </tr>
             <tr>
                 <td height="25px">
                     <%-- Menu --%>
                     <jsp:include page="include/menu.jsp"/>
                 </td>
             </tr>
             <tr>
                 <td height="350px" valign="top">
                      <%-- Page Content Area --%>
                      <h3>User Contact List</h3>
                      <c:if test="${param.act eq 'sv'}"> <!--if the action parameter contains condition sv then we can put a message-->
                          <p class="success">Contact Saved Successfully</p>
                      </c:if>  
                      <c:if test="${param.act eq 'del'}"> <!--if the action parameter contains condition del then we can put a message-->
                          <p class="success">Contact Deleted Successfully</p>
                      </c:if>  
                     
                          <!--Search table start-->
                          <table width="100%">
                              <tr>
                                   <td align="right">
                                        <form action="<s:url value="/user/contact_search"></s:url>">
                                         <input type="text" name="freeText" value="${param.freeText}" placeholder="Enter Text To Search">
                                         <button>Find</button>                                  
                                         </form>
                                   </td>    
                              </tr>                            
                          </table>
                           <!--End search table-->    
                        
                   <form action="<s:url value="/user/bulk_cdelete"></s:url>"><!--this form for bulk delete-->
                        
                      <c:if test="${not empty contactList}">
                            <button>Delete Selected Records</button> <br/><br/>
                      </c:if>﻿ 
                      
                       <!--User and Contact  table start-->   
                      <table border="1" cellpadding="3"width="100%">
                          <tr>
                             <!-- <th>S. NO</th>-->
                              <th>SELECT</th>
                              <th>C. ID</th>
                              <th>NAME</th>
                              <th>PHONE</th>
                              <th>EMAIL</th>
                              <th>ADDRESS</th>
                              <th>REMARK</th>
                              <th>ACTION</th>
                          </tr>
                          <c:if test="${empty contactList}"> <!--condition-->
                                <tr>
                                     <td align="center" colspan="8" class="error">No Records Present</td>
                                 </tr>
                          </c:if>
                          <c:forEach var="c" items="${contactList}" varStatus="st"> <!--this generating all the records from the database-->
                          <tr>
                              <!--<td>${st.count}</td>-->
                              <td align="center"><input type="checkbox" name="cid" value="${c.contactId}"></td>
                              <td>${c.contactId}</td>
                              <td>${c.name}</td>
                              <td>${c.phone}</td>
                              <td>${c.email}</td>
                              <td>${c.address}</td>
                              <td>${c.remark}</td>
                              <!--delete url-->
                              <s:url var="url_del" value="/user/del_contact">
                                  <s:param name="cid" value="${c.contactId}"> </s:param>
                              </s:url>
                              <!--edit url-->
                              <s:url var="url_edit" value="/user/edit_contact">
                                  <s:param name="cid" value="${c.contactId}"> </s:param>
                              </s:url>
                              <td> <a href="${url_edit}">Edit</a> | <a href="${url_del}">Delete</a></td>
                          </tr>
                          </c:forEach>
                      </table>
                        <!--User and Contact  table End-->  
                      </form>  <!--End bulk delete form-->        
                        
                 </td>
             </tr>
             <tr>
                 <td height="25px">
                     <%-- Footer --%>
                      <jsp:include page="include/footer.jsp"/>
                 </td>
             </tr>
             
         </table>
    </body>

</html>