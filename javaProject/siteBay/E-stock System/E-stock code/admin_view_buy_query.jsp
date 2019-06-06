<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>
<html>
<head>
<script language="javascript">
function b2click()
{
f.action="home.htm";
f.method="post";
f.target="_top";
f.submit();
}
</script>
<base target="_parent">
</head>
<body bgcolor="oldlace">
&nbsp;<p>&nbsp;</p>
<div align="center">
  <center>
  <form action="IndividualSales.jsp" method="post">
  <table border="1" cellpadding="0" cellspacing="0" width="165" height="50" id="AutoNumber1" style="border-left-style: none; border-right-style: none; border-top: 1.5pt solid green; border-bottom: 1.5pt solid green" fpstyle="1,011111100">
    <tr>
      <td width="309" height="50" style="border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid green">
  <div align="left">
  
      <font face="Garamond" color="#800080"><b>Enter ID number of Customer:</b></font> <input type="text" name="cust_id" size="20">&nbsp;
  <input type="submit" value="SubmitQuery" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3"></div>
      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </p>
      <p>&nbsp;</td>
    </tr>
  </table>
  </form>
  </center>
</div>
</body>
</html>