package persistence;

import model.FruitJuice;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFruitJuice(FruitJuice.FruitType fruitType, FruitJuice.DrinkSize drinkSize, FruitJuice fruitJuice) {
        assertEquals(fruitType, fruitJuice.getType());
        assertEquals(drinkSize, fruitJuice.getSize());
    }
}
