package com.wipro.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.util.DBUtil;

public class BankDAO {

public int generateSequenceNumber() {
Connection connection = DBUtil.getConnection();
String query = "SELECT transactionId_seq.NEXTVAL from dual";
try {
PreparedStatement ps = connection.prepareStatement(query);
ResultSet rs = ps.executeQuery();
rs.next();
int seqNumber = rs.getInt(1);
return seqNumber;
}catch(SQLException e) {
e.printStackTrace();
return 0;
}
}
public boolean validateAccount(String accountNumber) {

Connection connection = DBUtil.getConnection();
String query = "SELECT * FROM ACCOUNT_TBL WHERE Account_Number=?";
   
try {
PreparedStatement ps = connection.prepareStatement(query);
ps.setString(1,accountNumber);
ResultSet rs = ps.executeQuery();
if(rs.next()) {
return true;
}
return false;
}catch(SQLException e) {
e.printStackTrace();
return false;

}

}
public float findBalance(String accountNumber){

Connection connection = DBUtil.getConnection();
String query = "SELECT Balance FROM ACCOUNT_TBL WHERE Account_Number=?";
try {
PreparedStatement ps = connection.prepareStatement(query);
ps.setString(1,accountNumber);
ResultSet rs = ps.executeQuery();
if (rs.next()) {                
           return rs.getFloat(1);
       } else {
           return -1;
       }
}catch(SQLException e){
e.printStackTrace();
return -1;
  }

}

public boolean transferMoney(TransferBean transferBean) {
transferBean.setTransactionID(generateSequenceNumber());
Connection connection = DBUtil.getConnection();
String query = "INSERT INTO TRANSFER_TBL VALUES (?,?,?,?,?)";
 try {
 PreparedStatement ps = connection.prepareStatement(query);
     ps.setInt(1, transferBean.getTransactionID());
     ps.setString(2, transferBean.getFromAccountNumber());
     ps.setString(3, transferBean.getToAccountNumber());
     Date d = new Date(transferBean.getDateofTransaction().getTime());
     ps.setDate(4,d);
     ps.setFloat(5, transferBean.getAmount());
     
     int rows =ps.executeUpdate();
     if(rows>0) {
     return true;
     }

 }catch(SQLException e) {
 e.printStackTrace();
 return false;
 }
 return false;
}

public boolean updateBalance(String accountNumber,float newBalance) {

if(validateAccount(accountNumber)) {
Connection connection = DBUtil.getConnection();
String query = "UPDATE ACCOUNT_TBL SET balance=? WHERE account_number=?";
try {
PreparedStatement ps = connection.prepareStatement(query);
ps.setFloat(1, newBalance);
ps.setString(2,accountNumber);

ps.executeUpdate();
   return true;  
}catch(SQLException e) {
e.printStackTrace();
return false;
}
}
return false;

}
}
