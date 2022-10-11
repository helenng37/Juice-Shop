package model;

public class AppleJuice extends FruitJuice {
    private static int totalVolumeOfApple = 0;

    public AppleJuice() {
        super(FruitType.APPLE);
    }

    @Override
    public int getTotalVolume() {
        return totalVolumeOfApple;
    }

    @Override
    public void setTotalVolume(DrinkSize size) {
        switch (size) {
            case TALL:
                totalVolumeOfApple += TALL_VOLUME;
                break;
            case GRANDE:
                totalVolumeOfApple += GRANDE_VOLUME;
                break;
            case VENTI:
                totalVolumeOfApple += VENTI_VOLUME;
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
