var curs=document.all?"hand":"pointer";
var about=["Faculty","Courses","Projects","Guidance","Careers","Certification"];
function leftTab() {
document.write("<TABLE ALIGN=left width=120 cellspacing=3 cellpadding=5><TR><TD align=center valign=top>");
var ht=document.all?30:15;
style="style='font:bold 11px tahoma;filter:alpha(style=3);width:120px;height:"+ht+"px;padding:8px;text-align:center;background:#222234;-moz-outline:5px dashed gray;-moz-border-radius:20px;color:aliceblue;cursor:"+curs+"'"
for(var i=0;i<about.length;i++) document.write("<div "+style+" ondblclick='return false' onmouseover=rollOver(this); onmouseout=rollOut(this); onmousedown=mouseDown(this); onmouseup=mouseUp(this); onclick='return false'>"+about[i]+"</div><BR>");
document.write("</TABLE>");
}
/*function rollOver() {
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
}*/
document.write('<div id="ie5menu" class="skin0" onMouseover="highlightie5(event)" onMouseout="lowlightie5(event)" onClick="jumptoie5(event)" oncontextmenu=onclick(); ddisplay:none>');
document.write('<div class="cmenuitems" url="Home.jsp">Search.com</div>');
document.write('<div class="cmenuitems" url="javascript:viewSource()">View Source</div>');
document.write('<hr size=1 noshade color=white>');
document.write('<div class="cmenuitems" url="Advanced.jsp">Advanced Search</div>');
document.write('<div class="cmenuitems" url="PostResume.jsp">Post Your Resume</div>');
document.write('<div class="cmenuitems" url="Feedback.jsp">Feed Back</div>');
document.write('<HR size=1 noshade color=white>');
document.write('<div class="cmenuitems" url="mailto:ghkishore9@.com">Email Us</div>');
document.write('</div>');
var display_url=0
var ie5=document.all&&document.getElementById
var ns6=document.getElementById&&!document.all
var menuobj=document.getElementById("ie5menu");
function showmenuie5(e){
var fire=ie5? event.srcElement : e.target;
if(fire.className && fire.className=="menuitem") return;
if(fire.tagName=="A") {
	conf=confirm("Would You Like To Open This Link In New Window\n\n"+fire.getAttribute("href"));
	if(conf) {
		  open(fire.getAttribute("href"));
		  return false;
		  }
	return false;
} if(fire.getAttribute("onclick")!=null || fire.getAttribute("onfocus")!=null || fire.getAttribute("onmouseover")!=null || fire.getAttribute("name")!=null ||fire.tagName=="BUTTON") return false;
var rightedge=ie5? document.body.clientWidth-event.clientX : window.innerWidth-e.clientX
var bottomedge=ie5? document.body.clientHeight-event.clientY : window.innerHeight-e.clientY
if (rightedge<menuobj.offsetWidth)
menuobj.style.left=ie5? document.body.scrollLeft+event.clientX-menuobj.offsetWidth : window.pageXOffset+e.clientX-menuobj.offsetWidth
else
menuobj.style.left=ie5? document.body.scrollLeft+event.clientX : window.pageXOffset+e.clientX
if (bottomedge<menuobj.offsetHeight)
menuobj.style.top=ie5? document.body.scrollTop+event.clientY-menuobj.offsetHeight : window.pageYOffset+e.clientY-menuobj.offsetHeight
else
menuobj.style.top=ie5? document.body.scrollTop+event.clientY : window.pageYOffset+e.clientY
menuobj.style.visibility="visible"
return false
}
function hidemenuie5(e){
menuobj.style.visibility="hidden"
}
function highlightie5(e){
var firingobj=ie5? event.srcElement : e.target
if (firingobj.className=="cmenuitems"||ns6&&firingobj.parentNode.className=="cmenuitems"){
if (ns6&&firingobj.parentNode.className=="cmenuitems") firingobj=firingobj.parentNode //up one node
firingobj.style.backgroundColor="steelblue"
firingobj.style.color="white"
if (display_url==1)
window.status=event.srcElement.url
}
}
function lowlightie5(e){
var firingobj=ie5? event.srcElement : e.target
if (firingobj.className=="cmenuitems"||ns6&&firingobj.parentNode.className=="cmenuitems"){
if (ns6&&firingobj.parentNode.className=="cmenuitems") firingobj=firingobj.parentNode //up one node
firingobj.style.backgroundColor=""
firingobj.style.color="aliceblue"
window.status=''
}
}
function jumptoie5(e){
var firingobj=ie5? event.srcElement : e.target
if (firingobj.className=="cmenuitems"||ns6&&firingobj.parentNode.className=="cmenuitems"){
if (ns6&&firingobj.parentNode.className=="cmenuitems") firingobj=firingobj.parentNode
if (firingobj.getAttribute("target"))
window.open(firingobj.getAttribute("url"),firingobj.getAttribute("target"))
else
window.location=firingobj.getAttribute("url")
}
}
if (ie5||ns6){
menuobj.style.display=''
document.oncontextmenu=showmenuie5
document.onclick=hidemenuie5
}
function viewSource() {location="view-source:"+location;}
function setUserPane() {
document.write('<TABLE width=780 align=center cellspacing=5 cellpadding=5 style="font:bold 11px tahoma;">');
document.write('<form method=post onsubmit="return formValidate(this)" action="Client.jsp"><tr><TD align=center nowrap>')
document.write('<u>U</u>ser Id&nbsp;&nbsp;:&nbsp;&nbsp;<input accesskey="U" class=panel name=userid>&nbsp;&nbsp;&nbsp;&nbsp;<u>P</u>assword&nbsp;&nbsp;:&nbsp;&nbsp;<input accesskey="P" class=panel name=pwd type=password>&nbsp;&nbsp;<input type=hidden name="type" value="login"><button type=submit accesskey="S"><u>S</u>ign In</button>')
document.write('&nbsp;&nbsp;&nbsp;&nbsp;<a href="ForgotPassword.htm">Forgot Password</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="SignupNewUser.htm">New User&nbsp;?</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="ChangePasword.htm">Change Password</a></TD></tR></form></table>')
}
			/*Menu Bar*/

