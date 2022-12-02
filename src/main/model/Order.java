package model;

import logging.Event;
import logging.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

//Represents an order having a list of juice and the total bill
public class Order implements Writable {
    private List<FruitJuice> listOfJuice;
    private double totalBill;
    private String customerName;

    /*
     * EFFECTS: listOfJuice is set to a new ArrayList of FruitJuice
     * and totalBill is set to 0
     */
    public Order(String name) {
        this.listOfJuice = new ArrayList<FruitJuice>();
        this.totalBill = 0;
        setCustomerName(name);
        EventLog.getInstance().logEvent(new Event("Create new fruit juice order"));
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
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
        EventLog.getInstance().logEvent(new Event("Total bill set: $" + totalBill));
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
            EventLog.getInstance().logEvent(new Event("Add to order: " + juice.getType() + " - " + juice.getSize()));
        } else {
            EventLog.getInstance().logEvent(new Event("Add to order failed: "
                    + juice.getType() + " - " + juice.getSize()));
        }
    }

    @Override
    public JSONObject toJson() {
        JSONArray listOfJuice = new JSONArray();
        for (FruitJuice f: this.listOfJuice) {
            listOfJuice.put(f.toJson());
        }

        JSONObject json = new JSONObject();
        json.put("name", customerName);
        json.put("listOfJuice", listOfJuice);
        return json;
    }
}


