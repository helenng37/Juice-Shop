package model;

import static model.FruitJuice.DrinkSize.NUL;

public abstract class FruitJuice {
    public static enum DrinkSize { TALL, GRANDE, VENTI, NUL }

    public static enum FruitType { APPLE, ORANGE, GUAVA, LYCHEE, KIWI }

    public static final int MAX_VOLUME = 10000;
    public static final double TALL_PRICE = 3.50;
    public static final double GRANDE_PRICE = 4.25;
    public static final double VENTI_PRICE = 5.75;
    public static final int TALL_VOLUME = 300;
    public static final int GRANDE_VOLUME = 500;
    public static final int VENTI_VOLUME = 700;
    private double price;
    private int totalVolume = 0;
    private FruitType type;
    private DrinkSize size;

    public FruitJuice(FruitType type) {
        this.type = type;
        this.size = NUL;
        setPrice(size);
        setTotalVolume(size);
    }

    public FruitType getType() {
        return this.type;
    }

    public void setType(FruitType type) {
        this.type = type;
    }

    public void setSize(DrinkSize size) {
        this.size = size;
    }

    public DrinkSize getSize() {
        return this.size;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(DrinkSize size) {
        switch (size) {
            case TALL:
                this.price = TALL_PRICE;
                break;
            case GRANDE:
                this.price = GRANDE_PRICE;
                break;
            case VENTI:
                this.price = VENTI_PRICE;
                break;
        }
    }

    public abstract int getTotalVolume();

    public abstract void setTotalVolume(DrinkSize size);

    public abstract boolean isOutOfOrder();

    public abstract int remainingVolume();


}