var ie4=document.all    // Microsoft Internet Explorer
var ns4=document.layers // Netscape Navigator
var ns_gecko=document.getElementById&&!document.all //Netscape Gecko,Mozilla..
var ieop=0;
var op_id=0;
var sobj,ch;
var premeir=null;
var scrolltimer=null;
function getRef(id) {
bounce=ie4?document.all[id]:ns_gecko?document.getElementById(id):document.layers[id];
return bounce;
}
 function getSty(id) {
 styleobj=ns4 ? getRef(id) : getRef(id).style;
 return styleobj;
                     }
 function getOffsetPos(which,el,tagName) {
 var pos = 0
 while (el.tagName!=tagName) {
              pos+=el["offset" + which]
              el = el.offsetParent
  } return pos  }	

 function visualize(obj) { document.writeln(obj);}
 function setMenuRequireMents()  {
          this.tblColor="#558755"//"#222234"
          this.bgColor="#558755"//"#223354"
          this.color="aliceblue"
          this.onColor="ivory"
          this.onBg="#556689"
          this.ofColor=this.color
          this.ofBg=this.bgColor
          this.fontFamily="Ms Sans Serif"
          this.fontSize="10px"
          this.fontWeight="normal"
          this.width=165
          this.height=50
          this.Cursor=document.all?"hand":"pointer"; 
          this.Padding="15px"
          this.lineHeight="12pt"
          this.Appearance=ie4?"filter:progid.DXImageTransform.Microsoft.alpha(Opacity=80)":"-moz-opacity:0.8"
          this.textAlign="justify"
           
         }
 cb=new setMenuRequireMents();

 function resolve(ns,dom) { return (ns4?ns:dom);}
 function handle() {getSty('nsmenu').visibility=ns4?"hide":"hidden";}
 function Menu() {javascript:void(0)} 
 function getColor() {
 clrs=new Array("Red","Blue","Green","Maroon","Purple","sienna","black","navy","teal")
 retc=Math.round(Math.random()*clrs.length-1); return clrs[retc]; }

 function loca(urls,tgt) { 
 if(urls=="javascript:void(0)") return;
 else { if(tgt=="New") open(urls);
 else ie4?navigate(urls):location.replace(urls);
 hidemenu();
 }
 }

 function Menu() {
                 this.length=Menu.arguments.length
                 for (var i=0;i<this.length;i++)
                       this[i]=Menu.arguments[i]
                 return this;
                 }

 var newMenu=new Object();
 var menuCount=0;
 var samsungMenu
 function insertMenuList(menuname,item,menuAction) {
 if(!newMenu[menuname]) {
              samsungMenu=new Object();
                        samsungMenu.name=menuname;
                        samsungMenu.num=menuCount++;
                        samsungMenu.items=new Array();
                        samsungMenu.actions=new Array();
              newMenu[menuname]=samsungMenu;
                        }      
 if(!ns4) { 
      newMenu[menuname].items[newMenu[menuname].items.length]=item;
      newMenu[menuname].actions[newMenu[menuname].actions.length]=menuAction;              
      } else {
      newMenu[menuname].items.push(item);
      newMenu[menuname].actions.push(menuAction);
             }
  }

 function makeDiv(title) {
 var tagg=(ns4) ? "Layer" : "Div"
 var ind=(ns4 || ns_gecko) ? "<b>&raquo;</b>" : "4";
 var cls=( !ns4 ) ? "mores" : "pos"
 sty=( !ns4 ) ? " style='top:1pt;font-family:webdings;font-size:12px;float:right'" : ""
 indicator="<"+tagg+" class="+cls+sty+" title='Next'>"+ind+"</"+tagg+">"
 return (indicator+"<"+tagg+" class=manc position=relative onmouseover=msap(event,'"+title+"',this); onmouseout=getpos(event); title='"+title+"'>"+title+"</"+tagg+">")
 }
 function genMenu2(Caption,urls) {
 var secr=ns4?"A":"Div"
 return("<"+secr+" class=manc href='"+urls+"' onmouseover=handles();  onmouseout='return false' title='"+Caption+"'>"+Caption+"</"+secr+">")
 }

 function hidemenu() {
	 getSty('nsmenu').display=getSty('msap2').display=ns4?"hide":"none"
 }

 function delayhide() {
 setStatus();
 hf=setTimeout('hidemenu()',500);
 ieop=0;
 }

 function clearHide() { if (window.hf) clearTimeout(hf);}

 function incropacity() {
  if(ieop<=100) {
                ieop+=7;
				getSty('msap2')[ie4?"filter":"MozOpacity"]=ie4?"alpha(style=1,startX="+(ieop-45)+")":ieop/100;
                op_id=setTimeout('incropacity()', 1);
                } 
				}
 function setStatus() {
 if(window.scrolltimer) clearTimeout(scrolltimer);
 status=ie4?"Done":"Document: Done"; return true;}

 function scrollMenu(temp) {var spc=" "
 for(k=0;k<30;k++) spc+="  "
 sobj=spc+temp.substring(1)+" Menu "+spc
 scrolltimer=setInterval("scrollnow()",10); return true;}

 function scrollnow() {
 ch=sobj.charAt(0); sobj=sobj.substring(1,sobj.length);
 sobj+=ch; status=sobj; }

 function highlightmenu(e,state){
 if (ie4) source_el=event.srcElement
 else if (ns_gecko) source_el=e.target
 if (source_el.className=="menuitem"){
 source_el.style.backgroundColor=(state=="on")?cb.onBg:cb.ofBg;
 source_el.style.color=(state=="on")?cb.onColor:cb.ofColor }
 else{ while(source_el.tagName!="TABLE"){
       source_el=ns_gecko? source_el.parentNode : source_el.parentElement
        if (source_el.className=="menuitem"){
        source_el.style.backgroundColor=(state=="on")?cb.onBg:cb.ofBg;
        source_el.style.color=(state=="on")?cb.onColor:cb.ofColor;}
                             } //for while
                         } //for else
                      } //for Function
