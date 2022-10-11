package model;

public class GuavaJuice extends FruitJuice {
    private static int totalVolumeOfGuava = 0;

    public GuavaJuice() {
        super(FruitType.GUAVA);
    }

    @Override
    public int getTotalVolume() {
        return totalVolumeOfGuava;
    }

    @Override
    public void setTotalVolume(DrinkSize size) {
        switch (size) {
            case TALL:
                totalVolumeOfGuava += TALL_VOLUME;
                break;
            case GRANDE:
                totalVolumeOfGuava += GRANDE_VOLUME;
                break;
            case VENTI:
                totalVolumeOfGuava += VENTI_VOLUME;
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
