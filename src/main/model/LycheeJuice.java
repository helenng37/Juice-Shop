package model;

public class LycheeJuice extends FruitJuice {
    private static int totalVolumeOfLychee = 0;

    public LycheeJuice() {
        super(FruitType.LYCHEE);
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

    @Override
    public boolean isOutOfOrder() {
        return getTotalVolume() > FruitJuice.MAX_VOLUME;
    }

    @Override
    public int remainingVolume() {
        return FruitJuice.MAX_VOLUME - getTotalVolume();
    }
}