function rollout() {
obj=arguments[0];
if(getRef('msap2').currentStyle.visibility=="visible") {}
}

 function contains_ns6(a,b) {// check slave is contained by master 
 while(b.parentNode) //a is master,b is slave
       if((b=b.parentNode)==a)  return true;
       return false;}

 function dynamichide(evt) {
 if(ns_gecko && evt.currentTarget!=evt.relatedTarget&&!contains_ns6(evt.currentTarget,evt.relatedTarget))
 setTimeout("hidemenu()",500); }

 function getpos(evt) {
 if (ns4||ns_gecko) return;
 if(ie4 && !getRef('msap2').contains(evt.toElement) && !getRef('nsmenu').contains(evt.toElement))
 setTimeout("hidemenu()",500);
}
 scripttech=makeDiv("Script-Technologies")
 webtech=makeDiv("Web-Technologies")
 aspp=makeDiv("Asp-Tutorials");
 icici=makeDiv("ICICI-Technologies");
 j2ee=makeDiv("Java2EE");
 softwares=makeDiv("Softwares");
 downloads=makeDiv("Downloads");
 php=genMenu("PHP Scripting","http://www.php.net");

 function genMenu(Caption,urls) { var Toret;
 if(!ns4) Toret="<"+cb.LTag+" class=nested    onmouseout='return true' title='"+Caption+"'  oncontextmenu=loca('"+urls+"','New'); onclick=loca('"+urls+"','Self');>"+Caption+"</"+cb.LTag+">"
 else Toret="<"+cb.LTag+" class=menu href='"+urls+"' onmouseover='handle();return true;' onmouseout='return true'><font color='"+cb.color+"'>"+Caption+"</font></"+cb.LTag+">"
 return(Toret) }

 var mearray=new Array("Mailing","Freeweb","Search","Technology","Banking","Companies","Careers")
 insertMenuList("Mailing","Yahoo Mail","http://www.mail.yahoo.com")
 insertMenuList("Mailing","Rediff Mail","http://www.rediffmail.com")
 insertMenuList("Mailing","Hot Mail","http://www.hotmail.com")

 insertMenuList("Freeweb","Yahoo Geo Cities","http://www.geocities.yahoo.com")
 insertMenuList("Freeweb","Fortune City","http://www.fortunecity.com")
 insertMenuList("Freeweb","Angelfire","http://www.angelfire.com")
 insertMenuList("Freeweb","Lycos Tripod","http://www.tripod.com")
 insertMenuList("Freeweb","Spider City","http://www.spidercity.com")
 insertMenuList("Freeweb","Lycos Network","http://www.angelfire.com")

 insertMenuList("Search","Info Seek Terminal","http://www.infoseek.com")
 insertMenuList("Search","Lycos Network","http://www.lycos.com")
 insertMenuList("Search","Google Engine","http://www.google.com")
 insertMenuList("Search","Yahoo Search","http://www.yahoo.com")
 insertMenuList("Search","Search","http://www.search.com")
 insertMenuList("Search","Altavista","http://www.altavista.com")
 insertMenuList("Search","Web Crawler","http://www.webcrawler.com")

 insertMenuList("Careers","Jobs Ahead","http://www.jobsahead.com")
 insertMenuList("Careers","Timesjobs","http://www.timesjobs.com")
 insertMenuList("Careers","Naukri Dot Com","http://www.naukri.com")
 insertMenuList("Careers","Part Time Jobs","http://www.indianparttimejobs.com")
 insertMenuList("Careers","Ciol Jobs","http://www.cioljobs.com")
 insertMenuList("Careers","Freshers World","http://www.freshersworld.com")

 insertMenuList("Banking","Obc-India","http://www.obcindia.com")
 insertMenuList("Banking","Ing Vysya","http://www.ingvysyabank.com")
 insertMenuList("Banking","Unit Trust","http://www.utibank.com")
 insertMenuList("Banking","Life Insurance","http://www.licinida.com")
 insertMenuList("Banking","Om Kotak","http://www.kotak.com")

 insertMenuList("Companies","Eenadu","http://www.careers.com")
 insertMenuList("Companies","Microsoft","http://www.microsoft.com/india/careers")
 insertMenuList("Companies","ICICI","http://www.icicicareers.com")
 insertMenuList("Companies","World wide web3C","http://www.w3ccareers.com")
 insertMenuList("Companies","CGI India","http://www.cgiindiacareers.com")
 insertMenuList("Companies","CNBC Asia","http://www.cnbcasia.com")
 insertMenuList("Companies","Wipro","http://careers.wipro.com")
 insertMenuList("Companies","Infosys","http://www.infosys.com/careers")
 insertMenuList("Companies","GE Capital","http://careers.gecapitalindia.com")
 insertMenuList("Companies","Tata Consultancy","http://www.tcs.com")
 insertMenuList("Companies","Satyam Computers","http://www.satyam.com")
 insertMenuList("Companies","Visual Soft","http://www.visualsoft-tech.com")

 insertMenuList("Script-Technologies","Inside Dhtml","http://www.insidedhtml.com/constsets/menus/menubar.asp")
 insertMenuList("Script-Technologies","W3C Schools","http://www.w3cschools.com")
 insertMenuList("Script-Technologies","Secret Plus Menus","http://www.secretplus.com")
 insertMenuList("Script-Technologies","VML For Explorer","http://www.garybeene.com/vml/vml-sum.htm")
 insertMenuList("Script-Technologies","Pie Menus","http://www.piemenu.com/JavaScriptPieMenus.html")
 insertMenuList("Script-Technologies","Geckonnection","http://www.geckonnection.com") 
 insertMenuList("Script-Technologies","Gecko Effects","http://www.mozilla.org/docs/dom/samples")
 insertMenuList("Script-Technologies","DHTML Menus","http://dhtml-menu.com/menu-demos/demo497.html");
 insertMenuList("Script-Technologies","Gecko Development","http://cgi.din.or.jp/~hagi3/JavaScript/Mozilla/SampleList.cgi?fmt=html")
 insertMenuList("Script-Technologies","Mozilla","http://www.mozilla.org")
 insertMenuList("Script-Technologies","Netscape Gecko","http://devedge.netscape.com")
 insertMenuList("Web-Technologies","Dhtml Editor","http://www.devguru.com/features/tutorials/wysiwyg/wysiwyg4.html")
 insertMenuList("Web-Technologies","Kevinroth","http://www.kevinroth.com/rte/multi.htm")
 insertMenuList("Web-Technologies","Dyanmic Drive","http://www.dynamicdrive.com")
 insertMenuList("Web-Technologies","Quirks Mode","http://www.quirksmode.org/js/dhtmlnavi.html")
 insertMenuList("Web-Technologies","Java Script","http://javascript.internet.com")
 insertMenuList("Web-Technologies","JScript Easy","http://www.jsmadeeasy.com")
 insertMenuList("Web-Technologies","JS-Extension","http://www.js-x.com")
 insertMenuList("Web-Technologies","Dom Events","http://www.w3.org/tr/dom-level-2-events") 
 insertMenuList("Web-Technologies","MSDN Dom","http://msdn.microsoft.com/library/default.asp?url=/workshop/author/dhtml/reference/methods/showmodaldialog.asp");
 insertMenuList("Web-Technologies","JSCript Oreilly","http://examples.oreilly.de/english_examples/jscript2")
 insertMenuList("Web-Technologies","Lib Daemon","http://lib.daemon.am/Books/JavaScript")
 
 insertMenuList("Asp-Tutorials","ASP Tutorials","http://www.developeriq.com/asptutorials/examples.php3")
 insertMenuList("Asp-Tutorials","Emporium","http://www.aspemporium.com/aspemporium/index.asp")
 insertMenuList("Asp-Tutorials","Mastering Asp","http://www.asp101.com")
 insertMenuList("Asp-Tutorials","Action Jackson","http://www.actionjackson.com/asp/examples.asp");
 insertMenuList("Asp-Tutorials","Bitshop Hosting","http://www.bitshop.com");
 insertMenuList("Asp-Tutorials","Datareturn Hosting","http://www.datareturn.com");
 insertMenuList("Asp-Tutorials","Eweb Circle Hosting","http://www.ewebcircle.com.au");
 insertMenuList("Asp-Tutorials","Reference","http://www.activeserverpages.com");
 insertMenuList("Asp-Tutorials","Asp wire ","http://www.aspwire.com");
 insertMenuList("Asp-Tutorials","Visual Basic Wire","http://www.vbwire.com");
 insertMenuList("Asp-Tutorials","Asp Site","http://www.aspsite.com");
 insertMenuList("Asp-Tutorials","4GuysFromRolla","http://www.4GuysFromRolla.com");
 insertMenuList("Asp-Tutorials","VB Forums","http://www.vbforums.com");
 insertMenuList("Asp-Tutorials","15 Seconds","http://www.15seconds.com");
 insertMenuList("Asp-Tutorials","Swynk ASP","http://www.swynk.com");

 insertMenuList("ICICI-Technologies","Banking","http://www.icicibank.com")
 insertMenuList("ICICI-Technologies","Careers","http://www.icicicareers.com")
 insertMenuList("ICICI-Technologies","ICICI Lombard ","http://www.icicilombard.com")
 insertMenuList("ICICI-Technologies","Infotech","http://www.icici-infotech.com")

 insertMenuList("Java2EE","Java Tutorials","http://java.sun.com/docs/books/tutorial/index.html")
 insertMenuList("Java2EE","JFC Swings","http://java.sun.com/docs/books/tutorial/uiswing/index.html")
 insertMenuList("Java2EE","Java Reg.Expressions","http://java.sun.com/docs/books/tutorial/extra/regex/pattern.html")
 
 insertMenuList("Softwares","Sun Microsystems","http://java.sun.com") 
 insertMenuList("Softwares","Microsoft","http://msdn.microsoft.com");
 insertMenuList("Softwares","RFC-Editor","http://www.rfc-editor.org") 

 insertMenuList("Downloads","Deitel","http://www.deitel.com")
 insertMenuList("Downloads","Wrox","http://www.wrox.com")
 insertMenuList("Downloads","CodeProject","http://www.codeproject.com")
 insertMenuList("Downloads","Programmers Heaven","http://www.programmersheaven.com")

 insertMenuList("Technology",scripttech,"javascript:void(0)")
 insertMenuList("Technology",webtech,"javascript:void(0)")
 insertMenuList("Technology",aspp,"javascript:void(0)")
 insertMenuList("Technology",icici,"javascript:void(0)")
 insertMenuList("Technology",php,"javascript:void(0)") 
 insertMenuList("Technology",j2ee,"javascript:void(0)")
 insertMenuList("Technology",softwares,"javascript:void(0)")
 insertMenuList("Technology",downloads,"javascript:void(0)") 

 insertMenuList("Multimedia","Tutorial Outpost","http://www.tutorialoutpost.com") 
 insertMenuList("Multimedia","Action Script","http://www.actionscript-toolbox.com") 
 insertMenuList("Multimedia","Flashiness","http://www.flashiness.com") 
 insertMenuList("Multimedia","Flashkit","http://www.flashkit.com") 

 insertMenuList("Markets","BSE Trading","http://www.bseindia.com")
 insertMenuList("Markets","Finvarsity","http://www.finvarsity.com")
 insertMenuList("Markets","NSE Trading","http://www.nseindia.com")

 insertMenuList("Gallery","Lake World","http://www.lakeworld.com")
 insertMenuList("Gallery","Pinkworld","http://www.pinkworld.com")
 insertMenuList("Gallery","Santa Banta","http://www.santabanta.com")
 insertMenuList("Gallery","Blue Mountain","http://www.bluemountain.com")
 insertMenuList("Gallery","Web Shots","http://www.webshots.com")
 insertMenuList("Gallery","Glamsham","http://www.glamsham.com")
 insertMenuList("Gallery","Hall Mark","http://www.hallmark.com")
 insertMenuList("Gallery","Titanic","http://www.titanic.com")
 
 insertMenuList("Resume","Aanda Resume","http://www.aandaresume.com")
 insertMenuList("Resume","Resume.com","http://www.resumedotcom.com")
 insertMenuList("Resume","Resume Helper","http://www.resume-helper.com")
 insertMenuList("Resume","Cashmap","http://www.cashmap.com")
 insertMenuList("Resume","Online Tips","http://www.online-resume-tips.com")

 insertMenuList("ITParks","Technopark","http://www.technopark.org") 
 insertMenuList("ITParks","Tidelpark","http://www.tidelpark.com")
 insertMenuList("ITParks","Kerala-It","http://www.keralaitmission.org")
 insertMenuList("ITParks","Express India","http://www.expressindia.com")


 aobj="background-color:"+cb.bgColor+";color:"+cb.color+";width:"+cb.width+";font-size:"+cb.fontSize+";font-family:"+cb.fontFamily+";line-height:"+cb.lineHeight+";font-weight:"+cb.fontWeight
 aobj+=ns4?"":";cursor:"+cb.Cursor+";padding-left:"+cb.Padding;
 visualize("<style>\n #nsmenu,#msap2 {position:absolute;width:"+cb.width+"}\n");
 visualize(".menuitem {"+aobj+"}\n</style>\n\n");

 function msap(evt,menus,topp){
 themenuoffsetX=(ie4)?document.body.scrollLeft:window.pageXOffset // themenuoffsetX+evt.clientX-evt.offsetX-3 
 themenuoffsetY=(ie4)?document.body.scrollTop:window.pageYOffset //themenuoffsetY+evt.clientY-evt.offsetY-3
 mtable=buildPat(newMenu[menus])
 Item=(ie4)?evt.srcElement.innerText:evt.target.text;
 cftag=resolve("Layer","Div");cname=resolve("lh","mydiv");
 var ldiv="<"+cftag+" class="+cname+" onmouseover='clearHide()' onmouseout='delayhide()'>"+mtable+"</"+cftag+">";
 cur=(topp==null)?getRef('msap2'):getRef('nsmenu')
 if(topp==null) { clearHide(); 
				  if(ie4) { setStatus(); scrollMenu(Item);} 
				  incropacity();				   
				}
 reqtop=((topp!=null && !ns4) ? getRef('msap2').offsetTop+getRef('msap2').offsetParent.offsetTop-3+getOffsetPos("Top",topp,"TABLE"):"") 
 mLeft=ns4 ? evt.pageX-evt.layerX-4 :((topp==null)?document.all?getRef(menus).getClientRects()[0].left-2:getRef(menus).offsetLeft-1:"") //themenuoffsetX+evt.clientX-evt.offsetX-3
 mTop=ns4 ? evt.pageY-evt.layerY+15 : getRef('tab1').offsetTop+getRef('tab1').offsetHeight-1;
 nesTop=ns4 ? evt.pageY-evt.layerY-6 :reqtop;
 nesLeft=ns4 ? parseInt(getRef('msap2').clip.width+getRef('msap2').left-4):getRef('msap2').offsetLeft+getRef('msap2').offsetWidth-1
 cur.innerHTML="";
 mnTop=(topp==null)?mTop:nesTop
 mnLeft=(topp==null)?mLeft:nesLeft;
 if(ie4) cur.insertAdjacentHTML("afterBegin",mtable);
 else if(ns_gecko) cur.innerHTML=ldiv
 else { with(cur) {
				   document.write(ldiv);document.close();
                   clip.width=cb.width;
				   }
     }
 with(ns4?cur:cur.style) {left=mnLeft;top=mnTop;
                          display=ns4 ? "show" :"block";}
 } 

 function pointer() {
 var no=ie4?6:'<B>%</B>'
 return "<span style='padding:0px;float:right;top:-1pt;font-family:webdings;font-size:12px'>"+no+"</span>"
 } 
 point=pointer();
 function buildMenu() {
 ref=resolve("A","Div")
 var eff=ns4?'<ilayer><layer bgColor='+cb.bgColor+' visibility=show clip=-1,-1,87,15  onmouseover="bgColor=cb.onBg" onmouseout="bgColor=cb.ofBg" >':"";
 var endd=ns4?"</layer></ilayer>":"";
 visualize("<layer  id=mcont clip=0,0,631,19 left=3  bgcolor=black><TABLE bgcolor="+cb.tblColor+" align=center CellPadding=1 CellSpacing=0 id=tab1 OnMouseOut=highlightmenu(event,'off'); OnMouseOver=highlightmenu(event,'on'); width='780' border=0>");
 visualize("<TR id=tabs1>");
 for(var item in mearray) 
 visualize('<TD width=10% onselectstart="return false" id='+mearray[item]+' class=menuitem title="'+mearray[item]+'" oncontextmenu="return false">'+eff+'<'+ref+' class=menu href="javascript:Menu()" onfocus=this.blur(); onmouseover=msap(event,"'+mearray[item]+'",null); onmouseout=delayhide();>'+point+mearray[item]+'</'+ref+'>'+endd+'</td>');
 visualize("</TR></TBODY></TABLE></layer>");
 if(ie4)  pointer();
 }

