<LINK HREF="./Style/Search.cdf" rel="stylesheet">
<%	
	String path=request.getRequestURL().toString();
	path=path.substring(0,path.lastIndexOf("jaxp")+4);
	out.println("<html><head><meta http-equiv='page-enter' content='blendTrans(duration=0.5)'>");
	out.println("<title>&nbsp;&nbsp;::&nbsp;&nbsp;Jaxp Intranet Search Engine&nbsp;&nbsp;::&nbsp;&nbsp;</title>");
	out.println("<link href='"+path+"/Style/search.cdf' rel='stylesheet'></head>");
	out.println("<script src='"+path+"/JScript/effect.js'></script></head>");
%>
<title>Attach Ur Resume Here</title>
<body style="oveflow-x:hidden;overflow-y:scroll">
<fieldset><legend>Attach Your Resume Here</legend><BR><BR>
<table width=100% cellspacing=0 cellpadding=0 align=center>
<form method=post enctype="multipart/form-data" action="javascript:setAction()">
<TR><Td align=right>Select File To Upload:<TD><input type=file name=filename></TD></tr>
<TR><TD colspan=2 align=center><BR><button type=submit style="width:120px" accesskey="A"><u>A</U>ttach Resume</button>&nbsp;&nbsp;<button onclick="self.close()" style="width:120px"  accesskey="C"><u>C</u>lose Window</button></TR></form>
</table><BR><BR></fieldset>
<script>
function setAction() {
var check=document.forms[0].filename;
if(check.value.length>0 && check.value.substring(check.value.lastIndexOf(".")+1)=="doc") { 	
			document.forms[0].target="uploader";
			document.forms[0].action="Uploader.jsp"
			document.forms[0].submit();
 		    self.location="./JScript/prgbar.htm";
			

} else { alert("Please Select Word Dcoument Only to Attach");
		check.focus();
		 }
}	
</script>