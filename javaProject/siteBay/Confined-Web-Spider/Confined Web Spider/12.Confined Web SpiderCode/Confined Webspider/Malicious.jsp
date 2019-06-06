<%@ page isErrorPage="true"%>
<HTML><HEAD>
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
     String Client=session.getAttribute("Client")!=null?session.getAttribute("Client").toString():"";
	 String Session=(Client.length()==0)?"Signin":"Signout";
	 String quest=(request.getParameter("quest")!=null)?request.getParameter("quest"):"";
     String condition=(request.getParameter("hlightres")!=null&&request.getParameter("hlightres").equals("yes"))?"checked":"";
	 StringBuffer content=new StringBuffer();
	 String cursor=request.getHeader("User-Agent").indexOf("MSIE")!=-1?"hand":"pointer";
	 String style="width='10%' style='cursor:"+cursor+";color:lightslategray'";
	 content.append("</head><body oncontextmenu='return false' style='overflow-y:scroll;overflow-x:hidden'>");
	 content.append("<TABLE Width=780 cellspacing=1 bgcolor=gray align=center cellpadding=5 id=tabber>");
	 content.append("<TR bgcolor=whitesmoke>");
	 content.append("<TD nowrap "+style+" align=center url='Home.jsp'>Topic</TD>");
	 content.append("<TD nowrap "+style+" align=center url='Advanced.jsp'>Advanced</TD>");
	 content.append("<TD nowrap "+style+" align=center url='Products.jsp'>Products</TD>");
	 content.append("<TD nowrap "+style+" align=center url='Jobs.jsp'>Jobs</TD>");
	 content.append("<TD nowrap "+style+" align=center url='YellowPages.jsp'>Yellow Pages</TD>");
	 content.append("<TD nowrap "+style+" align=center url='PostResume.jsp'>Post Resume</TD>");
	 content.append("<TD nowrap "+style+" align=center url='"+Session+".jsp'>"+Session+"</TD></TR></TABLE>");
	 content.append("<TABLE width=780 align=center cellspacing=0 cellpadding=0><TR><TD><fieldset>");
	 content.append("<legend>Topic Search Engine</legend>");
	 content.append("<TABLE Width=680 align=center cellspacing=0 cellpadding=2><Form action='./Search' method=get>");
	 content.append("<TR><TD nowrap align=center>Probe In:");
	 content.append("<input type=radio class=check name=probe value=\"database\" checked>Database&nbsp;&nbsp;");
	 content.append("<input type=radio name=probe class=check value=\"global\">Global&nbsp;<input class=check type=checkbox name=hlightres value=yes "+condition+">Highlight Search Keywords in Search Results</TD></TR>");
	 content.append("<TR><TD align=center>Search Keyword:&nbsp;<input name=quest value='"+quest+"' class=global><input type=hidden name=limit value=5><input type=hidden name=pageno value=0>&nbsp;<button type=submit accesskey=\"Q\">Quest</button></TD></tr>");
	 content.append("<TR><TD colspan=2 align=center><a href='Advanced.jsp'>Advanced Search</a><FONT COLOR=WHITE>&nbsp;&nbsp;|&nbsp;&nbsp;</FONT><a href='Searchtips.htm'>Search Tips</a><FONT COLOR=WHITE>&nbsp;&nbsp;|&nbsp;&nbsp;</FONT><a href='Feedback.jsp'>Feedback</a></TD></tr></form></table>");
	 content.append("</fieldset></TD></TR></TABLE>");
	 out.println(content.toString());
 %><SCRIPT>buildMenu();</SCRIPT>
<center><BR><BR>
	<H4><%=msg%></h4><BR><BR>
	<button style='width:140px;height:25px;' onclick='location="javascript:history.back()"' accesskey="C"><u>C</u>ontinue Back</button>&nbsp;&nbsp;&nbsp;
	<button style='width:140px;height:25px;' onclick='location="./Home.jsp"' accesskey="N"><u>N</u>avigate Home</button>
<BR><BR><BR></center>
<Table width=780 align=center cellspacing=0 cellpadding=1 border=0>
<TR><Th align=center><font color=gray>For More Details Mail to me at&nbsp;&nbsp;<a href="compose.htm?ghkishore@hotmail.com">ghkishore@hotmail.com</font></a>&nbsp;&nbsp;or meet me at<bR><bR>
<a href="http://ghvishnu.tripod.com/hari.htm">http://ghvishnu.tripod.com/hari.htm</a></th></tr>
</TABLE>
</body></body></html>