/*****************************************************************************
Default browsercheck - Leave this one
******************************************************************************/
function lib_bwcheck(){ //Browsercheck (needed)
        this.ver=navigator.appVersion; this.agent=navigator.userAgent
        this.dom=document.getElementById?1:0
        this.ie5=(this.ver.indexOf("MSIE 5")>-1 && this.dom)?1:0;
        this.ie6=(this.ver.indexOf("MSIE 6")>-1 && this.dom)?1:0;
        this.ie7=(this.ver.indexOf("MSIE 7")>-1 && this.dom)?1:0;
        this.ie4=(document.all && !this.dom)?1:0;
        this.ie=this.ie4||this.ie5||this.ie6
        this.mac=this.agent.indexOf("Mac")>-1
        this.opera5=this.agent.indexOf("Opera 5")>-1
        this.ns6=(this.dom && parseInt(this.ver) >= 5) ?1:0;
        this.ns4=(document.layers && !this.dom)?1:0;
        this.bw=(this.ie7 ||this.ie6 || this.ie5 || this.ie4 || this.ns4 || this.ns6 || this.opera5 || this.dom)
        return this
}
var bw=new lib_bwcheck() //Making browsercheck object

var mDebugging=2 //General debugging variable. Set to 0 for no debugging, 1 for alerts or 2 for status debugging.

oCMenu=new makeCoolMenu("oCMenu") //Making the menu object. Argument: menuname
oCMenu.useframes=0 //Do you want to use the menus as coolframemenu or not? (in frames or not) - Value: 0 || 1
oCMenu.frame="frmMain" //The name of your main frame (where the menus should appear). Leave empty if you're not using frames - Value: "main_frame_name"
oCMenu.useclick=0 //If you want the menu to be activated and deactivated onclick only set this to 1. - Value: 0 || 1

/*If you set this to 1 you will get a "hand" cursor when moving over the links in NS4.
NOTE: This does not apply to the submenus if the menu is used in frames due some mayor problems with NS4*/
oCMenu.useNS4links=1

//After adding the "hover effect" for netscape as well, all styles are lost. But if you want padding add it here.
oCMenu.NS4padding=2

//If you have select boxes close to your menu the menu will check for that and hide them if they are in the way of the menu.
//This feature does unfortunatly not work in NS4!
oCMenu.checkselect=0

/*If you choose to have this code inside a linked js, or if your using frames it's important to set these variables.
This will help you get your links to link to the right place even if your files are in different folders.
The offlineUrl variable is the actual path to the directory where you js file are locally.
This is just so you can test it without uploading. Remember to start it with file:/// and only use slashes, no backward slashes!
Also remember to end with a slash                                                                                                 */
oCMenu.offlineUrl="file://D:/Tomcat 5.0/webapps/RealtorsPro" //Value: "path_to_menu_file_offline/"
//The onlineUrl variable is the online path to your script. Place in the full path to where your js file is. Remember to end with a slash.
oCMenu.onlineUrl="http://localhost:8000/RealtorsPro/" //Value: "path_to_menu_file_online/"

oCMenu.pagecheck=1 //Do you want the menu to check whether any of the subitems are out of the bouderies of the page and move them in again (this is not perfect but it hould work) - Value: 0 || 1
oCMenu.checkscroll=1 //Do you want the menu to check whether the page have scrolled or not? For frames you should always set this to 1. You can set this to 2 if you want this feature only on explorer since netscape doesn't support the window.onscroll this will make netscape slower (only if not using frames) - Value: 0 || 1 || 2
oCMenu.resizecheck=1 //Do you want the page to reload if it's resized (This should be on or the menu will crash in Netscape4) - Value: 0 || 1
oCMenu.wait=1000 //How long to wait before hiding the menu on mouseout. Netscape 6 is a lot slower then Explorer, so to be sure that it works good enough there you should not have this lower then 500 - Value: milliseconds

//Background bar properties
oCMenu.usebar=1 //If you want to use a background-bar for the top items set this on - Value: 1 || 0
oCMenu.barcolor="Navy" //The color of the background bar - Value: "color"
oCMenu.barwidth="100%" //The width of the background bar. Set this to "menu" if you want it to be the same width as the menu. (this will change to match the border if you have one) - Value: px || "%" || "menu"
oCMenu.barheight="menu" //The height of the background bar. Set this to "menu" if you want it to be the same height as the menu. (this will change to match the border if you have one) - Value: px || "%" || "menu"
oCMenu.barx=0 //The left position of the bar. Set this to "menu" if you want it be the same as the left position of the menu. (this will change to match the border if you have one)  - Value: px || "%" || "menu"
oCMenu.bary=0 //The top position of the bar Set this to "menu" if you want it be the same as the top position of the menu. (this will change to match the border if you have one)  - Value: px || "%" || "menu"
oCMenu.barinheritborder=0 //Set this to 1 if you want the bar to have the same border as the top menus - Value: 0 || 1

