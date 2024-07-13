import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/**
 * 	This RegularVendingController class is linked to the model and view of the regular vending machine
 */
public class RegularVendingController {
    private ArrayList<String> savedItems;
    private int index;
    private int savedItemCount = 0; 
    private boolean isChooseButtonClicked; 
    /**
     * 	Model of a regular vending machine
     */
    protected VendingMachine regularVendingModel; 
    /**
     * 	View of a regular vending machine
     */
    protected RegularVendingView regularVendingView;
    /**
     * 	Denominations of a regular vending machine
     */
    protected Denominations denominations; 
    private boolean hasPaid;
    private MaintenanceController maintenanceController;
    private MaintenanceView maintenanceView;
    private MainMenuView mainMenuView;

    /**
     * Constructs a RegularVendingController object
     * 
     * @param regularVendingModel         the model of the regular vending machine
     * @param regularVendingView          the view of the regular vending machine
     * @param mainMenuView                the view of the main menu 
     */
    public RegularVendingController(VendingMachine regularVendingModel, RegularVendingView regularVendingView, MainMenuView mainMenuView) {
        this.regularVendingModel = regularVendingModel;
        this.regularVendingView = regularVendingView;
        this.mainMenuView = mainMenuView;
        this.maintenanceView = new MaintenanceView(regularVendingModel, mainMenuView);
        this.maintenanceController = new MaintenanceController(new MaintenanceView(regularVendingModel, mainMenuView), regularVendingModel, mainMenuView);
        this.denominations = new Denominations();
        this.index = 0;

        this.isChooseButtonClicked = false;
        this.hasPaid = false;

        this.savedItems = new ArrayList<>();


        this.maintenanceView.getFrame().setVisible(false);

        chooseButtonAction();
        dispenseItemAction();
        customizeAction();
        maintenanceAction();
        payAction();
    }

