package com.murali.ledgerco;

import com.murali.ledgerco.model.Account;
import com.murali.ledgerco.model.Borrower;
import com.murali.ledgerco.model.Payment;

public class LedgerService {

	public static void createLoanAccount(String[] command) {
		
		String bankName = command[1];
		String borrowerName = command[2];
		Borrower newBorrower = new Borrower(bankName, borrowerName);
		
		int principal = Integer.parseInt(command[3]);
		int term = Integer.parseInt(command[4]);
		int interest = Integer.parseInt(command[5]);
		Account borrowerAccount = new Account(interest, term, principal);
		
		Ledger.accounts.put(newBorrower, borrowerAccount);
	}

	public static void makePayment(String[] command) {
		
		String bankName = command[1];
		String borrowerName = command[2];
		Borrower borrower = new Borrower(bankName, borrowerName);
		Account account = Ledger.accounts.get(borrower);
		
		long paymentAmount = Long.parseLong(command[3]);
		int emiNo = Integer.parseInt(command[4]);
		Payment payment = new Payment(paymentAmount, emiNo);
		
		account.addPayment(payment);
	}

	public static void showBalance(String[] command) {
		
		String bankName = command[1];
		String borrowerName = command[2];
		Borrower borrower = new Borrower(bankName, borrowerName);
		Account account = Ledger.accounts.get(borrower);
		
		int emiNo = Integer.parseInt(command[3]);;
		long totalPaid = account.totalPaidByEmi(emiNo);
		int emis = account.emisLeft(totalPaid);
		
		System.out.println(bankName + " " + borrowerName + " " + totalPaid + " " + emis);
	}

}
