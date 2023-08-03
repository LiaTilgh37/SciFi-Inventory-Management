import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Assignment07Methods {
	int numItems = 0;
	double totalWeight;
	private boolean centerFound = false;

	Scanner input = new Scanner(System.in);
	Driver.Field[][] field = new Driver.Field[7][7];

	public void findCenter(int row, int column) {
		boolean[][] visited = new boolean[field.length][field.length];

		boolean[][] entryPoint = new boolean[field.length][field.length];

		if (hasPathToCenter(field, visited, entryPoint, row, column)) {
			centerFound = true;
		}

		for (int r = 0; r < field.length; r++) {
			for (int c = 0; c < field.length; c++) {
				if (isEntryPoint(field, r, c) && !entryPoint[r][c]) {
					if (hasPathToCenter(field, visited, entryPoint, r, c)) {
						centerFound = true;
					}
				}
			}
		}

		centerFound = false;
	}

	private boolean isEntryPoint(Driver.Field[][] debrisField, int row, int column) {
		if (row == 0) {
			return true;
		}

		if (row == debrisField.length - 1) {
			return true;
		}

		if (column == 0) {
			return true;
		}

		if (column == debrisField.length - 1) {
			return true;
		}

		return false;
	}

	private boolean hasPathToCenter(Driver.Field[][] debrisField, boolean[][] visited, boolean[][] entryPoint, int row, int column) {
		if (row < 0 || column < 0 || row >= debrisField.length || column >= debrisField.length) {
			return false;
		}

		if (row == (debrisField.length-1)/2 && column == (debrisField.length-1)/2) {
			return true;
		}

		if (debrisField[row][column] == Driver.Field.DEBRIS || visited[row][column]) {
			return false;
		}

		if (isEntryPoint(debrisField, row, column) && entryPoint[row][column]) {
			return false;
		}

		visited[row][column] = true;

		if (isEntryPoint(debrisField, row, column)) {
			entryPoint[row][column] = true;
		}

		if (hasPathToCenter(debrisField, visited, entryPoint, row-1, column)
				|| hasPathToCenter(debrisField, visited, entryPoint, row+1, column)
				|| hasPathToCenter(debrisField, visited, entryPoint, row, column-1)
				|| hasPathToCenter(debrisField, visited, entryPoint, row, column+1)) {
			return true;
		}

		return false;
	}

	public void addItem(ArrayList<Item> cargohold) {
		// TODO: Add an item that is specified by the user

		try {
			boolean isAdded = false;

			String strItemName = "";
			int intItemWeight = 0;
			int intItemValue = 0;
			int intItemDurability = 0;
			int intItemId = 0;

//			if (cargohold.get(numItems) == null) {
				boolean loopAgain = false;
				String temp = "";

				System.out.println("What kind of item would you like to add?");
				System.out.println("1: Equipment");
				System.out.println("2: Consumable");
				System.out.println("3: Material");
				System.out.println("0: Other - Generic Item");
				// Get the user input
				int intCategoryChoice = input.nextInt();

				switch (intCategoryChoice) {
					case 1:
						boolean boolIsGun = false;
						boolean boolIsClothing = false;
						boolean boolIsTool = false;

						System.out.println("Enter item name:");
						strItemName = input.next();

						System.out.println("Enter item weight:");
						intItemWeight = input.nextInt();

						if (totalWeight + intItemWeight > 25) {
							System.out.println("Item will not fit.");
							return;
						}

						System.out.println("Enter item value:");
						intItemValue = input.nextInt();

						System.out.println("Enter item durability:");
						intItemDurability = input.nextInt();

						System.out.println("Enter item ID:");
						intItemId = input.nextInt();

						System.out.println("Is this item a gun? (true/false):");
						temp = input.next();
						while (!temp.toLowerCase().matches("true|false")) {
							System.err.println("Please only enter true or false");
							System.out.println("Is this item a gun? (true/false):");
							temp = input.next();
						}
						boolIsGun = Boolean.parseBoolean(temp.toLowerCase());

						System.out.println("Is this item clothing? (true/false):");
						temp = input.next();
						while (!temp.toLowerCase().matches("true|false")) {
							System.err.println("Please only enter true or false");
							System.out.println("Is this item clothing? (true/false):");
							temp = input.next();
						}
						boolIsClothing = Boolean.parseBoolean(temp.toLowerCase());

						System.out.println("Is this item a tool? (true/false):");
						temp = input.next();
						while (!temp.toLowerCase().matches("true|false")) {
							System.err.println("Please only enter true or false");
							System.out.println("Is this item a tool? (true/false):");
							temp = input.next();
						}
						boolIsTool = Boolean.parseBoolean(temp.toLowerCase());

//						cargohold[numItems] = new Equipment(strItemName, intItemWeight, intItemValue, intItemDurability, intItemId, boolIsGun, boolIsClothing, boolIsTool);
						cargohold.add(new Equipment(strItemName, intItemWeight, intItemValue, intItemDurability, intItemId, boolIsGun, boolIsClothing, boolIsTool));

						System.out.println("Item was successfully added!");

						totalWeight += intItemWeight;

						this.numItems++;

						break;
					case 2:
						boolean isVegetarian = false;

						System.out.println("Enter item name:");
						strItemName = input.next();

						System.out.println("Enter item weight:");
						intItemWeight = input.nextInt();

						if (totalWeight + intItemWeight > 25) {
							System.out.println("Item will not fit.");
							return;
						}

						System.out.println("Enter item value:");
						intItemValue = input.nextInt();

						System.out.println("Enter item durability:");
						intItemDurability = input.nextInt();

						System.out.println("Enter item ID:");
						intItemId = input.nextInt();

						System.out.println("Enter expiration date (dd-mm-yyyy):");
						String strExpDate = input.next();
						SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
						Date expDate = new Date();

						try {
							expDate = format.parse(strExpDate);
						} catch (ParseException e) {
							System.out.println(e);
						}

						System.out.println("How many calories?:");
						int calorieNum = input.nextInt();

						System.out.println("Is this item vegetarian? (true/false):");
						temp = input.next();
						while (!temp.toLowerCase().matches("true|false")) {
							System.err.println("Please only enter true or false");
							System.out.println("Is this item vegetarian? (true/false):");
							temp = input.next();
						}
						isVegetarian = Boolean.parseBoolean(temp.toLowerCase());

//						cargohold[numItems] = new Consumable(strItemName, intItemWeight, intItemValue, intItemDurability, intItemId, expDate, calorieNum, isVegetarian);
						cargohold.add(new Consumable(strItemName, intItemWeight, intItemValue, intItemDurability, intItemId, expDate, calorieNum, isVegetarian));

						System.out.println("Item was successfully added!");

						totalWeight += intItemWeight;

						this.numItems++;

						break;
					case 3:
						System.out.println("Enter item name:");
						strItemName = input.next();

						System.out.println("Enter item weight:");
						intItemWeight = input.nextInt();

						if (totalWeight + intItemWeight > 25) {
							System.out.println("Item will not fit.");
							return;
						}

						System.out.println("Enter item value:");
						intItemValue = input.nextInt();

						System.out.println("Enter item durability:");
						intItemDurability = input.nextInt();

						System.out.println("Enter item ID:");
						intItemId = input.nextInt();

						System.out.println("Enter purchase date (dd-mm-yyyy):");
						String strPurchaseDate = input.next();
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Date purchaseDate = new Date();

						try {
							purchaseDate = dateFormat.parse(strPurchaseDate);
						} catch (ParseException e) {
							System.out.println(e);
						}

						System.out.println("Enter item color:");
						String color = input.next();

						System.out.println("Enter item texture:");
						String texture = input.next();

//						cargohold[numItems] = new Material(strItemName, intItemWeight, intItemValue, intItemDurability, intItemId, purchaseDate, color, texture);
						cargohold.add(new Material(strItemName, intItemWeight, intItemValue, intItemDurability, intItemId, purchaseDate, color, texture));

						System.out.println("Item was successfully added!");

						totalWeight += intItemWeight;

						this.numItems++;

						break;
					case 0:
						System.out.println("Enter item name:");
						strItemName = input.next();

						System.out.println("Enter item weight:");
						intItemWeight = input.nextInt();

						if (totalWeight + intItemWeight > 25) {
							System.out.println("Item will not fit.");
							return;
						}

						System.out.println("Enter item value:");
						intItemValue = input.nextInt();

						System.out.println("Enter item durability:");
						intItemDurability = input.nextInt();

						System.out.println("Enter item ID:");
						intItemId = input.nextInt();

						Item item = new Item(strItemName, intItemWeight, intItemValue, intItemDurability, intItemId);

//						cargohold[numItems] = item;
						cargohold.add(item);

						System.out.println("Item was successfully added!");

						totalWeight += intItemWeight;

						this.numItems++;

						break;
				}
//			}
		} catch (Exception e) {
			System.out.println("Sorry, there was an error. Please try again.");
		}
	}

	public void removeItem(ArrayList<Item> cargohold) {
		// TODO: Remove an item that is specified by the user

		boolean found = false;

		if (this.numItems == 0) {
			System.out.println("The cargohold is empty.");

			return;
		}

		System.out.println("Enter the name of the item to be removed:");
		String name = input.next();

		ArrayList<Item> temp = new ArrayList<Item>();

		for (int i = 0, j = 0; i < this.numItems; i++) {
			if (name.equals(cargohold.get(i).name)) {
				cargohold.remove(i);
				found = true;
			}
		}
//		for (int k = 0; k < cargohold.size(); k++) {
//			cargohold.add(temp.get(k));
//		}

		numItems = 0;
		totalWeight = 0;

		for (int i = 0; i < cargohold.size(); i++) {
			if (cargohold.get(i) != null) {
				numItems++;
				totalWeight += cargohold.get(i).weight;
			}
		}
		if (found == false) {
			System.out.println("Item does not exist in the cargohold!");
		}
	}

	public void sortItems(ArrayList<Item> cargohold) {
		// TODO: Sort the items in the cargo hold (No need to display them here) - Use
		// Selection or Insertion sorts
		// NOTE: Special care is needed when dealing with strings! research the
		// compareTo() method with strings

		if (cargohold.size() == 0) {
			System.out.println("The cargohold is empty.");

			return;
		}

		int i;
		int j;
		int minimumIndex;

		for (i = 0; i < cargohold.size(); i++) {
			minimumIndex = i;
			for (j = i + 1; j < cargohold.size(); j++) {
				if (cargohold.get(j) != null) {
//					if (cargohold.get(j).name.compareTo(cargohold.get(i).name) < 0) {
					if (cargohold.get(i).name.equals(cargohold.get(j).name)) {
						minimumIndex = j;
					}
				}
			}

			if (minimumIndex != i) {
				Item temp = cargohold.get(minimumIndex);
				cargohold.set(minimumIndex, cargohold.get(i));
				cargohold.set(i, temp);
			}
		}
	}
	
	public void sortItemsJava(ArrayList<Item> cargohold) {
		// Sort your cargohold by using the Arrays or Collections classes.

		Collections.sort(cargohold);
		System.out.println("Items have been sorted successfully!");
	}

	public void searchItemsByName(ArrayList<Item> cargohold) {
		// TODO: Search for a user specified item

		if (this.numItems == 0) {
			System.out.println("The cargohold is empty.");

			return;
		}

		System.out.println("Enter the name of the item:");
		String itemName = input.next();

		System.out.println("Enter the attribute of the item:");
		String itemAttributeToFind = input.next();

		boolean weight = false;
		boolean value = false;
		boolean durability = false;
		boolean id = false;

		switch (itemAttributeToFind) {
			case "":
				break;
			case "weight":
				weight = true;
				break;
			case "value":
				value = true;
				break;
			case "durability":
				durability = true;
				break;
			case "id":
				id = true;
				break;
			default:
				System.out.println("That's not a valid attribute!");
				return;
		}

		int position = -1;

		System.out.println("Enter the attribute's field: ");
		int num = input.nextInt();

		for (int i = 0; i < cargohold.size(); i++) {
			if (cargohold.get(i) != null) {
				if (cargohold.get(i).name.equals(itemName)) {
					if (weight) {
						if (cargohold.get(i).weight != num) {
							continue;
						}
					}
					if (value) {
						if (cargohold.get(i).value != num) {
							continue;
						}
					}
					if (durability) {
						if (cargohold.get(i).durability != num) {
							continue;
						}
					}
					if (id) {
						if (cargohold.get(i).id != num) {
							continue;
						}
					}
					position = i;
					break;
				}
			}
		}

		if (position != -1) {
			System.out.println("That item (Name: " + cargohold.get(position).name + ") is in position " + position);
		} else {
			System.out.println("That item (Name: " + itemName + ") is not in the cargo hold.");
		}
	}

	public void searchItemsByID(ArrayList<Item> cargohold) {
		// TODO: Search for a user specified item

		if (this.numItems == 0) {
			System.out.println("The cargohold is empty.");

			return;
		}

		System.out.println("Enter the ID of the item:");
		int itemID = input.nextInt();

		System.out.println("Enter the attribute of the item:");
		String itemAttributeToFind = input.next();

		boolean weight = false;
		boolean value = false;
		boolean durability = false;
		boolean id = false;

		switch (itemAttributeToFind) {
			case "":
				break;
			case "weight":
				weight = true;
				break;
			case "value":
				value = true;
				break;
			case "durability":
				durability = true;
				break;
			case "id":
				id = true;
				break;
			default:
				System.out.println("That's not a valid attribute!");
				return;
		}

		int position = -1;

		System.out.println("Enter the attribute's field: ");
		int num = input.nextInt();

		for (int i = 0; i < cargohold.size(); i++) {
			if (cargohold.get(i) != null) {
				if (cargohold.get(i).id == itemID) {
					if (weight) {
						if (cargohold.get(i).weight != num) {
							continue;
						}
					}
					if (value) {
						if (cargohold.get(i).value != num) {
							continue;
						}
					}
					if (durability) {
						if (cargohold.get(i).durability != num) {
							continue;
						}
					}
					if (id) {
						if (cargohold.get(i).id != num) {
							continue;
						}
					}
					position = i;
					break;
				}
			}
		}

		if (position != -1) {
			System.out.println("That item (ID: " + cargohold.get(position).id + ") is in position " + position);
		} else {
			System.out.println("That item (ID: " + itemID + ") is not in the cargo hold.");
		}
	}
	
	public void loadItems(ArrayList<Item> cargohold){
		int loadedItemCounter = 0;
		Object result = null;

		try (FileInputStream fis = new FileInputStream("MidtermMethods.txt"); ObjectInputStream ois = new ObjectInputStream(fis)) {
			result = ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Sorry, there was an error. The data in the saved file may be corrupted. Please try again.");
			return;
		}

		for (int i = 0; i < ((ArrayList<Item>) result).size(); i++) {
			if (((ArrayList<Item>) result).get(i) != null) {
				loadedItemCounter++;
			}
		}

		numItems = loadedItemCounter;

		for (int i = 0; i < loadedItemCounter; i++) {
			totalWeight += ((ArrayList<Item>) result).get(i).weight;
			cargohold.add(((ArrayList<Item>) result).get(i));
		}

		System.out.println("Added " + loadedItemCounter + " items!");
	}
	
	public void saveItems(ArrayList<Item> cargohold){
		try (FileOutputStream fos = new FileOutputStream("MidtermMethods.txt");
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(cargohold);
			oos.flush();
		} catch (Exception e) {
			System.out.println("Sorry, there was an error. Please try again.");
		}
	}
	
	public void displayItems(ArrayList<Item> cargohold) {
		// TODO: Display only the unique items along with a count of any duplicates
		// For example it should say
		// Food - 2
		// Water - 3
		// Ammunition - 5

		if (this.numItems == 0) {
			System.out.println("The cargohold is empty.");

			return;
		}

		ArrayList<Item> cargoholdDisplay = cargohold;

		if (cargohold.size() == 0) {
			System.out.println("The cargohold is empty.");

			return;
		}

		int count;

		for (int i = 0; i < cargohold.size(); i++) {
			Item item;

			if (cargoholdDisplay.get(i) != null) {
				item = cargoholdDisplay.get(i);
			} else {
				continue;
			}

			count = 1;

			for (int n = i + 1; n < cargohold.size(); n++) {
				if (cargohold.get(n) != null && cargoholdDisplay.get(n).equals(item)) {
					count++;

					cargoholdDisplay.set(n, null);
				}
			}

			System.out.println(item.display() + " - " + count);
		}
	}
}


