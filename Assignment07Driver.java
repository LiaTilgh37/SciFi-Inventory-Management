

import java.util.ArrayList;
import java.util.Scanner;

public class Assignment07Driver {
	Assignment07Methods studentMethods = new Assignment07Methods();
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		new Assignment07Driver();
	}

	public Assignment07Driver() {
		ArrayList<Item> cargohold = new ArrayList<Item>();
		System.out.println("Welcome to the Discipulus Cargo Hold interface.");
		System.out.println("Please select a number from the options below");
		System.out.println("");
		while (true) {
			// Give the user a list of their options
			System.out.println("1: Add an item to the cargo hold.");
			System.out.println("2: Remove an item from the cargo hold.");
			System.out.println("3: Sort the contents of the cargo hold.");
			System.out.println("4: Sort the contents of the cargo hold (Using Java Sort).");
			System.out.println("5: Search for an item by name.");
			System.out.println("6: Search for an item by ID.");
			System.out.println("7: Load from file");
			System.out.println("8: Save to a file.");
			System.out.println("9: Display the items in the cargo hold.");
			System.out.println("0: Exit the Discipulus Cargo Hold interface.");
			// Get the user input
			int userChoice = input.nextInt();
			input.nextLine();
			switch (userChoice) {
				case 1:
					studentMethods.addItem(cargohold);
					break;
				case 2:
					studentMethods.removeItem(cargohold);
					break;
				case 3:
					studentMethods.sortItems(cargohold);
					break;
				case 4:
					studentMethods.sortItemsJava(cargohold);
					break;
				case 5:
					studentMethods.searchItemsByName(cargohold);
					break;
				case 6:
					studentMethods.searchItemsByID(cargohold);
					break;
				case 7:
					studentMethods.loadItems(cargohold);
					break;
				case 8:
					studentMethods.saveItems(cargohold);
					break;
				case 9:
					studentMethods.displayItems(cargohold);
					break;
				case 0:
					System.out.println("Thank you for using the Discipulus Cargo Hold interface. See you again soon!");
					System.exit(0);
			}
		}
	}
}
