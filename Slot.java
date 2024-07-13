import java.util.ArrayList;

/**
 * This Slot class represents the slots the vending machine has that is mapped for specific item. 
 * It is composed of the specific item, its slot number, total sales, and the list of items.
 */
public class Slot {
    private int slotNumber;
    private Item specificItem; // Name of the slot
    private ArrayList<Item> itemList;
    private int sales;

    /**
     * Constructs a Slot object for the specific item with its slot number
     * 
     * @param slotNumber        Slot number of the specific item
     * @param specificItem      Specific item inside the vending machine slot
     */
    public Slot(int slotNumber, Item specificItem) {
        this.slotNumber = slotNumber;
        this.specificItem = specificItem;
        this.itemList = new ArrayList<>();
    }

    /**
     * Adds a specific item inside a slot 
     * 
     * @param quantity      Amount of item to be added in the slot
     * @return              True if the item is successfully added, otherwise false.
     */
    public boolean addItem(int quantity) {
        int i;
        boolean value = false;

        for(i = 0; i < quantity; i++)
        {
            itemList.add(this.specificItem);
            value = true;
        }
            
        return value;
    }

    /**
     * Removes a specific item from the slot
     * 
     * @return          True if the item is successfully removed, otherwise false. 
     */
    public boolean removeItem() {
        int recentIndex = getItemList().size() - 1;
        boolean value = false;
        
        if(!itemList.isEmpty())
        {
            itemList.remove(recentIndex); 
            value = true;
        }
        
        return value;
    }

    /**
     * Display the items inside the slot
     */
    public void displayItems() {
        for(int i = 0; i < this.itemList.size(); i++)
        {
            System.out.println("Item #" + (i+1));
            System.out.println("Name : " + itemList.get(i).getName());
            System.out.println("Price : " + itemList.get(i).getPrice());
            System.out.println("Calories : " + itemList.get(i).getCalories());
            System.out.println("Quantity : " + itemList.size());
            System.out.println();
        }
    }

    /**
     *  Updates the sales whenever an item is successfully dispensed
     * 
     * @return      Added sales
     */
    public int addSales() {
        return this.sales++;
    }

    /**
     * Gets the slot number of the specific item
     * 
     * @return       Slot number
     */
    public int getSlotNumber() {
        return this.slotNumber;
    }

    /**
     * Gets the specific item allocated for each slot number
     * 
     * @return      Specific item
     */
    public Item getSpecificItem() {
        return this.specificItem;
    }

    /**
     * Get the list of items inside the slot
     * 
     * @return      List of items
     */
    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    /**
     * Get the total sales the vending machine has made
     * 
     * @return      Total sales
     */
    public int getSales() {
        return this.sales;
    }
}
