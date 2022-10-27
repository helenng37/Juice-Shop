package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Order order = new Order("My order");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyOrder() {
        try {
            Order order = new Order("My order");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOrder.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOrder.json");
            order = reader.read();
            assertEquals("My order", order.getCustomerName());
            assertEquals(0, order.getListOfJuice().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Order order = new Order("My order");
            order.addDrink(new AppleJuice(FruitJuice.DrinkSize.VENTI));
            order.addDrink(new OrangeJuice(FruitJuice.DrinkSize.GRANDE));
            order.addDrink(new GuavaJuice(FruitJuice.DrinkSize.TALL));
            order.addDrink(new KiwiJuice(FruitJuice.DrinkSize.VENTI));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            order = reader.read();
            assertEquals("My order", order.getCustomerName());
            List<FruitJuice> drinks = order.getListOfJuice();
            assertEquals(4, drinks.size());
            checkFruitJuice(FruitJuice.FruitType.APPLE, FruitJuice.DrinkSize.VENTI, drinks.get(0));
            checkFruitJuice(FruitJuice.FruitType.ORANGE, FruitJuice.DrinkSize.GRANDE, drinks.get(1));
            checkFruitJuice(FruitJuice.FruitType.GUAVA, FruitJuice.DrinkSize.TALL, drinks.get(2));
            checkFruitJuice(FruitJuice.FruitType.KIWI, FruitJuice.DrinkSize.VENTI, drinks.get(3));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
