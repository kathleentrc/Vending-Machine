import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

/**
 * 	This MaintenanceView displays the maintenance menu of the vending machine
 */
public class MaintenanceView {
    private ImageIcon icon;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JComboBox<String> slotComboBox;
    private JSpinner quantitySpinner;
    private JFrame restockFrame;
    private JFrame setPriceFrame;
    private JFrame collectMoneyFrame;
    private JFrame replenishMoneyFrame;
    private JFrame startingInventoryFrame;
    private JFrame endingInventoryFrame;
    private JFrame transactionsFrame;
    private VendingMachine vendingMachineModel;
    private MainMenuView mainMenuView;

    /**
     * Constructs a MaintenanceView object
     *  
     * @param vendingMachineModel          Responsible for the tasks and features performed by the vending machine
     * @param mainMenuView                 Shows the main menu view features
     */
    public MaintenanceView(VendingMachine vendingMachineModel, MainMenuView mainMenuView) {
        this.vendingMachineModel = vendingMachineModel;
        this.mainMenuView = mainMenuView;

        // Set up the main frame
        this.icon = new ImageIcon(getClass().getResource("/Images/VM ICON.png"));
        frame = new JFrame("Perform Vending Machine Maintenance");
        frame.setSize(480, 680); // Set the size of the frame
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Create the main panel and set layout
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon bgImageIcon = new ImageIcon(getClass().getResource("/Images/MAINTENANCE BG.png"));
                Image bgImage = bgImageIcon.getImage();
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Create the button panel on the left
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.setLayout(new GridLayout(8, 1, 10, 10));

        // Add buttons to the button panel
        String[] buttonNames = {
                "Restock Item", "Set Price", "Collect Money", "Replenish Money",
                "Starting Inventory", "Ending Inventory", "Transactions", "Exit"
        };
        for (String name : buttonNames) {
            JButton button = new JButton(name);
            button.addActionListener(new MaintenanceController(this, vendingMachineModel, mainMenuView));
            buttonPanel.add(button);
        }

        // Create separate frames for each option
        this.restockFrame = new JFrame("Restock Item");
        this.setPriceFrame = new JFrame("Set Price");
        this.collectMoneyFrame = new JFrame("Collect Money");
        this.replenishMoneyFrame = new JFrame("Replenish Money");
        this.startingInventoryFrame = new JFrame("Staring Inventory");
        this.endingInventoryFrame = new JFrame("Ending Inventory");
        this.transactionsFrame = new JFrame("Transactions");

        // Add button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.WEST);

        // Add main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(false);
    }

    /**
     * 	Gets the restockFrame
     */
    public void getRestockFrame() {
        restockFrame.getContentPane().removeAll();
        restockFrame.setSize(300, 300);
        restockFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel slotLabel = new JLabel("Choose a slot:");
        restockFrame.add(slotLabel, gbc);

        gbc.gridy++;
        String[] slotOptions  = {"Lettuce", "Tomato", "Cucumber", "Onion", "Roasted Chicken", 
        "Boiled Egg", "Spinach", "Grapes", "Croutons", "Fresh Herbs", 
        "Dressing", "Crushed Peanuts"};
        slotComboBox = new JComboBox<>(slotOptions);
        restockFrame.add(slotComboBox, gbc);

        gbc.gridy++;
        JLabel quantityLabel = new JLabel("Input Quantity:");
        restockFrame.add(quantityLabel, gbc);

        gbc.gridy++;
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 20, 1);
        this.quantitySpinner = new JSpinner(spinnerModel);
        restockFrame.add(quantitySpinner, gbc);

        gbc.gridy++;
        JButton addButton = new JButton("Add Stocks");
        restockFrame.add(addButton, gbc);

