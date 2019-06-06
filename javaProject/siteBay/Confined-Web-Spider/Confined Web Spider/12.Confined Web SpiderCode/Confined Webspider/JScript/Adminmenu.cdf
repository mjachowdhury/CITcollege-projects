document.oncontextmenu=new Function("return false");
var apptit="::Administration Terminal::";
for(tit=0;tit<150;tit++) apptit+="&nbsp;&nbsp;";document.write("<TITLE>"+apptit+"</TITLE>");
var sitems=[
			["Addresses","Admin_UrlDetails.jsp"],
			["Products","Admin_ProductDetails.jsp"],
			["Jobs","Admin_JobDetails.jsp"],
			["Yellow Pages","Admin_YellowPageDetails.jsp"],
			["Resumes","Admin_CheckResumes.jsp"],
			["Signout","Signout"]
  		  ]
 var incursor=document.all?"hand":"pointer";
 function buildTab() {
 document.write("<table border=0 width=780 align=center cellspacing=6 cellpadding=2><TR>");
 for(var it in sitems) document.write("<Td width=10% align=center style='font:bold 11px tahoma;border:1px solid #224355;color:whitesmoke;cursor:"+incursor+"' ooncontextmenu=newWin(this.getAttribute('url')); url='"+sitems[it][1]+"' onmouseout=stopFade(this); onmouseover='bgFade(this)'; bgcolor=#224355 onclick=setLocation(this.getAttribute('url'));>"+sitems[it][0]+"</td>");
 document.write("</table>");
 }
 var ini=0,fader;
var fcolor=["000000","111111","222222","333333","444444","555555","666666","777777","888888","999999","aaaaaa","bbbbbb","cccccc","dddddd","eeeeee","ffffff","honeydew"]
 //fcolor=fcolor.reverse();
function bgFade(obj) {
objt=eval(obj);
ini++;
objt.style.backgroundColor=fcolor[ini];
if(ini==fcolor.length) {
	ini=0;
	return}
fader=setTimeout("bgFade(objt)",50);
}

function setLocation() {
if(arguments[0]=="Signout") parent.location="Signout.jsp";
document.getElementById('frame1').setAttribute("src",arguments[0]);
}
var tabs = new Array (
	/*BUGFIX*/ new Array(""),
	["Addresses|Admin_UrlDetails.jsp|*"],
	["Products|Admin_ProductDetails.jsp|*"],
	["Jobs|Admin_JobDetails.jsp|*"],
	["Yellow Pages|Admin_YellowPageDetails.jsp|*"],
	["Resumes|Admin_CheckResumes.jsp|*"],
	["Signout|Signout.jsp|*"]

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
		HTML += "<INPUT style='cursor:"+incursor+"' TYPE='BUTTON' ID="+i+" CLASS='tabOff' VALUE='"+tab[0]+"' onClick='tabOnClick("+i+", "+i+", 0)' onfocus=this.blur()>&nbsp";
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
try{
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
	}catch(e) { }
}
function setFooter() {
with(document) {
	write("</TABLE><center><BR><BR>");
	write("<button style='width:140px;height:22px;' onclick='parent.location=\"javascript:history.back()\"' accesskey=\"C\"><u>C</u>ontinue Back</button>&nbsp;&nbsp;&nbsp;");
	write("<button style='width:140px;height:22px;' onclick='parent.location=\"./Home.jsp\"' accesskey=\"N\"><u>N</u>avigate Home</button>");
	write("<BR><BR><BR></center>");}
}
onload=function() {
var box=document.getElementsByTagName("INPUT");
var but=document.getElementsByTagName("BUTTON");
var combo=document.getElementsByTagName("SELECT");
var tarea=document.getElementsByTagName("TEXTAREA");
for(var b=0;b<box.length;b++) {
    if(box[b].type!="checkbox" && box[b].type!="radio") {
    box[b].onfocus=function() { bgFade(this);this.select();}
    box[b].onblur=function() {this.style["background"]="aliceblue"}}}
for(var c=0;c<combo.length;c++) {
    combo[c].onfocus=function() {this.style["background"]="honeydew";}
    combo[c].onblur=function() {this.style["background"]="aliceblue";}}
for(var a=0;a<tarea.length;a++) {
    tarea[a].onfocus=function() {bgFade(this);this.select();this.style["height"]="70px";}
    tarea[a].onblur=function() {this.style["background"]="aliceblue";this.style["height"]="20px";}}
for(var t=0;t<but.length;t++) {
	but[t].style['cursor']=incursor;
    but[t].onmouseover=function() {this.style["background"]="teal";}
    but[t].onmouseout=function() {this.style["background"]="brown";}}
   tabOnLoad();
}