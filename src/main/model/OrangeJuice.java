package model;

public class OrangeJuice extends FruitJuice {
    private static int totalVolumeOfOrange = 0;

    public OrangeJuice() {
        super(FruitType.ORANGE);
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

    @Override
    public boolean isOutOfOrder() {
        return getTotalVolume() > FruitJuice.MAX_VOLUME;
    }

    @Override
    public int remainingVolume() {
        return FruitJuice.MAX_VOLUME - getTotalVolume();
    }
}
