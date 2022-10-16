package model;

//Represents apple juice that extends FruitJuice
public class AppleJuice extends FruitJuice {
    private static int totalVolumeOfApple = 0;

    //EFFECTS: calls the constructor from the superclass FruitJuice to instantiate apple juice
    public AppleJuice() {
        super(FruitType.APPLE);
    }

    //EFFECTS: calls the constructor from the superclass FruitJuice to instantiate apple juice
    public AppleJuice(DrinkSize size) {
        super(FruitType.APPLE, size);
        setTotalVolume(size);
    }

    /*
     *MODIFIES: this
     *EFFECTS: sets totalVolumeOfApple to 0
     */
    public static void resetTotalVolume() {
        totalVolumeOfApple = 0;
    }

    @Override
    public int getTotalVolume() {
        return totalVolumeOfApple;
    }

    //REQUIRES: delta >= 0
    //MODIFIES: this
    //EFFECTS: add delta to totalVolume
    @Override
    public void addToTotalVolume(int delta) {
        totalVolumeOfApple += delta;
    }

    /*
     *MODIFIES: this
     *EFFECTS: the totalVolume is subtracted by the amount of volume
     */
    @Override
    public void subtractTotalVolume(int volume) {
        totalVolumeOfApple -= volume;
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
