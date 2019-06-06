<%@include file="Common.jsp"%>
<TABLE Align=center width=780><TR><TD>
<fieldset><legend>Retrieve Password Here</legend><BR>
<table width=600 align=center cellspacing=0 cellpadding=4><form method=post action='./RetrievePassword.jsp'>
<tr><Td align=right>User Name:</td><Td><input name=user></td></tR>
<tr><Td align=right>Hint Question:</td><Td><select name=hint_question>
     <option value="null" selected>Hint Question</option>
     <option value="food">Favorite Food</option>
     <option value="place">Historical Place</option>
     <option value="pet">Pet Name</option>
     <option value="event">Memorable Event</option>
     </select></td></tr>
<tr><Td align=right>Hint Answer:</td><Td><input name=hint_answer></td></tR>
<tr><td colspan=2 align=center><button type=submit accesskey="F"><u>F</u>ind Now</button>&nbsp;&nbsp;
<button type=reset accesskey="R"><u>R</u>efresh</button></td></tr>
</form></table><BR></fieldset></TD></TR></TABLE><script>setFooter();</SCRIPT>
</body></html>