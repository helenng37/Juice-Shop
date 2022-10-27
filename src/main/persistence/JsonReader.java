package persistence;

import model.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Method was taken from JsonReader in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads order from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Order read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOrder(jsonObject);
    }

    // Method was taken from JsonReader in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses order from JSON object and returns it
    private Order parseOrder(JSONObject order) {
        String name = order.getString("name");
        Order o = new Order(name);
        List<FruitJuice> listOfJuice = new ArrayList<>();
        for (Object value : order.getJSONArray("listOfJuice")) {
            FruitJuice juice = parseFruitJuice((JSONObject) value);
            if (juice != null)  {
                listOfJuice.add(juice);
            }
        }
        o.setListOfJuice(listOfJuice);
        return o;
    }

    // EFFECTS: parses fruitJuice from JSON object and returns it
    private FruitJuice parseFruitJuice(JSONObject fruitJuice) {
        try {
            FruitJuice juice = getFruitJuiceFromName(fruitJuice.getString("name"));
            if (juice != null) {
                FruitJuice.DrinkSize size = getDrinkSizeFromName(fruitJuice.getString("size"));
                if (size != null) {
                    juice.setSize(size);
                    juice.setPrice(size);
                    return juice;
                }
            }
        } catch (JSONException ignore) {
            return null;
        }
        return null;
    }

    private FruitJuice getFruitJuiceFromName(String name) {
        switch (name) {
            case "APPLE": return new AppleJuice();
            case "ORANGE": return new OrangeJuice();
            case "GUAVA": return new GuavaJuice();
            case "LYCHEE": return new LycheeJuice();
            case "KIWI": return new KiwiJuice();
            default: return null;
        }
    }

    private FruitJuice.DrinkSize getDrinkSizeFromName(String name) {
        switch (name) {
            case "TALL": return FruitJuice.DrinkSize.TALL;
            case "GRANDE": return FruitJuice.DrinkSize.GRANDE;
            case "VENTI": return FruitJuice.DrinkSize.VENTI;
            default: return null;
        }
    }
}

