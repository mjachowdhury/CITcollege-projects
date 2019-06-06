<%@page language="java" import="java.util.*"%>
<%
   if(session.getAttribute("Client")==null) throw new Exception("You are Not Logged On.<BR><br>Please Login To Upload Your Resume");
%>
<%@include file="Common.jsp"%>

<TABLE align=center width=780><TR><TD>
<fieldset><legend>Post Ur Resume</legend><BR>
<table width=600 cellspacing=0 cellpadding=3 align=center style="border:1px solid steelblue" id=advtab><form action="./UploadResume.jsp" method=get>
<TR><TD>Provide Mail-Id:</TD><Td><input name=mailid></td></tr>
<TR><TD>Resume Title:</TD><Td><input name=resumetitle></td></tr>
<TR><TD>Job code:</TD><Td><input name=jobcode>&nbsp;&nbsp;[&nbsp;Optional&nbsp;]&nbsp;</td></tr>
<TR><TD>Upload:</TD><Td><input name=resume readonly>&nbsp;&nbsp;<a href="javascript:getUploader('Upload.jsp')">Upload</a>
<TR><TD colspan=2 align=center><button type=submit accesskey="C">Continue</button>&nbsp;&nbsp;
<button type=reset accesskey="R">Reset</button>
</td></tr></form>
</table><BR></fieldset></TD></TR></TABLE>
<script>setBg();</script>
<script>
function getUploader() {
open(arguments[0],"","scrollbars=no,maximize=no,resizable=no,width=500,height=200,left=170,top=200");
}
setFooter();
</script>
