
<%@ page language="java"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<HTML> 
	<HEAD>
		<TITLE>Bluewega Travels User Registration Form</TITLE>
	</HEAD>
	<BODY>
	<BR>
	<CENTER>
	<html:form action="UserReg">
	<TABLE WIDTH="100%" HEIGHT="30" BORDER="0">
		<TR>
			<TD WIDTH="2%">
			</TD>
			<TD>
				<FONT FACE="VERDANA" SIZE="+1" COLOR="#00366C"><B>User Registration</B></FONT>
			</TD>
			<TD WIDTH="27%" ALIGN="RIGHT">
				<FONT FACE="VERDANA" SIZE="1" COLOR="#00366C"><B>Already have an Login ID?</B></FONT>
			</TD>
			<TD WIDTH="2%" ALIGN="RIGHT">
				<FONT COLOR="DARKBLUE" SIZE="1"><B>[</B></FONT>
			</TD>
			<TD WIDTH="4%" ALIGN="RIGHT">
				<A HREF="Userlogin.jsp" TITLE="To get Login Form" onMouseOver="window.status=('To get Login Form'); return true;" onMouseOut="window.status=(' '); return true;"><FONT FACE="VERDANA" SIZE="1" COLOR="#00366C"><B>Login</B></FONT></A>
			</TD>
			<TD WIDTH="2%" ALIGN="LEFT">
				<FONT SIZE="1"><B>]</B></FONT>
			</TD>
		</TR>
	</TABLE>
	<HR WIDTH="99%" SIZE="2" COLOR="#00366C">
	<TABLE WIDTH="90%" ALIGN="CENTER" BORDER="0">
		<TR>
			<TD>
				<P ALIGN="JUSTIFY"><FONT FACE="VERDANA" SIZE="2" COLOR="BLACK"> Welcome to Bluewega Travels Registration. Complete this form to become a registered member and get the premium services. You'll need Login Id and password  to access your account.</FONT><BR>
				<FONT FACE="VERDANA" SIZE="2" COLOR="BLACK">(<FONT FACE="VERDANA" COLOR="RED">Note : </FONT><FONT FACE="TIMES" COLOR="RED" SIZE="2">*</FONT> Fields are mandatory.)</FONT>
			</TD>
		</TR>
	</TABLE>
	<TABLE WIDTH="90%" ALIGN="CENTER" BORDER="0" CELLPADDING="4">
		<TR>
			<TD WIDTH="32%" ALIGN="RIGHT"><FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="login"/> : </B></FONT>
			</TD>
			<TD ALIGN="LEFT">
				<html:text property="login"/><FONT COLOR=RED SIZE="2">*</FONT>
				<FONT FACE="VERDANA" SIZE="1" COLOR="#00366C"> ID should be &gt;= 6 and &lt;=20 characters (a-z,0-9,_).</FONT><html:errors property="login"/>
			</TD>
		</TR>
		<TR>
			<TD ALIGN="RIGHT" WIDTH="32%"> <FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="pass"/> : </B></FONT>
			</TD>
			<TD COLSPAN="2">
				<html:password property="pass"/><FONT COLOR=RED SIZE="2">*</FONT> <FONT FACE="VERDANA" SIZE="1" COLOR="#00366C"> Password should be &gt;6 and &lt;=15 characters.</FONT><html:errors property="pass"/>
			</TD>
		</TR>
		<TR>
			<TD ALIGN="RIGHT" WIDTH="32%"> <FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="rtpass"/> : </B></FONT>
			</TD>
			<TD COLSPAN="2">
				<html:password property="rtpass"/><FONT COLOR=RED SIZE="2">*</FONT> <FONT FACE="VERDANA" SIZE="1" COLOR="#00366C"> Both Passwords are should be same.</FONT><html:errors property="rtpass"/>
			</TD>
		</TR>
	</TABLE>
	<HR WIDTH="90%" SIZE="1" COLOR="#00366C">

			<TABLE WIDTH="90%" ALIGN="CENTER" BORDER="0" CELLPADDING="5">
				<CAPTION ALIGN="LEFT">
					<FONT FACE="VERDANA" SIZE="3" COLOR="#00366C"><B>Account Details </B></FONT>
				</CAPTION>
				<TR>
					<TD>
						<P ALIGN="JUSTIFY"><FONT FACE="VERDANA" SIZE="2" COLOR="BLACK">If you forget your password, you can retrieve it by answering your unique hint question. Select a question from the following list such that only you know its answer.
						</FONT>
					</TD>
				</TR>
			</TABLE>
			
			<TABLE WIDTH="90%" ALIGN="CENTER" BORDER="0" CELLPADDING="4">
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="secque"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="secque">
							<html:option value="0">[Select a Question]</html:option>
							<html:option value="1">What is your nick name?</html:option>
							<html:option value="2">What is your pet's name?</html:option>
							<html:option value="3">What was the name of your first school?</html:option>
							<html:option value="4">Who was your childhood hero?</html:option>
							<html:option value="5">What is the exact time of Birth?</html:option>
							<html:option value="6">What is your favourite past-time?</html:option>
							<html:option value="7">What is your favourite food?</html:option>
							<html:option value="8">What is your all-time favourite sports team?</html:option>
							<html:option value="9">Who is your favourite Teacher?</html:option>
							<html:option value="10">Which is your favourite Movie?</html:option>
							<html:option value="11">What is your father's middle name?</html:option>
							<html:option value="12">What was your high school mascot?</html:option>
							<html:option value="13">What make was your first car or bike?</html:option>
							<html:option value="14">Where did you first meet your spouse?</html:option>
						</html:select><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="secque"/></TD>
				</TR>
				<TR>
					<TD ALIGN="RIGHT" WIDTH="32%"> <FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="secans" /> : </B></FONT>
					</TD>
					<TD>
						<html:text property="secans"/><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="secans"/></TD>
				</TR>
			</TABLE>
			<HR WIDTH="90%" SIZE="1" COLOR="#00366C">

			<TABLE WIDTH="90%" ALIGN="CENTER" BORDER="0" CELLPADDING="4">
				<CAPTION ALIGN="LEFT">
					<FONT FACE="VERDANA" SIZE="3" COLOR="#00366C"><B>About Yourself </B></FONT>
				</CAPTION>
				<TR>
					<TD>
						<P ALIGN="JUSTIFY"><FONT FACE="VERDANA" SIZE="2" COLOR="BLACK">We don't share your personal information. Our privacy policy ensures that any information you give is absolutely confidential.</FONT>
					</TD>
				</TR>
			</TABLE>
			<TABLE WIDTH="90%" ALIGN="CENTER" BORDER="0" CELLPADDING="4">
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="slt"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="slt">
							<html:option value="0">[Select One]</html:option>
							<html:option value="Adm">Adm.</html:option>
						    <html:option value="Capt">Capt.</html:option>
						    <html:option value="Col">Col.</html:option>
						    <html:option value="Comm">Comm.</html:option>
						    <html:option value="Dr">Dr.</html:option>
						    <html:option value="Gen">Gen.</html:option>
						    <html:option value="Inq">Inq.</html:option>
						    <html:option value="Lic">Lic.</html:option>
						    <html:option value="Lt">Lt.</html:option>
						    <html:option value="Miss">Miss.</html:option>
						    <html:option value="Mr">Mr.</html:option>
						    <html:option value="Mrs">Mrs.</html:option>
						    <html:option value="Ms">Ms.</html:option>
						    <html:option value="Nr">Nr.</html:option>
						    <html:option value="Prof">Prof.</html:option>
						    <html:option value="Sr">Sr.</html:option>
						    <html:option value="Sra">Sra.</html:option>
						    <html:option value="Srta">Srta.</html:option>
					    </html:select><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="slt"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="fname"/> : </B></FONT>
					</TD>
					<TD>
						<html:text property="fname"/><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="fname"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="lname"/> : </B></FONT>
					</TD>
					<TD>
						<html:text property="lname"/>
					</TD>
					<TD><html:errors property="lname"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B>Date Of Birth : </B></FONT>
					</TD>
					<TD>
						<html:select property="bdmonth">
							<html:option value="0">[ Month ]</html:option>
							<html:option value="1">January</html:option>
							<html:option value="2">February</html:option>
							<html:option value="3">March</html:option>
							<html:option value="4">April</html:option>
							<html:option value="5">May</html:option>
							<html:option value="6">June</html:option>
							<html:option value="7">July</html:option>
							<html:option value="8">August</html:option>
							<html:option value="9">September</html:option>
							<html:option value="10">October</html:option>
							<html:option value="11">November</html:option>
							<html:option value="12">December</html:option>
						</html:select>&nbsp;&nbsp;
						<html:select property="bdday">
							<html:option value="0">[ Day ]</html:option>
							<html:option value="1">1</html:option>
							<html:option value="2">2</html:option>
							<html:option value="3">3</html:option>
							<html:option value="4">4</html:option>
							<html:option value="5">5</html:option>
							<html:option value="6">6</html:option>
							<html:option value="7">7</html:option>
							<html:option value="8">8</html:option>
							<html:option value="9">9</html:option>
							<html:option value="10">10</html:option>
							<html:option value="11">11</html:option>
							<html:option value="12">12</html:option>
							<html:option value="13">13</html:option>
							<html:option value="14">14</html:option>
							<html:option value="15">15</html:option>
							<html:option value="16">16</html:option>
							<html:option value="17">17</html:option>
							<html:option value="18">18</html:option>
							<html:option value="19">19</html:option>
							<html:option value="20">20</html:option>
							<html:option value="21">21</html:option>
							<html:option value="22">22</html:option>
							<html:option value="23">23</html:option>
							<html:option value="24">24</html:option>
							<html:option value="25">25</html:option>
							<html:option value="26">26</html:option>
							<html:option value="27">27</html:option>
							<html:option value="28">28</html:option>
							<html:option value="29">29</html:option>
							<html:option value="30">30</html:option>
							<html:option value="31">31</html:option>
						</html:select>&nbsp;&nbsp;
						<html:select property="bdyear">
						    <html:option value="0">[ Year ]</html:option>
							<html:option value="2005">2007</html:option>
							<html:option value="2005">2006</html:option>
						    <html:option value="2005">2005</html:option>
							<html:option value="2004">2004</html:option>
							<html:option value="2003">2003</html:option>
							<html:option value="2002">2002</html:option>
							<html:option value="2001">2001</html:option>
							<html:option value="2000">2000</html:option>
							<html:option value="1999">1999</html:option>
							<html:option value="1998">1998</html:option>
							<html:option value="1997">1997</html:option>
							<html:option value="1996 ">1996</html:option>
							<html:option value="1995">1995</html:option>
							<html:option value="1994">1994</html:option>
							<html:option value="1993">1993</html:option>
							<html:option value="1992">1992</html:option>
							<html:option value="1991">1991</html:option>
							<html:option value="1990">1990</html:option>
							<html:option value="1989">1989</html:option>
							<html:option value="1988">1988</html:option>
							<html:option value="1987">1987</html:option>
							<html:option value="1986">1986</html:option>
							<html:option value="1985">1985</html:option>
							<html:option value="1984">1984</html:option>
							<html:option value="1983">1983</html:option>
							<html:option value="1982">1982</html:option>
							<html:option value="1981">1981</html:option>
							<html:option value="1980">1980</html:option>
							<html:option value="1979">1979</html:option>
							<html:option value="1978">1978</html:option>
							<html:option value="1977">1977</html:option>
							<html:option value="1976">1976</html:option>
							<html:option value="1975">1975</html:option>
							<html:option value="1974">1974</html:option>
							<html:option value="1973">1973</html:option>
							<html:option value="1972">1972</html:option>
							<html:option value="1971">1971</html:option>
							<html:option value="1970">1970</html:option>
							<html:option value="1969">1969</html:option>
							<html:option value="1968">1968</html:option>
							<html:option value="1967">1967</html:option>
							<html:option value="1966">1966</html:option>
							<html:option value="1965">1965</html:option>
							<html:option value="1964">1964</html:option>
							<html:option value="1963">1963</html:option>
							<html:option value="1962">1962</html:option>
							<html:option value="1961">1961</html:option>
							<html:option value="1960">1960</html:option>
							<html:option value="1959">1959</html:option>
							<html:option value="1958">1958</html:option>
							<html:option value="1957">1957</html:option>
							<html:option value="1956">1956</html:option>
							<html:option value="1955">1955</html:option>
							<html:option value="1954">1954</html:option>
							<html:option value="1953">1953</html:option>
							<html:option value="1952">1952</html:option>
							<html:option value="1951">1951</html:option>
							<html:option value="1950">1950</html:option>
							<html:option value="1949">1949</html:option>
							<html:option value="1948">1948</html:option>
							<html:option value="1947">1947</html:option> 
						</html:select><FONT COLOR=RED SIZE=2>*</FONT>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="gender"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="gender">
							<html:option value="0">----</html:option>
							<html:option value="m">Male</html:option>
							<html:option value="f">Female</html:option>
						</html:select><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="edu"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="edu">
							<html:option value="0">[Select One]</html:option>
							<html:option value="1">Graduate/Post Graduate-Technical</html:option>
							<html:option value="2">Graduate/Post Graduate-Professional</html:option>
							<html:option value="3">Graduate/Post Graduate-General</html:option>
							<html:option value="4">Some College but not Graduate</html:option>
							<html:option value="5">SSC/HSC</html:option>
							<html:option value="6">School 5-9 Years</html:option>
							<html:option value="7">School upto 4 years</html:option>
						</html:select><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="edu"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="occ"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="occ">
							<html:option value="0">[Select One]</html:option>
							<html:option value="1">Accounting/Finance</html:option>
						    <html:option value="2">Computer related(Other)
							</html:option>
						    <html:option value="3">Computer related (Internet) </html:option>
						    <html:option value="4">Consulting</html:option>
						    <html:option value="5">Customer Service/Support </html:option>
						    <html:option value="6">Education/Training</html:option>
						    <html:option value="7">Engineering/Technology
							</html:option>
						    <html:option value="8">Executive/Senior Management </html:option>
						    <html:option value="9">General Administrative/Supervisory </html:option>
						    <html:option value="10">Government/Military</html:option>
						    <html:option value="11">Homemaker</html:option>
						    <html:option value="12"> Manufacturing/Production/Operations</html:option>
						    <html:option value="13">Professional (Medical,Legal,etc.) </html:option>
						    <html:option value="14">Research and Development </html:option>
						    <html:option value="15">Sales/Marketing/Advertising</html:option>
						    <html:option value="16">Self-Employed/Owner</html:option>
						    <html:option value="17">Student</html:option>
						    <html:option value="18">Tradesman/Craftsman</html:option>
						    <html:option value="19">Unemployed/between jobs </html:option>
						    <html:option value="20">Retired</html:option>
						    <html:option value="21">Other</html:option>
					    </html:select>
					</TD>
					<TD><html:errors property="occ"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="ind"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="ind">
							<html:option value="0">[Select One]</html:option>
							<html:option value="1">Advertising/Marketing/PR</html:option>
							<html:option value="2">Aerospace</html:option>
							<html:option value="3">Agriculture/Chemicals/Forest Products</html:option>
							<html:option value="4">Automotive</html:option>
							<html:option value="5">Computers/Electronics</html:option>
							<html:option value="6">Construction</html:option>
							<html:option value="7">Consumer Goods</html:option>
							<html:option value="8">Education(includes students)</html:option>
							<html:option value="9">Energy/Mining</html:option>
							<html:option value="10">Finance/Insurance/Real Estate</html:option>
							<html:option value="11">Government/Military/Public Service</html:option>
							<html:option value="12">Hospitality/Recreation</html:option>
							<html:option value="13">Media/Publishing/Entertainment</html:option>
							<html:option value="14">Medical/Health Services</html:option>
							<html:option value="15">Pharmaceuticals</html:option>
							<html:option value="16">Retail</html:option>
							<html:option value="17">Services</html:option>
							<html:option value="18">Telecommunications/Networking</html:option>
							<html:option value="19">Travel/Transportation</html:option>
							<html:option value="20">Other</html:option>
						</html:select>
					</TD>
					<TD><html:errors property="ind"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="ain"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="ain">
							<html:option value="0">[Select One]</html:option>
							<html:option value="1"> No Income</html:option>
							<html:option value="2"> Under Rs.27,500</html:option>
							<html:option value="3"> Rs. 27,501 - Rs.50,000</html:option>
							<html:option value="4"> Rs. 50,001 - Rs.75,000</html:option>
							<html:option value="5"> Rs. 75,001 - Rs.1,00,000</html:option>
							<html:option value="6"> Rs.1,00,001 - Rs.1,50,000</html:option>
							<html:option value="7"> Rs.1,50,001 - Rs.5,27,000</html:option>
							<html:option value="8"> Rs.5,27,001 - Rs.15,27,000 </html:option>
							<html:option value="9"> Over Rs.15,27,001</html:option>
						</html:select>
					</TD>
					<TD><html:errors property="ain"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT" VALIGN="MIDDLE">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="addr"/> : </B></FONT>
					</TD>
					<TD>
						<html:textarea property="addr" cols="27" rows="2"> </html:textarea>
						<FONT COLOR="RED" SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="addr"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300" ><B><bean:message key="city"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="city">
							<html:option value="0">[Select a City]</html:option> 
							<html:option value="Ahmedabad">Ahmedabad</html:option>
							<html:option value="Bangalore">Bangalore</html:option>
							<html:option value="Baroda">Baroda</html:option>
							<html:option value="Bhopal">Bhopal</html:option>
							<html:option value="Bhubaneshwar">Bhubaneshwar</html:option>
							<html:option value="Chandigarh">Chandigarh</html:option>
							<html:option value="Chennai(Madras)">Chennai(Madras)</html:option>
							<html:option value="Cochin">Cochin</html:option>
							<html:option value="Coimbatore">Coimbatore</html:option>
							<html:option value="Delhi">Delhi</html:option>
							<html:option value="Goa">Goa</html:option>
							<html:option value="Hyderabad">Hyderabad</html:option>
							<html:option value="Kanpur">Kanpur</html:option>
							<html:option value="Indore">Indore</html:option>
							<html:option value="Jaipur">Jaipur</html:option>
							<html:option value="Kolkata(Calcutta)">Kolkata(Calcutta)</html:option>
							<html:option value="Lucknow">Lucknow</html:option>
							<html:option value="Ludhiana">Ludhiana</html:option>
							<html:option value="Madurai">Madurai</html:option>
							<html:option value="Mahabubnagar">Mahabubnagar</html:option>
							<html:option value="Mumbai">Mumbai</html:option>
							<html:option value="Nagpur">Nagpur</html:option>
							<html:option value="Noida">Noida</html:option>
							<html:option value="Patna">Patna</html:option>
							<html:option value="Pune">Pune</html:option>
							<html:option value="Surat">Surat</html:option>
							<html:option value="Tirupathi">Tirupathi</html:option>
							<html:option value="Varanasi">Varanasi</html:option>
							<html:option value="Vijayawada">Vijayawada</html:option>
							<html:option value="Vishakapatnam">Vishakapatnam</html:option>
							<html:option value="Warangal">Warangal</html:option>
						</html:select><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="city"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="state"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="state">
							<html:option value="0">[Select a State]</html:option>
							<html:option value="1">Andaman and Nicobar Islands</html:option>
							<html:option value="2">Andhra Pradesh</html:option>
							<html:option value="3">Arunachal Pradesh</html:option>
							<html:option value="4">Assam</html:option>
							<html:option value="5">Bihar</html:option>
							<html:option value="6">Chattisgarh</html:option>
							<html:option value="7">Dadra and Nagar Haveli </html:option>
							<html:option value="8">Daman and Diu</html:option>
							<html:option value="9">Delhi</html:option>
							<html:option value="10">Goa</html:option>
							<html:option value="11">Gujarat</html:option>
							<html:option value="12">Haryana</html:option>
							<html:option value="13">Himachal Pradesh</html:option>
							<html:option value="14">Jammu and Kashmir</html:option>
							<html:option value="15">Jharkhand</html:option>
							<html:option value="16">Karnataka</html:option>
							<html:option value="17">Kerala</html:option>
							<html:option value="18">Lakshadweep</html:option>
							<html:option value="19">Madhya Pradesh</html:option>
							<html:option value="20">Maharashtra</html:option>
							<html:option value="21">Manipur</html:option>
							<html:option value="22">Meghalaya</html:option>
							<html:option value="23">Mizoram</html:option>
							<html:option value="24">Meghalaya</html:option>
							<html:option value="25">Nagaland</html:option>
							<html:option value="26">Orissa</html:option>
							<html:option value="27">Pondicherry</html:option>
							<html:option value="28">Punjab</html:option>
							<html:option value="29">Rajasthan</html:option>
							<html:option value="30">Sikkim</html:option>
							<html:option value="31">Tamilanadu</html:option>
							<html:option value="32">Tripura</html:option>
							<html:option value="33">Uttaranchal</html:option>
							<html:option value="34">Uttar Pradesh</html:option>
							<html:option value="35">West Bengal</html:option>
						</html:select><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="state"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="country"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="country">
							<html:option value="0">[Select a Country]</html:option>
							<html:option value="1">Afghanistan</html:option>
							<html:option value="2">Albania</html:option>
							<html:option value="2">Algeria</html:option>
							<html:option value="3">American Samoa</html:option>
							<html:option value="4">Andorra</html:option>
							<html:option value="5">Angola</html:option>
							<html:option value="6">Anguilla</html:option>
							<html:option value="7">Antarctica</html:option>
							<html:option value="8">Antigua And Barbuda</html:option>
							<html:option value="9">Argentina</html:option>
							<html:option value="10">Armenia</html:option>
							<html:option value="11">Aruba</html:option>
							<html:option value="12">Australia</html:option>
							<html:option value="13">Austria</html:option>
							<html:option value="14">Azerbaijan</html:option>
							<html:option value="15">Bahamas</html:option>
							<html:option value="16">Bahrain</html:option>
							<html:option value="17">Bangladesh</html:option>
							<html:option value="18">Barbados</html:option>
							<html:option value="19">Belarus</html:option>
							<html:option value="20">Belgium</html:option>
							<html:option value="21">Belize</html:option>
							<html:option value="22">Benin</html:option>
							<html:option value="23">Bermuda</html:option>
							<html:option value="24">Bhutan</html:option>
							<html:option value="25">Bolivia</html:option>
							<html:option value="26">Bosnia And Herzegowina </html:option>
							<html:option value="27">Botswana</html:option>
							<html:option value="28">Bouvet Island</html:option>
							<html:option value="29">Brazil</html:option>
							<html:option value="30">British Indian Ocean Territory </html:option>
							<html:option value="31">Brunei Darussalam</html:option>
							<html:option value="32">Bulgaria</html:option>
							<html:option value="33">Burkina Faso</html:option>
							<html:option value="34">Burundi</html:option>
							<html:option value="35">Cambodia</html:option>
							<html:option value="36">Cameroon</html:option>
							<html:option value="37">Canada</html:option>
							<html:option value="38">Cape Verde</html:option>
							<html:option value="39">Cayman Islands</html:option>
							<html:option value="40">Central African Republic </html:option>
							<html:option value="41">Chad</html:option>
							<html:option value="42">Chile</html:option>
							<html:option value="43">China</html:option>
							<html:option value="44">Christmas Island</html:option>
							<html:option value="45">Cocos (Keeling) Islands </html:option>
							<html:option value="46">Colombia</html:option>
							<html:option value="47">Comoros</html:option>
							<html:option value="48">Congo</html:option>
							<html:option value="49">Cook Islands</html:option>
							<html:option value="50">Costa Rica</html:option>
							<html:option value="51">Cote D Ivoire</html:option>
							<html:option value="52">Croatia</html:option>
							<html:option value="53">Cyprus</html:option>
							<html:option value="54">Czech Republic</html:option>
							<html:option value="55">Denmark</html:option>
							<html:option value="56">Djibouti</html:option>
							<html:option value="57">Dominica</html:option>
							<html:option value="58">Dominican Republic</html:option>
							<html:option value="59">East Timor</html:option>
							<html:option value="60">Ecuador</html:option>
							<html:option value="61">Egypt</html:option>
							<html:option value="62">El Salvador</html:option>
							<html:option value="63">Equatorial Guinea</html:option>
							<html:option value="64">Eritrea</html:option>
							<html:option value="65">Estonia</html:option>
							<html:option value="66">Ethiopia</html:option>
							<html:option value="67">Falkland Islands</html:option>
							<html:option value="68">Faroe Islands</html:option>
							<html:option value="69">Fiji</html:option>
							<html:option value="70">Finland</html:option>
							<html:option value="71">France</html:option>
							<html:option value="72">France, Metropolitan</html:option>
							<html:option value="73">French Guiana</html:option>
							<html:option value="74">French Polynesia</html:option>
							<html:option value="75">French Southern Territories </html:option>
							<html:option value="76">Gabon</html:option>
							<html:option value="77">Gambia</html:option>
							<html:option value="78">Georgia</html:option>
							<html:option value="79">Germany</html:option>
							<html:option value="80">Ghana</html:option>
							<html:option value="81">Gibraltar</html:option>
							<html:option value="82">Greece</html:option>
							<html:option value="83">Greenland</html:option>
							<html:option value="84">Grenada</html:option>
							<html:option value="85">Guadeloupe</html:option>
							<html:option value="86">Guam</html:option>
							<html:option value="87">Guatemala</html:option>
							<html:option value="88">Guinea</html:option>
							<html:option value="89">Guinea-Bissau</html:option>
							<html:option value="90">Guyana</html:option>
							<html:option value="91">Haiti</html:option>
							<html:option value="92">Heard And Mc Donald Islands </html:option>
							<html:option value="93">Honduras</html:option>
							<html:option value="94">Hong Kong</html:option>
							<html:option value="95">Hungary</html:option>
							<html:option value="96">Iceland</html:option>
							<html:option value="97">India</html:option>
							<html:option value="98">Indonesia</html:option>
							<html:option value="99">Ireland</html:option>
							<html:option value="100">Israel</html:option>
							<html:option value="101">Italy</html:option>
							<html:option value="102">Jamaica</html:option>
							<html:option value="103">Japan</html:option>
							<html:option value="104">Jordan</html:option>
							<html:option value="105">Kazakhstan</html:option>
							<html:option value="106">Kenya</html:option>
							<html:option value="107">Kiribati</html:option>
							<html:option value="108">Kuwait</html:option>
							<html:option value="109">Kyrgyzstan</html:option>
							<html:option value="110">Lao Peoples Republic </html:option>
							<html:option value="111">Latvia</html:option>
							<html:option value="112">Lebanon</html:option>
							<html:option value="113">Lesotho</html:option>
							<html:option value="114">Liberia</html:option>
							<html:option value="115">Liechtenstein</html:option>
							<html:option value="116">Lithuania</html:option>
							<html:option value="117">Luxembourg</html:option>
							<html:option value="118">Macau</html:option>
							<html:option value="119">Macedonia</html:option>
							<html:option value="120">Madagascar</html:option>
							<html:option value="121">Malawi</html:option>
							<html:option value="122">Malaysia</html:option>
							<html:option value="123">Maldives</html:option>
							<html:option value="124">Mali</html:option>
							<html:option value="125">Malta</html:option>
							<html:option value="126">Marshall Islands</html:option>
							<html:option value="127">Martinique</html:option>
							<html:option value="128">Mauritania</html:option>
							<html:option value="129">Mauritius</html:option>
							<html:option value="130">Mayotte</html:option>
							<html:option value="131">Mexico</html:option>
							<html:option value="132">Micronesia</html:option>
							<html:option value="133">Moldova</html:option>
							<html:option value="134">Monaco</html:option>
							<html:option value="135">Mongolia</html:option>
							<html:option value="136">Montserrat</html:option>
							<html:option value="137">Morocco</html:option>
							<html:option value="138">Mozambique</html:option>
							<html:option value="139">Myanmar</html:option>
							<html:option value="140">Namibia</html:option>
							<html:option value="141">Nauru</html:option>
							<html:option value="142">Nepal</html:option>
							<html:option value="143">Netherlands</html:option>
							<html:option value="144">Netherlands  Antilles </html:option>
							<html:option value="145">New Caledonia</html:option>
							<html:option value="146">NewZealand</html:option>
							<html:option value="147">Nicaragua</html:option>
							<html:option value="148">Niger</html:option>
							<html:option value="149">Nigeria</html:option>
							<html:option value="150">Niue</html:option>
							<html:option value="151">Norfolk Island</html:option>
							<html:option value="152">Northern Mariana Islands </html:option>
							<html:option value="153">Norway</html:option>
							<html:option value="154">Oman</html:option>
							<html:option value="155">Pakistan</html:option>
							<html:option value="156">Palau</html:option>
							<html:option value="157">Palestinian  Territory </html:option>
							<html:option value="158">Panama</html:option>
							<html:option value="159">Papua New  Guinea</html:option>
							<html:option value="160">Paraguay</html:option>
							<html:option value="161">Peru</html:option>
							<html:option value="162">Philippines</html:option>
							<html:option value="163">Pitcairn</html:option>
							<html:option value="164">Poland</html:option>
							<html:option value="165">Portugal</html:option>
							<html:option value="166">Puerto Rico</html:option>
							<html:option value="167">Qatar</html:option>
							<html:option value="168">Reunion</html:option>
							<html:option value="169">Romania</html:option>
							<html:option value="170">Russian Federation</html:option>
							<html:option value="171">Rwanda</html:option>
							<html:option value="172">Saint Kitts And Nevis </html:option>
							<html:option value="173">Saint Lucia</html:option>
							<html:option value="174">Saint Vincent And The Grenadines </html:option>
							<html:option value="175">Samoa</html:option>
							<html:option value="176">San Marino</html:option>
							<html:option value="177">Sao Tome And Principe </html:option>
							<html:option value="178">Saudi Arabia</html:option>
							<html:option value="179">Senegal</html:option>
							<html:option value="180">Seychelles</html:option>
							<html:option value="181">Sierra Leone</html:option>
							<html:option value="182">Singapore</html:option>
							<html:option value="183">Slovakia</html:option>
							<html:option value="184">Slovenia</html:option>
							<html:option value="185">Solomon Islands</html:option>
							<html:option value="186">Somalia</html:option>
							<html:option value="187">South Africa</html:option>
							<html:option value="188">South Georgia</html:option>
							<html:option value="189">South Korea</html:option>
							<html:option value="190">The South Sandwich Islands </html:option>
							<html:option value="191">Spain</html:option>
							<html:option value="192">Sri Lanka</html:option>
							<html:option value="193">St Helena</html:option>
							<html:option value="194">St Pierre and Miquelon </html:option>
							<html:option value="195">Suriname</html:option>
							<html:option value="196">Svalbard And Jan Mayen Islands </html:option>
							<html:option value="197">Swaziland</html:option>
							<html:option value="198">Sweden</html:option>
							<html:option value="199">Switzerland</html:option>
							<html:option value="200">Syrian Arab Republic </html:option>
							<html:option value="201">Taiwan</html:option>
							<html:option value="202">Tajikistan</html:option>
							<html:option value="203">Tanzania</html:option>
							<html:option value="204">Thailand</html:option>
							<html:option value="205">Togo</html:option>
							<html:option value="206">Tokelau</html:option>
							<html:option value="207">Tonga</html:option>
							<html:option value="208">Trinidad And Tobago</html:option>
							<html:option value="209">Tunisia</html:option>
							<html:option value="210">Turkey</html:option>
							<html:option value="220">Turkmenistan</html:option>
							<html:option value="221">Turks And Caicos Islands </html:option>
							<html:option value="222">Tuvalu</html:option>
							<html:option value="223">Uganda</html:option>
							<html:option value="224">Ukraine</html:option>
							<html:option value="225">United Arab Emirates </html:option>
							<html:option value="226">United Kingdom</html:option>
							<html:option value="227">United States</html:option>
							<html:option value="228">US Minor Outlying Islands </html:option>
							<html:option value="229">Uruguay</html:option>
							<html:option value="230">Uzbekistan</html:option>
							<html:option value="231">Vanuatu</html:option>
							<html:option value="232">Vatican City State</html:option>
							<html:option value="233">Venezuela</html:option>
							<html:option value="234">Viet Nam</html:option>
							<html:option value="235">Virgin Islands (British) </html:option>
							<html:option value="236">Virgin Islands (U.S.) </html:option>
							<html:option value="237">Wallis And Futuna Islands </html:option>
							<html:option value="238">Western Sahara</html:option>
							<html:option value="239">Yemen</html:option>
							<html:option value="240">Yugoslavia</html:option>
							<html:option value="241">Zaire</html:option>
							<html:option value="242">Zambia</html:option>
							<html:option value="243">Zimbabwe</html:option>
						</html:select><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="country"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="zip"/> : </B></FONT>
					</TD>
					<TD>
						<html:text property="zip"/><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="zip"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="phno"/> : </B></FONT>
					</TD>
					<TD>
						<html:text property="phno"/><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="phno"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="email"/> : </B></FONT>
					</TD>
					<TD>
						<html:text property="email"/><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="email"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="idproof"/> : </B></FONT>
					</TD>
					<TD>
						<html:select property="idproof">
							<html:option value="0">[Select an Identity Proof] </html:option>
							<html:option value="pan">Permanent Account No </html:option>
							<html:option value="pp">Passport</html:option>
							<html:option value="dl">Driving Licence</html:option>
						</html:select><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="idproof"/></TD>
				</TR>
				<TR>
					<TD WIDTH="32%" ALIGN="RIGHT">
						<FONT FACE="VERDANA" SIZE="2" COLOR="#004300"><B><bean:message key="idno"/> : </B></FONT>
					</TD>
					<TD>
						<html:text property="idno"/><FONT COLOR=RED SIZE="2">*</FONT>
					</TD>
					<TD><html:errors property="idno"/></TD>
				</TR>
				<TR>
					<TD COLSPAN="2">
						<HR SIZE="1" COLOR="#00366C">
					</TD>
				<TR>
					<TD COLSPAN="2">
						<P ALIGN="JUSTIFY"><FONT FACE="VERDANA" SIZE="2" COLOR="BLACK">By submitting your registration information, you indicate that you agree to the terms and conditions and have read and understand the privacy policy. Your submission of this form will constitute your consent to the collection and use of this information and the transfer of this information for processing and storage by Bluewega Travels and its affiliates. You also agree to receive required administrative and legal notices such as this electronically. </FONT>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="27%"></TD>
					<TD ALIGN="LEFT"><BR>
						<html:submit>Register</html:submit>&nbsp;&nbsp;
						<html:reset>Reset</html:reset>
					</TD>
				</TR>
			</TD>
		</TR>
	</TABLE>
	</html:form>
	</CENTER>
	</BODY>
</HTML>