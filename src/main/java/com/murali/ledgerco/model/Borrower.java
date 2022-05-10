package com.murali.ledgerco.model;

public class Borrower {
	private String bankName;
	private String borrowerName;
	private int hashCode;
	public Borrower(String bankName, String borrowerName) {
		super();
		this.bankName = bankName;
		this.borrowerName = borrowerName;
		this.hashCode = (bankName+borrowerName).hashCode();
	}
	public String getBankName() {
		return bankName;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	//Overriding methods to make sure Borrower is a good key.
	@Override
	public int hashCode() {
		return hashCode;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null) return false;
		if(this.getClass()!=obj.getClass()) return false;
		Borrower borrower = (Borrower) obj;
		if(this.bankName.equals(borrower.bankName)&&this.borrowerName.equals(borrower.borrowerName)) return true;
		return false;
	}
}
