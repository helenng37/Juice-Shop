package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static model.FruitJuice.*;
import static org.junit.jupiter.api.Assertions.*;

class OrangeJuiceTest {
    private FruitJuice orangeJuice;

    @BeforeEach
    void runBefore() {
        OrangeJuice.resetTotalVolume();
        orangeJuice = new OrangeJuice(FruitJuice.DrinkSize.TALL);
    }

    @Test
    void testConstructorWithoutParameter() {
        orangeJuice = new OrangeJuice();
        assertEquals(FruitType.ORANGE, orangeJuice.getType());
        assertEquals(FruitJuice.DrinkSize.NUL, orangeJuice.getSize());
        assertEquals(0, orangeJuice.getPrice());
    }

    @Test
    void testConstructorWithParameterSize() {
        assertEquals(FruitType.ORANGE, orangeJuice.getType());
        assertEquals(FruitJuice.DrinkSize.TALL, orangeJuice.getSize());
        assertEquals(3.50, orangeJuice.getPrice());
    }
    @Test
    void testGetTotalVolume() {
        FruitJuice orangeJuice1 = new OrangeJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice orangeJuice2 = new OrangeJuice(FruitJuice.DrinkSize.GRANDE);
        int expectedTotalVolume = TALL_VOLUME + GRANDE_VOLUME + GRANDE_VOLUME;
        assertEquals(expectedTotalVolume, orangeJuice2.getTotalVolume());
    }

    @Test
    void testIsOutOfOrder() {
        FruitJuice orangeJuice1 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice2 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice3 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice4 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice5 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice6 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice7 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        assertTrue(orangeJuice.isOutOfOrder());
    }

    @Test
    void testIsNotOutOfOrder() {
        FruitJuice orangeJuice1 = new OrangeJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice orangeJuice2 = new OrangeJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice orangeJuice3 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        assertFalse(orangeJuice.isOutOfOrder());
    }

    @Test
    void testRemainingVolume() {
        FruitJuice orangeJuice1 = new OrangeJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice orangeJuice2 = new OrangeJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice orangeJuice3 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        int expectedRemainingVolume = MAX_VOLUME - TALL_VOLUME - TALL_VOLUME -GRANDE_VOLUME - VENTI_VOLUME;
        assertEquals(expectedRemainingVolume, orangeJuice.remainingVolume());
    }

    @Test
    void testConvertFromSizeToVolumeTallSize() {
        assertEquals(300, orangeJuice.convertFromSizeToVolume(orangeJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeGrandeSize() {
        orangeJuice = new OrangeJuice(FruitJuice.DrinkSize.GRANDE);
        assertEquals(500, orangeJuice.convertFromSizeToVolume(orangeJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeVentiSize() {
        orangeJuice = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        assertEquals(700, orangeJuice.convertFromSizeToVolume(orangeJuice.getSize()));
    }

    @Test
    void testSubtractTotalVolume() {
        FruitJuice orangeJuice1 = new OrangeJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice orangeJuice2 = new OrangeJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice orangeJuice3 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        orangeJuice.subtractTotalVolume(orangeJuice.convertFromSizeToVolume(DrinkSize.VENTI));
        int expectedTotalVolume = TALL_VOLUME + TALL_VOLUME + GRANDE_VOLUME;
        assertEquals(expectedTotalVolume, orangeJuice.getTotalVolume());
    }

    @Test
    void testSetPriceForTallSize() {
        FruitJuice initialOrangeJuice = new OrangeJuice();
        initialOrangeJuice.setPrice(DrinkSize.TALL);
        assertEquals(TALL_PRICE, initialOrangeJuice.getPrice());
    }

    @Test
    void testSetPriceForGrandeSize() {
        FruitJuice initialOrangeJuice = new OrangeJuice();
        initialOrangeJuice.setPrice(DrinkSize.GRANDE);
        assertEquals(GRANDE_PRICE, initialOrangeJuice.getPrice());
    }

    @Test
    void testSetPriceForVentiSize() {
        FruitJuice initialOrangeJuice = new OrangeJuice();
        initialOrangeJuice.setPrice(DrinkSize.VENTI);
        assertEquals(VENTI_PRICE, initialOrangeJuice.getPrice());
    }

    @Test
    void testValidateDrinkEnoughForTall() {
        FruitJuice orangeJuice1 = new OrangeJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice orangeJuice2 = new OrangeJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice orangeJuice3 = new OrangeJuice();
        assertTrue(orangeJuice3.validateDrink(DrinkSize.TALL));
    }

    @Test
    void testValidateDrinkNotEnoughForTall() {
        FruitJuice orangeJuice1 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice2 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice3 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice4 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice5 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice6 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice7 = new OrangeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice orangeJuice8 = new OrangeJuice();
        assertFalse(orangeJuice8.validateDrink(DrinkSize.TALL));
    }
}