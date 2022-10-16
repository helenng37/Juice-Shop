package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static model.FruitJuice.*;
import static org.junit.jupiter.api.Assertions.*;

class AppleJuiceTest {

    private FruitJuice appleJuice;
    @BeforeEach
    void runBefore() {
        AppleJuice.resetTotalVolume();
        appleJuice = new AppleJuice(FruitJuice.DrinkSize.TALL);
    }

    @Test
    void testConstructorWithoutParameter() {
        appleJuice = new AppleJuice();
        assertEquals(FruitJuice.FruitType.APPLE, appleJuice.getType());
        assertEquals(FruitJuice.DrinkSize.NUL, appleJuice.getSize());
        assertEquals(0, appleJuice.getPrice());
    }

    @Test
    void testConstructorWithParameterSize() {
        assertEquals(FruitJuice.FruitType.APPLE, appleJuice.getType());
        assertEquals(FruitJuice.DrinkSize.TALL, appleJuice.getSize());
        assertEquals(3.50, appleJuice.getPrice());
    }
    @Test
    void testGetTotalVolume() {
        FruitJuice appleJuice1 = new AppleJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice appleJuice2 = new AppleJuice(FruitJuice.DrinkSize.GRANDE);
        int expectedTotalVolume = TALL_VOLUME + GRANDE_VOLUME + GRANDE_VOLUME;
        assertEquals(expectedTotalVolume, appleJuice2.getTotalVolume());
    }

    @Test
    void testSetTotalVolumeForTallInput() {
        AppleJuice.resetTotalVolume();
        FruitJuice appleJuice1 = new AppleJuice();
        appleJuice1.setSize(DrinkSize.TALL);
        appleJuice1.setTotalVolume(DrinkSize.TALL);
        assertEquals(TALL_VOLUME, appleJuice1.getTotalVolume());
    }

    @Test
    void testSetTotalVolumeForGrandeInput() {
        AppleJuice.resetTotalVolume();
        FruitJuice appleJuice2 = new AppleJuice();
        appleJuice2.setSize(DrinkSize.GRANDE);
        appleJuice2.setTotalVolume(DrinkSize.GRANDE);
        assertEquals(GRANDE_VOLUME, appleJuice2.getTotalVolume());
    }

    @Test
    void testSetTotalVolumeForVentiInput() {
        AppleJuice.resetTotalVolume();
        FruitJuice appleJuice3 = new AppleJuice();
        appleJuice3.setSize(DrinkSize.VENTI);
        appleJuice3.setTotalVolume(DrinkSize.VENTI);
        assertEquals(VENTI_VOLUME, appleJuice3.getTotalVolume());
    }

    @Test
    void testIsOutOfOrder() {
        FruitJuice appleJuice1 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice2 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice3 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice4 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice5 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice6 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice7 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        assertTrue(appleJuice.isOutOfOrder());
    }

    @Test
    void testIsNotOutOfOrder() {
        FruitJuice appleJuice1 = new AppleJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice appleJuice2 = new AppleJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice appleJuice3 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        assertFalse(appleJuice3.isOutOfOrder());
    }

    @Test
    void testRemainingVolume() {
        FruitJuice appleJuice1 = new AppleJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice appleJuice2 = new AppleJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice appleJuice3 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        int expectedRemainingVolume = MAX_VOLUME - TALL_VOLUME - TALL_VOLUME -GRANDE_VOLUME - VENTI_VOLUME;
        assertEquals(expectedRemainingVolume, appleJuice3.remainingVolume());
    }

    @Test
    void testConvertFromSizeToVolumeTallSize() {
        assertEquals(300, appleJuice.convertFromSizeToVolume(appleJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeGrandeSize() {
        appleJuice = new AppleJuice(FruitJuice.DrinkSize.GRANDE);
        assertEquals(500, appleJuice.convertFromSizeToVolume(appleJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeVentiSize() {
        appleJuice.setSize(DrinkSize.VENTI);
        assertEquals(700, appleJuice.convertFromSizeToVolume(appleJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeNulSize() {
        appleJuice.setSize(DrinkSize.NUL);
        assertEquals(0, appleJuice.convertFromSizeToVolume(appleJuice.getSize()));
    }

    @Test
    void testSubtractTotalVolume() {
        FruitJuice appleJuice1 = new AppleJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice appleJuice2 = new AppleJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice appleJuice3 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        appleJuice.subtractTotalVolume(appleJuice.convertFromSizeToVolume(DrinkSize.VENTI));
        int expectedTotalVolume = TALL_VOLUME + TALL_VOLUME + GRANDE_VOLUME;
        assertEquals(expectedTotalVolume, appleJuice3.getTotalVolume());
    }

    @Test
    void testSetPriceForTallInput() {
        AppleJuice.resetTotalVolume();
        FruitJuice appleJuice1 = new AppleJuice();
        appleJuice1.setPrice(DrinkSize.TALL);
        assertEquals(TALL_PRICE, appleJuice1.getPrice());
    }

    @Test
    void testSetPriceForGrandeInput() {
        AppleJuice.resetTotalVolume();
        FruitJuice appleJuice2 = new AppleJuice();
        appleJuice2.setPrice(DrinkSize.GRANDE);
        assertEquals(GRANDE_PRICE, appleJuice2.getPrice());
    }

    @Test
    void testSetPriceForVentiInput() {
        AppleJuice.resetTotalVolume();
        FruitJuice appleJuice3 = new AppleJuice();
        appleJuice3.setPrice(DrinkSize.VENTI);
        assertEquals(VENTI_PRICE, appleJuice3.getPrice());
    }

    @Test
    void testValidateDrinkEnoughForTall() {
        FruitJuice appleJuice1 = new AppleJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice appleJuice2 = new AppleJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice appleJuice3 = new AppleJuice();
        assertTrue(appleJuice3.validateDrink(DrinkSize.TALL));
    }

    @Test
    void testValidateDrinkNotEnoughForTall() {
        FruitJuice appleJuice1 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice2 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice3 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice4 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice5 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice6 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice7 = new AppleJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice appleJuice8 = new AppleJuice();
        assertFalse(appleJuice8.validateDrink(DrinkSize.TALL));
    }
}