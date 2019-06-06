<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>ChangepasswordForm.html</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type"
			content="text/html; charset=ISO-8859-1">
		<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	</head>
	<body>
		<form id="form3" name="newedesignation" method="post"
			action="ChangePasswordAction.jsp">
			<table border="0" align="center">
				<tr>
					<td>
						Login Name
					</td>
					<td>
						<input type="text" name="username" />
					</td>
				</tr>
				<tr>
					<td>
						Old Password
					</td>
					<td>
						<input type="password" name="oldpassword" />
					</td>
				</tr>
				<tr>
					<td>
						New Password
					</td>
					<td>
						<input type="password" name="newpassword" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input type="submit" name="Submit" value="Change" />

						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
