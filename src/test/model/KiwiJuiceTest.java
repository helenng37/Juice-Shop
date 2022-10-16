package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static model.FruitJuice.*;
import static org.junit.jupiter.api.Assertions.*;

class KiwiJuiceTest {
    private FruitJuice kiwiJuice;

    @BeforeEach
    void runBefore() {
        KiwiJuice.resetTotalVolume();
        kiwiJuice = new KiwiJuice(FruitJuice.DrinkSize.TALL);
    }

    @Test
    void testConstructorWithoutParameter() {
        kiwiJuice = new KiwiJuice();
        assertEquals(FruitType.KIWI, kiwiJuice.getType());
        assertEquals(FruitJuice.DrinkSize.NUL, kiwiJuice.getSize());
        assertEquals(0, kiwiJuice.getPrice());
    }

    @Test
    void testConstructorWithParameterSize() {
        assertEquals(FruitType.KIWI, kiwiJuice.getType());
        assertEquals(FruitJuice.DrinkSize.TALL, kiwiJuice.getSize());
        assertEquals(3.50, kiwiJuice.getPrice());
    }
    @Test
    void testGetTotalVolume() {
        FruitJuice kiwiJuice1 = new KiwiJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice kiwiJuice2 = new KiwiJuice(FruitJuice.DrinkSize.GRANDE);
        int expectedTotalVolume = TALL_VOLUME + GRANDE_VOLUME + GRANDE_VOLUME;
        assertEquals(expectedTotalVolume, kiwiJuice2.getTotalVolume());
    }

    @Test
    void testSetTotalVolumeForTallInput() {
        KiwiJuice.resetTotalVolume();
        FruitJuice kiwiJuice1 = new KiwiJuice();
        kiwiJuice1.setSize(DrinkSize.TALL);
        kiwiJuice1.setTotalVolume(DrinkSize.TALL);
        assertEquals(TALL_VOLUME, kiwiJuice1.getTotalVolume());
    }

    @Test
    void testSetTotalVolumeForGrandeInput() {
        KiwiJuice.resetTotalVolume();
        FruitJuice kiwiJuice2 = new KiwiJuice();
        kiwiJuice2.setSize(DrinkSize.GRANDE);
        kiwiJuice2.setTotalVolume(DrinkSize.GRANDE);
        assertEquals(GRANDE_VOLUME, kiwiJuice2.getTotalVolume());
    }

    @Test
    void testSetTotalVolumeForVentiInput() {
        KiwiJuice.resetTotalVolume();
        FruitJuice kiwiJuice3 = new KiwiJuice();
        kiwiJuice3.setSize(DrinkSize.VENTI);
        kiwiJuice3.setTotalVolume(DrinkSize.VENTI);
        assertEquals(VENTI_VOLUME, kiwiJuice3.getTotalVolume());
    }

    @Test
    void testSetTotalVolumeForDefaultInput() {
        KiwiJuice.resetTotalVolume();
        FruitJuice kiwiJuice1 = new KiwiJuice();
        kiwiJuice1.setTotalVolume(DrinkSize.NUL);
        assertEquals(0, kiwiJuice1.getTotalVolume());
    }

    @Test
    void testIsOutOfOrder() {
        FruitJuice kiwiJuice1 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice2 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice3 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice4 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice5 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice6 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice7 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        assertTrue(kiwiJuice.isOutOfOrder());
    }

    @Test
    void testIsNotOutOfOrder() {
        FruitJuice kiwiJuice1 = new KiwiJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice kiwiJuice2 = new KiwiJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice kiwiJuice3 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        assertFalse(kiwiJuice.isOutOfOrder());
    }

    @Test
    void testRemainingVolume() {
        FruitJuice kiwiJuice1 = new KiwiJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice kiwiJuice2 = new KiwiJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice kiwiJuice3 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        int expectedRemainingVolume = MAX_VOLUME - TALL_VOLUME - TALL_VOLUME -GRANDE_VOLUME - VENTI_VOLUME;
        assertEquals(expectedRemainingVolume, kiwiJuice.remainingVolume());
    }

    @Test
    void testConvertFromSizeToVolumeTallSize() {
        assertEquals(300, kiwiJuice.convertFromSizeToVolume(kiwiJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeGrandeSize() {
        kiwiJuice = new KiwiJuice(FruitJuice.DrinkSize.GRANDE);
        assertEquals(500, kiwiJuice.convertFromSizeToVolume(kiwiJuice.getSize()));
    }

    @Test
    void testConvertFromSizeToVolumeVentiSize() {
        kiwiJuice = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        assertEquals(700, kiwiJuice.convertFromSizeToVolume(kiwiJuice.getSize()));
    }

    @Test
    void testSubtractTotalVolume() {
        FruitJuice kiwiJuice1 = new KiwiJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice kiwiJuice2 = new KiwiJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice kiwiJuice3 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        kiwiJuice.subtractTotalVolume(kiwiJuice.convertFromSizeToVolume(DrinkSize.VENTI));
        int expectedTotalVolume = TALL_VOLUME + TALL_VOLUME + GRANDE_VOLUME;
        assertEquals(expectedTotalVolume, kiwiJuice.getTotalVolume());
    }

    @Test
    void testSetPriceForTallSize() {
        KiwiJuice.resetTotalVolume();
        FruitJuice kiwiJuice1 = new KiwiJuice();
        kiwiJuice1.setPrice(DrinkSize.TALL);
        assertEquals(TALL_PRICE, kiwiJuice1.getPrice());
    }

    @Test
    void testSetPriceForGrandeSize() {
        KiwiJuice.resetTotalVolume();
        FruitJuice kiwiJuice2 = new KiwiJuice();
        kiwiJuice2.setPrice(DrinkSize.GRANDE);
        assertEquals(GRANDE_PRICE, kiwiJuice2.getPrice());
    }

    @Test
    void testSetPriceForVentiSize() {
        KiwiJuice.resetTotalVolume();
        FruitJuice kiwiJuice3 = new KiwiJuice();
        kiwiJuice3.setPrice(DrinkSize.VENTI);
        assertEquals(VENTI_PRICE, kiwiJuice3.getPrice());
    }

    @Test
    void testSetPriceForNulInput() {
        AppleJuice.resetTotalVolume();
        FruitJuice kiwiJuice1 = new KiwiJuice();
        kiwiJuice1.setPrice(DrinkSize.NUL);
        assertEquals(0, kiwiJuice1.getPrice());
    }

    @Test
    void testValidateDrinkEnoughForTall() {
        FruitJuice kiwiJuice1 = new KiwiJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice kiwiJuice2 = new KiwiJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice kiwiJuice3 = new KiwiJuice();
        assertTrue(kiwiJuice3.validateDrink(DrinkSize.TALL));
    }

    @Test
    void testValidateDrinkNotEnoughForTall() {
        FruitJuice kiwiJuice1 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice2 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice3 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice4 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice5 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice6 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice7 = new KiwiJuice(FruitJuice.DrinkSize.VENTI);
        FruitJuice kiwiJuice8 = new KiwiJuice();
        assertFalse(kiwiJuice8.validateDrink(DrinkSize.TALL));
    }
}