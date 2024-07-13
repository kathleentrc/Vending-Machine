import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 	This SpecialVendingController inherits the RegularVendingController class.
 */
public class SpecialVendingController extends RegularVendingController {
    private SpecialVendingView specialVendingView;
    private SpecialVendingMachine specialVendingModel;

    /**
     * Constructs a SpecialVendingController object
     * 
     * @param specialVendingModel           Responsible for the tasks performed by the special vending machine
     * @param specialVendingView            Shows the view of the special vending machine
     * @param mainMenuView                  Shows the view of the main menu feature of the vending machine
     */
    public SpecialVendingController(SpecialVendingMachine specialVendingModel, SpecialVendingView specialVendingView, MainMenuView mainMenuView) {
        super(specialVendingModel, specialVendingView, mainMenuView);
        this.specialVendingView = specialVendingView;
        this.specialVendingModel = specialVendingModel;
    }

    /**
     * Shows a JOptionPane that displays the list of items dispensed in a combo.
     */
    @Override
    public void customizeAction(){
        // Get the customize button
        JButton customButton = this.regularVendingView.getCustomizeButton(); 
        
        // Add an action listener
        customButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regularVendingView.getTextField().setText("Dispensing Hail Caesar Salad..."); // Display the selected item name in the textField
                ArrayList<Item> itemCombo = specialVendingModel.dispenseItems("Lettuce", "Dressing", "Croutons", "Roasted Chicken", "Fresh Herbs"); // Ingredients of the salad
                String text = "Preparing " + itemCombo.get(0).getName() + "...\n" +
                              "Mixing " + itemCombo.get(1).getName() + "...\n" +
                              "Adding " + itemCombo.get(2).getName() + "...\n" +
                              "Combining " + itemCombo.get(3).getName() + "...\n"+
                              "Tossing " + itemCombo.get(4).getName() + "...\n";
                JOptionPane.showMessageDialog(regularVendingView.getFrame(), text, "Salad Combo",
                JOptionPane.INFORMATION_MESSAGE); 

                JOptionPane.showMessageDialog(regularVendingView.getFrame(), "Hail Caesar Dispensed Successfully", "Salad Combo",
                JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * Checks if an ietm is sellable or not
     */
    public void sellableChecker(){
        JCheckBox isSellable = specialVendingView.getSellableCheckBox();
        isSellable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (isSellable.isSelected()) {
                    System.out.println("Checkbox selected");
                } else {
                    System.out.println("Checkbox unselected");
                }
            }
        });
    }
}

