<%@ include file="Common.jsp"%>
<title>Login New User</title>
<TABLE Align=center width=780>
<TR><TD><fieldset><legend align=center>Sign in Existing User</legend><BR>
<script>if(location.search!="") document.write("<center><h4>"+unescape(location.search.substring(1))+"</center></h4><BR>");
</script>
<center><a href="SignupNewUser.jsp">Signup New User</a><font color=white><font color=black>&nbsp;&nbsp;||&nbsp;&nbsp;</font></font><a href="ForgotPassword.jsp">Forgot Password&nbsp;?</a><font color=black>&nbsp;&nbsp;||&nbsp;&nbsp;</font>&nbsp;<a href="ChangePassword.jsp">Change Password Here</a></center><br>
<table width=300 align=center cellspacing=0 cellpadding=3><form action="CheckAdmin.jsp">
<TR><TD align=right nowrap>Admin Id:</td><td><input name=userid autocomplete=true></td></tr>
<TR><TD align=right nowrap>Password:</td><td><input type=password name=password></td></tr>
<TR><TD align=center colspan=2 nowrap><input type=checkbox value="admin" name=admin class=check>&nbsp;Login as Administrator&nbsp;&nbsp;<button type=submit>Sign in</button>&nbsp;&nbsp;
</td></tr></form>
</TABLE><BR></fieldset></TD></TR></TABLE><script>setFooter();</SCRIPT>
