<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
<!--
.style1 {color: #000000}
.style3 {color: #000000; font-weight: bold; }
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
-->
</style>
</head>
<body>

	<h1><jsp:include page="header.html"/></h1>
	
<div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/>  
	</ul>
</div>
<hr />
<div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Example form
      <div class="sub3">
          <form id="form1" method="post" action="">
            <br />
            <table width="245" border="0">
              <tr>
                <td width="79"><strong>First name</strong></td>
                <td width="156"><span class="style1">
                  <label>
                  <input type="text" name="textfield" id="textfield" />
                  </label>
                </span></td>
              </tr>
              <tr>
                <td><span class="style3">Last Name</span></td>
                <td><span class="style1">
                 
                  <input type="password" name="textfield2" id="textfield2" />
                  
                </span></td>
              </tr>
              <tr>
                <td height="10"><span class="style3">Address</span></td>
                <td><label>
                  <input type="text" name="textfield3" id="textfield3" />
                </label></td>
              </tr>
              <tr>
                <td height="45" colspan="2"><div align="center">
                    <input type="submit" name="button" id="button" value="Register" />
                </div></td>
              </tr>
            </table>
          </form>
      </div>
    </div>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  </div>
  <p></p>
		<p>&nbsp;</p>
  </div>
	
</div>
<div id="recent-posts">
	<h2>&nbsp;</h2>

  
</div>


<div id="footer">
	
</div>
</body>
</html>
