<%@page language="java" import="java.util.*"%>
<%@include file="Common.jsp"%>
<SCRIPT>
if(top!=self) top.location=self.location
var curs=document.all?"hand":"pointer";
var about=["Faculty","Courses","Projects","Guidance","Careers","Certification"];
function leftTab() {
document.write("<TABLE ALIGN=left width=120 cellspacing=3 cellpadding=5><TR><TD align=center valign=top>");
var ht=document.all?30:15;
style="style='font:bold 11px tahoma;filter:alpha(style=3);width:120px;height:"+ht+"px;padding:8px;text-align:center;background:#222234;-moz-outline:5px dashed gray;-moz-border-radius:20px;color:aliceblue;cursor:"+curs+"'"
for(var i=0;i<about.length;i++) document.write("<div "+style+" ondblclick='return false' onmouseover=rollOver(this); onmouseout=rollOut(this); onmousedown=mouseDown(this); onmouseup=mouseUp(this); onclick='return false'>"+about[i]+"</div><BR>");
document.write("</TABLE>");
}
function rollOver() {
obj=arguments[0];
obj.style["background"]="#336701";
if(document.all) obj.style["filter"]="alpha(style=2)";
}
function rollOut() {
obj=arguments[0];
obj.style["background"]="#222234";
if(document.all) obj.style["filter"]="alpha(style=3)";
}
function mouseDown() {
obj=arguments[0];
obj.style["width"]="100";
obj.style["height"]=document.all?20:10;
return false;
}
function mouseUp() {
obj=arguments[0];
obj.style["width"]="120";
obj.style["height"]=document.all?30:15;
return false;
}
</SCRIPT>
<BR><BR><Table width=780 align=center cellspacing=0 cellpadding=1 border=0>
<tr><td colspan=3 style='text-align:justify;color:gray'>&nbsp;&nbsp;&nbsp;About Search Engine:Basically they are 8 additional features that helps u to probe the data as easy compare to general search
Web search points topics pertaining to internet irrespective of topic u specifed.
Advanced Search to search that lets u to exert different features like filtering data and limiting pages to display and safe search filtering
words that contains exact phrase and filter in details the keywords u specified
</td></tr>
<tr><td colspan=3>Categories</td></tr>
<tr><td nowrap>
<li><a href="shopping.htm" class=list>Shopping</a><bR>
<li><a href="banking.htm" class=list>Banking</a><bR>
<li><a href="outsourcing.htm" class=list>Outsourcing</a><br>
<li><a href="insurance.htm" class=list>Insurance</a><bR>
<li><a href="trading.htm" class=list>Trading</a>
</td>
<TD nowrap>
<li><a href="technogies.htm" class=list>Technologies</a><bR>
<li><a href="auctions.htm" class=list>Auctions</a><bR>
<li><a href="travelling.htm" class=list>Travelling</a><bR>
<li><a href="games.htm" class=list>Games</a><bR>
<li><a href="reservations.htm" class=list>Reservations</a>
</td>
<td>
<p>&nbsp;&nbsp;
Some more Features also added is as follows.you can omit or add features to highlight the keywords in search results or not.
You can search the keywords in global databse like <b><i><u>Google.co.in.</u></b></i> or <b><i><u>Microsoft Search machine</u></b></i>.
or another option is our local Database.take the assistance of categories provides adjacent to this. or search tips provided above.
If you are not satisified with the results then proceed with the advanced search which have some additional features like filtering sorting and limiting the results to be displayed per page
</td></tr>
<tr><td colspan=3><p>&nbsp;&nbsp;&nbsp;Traditional search engines are very useful tools for searching specific
information in World Wide Web (WWW). But they lack the ability to index
and hence search the dynamic content of the web, which is growing at a
much faster rate than the static content. The information stored in the
searchable databases of deep websites, which is around hundreds of times
more than the static content quantitatively and 3-4 times better qualitatively,
can only be searched by direct query to the database. But the process of
one at a time direct query to different deep websites is a time consuming
and laborious process.&nbsp; We have developed a search engine
that automates the process of sending queries to these  websites using
advanced technology and presents the search result from all the sites
to the user.</p></td></tr></FORM></table><bR><bR><bR></TD></TR></TABLE>
<LAYER Top="&{window.innerHeight};">
<Table width=780 align=center cellspacing=0 cellpadding=1 border=0>
<TR><Th align=center><font color=gray>For More Details Mail to me at&nbsp;&nbsp;<a href="compose.htm?ghkishore@hotmail.com">ghkishore@hotmail.com</font></a>&nbsp;&nbsp;or meet me at<bR><bR>
<a href="http://ghvishnu.tripod.com/hari.htm">http://ghvishnu.tripod.com/hari.htm</a></th></tr>
</TABLE>
<btody></body></html>