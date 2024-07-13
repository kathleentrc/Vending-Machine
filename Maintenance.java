/**
 * This Maintenance class is the representation of the maintenance features of the created vending machine. 
 * This class contains the item and money to be used for the maintenance features. The maintenance features 
 * include restocking or stocking specific items, setting the price of each item type, collecting the money, 
 * and replenishing the money for different denominations. 
 */
public class Maintenance {
    private Slot slot;
    private Denominations money;

    /**
     * Constructs a Maintence object for the chosen specific item
     * 
     * @param slot      Slot to be inputted and managed
     */
    public Maintenance(Slot slot){
        this.slot = slot;
    }

    /**
     * Constructs a Maintence object for the money
     * 
     * @param money     Money to be inputted and managed
     */
    public Maintenance(Denominations money) {
        this.money = money;
    }

    /**
     * Restocks the items in the slot by allowing the user to input a specific amount to add
     * 
     * @param addStock      Stock to be added, inputted by the user 
     */
    public void restockItem(int addStock){
        int i;

        for(i = 0; i < addStock; i++) {
            this.slot.addItem(addStock);
        }
    }
    
    /**
     * Collects money from the vending machine depending on the user's input. 
     * 
     * @param choice        Inputted amount by the user
     * @param quantity      Quantity of the inputted amount by the user
     * @return              Total amont of money to be collected by the user
     */
    public int collectMoney(int choice, int quantity){
        int total = 0;
        
        // multiply the peso value to the quantity to get total
        switch(choice) {
            case 1: this.money.setOnePesoCoin(this.money.getOnePesoCount() - quantity); 
                    total = 1 * quantity;
                    break; 
            case 2: this.money.setFivePesoCoin(this.money.getFivePesoCount() - quantity); 
                    total = 5 * quantity;
                    break;
            case 3: this.money.setTenPesoCoin(this.money.getTenPesoCount() - quantity); 
                    total = 10 * quantity;
                    break;
            case 4: this.money.setTwentyPesoBill(this.money.getTwentyPesoCount() - quantity); 
                    total = 20 * quantity;
                    break;
            case 5: this.money.setFiftyPesoBill(this.money.getFiftyPesoCount() - quantity); 
                    total = 50 * quantity;
                    break;
            case 6: this.money.setOneHundredPesoBill(this.money.getOneHundredPesoCount() - quantity); 
                    total = 100 * quantity;
                    break;
            case 7: this.money.setTwoHundredPesoBill(this.money.getTwoHundredPesoCount() - quantity); 
                    total = 200 * quantity;
                    break;
            case 8: this.money.setFiveHundredPesoBill(this.money.getFiveHundredPesoCount() - quantity);
                    total = 500 * quantity;
                    break;
            case 9: this.money.setOneThousandPesoBill(this.money.getOneThousandPesoCount() - quantity); 
                    total = 1000 * quantity;
                    break;
        } 
        return total;
    }

    /**
     * Replenishes money inside the vending machine
     * 
     * @param choice        Chosen money by the user
     * @param quantity      Amount of the chosen money by the user
     */
    public void replenishMoney(int choice, int quantity){
        // add the current count of peso denomination to the quantity specified to replenish
        switch(choice) {
            case 1: this.money.setOnePesoCoin(this.money.getOnePesoCount() + quantity); break; 
            case 2: this.money.setFivePesoCoin(this.money.getFivePesoCount() + quantity); break;
            case 3: this.money.setTenPesoCoin(this.money.getTenPesoCount() + quantity); break;
            case 4: this.money.setTwentyPesoBill(this.money.getTwentyPesoCount() + quantity); break;
            case 5: this.money.setFiftyPesoBill(this.money.getFiftyPesoCount() + quantity); break;
            case 6: this.money.setOneHundredPesoBill(this.money.getOneHundredPesoCount() + quantity); break;
            case 7: this.money.setTwoHundredPesoBill(this.money.getTwoHundredPesoCount() + quantity); break;
            case 8: this.money.setFiveHundredPesoBill(this.money.getFiveHundredPesoCount() + quantity);break;
            case 9: this.money.setOneThousandPesoBill(this.money.getOneThousandPesoCount() + quantity); break;
        }
    }

    /**
     * Displays the total money denominations in the machine
     */
    public void displayMoney(){
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("            TOTAL DENOMINATIONS");
        System.out.println("---------------------------------------------\n");
        System.out.println("[1] [PHP 1.00, Total = " + this.money.getOnePesoCount() +"] Qty: " + this.money.getOnePesoCount());
        System.out.println("[2] [PHP 5.00, Total = " + this.money.getFivePesoCount()*5 +"] Qty: " + this.money.getFivePesoCount());
        System.out.println("[3] [PHP 10.00, Total = " + this.money.getTenPesoCount()*10 +"] Qty: " + this.money.getTenPesoCount());
        System.out.println("[4] [PHP 20.00, Total = " + this.money.getTwentyPesoCount()*20 +"] Qty: " + this.money.getTwentyPesoCount());
        System.out.println("[5] [PHP 50.00, Total = " + this.money.getFiftyPesoCount()*50 +"] Qty: " + this.money.getFiftyPesoCount());
        System.out.println("[6] [PHP 100.00, Total = " + this.money.getOneHundredPesoCount()*100 +"] Qty: " + this.money.getOneHundredPesoCount());
        System.out.println("[7] [PHP 200.00, Total = " + this.money.getTwoHundredPesoCount()*200 +"] Qty: " + this.money.getTwoHundredPesoCount());
        System.out.println("[8] [PHP 500.00, Total = " + this.money.getFiveHundredPesoCount()*500 +"] Qty: " + this.money.getFiveHundredPesoCount());
        System.out.println("[9] [PHP 1000.00, Total = " + this.money.getOneThousandPesoCount()*1000 +"] Qty: " + this.money.getOneThousandPesoCount());
        System.out.println("---------------------------------------------");
    }

    /**
     * Sets a new price for the chosen specific item
     * 
     * @param price     New price inputted by the user
     */
    public void setNewPrice(double price){
        int i;
        int totalItems = this.slot.getItemList().size();

        for(i = 0; i < totalItems; i++) {
            this.slot.getItemList().get(i).setPrice(price);
        }
    }
}