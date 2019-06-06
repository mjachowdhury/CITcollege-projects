<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/top_main.jsp" %>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <%@ include file="/includes/page_top.jsp" %>
  <tr>
    <td>&nbsp;</td>
    <%@ include file="/includes/navbar.jsp" %>
    <td valign="top">
		<br/>
	    <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
	      <!-- Begin You are here -->
	      <tr>
	        <td width="1%"><img src="/claros/images/spacer.gif" width="3" height="1"></td>
	        <td><img src="/claros/images/spacer.gif" width="600" height="1"></td>
	        </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td><table width="99%" height="20" border="0" cellpadding="3" cellspacing="3" align="left">
	          <tr>
	            <td nowrap width="96%">
	            	<img src="/claros/images/grey_bullet.gif" width="5" height="12">
	            	<span class="tableheadertext youarehere">CALENDAR - SAVE EVENT : </span>
	            </td>
	            <td align="right" width="1%">
	            	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	            		<tr><td align="center" nowrap>
	            			<html:link page="/newEvent.do"><img src="/claros/images/add.gif" width="24" height="24" border="0"><br>New Event</html:link>
	            		</td>
	            		</tr>
	            	</table>
	            </td>
	          </tr>
	        </table></td>
	      </tr>
	      <!-- End You are here -->
	
		  <tr>
	        <td>&nbsp;</td>
		    <td>
			  <!-- BEGIN CONTENT -->
	
	
		      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
		        <tr>
		          <td valign="top" width="99%">

			          <table width="100%" border="0" cellspacing="3" cellpadding="0">
			          <tr>
			            <td align="center">

					    	<br/>
					    	<br/>
					    	<br/>
					    	<span class="youarehere">
					      	<strong>Event saved successfully.</strong>
					      	</span>
					    	<br/>
					    	<br/>

						</td>
					  </tr>
					  </table>
				  </td>
				</tr>
			  </table>
			  <!-- END CONTENT -->
			</td>
		  </tr>
		</table>		  
    </td>
  </tr>
</table>
</body>
</html>
