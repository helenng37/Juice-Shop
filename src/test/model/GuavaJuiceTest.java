package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static model.FruitJuice.*;
import static org.junit.jupiter.api.Assertions.*;

class GuavaJuiceTest {
    private FruitJuice guavaJuice;

    @BeforeEach
    void runBefore() {
        GuavaJuice.resetTotalVolume();
        guavaJuice = new GuavaJuice(FruitJuice.DrinkSize.TALL);
    }

    @Test
    void testConstructorWithoutParameter() {
        guavaJuice = new GuavaJuice();
        assertEquals(FruitType.GUAVA, guavaJuice.getType());
        assertEquals(FruitJuice.DrinkSize.NUL, guavaJuice.getSize());
        assertEquals(0, guavaJuice.getPrice());
    }

    @Test
    void testConstructorWithParameterSize() {
        assertEquals(FruitType.GUAVA, guavaJuice.getType());
        assertEquals(FruitJuice.DrinkSize.TALL, guavaJuice.getSize());
        assertEquals(3.50, guavaJuice.getPrice());
    }

    @Test
    void testGetTotalVolume() {
        FruitJuice guavaJuice1 = new GuavaJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice guavaJuice2 = new GuavaJuice(FruitJuice.DrinkSize.GRANDE);
        int expectedTotalVolume = TALL_VOLUME + GRANDE_VOLUME + GRANDE_VOLUME;
        assertEquals(expectedTotalVolume, guavaJuice2.getTotalVolume());
    }

    @Test
    void testSetTotalVolume() {
        GuavaJuice.resetTotalVolume();
        FruitJuice guavaJuice1 = new GuavaJuice();
        guavaJuice1.setSize(DrinkSize.TALL);
        guavaJuice1.setTotalVolume(DrinkSize.TALL);
        FruitJuice guavaJuice2 = new GuavaJuice();
        guavaJuice2.setSize(DrinkSize.GRANDE);
        guavaJuice2.setTotalVolume(DrinkSize.GRANDE);
        FruitJuice guavaJuice3 = new GuavaJuice();
        guavaJuice3.setSize(DrinkSize.VENTI);
        guavaJuice3.setTotalVolume(DrinkSize.VENTI);
        int expectedTotalVolume = TALL_VOLUME + GRANDE_VOLUME + VENTI_VOLUME;
        assertEquals(expectedTotalVolume, guavaJuice3.getTotalVolume());
    }


    @Test
    void testIsOutOfOrder() {
        FruitJuice guavaJuice1 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice2 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice3 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice4 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice5 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice6 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice7 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        assertTrue(guavaJuice.isOutOfOrder());
    }

    @Test
    void testIsNotOutOfOrder() {
        FruitJuice guavaJuice1 = new GuavaJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice guavaJuice2 = new GuavaJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice guavaJuice3 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        assertFalse(guavaJuice.isOutOfOrder());
    }

    @Test
    void testRemainingVolume() {
        FruitJuice guavaJuice1 = new GuavaJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice guavaJuice2 = new GuavaJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice guavaJuice3 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        int expectedRemainingVolume = MAX_VOLUME - TALL_VOLUME - TALL_VOLUME - GRANDE_VOLUME - VENTI_VOLUME;
        assertEquals(expectedRemainingVolume, guavaJuice.remainingVolume());
    }

    @Test
    void testConvertFromSizeToVolumeTallSize() {
        assertEquals(300, guavaJuice.convertFromSizeToVolume(guavaJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeGrandeSize() {
        guavaJuice = new GuavaJuice(FruitJuice.DrinkSize.GRANDE);
        assertEquals(500, guavaJuice.convertFromSizeToVolume(guavaJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeVentiSize() {
        guavaJuice = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        assertEquals(700, guavaJuice.convertFromSizeToVolume(guavaJuice.getSize()));
    }

    @Test
    void testSubtractTotalVolume() {
        FruitJuice guavaJuice1 = new GuavaJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice guavaJuice2 = new GuavaJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice guavaJuice3 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        guavaJuice.subtractTotalVolume(guavaJuice.convertFromSizeToVolume(DrinkSize.VENTI));
        int expectedTotalVolume = TALL_VOLUME + TALL_VOLUME + GRANDE_VOLUME;
        assertEquals(expectedTotalVolume, guavaJuice.getTotalVolume());
    }

    @Test
    void testSetPrice() {
        GuavaJuice.resetTotalVolume();
        FruitJuice guavaJuice1 = new GuavaJuice();
        guavaJuice1.setPrice(DrinkSize.TALL);
        FruitJuice guavaJuice2 = new GuavaJuice();
        guavaJuice2.setPrice(DrinkSize.GRANDE);
        FruitJuice guavaJuice3 = new GuavaJuice();
        guavaJuice3.setPrice(DrinkSize.VENTI);
        double expectedTotalPrice = TALL_PRICE + GRANDE_PRICE + VENTI_PRICE;
        double actualTotalPrice = guavaJuice1.getPrice() + guavaJuice2.getPrice() + guavaJuice3.getPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test
    void testValidateDrinkEnoughForTall() {
        FruitJuice guavaJuice1 = new GuavaJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice guavaJuice2 = new GuavaJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice guavaJuice3 = new GuavaJuice();
        assertTrue(guavaJuice3.validateDrink(DrinkSize.TALL));
    }

    @Test
    void testValidateDrinkNotEnoughForTall() {
        FruitJuice guavaJuice1 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice2 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice3 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice4 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice5 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice6 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice7 = new GuavaJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice guavaJuice8 = new GuavaJuice();
        assertFalse(guavaJuice8.validateDrink(DrinkSize.TALL));
    }
}

