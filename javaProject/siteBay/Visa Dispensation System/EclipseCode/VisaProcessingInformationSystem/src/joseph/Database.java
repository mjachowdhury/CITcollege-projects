package joseph;

import java.sql.*;

public class Database
{

    public Database()
        throws Exception
    {
        Con = null;
        st = null;
        rs = null;
        Class.forName("com.mysql.jdbc.Driver");
        Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/visa", "root", "root");
        st = Con.createStatement();
    }

    public ResultSet getResultSet(String s)
        throws Exception
    {
        rs = st.executeQuery(s);
        return rs;
    }

    public int setResultSet(String s)
        throws Exception
    {
        int i = st.executeUpdate(s);
        return i;
    }

    public void closeResultSet()
        throws Exception
    {
        rs.close();
        st.close();
        Con.close();
    }

    public void closeSetResultSet()
        throws Exception
    {
        st.close();
        Con.close();
    }

    private Connection Con;
    private Statement st;
    private ResultSet rs;
}