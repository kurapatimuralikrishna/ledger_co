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
	/**
	 * Given @param payment calculates the EMI to be paid in next emi. 
	 * For final EMI of loan, this @return remaining due and EMI amount for other EMIs.
	 */
	private long paidInNextEmi(long payment) {
		long remainingDue = amount - payment;
		if(remainingDue>emiAmount) return emiAmount;
		else return remainingDue;
	}
	//balance method - returns balance
	/**
	 * @param emiNo - all payments done before and during this EMI will be counted
	 * @return - total payments done for executing BALANCE command
	 */
	public long totalPaidByEmi(int emiNo) {
		long totalPaidExcludingFinal = emiAmount*(emiNo-1);
		long totalPaidInLumpSum = 0;
		for(int index=0;index<payments.size()&&payments.get(index).getEmiNumber()<emiNo;index++) {
			totalPaidInLumpSum+=payments.get(index).getPaymentAmount();
		}
		long finalEmiAmount = paidInNextEmi(totalPaidExcludingFinal + totalPaidInLumpSum);
		long totalPaid = totalPaidExcludingFinal + totalPaidInLumpSum + finalEmiAmount;
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
