package com.wipro.bank.service;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.dao.BankDAO;
import com.wipro.bank.util.InsufficientFundsException;

public class BankService {

public String checkBalance(String accountNumber) {

BankDAO bankDAO = new BankDAO();
if(bankDAO.validateAccount(accountNumber)) {
float balance=bankDAO.findBalance(accountNumber);
return "BALANCE IS : "+balance;
}else {
return "ACCOUNT NUMBER INVALID";
}
}

public String transfer(TransferBean transferBean) {

   if (transferBean == null) return "INVALID";

   BankDAO dao = new BankDAO();

   String from = transferBean.getFromAccountNumber();
   String to = transferBean.getToAccountNumber();
   float amount = transferBean.getAmount();

   try {
       if (!dao.validateAccount(from) || !dao.validateAccount(to)) {
           return "INVALID ACCOUNT";
       }

       if (amount <= 0) {
           return "INVALID AMOUNT";
       }
       float fromBal = dao.findBalance(from);
       if (fromBal < amount) {
           throw new InsufficientFundsException();
       }
       boolean credit = dao.updateBalance(to, dao.findBalance(to) + amount);
       boolean debit = dao.updateBalance(from, fromBal - amount);

       if (!debit || !credit) {
           return "FAILED";
       }

       dao.transferMoney(transferBean);
       return "SUCCESS";

   } catch (InsufficientFundsException e) {
       return "INSUFFICIENT BALANCE";
   }
   
}

}
