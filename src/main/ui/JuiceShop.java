package ui;

import model.*;

import java.util.Scanner;

//Simulate a juice shop
public class JuiceShop {
    private Order order;
    private FruitJuice juice;
    private int numDinks;

    /*
     * EFFECTS: numDrinks is set to 0, and order is set to new Order
     * runs the juice shop application
     */
    public JuiceShop() {
        numDinks = 0;
        order = new Order();
        runApp();
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user inputs
     */
    public void runApp() {
        System.out.println("Hello, how many drinks would you like to order? ");
        Scanner scanner1 = new Scanner(System.in);
        int num = scanner1.nextInt();
        numDinks += num;
        for (int i = 0; i < num; i++) {
            initFruitType();
            initDrinkSize();
        }
        System.out.println("Do you want to order more drinks? (y/n)");
        Scanner scanner2 = new Scanner(System.in);
        String keepGoing = scanner2.nextLine();
        if (keepGoing.toLowerCase().equals("y")) {
            runApp();
        }
        printTotalBill();
        System.out.println("\nThank you. Goodbye!");
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
        numDinks = order.getListOfJuice().size();
        System.out.println("Your total drinks are: " + numDinks);
        for (int i = 0; i < numDinks; i++) {
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
        if (!juice.isOutOfOrder() && juice.remainingVolume() >= FruitJuice.TALL_VOLUME) {
            juice.setSize(FruitJuice.DrinkSize.TALL);
            juice.setPrice(FruitJuice.DrinkSize.TALL);
            juice.setTotalVolume(FruitJuice.DrinkSize.TALL);
        } else {
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
        if (!juice.isOutOfOrder() && juice.remainingVolume() >= FruitJuice.GRANDE_VOLUME) {
            juice.setSize(FruitJuice.DrinkSize.GRANDE);
            juice.setPrice(FruitJuice.DrinkSize.GRANDE);
            juice.setTotalVolume(FruitJuice.DrinkSize.GRANDE);
        } else {
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
        if (!juice.isOutOfOrder() && juice.remainingVolume() >= FruitJuice.VENTI_VOLUME) {
            juice.setSize(FruitJuice.DrinkSize.VENTI);
            juice.setPrice(FruitJuice.DrinkSize.VENTI);
            juice.setTotalVolume(FruitJuice.DrinkSize.VENTI);
        } else {
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
}
