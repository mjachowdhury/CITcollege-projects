/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;


/**
 * This class is about DB for the Dental Management
 * It will create the table and will connect and close connection with DB
 * It will also do the function add delete data for the Dental Management.
 * @author Mohammed
 *
 */
public class DBConnection {
	
    
    private String protocol = "jdbc:mysql://localhost:3306/";
    private String DBlocation = "dental";
    private Connection connection = null;
   
    public DBConnection(String name, String password){
    	    	
        try{
            connection = DriverManager.getConnection( protocol + DBlocation , name, password);
        }catch(SQLException  e){
            System.out.println("Database does not exist\nCreating datatbase.......");
            try {
                connection = DriverManager.getConnection( protocol + DBlocation + ";create=true" , name, password );
                
                //if i do not create database from the sql file then i can create with below method createDatabse()
                
               // createDatabase();
                
            }catch (SQLException ex) {
                System.out.println("Database " + DBlocation + " failed to create");
                ex.printStackTrace();
            }
            System.out.println("Database " + DBlocation + " created");
        }
    }
    
    
    
    /**
     * This method will create connection with db
     * @return
     */
    public Connection getConnection(){
        return this.connection;
    }
    
    /**
     * This method will close the DB connection 
     */
    public void closeConnection(){
        try {
            this.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method will close the statement 
     * @param statement
     */
    public void closeStatment(Statement statement){
        try {
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * This method will check is table empty or not and will return the value
     * @param table
     * @return
     */
    public Boolean isTableEmpty(String table){
        
        Boolean isEmpty = true;
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        
        sql = "SELECT * FROM " + table;
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            
            if(resultSet.next() == false){
                isEmpty = true;
            }else{isEmpty = false;}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        
        return isEmpty;
    }
    
    /**
     * This method will return string from the table and particular column
     * @param column
     * @param table
     * @return
     */
    public ArrayList<String> getStringDataColumn(String column, String table){//parameters Strings type of name, name of table
        ArrayList<String> data = new ArrayList<>();
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + column + " FROM " + table;
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        
        
        return data;
    }
    
    /**
     * This method will return Double data from the table 
     * @param column
     * @param table
     * @return
     */
    public ArrayList<Double> getDoubleDataColumn(String column, String table){//parameters Strings type of name, name of table
        ArrayList<Double> data = new ArrayList<>();
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + column + " FROM " + table;
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data.add(resultSet.getDouble(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        
        return data;
    }
    
    /**
     * This method will return String data from the table with where cluse
     * @param column
     * @param table
     * @param where
     * @param value
     * @return
     */
    
    public ArrayList<String> getStringDataColumnWithWhere(String column, String table, String where, String value){//parameters Strings type of name, name of table
        ArrayList<String> data = new ArrayList<>();
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + column + " FROM " + table + " WHERE " + where + " = '" + value + "'";
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    /**
     * This method will return String data from the table with particular order with where cluse
     * @param column
     * @param table
     * @param where
     * @param value
     * @return
     */
    public ArrayList<String> getStringDataColumnWithWhereSortNum(String column, String table, String where, String value){//parameters Strings type of name, name of table
        ArrayList<String> data = new ArrayList<>();
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + column + " FROM " + table + " WHERE " + where + " = '" + value + "' ORDER BY patientNumber ASC";
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    
    /**
     * This method will return Int type data from the table with where cluse
     * @param column
     * @param table
     * @param where
     * @param value
     * @return
     */
    public ArrayList<Integer> getIntDataColumnWithWhere(String column, String table, String where, int value){//parameters Strings type of name, name of table
        ArrayList<Integer> data = new ArrayList<>();
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + column + " FROM " + table + " WHERE " + where + " = " + value;
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data.add(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    
    /**
     * This method will return String size  from the table 
     * @param table
     * @param where
     * @param value
     * @return
     */
    public int getListSizeWithString(String table, String where, String value){//parameters Strings type of name, name of table
        int data = 0;
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT * FROM " + table + " WHERE " + where + " = '" + value + "'";
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    
    /**
     * This method will return Int type data from the table 
     * @param table
     * @param where
     * @param value
     * @return
     */
    public int getListSizeWithInt(String table, String where, int value){//parameters Strings type of name, name of table
        int data = 0;
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT * FROM " + table + " WHERE " + where + " = " + value;
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    
    /**
     * This method will return String data from the table
     * @param select
     * @param from
     * @param where
     * @param value
     * @return
     */
    public String getStringData(String select, String from, String where, String value){//parameters Strings type of name, name of table
        String data = "";
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = '" + value +  "'";
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data = resultSet.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    
    /**
     * This method will return String data with Int value from the table
     * @param select
     * @param from
     * @param where
     * @param value
     * @return
     */
    public String getStringDataWithInt(String select, String from, String where, int value){//parameters Strings type of name, name of table
        String data = "";
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = " + value;
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data = resultSet.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    
    /**
     * This method will return Date from the table with int value
     * @param select
     * @param from
     * @param where
     * @param value
     * @return
     */
    
    public Timestamp getDateWithInt(String select, String from, String where, int value){//parameters Strings type of name, name of table
        Timestamp data = null;
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = " + value;
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data = resultSet.getTimestamp(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    
    /**
     * This method will return Double value with int value from the table
     * @param select
     * @param from
     * @param where
     * @param value
     * @return
     */
    public double getDoubleWithInt(String select, String from, String where, int value){//parameters Strings type of name, name of table
        double data = 0.0;
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = " + value;
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data = resultSet.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    
    /**
     * This method will return Boolean value from the table
     * @param select
     * @param from
     * @param where
     * @param value
     * @return
     */
    public Boolean getBooleanWithInt(String select, String from, String where, int value){//parameters Strings type of name, name of table
        Boolean data = null;
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = " + value;
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data = resultSet.getBoolean(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    
    
    /**
     * This method will return Int data from the table
     * @param select
     * @param from
     * @param where
     * @param value
     * @return
     */
    public int getIntData(String select, String from, String where, String value){//parameters Strings type of name, name of table
        int data = -1;
        String sql;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = '" + value +  "'";
        try {
            statement = this.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                data = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        return data;
    }
    
    /**
     * This method will remove row from the table
     * @param from
     * @param where
     * @param value
     */
    public void removeRowWithString(String from, String where, String value){//parameters Strings type of name, name of table
        String sql, sqlDel;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT * FROM " + from + " WHERE " + where + " = '" + value +  "'";
        sqlDel = "DELETE FROM " + from + " WHERE " + where + " = '" + value + "'";
        try {
            statement = this.getConnection().createStatement();
            statement.execute(sqlDel);
            //while(resultSet.next()){
            //    resultSet.deleteRow();
            //}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        
    }
    
    /**
     * This method will remove row with int value from the table
     * @param from
     * @param where
     * @param value
     */
    public void removeRowWithInt(String from, String where, int value){//parameters Strings type of name, name of table
        String sql, sqlDel;
        Statement statement = null;
        ResultSet resultSet;
        sql = "SELECT * FROM " + from + " WHERE " + where + " = " + value;
        sqlDel = "DELETE FROM " + from + " WHERE " + where + " = " + value;
        try {
            statement = this.getConnection().createStatement();
            statement.execute(sqlDel);
            //while(resultSet.next()){
            //    resultSet.deleteRow();
            //}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(statement);
        }
        
    }
    
    
    /**
     * This method will add patient in the table 
     * @param name
     * @param address
     * @param dentist
     */
    public void addToPatientTable(String name, String address, /*String contact,*/ String dentist){
        Statement update = null;
        try {
            update = this.getConnection().createStatement();
            String sql;
            sql= "INSERT INTO Patients(patientName, patientAddress, patientDentist)"
                    + " VALUES('" + name + "','" + address + "','" + dentist + "')";
            update.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeStatment(update);
        }
    }
    
    /**
     * This method will add invoice to the table 
     * @param date
     * @param patNum
     */
    public void addToInvoiceTable(Date date, int patNum){
        Statement update = null;
        try {
            update = this.getConnection().createStatement();
            String sql;
            sql= "INSERT INTO Invoices(invoiceAmount ,invoiceDate, invoicePaid, invoicePatient)"
                    + " VALUES(0.0, '" + date.toString() + "', false, " + patNum + ")";
            update.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeStatment(update);
        }
    }
    
    /**
     * This method will update payment in the table 
     * @param value
     * @param num
     */
    public void updatePaid(Boolean value, int num){
        Statement update = null;
        try{
            update = this.getConnection().createStatement();
            String sql;
            sql= "UPDATE Invoices SET invoicePaid = " + value + " WHERE invoiceNumber = " + num;
            update.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            this.closeStatment(update);
        }
    }
    
    /**
     * This method will add procedure to the table 
     * @param name
     * @param cost
     * @param invNum
     */
    public void addToProcedureTable(String name, double cost, int invNum){
        Statement update = null;
        try {
            update = this.getConnection().createStatement();
            String sql;
            sql= "INSERT INTO Procedures(procedureName, procedureCost, procedureInvoice)"
                    + " VALUES('" + name + "'," + cost + "," + invNum + ")";
            update.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeStatment(update);
        }
    }
    
    /**
     * This method will add payment to the table 
     * @param amount
     * @param date
     * @param invNum
     */
    public void addToPaymentTable(Double amount, Date date, int invNum){
        Statement update = null;
        try {
            update = this.getConnection().createStatement();
            String sql;
            sql= "INSERT INTO Payments(paymentAmount, paymentDate, paymentInvoice)"
                    + " VALUES(" + amount + ",'" + date.toString() + "'," + invNum + ")";
            update.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeStatment(update);
        }
    }
    
    public void addToProcedureTypeTable(String name, double cost){ 
        Statement update = null;                                   
        try {
            update = this.getConnection().createStatement();
            String sql;
            sql= "INSERT INTO ProcedureTypes(typeName, typeCost)"
                    + " VALUES('" + name + "'," + cost + ")";
            update.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeStatment(update);
        }
    }
       
    //***** This part will create table in the database 
    
   /* private void createDatabase(){
        Statement initialiseTable = null;
        try {
            initialiseTable = this.getConnection().createStatement();
            String tableUpdate;
            
            tableUpdate = "CREATE TABLE dentists(\n" +
                            "    userName VARCHAR(20) NOT NULL,\n" +
                            "    userAddress VARCHAR(20),\n" +
                            "    userPassword varchar(20),\n" +
                            "    PRIMARY KEY(userName)\n" +
                            ")";
            
            tableUpdate = "CREATE TABLE `dental`.`dentists` ("+
            			  "		`userName` VARCHAR(20) NOT NULL,"+
            			  "		`userAddress` VARCHAR(20) NULL,"+
            			  "		`userPassword` VARCHAR(20) NULL, PRIMARY KEY (`userName`))";          
            initialiseTable.executeUpdate(tableUpdate);
            
            tableUpdate = "CREATE TABLE Patients(\n" +
                            "    patientNumber INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),\n" +
                            "    patientName VARCHAR(45),\n" +
                            "    patientAddress VARCHAR(45),\n" +
                            "    patientDentist VARCHAR(45),\n" +
                            "    PRIMARY KEY(patientNumber)\n" +
                            ")";
            
            tableUpdate = "CREATE TABLE `dental`.`patients` ("+
            			  "		`patientNumber` INT NOT NULL AUTO_INCREMENT,"+
            			  "		`patientName` VARCHAR(45) NULL,"+
            			  "		`patientAddress` VARCHAR(45) NULL,"+
            			  "		`patientDentist` VARCHAR(45) NULL,"+
            			  " 	 PRIMARY KEY (`patientNumber`), UNIQUE INDEX `patientNumber_UNIQUE` (`patientNumber` ASC))";          
            initialiseTable.executeUpdate(tableUpdate);
            
            tableUpdate = "CREATE TABLE Invoices(\n" +
                            "    invoiceNumber INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),\n" +
                            "    invoiceAmount DOUBLE,\n" +
                            "    invoiceDate VARCHAR(45),\n" +
                            "    invoicePaid BOOLEAN,\n" +
                            "    invoicePatient INT,\n" +
                            "    PRIMARY KEY(invoiceNumber)\n" +
                            ")";
            
            tableUpdate = "CREATE TABLE `dental`.`invoices` ("+
            				"	`invoiceNumber` INT NOT NULL AUTO_INCREMENT,"+
            				"	`invoiceAmount` DOUBLE NULL,"+
            				"	`invoiceDate` VARCHAR(45) NULL,"+
            				"	`invoicePaid` TINYINT NULL,"+
            				"	`invoicePatient` INT NULL,"+
            				"	 PRIMARY KEY (`invoiceNumber`),UNIQUE INDEX `invoiceNumber_UNIQUE` (`invoiceNumber` ASC))";
            initialiseTable.executeUpdate(tableUpdate);
            
            
            tableUpdate = "CREATE TABLE Procedures(\n" +
                            "    procedureNumber INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),\n" +
                            "    procedureName VARCHAR(45),\n" +
                            "    procedureCost DOUBLE,\n" +
                            "    procedureInvoice INT,\n" +
                            "    PRIMARY KEY(procedureNumber)\n" +
                            ")";
            
            tableUpdate = "CREATE TABLE `dental`.`procedures` ("+
            			  "		`procedureNumber` INT NOT NULL AUTO_INCREMENT,"+
            			  "		`procedureName` VARCHAR(45) NULL,"+
            			  "		`procedureCost` DOUBLE NULL,"+
            			  "		`procedureInvoice` INT NULL,"+
            			  "		 PRIMARY KEY (`procedureNumber`),"+
            			  "		 UNIQUE INDEX `procedureNumber_UNIQUE` (`procedureNumber` ASC))";       
            initialiseTable.executeUpdate(tableUpdate);
            
            
            tableUpdate = "CREATE TABLE Payments(\n" +
                            "    paymentNumber INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),\n" +
                            "    paymentAmount DOUBLE,\n" +
                            "    paymentDate VARCHAR(45),\n" +
                            "    paymentInvoice INT,\n" +
                            "    PRIMARY KEY(paymentNumber)\n" +
                            ")";
            
            tableUpdate = "CREATE TABLE `dental`.`payments` ("+
            			  "		`paymentNumber` INT NOT NULL AUTO_INCREMENT,"+
            			  "		`paymentAmount` DOUBLE NULL,"+
            			  "		`paymentDate` VARCHAR(45) NULL,"+
            			  "		`paymentInvoice` INT NULL,"+
            			  "		 PRIMARY KEY (`paymentNumber`),"+
            			  "		 UNIQUE INDEX `paymentNumber_UNIQUE` (`paymentNumber` ASC))";
            initialiseTable.executeUpdate(tableUpdate);
            
            
            
            tableUpdate = "CREATE TABLE ProcedureTypes(\n" +
                            "    procedureTypeNumber INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),\n" +
                            "    typeName VARCHAR(45),\n" +
                            "    typeCost DOUBLE,\n" +
                            "    PRIMARY KEY(procedureTypeNumber)\n" +
                            ")";
            initialiseTable.executeUpdate(tableUpdate);
            
            tableUpdate = "CREATE TABLE `dental`.`proceduretypes` ("+
            			  "		`procedureTypeNumber` INT NOT NULL AUTO_INCREMENT,"+
            			  "		`typeName` VARCHAR(45) NULL,"+
            			  "		`typeCost` DOUBLE NULL,"+
            			  "		 PRIMARY KEY (`procedureTypeNumber`),"+
            			  "		 UNIQUE INDEX `procedureTypeNumber_UNIQUE` (`procedureTypeNumber` ASC))";


            tableUpdate = "INSERT INTO Dentists VALUES('dentist00','address00','qwe')";
            initialiseTable.executeUpdate(tableUpdate);
            tableUpdate = "INSERT INTO Dentists VALUES('dentist01','address01','qwe')";
            initialiseTable.executeUpdate(tableUpdate);
            tableUpdate = "INSERT INTO Dentists VALUES('dentist02','address02','qwe')";
            initialiseTable.executeUpdate(tableUpdate);

            tableUpdate = "INSERT INTO ProcedureTypes(typeName, typeCost) VALUES('procType00',50.0)";
            initialiseTable.executeUpdate(tableUpdate);
            tableUpdate = "INSERT INTO ProcedureTypes(typeName, typeCost) VALUES('procType01',100.0)";
            initialiseTable.executeUpdate(tableUpdate);
            tableUpdate = "INSERT INTO ProcedureTypes(typeName, typeCost) VALUES('procType02',150.0)";
            initialiseTable.executeUpdate(tableUpdate);
            tableUpdate = "INSERT INTO ProcedureTypes(typeName, typeCost) VALUES('procType03',200.0)";
            initialiseTable.executeUpdate(tableUpdate);
            tableUpdate = "INSERT INTO ProcedureTypes(typeName, typeCost) VALUES('procType04',250.0)";
            initialiseTable.executeUpdate(tableUpdate);
            tableUpdate = "INSERT INTO ProcedureTypes(typeName, typeCost) VALUES('procType05',300.0)";
            initialiseTable.executeUpdate(tableUpdate);
            tableUpdate = "INSERT INTO ProcedureTypes(typeName, typeCost) VALUES('procType06',400.0)";
            initialiseTable.executeUpdate(tableUpdate);
            tableUpdate = "INSERT INTO ProcedureTypes(typeName, typeCost) VALUES('procType07',600.0)";
            initialiseTable.executeUpdate(tableUpdate);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.closeStatment(initialiseTable);
        }
                
    }*/

}
