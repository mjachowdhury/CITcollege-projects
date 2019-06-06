<%@ page errorPage="Malicious.jsp" isErrorPage="false"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("resumedir");
	}
	public class Uploader {
	JspWriter out;
	HttpServletResponse response;
	HttpServletRequest request;
	HttpSession session;
	String contextpath;
	boolean upload=false;
	public Uploader(JspWriter browser,HttpServletRequest req,HttpServletResponse res,HttpSession session) {
	try{
		this.out=browser;
		this.response=res;
		this.request=req;
		this.session=session;
		this.contextpath=this.request.getRealPath("\\");
	    } catch(Exception e) { System.out.println("Error in Constructor:\t"+e);}
	}
		
	public boolean uploadResume() throws Exception {
	String Contenttype=request.getContentType();
	if((Contenttype!=null)&&(Contenttype.indexOf("multipart/form-data")>=0)) {
	DataInputStream in=new DataInputStream(request.getInputStream());
	int clength=request.getContentLength();
	byte data[]=new byte[clength];
	int byteread=0;
	int totalread=0;
	while(totalread<clength) {
			byteread=in.read(data,totalread,clength);
			totalread+=byteread;
		}
	String file=new String(data);
	String save=file.substring(file.indexOf("filename=\"")+10);
	save=save.substring(0,save.indexOf("\n"));
	save=save.substring(save.lastIndexOf("\\")+1,save.indexOf("\""));
	int lastindex=Contenttype.lastIndexOf("=");
	String boundary=Contenttype.substring(lastindex+1,Contenttype.length());
	int pos;
	pos=file.indexOf("filename=\"");
	pos=file.indexOf("\n",pos)+1;
	pos=file.indexOf("\n",pos)+1;
	pos=file.indexOf("\n",pos)+1;
	int boundloc=file.indexOf(boundary,pos)-4;
	int startpos=((file.substring(0,pos)).getBytes()).length;
	int endpos=((file.substring(0,boundloc)).getBytes()).length;
	String filename=session.getAttribute("Client").toString()+"_resume.doc";
	String split=System.getProperty("file.separator");
	File f=new File(database);
	if(!f.exists()) {upload=false;return upload;}
	FileOutputStream fout=new FileOutputStream(database+split+filename);
	fout.write(data,startpos,(endpos-startpos));
	fout.flush();
	fout.close();
	upload=true;
		} //for if
		return upload;
		} 
	} 
%>
<%
   response.setHeader("Cahce-Control","no-cache");
   response.setHeader("Cache-Control","no-store");
   response.setHeader("Pragma","no-cache");
   response.setDateHeader("Expires",0);
   if(session.getAttribute("Client")==null) throw new Exception("Invalid Session. Please Login To Activate Your Account");
   Uploader upload=new Uploader(out,request,response,session);
   boolean uploadfile=upload.uploadResume();
   String filename=session.getAttribute("Client").toString()+"_resume.doc";
   String warn="Error Occured In Uploading Resume.\\n\\n";
   warn+="Might be Target Directory or Database Destination Not Found.\\n\\n";
   warn+="Please Report This To Administrator";
   if(uploadfile) {
	   out.println("<SCRIPT>");
	   out.println("parent.opener.document.forms[1].resume.value='"+filename+"'");
   	   out.println("setTimeout('parent.close()',10000);");
	   out.println("</script>");
   } else out.println("<SCRIPT>alert('"+warn+"');parent.location='Upload.jsp'</SCRIPT>");
%>