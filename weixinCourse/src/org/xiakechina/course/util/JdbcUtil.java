package org.xiakechina.course.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JdbcUtil { 
    private static String dbUrl = "jdbc:mysql://";
    public static String port;
    public static String host;
    public static String username;
    public static String password;
    public static String databaseName = "zJtjKTokkLUoGqQZMBkC";
  
    //拒绝new一个实例  
    private JdbcUtil() {};  
  
    static {//注册驱动  
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (ClassNotFoundException e) {  
            throw new ExceptionInInitializerError(e);
        }  
    }  

    public static Connection getConnection() throws SQLException {
    	String connName = dbUrl + host + ":" + port + "/" + databaseName;
        return DriverManager.getConnection(connName);
    }  
}
