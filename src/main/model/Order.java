package model;

import java.util.ArrayList;
import java.util.List;

import static model.FruitJuice.MAX_VOLUME;
import static model.FruitJuice.TALL_VOLUME;

//Represents an order having a list of juice and the total bill
public class Order {
    private List<FruitJuice> listOfJuice;
    private double totalBill;

    /*
     * EFFECTS: listOfJuice is set to a new ArrayList of FruitJuice
     * and totalBill is set to 0
     */
    public Order() {
        this.listOfJuice = new ArrayList<FruitJuice>();
        this.totalBill = 0;
    }

    public List<FruitJuice> getListOfJuice() {
        return this.listOfJuice;
    }

    public double getTotalBill() {
        return this.totalBill;
    }

    public void setListOfJuice(List<FruitJuice> listOfJuice) {
        this.listOfJuice = listOfJuice;
    }

    public void setTotalBill(List<FruitJuice> listOfJuice) {
        for (FruitJuice f: listOfJuice) {
            totalBill += f.getPrice();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds juice to the order
     */
    public void addDrink(FruitJuice juice) {
        int tmpVolume = juice.convertFromSizeToVolume(juice.getSize());
        juice.subtractTotalVolume(tmpVolume);
        if (tmpVolume <= juice.remainingVolume()) {
            listOfJuice.add(juice);
            juice.setTotalVolume(juice.getSize());
        }
    }
}


    /*
     * MODIFIES: this
     * EFFECTS: if there is a drink that is not available, then remove that drink out of the list
     */
//    public void checkAvailabilityAndRemoveDrink() {
//        List<FruitJuice> removedJuiceList = new ArrayList<>();
//        List<FruitJuice> newJuiceList = new ArrayList<>();
//        for (FruitJuice f: listOfJuice) {
//            if (MAX_VOLUME - f.getTotalVolume() < TALL_VOLUME) {
//                removedJuiceList.add(f);
//            }
//        }
//        for (int i = 0; i < listOfJuice.size(); i++) {
//            if (!(listOfJuice.get(i).equals(removedJuiceList.get(i)))) {
//                newJuiceList.add(listOfJuice.get(i));
//            }
//        }
//        listOfJuice = newJuiceList;
//    }
//}
