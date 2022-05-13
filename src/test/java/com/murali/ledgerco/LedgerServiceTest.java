package com.murali.ledgerco;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class LedgerServiceTest {

	
	@Test
	public void getScannerAndCommandsTest() throws FileNotFoundException {
		String inputPath = "sample_input/input1.txt";
		Scanner sc = new Scanner(new FileInputStream(inputPath));
		assertEquals(sc,LedgerService.getScanner(inputPath));
		List<String> commands = new ArrayList<>();
		while (sc.hasNextLine()) {
			commands.add(sc.nextLine());
		}
		assertEquals(commands,LedgerService.getRawCommnads(sc));
	}
	
	@Test
	public void getCommandsTest() {
		
	}
}
