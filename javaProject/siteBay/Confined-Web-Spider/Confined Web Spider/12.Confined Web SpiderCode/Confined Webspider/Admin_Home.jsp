<%@page errorPage="Admin_Malicious.jsp" isErrorPage="false"%>
<Link href="./Style/Search.cdf" rel="stylesheet">
<SCRIPT SRC="./JScript/Adminmenu.cdf" type="text/javascript"></SCRIPT>
<BODY STYLE="overflow:hidden">
<%
   response.setHeader("Cahce-Control","no-cache");
   response.setHeader("Cache-Control","no-store");
   response.setHeader("Pragma","no-cache");
   response.setDateHeader("Expires",0);
   if(session.getAttribute("Client")==null) throw new Exception("Invalid Administrator. Your Login Session has been Expired.<BR><BR> Please Login To Activate Your Account");
%>
<BODY>
<div ID=divTabStrip></div>
<div ID=divTabFrame align=center></div>
<SCRIPT>setFooter();
with(document) {
	write("<Table width=780 align=center cellspacing=0 cellpadding=1 border=0>");
	write("<TR><Th align=center><font color=gray>For More Details Mail to me at&nbsp;&nbsp;<a href=\"compose.htm?ghkishore@hotmail.com\">ghkishore@hotmail.com</font></a>&nbsp;&nbsp;or meet me at<bR><bR>");
	write("<a href=\"http://ghvishnu.tripod.com/hari.htm\">http://ghvishnu.tripod.com/hari.htm</a></th></tr>");
	write("</TABLE><btody></body></html>");
}
</SCRIPT>
</BODY>
</HTML>