function buildPat(menus) {
 var nss=ns4?"<ilayer><layer clip=-1,-2,125,16  bgColor="+cb.bgColor+" onmouseover='bgColor=cb.onBg' onmouseout='bgColor=cb.ofBg'>":""
 if(ns4) applied="<table  cellspacing=1 border=0 cellpadding=5>"
 else applied="<table cellspacing=0 cellpadding=2 onMouseover=highlightmenu(event,'on'); onMouseout=highlightmenu(event,'off'); oncontextmenu='return false'>"
 for(var Menu in menus.items) { 
        loc=menus.actions[Menu];fresh=menus.items[Menu];
        rfresh=(fresh.indexOf("<")!=-1)?"Right Click To Open In New Window":fresh;
        if(!ns4) applied+='<tr><td nowrap onselectstart="return false" title="'+rfresh+'" class=menuitem onclick=loca("'+loc+'","self"); oncontextmenu=loca("'+loc+'","New"); onclick=loca("'+loc+'","Self");>'+fresh+'</tr>' 
        else applied+='<tr><td nowrap class=menuitem>'+nss+'<a class=manc onmouseover="return true;" onmouseout="return true;" href="'+loc+'">&nbsp;&nbsp;&nbsp;&nbsp;'+fresh+'</layer></ilayer></td></tr>' 
        } applied+="</table>" 
 return applied; }
 visualize("<SPAN id=msap2 onmouseout=getpos(event); onmouseover=clearHide();></SPAN>");
 visualize("<SPAN id=nsmenu onmouseout=getpos(event); onmouseover=clearHide();></SPAN>");

 
				/*Effects*/
