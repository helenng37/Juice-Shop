package model;

//Represents orange juice that extends FruitJuice
public class OrangeJuice extends FruitJuice {
    private static int totalVolumeOfOrange = 0;

    //EFFECTS: calls the constructor from the superclass FruitJuice to instantiate orange juice
    public OrangeJuice() {
        super(FruitType.ORANGE);
    }

    //EFFECTS: calls the constructor from the superclass FruitJuice to instantiate orange juice
    public OrangeJuice(DrinkSize size) {
        super(FruitType.ORANGE, size);
        setTotalVolume(size);
    }

    /*
     *MODIFIES: this
     *EFFECTS: sets totalVolumeOfOrange to 0
     */
    public static void resetTotalVolume() {
        totalVolumeOfOrange = 0;
    }

    @Override
    public int getTotalVolume() {
        return totalVolumeOfOrange;
    }

    @Override
    public void setTotalVolume(DrinkSize size) {
        switch (size) {
            case TALL:
                totalVolumeOfOrange += TALL_VOLUME;
                break;
            case GRANDE:
                totalVolumeOfOrange += GRANDE_VOLUME;
                break;
            case VENTI:
                totalVolumeOfOrange += VENTI_VOLUME;
                break;
        }
    }

    /*
     *MODIFIES: this
     *EFFECTS: the totalVolume is subtracted by the amount of volume
     */
    @Override
    public void subtractTotalVolume(int volume) {
        totalVolumeOfOrange -= volume;
    }

    /*
     * EFFECTS: return a boolean true if remainingVolume() < TALL_VOLUME, false otherwise
     */
    @Override
    public boolean isOutOfOrder() {
        return remainingVolume() < TALL_VOLUME;
    }

    /*
     * EFFECTS: return an integer that is the remaining volume of the juice after every order
     */
    @Override
    public int remainingVolume() {
        return FruitJuice.MAX_VOLUME - getTotalVolume();
    }
}
