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
					LedgerService.createLoanAccount(command);
					break;
				}
				case "PAYMENT": {
					LedgerService.makePayment(command);
					break;
				}
				case "BALANCE": {
					LedgerService.showBalance(command);
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
