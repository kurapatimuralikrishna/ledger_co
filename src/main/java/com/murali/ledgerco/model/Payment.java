package com.murali.ledgerco.model;

public class Payment implements Comparable<Payment> {
	private long paymentAmount;
	private int emiNumber;
	public Payment(long paymentAmount, int emiNumber) {
		super();
		this.paymentAmount = paymentAmount;
		this.emiNumber = emiNumber;
	}
	public long getPaymentAmount() {
		return paymentAmount;
	}
	public int getEmiNumber() {
		return emiNumber;
	}
	@Override
	public int compareTo(Payment o) {
		if(this.emiNumber<o.emiNumber) return -1;
		else if(this.emiNumber==o.emiNumber) return 0;
		else return 1;
	}
}