var spcss="&nbsp;"
for(var tits=0;tits<150;tits++) spcss+="&nbsp;&nbsp;"
document.write("<title>&nbsp;&nbsp;::&nbsp;&nbsp;Sathya Technologies&nbsp;&nbsp;::"+spcss+"</title>");
document.onselectstart=new Function("return false");
document.onselectstart=function() {
if(!document.all) return false;
else {
	      type=event.srcElement.type;
           if(type=="text" || type=="password" ||type=="textarea") return true;
		   else return false;}
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
cursor=document.all?"hand":"pointer";
onload=function() {
try{
	with(document.body.style) {
	if(top==self) {
	overflowX="hidden";
	overflowY="scroll";
	}
	margin="0px";
	background="linen";
	backgroundRepeat="no-repeat";
	backgroundAttachment="fixed"
	backgroundPosition="-250 -100";
	}
        var target=document.getElementById('tabber').rows[0];
	for(var r=0;r<target.cells.length;r++) {
	target.cells[r].onmouseover=function() {this.style["background"]="royalblue";this.style.color="white";status=this.innerHTML}
	target.cells[r].onmouseout=function() {this.style["background"]="whitesmoke";this.style.color="lightslategray";retrieve(target);}
	target.cells[r].onclick=function() {location=this.getAttribute('url');}
		}
	retrieve(target);
	}catch(e) {status=e.description}
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
	but[t].style['cursor']=cursor;
    but[t].onmouseover=function() {this.style["background"]="teal";}
    but[t].onmouseout=function() {this.style["background"]="brown";}}
	disableAnchor();
}
function disableAnchor() {
	var lks=document.getElementsByTagName("A");
	for(lk=0;lk<lks.length;lk++) {
   lks[lk].onmouseover=function() {status=this.innerHTML;return true;}
   lks[lk].onmouseout=function() {status="Done";return true;}
   lks[lk].onfocus=function() {status="Done";this.blur();return true;}
	}
}
var s=0;
var i=0;
var m=40;
var d=2000;
var msg=new Array();
msg[0]="At Sathya !!!"
msg[1]="J2EE"
msg[2]="J2ME"
msg[3]=".Net"
msg[4]="Testing Tools"
msg[5]="Oracle"
msg[6]="SQL Server"
msg[7]="Web Services"
function changeMsg(){
	if(document.getElementById){
		if (i>=msg.length){
			i=0;
		}
		txt=document.getElementById("cont");
		txt.innerHTML=msg[i]; s=0;zoomTxt();i++;
		setTimeout("changeMsg()", d);
	}
}
function zoomTxt(){
	if(s<m){
		txt.style.fontSize = s;s+=5;
		setTimeout("zoomTxt()", 30);
	}
}
document.write('<LINK href="./content.cdf" rel=stylesheet>');
var bleft=screen.availWidth/100;
var btop=screen.availHeight-100;

				/*VBScript*/

