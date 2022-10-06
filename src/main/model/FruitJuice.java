package model;

public class FruitJuice {
    public static enum DrinkSize { TALL, GRANDE, VENTI }

    public static enum FruitType { APPLE, ORANGE, GUAVA, JACKFRUIT, PAPAYA, RAMBUTAN, LYCHEE, KIWI, TOMATO, CELERY }

    public static final int MAX_VOLUME = 10000;
    public static final double TALL_PRICE = 3.50;
    public static final double GRANDE_PRICE = 4.25;
    public static final double VENTI_PRICE = 5.75;
    public static final int TALL_VOLUME = 300;
    public static final int GRANDE_VOLUME = 500;
    public static final int VENTI_VOLUME = 700;

    private double price;
    private int totalVolume;
    private FruitType type;
    private DrinkSize size;

    public FruitJuice(FruitType type, DrinkSize size) {
        this.type = type;
        this.size = size;
        setPrice(size);
        setTotalVolume(size);
    }

    public FruitType getType() {
        return this.type;
    }

    public DrinkSize getSize() {
        return this.size;
    }

    public double getPrice() {
        return this.price;
    }

    public int getTotalVolume() {
        return this.totalVolume;
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

    public void setTotalVolume(DrinkSize size) {
        switch (size) {
            case TALL:
                this.totalVolume += TALL_VOLUME;
                break;
            case GRANDE:
                this.totalVolume += GRANDE_VOLUME;
                break;
            case VENTI:
                this.totalVolume += VENTI_VOLUME;
                break;
        }
    }
}