//Placement properties
oCMenu.rows=1 //This controls whether the top items is supposed to be laid out in rows or columns. Set to 0 for columns and 1 for row - Value 0 || 1
oCMenu.fromleft=20 //This is the left position of the menu. (Only in use if menuplacement below is 0 or aligned) (will change to adapt any borders) - Value: px || "%"
oCMenu.fromtop=0 //This is the left position of the menu. (Only in use if menuplacement below is 0 or aligned) (will change to adapt any borders) - Value: px || "%"
oCMenu.pxbetween=30 //How much space you want between each of the top items. - Value: px || "%"

/*You have several different ways to place the top items.
You can have them right beside eachother (only adding the pxbetween variable)
oCMenu.menuplacement=0

You can have them aligned to one of the sides - This is mostly when not using frames, but can be used in both conditions
Values: (If you get strange results check the fromleft,fromtop and pxbetween variables above)
For menus that are placed in columns (align=left or align=right (se below)) you can align them to the "right" or "center"
For menus that are placed in rows (align=top or align=bottom (se below)) you can align them to the "bottom", "center" or "bottomcenter"
oCMenu.menuplacement="center"

You can also set them directly in pixels: (Remember to have as many array members as you have top items)
oCMenu.menuplacement=new Array(10,200,400,600)

Or you can place in percentage: (remember to use the ' ' around the numbers)


Choose one of those options to get the desired results.

to adjust the horizontal space between mwnu items % || Pixels
oCMenu.menuplacement=new Array('1','150','301','451','601')
*/
oCMenu.menuplacement=new Array('2%','14%','26%','38%','50%','62%','74%','86%','98%')


oCMenu.NS4hover=1
/*
Now we are ready for the properties of each level. For those of that have used the old
coolmenus for coolframemenu I will try and explain how this works like this:
level[0] = top items
level[1] = sub items
level[2] = sub2 items
level[3] = sub3 items and so on....
All menus will inherit the properties, and all properties does only HAVE to be spesifed on the top level.
If a level doesn't have on property spesified it will look for it on the last level that was spesified,
if it still doesn't exist it will get the properties from level[0]

Which means that if you set the background color on level[0] to "black" and doesn't spesify any more levels or doesn't
spesify the background color on the last level you spesified ALL menus will get the color from level[0]

Did that make sense at all? This can be a little hard to understand, look at the different examples on my site
and play with and I am sure you'll get what I mean.
*/

//TOP LEVEL PROPERTIES - ALL OF THESE MUST BE SPESIFIED FOR LEVEL[0]
oCMenu.level[0]=new Array() //Add this for each new level
oCMenu.level[0].width=110 //The default width for each level[0] (top) items. You can override this on each item by spesifying the width when making the item. - Value: px || "%"
oCMenu.level[0].height=25 //The default height for each level[0] (top) items. You can override this on each item by spesifying the height when making the item. - Value: px || "%"
oCMenu.level[0].bgcoloroff="Navy" //The default background color for each level[0] (top) items. You can override this on each item by spesifying the backgroundcolor when making the item. - Value: "color"
oCMenu.level[0].bgcoloron="#336699" //The default "on" background color for each level[0] (top) items. You can override this on each item by spesifying the "on" background color when making the item. - Value: "color"
oCMenu.level[0].textcolor="White" //The default text color for each level[0] (top) items. You can override this on each item by spesifying the text color when making the item. - Value: "color"
oCMenu.level[0].hovercolor="Yellow" //The default "on" text color for each level[0] (top) items. You can override this on each item by spesifying the "on" text color when making the item. - Value: "color"
oCMenu.level[0].style="padding-top:2px; font-family:tahoma,arial,helvetica; font-size:12px;" //The style for all level[0] (top) items. - Value: "style_settings"
oCMenu.level[0].border=0 //The border size for all level[0] (top) items. - Value: px
oCMenu.level[0].bordercolor="red" //The border color for all level[0] (top) items. - Value: "color"
oCMenu.level[0].offsetX=0 //The X offset of the submenus of this item. This does not affect the first submenus, but you need it here so it can be the default value for all levels. - Value: px
oCMenu.level[0].offsetY=-1 //The Y offset of the submenus of this item. This does not affect the first submenus, but you need it here so it can be the default value for all levels. - Value: px
oCMenu.level[0].NS4font="tahoma,arial,helvetica"
oCMenu.level[0].NS4fontSize="2"
                               /*New: Added animation features that can be controlled on each level.*/
