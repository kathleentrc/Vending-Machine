import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 	This RegularVendingView represents the 'View' of the MVC architecture that we used for our Regular Vending Machine
 */
public class RegularVendingView {
    /**
     * Image used for the icon of the program
     */
    protected ImageIcon icon; 
    /**
     * Main frame of the program
     */
    protected JFrame frame; 
    /**
     * Combo box of item names the user could choose from
     */
    protected JComboBox<String> nameOptions; 
    /**
     * Text field where a user could put an input
     */
    protected JTextField textField; 
    /**
     * Combo box of the quanity an item could have that the user could choose from
     */
    protected JComboBox<String> quantityOptions; 
    /**
     * Text area for the user to input the item's calories
     */
    protected JTextArea caloriesInput;
    /**
     * Text are for the user the input the item's price
     */
    protected JTextArea priceInput;  
    /**
     * Button for the user to confirm their choices
     */
    protected JButton chooseButton; 
    /**
     * Button for the user to save their choices
     */
    protected JButton saveButton;  
    /**
     * GridBagConstraits for proper layout
     */
    protected GridBagConstraints gbc;  

    // Regular Vending Machine attributes
    private ArrayList<JButton> buttonList;
    private JButton payButton;
    private JTextArea chosenItemTextArea;
    private JButton dispenseButton;
    private JButton lastClickedSlot;
    private JButton customizeButton;
    private JButton maintenanceButton;
    private String[] buttonLabels; 
    private int buttonIndex;
    private int lastClickedSlotIndex;
    /**
     * Slot list consisting of items
     */
    protected ArrayList<Slot> itemList;

