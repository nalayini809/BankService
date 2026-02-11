package com.wipro.bank.bean;
import java.util.Date;
public class TransferBean {
	private int transactionID;
	private String fromAccountNumber;
	private String toAccountNumber;
	private Date dateofTransaction;
	private float amount;
	public final int getTransactionID() {
		return transactionID;
	}
	public final void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public final String getFromAccountNumber() {
		return fromAccountNumber;
	}
	public final void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	public final String getToAccountNumber() {
		return toAccountNumber;
	}
	public final void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	public final Date getDateofTransaction() {
		return dateofTransaction;
	}
	public final void setDateofTransaction(Date dateofTransaction) {
		this.dateofTransaction = dateofTransaction;
	}
	public final float getAmount() {
		return amount;
	}
	public final void setAmount(float amount) {
		this.amount = amount;
	}
}