oCMenu.level[0].clip=0 //Set this to 1 if you want the submenus of this level to "slide" open in a animated clip effect. - Value: 0 || 1
oCMenu.level[0].clippx=0 //If you have clip spesified you can set how many pixels it will clip each timer in here to control the speed of the animation. - Value: px
oCMenu.level[0].cliptim=0 //This is the speed of the timer for the clip effect. Play with this and the clippx to get the desired speed for the clip effect (be carefull though and try and keep this value as high or possible or you can get problems with NS4). - Value: milliseconds
                          //Filters - This can be used to get some very nice effect like fade, slide, stars and so on. EXPLORER5.5+ ONLY - If you set this to a value it will override the clip on the supported browsers
oCMenu.level[0].filter=0 //VALUE: 0 || "filter specs"

/*And last but not least the align variable.

This spesifies how the submenus of this level comes out.
Values:
"bottom": The sub menus of this level will come out on the top of this item
"top": The sub menus of this level will come out on the bottom of this item
"left": The sub menus of this level will come out on the right of this item
"right": The sub menus of this level will come out on the left of this item

In generally "left" and "right" works best for menus in columns and "top" and "bottom" works best for menus in rows.
But by all means feel free to play with it.

If you have set pagecheck to 1 above this is what the pagecheck will change when reaching the bounderies of the page.
If it reaches the right boundery and it's aligned left it will change the align to right and so on.
*/
oCMenu.level[0].align="bottom" //Value: "top" || "bottom" || "left" || "right"

//EXAMPLE SUB LEVEL[1] PROPERTIES - You have to spesify the properties you want different from LEVEL[0] - If you want all items to look the same just remove this
oCMenu.level[1]=new Array() //Add this for each new level (adding one to the number)
oCMenu.level[1].width=oCMenu.level[0].width-2
oCMenu.level[1].height=22
oCMenu.level[1].style="padding-top:2px; font-family:tahoma, arial,helvetica; font-size:11px;"
oCMenu.level[1].align="bottom"
oCMenu.level[1].offsetX=0// oCMenu.level[1].offsetX=-(oCMenu.level[0].width-2)/2+20
oCMenu.level[1].offsetY=0
oCMenu.level[1].border=1
oCMenu.level[1].bordercolor="#006699"

//EXAMPLE SUB LEVEL[2] PROPERTIES - You have to spesify the properties you want different from LEVEL[1] OR LEVEL[0] - If you want all items to look the same just remove this
oCMenu.level[2]=new Array() //Add this for each new level (adding one to the number)
oCMenu.level[2].width=oCMenu.level[0].width
oCMenu.level[2].height=20
oCMenu.level[2].bgcoloroff="#009999"
oCMenu.level[2].bgcoloron="#0099cc"
oCMenu.level[2].style="padding:2px; font-family:tahoma,arial,helvetica; font-size:10px; font-weight:bold"
oCMenu.level[2].align="bottom"
oCMenu.level[2].offsetX=0
oCMenu.level[2].offsetY=0
oCMenu.level[2].border=1
oCMenu.level[2].bordercolor="#006699"
oCMenu.level[2].NS4font="tahoma,arial,helvetica"
oCMenu.level[2].NS4fontSize="1"

