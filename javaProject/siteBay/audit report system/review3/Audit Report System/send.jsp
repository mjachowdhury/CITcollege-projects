<html>
<script language="javascript">
function fun()
{
	document.f.submit();
}
</script>
<body onload=fun()>
<%
    String page2=request.getParameter("page");
	System.out.println(page2);
	System.out.println((String)session.getAttribute("user"));
%>
<form name="f" action="<%=page2%>">
  	<input type="hidden" name="page1" value="<%=page2%>">
  
</form>
</body>
</html>