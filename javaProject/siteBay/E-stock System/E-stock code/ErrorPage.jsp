<%@ page isErrorPage= "true" info = "This is the error page of the application" contentType = "text/html" %>

<html>
<body bgcolor="oldlace">
	<h1>OOP's An error has occurred </h1>
	<p> The server could not process the request<p>

	
	<b> Type of Exception    :</b> <%= exception.getClass() %></br>		
	<b> Message  :</b> <%= exception.getMessage() %> </br>

	<p> Please retry.....</p>
</body>
</html>


 