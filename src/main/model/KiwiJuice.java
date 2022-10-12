package model;

//Represents kiwi juice that extends FruitJuice
public class KiwiJuice extends FruitJuice {
    private static int totalVolumeOfKiwi = 0;

    //EFFECTS: calls the constructor from the superclass FruitJuice to instantiate kiwi juice
    public KiwiJuice() {
        super(FruitType.KIWI);
    }

    //EFFECTS: calls the constructor from the superclass FruitJuice to instantiate kiwi juice
    public KiwiJuice(DrinkSize size) {
        super(FruitType.KIWI, size);
        setTotalVolume(size);
    }

    /*
     *MODIFIES: this
     *EFFECTS: sets totalVolumeOfKiwi to 0
     */
    public static void resetTotalVolume() {
        totalVolumeOfKiwi = 0;
    }

    @Override
    public int getTotalVolume() {
        return totalVolumeOfKiwi;
    }

    @Override
    public void setTotalVolume(DrinkSize size) {
        switch (size) {
            case TALL:
                totalVolumeOfKiwi += TALL_VOLUME;
                break;
            case GRANDE:
                totalVolumeOfKiwi += GRANDE_VOLUME;
                break;
            case VENTI:
                totalVolumeOfKiwi += VENTI_VOLUME;
                break;
        }
    }

    /*
     *MODIFIES: this
     *EFFECTS: the totalVolume is subtracted by the amount of volume
     */
    @Override
    public void subtractTotalVolume(int volume) {
        totalVolumeOfKiwi -= volume;
    }

    /*
     * EFFECTS: return a boolean true if getTotalVolume() > FruitJuice.MAX_VOLUME, false otherwise
     */
    @Override
    public boolean isOutOfOrder() {
        return getTotalVolume() > FruitJuice.MAX_VOLUME;
    }

    /*
     * EFFECTS: return an integer that is the remaining volume of the juice after every order
     */
    @Override
    public int remainingVolume() {
        return FruitJuice.MAX_VOLUME - getTotalVolume();
    }
}