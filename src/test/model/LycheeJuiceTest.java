package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static model.FruitJuice.*;
import static org.junit.jupiter.api.Assertions.*;

class LycheeJuiceTest {
    private FruitJuice lycheeJuice;

    @BeforeEach
    void runBefore() {
        LycheeJuice.resetTotalVolume();
        lycheeJuice = new LycheeJuice(FruitJuice.DrinkSize.TALL);
    }

    @Test
    void testConstructorWithoutParameter() {
        lycheeJuice = new LycheeJuice();
        assertEquals(FruitType.LYCHEE, lycheeJuice.getType());
        assertEquals(FruitJuice.DrinkSize.NUL, lycheeJuice.getSize());
        assertEquals(0, lycheeJuice.getPrice());
    }

    @Test
    void testConstructorWithParameterSize() {
        assertEquals(FruitType.LYCHEE, lycheeJuice.getType());
        assertEquals(FruitJuice.DrinkSize.TALL, lycheeJuice.getSize());
        assertEquals(3.50, lycheeJuice.getPrice());
    }
    @Test
    void testGetTotalVolume() {
        FruitJuice lycheeJuice1 = new LycheeJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice lycheeJuice2 = new LycheeJuice(FruitJuice.DrinkSize.GRANDE);
        int expectedTotalVolume = TALL_VOLUME + GRANDE_VOLUME + GRANDE_VOLUME;
        assertEquals(expectedTotalVolume, lycheeJuice2.getTotalVolume());
    }

    @Test
    void testIsOutOfOrder() {
        FruitJuice lycheeJuice1 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice2 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice3 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice4 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice5 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice6 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice7 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        assertTrue(lycheeJuice.isOutOfOrder());
    }

    @Test
    void testIsNotOutOfOrder() {
        FruitJuice lycheeJuice1 = new LycheeJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice lycheeJuice2 = new LycheeJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice lycheeJuice3 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        assertFalse(lycheeJuice.isOutOfOrder());
    }

    @Test
    void testRemainingVolume() {
        FruitJuice lycheeJuice1 = new LycheeJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice lycheeJuice2 = new LycheeJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice lycheeJuice3 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        int expectedRemainingVolume = MAX_VOLUME - TALL_VOLUME - TALL_VOLUME -GRANDE_VOLUME - VENTI_VOLUME;
        assertEquals(expectedRemainingVolume, lycheeJuice.remainingVolume());
    }

    @Test
    void testConvertFromSizeToVolumeTallSize() {
        assertEquals(300, lycheeJuice.convertFromSizeToVolume(lycheeJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeGrandeSize() {
        lycheeJuice = new LycheeJuice(FruitJuice.DrinkSize.GRANDE);
        assertEquals(500, lycheeJuice.convertFromSizeToVolume(lycheeJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeVentiSize() {
        lycheeJuice = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        assertEquals(700, lycheeJuice.convertFromSizeToVolume(lycheeJuice.getSize()));
    }

    @Test
    void testSubtractTotalVolume() {
        FruitJuice lycheeJuice1 = new LycheeJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice lycheeJuice2 = new LycheeJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice lycheeJuice3 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        lycheeJuice.subtractTotalVolume(lycheeJuice.convertFromSizeToVolume(DrinkSize.VENTI));
        int expectedTotalVolume = TALL_VOLUME + TALL_VOLUME + GRANDE_VOLUME;
        assertEquals(expectedTotalVolume, lycheeJuice.getTotalVolume());
    }

    @Test
    void testSetPriceForTallSize() {
        FruitJuice initialLycheeJuice = new LycheeJuice();
        initialLycheeJuice.setPrice(DrinkSize.TALL);
        assertEquals(TALL_PRICE, initialLycheeJuice.getPrice());
    }

    @Test
    void testSetPriceForGrandeSize() {
        FruitJuice initialLycheeJuice = new LycheeJuice();
        initialLycheeJuice.setPrice(DrinkSize.GRANDE);
        assertEquals(GRANDE_PRICE, initialLycheeJuice.getPrice());
    }

    @Test
    void testSetPriceForVentiSize() {
        FruitJuice initialLycheeJuice = new LycheeJuice();
        initialLycheeJuice.setPrice(DrinkSize.VENTI);
        assertEquals(VENTI_PRICE, initialLycheeJuice.getPrice());
    }

    @Test
    void testValidateDrinkEnoughForTall() {
        FruitJuice lycheeJuice1 = new LycheeJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice lycheeJuice2 = new LycheeJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice lycheeJuice3 = new LycheeJuice();
        assertTrue(lycheeJuice3.validateDrink(DrinkSize.TALL));
    }

    @Test
    void testValidateDrinkNotEnoughForTall() {
        FruitJuice lycheeJuice1 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice2 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice3 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice4 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice5 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice6 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice7 = new LycheeJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice lycheeJuice8 = new LycheeJuice();
        assertFalse(lycheeJuice8.validateDrink(DrinkSize.TALL));
    }
}