class Item implements Serializable, Comparable<Item> {
	String name;
	String type;
	double weight;
	double value;
	double durability;
	public int id;

	public Item() {
	}

	public Item(String name, double weight, double value, double durability, int id) {
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.durability = durability;
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public String display() {
		return name + "[weight: " + weight + ", value: " + value + ", durability: " + durability + ", id: " + id + "]";
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", weight=" + weight + ", value=" + value + ", durability=" + durability + ", id=" + id + "]";
	}

	@Override
	public int compareTo(Item item) {
		System.out.println(Integer.compare(getId(), item.getId()));
		return Integer.compare(getId(), item.getId());
	}
}

class ItemIDComparator implements Comparator<Item> {
	public int compare(Item item1, Item item2) {
		if (item1 == null || item2 == null) {
			return 0;
		}
		return item1.getId() - item2.getId();
	}
}


class Equipment extends Item {
	boolean isGun;
	boolean isClothing;
	boolean isTool;

	public Equipment() {
	}

	public Equipment(String name, double weight, double value, double durability, int id, boolean isGun, boolean isClothing, boolean isTool) {
		super(name, weight, value, durability, id);
		this.isGun = isGun;
		this.isClothing = isClothing;
		this.isTool = isTool;
	}

	public boolean isGun() {
		return isGun;
	}

