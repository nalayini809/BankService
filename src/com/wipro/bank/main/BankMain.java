package com.wipro.bank.main;
import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.service.BankService;
public class BankMain {
public static void main(String[] args) {
	BankService bankService = new BankService();
    System.out.println(bankService.checkBalance("1234567891"));
    TransferBean transferBean = new TransferBean();
    transferBean.setFromAccountNumber("1234567891");
    transferBean.setAmount(200.25f);
    transferBean.setToAccountNumber("1234567893");
    transferBean.setDateofTransaction(new java.util.Date());
    try {
         System.out.println(bankService.transfer(transferBean));
} catch (Exception e) {
e.printStackTrace();
}
}
}
