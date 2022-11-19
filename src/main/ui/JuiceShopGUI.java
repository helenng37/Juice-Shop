package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JuiceShopGUI extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new JuiceShopGUI();
    }

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private static final int LABEL_HEIGHT = 30;
    private static final int X_OFFSET_BASE = 10;
    private static final int Y_OFFSET_HEAD = 10;
    private static final int Y_OFFSET_BODY = 210;
    private static final int BUTTON_WIDTH = 150;

    private List<OneDrink> drinkList = new ArrayList<>();
    private Order order;
    private String orderSummary;

    public JuiceShopGUI() {
        super("Juice Shop");
        resetOrder();
        initializeHeader();
        initializeBody();
    }


    /*
     * REQUIRES:
     * MODIFIES: order, orderSummary
     * EFFECTS: re/initialize order, orderSummary
     */
    private void resetOrder() {
        order = new Order("");
        orderSummary = "";
    }

    /*
     * REQUIRES: drinkList
     * MODIFIES: order, orderSummary
     * EFFECTS: add more drinks to order if the number of drinks in the order isn't equal to the drink list
     */
    @SuppressWarnings("methodlength")
    public Order getOrder() {
        StringBuilder sb = new StringBuilder();
        List<FruitJuice> juices = new ArrayList<>();
        boolean allOutOfOrder = true;
        for (int i = order.getListOfJuice().size(); i < drinkList.size(); i++) {
            OneDrink d = drinkList.get(i);
            FruitJuice.FruitType t = d.getSelectedType();
            if (t != null) {
                FruitJuice j = getFruitJuice(t);
                FruitJuice.DrinkSize s = d.getSelectedSize();
                if (j != null && s != null) {
                    if (j.validateDrink(s)) {
                        j.setSize(s);
                        j.setPrice(s);
                        order.addDrink(j);
                        juices.add(j);
                        sb.append(j.getType()).append(" - ")
                                .append(j.getSize()).append(" - $")
                                .append(j.getPrice()).append("\n");
                        allOutOfOrder = false;
                    } else {
                        sb.append("Out of Order\n");
                    }
                }
            }
        }
        if (!allOutOfOrder) {
            order.setTotalBill(juices);
            orderSummary += sb.toString();
        }
        return order;
    }

    /*
     * REQUIRES:
     * MODIFIES: order, orderSummary, drinkList
     * EFFECTS: set new order, load the order on the display screen,
     * and create new order summary according to the new order
     */
    public void setOrder(Order order) {
        if (order != null) {
            this.order = order;
            StringBuilder sb = new StringBuilder();
            for (FruitJuice j : order.getListOfJuice()) {
                OneDrink d = getNextDrinkForm();
                d.setSelected(j.getType(), j.getSize());
                d.draw();
                sb.append(j.getType()).append(" - ")
                        .append(j.getSize()).append(" - $")
                        .append(j.getPrice()).append("\n");
            }
            revalidate();
            repaint();
            orderSummary = sb.toString();
        }
    }

    /*
     * MODIFIES: order, orderSummary if necessary
     * EFFECTS: return the order summary to display on a panel
     */
    public String getOrderSummary() {
        getOrder();
        return "Your Order:\n" + orderSummary + "Total Bill: $" + getOrder().getTotalBill();
    }

    /*
     * EFFECTS: display welcome message and images of available fruits
     */
    private void initializeHeader() {
        JLabel welcome = new JLabel("WELCOME TO THE JUICE SHOP!");
        welcome.setBounds(X_OFFSET_BASE, Y_OFFSET_HEAD, 500, LABEL_HEIGHT);
        add(welcome);
        JLabel juiceChoice = new JLabel("FRUITS OF THE DAY:");
        juiceChoice.setBounds(X_OFFSET_BASE, Y_OFFSET_HEAD + LABEL_HEIGHT, 500, LABEL_HEIGHT);
        add(juiceChoice);
        for (FruitJuice.FruitType t: FruitJuice.FruitType.values()) {
            JLabel fruit = new JLabel(t.name());
            fruit.setBounds(40 + 130 * t.ordinal(), Y_OFFSET_HEAD + LABEL_HEIGHT * 2, 130, LABEL_HEIGHT);
            add(fruit);
            JLabel fruitImg = new JLabel(getImageIcon(t));
            fruitImg.setBounds(130 * t.ordinal(), Y_OFFSET_HEAD + LABEL_HEIGHT * 3, 130, 100);
            add(fruitImg);
        }
    }

    //EFFECTS: get location of fruit images
    private ImageIcon getImageIcon(FruitJuice.FruitType t) {
        switch (t) {
            case APPLE: return new ImageIcon("src/resource/apple.png");
            case GUAVA: return new ImageIcon("src/resource/guava.png");
            case KIWI: return new ImageIcon("src/resource/kiwi.png");
            case LYCHEE: return new ImageIcon("src/resource/lychee.png");
            case ORANGE: return new ImageIcon("src/resource/orange.png");
            default: return null;
        }
    }

    /*
     * MODIFIES: the display panel
     * EFFECTS: draw the display panel with messages and buttons
     */
    private void initializeBody() {
        new SubmitOrder(this);
        drawAddDrinkButton();
        new SaveOrder(this);
        new LoadOrder(this);

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    /*
     * EFFECTS: create the button to add more drink items when clicked
     */
    private void drawAddDrinkButton() {
        JButton button = new JButton("Add Drink");
        button.setBounds(X_OFFSET_BASE * 2 + BUTTON_WIDTH, Y_OFFSET_BODY, BUTTON_WIDTH, LABEL_HEIGHT);
        add(button);
        button.addActionListener(this);
    }

    /*
     * MODIFIES: drinkList
     * EFFECTS: add one more drink selection to the drinkList and the display panel
     */
    private OneDrink getNextDrinkForm() {
        OneDrink d = new OneDrink(this, X_OFFSET_BASE,
                Y_OFFSET_BODY + LABEL_HEIGHT + (LABEL_HEIGHT * 2 * drinkList.size()));
        drinkList.add(d);
        return d;
    }

    /*
     * MODIFIES: the display panel, drinkList
     * EFFECTS: handles the action when the Add Drink button is clicked:
     * add one more drink selection with non-selected fields
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        getNextDrinkForm().draw();
        revalidate();
        repaint();
    }

    // Submit Order button
    public static class SubmitOrder implements ActionListener {
        private final JuiceShopGUI juiceShopGUI;

        public SubmitOrder(JuiceShopGUI juiceShopGUI) {
            this.juiceShopGUI = juiceShopGUI;
            JButton button = new JButton("Submit Order");
            button.setBounds(X_OFFSET_BASE, Y_OFFSET_BODY, BUTTON_WIDTH, LABEL_HEIGHT);
            juiceShopGUI.add(button);
            button.addActionListener(this);
        }

        /*
         * REQUIRES:
         * MODIFIES: order, orderSummary
         * EFFECTS: re-read the drinkList for drink in the order and display the order summary
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            juiceShopGUI.resetOrder();
            JOptionPane.showMessageDialog(juiceShopGUI, juiceShopGUI.getOrderSummary());
        }
    }

    // Save Order button
    public static class SaveOrder implements ActionListener {
        private final JuiceShopGUI juiceShopGUI;

        public SaveOrder(JuiceShopGUI juiceShopGUI) {
            this.juiceShopGUI = juiceShopGUI;
            JButton button = new JButton("Save Order");
            button.setBounds(X_OFFSET_BASE * 3 + BUTTON_WIDTH * 2, Y_OFFSET_BODY, BUTTON_WIDTH, LABEL_HEIGHT);
            juiceShopGUI.add(button);
            button.addActionListener(this);
        }

        /*
         * REQUIRES:
         * MODIFIES: order, orderSummary
         * EFFECTS: re-read the drinkList for drink in the order, save the order toe file, and display the order summary
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            juiceShopGUI.resetOrder();
            saveOrder(juiceShopGUI.getOrder());
            JOptionPane.showMessageDialog(juiceShopGUI, "Order Saved:\n" + juiceShopGUI.getOrderSummary());
        }
    }

    // Load Order button
    public static class LoadOrder implements ActionListener {
        private final JuiceShopGUI juiceShopGUI;

        public LoadOrder(JuiceShopGUI juiceShopGUI) {
            this.juiceShopGUI = juiceShopGUI;
            JButton button = new JButton("Load Order");
            button.setBounds(X_OFFSET_BASE * 4 + BUTTON_WIDTH * 3, Y_OFFSET_BODY, BUTTON_WIDTH, LABEL_HEIGHT);
            juiceShopGUI.add(button);
            button.addActionListener(this);
        }

        /*
         * MODIFIES: drinkList, order, orderSummary
         * EFFECTS: load the order from file and display the order
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            for (OneDrink d: juiceShopGUI.drinkList) {
                d.remove();
            }
            juiceShopGUI.drinkList = new ArrayList<>();
            juiceShopGUI.setOrder(loadOrder());
        }
    }

    // One drink selection of fruit type and size
    public static class OneDrink {
        private static final int TYPE_WIDTH = 100;
        private static final int SIZE_WIDTH = 100;

        private final JFrame jframe;
        private final int xoffset;
        private final int yoffset;
        private final ButtonGroup bgTypes = new ButtonGroup();
        private final ButtonGroup bgSizes = new ButtonGroup();
        private List<JLabel> labels = new ArrayList<>();
        private List<JRadioButton> buttons = new ArrayList<>();

        private final Map<FruitJuice.FruitType, JRadioButton> types = new HashMap<>();
        private final Map<FruitJuice.DrinkSize, JRadioButton> sizes = new HashMap<>();

        private FruitJuice.FruitType selectedType;
        private FruitJuice.DrinkSize selectedSize;

        private OneDrink(JFrame jframe, int xoffset, int yoffset) {
            this.jframe = jframe;
            this.xoffset = xoffset;
            this.yoffset = yoffset;
        }

        /*
         * REQUIRES:
         * MODIFIES: the panel
         * EFFECTS: draw another line of drink type and size selections
         */
        private void draw() {
            addLabels();
            addTypeRadioButtons();
            addSizeRadioButtons();
        }

        /*
         * REQUIRES:
         * MODIFIES: selectedType, selectedSize
         * EFFECTS: set the selected fruit type and size
         */
        private void setSelected(FruitJuice.FruitType selectedType, FruitJuice.DrinkSize selectedSize) {
            this.selectedType = selectedType;
            this.selectedSize = selectedSize;
        }

        /*
         * REQUIRES:
         * MODIFIES: the panel
         * EFFECTS: display the labels of juice type and drink size selections
         */
        private void addLabels() {
            JLabel juiceChoice = new JLabel("Select Juice");
            juiceChoice.setBounds(xoffset, yoffset, FruitJuice.FruitType.values().length * TYPE_WIDTH, LABEL_HEIGHT);
            jframe.add(juiceChoice);
            labels.add(juiceChoice);

            JLabel juiceSize = new JLabel("Select Size");
            juiceSize.setBounds(xoffset * 3 + FruitJuice.FruitType.values().length * TYPE_WIDTH, yoffset,
                    FruitJuice.FruitType.values().length * SIZE_WIDTH, LABEL_HEIGHT);
            jframe.add(juiceSize);
            labels.add(juiceSize);
        }

        /*
         * REQUIRES:
         * MODIFIES: the panel
         * EFFECTS: draw the radio buttons for fruit type selection
         */
        private void addTypeRadioButtons() {
            for (FruitJuice.FruitType t: FruitJuice.FruitType.values()) {
                JRadioButton button = new JRadioButton(t.name(), selectedType != null && selectedType.equals(t));
                button.setBounds(xoffset + TYPE_WIDTH * t.ordinal(), yoffset + LABEL_HEIGHT, TYPE_WIDTH, LABEL_HEIGHT);
                jframe.add(button);
                bgTypes.add(button);
                types.put(t, button);
                buttons.add(button);
            }
        }

        /*
         * REQUIRES:
         * MODIFIES: the panel
         * EFFECTS: draw the radio buttons for drink size selection
         */
        private void addSizeRadioButtons() {
            for (FruitJuice.DrinkSize s: FruitJuice.DrinkSize.values()) {
                if (s != FruitJuice.DrinkSize.NUL) {
                    JRadioButton button = new JRadioButton(s.name(), selectedSize != null && selectedSize.equals(s));
                    button.setBounds(xoffset * 3 + FruitJuice.FruitType.values().length * TYPE_WIDTH
                                    + SIZE_WIDTH * s.ordinal(), yoffset + LABEL_HEIGHT, SIZE_WIDTH, LABEL_HEIGHT);
                    jframe.add(button);
                    bgSizes.add(button);
                    sizes.put(s, button);
                    buttons.add(button);
                }
            }
        }

        //EFFECTS: return the selected fruit type from the radio buttons
        private FruitJuice.FruitType getSelectedType() {
            for (FruitJuice.FruitType j: types.keySet()) {
                if (types.get(j).isSelected()) {
                    return j;
                }
            }
            return null;
        }

        //EFFECTS: return the selected fruit size from the radio buttons
        private FruitJuice.DrinkSize getSelectedSize() {
            for (FruitJuice.DrinkSize j: sizes.keySet()) {
                if (sizes.get(j).isSelected()) {
                    return j;
                }
            }
            return null;
        }

        /*
         * REQUIRES:
         * MODIFIES: the panel
         * EFFECTS: remove this drink's selections and labels from the panel display
         */
        private void remove() {
            for (JLabel l: labels) {
                jframe.remove(l);
            }
            for (JRadioButton b: buttons) {
                jframe.remove(b);
            }
        }
    }

    //EFFECTS: return the fruit juice from type
    private static FruitJuice getFruitJuice(FruitJuice.FruitType type) {
        switch (type) {
            case APPLE: return new AppleJuice();
            case GUAVA: return new GuavaJuice();
            case KIWI: return new KiwiJuice();
            case LYCHEE: return new LycheeJuice();
            case ORANGE: return new OrangeJuice();
            default: return null;
        }
    }

    private static final String JSON_STORE = "./data/order.json";

    private static final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private static final JsonReader jsonReader = new JsonReader(JSON_STORE);

    //EFFECTS: save the order to the file
    private static void saveOrder(Order order) {
        try {
            jsonWriter.open();
            jsonWriter.write(order);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: load the o
    private static Order loadOrder() {
        try {
            return jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            return null;
        }
    }
}
