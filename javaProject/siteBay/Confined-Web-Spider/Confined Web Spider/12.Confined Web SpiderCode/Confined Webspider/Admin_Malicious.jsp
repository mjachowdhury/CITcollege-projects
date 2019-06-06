<%@ page isErrorPage="true"%>
<HTML><HEAD>
<h1>Welcome</h1>
<META Name="Author" name="Jaxp Search">
<META HTTP-EQUIV="page-enter" Content="blendTrans(duration=0.7)">
<LINK HREF="./Style/Search.cdf" REL="stylesheet"></HEAD>
<SCRIPT>var apptit="::Search Engine::"; for(tit=0;tit<150;tit++) apptit+="&nbsp;&nbsp;";document.write("<TITLE>"+apptit+"</TITLE>");</SCRIPT>
<SCRIPT SRC="./JScript/Email.cdf" Type="text/javascript"></SCRIPT>
<SCRIPT SRC="./JScript/Vertical.cdf" Type="text/javascript"></SCRIPT>
<%	
	 response.setHeader("Cahce-Control","no-cache");
     response.setHeader("Cache-Control","no-store");
     response.setHeader("Pragma","no-cache");
     response.setDateHeader("Expires",0);
     String msg=exception.getMessage();
	 msg=msg!=null?msg:"Unknown Response Found. Internal Server Error";
 %>
<center><BR><BR>
	<H4><%=msg%></h4><BR><BR>
	<button style='width:140px;height:22px;' onclick='location="javascript:history.back()"' accesskey="C"><u>C</u>ontinue Back</button>&nbsp;&nbsp;&nbsp;
	<button style='width:140px;height:22px;' onclick='location="./Home.jsp"' accesskey="N"><u>N</u>avigate Home</button>
<BR><BR><BR></center>
<Table width=780 align=center cellspacing=0 cellpadding=1 border=0>
<TR><Th align=center><font color=gray>For More Details Mail to me at&nbsp;&nbsp;<a href="compose.htm?ghkishore@hotmail.com">ghkishore@hotmail.com</font></a>&nbsp;&nbsp;or meet me at<bR><bR>
<a href="http://ghvishnu.tripod.com/hari.htm">http://ghvishnu.tripod.com/hari.htm</a></th></tr>
</TABLE>
</body></body></html>