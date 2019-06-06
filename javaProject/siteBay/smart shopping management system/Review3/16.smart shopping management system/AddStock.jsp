<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>E-Acquistion Add Stock Page</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<script language="javascript">
function validate()
{
   var temp = document.stock;
   if(temp.pname.value=="" || temp.price.value=="")
   {
       alert("All Fields are manditory");
       return false;
   }
   if(temp.category.value=="")
   {
   	   alert("Please select category");
       return false;
   }
   //return true;
   
if(document.forms[0].price.value!="")       /*for price*/
{
var str;
var str_len;
var  k;
var  m;

str= document.forms[0].price.value;
str_len=str.length;

for(var i=0;i<str_len;i++)
{
k=str.charCodeAt(i);
if( ((k>47)&&(k<58))||(k==32)  )
{
  m=1;
}
else 
{

 m=2;
break;
}
}
if(m==2)
{
alert("please fill the price with ONLY NUMERIC");
document.forms[0].price.focus();
return false;
 }
}

}
</script>
  <script type="text/javascript" src="scripts/general.js"> </script>
   <script type="text/javascript" src="scripts/ts_picker.js"> </script>
<body>
<!-- start header -->
<div id="logo">
	<h1><jsp:include page="header.html"/></h1>
	</div>
<div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/> 
	</ul>
</div>
<!-- end header -->
<!-- start page -->
<div id="page">
  <div id="page-bg">
		<!-- start content -->
	<!-- end content -->
	<!-- start sidebar -->
<div id="latest-post">
<ul>
				
					<h2 align="center">AddStock Form</h2>
				
				
				   <form action="AddStockAction.jsp" method="post" name="stock" onSubmit="return validate()">

<table border="0" align="center">
  <!-- tr>
    <td class="tiny style3">Product ID </td>
    <td><input type="text" name="pid" class="textfield"/>
    </td>
  </tr> -->
  <tr>
    <td><span class="style3">Product Name</span></td>
    <td><input type="text" name="pname" class="textfield"/>
    </td>
  </tr>
  
  <tr>
    <td class="style3">Price</td>
    <td><input type="text" name="price" class="textfield"/></td>
  </tr>
  <tr>
    <td class="style3">Category</td>
    <td><SELECT name="category">
    <OPTION value="">
	-- select category --
	</OPTION>
	<OPTION value="electrical and electronics">
	electrical & electronics
	</OPTION>
	<OPTION value="stationary">
	stationary
	</OPTION>
	<OPTION value="beauty">
	beauty
	</OPTION>
	<OPTION value="garments">
	garments
	</OPTION>
	<OPTION value="jewelary">
	jewelary
	</OPTION>
	<OPTION value="others">
	others
	</OPTION>
	</SELECT></td>
  </tr>
    <tr>
    <td height="11" colspan="2"></td>
    </tr>
  <tr>
    <td colspan="2"><div align="center">
      <input name="Input" type="submit" value="Add Stock" />
    </div></td>
  </tr>
</table>
</form>
				
				
</ul>
		</div>
    <div style="clear: both;">&nbsp;</div>
	</div>
</div>
<!-- end page -->
<div id="footer">
			<p id="legal">
				<a href="#">Privacy Policy</a> |
				<a href="#">Terms of Use</a>
			</p>
		</div>
</body>
</html>
