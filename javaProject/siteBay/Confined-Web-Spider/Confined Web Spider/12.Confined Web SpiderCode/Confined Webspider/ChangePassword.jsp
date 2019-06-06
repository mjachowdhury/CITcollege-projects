<%@include file="Common.jsp"%>
<TABLE Align=center width=780><TR><TD>
<fieldset><legend>Change Password Here</legend><BR>
<table width=600 align=center><form method=post action='./NewPassword.jsp'>
<tr><Td align=right>User Name:</td><Td><input name=user></td></tR>
<tr><Td align=right>Existing Password:</td><Td><input name=exist type=password>
<tr><Td align=right>New Password:</td><Td><input name=pass type=password></td></tR>
<tr><Td align=right>Retype Password:</td><Td><input name=repass type=password></td></tR>
<tr><td colspan=2 align=center><BR><button type=submit accesskey="C"><u>C</u>hange</button>&nbsp;&nbsp;
<button type=reset accesskey="R"><u>R</u>efresh</button></td></tr>
</form></table><BR></fieldset></TD></TR></TABLE><script>setFooter();</SCRIPT>
</body></html>