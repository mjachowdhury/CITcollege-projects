<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*,java.util.*,java.text.*" %>
<html>
<head>
<script language="javascript">
function b1click()
{
f.action="cust_request.jsp";
f.method="post";
f.submit();
}
function b2click()
{
f.action="home.htm";
f.method="post";
f.target="_top";
f.submit();
}
</script>
</head>
<body bgcolor="oldlace">
<%!
/*Retrieving the values of Sell Shares form in variables. */

        
String cid="", company="",no_shares="", validity="", order_type="", rate="";
int cust_id=0, share_no=0;
float share_rate=0;
Connection conn=null;
PreparedStatement stmt = null, ps = null;;
Statement st = null;
ResultSet rs=null;
int y=0;
%>
<%
try
{      
	
cid=(String)session.getAttribute("CUSTID");
company=(String)session.getAttribute("name_company");
no_shares=(String)session.getAttribute("no_share");
validity=(String)session.getAttribute("validity");
order_type=(String)session.getAttribute("order_type");
rate=(String)session.getAttribute("rate");
cust_id=Integer.parseInt(cid);
share_no=Integer.parseInt(no_shares);
share_rate=Float.parseFloat(rate.trim());

}
catch(Exception e)
{
    out.print(e);       
}

//out.println("Reache1");

/*Storing the values of the variables in the Seller table of the savv.mdb database. */
 



try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
conn=DriverManager.getConnection("jdbc:odbc:stis","stis","stis");

st = conn.createStatement();
//double psv = Double.parseDouble((String)session.getAttribute("shareValue")); //shareValue

String	qry = "select RATE_PER_SHARE from  buyer where COMPANY_NAME = '"+company+"'";

rs = st.executeQuery(qry);
int shareValue = 0;

	while(rs.next())
	{
			shareValue = rs.getInt(1);
	}


qry = "insert into portfolio values(?,?,?,?,?,?,?, ?)";

ps = conn.prepareStatement(qry);


ps.setInt(1, cust_id);
ps.setString(2,company);
ps.setInt(3,share_no);
ps.setString(4, ""+shareValue);
ps.setString(5, ""+(share_rate));
ps.setString(6, ""+(shareValue * share_no));
ps.setString(7, ""+(share_rate * share_no));

float profit = (float)(share_rate * share_no) - (shareValue * share_no);
ps.setString(8, ""+profit);


y=ps.executeUpdate();


//out.println("Reache2");
stmt=conn.prepareStatement("insert into seller(CID,COMPANY_NAME,NO_OF_SHARES,RATE_PER_SHARE, VALIDITY,DATE_S,ORDER_TYPE, commission) values (?,?,?,?,?,?,?, ?)");
java.util.Date now = new java.util.Date();
DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
String s1 = df1.format(now);
stmt.setInt(1,cust_id);
stmt.setString(2,company);
stmt.setInt(3,share_no);
stmt.setFloat(4,share_rate);
stmt.setString(5,validity);
stmt.setString(6,s1);
stmt.setString(7,order_type);

//retrieving commisiion from session object

float commission = Float.parseFloat((String)session.getAttribute("commission"));
stmt.setFloat(8, commission);
y=stmt.executeUpdate();

//out.println("Reache3");

if(y>=1)
{
stmt=conn.prepareStatement("select * from seller where cid=?");
stmt.setString(1,cid);
rs=stmt.executeQuery();
%> 


<br><br>
<div align="center">
  <center>
<table border="1" width="497" cellpadding="0" cellspacing="0" 

bordercolorlight="#C0003B" bordercolordark="#FFFFFF" style="border-collapse: collapse; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: 1.5pt solid gray" fpstyle="25,011111100" bordercolor="#111111">
  <tr> 
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="55">
    <font face="Arial, Helvetica, 
sans-serif" size="2">Symbol</font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="103"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">No_Of_Shares</font></b></font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="66"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, sans-serif">Rate    </font></b></font></td>
    <td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="100"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Validity</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="103"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Date Of Sale</font></b></font></td>
<td style="font-weight: bold; color: white; border-left-style: none; border-right-style: none; border-top-style: none; border-bottom: .75pt solid black; background-color: #008078" width="70"><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica, 
sans-serif">Order Type</font></b></font></td>
  </tr>
  <%
while(rs.next())
{
String a=rs.getString(2);
String b=rs.getString(3);
String c=rs.getString(4);
String d=rs.getString(5);
String e=rs.getString(6);
String f=rs.getString(7);
%> 
  <tr> 
    <td style="border-style: none" width="55"><font size="2" face="Arial, Helvetica, sans-serif"><%=a%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="103"><font size="2" face="Arial, Helvetica, sans-serif"><%=b%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="66"><font size="2" face="Arial, Helvetica, sans-serif"><%=c%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="100"><font size="2" face="Arial, Helvetica, sans-serif"><%=d%></font>&nbsp;</td>
    <td style="font-weight: normal; color: black; border-style: none; background-color: white" width="103"><font size="2" face="Arial, Helvetica, sans-serif"><%=e%></font>&nbsp;</td>
	<td style="font-weight: normal; color: black; border-style: none; background-color: white" width="70"><font size="2" face="Arial, Helvetica, sans-serif"><%=f%></font>&nbsp;</td>
  </tr>
  <%
}//while
}//if
conn.close();  
}//try
catch(SQLException ee)
{
    out.println("Error is :"+ee );
}
%> 
</table>
  </center>
</div>
<form name="f">
  <div align="center">
    <input type="button" name="b1" value="More to sell..." onclick="b1click()" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
    <input type="button" name="b2" value="Proceed to Checkout" onclick="b2click()" style="color: #800080; font-style: italic; font-weight: bold; border-style: outset; border-width: 3">
  </div>
</form>
</body>
</html>