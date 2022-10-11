package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<FruitJuice> listOfJuice;
    private double totalBill;

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


}