/*Variables for each menu item: (** means that they have to be spesified!)
name: The name of the item. This must be unique for each item. Do not use spaces or strange charachters in this one! **
parent: The name of the menuitem you want this to "connect" to. This will be a submenu of the item that have the name you place in here. ** for all other then the topitems
text: The text you want in the item. ** (except if you use images)
link: The page you want this item to link to.
target: The target window or frame you want the link to go to (Default is same window if you're not using frames, and the mainframe if you're using frames)
width: The width of the element. If not spesified it will get the default width spesified above.
height: The height of the element. If not spesified it will get the default height spesified above.
img1: The "off" image for element if you want to use images.
img2: The image that appears onmouseover if using images.
bgcoloroff: The background color for this item. If not spesified it will get the default background color spesified above.
bgcoloron: The "on" background color for this item. If not spesified it will get the default "on" background color spesified above.
textcolor: The text color for this item. If not spesified it will get the default text color spesified above.
hovercolor: The "on" text color for this item. If not spesified it will get the default "on" text color spesified above. Netscape4 ignores this
onclick: If you want something to happen when the element is clicked (different from going to a link) spesifiy it here.
onmouseover: This will happen when you mouseover the element. Could be status text, another imageswap or whatever.
onmouseout: This will happen when you mouseout the element.

Remember you can have as many levels/sublevels as you want. Just make sure you spesify the correct "parent" for each item.
To set styles for each level see above.

oCMenu.makeMenu('name','parent','<b>Text</b>','link','target frame',width,height,"img1","img2")
*/
oCMenu.makeMenu('top','','<b>&nbsp;Home</b>','Report.jsp','Frame3')

oCMenu.makeMenu('top0','','<b>Plots</b>','','')
    oCMenu.makeMenu('sub00','top0','&nbsp;New Plot&nbsp;','AddPlot2.jsp','Frame3')
    oCMenu.makeMenu('sub01','top0','&nbsp;View','ViewPlot.html','Frame3')
    oCMenu.makeMenu('sub02','top0','&nbsp;Edit','EditPlot2.jsp','Frame3')
oCMenu.makeMenu('top1','','<b>Appartments</b>','','')
    oCMenu.makeMenu('sub10','top1','&nbsp;New Appartment&nbsp;','AddAppartment2.jsp','Frame3')
    oCMenu.makeMenu('sub11','top1','&nbsp;View','ViewAppartments.jsp','Frame3')
oCMenu.makeMenu('top2','','<b>&nbsp;Sales&nbsp;</b>','','')
    oCMenu.makeMenu('sub20','top2','&nbsp;Sales&nbsp;>','')
		oCMenu.makeMenu('sub200','sub20','&nbsp;New Entry','SalesEntry2.jsp','Frame3')
        oCMenu.makeMenu('sub201','sub20','&nbsp;Alter Details','AlterSalesEntry2.jsp','Frame3')
		oCMenu.makeMenu('sub202','sub20','&nbsp;Veiw Details','ViewEntries.jsp','Frame3')
    oCMenu.makeMenu('sub21','top2','&nbsp;Payments&nbsp;>','')
		oCMenu.makeMenu('sub210','sub21','&nbsp;Make Payment','MadePayment.jsp','Frame3')
		oCMenu.makeMenu('sub211','sub21','&nbsp;View Payments','ViewPayments.jsp','Frame3')
    oCMenu.makeMenu('sub22','top2','&nbsp;Reports&nbsp;>','','')
		oCMenu.makeMenu('sub220','sub22','&nbsp;Sundry Debitors','SunDebitors.jsp','Frame3')
		oCMenu.makeMenu('sub221','sub22','&nbsp;Sundry Creditors','SunCreditors.jsp','Frame3')
oCMenu.makeMenu('top3','','<b>&nbsp;Employees&nbsp;</b>','','')
    oCMenu.makeMenu('sub30','top3','&nbsp;Add Employee&nbsp;','AddEmployee2.jsp','Frame3')
    oCMenu.makeMenu('sub31','top3','&nbsp;View Employees','ConsolidatedSort.jsp','Frame3')
oCMenu.makeMenu('top4','','&nbsp;Reports&nbsp;','')
		oCMenu.makeMenu('sub40','top4','&nbsp;Sold Plots','SoldPlots.jsp','Frame3')
        oCMenu.makeMenu('sub41','top4','&nbsp;Reserved Plots','ReservedPlots.jsp','Frame3')
		oCMenu.makeMenu('sub42','top4','&nbsp;Vacant Plots','VaccentPlots.jsp','Frame3')

oCMenu.makeMenu('top5','','<b>&nbsp;About</b>','About.jsp','')//,0,0,"","",'red','silver')
oCMenu.makeMenu('top6','','<b>&nbsp;Logout</b>','Logout.jsp','_top')//,0,0,"","",'red','silver')

//Leave these two lines! Making the styles and then constructing the menu
oCMenu.makeStyle(); oCMenu.construct()

//"Hack" to make semitransparent menus in Explorer.
//.cl_menuname_level_b for setting special styles to the border at that level