package com.jkr.game.io;

import java.util.Scanner;

public class ConsoleIO {
	
	private static final Scanner scanner = new Scanner(System.in);

	public static void printMessage(String msg) {
		System.out.println(msg);
	}
	
	public static String readLine() {
		return scanner.nextLine();
	}

}
