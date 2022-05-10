package com.murali.ledgerco;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.murali.ledgerco.model.Account;
import com.murali.ledgerco.model.Borrower;
import com.murali.ledgerco.model.Payment;

public class Ledger {
	// Variable that contains all accounts managed by company
	public static Map<Borrower, Account> accounts;
	static {
		accounts = new HashMap<>();
	}

	public static void main(String[] args) {
		try {
			// the file to be opened for reading
			FileInputStream fis = new FileInputStream("sample_input/input1.txt");
			Scanner sc = new Scanner(fis);
			List<String> commands = new ArrayList<>();
			while (sc.hasNextLine()) {
				// processing input commands
				commands.add(sc.nextLine());
			}
			for (String rawCommand : commands) {
				String[] command = rawCommand.split(" ");
				String commandType = command[0];
				switch (commandType) {
				case "LOAN": {
					String bankName = command[1];
					String borrowerName = command[2];
					Borrower newBorrower = new Borrower(bankName, borrowerName);
					int principal = Integer.parseInt(command[3]);
					int term = Integer.parseInt(command[4]);
					int interest = Integer.parseInt(command[5]);
					Account borrowerAccount = new Account(interest, term, principal);
					accounts.put(newBorrower, borrowerAccount);
					break;
				}
				case "PAYMENT": {
					String bankName = command[1];
					String borrowerName = command[2];
					Borrower borrower = new Borrower(bankName, borrowerName);
					long paymentAmount = Long.parseLong(command[3]);
					int emiNo = Integer.parseInt(command[4]);
					Payment payment = new Payment(paymentAmount, emiNo);
					accounts.get(borrower).addPayment(payment);
					break;
				}
				case "BALANCE": {
					String bankName = command[1];
					String borrowerName = command[2];
					Borrower borrower = new Borrower(bankName, borrowerName);
					int emiNo = Integer.parseInt(command[3]);;
					long totalPaid = accounts.get(borrower).totalPaidByEmi(emiNo);
					int emis = accounts.get(borrower).emisLeft(totalPaid);
					System.out.println(bankName + " " + borrowerName + " " + totalPaid + " " + emis);
					break;
				}
				}
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}