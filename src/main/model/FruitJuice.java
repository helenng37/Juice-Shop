package model;

import static model.FruitJuice.DrinkSize.*;

//Represents a drink having drink size, fruit type, price (in dolars)
public abstract class FruitJuice {
    public static enum DrinkSize { TALL, GRANDE, VENTI, NUL }

    public static enum FruitType { APPLE, ORANGE, GUAVA, LYCHEE, KIWI }

    public static final int MAX_VOLUME = 5000;
    public static final double TALL_PRICE = 3.50;
    public static final double GRANDE_PRICE = 4.25;
    public static final double VENTI_PRICE = 5.75;
    public static final int TALL_VOLUME = 300;
    public static final int GRANDE_VOLUME = 500;
    public static final int VENTI_VOLUME = 700;
    private double price;
    private FruitType type;
    private DrinkSize size;

    /*
     * REQUIRES: type is in enum FruitType including APPLE, ORANGE, GUAVA, LYCHEE, KIWI
     * EFFECTS: type is set to FruitType type, size is set to NUL
     * price is set to 0
     */
    public FruitJuice(FruitType type) {
        this.type = type;
        this.size = NUL;
        this.price = 0;
    }

    /*
     * REQUIRES: type is in enum FruitType including APPLE, ORANGE, GUAVA, LYCHEE, KIWI
     * size is in enum DrinkSize
     * EFFECTS: type is set to FruitType type, size is set to size
     * price is set by calling the method setPrice taking size as a parameter
     */
    public FruitJuice(FruitType type, DrinkSize size) {
        this.type = type;
        this.size = size;
        setPrice(size);
    }

    public FruitType getType() {
        return this.type;
    }

    public DrinkSize getSize() {
        return this.size;
    }

    public void setSize(DrinkSize size) {
        this.size = size;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(DrinkSize size) {
        if (size.equals(TALL)) {
            this.price = TALL_PRICE;
        } else if (size.equals(GRANDE)) {
            this.price = GRANDE_PRICE;
        } else if (size.equals(VENTI)) {
            this.price = VENTI_PRICE;
        } else {
            this.price = 0;
        }
    }

    /*
     * EFFECTS: returns an integer (volume of a drink) from its size
     */
    public int convertFromSizeToVolume(DrinkSize size) {
        switch (size) {
            case TALL:
                return TALL_VOLUME;
            case GRANDE:
                return GRANDE_VOLUME;
            case VENTI:
                return VENTI_VOLUME;
        }
        return 0;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if there is a drink that is not available, then remove that drink out of the list
     */
    public boolean validateDrink(DrinkSize size) {
        if (remainingVolume() >= convertFromSizeToVolume(size)) {
            setSize(size);
            setPrice(size);
            setTotalVolume(size);
            return true;
        }
        return false;
    }

    //abstract class, which is implemented in subclasses
    public abstract int getTotalVolume();

    //abstract class, which is implemented in subclasses
    public void setTotalVolume(DrinkSize size) {
        switch (size) {
            case TALL:
                addToTotalVolume(TALL_VOLUME);
                break;
            case GRANDE:
                addToTotalVolume(GRANDE_VOLUME);
                break;
            case VENTI:
                addToTotalVolume(VENTI_VOLUME);
                break;
            default: addToTotalVolume(0);
        }
    }

    public abstract void addToTotalVolume(int delta);

    //abstract class, which is implemented in subclasses
    public abstract void subtractTotalVolume(int volume);

    /* abstract class, which is implemented in subclasses
     * EFFECTS: return a boolean true if the juice is out of order, false otherwise
     */
    public abstract boolean isOutOfOrder();

    /* abstract class, which is implemented in subclasses
     * EFFECTS: return an integer that is the remaining volume of the juice after every order
     */
    public abstract int remainingVolume();
}