if(document.all) {
document.write("<SCRIPT LANGUAGE=VBS>");
document.write("Function info(msg)");
document.write("msgbox msg,vbokonly or vbinformation,\":: Sathya Technologies ::\"");
document.write("End Function");
document.write("</SCRIP"+"T>");
}


                /*Fading Progress Bar*/
var w3c=(document.getElementById)?true:false;
var ie=(document.all)?true:false;
var N=-1;
//var xyz = createBar( total_width, total_height,background_color,border_width, border_color, block_color, scroll_speed, block_count, scroll_count, action_to_perform_after_scrolled_n_times)*/
function createBar(w,h,bgc,brdW,brdC,blkC,speed,blocks,count,action){
if(ie||w3c){
var t='<div id="_xpbar'+(++N)+'" style="visibility:visible; position:relative; overflow:hidden; width:'+w+'px; height:'+h+'px; background-color:'+bgc+'; border-color:'+brdC+'; border-width:'+brdW+'px; border-style:solid; font-size:1px;">';
t+='<span id="blocks'+N+'" style="left:-'+(h*2+1)+'px; position:absolute; font-size:1px">';
for(i=0;i<blocks;i++){
t+='<span style="background-color:'+blkC+'; left:-'+((h*i)+i)+'px; font-size:1px; position:absolute; width:'+h+'px; height:'+h+'px; '
t+=(ie)?'filter:alpha(opacity='+(100-i*(100/blocks))+')':'-Moz-opacity:'+((100-i*(100/blocks))/100);
t+='"></span>';
}
t+='</span></div>';
document.write(t);
var bA=(ie)?document.all['blocks'+N]:document.getElementById('blocks'+N);
bA.bar=(ie)?document.all['_xpbar'+N]:document.getElementById('_xpbar'+N);
bA.blocks=blocks;
bA.N=N;
bA.w=w;
bA.h=h;
bA.speed=speed;
bA.ctr=0;
bA.count=count;
bA.action=action;
bA.togglePause=togglePause;
bA.showBar=function(){
this.bar.style.visibility="visible";
}
bA.hideBar=function(){
this.bar.style.visibility="hidden";
}
bA.tid=setInterval('startBar('+N+')',speed);
return bA;
}}

