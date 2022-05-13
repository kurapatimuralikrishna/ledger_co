package com.murali.ledgerco;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.murali.ledgerco.model.Account;
import com.murali.ledgerco.model.Borrower;

public class LedgerTest {
	@Test
	public void checkAccounts() {
		assertEquals(new HashMap<Borrower,Account>(),Ledger.accounts);
	}
	//Test account instantiation
	//Check getScanner and getCommands methods
	//when new borrower - test hash code created and equals method
	//when new account - test loan amount and EMI amount
	//when payment created, test amount and compare to
	//when payment added test if present
	//Check if different methods of account are functioning properly
	//check Ledger service methods
}