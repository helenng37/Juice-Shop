package ui;

import model.FruitJuice;
import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class JuiceShop {
    private Order order;
    private List<FruitJuice.FruitType> fruitTypeList;
    private List<FruitJuice.DrinkSize> drinkSizeList;
    private int numDinks;

    public JuiceShop() {
        numDinks = 0;
        order = new Order();
        fruitTypeList = new ArrayList<>();
        drinkSizeList = new ArrayList<>();
        runApp();
    }

    public void runApp() {
        System.out.println("Hello, how many drinks would you like to order? ");
        Scanner scanner1 = new Scanner(System.in);
        int num = scanner1.nextInt();
        numDinks += num;
        for (int i = 0; i < num; i++) {
            initFruitList();
            initSizeList();
        }
        System.out.println("Do you want to order more drinks? (y/n)");
        Scanner scanner2 = new Scanner(System.in);
        String keepGoing = scanner2.nextLine();
        if (keepGoing.toLowerCase().equals("y")) {
            runApp();
        }
        addDrink(fruitTypeList, drinkSizeList);
        printTotalBill();
        System.out.println("\nThank you. Goodbye!");
    }

    public void initFruitList() {
        displayFruitMenu();
        Scanner input1 = new Scanner(System.in);
        input1.useDelimiter("\n");
        getFruit(input1.next().toLowerCase());
    }

    public void initSizeList() {
        displayDrinkSizeMenu();
        Scanner input2 = new Scanner(System.in);
        input2.useDelimiter("\n");
        getSize(input2.next());
    }

    @SuppressWarnings("methodlength")
    public void getFruit(String fruit) {
        switch (fruit) {
            case "a": fruitTypeList.add(FruitJuice.FruitType.APPLE);
                     break;
            case "o": fruitTypeList.add(FruitJuice.FruitType.ORANGE);
                     break;
            case "g": fruitTypeList.add(FruitJuice.FruitType.GUAVA);
                     break;
            case "j": fruitTypeList.add(FruitJuice.FruitType.JACKFRUIT);
                     break;
            case "p": fruitTypeList.add(FruitJuice.FruitType.PAPAYA);
                     break;
            case "r": fruitTypeList.add(FruitJuice.FruitType.RAMBUTAN);
                     break;
            case "l": fruitTypeList.add(FruitJuice.FruitType.LYCHEE);
                     break;
            case "k": fruitTypeList.add(FruitJuice.FruitType.KIWI);
                     break;
            case "t": fruitTypeList.add(FruitJuice.FruitType.TOMATO);
                     break;
            case "c": fruitTypeList.add(FruitJuice.FruitType.CELERY);
                     break;
            default: System.out.println("Selection not valid. Please enter again.");
                     initFruitList();
        }
    }

    public void getSize(String size) {
        switch (size) {
            case "1": drinkSizeList.add(FruitJuice.DrinkSize.TALL);
                      break;
            case "2": drinkSizeList.add(FruitJuice.DrinkSize.GRANDE);
                      break;
            case "3": drinkSizeList.add(FruitJuice.DrinkSize.VENTI);
                      break;
            default: System.out.println("Selection not valid. Please enter again.");
                     initSizeList();
        }
    }

    public void displayFruitMenu() {
        System.out.println("\nSelect Fruit Type from:");
        System.out.println("\ta -> apple");
        System.out.println("\to -> orange");
        System.out.println("\tg -> guava");
        System.out.println("\tj -> jackfruit");
        System.out.println("\tp -> papaya");
        System.out.println("\tr -> rambutan");
        System.out.println("\tl -> lychee");
        System.out.println("\tk -> kiwi");
        System.out.println("\tt -> tomato");
        System.out.println("\tc -> celery");
        System.out.println("\tYou can enter fruit choice by typing the letter: ");
    }

    public void displayDrinkSizeMenu() {
        System.out.println("\nSelect Drink Size from:");
        System.out.println("\t1 -> tall size. Price: $3.50.");
        System.out.println("\t2 -> grande size. Price: $4.25");
        System.out.println("\t3 -> venti size. Price: $5.75");
        System.out.println("\tYou can enter drink size by typing the number: ");
    }

    public void addDrink(List<FruitJuice.FruitType> fruitList, List<FruitJuice.DrinkSize> sizeList) {
        for (int i = 0; i < numDinks; i++) {
            order.getListOfJuice().add(new FruitJuice(fruitList.get(i), sizeList.get(i)));
        }
    }

    public void printTotalBill() {
        System.out.println("Your total drinks are: " + numDinks);
        for (int i = 0; i < numDinks; i++) {
            System.out.println("Drink " + (i + 1) + ": Fruit: " + fruitTypeList.get(i)
                    + " - Size: " + drinkSizeList.get(i));
        }
        order.setTotalBill(order.getListOfJuice());
        System.out.println("Total: $" + order.getTotalBill());
    }
}
