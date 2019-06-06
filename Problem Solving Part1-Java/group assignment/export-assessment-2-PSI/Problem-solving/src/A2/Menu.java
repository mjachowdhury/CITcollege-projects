package A2;

import java.util.Scanner;

public class Menu {

	private static boolean quit = false;
	public static Scanner input = new Scanner(System.in);
	private static Elf[] elves = { new Elf("John"), new Elf("James"), new Elf("Judy"), new Elf("Jacob"), new Elf("Jasmine"), new Elf("JJ"), new Elf("Jonas"), new Elf("Jerry"), new Elf("Jenny"), new Elf("Jessy") };
	
	public static void main(String[] args) {
		while(!quit) {
			showMenu();
			getUserSelection();
		}
	}

	private static void showMenu() {
		System.out.println("Hi Santa!\n"
				+ "This are your options:\n"
				+ "\n"
				+ "1. Search for an Elf\n"
				+ "2. Show outstanding total\n"
				+ "3. Reset all hours\n"
				+ "4. Quit program");
	}
	private static void getUserSelection() {
		int selection = input.nextInt();
		switch(selection) {
			case 1:
				searchElf();
				break;
			case 2:
				listAllElves();
				break;
			case 3:
				quitProgram();
				break;
		}
	}
	
	private static void searchElf() {
		String search;
		System.out.println("Please enter a name:");
		search = input.next();
		
		for(int i = 0; i < elves.length; i++) {
			if(elves[i].getName().equals(search)){
				System.out.println(" Name | Outstanding Salary\n"
						+ elves[i].getName() + " | " + elves[i].totalOutstandingSalary());
			}
		}
		
	}
	
	private static void listAllElves() {
		for(int i = 0; i < elves.length; i++) {
			System.out.println(elves[i].getName() + " - " + elves[i].totalOutstandingSalary());
		}
	}
	
	private static void quitProgram() {
		quit = true;
	}
}
