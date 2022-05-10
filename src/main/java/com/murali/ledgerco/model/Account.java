package com.murali.ledgerco.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
	private long amount;
	private long emiAmount;
	//map of payments done with EMI number as key
	private List<Payment> payments;
	public Account(int principal, int term, int interest) {
		super();
		this.amount = principal*term*interest;
		this.emiAmount = (long)Math.ceil(this.amount/(term*12.0));
		this.payments = new ArrayList<>();
	}
	
	/**
	 * payment method - adds lump sum payment to list of payments
	 * @param payment
	 */
	public void addPayment(Payment payment) {
		payments.add(payment);
		Collections.sort(payments);
	}
	//balance method - returns balance
	/**
	 * @param emiNo - all payments done before and during this EMI will be counted
	 * @return - total payments done for executing BALANCE command
	 */
	public long totalPaidByEmi(int emiNo) {
		long totalPaid = emiAmount*emiNo;
		for(int index=0;payments.get(index).getEmiNumber()<emiNo;index++) {
			totalPaid+=payments.get(index).getPaymentAmount();
		}
		return totalPaid;
	}
	//EMIs left method - returns remaining EMIs
	/**
	 * Calculates amount due using
	 * @param totalPaid - amount paid up until this EMI and
	 * @return - the number of EMIs left to clear the loan
	 */
	public int emisLeft(long totalPaid) {
		long amountDue = amount-totalPaid;
		int emis = (int)Math.ceil((double)amountDue/emiAmount);
		return emis;
	}
	
}
