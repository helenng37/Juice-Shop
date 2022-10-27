package persistence;

import model.FruitJuice;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Order order = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyOrder() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyOrder.json");
        try {
            Order order = reader.read();
            assertEquals("My order", order.getCustomerName());
            assertEquals(0, order.getListOfJuice().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralOrder() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralOrder.json");
        try {
            Order order = reader.read();
            assertEquals("My order", order.getCustomerName());
            List<FruitJuice> drinks = order.getListOfJuice();
            assertEquals(4, drinks.size());
            checkFruitJuice(FruitJuice.FruitType.APPLE, FruitJuice.DrinkSize.GRANDE, drinks.get(0));
            checkFruitJuice(FruitJuice.FruitType.KIWI, FruitJuice.DrinkSize.TALL, drinks.get(1));
            checkFruitJuice(FruitJuice.FruitType.ORANGE, FruitJuice.DrinkSize.VENTI, drinks.get(2));
            checkFruitJuice(FruitJuice.FruitType.LYCHEE, FruitJuice.DrinkSize.TALL, drinks.get(3));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNoSize() {
        JsonReader reader = new JsonReader("./data/testReaderNoSize.json");
        try {
            Order order = reader.read();
            assertEquals("My order", order.getCustomerName());
            List<FruitJuice> drinks = order.getListOfJuice();
            assertEquals(0, drinks.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNoName() {
        JsonReader reader = new JsonReader("./data/testReaderNoName.json");
        try {
            Order order = reader.read();
            assertEquals("My order", order.getCustomerName());
            List<FruitJuice> drinks = order.getListOfJuice();
            assertEquals(0, drinks.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderInvalidSize() {
        JsonReader reader = new JsonReader("./data/testReaderInvalidSize.json");
        try {
            Order order = reader.read();
            assertEquals("My order", order.getCustomerName());
            List<FruitJuice> drinks = order.getListOfJuice();
            assertEquals(0, drinks.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderInvalidName() {
        JsonReader reader = new JsonReader("./data/testReaderInvalidName.json");
        try {
            Order order = reader.read();
            assertEquals("My order", order.getCustomerName());
            List<FruitJuice> drinks = order.getListOfJuice();
            assertEquals(0, drinks.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderInvalidSizeAndName() {
        JsonReader reader = new JsonReader("./data/testReaderInvalidSizeAndName.json");
        try {
            Order order = reader.read();
            assertEquals("My order", order.getCustomerName());
            List<FruitJuice> drinks = order.getListOfJuice();
            assertEquals(0, drinks.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
