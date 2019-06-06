    <td valign="top">
      <br>
      <br>
      <table width="90%" border="0" cellspacing="0" cellpadding="0" id="navbar">
        <tr>
          <td id="navrow"><a href="#" onclick="toggle('email')"><img src="/claros/images/blue_bullet.gif" width="9" height="8" id="navbullet" border="0">&nbsp;E-mail</a><br>
          <div id="maildiv">
          	<table width="95%" border="0" align="left" cellpadding="0" cellspacing="3" id="mboxes">
            	<c:forEach items="${mailFolders}" var="fold">
	              <tr>
	                <td nowrap>
	                	<c:choose>
	                		<c:when test="${fold.type == 1}">
	                			<img src="/claros/images/ico_inbox.gif" alt="inbox" width="21" height="17">
	                		</c:when>
	                		<c:when test="${fold.type == 2}">
	                			<img src="/claros/images/ico_junk.gif" alt="junk" width="21" height="17">
	                		</c:when>
	                		<c:when test="${fold.type == 3}">
	                			<img src="/claros/images/ico_sent.gif" alt="sent" width="21" height="17">
	                		</c:when>
	                		<c:otherwise>
	                			<img src="/claros/images/ico_regular_folder.gif" alt="sent" width="21" height="17">
	                		</c:otherwise>
	                	</c:choose>
            			<html-el:link href="/claros/webmail/mailbox.do?fid=${fold.id}"><str:truncateNicely upper="10" appendToEnd=".."><c:out value="${fold.folderName}"/></str:truncateNicely></html-el:link>
                		<c:if test="${fold.unreadItemCount > 0}">
                			(<strong><c:out value="${fold.unreadItemCount}"/></strong>)
                		</c:if>
	                </td>
	              </tr>
				</c:forEach>
            </table>
          </div>
		  </td>
        </tr>
        <tr>
          <td id="navrow"><img src="/claros/images/dark_grey_bullet.gif" width="5" height="12" id="navbullet">&nbsp;<a href="/claros/contacts/listContacts.do">Addresses </a></td>
        </tr>
        <tr>
          <td id="navrow"><img src="/claros/images/dark_grey_bullet.gif" width="5" height="12" id="navbullet">&nbsp;<a href="/claros/calendar/listDaily.do?t=">Calendar</a></td>
        </tr>
        <tr>
          <td id="navrow"><a href="#" onclick="toggle('notes')"><img src="/claros/images/blue_bullet.gif" width="9" height="8" id="navbullet" border="0">&nbsp;Notes</a><br>
          <div id="notesdiv">
          	<table width="95%" border="0" align="left" cellpadding="0" cellspacing="3" id="mboxes">
              <tr>
                <td nowrap>
                	<img src="/claros/images/ico_regular_folder.gif" alt="sent" width="21" height="17">
                	<html-el:link href="/claros/notes/listNotes.do?fid=0">Unfiled</html-el:link>
                </td>
              </tr>
            	<c:forEach items="${notesFolders}" var="nfold">
	              <tr>
	                <td nowrap>
	                	<img src="/claros/images/ico_regular_folder.gif" alt="sent" width="21" height="17">
	                	<html-el:link href="/claros/notes/listNotes.do?fid=${nfold.id}"><str:truncateNicely upper="11" appendToEnd="..."><c:out value="${nfold.folderName}"/></str:truncateNicely></html-el:link>
	                </td>
	              </tr>
				</c:forEach>
            </table>
          </div>
		  </td>
        </tr>
      </table>
      <br>
      <br>
      <div style="visibility:hidden"> 
      	<iframe src="/claros/webmail/checkNewMail.do" width="10" height="10" frameborder="0"></iframe>
      </div>
    </td>
