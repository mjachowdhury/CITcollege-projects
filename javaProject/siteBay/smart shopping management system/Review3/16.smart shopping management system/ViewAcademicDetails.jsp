
<%@page import="com.dts.dae.dao.UniversitesDao"%>
<%@page import="com.dts.core.util.CoreHash"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.dts.dae.dto.UniversityBean"%>
<%@page import="com.dts.dae.dao.InstitutionDao"%>
<%@page import="com.dts.dae.dao.DegreesDao"%>
<%@page import="com.dts.dae.dao.InstitutionBean"%>
<%@page import="com.dts.dae.dto.DegreeBean"%>
<%@page import="com.dts.dae.dao.AcademicStudentsDao"%>
<%@page import="com.dts.dae.dto.AcademicStudentBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>View Academic Details Page</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<%
	try{
	String sUniversityId = (String)request.getParameter("university");
	sUniversityId = sUniversityId==null?"":sUniversityId;
	
	String sCollegeId = (String)request.getParameter("college");
	sCollegeId = sCollegeId==null?"":sCollegeId;
	
	String sDegree = (String)request.getParameter("degree");
	sDegree = sDegree==null?"":sDegree;
	
	//sDegree="asdfdfd";
	
	String sYearOfPass = (String)request.getParameter("year");
	sYearOfPass = sYearOfPass==null?"":sYearOfPass;
	
	String sCast = (String)request.getParameter("cast");
	sCast = sCast==null?"":sCast;
	System.out.println("sUniversityId:"+sUniversityId+", sCollegeId:"+sCollegeId+", sDegree:"+sDegree+", sYearOfPass:"+sYearOfPass+", sCast:"+sCast);
%>

<script language="JavaScript" src="scripts/ts_picker.js"></script>
<script language="JavaScript">
function onPageLoad()
{
	//alert("Inside onPageLoad():"+"<%=sUniversityId%>");
	document.getElementById("university").value = "<%=sUniversityId%>";
	document.getElementById("college").value = "<%=sCollegeId%>";
	document.getElementById("year").value = "<%=sYearOfPass%>";
	document.getElementById("degree").value = "<%=sDegree%>";
	document.getElementById("cast").value = "<%=sCast%>";
	
	if("<%=sUniversityId%>" == "")
	{
		document.getElementById("college").disabled = true;
		document.getElementById("degree").disabled = true;
		document.getElementById("year").disabled = true;
		document.getElementById("cast").disabled = true;
	}else if("<%=sCollegeId%>" == "")
	{
		document.getElementById("degree").disabled = true;
		document.getElementById("year").disabled = true;
		document.getElementById("cast").disabled = true;
	}else if(document.getElementById("degree").selectedIndex == 0)
	{
		document.getElementById("year").disabled = true;
		document.getElementById("cast").disabled = true;
	}
}
function validatePage()
{
	alert("Inside validatePage()");
	
	return true;
}
function submitQuery(name)
{
	//alert("Inside submitQuery():"+name);
	var university = document.getElementById("university").value;
	var college = document.getElementById("college").value;
	var degree = document.getElementById("degree").value;
	var year = document.getElementById("year").value;
	var cast = document.getElementById("cast").value;
	
	var paraString = "";
	if(name=="university")
	{
		paraString = "university="+university;
	}else if(name=="college")
	{
		paraString = "university="+university+"&college="+college;
	}else if(name=="degree")
	{
		paraString = "university="+university+"&college="+college+"&degree="+degree;
	}	
	else
	{
		paraString = "university="+university+"&college="+college+"&degree="+degree+"&year="+year+"&cast="+cast;
	}
	
	//paraString = "university="+university+"&college="+college+"&degree="+degree+"&year="+year+"&cast="+cast;
	//alert("paraString:"+paraString); 
	location.href="ViewAcademicDetails.jsp?"+paraString;	
}
</script>
<body onload="onPageLoad()">

<!-- start header -->
<div id="logo">
	<h1><jsp:include page="header.html"/></h1>
	</div>
<div id="menu">
	<ul>
		<jsp:include page="generaloptions.jsp"/>
	</ul>
</div>

<!-- end header -->
<!-- start page -->
<center><hr/><br/><h2 style="color:white">View Academic Details</h2><br/>
<table width="765" border="0" cellpadding="0" cellspacing="0" class="bluestripeback">
  <tr align="center" valign="top">
    <td><table width="625" border="0" cellspacing="0" cellpadding="0">
        <tr align="center" valign="middle"> 
        </tr>
        <tr align="left" valign="top"> 
          <td width="30">&nbsp;</td>
          <!-- InstanceBeginEditable name="content" -->
          <td width="565" height="50">
          <%
          		String sQueryString = "select * from students";
          		String sStatus = (String)request.getParameter("status");
          		UniversitesDao universitesDao = new UniversitesDao();
          		InstitutionDao institutionDao = new InstitutionDao();
          		DegreesDao degreesDao = new DegreesDao();
          		
          		UniversityBean universityBean = null;
          		InstitutionBean institutionBean = null;
          		
          		/*
          		institutionBean = new InstitutionBean();
				institutionBean.setInstitutionId("inst_8");
				institutionBean.setUniversityId("uni Id");
				institutionBean.setInstitutionName("Institute Name");
				institutionBean.setRegdYear("1-Jan-2000");
				institutionBean.setLocation("bvrm");
				institutionBean.setPhone("456545");
				institutionBean.setFax("123456");
				institutionBean.setWebsite("www.web.com");
				new InstitutionDao().addInstitute(institutionBean); */
          		
          		DegreeBean degreeBean = null;
          		CoreHash chUniversities =  universitesDao.listUniversities();
          		CoreHash chInstitutions =  new CoreHash();
          		if(sUniversityId.trim().length() >0)
          		{
          			chInstitutions =  institutionDao.listUniversityInstitutes(sUniversityId);
          		}
          		CoreHash chDegrees =  new CoreHash();;
          		if(sCollegeId.trim().length() >0)
          		{
          			chDegrees =  degreesDao.listInstitutionDegrees(sCollegeId);
          			sQueryString += " where institute_id='"+sCollegeId+"'";
          		}
          		
          		if(sDegree.trim().length() >0)
          		{
          			sQueryString += " and deg_id='"+sDegree+"'";
          		}
          		if(sYearOfPass.trim().length() >0)
          		{
          			sQueryString += " and year_of_pass='"+sYearOfPass+"'";
          		}
          		if(sCast.trim().length() >0)
          		{
          			sQueryString += " and cast='"+sCast+"'";
          		}
          		
          		Enumeration enUniversities = chUniversities.elements();
          		Enumeration enInstitutes = chInstitutions.elements();
          		Enumeration enDegrees = chDegrees.elements();
          %>
            <br/> <center><span style="color:white">
              <h3><%=sStatus==null?"":sStatus%></h3></span>
            </center>
            <center>
              <form action="ViewAcademicDetails.jsp" method="post" name="register">
                <table width="440" border="1" cellpadding="1" cellspacing="0" bgcolor="white">
                  <tbody>
                    <tr> 
                      <td valign="top"><strong>University</strong></td>
                      <td valign="top"><strong>Institute</strong></td>
                      <td valign="top"><strong>Degree</strong></td>
                      <td valign="top"><strong>Year of Pass</strong></td>
                      <td valign="top"><strong>Cast</strong></td>
                    </tr>
                    <tr>
                    	<td valign="top"><select name="university" id="university" onchange="submitQuery('university')">
                    		<option value="">[Select University]</option>
                    	<%
                    		while(enUniversities.hasMoreElements())     
            				{
            					universityBean = (UniversityBean)enUniversities.nextElement();
                    	%>
	                      	<option value="<%=universityBean.getUniversityId()%>"><%=universityBean.getUniversityName()%></option>
	                     <%
	                     	} 	
	                     %>
	                      </select>
	                    </td>
	                    <td valign="top"><select name="college" id="college" onchange="submitQuery('college')">
	                      	<option value="">[Select College]</option>
                    	<%
                    		while(enInstitutes.hasMoreElements())     
            				{
            					institutionBean = (InstitutionBean)enInstitutes.nextElement();
                    	%>
	                      	<option value="<%=institutionBean.getInstitutionId()%>"><%=institutionBean.getInstitutionName() %></option>
	                     <%
	                     	} 	
	                     %>
	                      </select>
	                    </td>
	                    <td valign="top"><select name="degree" id="degree" onchange="submitQuery('degree')">
	                      	<option value="">[Select College]</option>
                    	<%
                    		while(enDegrees.hasMoreElements())     
            				{
            					degreeBean = (DegreeBean)enDegrees.nextElement();
                    	%>
	                      	<option value="<%=degreeBean.getDegreeId()%>"><%=degreeBean.getDegreeName()%></option>
	                     <%
	                     	} 	
	                     %>
	                      </select>
	                    </td>
	                    <td valign="top"><select name="year" id="year" onchange="submitQuery('year')">
	                      	<option value="">[Select YearOfPass]</option>
	                      	<option value="1999-2000">1999-2000</option>
	                      	<option value="2000-2001">2000-2001</option>
	                      	<option value="2001-2002">2001-2002</option>
	                      	<option value="2002-2003">2002-2003</option>
	                      </select>
	                    </td>
	                    <td valign="top"><select name="cast" id="cast" onchange="submitQuery('cast')">
	                      	<option value="">[Select Cast]</option>
	                      	<option value="ST">ST</option>
	                      	<option value="SC">SC</option>
	                      	<option value="OBC">OBC</option>
	                      	<option value="OC">OC</option>
	                      </select>
	                    </td>
                    </tr>
                  </tbody>
                </table>
              </form>
            </center>            </td>
          <!-- InstanceEndEditable --> 
          <td width="30">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
</table>
<table border="1"><tr><td>
<div id="page">
	<div id="page-bg">
		<!-- start content -->
		<!-- end content -->
        <!-- start sidebar -->
<div id="centent">
			<form id="form1" method="post" action="">
			  <fieldset>
			  <legend>Academic Search Results</legend>
              <table width="259" border="1" align="center">
                <tr>
                  <td nowrap><strong>Hall Ticket No</strong></td>
                  <td nowrap><strong>Student Name</strong></td>
                  <td nowrap><strong>Institute Name</strong></td>
                  <td nowrap><strong>Degree</strong></td>
                  <td nowrap><strong>Date of Birth</strong></td>
                  <td nowrap><strong>Rank</strong></td>
                  <td nowrap><strong>Cast</strong></td>
                  <td nowrap><strong>Year of Pass</strong></td>
                </tr>
                
                <%
	                AcademicStudentsDao academicStudentsDao = new AcademicStudentsDao();
					CoreHash chStudents = academicStudentsDao.listAcademicStudents(sQueryString);
					Enumeration enStudents = chStudents.elements();
                	boolean bFound = false;
                	AcademicStudentBean academicStudentBean = null;
                   	while(enStudents.hasMoreElements())     
            		{
            			bFound = true;
           				academicStudentBean = (AcademicStudentBean)enStudents.nextElement();
                    %>
                    <tr>
	              	<td align="left"><%=academicStudentBean.getHallTicketNo()%></td>
	              	<td align="left"><%=academicStudentBean.getFirstName()%>&nbsp;<%=academicStudentBean.getLastName()%></td>
	              	<td align="left"><%=new InstitutionDao().getInstitutionName(academicStudentBean.getInstituteId())%></td>
	              	<td align="left"><%=new DegreesDao().getDegreeName(academicStudentBean.getDegreeId())%></td>
	              	<td align="left"><%=academicStudentBean.getDateOfBirth()%></td>
	              	<td align="left"><%=academicStudentBean.getRank()%></td>
	              	<td align="left"><%=academicStudentBean.getCast()%></td>
	              	<td align="left"><%=academicStudentBean.getYearOfPass()%></td>
	              	</tr>
	              	
	             <%
	               	}
	               	if(!bFound)
	               	{ 	
	              %>
	              <tr>
	              	<td colspan="8">No Search Results found</td>
	              </tr>
	              <%
	              	}
	              %>
              </table>
			  <p>&nbsp;</p>
			  </fieldset>
			  </form>
	  </div>
    <!-- end sidebar -->
		<div style="clear: both;">&nbsp;</div>
  </div>
</div>
</td></tr></table>
</center>	
<!-- end page -->
<div id="footer">
	<p id="legal"><a href="#">Privacy Policy</a> | <a href="#">Terms of Use</a></p>
</div>
</body>
<%
	}catch(Exception e){e.printStackTrace();}
%>
</html>
