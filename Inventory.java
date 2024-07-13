import java.util.ArrayList;

/**
 * 	This Inventory class handles and shows the inventory of the vending machine in the program 
 */
public class Inventory {
    private final ArrayList<Slot> startingInventory;

    /**
     * Constructs an Inventory class
     */
    public Inventory() {
        this.startingInventory = new ArrayList<>();
    }

    /**
     * Updates the starting inventory by
     * 
     * @param slot          designated slot of the item
     */
    public void updateStartingInventory(Slot slot) {
        this.startingInventory.add(slot);
    }

    /**
     * Gets the starting inventory of the vending machine
     * 
     * @return  List of items the vending machine has from its creation
     */
    public ArrayList<Slot> getStartingInventory() {
        return this.startingInventory;
    }

}