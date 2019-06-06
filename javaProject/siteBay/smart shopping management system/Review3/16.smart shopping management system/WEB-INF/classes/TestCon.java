/*   */ import com.dts.core.dao.AbstractDataAccessObject;
/*   */ import java.io.PrintStream;
/*   */ import java.sql.Connection;
/*   */ 
/*   */ public class TestCon
/*   */ {
/*   */   public static void main(String[] args)
/*   */     throws Exception
/*   */   {
/* 7 */     AbstractDataAccessObject ss = new AbstractDataAccessObject();
/* 8 */     Connection con = ss.getConnection();
/* 9 */     System.out.println("conection=" + con);
/*   */   }
/*   */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     TestCon
 * JD-Core Version:    0.6.2
 */