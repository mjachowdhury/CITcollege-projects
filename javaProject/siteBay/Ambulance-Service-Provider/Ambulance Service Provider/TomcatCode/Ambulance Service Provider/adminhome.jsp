<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<h1 align=center><jsp:include page="header.html"/></h1>
	
<div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/>  
	</ul>
</div>
<hr />
<div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title" align=center><br><br><br/>Welcome to Home Page</h1>
  
          <form id="form1" method="post" action="">
            
            <table width="245" border="0">
              <tr>
              <td>
              <%  if(request.getParameter("status")!=null){ %>
              <%=request.getParameter("status") %>
              <% }%>
              </td>
              </tr>
            </table>
          </form>
      </div>
  
    <p>&nbsp;</p>
    
  
  <p></p>
		<p>&nbsp;</p>
 
	

<div id="recent-posts">
	<h2>&nbsp;</h2>

  
</div>


<div id="footer">
	
</div>
</body>
</html>
