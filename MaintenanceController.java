import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 	This MaintenanceController implements the ActionListener interface
 */
public class MaintenanceController implements ActionListener {
    private MaintenanceView maintenanceView;
    private VendingMachine vendingMachineModel;
    private MainMenuView mainMenuView;

    /**
     * 	Constructs a MaintenanceController object
     * 
     * @param maintenanceView           Shows the view of the maintenance feature
     * @param vendingMachineModel       Responsible for the tasks performed by the maintenance feature
     * @param mainMenuView              Shows the view of the main menu feature
     */
    public MaintenanceController(MaintenanceView maintenanceView, VendingMachine vendingMachineModel, MainMenuView mainMenuView) {
        this.maintenanceView = maintenanceView;
        this.vendingMachineModel = vendingMachineModel;
        this.mainMenuView = mainMenuView;
    }

    /**
     * Performs actions based on the chosen button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = e.getActionCommand();

        // Shows the frame depending on which button is clicked
        if (buttonName.equals("Restock Item")) {
            maintenanceView.getRestockFrame();
            String selectedItem = maintenanceView.getSlotComboBox().getSelectedItem().toString();

            if(searchItem(selectedItem) != null) { // search item
                Slot slot = searchItem(selectedItem);
                Maintenance itemMaintenance = new Maintenance(slot);
                int quantity = (int)maintenanceView.getQuantitySpinner().getValue();

                itemMaintenance.restockItem(quantity); // restock item

                JOptionPane.showMessageDialog(maintenanceView.getFrame(), quantity + " stocks of " + selectedItem +" are added succesfully.", "Restock Item",
                JOptionPane.INFORMATION_MESSAGE); 
            } 
        } else if (buttonName.equals("Set Price")) {
            maintenanceView.getSetPriceFrame();
        } else if (buttonName.equals("Collect Money")) {
            maintenanceView.getCollectMoneyFrame();
        } else if (buttonName.equals("Replenish Money")) {
            maintenanceView.getReplenishMoneyFrame();
        } else if (buttonName.equals("Starting Inventory")) {
            maintenanceView.getStartingInventoryFrame();
        } else if (buttonName.equals("Ending Inventory")) {
            maintenanceView.getEndingInventoryFrame();
        } else if (buttonName.equals("Transactions")) {
            maintenanceView.getTransactionsFrame();
        } else if (buttonName.equals("Exit")) {
            maintenanceView.getFrame().setVisible(false); // Hides the maintenance frame
            mainMenuView.getFrame().setVisible(true);     // Redirects user to the main menu
        }
    }

    /**
     * Searches for the slot where a specific item is stored
     * 
     * @param nameToFind        name to be found
     * @return                  slot of item searched
     */
    public Slot searchItem(String nameToFind) {
        int i;
        boolean isFound = false;
        Slot slot = null;

        for(i = 0; i < vendingMachineModel.getSlotList().size() && !isFound; i++) {
            String itemName = vendingMachineModel.getSlotList().get(i).getSpecificItem().getName();
            if(itemName.compareTo(nameToFind) == 0) {
                isFound = true;
                slot = vendingMachineModel.getSlotList().get(i); // get slot where the item is stored
            }
        }

        return slot;
    }

}
