package com.murali.ledgerco;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.murali.ledgerco.model.Account;
import com.murali.ledgerco.model.Borrower;

public class Ledger {
	// Variable that contains all accounts managed by company
	public static Map<Borrower, Account> accounts;
	static {
		accounts = new HashMap<>();
	}

	public static void main(String[] args) {
		try {
			
			Scanner sc = LedgerService.getScanner();
			List<String> commands = LedgerService.getRawCommnads(sc);
			
			for (String rawCommand : commands) {
				
				String[] command = rawCommand.split(" ");
				String commandType = command[0];
				Borrower borrower = LedgerService.getBorrower(command);
				
				switch (commandType) {
				case "LOAN": {
					LedgerService.createLoanAccount(borrower,command);
					break;
				}
				case "PAYMENT": {
					LedgerService.makePayment(borrower,command);
					break;
				}
				case "BALANCE": {
					LedgerService.showBalance(borrower,command);
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
