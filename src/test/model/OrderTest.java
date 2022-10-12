package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    private Order order;

    @BeforeEach
    void runBefore() {
        AppleJuice.resetTotalVolume();
        OrangeJuice.resetTotalVolume();
        GuavaJuice.resetTotalVolume();
        KiwiJuice.resetTotalVolume();
        LycheeJuice.resetTotalVolume();
        order = new Order();
    }

    @Test
    void testConstructor() {
        assertEquals(0, order.getTotalBill());
        assertEquals(0, order.getListOfJuice().size());
    }

    @Test
    void testAddDrinkNormalCase() {
        order.addDrink(new AppleJuice(FruitJuice.DrinkSize.TALL));
        order.addDrink(new OrangeJuice(FruitJuice.DrinkSize.GRANDE));
        assertEquals(2, order.getListOfJuice().size());
    }

    @Test
    void testAddDrinkWhenDrinkIsSoldOut() {
        order.addDrink(new AppleJuice(FruitJuice.DrinkSize.VENTI));
        order.addDrink(new AppleJuice(FruitJuice.DrinkSize.VENTI));
        order.addDrink(new AppleJuice(FruitJuice.DrinkSize.VENTI));
        order.addDrink(new AppleJuice(FruitJuice.DrinkSize.VENTI));
        order.addDrink(new AppleJuice(FruitJuice.DrinkSize.VENTI));
        order.addDrink(new AppleJuice(FruitJuice.DrinkSize.VENTI));
        order.addDrink(new AppleJuice(FruitJuice.DrinkSize.VENTI));
        order.addDrink(new AppleJuice(FruitJuice.DrinkSize.TALL));
        assertEquals(7, order.getListOfJuice().size());
    }

    @Test
    void testSetOfList() {
        List<FruitJuice> juiceList = new ArrayList<>();
        juiceList.add(new AppleJuice(FruitJuice.DrinkSize.VENTI));
        juiceList.add(new LycheeJuice(FruitJuice.DrinkSize.VENTI));
        juiceList.add(new KiwiJuice(FruitJuice.DrinkSize.GRANDE));
        order.setListOfJuice(juiceList);
        assertEquals(juiceList, order.getListOfJuice());
    }

    @Test
    void testSetTotalBill() {
        List<FruitJuice> juiceList = new ArrayList<>();
        juiceList.add(new AppleJuice(FruitJuice.DrinkSize.VENTI));
        juiceList.add(new LycheeJuice(FruitJuice.DrinkSize.VENTI));
        juiceList.add(new KiwiJuice(FruitJuice.DrinkSize.GRANDE));
        order.setListOfJuice(juiceList);
        order.setTotalBill(order.getListOfJuice());
        double expectedBill = juiceList.get(0).getPrice() + juiceList.get(1).getPrice() + juiceList.get(2).getPrice();
        assertEquals(expectedBill, order.getTotalBill());
    }
}