    /**
     * Chooses an item based on the button selected
     */
    public void chooseButtonAction() {
        JButton chooseButton = this.regularVendingView.getChooseButton();

        // Add ActionListener to chooseButton
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) regularVendingView.getNameOptions().getSelectedItem();
                if (selectedOption != null) {
                    regularVendingView.getTextField().setText(selectedOption); // Display the selected item name in the textField
                    isChooseButtonClicked = true;
                }
            }
        });
    
        // Get the saveButton instance from the regularVendingView
        JButton saveButton = regularVendingView.getSaveButton();

        // Add ActionListener to saveButton
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) regularVendingView.getNameOptions().getSelectedItem();
                String quantity = (String) regularVendingView.getQuantityOptions().getSelectedItem();
                String calories = regularVendingView.getCaloriesInput().getText().trim();
                String price = regularVendingView.getPriceInput().getText().trim();

                // Check if any of the inputs are empty
                if (name.isEmpty() || quantity.isEmpty() || calories.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int quantityValue = Integer.parseInt(quantity);
                        double caloriesValue = Double.parseDouble(calories);
                        double priceValue = Double.parseDouble(price);

                        if (!isChooseButtonClicked) {
                            JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Please click 'Choose' to select an item before saving.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (savedItemCount < 12) {
                            // Add the selected item to the list of saved items
                            savedItems.add(name);

                            // Add a slot
                            regularVendingModel.addSlot(new Slot((index+1), new Item(name, caloriesValue, priceValue, quantityValue)));
                            regularVendingModel.getSlotList().get(index).addItem(quantityValue);
                            index++; // increment index for next slot

                            // Reset the flag for "Choose" button click
                            isChooseButtonClicked = false;

                            // Remove the chosen option from the nameComboBox
                            regularVendingView.getNameOptions().removeItemAt(regularVendingView.getNameOptions().getSelectedIndex());

                            // Set text in textField based on the most recent option after removal
                            if (regularVendingView.getNameOptions().getItemCount() > 0) {
                                regularVendingView.getTextField().setText((String) regularVendingView.getNameOptions().getItemAt(0));
                            } else {
                                regularVendingView.getTextField().setText(""); // Clear the text areas
                            }

                            JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Saved successfully!", "Save Success", JOptionPane.INFORMATION_MESSAGE);
                            regularVendingView.getCaloriesInput().setText(""); // Clear the text areas after saving
                            regularVendingView.getPriceInput().setText(""); // Clear the text areas after saving
                            regularVendingView.getTextField().setText(""); // Clear the text areas after saving
                            savedItemCount++; // Increment savedItemCount when an item is successfully saved

                            // required number of slots
                            if (savedItemCount == 12){ 
                            regularVendingView.getFrame().setVisible(false);
                            regularVendingView.displayVendingMachine();
                        }
                    }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Please enter valid numeric values for Calories and Price.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }); 
    }


    /**
     * Receives the user payment
     */
    public void payAction() {
        JButton payButton = this.regularVendingView.getPayButton();
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaymentOptionsFrame();
            }
        });
    }
    
    /**
     * Displays the denominations upon payment
     */
    private void showPaymentOptionsFrame() {
        // Create a new frame for the payment options
        JFrame paymentFrame = new JFrame("Payment Options");
        paymentFrame.setSize(300, 100);
        paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentFrame.setLocationRelativeTo(null);

        // Create the choice combo box for payment options
        String[] paymentOptions = {"Bills", "Coins"};
        JComboBox<String> paymentComboBox = new JComboBox<>(paymentOptions);
        paymentComboBox.setEditable(true); // Allow custom input for the combo box
    
        // Add an ActionListener to the paymentComboBox
        paymentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) paymentComboBox.getSelectedItem();
                if (selectedOption != null && !selectedOption.equals("Choose your payment denomination: ")) {
                    if (selectedOption.equals("Bills")) {
                        displayBillsFrame(paymentFrame);
                    } else if (selectedOption.equals("Coins")){
                        displayCoinsFrame(paymentFrame);
                    }
                }
            }
        });
    
        // Set the placeholder text as the selected item but not a selectable option
        paymentComboBox.setSelectedItem("Choose your payment denominations");
    
        // Add the combo box to the frame
        paymentFrame.add(paymentComboBox, BorderLayout.CENTER);
    
        // Show the frame
        paymentFrame.setVisible(true);
    }
    
    /**
     * Inserts coins into the machine and dispenses change
     */
    private void displayCoinsFrame(JFrame paymentFrame) {
        // Close the paymentFrame before displaying the coins frame
        paymentFrame.dispose();
    
        // Create a new frame for the coins
        JFrame coinsFrame = new JFrame("Coins Payment");
        coinsFrame.setSize(250, 150);
        coinsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        coinsFrame.setLocationRelativeTo(null);
    
        // Create a label to display payment instructions
        JLabel instructionLabel = new JLabel("\u20B1");
    
        // Create the choice combo box for coins
        String[] coinOptions = {"1", "5", "10"};
        JComboBox<String> coinsComboBox = new JComboBox<>(coinOptions);
    
        // Create a spinner for coins quantity
        SpinnerModel coinsSpinnerModel = new SpinnerNumberModel(1, 1, 1000, 1); // Start, Minimum, Maximum, Step
        JSpinner coinsSpinner = new JSpinner(coinsSpinnerModel);
    
        // Create a button to confirm payment
        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCoins = (String) coinsComboBox.getSelectedItem();
                int numberOfCoins = Integer.parseInt(selectedCoins);
                int quantity = (int) coinsSpinner.getValue();

                switch(numberOfCoins) {
                    case 1: denominations.insertOnePesoCoin(quantity); break;
                    case 5: denominations.insertFivePesoCoin(quantity); break;
                    case 10: denominations.insertTenPesoCoin(quantity); break;
                }

                int index = regularVendingView.getLastClickedSlotIndex();
                int price = (int)regularVendingModel.getSlotList().get(index).getSpecificItem().getPrice();
                int change;
                
                // Check if the amount supplied is correct
                if(price > (numberOfCoins*quantity)) {
                    JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Insufficient Payment. Please try again.", "Notice", JOptionPane.ERROR_MESSAGE);
                } else {
                    change = (numberOfCoins*quantity) - price;
                    if(change <= 0) {
                        JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Payment Received. Thank you for inserting exact amount", "Success", JOptionPane.INFORMATION_MESSAGE);
                        hasPaid = true; 
                    } else {
                        JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Payment Received. Your change is PHP " + change + "\nDispensing denominations...", "Success", JOptionPane.INFORMATION_MESSAGE);
                        hasPaid = true;
                        produceChange((double)price, (double)(numberOfCoins*quantity));
                        coinsFrame.setVisible(false);
                    }
                }
            }
        });
    
        // Create a panel to hold the components
        JPanel coinsPanel = new JPanel(new GridLayout(3, 1));
        coinsPanel.add(instructionLabel);
        coinsPanel.add(coinsComboBox);
        coinsPanel.add(new JLabel("Quantity:"));
        coinsPanel.add(coinsSpinner);
        coinsPanel.add(payButton);
    
        // Add the panel to the frame
        coinsFrame.add(coinsPanel);
    
        // Show the frame
        coinsFrame.setVisible(true);
    }
    
    private void displayBillsFrame(JFrame paymentFrame) {
        // Close the paymentFrame before displaying the bills frame
        paymentFrame.dispose();
    
        // Create a new frame for the bills
        JFrame billsFrame = new JFrame("Bills Payment");
        billsFrame.setSize(250, 150);
        billsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        billsFrame.setLocationRelativeTo(null);
    
        // Create a label to display payment instructions
        JLabel instructionLabel = new JLabel("\u20B1");
    
        // Create the choice combo box for bills
        String[] billOptions = {"20", "50", "100", "200", "500", "1000"};
        JComboBox<String> billsComboBox = new JComboBox<>(billOptions);
    
        // Create a spinner for bills quantity
        SpinnerModel billsSpinnerModel = new SpinnerNumberModel(1, 1, 1000, 1); // Start, Minimum, Maximum, Step
        JSpinner billsSpinner = new JSpinner(billsSpinnerModel);
    
        // Create a button to confirm payment
        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBills = (String) billsComboBox.getSelectedItem();
                int numberOfBills = Integer.parseInt(selectedBills);
                int quantity = (int) billsSpinner.getValue();

                switch(numberOfBills) {
                    case 20: denominations.insertOnePesoCoin(quantity); break;
                    case 50: denominations.insertFivePesoCoin(quantity); break;
                    case 100: denominations.insertOneHundredPesoBill(quantity); break;
                    case 200: denominations.insertTwoHundredPesoBill(quantity); break;
                    case 500: denominations.insertFiveHundredPesoBill(quantity); break;
                    case 1000: denominations.insertOneThousandPesoBill(quantity); break;
                }

                int index = regularVendingView.getLastClickedSlotIndex();
                int price = (int)regularVendingModel.getSlotList().get(index).getSpecificItem().getPrice();
                int change;

                if(price > (numberOfBills*quantity)) {
                    JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Insufficient Payment. Please try again.", "Notice", JOptionPane.ERROR_MESSAGE);
                } else {
                    change = (numberOfBills*quantity) - price;
                    if(change <= 0) {
                        hasPaid = true;
                        JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Payment Received. Thank you for inserting exact amount", "Success", JOptionPane.INFORMATION_MESSAGE);
                        billsFrame.setVisible(false);
                    } else {
                        hasPaid = true;
                        JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Payment Received. Your change is PHP " + change + "\nDispensing denominations...", "Success", JOptionPane.INFORMATION_MESSAGE);
                        produceChange((double)price, (double)(numberOfBills*quantity));
                        billsFrame.setVisible(false);
                    }
                }
            }
        });
    
        // Create a panel to hold the components
        JPanel billsPanel = new JPanel(new GridLayout(3, 1));
        billsPanel.add(instructionLabel);
        billsPanel.add(billsComboBox);
        billsPanel.add(new JLabel("Quantity:"));
        billsPanel.add(billsSpinner);
        billsPanel.add(payButton);
    
        // Add the panel to the frame
        billsFrame.add(billsPanel);
    
        // Show the frame
        billsFrame.setVisible(true);
    }

    /**
     * Counts the digits in the total amount of change
     * 
     * @param change        computed change
     * @return              total number of digits
     */
    private int countDigits(int change) {
        int temp;
        int count = 0;

        // set change to temp
        temp = change;
        
        // continue dividing until temp reaches zero
        while(temp != 0) {
            temp /= 10;
            count++;        // increment the count of digits
        }

        return count;
    }

    /**
     * Produces change based on the amount of payment the user gave.
     * 
     * @param price           Price of the item
     * @param payment         Inputted payment from the user
     */
    public void produceChange(double price, double payment) {
        int i;
        double digit;
        double temp = 0;
        double change;
        int digits;

        regularVendingView.getChosenItemTextArea().setText("");

        change = payment - price; 
        digits = countDigits((int)change); // count number of digits
        
        // this condition will execute if digit count is more than 1
        if(digits >= 1) { 
            // get last digit
            digit = change % 10;
            temp = digit;  

            if (digit >= 5 && digit <= 9) { 
                denominations.setFivePesoCoin(denominations.getFivePesoCount()-1); 
                regularVendingView.getChosenItemTextArea().append("Dispensing PHP 5.00...\n");
            } 

            if(digit != 0) {
                for(i = 0; i < digit; i++) {
                    denominations.setOnePesoCoin(denominations.getOnePesoCount()-1); 
                    regularVendingView.getChosenItemTextArea().append("Dispensing PHP 1.00...\n");
                }
            }
        }

        // this condition will execute if digit count is more than 2
        if(digits >= 2) {
            // get last digit
            digit = change % 100 - temp;
            temp = digit;

            if(digit >= 50 && digit <= 90) {
                denominations.setFiftyPesoBill(denominations.getFiftyPesoCount()-1); 
                digit -= 50;
                regularVendingView.getChosenItemTextArea().append("Dispensing PHP 50.00...\n");
            } else if(digit >= 20 && digit <= 40) {
                denominations.setTwentyPesoBill(denominations.getTwentyPesoCount()-1);
                digit -= 20;
                regularVendingView.getChosenItemTextArea().append("Dispensing PHP 20.00...\n");
            } 

            if(digit != 0) {
                for(i = 0; i < digit; i+=10) {
                    denominations.setTenPesoCoin(denominations.getTenPesoCount()-1);
                    regularVendingView.getChosenItemTextArea().append("Dispensing PHP 10.00...\n");
                }
            }
        }
        
        // this condition will execute if digit count is more than 3
        if(digits >= 3) {
            // get last digit
            digit = change % 1000 - temp;
            temp = digit;

            if(digit >= 500 && digit <= 900) {
                denominations.setFiveHundredPesoBill(denominations.getFiveHundredPesoCount()-1);
                digit -= 500;
                regularVendingView.getChosenItemTextArea().append("Dispensing PHP 500.00...\n");
            } else if(digit >= 200 && digit <= 400) {
                denominations.setTwoHundredPesoBill(denominations.getTwoHundredPesoCount()-1);
                digit -= 200;
                regularVendingView.getChosenItemTextArea().append("Dispensing PHP 200.00...\n");
            } 

            if(digit != 0) {
                for(i = 0; i < digit; i+=100) {
                    denominations.setOneHundredPesoBill(denominations.getOneHundredPesoCount()-1);
                    regularVendingView.getChosenItemTextArea().append("Dispensing PHP 100.00...\n");
                }
            }
        }
        
        // this condition will execute if digit count is more than 4
        if(digits >= 4) {
            // get last digit
            digit = change % 10000 - temp;
            temp = digit;

            if(digit == 1000) {
                denominations.setOneThousandPesoBill(denominations.getOneThousandPesoCount()-1);
                digit -= 1000;
                regularVendingView.getChosenItemTextArea().append("Dispensing PHP 1000.00...\n");
            }
        }
    }

    /**
     * Inserts coins into the machine and dispenses change
     */
    public void dispenseItemAction() {
        JButton dispenseButton = this.regularVendingView.getDispenseButton();
        
            // Add actionListener to dispenseButton
            dispenseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(hasPaid){
                        JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Thank you for your purchase!", "Thank You", JOptionPane.INFORMATION_MESSAGE);  
                        // reset hasPaid
                        hasPaid = false;
                    } else if(!hasPaid){
                        JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Please pay first before dispensing", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
    }

    /**
     * Displays that customization features are only available for special vending machines
     */
    public void customizeAction(){
        JButton customButton = this.regularVendingView.getCustomizeButton();
        customButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(regularVendingView.getFrame(),"Customization feature is not available for Regular Vending Machines","Notice",
                JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * Displays the MaintenanceView class upon clicking the maintenance button
     */
    public void maintenanceAction() {
        JButton maintenanceButton = this.regularVendingView.getMaintenanceButton();
        maintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regularVendingView.getFrame().setVisible(false);
                maintenanceView.getFrame().setVisible(true); // Show the maintenance view
            }
        });
    }
    
    
    /**
     * Getter for savedItemCount
     * 
     * @return          savedItemCount
     */
    public int getSavedItemCount() {
        return savedItemCount;
    }
    
    /**
     * Getter for savedItems
     * 
     * @return          savedItems
     */
    public ArrayList<String> getSavedItems() {
        return savedItems;
    }

    /**
     * Getter for slotList
     * 
     * @return          slotList
     */
    public ArrayList<Slot> getSlotList() {
        return this.regularVendingModel.getSlotList();
    }
}
    