function startBar(bn){
var t=(ie)?document.all['blocks'+bn]:document.getElementById('blocks'+bn);
if(parseInt(t.style.left)+t.h+1-(t.blocks*t.h+t.blocks)>t.w){
t.style.left=-(t.h*2+1)+'px';
t.ctr++;
if(t.ctr>=t.count){
eval(t.action);
t.ctr=0;
}}else t.style.left=(parseInt(t.style.left)+t.h+1)+'px';
}

function togglePause(){
if(this.tid==0){
this.tid=setInterval('startBar('+this.N+')',this.speed);
}else{
clearInterval(this.tid);
this.tid=0;
}}

function togglePause(){
if(this.tid==0){
this.tid=setInterval('startBar('+this.N+')',this.speed);
}else{
clearInterval(this.tid);
this.tid=0;
}}

				/*Validation*/
function isEmptyValue() {
var target=arguments[0];
	if(target.value.length==0 || (target.value.length>0 && target.value.charAt(0)==' ')) {
	var msg="Please Enter All Values With Out Spaces";
	document.all?info(msg):alert(msg);
	target.focus();
	return false;
	}
return true;
}
function leftTrim() {
var string=arguments[0];
if(string.length==0) return string;
while(string.charAt(0)==' ') string=string.substring(1);
return string;
}

function rightTrim() {
var string=arguments[0];
if(string.length==0) return string;
while(string.charAt(string.length-1)==' ') string=string.substring(string.length-2);
return string;
}

