package com.murali.ledgerco.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
	private static final int MONTHS_IN_YEAR = 12;
	private long amount;
	private long emiAmount;
	private List<Payment> payments;
	
	public Account(int principal, int term, int interest) {
		super();
		this.amount = (long)Math.ceil(principal*term*interestAsPercent(interest));
		this.emiAmount = (long)Math.ceil((double)this.amount/(term*MONTHS_IN_YEAR));
		this.payments = new ArrayList<>();
	}
	
	private double interestAsPercent(int interest) {
		return (double)interest/100;
	}

	public void addPayment(Payment payment) {
		payments.add(payment);
		Collections.sort(payments);
	}
	
	private long amountPaidInNextEmi(long payment) {
		long remainingDue = amount - payment;
		if(remainingDue>emiAmount) return emiAmount;
		else return remainingDue;
	}
	
	public long totalPaidByEmi(int emiNo) {
		long totalPaidExcludingFinal = emiAmount*(emiNo-1);
		long totalPaidInLumpSum = 0;
		for(int index=0;isInBounds(index)&&isBeforeEmi(index, emiNo);index++) {
			totalPaidInLumpSum+=payments.get(index).getPaymentAmount();
		}
		long finalEmiAmount = amountPaidInNextEmi(totalPaidExcludingFinal + totalPaidInLumpSum);
		long totalPaid = totalPaidExcludingFinal + totalPaidInLumpSum + finalEmiAmount;
		return totalPaid;
	}

	public int emisLeft(long totalPaid) {
		long amountDue = amount-totalPaid;
		int emis = (int)Math.ceil((double)amountDue/emiAmount);
		return emis;
	}
	
	private boolean isBeforeEmi(int index, int emiNo) {
		return payments.get(index).getEmiNumber()<emiNo;
	}

	private boolean isInBounds(int index) {
		return index<payments.size();
	}
	
}