    /**
     * Constructs a regular vending view object
     */
    public RegularVendingView(){
        this.icon = new ImageIcon(getClass().getResource("/Images/VM ICON.png")); 
        this.frame = new JFrame("Customize Your Vending Machine"); 

        this.frame.setSize(480, 680); 
        this.frame.setIconImage(icon.getImage());

        this.gbc = new GridBagConstraints();

        String[] availableItems = {"Lettuce", "Tomato", "Cucumber", "Onion", "Roasted Chicken", 
                                   "Boiled Egg", "Spinach", "Grapes", "Croutons", "Fresh Herbs", 
                                   "Dressing", "Crushed Peanuts"};

        this.chooseButton = new JButton("Choose");
        this.nameOptions = new JComboBox<>(availableItems);
        this.textField = new JTextField(30);

        this.saveButton = new JButton("Save");

        String[] itemQuantity = {"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        this.quantityOptions = new JComboBox<>(itemQuantity);

        this.caloriesInput = new JTextArea(2, 10);
        this.priceInput = new JTextArea(2, 10);

        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        this.payButton = new JButton("Pay");
        
        this.chosenItemTextArea = new JTextArea();

        this.buttonList = new ArrayList<>();
        this.customizeButton = new JButton("Customize");
        this.maintenanceButton = new JButton("Maintenance");
        
        this.dispenseButton = new JButton("Dispense");
        this.buttonLabels = new String[]{
            "01", "02", "03", "04", "05", "06",
            "07", "08", "09", "10", "11", "12"
        };
    }

    /**
     * Displays the assigned image as the background of the panel
     * 
     * @return          The background image
     */
    public JPanel displayBackground() {

        final ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Images/ASK USER RVM.png"));
        JPanel regVMBG = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, null);
            }
        };

        return regVMBG;
    }

    /**
     * Shows the GUI when the user are asked to make their own Regular Vending Machine 
     */
    public void CustomRVM() {
        frame.setSize(480, 680);
        JPanel regVMBG = displayBackground();

        JPanel panel = new JPanel(new GridBagLayout());

        // Item Name
        JLabel nameLabel = new JLabel("Item Name: ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; // Column index 
        gbc.gridy = 0; // Row index
        gbc.anchor = GridBagConstraints.WEST; // Anchor point
        gbc.insets = new Insets(5, 17, 5, 17); // Padding
        panel.add(nameLabel, gbc);

        textField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Columns to span
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill component
        panel.add(textField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameOptions, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(chooseButton, gbc);

        // Item Quantity
        JLabel quantityLabel = new JLabel("Item Quantity: ");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 12));
        quantityLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(quantityOptions, gbc);

        // Item Calories
        JLabel caloriesLabel = new JLabel("Item Calories: ");
        caloriesLabel.setFont(new Font("Arial", Font.BOLD, 12));
        caloriesLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(caloriesLabel, gbc);

        JScrollPane caloriesScrollPane = new JScrollPane(caloriesInput);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(caloriesScrollPane, gbc);

        // Item Price
        JLabel priceLabel = new JLabel("Item Price: ");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 12));
        priceLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(priceLabel, gbc);

        JScrollPane priceScrollPane = new JScrollPane(priceInput);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(priceScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(this.saveButton, gbc);

        panel.setOpaque(false); // Make the panel transparent

        regVMBG.add(panel);

        frame.setIconImage(icon.getImage());
        frame.getContentPane().add(regVMBG);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Method that displays the created vending machine that the user has customized
     */
    public void displayVendingMachine() {
        this.frame = new JFrame("Greens & Grains");
        frame.setSize(480, 680);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(icon.getImage());
    
        // Create the background panel with the image
        final ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Images/TEMPLATE.png"));
        JPanel regVMBG = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        int rows = 4;
        int columns = 3;
        int hGap = 5;
        int vGap = 5;
        int buttonWidth = 50;
        int buttonHeight = 20;
        int largeButtonWidth = 100;
        int largeButtonHeight = 40;
        
        // Filenames for the images to be displayed on the Vending Machine
        String[] imageFilenames = {
            "1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png","9.png","10.png","11.png","12.png",
        };

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding
        
        // Panel 1 : Vending Machine Products Image
        JPanel imagePanel = new JPanel(new GridLayout(3, columns, hGap, vGap));
        imagePanel.setOpaque(false);
        for (int i = 0; i < imageFilenames.length; i++) {
            String imageFilename = imageFilenames[i];
            Image image = loadImageFromFile(imageFilename); // Load image based on filename
            if (image != null) {
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                imagePanel.add(imageLabel);
            } else {
                System.err.println("Error loading image: " + imageFilename);
            }
        }
        mainPanel.add(imagePanel, BorderLayout.NORTH);

        // Panel 2: Buttons and ComboBoxes 
        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.setOpaque(false);

        // Panel 2 Left: Number Buttons
        JPanel buttonsColumnPanel = new JPanel(new GridLayout(rows, columns, hGap, vGap));
        buttonsColumnPanel.setOpaque(false);

        for (int i = 0; i < (rows * columns); i++) {
            int finalButtonIndex = i; 
            JButton button = new JButton(buttonLabels[i]); 
            button.setActionCommand(Integer.toString(i)); 
            buttonList.add(button);
            button.setFont(new Font("Arial", Font.PLAIN, 12));
            button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        
            // Add ActionListener to each button
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chosenItemTextArea.setText(""); // Clears the text area
                    displayItem(finalButtonIndex);
                    setLastClickedSlotIndex(finalButtonIndex);
                }
            });
            buttonsColumnPanel.add(button);
            buttonList.add(button);
        }        
        buttonsPanel.add(buttonsColumnPanel, BorderLayout.WEST);

        // Panel 2 Right: Chosen Item Text Area
        chosenItemTextArea.setEditable(false);
        mainPanel.add(new JScrollPane(chosenItemTextArea), BorderLayout.CENTER);
        
        // Combo boxes column panel
        JPanel comboBoxesColumnPanel = new JPanel(new GridBagLayout());
        comboBoxesColumnPanel.setOpaque(false);

        // Creates the 'Pay' button
        payButton.setFont(new Font("Arial", Font.PLAIN, 12));
        payButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        comboBoxesColumnPanel.add(payButton, gbc);
       
        // Creates  the 'Current Chosen Item' label and text box
        JLabel currentChosenItemLabel = new JLabel("Current Chosen Item:");
        currentChosenItemLabel.setForeground(Color.WHITE); 
        currentChosenItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane chosenItemScrollPane = new JScrollPane(chosenItemTextArea);
        chosenItemScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        chosenItemTextArea.setLineWrap(true);
        chosenItemTextArea.setWrapStyleWord(true);
        chosenItemTextArea.setPreferredSize(new Dimension(150, 80));

        // Creates  the 'Dispense' button
        dispenseButton.setFont(new Font("Arial", Font.PLAIN, 12));
        dispenseButton.setPreferredSize(new Dimension(100, 30));

        // Add components to the panel with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3; 
        gbc.insets = new Insets(0, 0, 5, 0);
        comboBoxesColumnPanel.add(currentChosenItemLabel, gbc);

        gbc.gridy = 3;
        gbc.weighty = 1.0; 
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 5, 0);
        comboBoxesColumnPanel.add(chosenItemScrollPane, gbc);

        gbc.gridy = 4;
        gbc.weighty = 0.0; 
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 0, 0);
        comboBoxesColumnPanel.add(dispenseButton, gbc);

        buttonsPanel.add(comboBoxesColumnPanel, BorderLayout.CENTER);

        // Create and add maintenance button 
        maintenanceButton.setFont(new Font("Arial", Font.PLAIN, 12));
        maintenanceButton.setPreferredSize(new Dimension(largeButtonWidth, largeButtonHeight));

        buttonsPanel.add(maintenanceButton, BorderLayout.SOUTH);

        // Create and add customize button 
        customizeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        customizeButton.setPreferredSize(new Dimension(largeButtonWidth, largeButtonHeight));

        buttonsPanel.add(customizeButton, BorderLayout.NORTH);

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.setOpaque(false);

        regVMBG.add(mainPanel, gbc);
        regVMBG.setOpaque(false);

        // Add the background panel to the frame
        frame.setContentPane(regVMBG);

        frame.getContentPane().add(mainPanel);

        // Show the frame after all components are added
        frame.setVisible(true);
    }

    /**
     * 	Displays the item and appends it in the Chosen Item Text Area
     */
    private void displayItem(int index) {
        chosenItemTextArea.append("Item: " + getItems().get(index).getSpecificItem().getName() + "\n"); 
        chosenItemTextArea.append("Price: " + getItems().get(index).getSpecificItem().getPrice() + "\n"); 
        chosenItemTextArea.append("Calories: " + getItems().get(index).getSpecificItem().getCalories() + "\n"); 
        chosenItemTextArea.append("Quantity: " + getItems().get(index).getItemList().size() + "\n"); 
    }

        private static Image loadImageFromFile(String filename) {
            try {
                ClassLoader classLoader = RegularVendingView.class.getClassLoader();
                // Replace "images/" with the directory where your images are located relative to the classpath
                String imagePath = "images/" + filename;
                return ImageIO.read(classLoader.getResource(imagePath));
            } catch (IOException | NullPointerException ex) {
                System.err.println("Error loading image: " + filename);
            }
            return null;
        }

    // Getters

    /**
     * Gets the choose button
     * 
     * @return             'choose' button
     */
    public JButton getChooseButton() {
        return chooseButton;
    }

    /**
     * Gets the save button
     * 
     * @return             'save' button
     */
    public JButton getSaveButton() {
        return saveButton;
    }

    /**
     * Gets the item's name options
     * 
     * @return             name options combo box
     */
    public JComboBox<String> getNameOptions() {
        return nameOptions;
    }

    /**
     * Gets the text field of the current item name the user chose
     * 
     * @return             textfield
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * Gets the options for the quanity of each slots
     * 
     * @return             quantity options combo box
     */
    public JComboBox<String> getQuantityOptions() {
        return quantityOptions;
    }

    /**
     * Gets the text area where the user would input the amount of calories an item have
     * 
     * @return             text area for calories input
     */
    public JTextArea getCaloriesInput() {
        return caloriesInput;
    }

    /**
     * Gets the text area where the user would input the price an item would have
     * 
     * @return             text area for price input
     */
    public JTextArea getPriceInput() {
        return priceInput;
    }

    /**
     * Gets the current frame
     * 
     * @return             frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets the dispense button
     * 
     * @return             'dispense' button
     */
    public JButton getDispenseButton(){
        return dispenseButton;
    }

    /**
     * Gets the array list consisting the number buttons
     * 
     * @return             button list
     */
    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }

    /**
     * Gets the text area where the information about the user's chosen item displays
     * 
     * @return             chosen item text area
     */
    public JTextArea getTextArea() {
        return chosenItemTextArea;
    }

    /**
     * Gets the user's last clicked number button for the slots
     * 
     * @return             last clcked slot
     */
    public JButton getLastClickedSlot() {
        return this.lastClickedSlot;
    }


    /**
     * Gets the text area where the information about the user's chosen item displays
     * 
     * @return             chosen item text area
     */
    public JTextArea getChosenItemTextArea() {
        return chosenItemTextArea;
    }

    /**
     * Gets the customize button that allows the user to customize a salad
     * 
     * @return             'customize' button
     */
    public JButton getCustomizeButton() {
        return customizeButton;
    }

    /**
     * Gets the maintenance button that allows the user to perform a maintenance 
     * 
     * @return             'maintenance' button
     */
    public JButton getMaintenanceButton() {
        return maintenanceButton;
    }

    /**
     * Gets the index of the buttons
     * 
     * @return             button index
     */
    public int getButtonIndex() {
        return buttonIndex;
    }

    /**
     * Gets the label of the buttons
     * 
     * @return             buttons label
     */
    public String[] getButtonLabels() {
        return buttonLabels;
    }

    /**
     * Gets the index of the user's last clicked button for the slots
     * 
     * @return             last clicked slot index
     */
    public int getLastClickedSlotIndex() {
        return this.lastClickedSlotIndex;
    }

    /**
     * Gets the list of the items in the slot
     * 
     * @return             item list
     */
    public ArrayList<Slot> getItems() {
        return this.itemList;
    }

    /**
     * Gets the pay button the user uses when paying
     * 
     * @return             'pay' button
     */
    public JButton getPayButton(){
        return this.payButton;
    }

    /**
     * Sets the last clicked slot of the user
     * 
     * @param slot        button of the last clicked slot 
     */
    public void setLastClickedSlot(JButton slot) {
        this.lastClickedSlot = slot;
    }

    /**
     * Sets the index for the user's last clicked slot
     * 
     * @param index      index of the last clicked index
     */
    public void setLastClickedSlotIndex(int index) {
        this.lastClickedSlotIndex = index;
    }

    /**
     *  Sets the items inside the item lists
     * 
     * @param items      items inside the slot list
     */
    public void setItems(ArrayList<Slot> items) {
        this.itemList = items;
    }
}