function formValidate(frm) {
for(var f=0;f<frm.elements.length;f++) {
	if(frm[f].type=="text" || frm[f].type=="textarea" || frm[f].type=="password") {
		var check=isEmptyValue(frm[f]);
		status=check;
		if(!check) return false;
		}
	}  return true;
}
function changePassword() {
var frm=arguments[0];
var check=formValidate(frm);
if(!check) return false;
if(frm.pass.value==frm.repass.value) return true;
else  {
       var wmsg="Confirm Password Not Mathced With The Password U Supplied"
       document.all?info(wmsg):alert(wmsg);
       frm.repass.focus();
       return false;}
}
var buffer="";
function setHeader() {
buffer+='<BODY bgColor=#ffffff bgProperties=fixed leftMargin=0 topMargin=0>'
buffer+='<TABLE align=center cellPadding=0 cellSpacing=0 width=780><form action="Probe" onsubmit="return formValidate(this)">'
buffer+='<TBODY><TR><TD rowSpan=2 valign=top><IMG border=0 height=90 src="./images/sathya.gif"  width=500></TD>'
buffer+='<TD align=right bgColor=royalblue noWrap style="PADDING-LEFT: 10px; PADDING-RIGHT: 12px" width=280  valign=top><A class=header href="./index.htm">Home</A>&nbsp;&nbsp;<FONT  color=white><B>|</B></FONT>&nbsp;&nbsp; <A class=header href="./aboutus.htm">About Us</A>&nbsp;&nbsp;<FONT color=white><B>|</B></FONT>&nbsp;&nbsp; <A class=header href="./contactus.htm">Contact</A>&nbsp;&nbsp;<FONT color=white><B>|</B></FONT>&nbsp;&nbsp; <A class=header href="./sitemap.htm">Site map</A> </TD></TR>'
buffer+='<TR><TD align=middle><BR><DIV style="FONT: bold 11px tahoma; LEFT: 0px; POSITION: relative; TOP: -10px"><u>Q</u>uest&nbsp;:&nbsp;<INPUT name=search accesskey="Q">&nbsp;&nbsp;<input type=hidden name=pageno value=0><BUTTON type=submit accesskey="G"><u>G</u>o</BUTTON><BR><BR>'
buffer+='<CENTER><A href="./advanced.htm">Advanced Search</A>&nbsp;&nbsp;|&nbsp;&nbsp;<A href="./Jobs.jsp">Jobs Search</A></CENTER></DIV>'
//var bar1= createBar(280,17,'white',1,'black','green',8,15,3,"");
buffer+='</TD></TR></TBODY></form></TABLE>'
document.write(buffer);
}
function setFooter() {
with(document) {
	write("</TABLE><center><BR><BR>");
	write("<button style='width:140px;height:22px;' onclick='parent.location=\"javascript:history.back()\"' accesskey=\"C\"><u>C</u>ontinue Back</button>&nbsp;&nbsp;&nbsp;");
	write("<button style='width:140px;height:22px;' onclick='parent.location=\"./Home.jsp\"' accesskey=\"N\"><u>N</u>avigate Home</button>");
	write("<BR><BR><BR></center>");
	write("<Table width=780 align=center cellspacing=0 cellpadding=1 border=0>");
	write("<TR><Th align=center><font color=gray>For More Details Mail to me at&nbsp;&nbsp;<a href=\"compose.htm?ghkishore@hotmail.com\">ghkishore@hotmail.com</font></a>&nbsp;&nbsp;or meet me at<bR><bR>");
	write("<a href=\"http://ghvishnu.tripod.com/hari.htm\">http://ghvishnu.tripod.com/hari.htm</a></th></tr>");
	write("</TABLE><btody></body></html>");}
}
/*************************************************Commomn.cdf******************************************/
function disableAnchor() {
var anc=document.getElementsByTagName("A");
for(a=0;a<anc.length;a++) {
anc[a].onmouseover=function() { status="Done";return true;}
anc[a].onmouseout=function() { status="Done";return true;}
anc[a].onfocus=function() { this.blur();status="Done";return true;}
anc[a].oncontextmenu=function() { 
				redirect=this.href;
				if(redirect=="javascript:void(0)") return;
				cfm=confirm("Would You Like to Open this Link In New Window");
				if(cfm) open(redirect);
				else {}
				return false;
				}
	}
}

function setProperty() {
	var ns4=document.layers;
	var gecko=document.getElementById&&!document.all;
	if(ns4) return;
	var idn=arguments[0];
	try{
	with(idn) {
		style["background"]="royalblue";
		style["color"]="aliceblue";
		setAttribute("url","javascript:void(0)");
		oncontextmenu=new Function("return false");
		}
	}catch(Exception) { status=Exception.description}
}
function setBg() {
var cells=document.getElementById('advtab').getElementsByTagName("TR");
var ud=true;
for(var c=0;c<cells.length;c++) {
	ud=!ud;
	bgc=ud?"cadetblue":"whitesmoke";
	color=ud?"whitesmoke":"teal";
 	cells[c].style.cssText="background:"+bgc+";font:bold 12px tahoma;";
	cells[c].firstChild.style["cssText"]="text-align:right;padding-right:10px;color:"+color;
	if(cells[c].childNodes.length==1)	cells[c].lastChild.style["cssText"]="color:"+color+";text-align:right";
	else cells[c].lastChild.style["cssText"]="color:"+color

	}
}
function retrieve(target) {
try{
	var path=""+location
	var stat="";
	if(location.search!=null && location.search.length>0) 
	stat=path.substring(path.lastIndexOf("/")+1,path.indexOf("?"));
	else stat=path.substring(path.lastIndexOf("/")+1);
	switch(stat.toLowerCase()) {
	case "":
	case "search":
	case "home.jsp":	setProperty(target.cells[0]);
				break;
	case "advancedsearch":
	case "advanced.jsp":	setProperty(target.cells[1]);
				break;	
	case "productsearch.jsp":
	case "products.jsp":	setProperty(target.cells[2]);
				break;	
	case "jobsearch.jsp":
	case "jobs.jsp":	setProperty(target.cells[3]);
				break;	
	case "yellowpagesearch.jsp":
	case "yellowpages.jsp":	setProperty(target.cells[4]);
				break;	
	
	case "postresume.jsp":	setProperty(target.cells[5]);
				break;	
	case "signin.jsp":	setProperty(target.cells[6]);
				break;	
	default:		setProperty(target.cells[0]);
				break;
		}
	}catch(e) {status=e.description;}
}

/*************************************Left Tab**************************************/
var curs=document.all?"hand":"pointer";
var about=["Sign In","Topic","Advanced","Products","Jobs","Yellow Pages","Post Resume"];
var lurls=["Signin.jsp","Home.jsp","Advanced.jsp","Products.jsp","Jobs.jsp","YellowPages.jsp","PostResume.jsp"];
function leftTab() {
document.write("<TABLE ALIGN=left width=120 cellspacing=3 cellpadding=5><TR><TD align=center valign=top>");
var ht=document.all?30:15;
style="style='font:bold 11px tahoma;filter:alpha(style=3);width:120px;height:"+ht+"px;padding:8px;text-align:center;background:#222234;-moz-outline:5px dashed gray;-moz-border-radius:20px;color:aliceblue;cursor:"+curs+"'"
for(var i=0;i<about.length;i++) document.write("<div "+style+" ondblclick='return false' onmouseover=rollOver(this); onmouseout=rollOut(this); onmousedown=mouseDown(this); onmouseup=mouseUp(this); onclick='location=\""+lurls[i]+"\"'>"+about[i]+"</div><BR>");
document.write("</TABLE>");
}
function rollOver() {
obj=arguments[0];
obj.style["background"]="#435434";
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

