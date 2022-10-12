package model;

//Represents lychee juice that extends FruitJuice
public class LycheeJuice extends FruitJuice {
    private static int totalVolumeOfLychee = 0;

    //EFFECTS: calls the constructor from the superclass FruitJuice to instantiate lychee juice
    public LycheeJuice() {
        super(FruitType.LYCHEE);
    }

    //EFFECTS: calls the constructor from the superclass FruitJuice to instantiate lychee juice
    public LycheeJuice(DrinkSize size) {
        super(FruitType.LYCHEE, size);
        setTotalVolume(size);
    }

    /*
     *MODIFIES: this
     *EFFECTS: sets totalVolumeOfLychee to 0
     */
    public static void resetTotalVolume() {
        totalVolumeOfLychee = 0;
    }

    @Override
    public int getTotalVolume() {
        return totalVolumeOfLychee;
    }

    @Override
    public void setTotalVolume(DrinkSize size) {
        switch (size) {
            case TALL:
                totalVolumeOfLychee += TALL_VOLUME;
                break;
            case GRANDE:
                totalVolumeOfLychee += GRANDE_VOLUME;
                break;
            case VENTI:
                totalVolumeOfLychee += VENTI_VOLUME;
                break;
        }
    }

    /*
     *MODIFIES: this
     *EFFECTS: the totalVolume is subtracted by the amount of volume
     */
    @Override
    public void subtractTotalVolume(int volume) {
        totalVolumeOfLychee -= volume;
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
