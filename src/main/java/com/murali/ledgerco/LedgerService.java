package com.murali.ledgerco;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.murali.ledgerco.model.Account;
import com.murali.ledgerco.model.Borrower;
import com.murali.ledgerco.model.Payment;

public class LedgerService {
	

	public static void createLoanAccount(Borrower borrower, String[] command) {
		
		int principal = Integer.parseInt(command[3]);
		int term = Integer.parseInt(command[4]);
		int interest = Integer.parseInt(command[5]);
		Account borrowerAccount = new Account(principal, term, interest);
		
		Ledger.accounts.put(borrower, borrowerAccount);
	}

	public static void makePayment(Borrower borrower, String[] command) {
		
		Account account = Ledger.accounts.get(borrower);
		
		long paymentAmount = Long.parseLong(command[3]);
		int emiNo = Integer.parseInt(command[4]);
		Payment payment = new Payment(paymentAmount, emiNo);
		
		account.addPayment(payment);
	}

	public static void showBalance(Borrower borrower, String[] command) {
		
		Account account = Ledger.accounts.get(borrower);
		
		int emiNo = Integer.parseInt(command[3]);;
		long totalPaid = account.totalPaidByEmi(emiNo);
		int emis = account.emisLeft(totalPaid);
		
		System.out.println(borrower.getBankName() + " " + borrower.getBorrowerName() + " " + totalPaid + " " + emis);
	}

	public static Borrower getBorrower(String[] command) {
		String bankName = command[1];
		String borrowerName = command[2];
		Borrower borrower = new Borrower(bankName, borrowerName);
		return borrower;
	}

	static List<String> getRawCommnads(Scanner sc) {
		List<String> commands = new ArrayList<>();
		while (sc.hasNextLine()) {
			commands.add(sc.nextLine());
		}
		return commands;
	}

	public static Scanner getScanner(String inputPath) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(inputPath);
		Scanner sc = new Scanner(fis);
		return sc;
	}

}
