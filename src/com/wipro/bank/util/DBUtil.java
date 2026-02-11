package com.wipro.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
public static Connection getConnection(){

try {
Class.forName("oracle.jdbc.driver.OracleDriver");
}
catch(ClassNotFoundException e){
e.printStackTrace();
}
String url = "jdbc:oracle:thin:@//localhost:1521/XE";
String user = "system";
String password = "System123";

try {
Connection connection = DriverManager.getConnection(url,user,password);
return connection;
}
catch(SQLException e){
e.printStackTrace();
}
return null;
}
}