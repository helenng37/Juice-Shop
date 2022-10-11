package model;

public class KiwiJuice extends FruitJuice {
    private static int totalVolumeOfKiwi = 0;

    public KiwiJuice() {
        super(FruitType.KIWI);
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

    @Override
    public boolean isOutOfOrder() {
        return getTotalVolume() > FruitJuice.MAX_VOLUME;
    }

    @Override
    public int remainingVolume() {
        return FruitJuice.MAX_VOLUME - getTotalVolume();
    }
}