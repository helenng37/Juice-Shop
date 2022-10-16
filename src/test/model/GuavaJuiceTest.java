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
    void testSetPriceForTallSize() {
        FruitJuice initialGuavaJuice = new GuavaJuice();
        initialGuavaJuice.setPrice(DrinkSize.TALL);
        assertEquals(3.50, initialGuavaJuice.getPrice());
    }

    @Test
    void testSetPriceForGrandeSize() {
        FruitJuice initialGuavaJuice = new GuavaJuice();
        initialGuavaJuice.setPrice(DrinkSize.GRANDE);
        assertEquals(4.25, initialGuavaJuice.getPrice());
    }

    @Test
    void testSetPriceForVentiSize() {
        FruitJuice initialGuavaJuice = new GuavaJuice();
        initialGuavaJuice.setPrice(DrinkSize.VENTI);
        assertEquals(5.75, initialGuavaJuice.getPrice());
    }

    @Test
    void testValidateDrinkEnoughForTall() {
        FruitJuice guavaJuice1 = new GuavaJuice(FruitJuice.DrinkSize.TALL);
        FruitJuice guavaJuice2 = new GuavaJuice(FruitJuice.DrinkSize.GRANDE);
        FruitJuice guavaJuice3 = new GuavaJuice();
        assertTrue(guavaJuice3.validateDrink(DrinkSize.VENTI));
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