	public void setGun(boolean gun) {
		isGun = gun;
	}

	public boolean isClothing() {
		return isClothing;
	}

	public void setClothing(boolean clothing) {
		isClothing = clothing;
	}

	public boolean isTool() {
		return isTool;
	}

	public void setTool(boolean tool) {
		isTool = tool;
	}

	public String display() {
		return name + "[weight: " + weight + ", value: " + value + ", durability: " + durability + ", id: " + id + ", isGun: " + isGun + ", isClothing: " + isClothing + ", isTool: " + isTool + "]";
	}

	@Override
	public String toString() {
		return "Equipment [name=" + name + ", weight=" + weight + ", value=" + value + ", durability=" + durability + ", id=" + id + ", isGun=" + isGun + ", isClothing=" + isClothing + ", isTool=" + isTool + " ]";
	}
}


class Consumable extends Item {
	Date expDate = new Date();
	int calorieNum;
	boolean isVegetarian;

	public Consumable() {
	}

	public Consumable(String name, double weight, double value, double durability, int id, Date expDate, int calorieNum, boolean isVegetarian) {
		super(name, weight, value, durability, id);
		this.expDate = expDate;
		this.calorieNum = calorieNum;
		this.isVegetarian = isVegetarian;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public int getCalorieNum() {
		return calorieNum;
	}

	public void setCalorieNum(int calorieNum) {
		this.calorieNum = calorieNum;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		isVegetarian = vegetarian;
	}

	public String display() {
		return name + "[weight: " + weight + ", value: " + value + ", durability: " + durability + ", id: " + id + ", expDate: " + expDate + ", calorieNum: " + calorieNum + ", isVegetarian: " + isVegetarian + "]";
	}

	@Override
	public String toString() {
		return "Consumable [name=" + name + ", weight=" + weight + ", value=" + value + ", durability=" + durability + ", id=" + id + ", expDate=" + expDate + ", calorieNum=" + calorieNum + ", isVegetarian=" + isVegetarian + " ]";
	}
}


class Material extends Item {
	Date purchaseDate = new Date();
	String color;
	String texture;

	public Material() {
	}

	public Material(String name, double weight, double value, double durability, int id, Date purchaseDate, String color, String texture) {
		super(name, weight, value, durability, id);
		this.purchaseDate = purchaseDate;
		this.color = color;
		this.texture = texture;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public String display() {
		return name + "[weight: " + weight + ", value: " + value + ", durability: " + durability + ", id: " + id + ", purchaseDate: " + purchaseDate + ", color: " + color + ", texture: " + texture + "]";
	}

	@Override
	public String toString() {
		return "Material [name=" + name + ", weight=" + weight + ", value=" + value + ", durability=" + durability + ", id=" + id + ", purchaseDate=" + purchaseDate + ", color=" + color + ", texture=" + texture + " ]";
	}
}