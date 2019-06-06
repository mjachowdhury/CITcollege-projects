<HTML>
<HEAD>
<TITLE>Sathya Technologies</TITLE>
</HEAD>
<STYLE>
<%
	String;
%>
BODY
{
	BACKGROUND:url(./images/telecom.jpg);
	SCROLL: NO;
	margin:0px;
	overflow:hidden;
}
.tabFrame {
	BORDER-RIGHT: #FF9900 9PX SOLID;
	BORDER-TOP: #FF9900 9PX SOLID;
	SCROLLBAR-FACE-COLOR: #6699CC;
	SCROLLBAR-HIGHLIGHT-COLOR: #FFFFFF;
	BORDER-LEFT: #FF9900 9PX SOLID; WIDTH: 780px;
	SCROLLBAR-SHADOW-COLOR: #6699CC;
	SCROLLBAR-ARROW-COLOR: #FFFFFF;
	BORDER-BOTTOM: #FF9900 9PX SOLID;
	SCROLLBAR-DARKSHADOW-COLOR: #6699CC;
	HEIGHT: 330px;
}
.tabOff {
	FONT-FAMILY: Verdana;
	FONT-SIZE: 11;
	FONT-WEIGHT: 100;
	TEXT-ALIGN: CENTER;
	COLOR: #000000;
	BACKGROUND-COLOR: #FBEDBB;
	BORDER-BOTTOM: #FBEDBB 1PX SOLID;
	BORDER-TOP: #FF9900 1PX SOLID;
	BORDER-LEFT: #FF9900 1PX SOLID;
	BORDER-RIGHT: #FF9900 1PX SOLID;
	HEIGHT: 20;
	width:110px;
	-moz-border-radius-bottomleft:0px;
	-moz-border-radius-topleft:20px;
	-moz-border-radius-bottomright:0px;
	-moz-border-radius-topright:20px;
}
.tabOn {
	FONT-FAMILY: Verdana;
	FONT-SIZE: 11;
	FONT-WEIGHT: 700;
	TEXT-ALIGN: CENTER;
	COLOR: #FFFFFF;
	BACKGROUND-COLOR: #FF9900;
	BORDER-BOTTOM: #FF9900 1PX SOLID;
	BORDER-TOP: #FF9900 1PX SOLID;
	BORDER-LEFT: #FF9900 1PX SOLID;
	BORDER-RIGHT: #000000 1PX SOLID;
	HEIGHT: 20;
	width:110px;
	-moz-border-radius-bottomleft:0px;
	-moz-border-radius-topleft:20px;
	-moz-border-radius-bottomright:0px;
	-moz-border-radius-topright:20px;
}

</STYLE>
<SCRIPT LANGUAGE="JavaScript">
var icurs=document.all?"hand":"pointer"
document.write("<style>input {cursor:"+icurs+"}</style>");
// Tab Name | URL | * (Default Selected Tab)
var tabs = new Array (
	/*BUGFIX*/ new Array(""),
	["Home|Home.jsp|*"],//remove |* to select next tab
	["Addresses|Admin_UrlDetails.jsp|*"],
	["Products|Admin_ProductDetails.jsp|*"],
	["Jobs|Admin_JobDetails.jsp|*"],
	["Yellow Pages|Admin_YellowPageDetails.jsp|*"],
	["Resumes|Admin_CheckResumes.jsp|*"],
	["Signout|Signout|*"]

		);
var HTML = "";
function tabHide(ID) {
try{
	var e = document.getElementById(ID);
	e.filters[0].Apply();
	if (e != null)	{
		e.style.display = "none";
		e.filters[0].Stop();
	}
}catch(e) { }
}
function tabShow(ID) {
try{
	var e = document.getElementById(ID);
	e.filters[0].Apply();
	if (e != null)	{
		e.style.display = "block";
		e.filters[0].Play();
	}
}catch(e) { }
}
function tabOnClick(ID, parent, child) {
	var oElement = null;
	for (var i = 1; i < tabs.length; i++)	{
		oElement = document.getElementById(i);
		oElement.className = "tabOff";
		for (var j = 1; j < tabs[i].length; j++) {
			var childID = i + "" + j;
			oElement = document.getElementById(childID);
			oElement.className = "tabOff";
		}
	}	
	oElement = document.getElementById(ID);
	oElement.className = "tabOn";
	var tab = tabs[parent][child].split("|");
	document.getElementById('divTabFrame').innerHTML = "<IFRAME SRC='"+tab[1]+"' CLASS='tabFrame'></IFRAME>";
	if (child == 0)	{
		for (var i = 1; i < tabs.length; i++) {
			tabHide("child"+i);
		}
		tabShow("child"+ID);
	}
	if(document.all) document.body.focus();
}
function tabLoadParents() {
	for (var i = 1; i < tabs.length; i++)	{
		var tab = tabs[i][0].split("|");
		HTML += "<INPUT TYPE='BUTTON' ID="+i+" CLASS='tabOff' VALUE='"+tab[0]+"' onClick='tabOnClick("+i+", "+i+", 0)' onfocus=this.blur()>&nbsp";
	}
}
function tabLoadChildren()
{
	for (var i = 1; i < tabs.length; i++)
	{
		HTML += "<TD STYLE='DXImageTransform' ID=child"+i+">";

		for (var j = 1; j < tabs[i].length; j++)
		{
			var tab = tabs[i][j].split("|");	

			var childID = i + "" + j;
			HTML += "<INPUT  TYPE='BUTTON' ID="+childID+" CLASS='tabOff' VALUE='"+tab[0]+"' onClick='tabOnClick("+childID+", "+i+", "+j+")' onfocus=this.blur();>&nbsp";		
		}

		HTML += "</TD>";
	}
}

function tabOnLoad() {
	HTML += "<TABLE BORDER='0' CELLPADDING='0' CELLSPACING='0' WIDTH='780' align=center>";
	HTML += "<TD ALIGN='LEFT'>&nbsp;&nbsp;";
	tabLoadParents();
	tabLoadChildren();
	HTML += "</TD></TR>";
	HTML += "</TABLE>";	
	document.getElementById('divTabStrip').innerHTML = HTML;
	for (var i = 1; i < tabs.length; i++)	{
		for (var j = 1; j < tabs[i].length; j++) {
			var childID = i + "" + j;
			oElement = document.getElementById(childID);
			oElement.className = "tabOff";
		}
	}
	for (var i = 1; i < tabs.length; i++)	{
		var tab = tabs[i][0].split("|");
		if (tab[2] == "*")		{
			tabOnClick(i, i, 0);
			break;			
		}
	}
}
//-->
</SCRIPT>
<BODY onload="tabOnLoad()">
<div ID=divTabStrip></div>
<div ID=divTabFrame align=center></div>
</BODY>
</HTML>