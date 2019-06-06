<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

    <h1 align="center">Report on Feedback given by customers</h1>
    <br>
     <%@ page import="java.sql.*" %>
     <center><a href="admin_workshop.htm">Admin Home</a>  </center><br>
    <table border="2" cellpadding="2" cellspacing="2" bgcolor="cyan" title="Report" align="center">
        <tr align="center">
        <%!
        String query = "select name, email, comments from feedback";
       
        ResultSetMetaData rsmd = null;
        ResultSet rs = null;
        Connection con = null;
        Statement st = null;
        %>
        <%
        
        try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con = DriverManager.getConnection("jdbc:odbc:stis", "stis", "stis");
        st = con.createStatement();
        rs = st.executeQuery(query);
        rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        
        for(int i=1;i<=cols;i++)
        {%>
        <th>
            <%=rsmd.getColumnLabel(i)%>
        </th>
        <%}%>
        
        <%while(rs.next())
          {%>
          <tr align="center">
              
              <%for(int i=1;i<=cols;i++)
              {%>
              <td>
                  <%=rs.getString(i)%>
              </td>
              <%}%>
          </tr>
          <%}//while
            }
            catch(Exception e)
            {
                out.println("Exception : "+e);
            }
        %>
    </table>
    
    <br>
   <center><a href="admin_workshop.htm">Admin Home</a>  </center>
    </body>
</html>
