package ui;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;

//Simulate a juice shop
public class JuiceShop {
    private static final String JSON_STORE = "./data/order.json";
    private Order order;
    private FruitJuice juice;
    private int numDrinks;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    /*
     * EFFECTS: numDrinks is set to 0, and order is set to new Order
     * runs the juice shop application
     */
    public JuiceShop() {
        numDrinks = 0;
        order = new Order("");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user inputs
     */
    public void runApp() {
        System.out.println("\nPlease enter your name: ");
        order.setCustomerName(new Scanner(System.in).nextLine());
        while (true) {
            System.out.println("\nHello, please select from the following options:");
            System.out.println("\to -> Order a drink");
            System.out.println("\tl -> Load existing order from file");
            System.out.println("\ts -> Save order to file");
            System.out.println("\tq -> Print bill and quit");
            String cmd = new Scanner(System.in).next().trim().toLowerCase();
            if (cmd.equals("o")) {
                initFruitType();
                initDrinkSize();
            } else if (cmd.equals("l")) {
                loadOrder();
            } else if (cmd.equals("s")) {
                saveOrder();
            } else if (cmd.equals("q")) {
                printTotalBill();
                break;
            } else {
                System.out.println("Invalid option...");
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes fruit type and add that juice to the order,
     * but the volume of juice is not set (Drink Size is NUL)
     */
    public void initFruitType() {
        displayFruitMenu();
        Scanner input1 = new Scanner(System.in);
        input1.useDelimiter("\n");
        getFruit(input1.next().toLowerCase());
        order.getListOfJuice().add(juice);
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes drink size
     */
    public void initDrinkSize() {
        displayDrinkSizeMenu();
        Scanner input2 = new Scanner(System.in);
        input2.useDelimiter("\n");
        getSize(input2.next());
    }

    /*
     * REQUIRES: a non-empty string fruit
     * MODIFIES: this
     * EFFECTS: initializes new fruit
     */
    public void getFruit(String fruit) {
        switch (fruit) {
            case "a":
                juice = new AppleJuice();
                break;
            case "o":
                juice = new OrangeJuice();
                break;
            case "g":
                juice = new GuavaJuice();
                break;
            case "l":
                juice = new LycheeJuice();
                break;
            case "k":
                juice = new KiwiJuice();
                break;
            default:
                System.out.println("Selection not valid. Please enter again.");
                initFruitType();
        }
    }

    /*
     * REQUIRES: a non-empty string size
     * MODIFIES: this
     * EFFECTS: set size for juice
     */
    public void getSize(String size) {
        switch (size) {
            case "1": checkJuiceEnoughForTall();
                      break;
            case "2": checkJuiceEnoughForGrande();
                      break;
            case "3": checkJuiceEnoughForVenti();
                      break;
            default:
                System.out.println("Selection not valid. Please enter again.");
                initDrinkSize();
        }
    }

    // EFFECTS: displays menu of fruit options to user
    public void displayFruitMenu() {
        System.out.println("\nSelect Fruit Type from:");
        System.out.println("\ta -> apple");
        System.out.println("\to -> orange");
        System.out.println("\tg -> guava");
        System.out.println("\tl -> lychee");
        System.out.println("\tk -> kiwi");
        System.out.println("\tYou can enter fruit choice by typing the letter: ");
    }

    // EFFECTS: displays menu of drink size options to user
    public void displayDrinkSizeMenu() {
        System.out.println("\nSelect Drink Size from:");
        System.out.println("\t1 -> tall size. Price: $3.50.");
        System.out.println("\t2 -> grande size. Price: $4.25");
        System.out.println("\t3 -> venti size. Price: $5.75");
        System.out.println("\tYou can enter drink size by typing the number: ");
    }

    // EFFECTS: prints the total bill
    public void printTotalBill() {
        numDrinks = order.getListOfJuice().size();
        System.out.println("Your total drinks are: " + numDrinks);
        for (int i = 0; i < numDrinks; i++) {
            System.out.println("Drink " + (i + 1) + ": Fruit: " + order.getListOfJuice().get(i).getType()
                    + " - Size: " + order.getListOfJuice().get(i).getSize());
        }
        order.setTotalBill(order.getListOfJuice());
        System.out.println("Total: $" + order.getTotalBill());
    }

    /* MODIFIES: this
     * EFFECTS: if juice is not out of order AND juice.remainingVolume() >= FruitJuice.TALL_VOLUME
     * size of juice is set to TALL, price is set to TALL_PRICE and volume is set to TALL_VOLUME
     * otherwise, remove the juice and ask for customer's opinion (want to change to another fruit?)
     */
    public void checkJuiceEnoughForTall() {
        if (!juice.validateDrink(FruitJuice.DrinkSize.TALL)) {
            removeJuice();
            System.out.println(juice.getType() + " is out of order.");
            System.out.println("Do you want to choose another juice?(y/n) ");
            Scanner input = new Scanner(System.in);
            String change = input.nextLine();
            if (change.toLowerCase().equals("y")) {
                initFruitType();
            }
        }
    }

    /* MODIFIES: this
     * EFFECTS: if juice is not out of order AND juice.remainingVolume() >= FruitJuice.GRANDE_VOLUME
     * size of juice is set to GRANDE, price is set to GRANDE_PRICE and volume is set to GRANDE_VOLUME
     * otherwise, ask for customer's opinion (continue or not)
     * if the customer does not want to order anymore, then remove the juice out of the order
     */
    public void checkJuiceEnoughForGrande() {
        if (!juice.validateDrink(FruitJuice.DrinkSize.GRANDE)) {
            System.out.println("This size is out of order. ");
            System.out.println("Do you want to choose TALL size? ");
            System.out.println("y -> Continue");
            System.out.println("n -> Remove juice");
            Scanner input = new Scanner(System.in);
            String change = input.nextLine();
            if (change.toLowerCase().equals("y")) {
                checkJuiceEnoughForTall();
            }
            removeJuice();
        }
    }

    /* MODIFIES: this
     * EFFECTS: if juice is not out of order AND juice.remainingVolume() >= FruitJuice.VENTI_VOLUME
     * size of juice is set to VENTI, price is set to VENTI_PRICE and volume is set to VENTI_VOLUME
     * otherwise, ask for customer's opinion (continue or not)
     * if the customer does not want to order anymore, then remove the juice out of the order
     */
    public void checkJuiceEnoughForVenti() {
        if (!juice.validateDrink(FruitJuice.DrinkSize.VENTI)) {
            System.out.println("This size is out of order. ");
            System.out.println("Do you want to choose GRANDE size? ");
            System.out.println("y -> Continue");
            System.out.println("n -> Remove juice");
            Scanner input = new Scanner(System.in);
            String change = input.nextLine();
            if (change.toLowerCase().equals("y")) {
                checkJuiceEnoughForGrande();
            }
            removeJuice();
        }
    }

    /* MODIFIES: this
     * EFFECTS: removes the juice out of the order
     */
    public void removeJuice() {
        order.getListOfJuice().remove(juice);
    }

    // EFFECTS: saves the order to file
    private void saveOrder() {
        try {
            jsonWriter.open();
            jsonWriter.write(order);
            jsonWriter.close();
            System.out.println("Saved this order to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads order from file
    private void loadOrder() {
        try {
            order = jsonReader.read();
            System.out.println("Loaded order from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