        // Make the frame visible after adding components
        restockFrame.setLocationRelativeTo(null);
        restockFrame.setResizable(false);
        restockFrame.setVisible(true);  
    }

    /**
     * 	Gets the current frame
     * 
     *  @return             current frame
     */
    public JFrame getFrame(){
        return this.frame;
    }

    /**
     * Gets the collectMoneyFrame
     */
    public void getCollectMoneyFrame() {
        collectMoneyFrame.getContentPane().removeAll();
        collectMoneyFrame.setSize(300, 300);
        collectMoneyFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Add "Coins" label and combo box
        JLabel coinsLabel = new JLabel("Coins:");
        collectMoneyFrame.add(coinsLabel, gbc);

        gbc.gridx++;
        String[] coinOptions = {"0", "1", "5", "10"};
        JComboBox<String> coinComboBox = new JComboBox<>(coinOptions);
        collectMoneyFrame.add(coinComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Add "Coins Quantity" label and spinner
        JLabel coinsQuantityLabel = new JLabel("Coins Quantity:");
        collectMoneyFrame.add(coinsQuantityLabel, gbc);

        gbc.gridx++;
        SpinnerNumberModel coinsSpinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner coinsSpinner = new JSpinner(coinsSpinnerModel);
        collectMoneyFrame.add(coinsSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Add "Bills" label and combo box
        JLabel billsLabel = new JLabel("Bills:");
        collectMoneyFrame.add(billsLabel, gbc);

        gbc.gridx++;
        String[] billOptions = {"0", "20", "50", "100", "200", "500", "1000"};
        JComboBox<String> billComboBox = new JComboBox<>(billOptions);
        collectMoneyFrame.add(billComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Add "Bills Quantity" label and spinner
        JLabel billsQuantityLabel = new JLabel("Bills Quantity:");
        collectMoneyFrame.add(billsQuantityLabel, gbc);

        gbc.gridx++;
        SpinnerNumberModel billsSpinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner billsSpinner = new JSpinner(billsSpinnerModel);
        collectMoneyFrame.add(billsSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; 
        JButton collectMoneyButton = new JButton("Collect Money");
        collectMoneyFrame.add(collectMoneyButton, gbc);

        // Make the frame visible after adding components
        collectMoneyFrame.setLocationRelativeTo(null);
        collectMoneyFrame.setResizable(false);
        collectMoneyFrame.setVisible(true);
    }

    /**
     * 	Gets the replenishMoneyFrame
     */
    public void getReplenishMoneyFrame() {
        replenishMoneyFrame.getContentPane().removeAll();
        replenishMoneyFrame.setSize(300, 300);
        replenishMoneyFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Add "Coins" label and combo box
        JLabel coinsLabel = new JLabel("Coins:");
        replenishMoneyFrame.add(coinsLabel, gbc);

        gbc.gridx++;
        String[] coinOptions = {"0", "1", "5", "10"};
        JComboBox<String> coinComboBox = new JComboBox<>(coinOptions);
        replenishMoneyFrame.add(coinComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Add "Coins Quantity" label and spinner
        JLabel coinsQuantityLabel = new JLabel("Coins Quantity:");
        replenishMoneyFrame.add(coinsQuantityLabel, gbc);

        gbc.gridx++;
        SpinnerNumberModel coinsSpinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner coinsSpinner = new JSpinner(coinsSpinnerModel);
        replenishMoneyFrame.add(coinsSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Add "Bills" label and combo box
        JLabel billsLabel = new JLabel("Bills:");
        replenishMoneyFrame.add(billsLabel, gbc);

        gbc.gridx++;
        String[] billOptions = {"0", "20", "50", "100", "200", "500", "1000"};
        JComboBox<String> billComboBox = new JComboBox<>(billOptions);
        replenishMoneyFrame.add(billComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Add "Bills Quantity" label and spinner
        JLabel billsQuantityLabel = new JLabel("Bills Quantity:");
        replenishMoneyFrame.add(billsQuantityLabel, gbc);

        gbc.gridx++;
        SpinnerNumberModel billsSpinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner billsSpinner = new JSpinner(billsSpinnerModel);
        replenishMoneyFrame.add(billsSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; 
        JButton collectMoneyButton = new JButton("Collect Money");
        replenishMoneyFrame.add(collectMoneyButton, gbc);

        // Make the frame visible after adding components
        replenishMoneyFrame.setLocationRelativeTo(null);
        replenishMoneyFrame.setResizable(false);
        replenishMoneyFrame.setVisible(true);
    }

    /**
     * 	Gets the startingInventoryFrame
     */
    public void getStartingInventoryFrame() {
        startingInventoryFrame.getContentPane().removeAll();
        startingInventoryFrame.setSize(300, 300);
        startingInventoryFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel startingLabel = new JLabel("STARTING INVENTORY");
        startingInventoryFrame.add(startingLabel, gbc);

        // Display the starting inventory
        JTextArea startingInventoryTextArea = new JTextArea(10, 20); // Rows and columns for the text area
        startingInventoryTextArea.setEditable(false); // Make the text area read-only

        // Add a scrollpane so that it will still fit
        JScrollPane scrollPane = new JScrollPane(startingInventoryTextArea);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        startingInventoryFrame.add(scrollPane, gbc);

        String startingInventoryData = "Loading Starting Inventory...";
        startingInventoryTextArea.setText(startingInventoryData);

        startingInventoryFrame.setLocationRelativeTo(null);
        startingInventoryFrame.setResizable(false);
        startingInventoryFrame.setVisible(true);
    }
    
    /**
     * 	Gets the endingInventoryFrame
     */
    public void getEndingInventoryFrame() {
        endingInventoryFrame.getContentPane().removeAll();
        endingInventoryFrame.setSize(300, 300);
        endingInventoryFrame.setLayout(new GridBagLayout());
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
    
        JLabel endingLabel = new JLabel("ENDING INVENTORY");
        endingInventoryFrame.add(endingLabel, gbc);
    
        // Display the ending inventory
        JTextArea endingInventoryTextArea = new JTextArea(10, 20); // Rows and columns for the text area
        endingInventoryTextArea.setEditable(false); // Make the text area read-only
    
        // Add a scrollpane so that it will still fit
        JScrollPane scrollPane = new JScrollPane(endingInventoryTextArea);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        endingInventoryFrame.add(scrollPane, gbc);
    
        String endingInventoryData = "Loading Ending Inventory...";
        endingInventoryTextArea.setText(endingInventoryData);
    
        // Make the frame visible after adding components
        endingInventoryFrame.setLocationRelativeTo(null);
        endingInventoryFrame.setResizable(false);
        endingInventoryFrame.setVisible(true);
    }

    /**
     * 	Gets the transactionsFrame
     */
    public void getTransactionsFrame() {
        transactionsFrame.getContentPane().removeAll();
        transactionsFrame.setSize(300, 300);
        transactionsFrame.setLayout(new GridBagLayout());
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
    
        JLabel transactionsLabel = new JLabel("TRANSACTIONS SUMMARY");
        transactionsFrame.add(transactionsLabel, gbc);
    
        // Display the ending inventory
        JTextArea transactionsTextArea = new JTextArea(10, 20); // Rows and columns for the text area
        transactionsTextArea.setEditable(false); // Make the text area read-only
    
        // Add a scrollpane so that it will still fit
        JScrollPane scrollPane = new JScrollPane(transactionsTextArea);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        transactionsFrame.add(scrollPane, gbc);
    
        String transactionsData = "Loading Transactions Summary...";
        transactionsTextArea.setText(transactionsData);
    
        // Make the frame visible after adding components
        transactionsFrame.setLocationRelativeTo(null);
        transactionsFrame.setResizable(false);
        transactionsFrame.setVisible(true);
    }

    /**
     * 	Gets the slotComboBox
     * 
     *  @return         slotComboBox
     */
    public JComboBox<String> getSlotComboBox() {
        return this.slotComboBox;
    }

    /**
     * 	Gets the quantitySpinner
     * 
     *  @return         quantitySpinner
     */
    public JSpinner getQuantitySpinner() {
        return this.quantitySpinner;
    }

    /**
     * 	Gets the setPriceFrame
     * 
     */
    public void getSetPriceFrame() {
        setPriceFrame.getContentPane().removeAll();
        setPriceFrame.setSize(300, 300);
        setPriceFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel slotLabel = new JLabel("Choose a slot:");
        setPriceFrame.add(slotLabel, gbc);

        gbc.gridy++;
        String[] slotOptions  = {"Lettuce", "Tomato", "Cucumber", "Onion", "Roasted Chicken", 
        "Boiled Egg", "Spinach", "Grapes", "Croutons", "Fresh Herbs", 
        "Dressing", "Crushed Peanuts"};
        JComboBox<String> slotComboBox = new JComboBox<>(slotOptions);
        setPriceFrame.add(slotComboBox, gbc);

        gbc.gridy++;
        JLabel quantityLabel = new JLabel("Input New Price:");
        setPriceFrame.add(quantityLabel, gbc);

        gbc.gridy++;
        JTextField priceTextField = new JTextField(10); // Create a text field to input the new price
        setPriceFrame.add(priceTextField, gbc);

        gbc.gridy++;
        JButton setPriceButton = new JButton("Set New Price");
        setPriceFrame.add(setPriceButton, gbc);

        // Make the frame visible after adding components
        setPriceFrame.setLocationRelativeTo(null);
        setPriceFrame.setResizable(false);
        setPriceFrame.setVisible(true);  
    }

}
