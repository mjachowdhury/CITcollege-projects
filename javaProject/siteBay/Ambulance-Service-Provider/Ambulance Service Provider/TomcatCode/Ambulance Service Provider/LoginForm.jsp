<html>
<style>
legend{font-size:15;font-family:arial;font-weight:bold}
label{font-size:15;font-family:arial;font-weight:bold;color:red}
A:link {
	FONT-WEIGHT: bold; FONT-SIZE: 10pt; FONT-FAMILY: arial; TEXT-DECORATION: none;
	color:green
}
A:active {
	FONT-WEIGHT: bold; FONT-SIZE: 10pt; FONT-FAMILY: arial; TEXT-DECORATION: none;
	color:green
}
A:visited {
	FONT-WEIGHT: bold; FONT-SIZE: 10pt; FONT-FAMILY: verdana; TEXT-DECORATION: none;
	color:green
}
A:hover {
	FONT-WEIGHT: bold; FONT-SIZE: 10pt; FONT-FAMILY: arial; TEXT-DECORATION: underline;
	color:green
}

</style>
<body>
<img src = 'images\ambulance1.jpg' style = 'position:absolute;top:260px;left:200px'/>
	
		<h1><jsp:include page="header.html"/></h1><br>
 

<div id="content">
	<div id="sidebar">
		<div id="login" class="boxed">
			<h2 class="title">
			<%
			 if(request.getParameter("status")!=null)
			 {
			 %>
			 <%=request.getParameter("status")%>	
			 <% }
			 %>
			</h2>
			
				<form id="form1" method="post" action="LoginAction.jsp">
					<fieldset>
					<legend>Sign-In</legend>
					<br>
						<center>
					<label >Username:</label>
					<input  type="text" name="username" value="" />&nbsp;&nbsp;&nbsp;
					<label >Password:</label>
					<input type="password" name="password" value="" />&nbsp;&nbsp;&nbsp;
					<input  type="submit" name="inputsubmit1" value="Sign In" />&nbsp;&nbsp;&nbsp;
					<a href="RecoverPassword.jsp">Forgot password?</a>
					</center>	<br>
					</fieldset>
				</form>
			
		</div>
		<div id="updates" class="boxed">
			
	  </div>
	</div>
	<div id="main">
<%--		<div id="welcome" class="post">
	
<!--		  <h3 class="date"><span class="month">Mar.</span> <span class="day">8</span><span class="year">, 2007</span></h3>  -->
			<div class="story">
<!--				<img src="images/img06.jpg" alt="" width="120" height="151" class="left" />
			  <p><strong>Advantage</strong> is a free template from <a href="http://www.freecsstemplates.org/">Free CSS Templates</a> released under a <a href="http://creativecommons.org/licenses/by/2.5/">Creative Commons Attribution 2.5 License</a>. The photo is from <a href="http://www.pdphoto.org/">PDPhoto.org</a>. You're free to use it for both commercial or personal use. I only ask that you link back to my site in some way. <em>Enjoy :)</em></p>
			</div>-->
		  <div class="meta">
				
			</div>
		</div>
		<div id="example" class="post"></div>
	</div>
</div>--%>
<%-- <div id="footer">
	<p id="legal"><a href="#">Privacy Policy</a> | <a href="#">Terms of Use</a></p>
</div> --%>
</body>
</html>
