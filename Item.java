/**
 * 	The item class represents a product inside a vending machine. It is composed of attributes
 *  such as name, calories, price, and quantity
 */
public class Item {
    private final String name;
    private double calories;
    private double price;
    private boolean isSellable;
    private int quantity;
    
    /**
     * Constructs an item class that takes in a name, calories, price, and quantity
     * 
     * @param name                  Name of the item
     * @param calories              Total number of calories
     * @param price                 Price of the item
     * @param quantity              Total quantity of the item 
     */
    public Item(String name, double calories, double price, int quantity) {
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.quantity = quantity;
        this.isSellable = false;    // the item is not sellable on its own by default
    }

    /**
     * Gets the name of the item
     * 
     * @return             Name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the total number of calories in an item
     * 
     * @return           Total number of calories 
     */
    public double getCalories() {
        return this.calories;
    }

    /**
     * Gets the total price of an item
     * 
     * @return          Price of an item
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Gets the sellable attribute of the item
     * 
     * @return          Price of an item
     */
    public boolean getIsSellable() {
        return this.isSellable;
    }

    /**
     * Gets the quantity attribute of the item
     * 
     * @return          Initial quantity of a specific item upon creation
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Sets a new price for an item
     * 
     * @param price         New price of the item
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Sets a new price for an item
     * 
     * @param status        New price of the item
     */
    public void setIsSellable(boolean status) {
        this.isSellable = status;
    }
}
