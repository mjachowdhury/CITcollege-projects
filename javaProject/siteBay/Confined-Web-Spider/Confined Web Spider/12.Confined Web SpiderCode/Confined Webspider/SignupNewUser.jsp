<%@include file="Common.jsp"%>
<TABLE Align=center width=780><TR><TD>
<fieldset><legend>Register New User</legend><BR>
<table border=0 cellpadding=5 cellspacing=0 width="750" style="font-family:tahoma;font-weight:bold;color:gray;font-size:12px"><form method=post action="./RegisterNewUser.jsp" onsubmit="return ValidateForm(this)">
<tr>
    <td align=right>User Name:</td>
    <td><INPUT name="user_name"></td>
</tr>
<tr>
   <td align=right>Password:</td>
   <td><INPUT  name="password" type=password></td>
</tr>
<tr>
   <td align=right>Confirm Password:</td>
   <td><INPUT name="Confirm_Password" type=password></td>
</tr>
<tr>
   <td align=right>Alternate E-mail:</td>
   <td><INPUT name="alternate_email"></td>
</tr>
<tr>
  <Td align=right>Sex:</td>
  <Td><input type=radio name=sex class=check checked value="male">Male&nbsp;&nbsp;
      <input type=radio name=sex class=check value="female">Female</td>
</tr>
<tr>
     <td align=right>Date Of Birth:
     <Td><input name=date /></td>
</tr>
<tr>
     <td align=right>Hint Question:</td>
     <td><select name=hint_question>
     <option value="null" selected>Hint Question</option>
     <option value="food">Favorite Food</option>
     <option value="place">Historical Place</option>
     <option value="pet">Pet Name</option>
     <option value="event">Memorable Event</option>
     </select></TD></tR>
<TR><TD align=right>Hint Answer:</td><td>
     <INPUT name=hint_answer></td>
</tr>
<tr>
    <td align=right>Address:
    <td><textarea wrap=off accesskey="D" rows=3 cols=20 name=address></textarea></td>
</tr>
<Tr><td align=right>Occupation:
    <td><select name=occupation accesskey="O">
    <option value="null" selected>Select Qualification
    <option value="advocate">Advocate
    <option value="collector">Collector
    <option value="engineer">Engineer
    <option value="graduate">Graduate
    <option value="fashion_designer">Fashion Designer
    <option value="IT_consultant">IT-Consultant
    <option value="lawyer">Lawyer
    <option value="lecturer">Lecturer [Faculty]
    <option value="Manager">Manager in Organizaion
    <option value="programmer">Programmer
    <option value="post_graduate">Post Graduate
    <option value="student">Student
    <option value="Teacher">Teacher
    </select></tr>
<Tr><td align=right>Sun Sign:
    <td><INPUT name=zodiac size=30>
</tr>
<Tr>
    <td align=right>Phone/Mobile:
    <td><INPUT name=phone>
</tr>
<tr><td colspan=2 align=center><button type=submit>Proceed</button>&nbsp;&nbsp;<button type=reset>Refresh</button></table></fieldset></TD></TR></TABLE><script>setFooter();</SCRIPT>
</form>
</body></html>