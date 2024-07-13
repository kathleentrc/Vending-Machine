import java.util.ArrayList;
import java.util.Scanner;

/**
 * 	This VendingMachine class allows for creating slots, dispensing an item, 
 *  collecting payment, producing change, and performing maintenance
 */
public class VendingMachine {
    /**
     * Scanner for getting input
     */
    protected Scanner sc;
    /**
     * Slot list inside the vending machine
     */
    protected ArrayList<Slot> slotList;
    /**
     * Different payment denominations
     */
    protected Denominations payment;
    /**
     * Starting inventory of the vending machine
     */
    protected Inventory startingInventory;

    /**
     * Constructs a vending machine object
     */
    public VendingMachine() {
        this.slotList = new ArrayList<>();
        this.sc = new Scanner(System.in); 
        this.payment = new Denominations();
        this.startingInventory = new Inventory();
    }
    
    /**
     * Displays the starting menu of the vending machine
     */
    public void displayMenu() {
        int slots =12;
        int i;

        for (i = 0; i < slots; i++) {
            String name;
            double calories;
            double price;
            int quantity;
            int isValid = 0;

            System.out.println();
            System.out.println("[ Slot #" + (i+1) +" ]"); 
            System.out.println();

            System.out.print("Enter Salad Item: "); 
            sc.nextLine();
            name = sc.nextLine();

            System.out.print("Enter Calories: ");
            calories = sc.nextDouble();

            System.out.print("Enter Price: ");
            price = sc.nextDouble();
            
            do {
                System.out.print("Enter Quantity: ");
                quantity = sc.nextInt();

                if(quantity < 10) 
                    System.out.println("\nError: The slot should have at least 10 items. Try Again.\n");
                else if(quantity > 20)
                    System.out.println("\nError: The slot should have at most 20 items. Try Again.\n");
                else
                    isValid = 1;

            } while (isValid == 0);
            
                Item item = new Item(name, calories, price, quantity); 
                Slot slot = new Slot(i+1, item); 
                
                slotList.add(slot); 
                slotList.get(i).addItem(quantity);

                startingInventory.updateStartingInventory(slot);
                System.out.println("\nItems are successfully added!"); 
            } 

            System.out.println();
            System.out.println("[ " + slots + " slots are successfully added! ]\n");
            System.out.println("---------------------------------------------");
            System.out.println("                SLOT LIST");
            System.out.println("---------------------------------------------\n");
            displaySlots();
        }

    /**
     * Displays a list of slots
     */
    public void displaySlots() {
        for (Slot slot : slotList) {
            System.out.println();
            System.out.println("Slot Number: " + slot.getSlotNumber());
            System.out.println("Item Name: " + slot.getSpecificItem().getName());
            System.out.println("Calories: " + slot.getSpecificItem().getCalories());
            System.out.println("Price: " + slot.getSpecificItem().getPrice());
            System.out.println("Quantity: " + slot.getItemList().size());
            System.out.println();
        }
    }
   
    /**
     * Allows the user to test the vending machine's vending feature or maintenance feature
     */
    public void testVendingMachine() {
        MainMenu mainMenu = new MainMenu();
        int input;

        System.out.println();
        System.out.println("=============================================");
        System.out.println("            TEST VENDING MACHINE");
        System.out.println("=============================================\n");
        System.out.println("[1] Vending Features");
        System.out.println("[2] Maintenance Features");
        System.out.println("[3] Exit\n");
        System.out.print("Input Choice: ");

        input = getUserInput(1, 3);

        switch (input) {
            case 1:
                testVendingFeatures();
                break;
            case 2:
                testMaintenanceFeatures();
                break;
            case 3:
                System.out.println("\nRedirecting to Main Menu...\n");
                mainMenu.displayMainMenu();
                break;
        }
    }

    /**
     * Displays the different denominations to choose from
     * 
     * @return input        Chosen denomination by the user
     */
    public int displayDenominations() {
        int input;
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("[1] 1 PHP");
        System.out.println("[2] 5 PHP");
        System.out.println("[3] 10 PHP");
        System.out.println("[4] 20 PHP");
        System.out.println("[5] 50 PHP");
        System.out.println("[6] 100 PHP");
        System.out.println("[7] 200 PHP");
        System.out.println("[8] 500 PHP");
        System.out.println("[9] 1000 PHP");
        System.out.println("---------------------------------------------");
        System.out.print("\nInsert Choice: ");
        input = getUserInput(1, 9);
        return input;
    }

    /**
     * Adds a slot to a slot list
     * 
     * @param slot              Slot object to be added to the slot list
     * @return value            True if add slot is valid and false if invalid 
     */
    public boolean addSlot(Slot slot) {
        boolean value = false;

        if(slotList.size() <= 12) {     // 12 is the slot limit
            slotList.add(slot);
            value = true;
        }

        return value;
    }

    /**
     * Inserts payment in the machine
     * 
     * @return total            Total amount of payment inserted, otherwise -1 if invalid
     */
    public int insertPayment() {
        Denominations change = new Denominations();
        int input;
        int quantity;
        int total = 0;
        int insert = 1; // 1 - insert again ; 2 - done

        if(!change.isEnoughChange()) {
            System.out.println("\nError: Not enough change on vending machine. Sorry for the inconvenience.\n");
            System.out.println("Cancelling Transaction...\n");
            System.out.println("Redirecting to main menu...\n");
            
            return -1;
        }

        while(insert == 1) {
                
            input = displayDenominations(); 
            System.out.print("Enter Quantity: ");
            quantity = sc.nextInt();
                
            switch(input) {
                case 1: total += this.payment.insertOnePesoCoin(quantity); break;
                case 2: total += this.payment.insertFivePesoCoin(quantity); break;
                case 3: total += this.payment.insertTenPesoCoin(quantity); break;
                case 4: total += this.payment.insertTwentyPesoBill(quantity); break;
                case 5: total += this.payment.insertFiftyPesoBill(quantity); break;
                case 6: total += this.payment.insertOneHundredPesoBill(quantity); break;
                case 7: total += this.payment.insertTwoHundredPesoBill(quantity); break;
                case 8: total += this.payment.insertFiveHundredPesoBill(quantity); break;
                case 9: total += this.payment.insertOneThousandPesoBill(quantity); break;
            }

            System.out.println();
            System.out.println("[1] Insert Again");
            System.out.println("[2] Done");
            System.out.print("\nInput Choice: ");
            insert = getUserInput(1, 2);
        }

        return total;   // return total amount

    }

    /**
     * Dispenses an item from the vending machine
     * 
     * @param slotNumber            Slot number of the item to be dispensed
     * @return                      Specifictem to be dispensed
     */
    public Item dispenseItem(int slotNumber) {
        Item specificItem = slotList.get(slotNumber-1).getSpecificItem();

        // dispense item
        slotList.get(slotNumber-1).removeItem();
        slotList.get(slotNumber-1).addSales();

        //slotList.get(slotNumber-1).getSpecificItem().setQuantity(specificItem.getQuantity()-1);
        
        return specificItem;
    }

    /**
     * Displays the dispensed item
     *
     * @param item           Item to display
     */
    public void displayDispensedItem(Item item) {
        System.out.println("Dispensing Item: " + item.getName() + "...");
        System.out.println();
        System.out.println("[ Item dispensed successfully! ]");
    }

    /**
     * Gets the slot list
     * 
     * @return               List of slots inside the vending machine
     */
    public ArrayList<Slot> getSlotList() {
        return this.slotList;
    }

    /**
     * Displays the starting inventory
     */
    public void displayStartingInventory() {
        int i;
        System.out.println("\n---------------------------------------------");
        System.out.println("               STARTING INVENTORY");
        System.out.println("---------------------------------------------\n");

        for(i = 0; i < startingInventory.getStartingInventory().size(); i++) {
            System.out.println("[ Slot #" + (i+1) +" ]");
            System.out.println();
            System.out.println("Item Name: " + startingInventory.getStartingInventory().get(i).getSpecificItem().getName());
            System.out.println("Price: " + startingInventory.getStartingInventory().get(i).getSpecificItem().getPrice());
            System.out.println("Quantity: " + startingInventory.getStartingInventory().get(i).getItemList().size());
            System.out.println();
        }
    }

    /**
     * Displays the ending inventory
     */
    public void displayEndingInventory() {
        int i;

        System.out.println("\n---------------------------------------------");
        System.out.println("               ENDING INVENTORY");
        System.out.println("---------------------------------------------\n");

        for(i = 0; i < slotList.size(); i++) {
            System.out.println("[ Slot #" + slotList.get(i).getSlotNumber() +" ]");
            System.out.println();
            System.out.println("Item Name: " + slotList.get(i).getSpecificItem().getName());
            System.out.println("Price: " + slotList.get(i).getSpecificItem().getPrice());
            System.out.println("Quantity : " + slotList.get(i).getItemList().size());
            System.out.println();
        }
    }

    /**
     * Prints a summary of transactions
     */
    public void printSummaryOfTransactions() {
        int i;
        double totalEarnings = 0;

        System.out.println("\n---------------------------------------------");
        System.out.print("            SUMMARY OF TRANSACTIONS\n");
        System.out.println("---------------------------------------------\n");
        
        for(i = 0; i < slotList.size(); i++) {
            System.out.println("Item Name : " + slotList.get(i).getSpecificItem().getName());
            System.out.println("Quantity of Item Sold : " + slotList.get(i).getSales());
            System.out.println("Total Sales : PHP " + slotList.get(i).getSales() * slotList.get(i).getSpecificItem().getPrice());
            System.out.println();
            totalEarnings += slotList.get(i).getSales() * slotList.get(i).getSpecificItem().getPrice();
        }

        System.out.println("Total Earnings: PHP " + totalEarnings);
    }

    /**
     * Gets the payment 
     * 
     * @return                 Payment represented in denominations
     */
    public Denominations getPayment() {
        return this.payment;
    }

    /**
     * Gets user input given a maximum and minimum range
     * 
     * @param min               Minimum value limit
     * @param max               Maximum value limit
     * @return                  Input of user
     */
    protected int getUserInput(int min, int max) {
        int input;

        while (true) {
            input = sc.nextInt();

            if (input >= min && input <= max) {
                break;
            }

            System.out.println("\nInvalid Input! Please try again.\n");
            System.out.print("Input Choice: ");
        }

        return input;
    }
    
    /**
     * Tests the vending features of the machine
     */
    public void testVendingFeatures() {
        int i;
        int payment = 0;
        int input;
        int itemChoice;
        double price;
        Item dispense;

        System.out.println();
        System.out.println("=============================================");
        System.out.println("\t\tW E L C O M E");
        System.out.println("\t\t    T O");
        System.out.println(" \tG R E E N S  &  G R A I N S !");
        System.out.println("---------------------------------------------");
        System.out.println("         - A Salad Vending Machine -");
        System.out.println("=============================================");

        for(i = 0; i < slotList.size(); i++) {
            System.out.println();
            System.out.println("[ " + (i+1) + " ] " + slotList.get(i).getSpecificItem().getName());

            if(slotList.get(i).getItemList().size() > 0) {
                System.out.println("Stock: " + slotList.get(i).getItemList().size()); 
                System.out.println("Calories: " + slotList.get(i).getSpecificItem().getCalories());
                System.out.println("Price: PHP " + slotList.get(i).getSpecificItem().getPrice() + '0');
            } else 
                System.out.println("OUT OF STOCK");
            

            System.out.println();
        } 
        System.out.print("Choose item to dispense: ");
        itemChoice = getUserInput(1, slotList.size()); 
        price = slotList.get(itemChoice-1).getSpecificItem().getPrice();

        displayPaymentOptions(price);

        input = getUserInput(1, 3);
        System.out.println();

        switch (input) {
            case 1: 
                System.out.println("Please pay PHP " + price);

                do {
                    payment += insertPayment();

                    if(payment == -1)
                        testVendingMachine();

                    if(payment < price)
                        System.out.println("\nError: Insufficient Payment. Please pay the remaining price of PHP " + (price-payment));
                        
                } while (payment < price);

                getPayment().produceChange(price, (double)payment);
                dispense = dispenseItem(itemChoice);
                displayDispensedItem(dispense);
                break; 
            case 2:
                System.out.println("Cancelling Transaction...\n");
                testVendingFeatures();
                break;
            case 3:
                System.out.println("\nRedirecting to Main Menu...\n");
                testVendingMachine();
                break;
        }

        System.out.println();
        System.out.println("[1] Dispense Again");
        System.out.println("[2] Exit");
        System.out.print("\nInput Choice: ");
        input = getUserInput(1, 2);

        if(input == 1) {
            System.out.println();
            testVendingFeatures();
         }else {
            testVendingMachine();
        }
    }
    
    /**
     * Displays the different payment options of the machine
     * 
     * @param price             Price of the item
     */
    public void displayPaymentOptions(double price) {
        System.out.println();
        System.out.println("---------------------------------------------\n");
        System.out.println("The total amount is PHP " + price);
        System.out.println();
        System.out.println("[1] Proceed with Transaction");
        System.out.println("[2] Cancel Transaction"); 
        System.out.println("[3] Exit Menu"); 
        System.out.print("\nInput Choice: ");
    }

    /**
     * Tests the maintenance features of the machine
     */
    public void testMaintenanceFeatures() {
        int input;
        int quantity;
        int total = 0;
        int collectAgain = 0;
        Item item;
        Maintenance itemMaintenance;
        Maintenance itemPriceMaintenance;
        Maintenance moneyMaintenance;
        Maintenance replenishMoneyMaintenance;
        int currentStock = 0;
        int remainingStocks;
        int newStock;
        double price;
        int index = 0;

        System.out.println();
        System.out.println("============================================");
        System.out.println("             MAINTENANCE FEATURES");
        System.out.println("============================================\n");
        System.out.println("[1] Restock Item");
        System.out.println("[2] Set Price"); 
        System.out.println("[3] Collect Money"); 
        System.out.println("[4] Replenish Money");
        System.out.println("[5] View Starting Inventory");
        System.out.println("[6] View Ending Inventory");
        System.out.println("[7] Print Summary of Transactions");
        System.out.println("[8] Exit Menu");
        System.out.print("\nInput Choice: ");

        input = getUserInput(1, 8);

        switch (input) {
            case 1: 
                System.out.println("---------------------------------------------");
                System.out.println("              CURRENT SLOT LIST");
                item = getItem();
                
                for(int i = 0; i < this.slotList.size(); i++) {
                    if(item.getName().compareTo(slotList.get(i).getSpecificItem().getName()) == 0) {
                        currentStock = slotList.get(i).getItemList().size();
                        index = i;
                    }
                        
                }

                itemMaintenance = new Maintenance(slotList.get(index));
                remainingStocks = 20 - currentStock;

                if(remainingStocks == 0)
                    System.out.println("\nThe stocks of this item is already at its maximum capacity.\n");
                else {
                    do {
                        System.out.print("Enter the amount of stocks to add: ");
                        quantity = sc.nextInt(); 
                        newStock = currentStock + quantity;
                        
                        if(newStock > 20)
                            System.out.println("\nThis will exceed the maximum stock of 20. Please try again.\n");
                        
                    } while(newStock > 20);

                    itemMaintenance.restockItem(quantity); 
                    System.out.println();
                    System.out.println("[ " + quantity + "pcs of " + item.getName() + " are successfully added! ]");
                    System.out.println();
                    System.out.println("---------------------------------------------");
                    System.out.println("              UPDATED SLOT LIST");
                    System.out.println("---------------------------------------------");
                    displaySlots();
                }
                break; 
            case 2:
                System.out.println("\n---------------------------------------------");
                System.out.println("              SET NEW PRICE");

                item = getItem();

                for(int i = 0; i < this.slotList.size(); i++) {
                    if(item.getName().compareTo(slotList.get(i).getSpecificItem().getName()) == 0) {
                        index = i;
                    }
                }
                itemPriceMaintenance = new Maintenance(slotList.get(index));

                System.out.print("Input new price: ");
                price = sc.nextDouble();
                itemPriceMaintenance.setNewPrice(price);

                System.out.println();
                System.out.println("[ Price of " + item.getName() + " is successfully updated to PHP " + item.getPrice() + " ]");
                System.out.println();
                System.out.println("---------------------------------------------");
                System.out.println("             UPDATED NEW PRICE");
                System.out.println("---------------------------------------------");

                displaySlots();

                break;
           case 3:
                moneyMaintenance = new Maintenance(getPayment());
                moneyMaintenance.displayMoney();

                do{
                    System.out.print("\nEnter choice: ");
                    input = sc.nextInt();
                    System.out.print("Enter quantity: ");
                    quantity = sc.nextInt();
                    total += moneyMaintenance.collectMoney(input, quantity);
                    moneyMaintenance.displayMoney();
                    System.out.println("\nDo you want to collect more money?\n");
                    System.out.println("[1] Yes");
                    System.out.println("[2] No");
                    System.out.print("\nInput choice: ");
                    collectAgain = getUserInput(1, 2);
                } while(collectAgain == 1);

                System.out.println("\nTotal Collected Money: PHP " + total);
                
                break;
            case 4:
                replenishMoneyMaintenance = new Maintenance(getPayment());

                System.out.println("\n---------------------------------------------");
                System.out.print("              REPLENISH MONEY");

                input = displayDenominations();

                System.out.print("Enter Quantity: ");
                quantity = sc.nextInt();

                replenishMoneyMaintenance.replenishMoney(input, quantity);
                System.out.println("\n[ Inserted amount is successfully replenished! ]");
                replenishMoneyMaintenance.displayMoney();
                break;
            case 5: 
                System.out.println();
                displayStartingInventory(); 
                break;
            case 6:
                System.out.println();
                displayEndingInventory();
                break;
            case 7: 
                System.out.println();
                printSummaryOfTransactions();
                break;
            case 8:
                System.out.println("\nRedirecting to Main Menu...\n");
                testVendingMachine();
                break;
        }
        
        testMaintenanceFeatures();
    }

    /**
     * Gets the Item inside a slot list
     * 
     * @return          The retrieved item from the slot list
     */
    private Item getItem() {
        int input;
        int i;
        for (i = 0; i < slotList.size(); i++) {
            System.out.println("---------------------------------------------");
            System.out.println();
            System.out.println("Slot Number: " + slotList.get(i).getSlotNumber());
            System.out.println("Item Name: " + slotList.get(i).getSpecificItem().getName());
            System.out.println("Calories: " + slotList.get(i).getSpecificItem().getCalories());
            System.out.println("Price: " + slotList.get(i).getSpecificItem().getPrice());
            System.out.println("Quantity: " + slotList.get(i).getItemList().size());
            System.out.println();
        }

        System.out.println("---------------------------------------------");
        System.out.print("Enter slot number: ");
        input = sc.nextInt();

        return slotList.get((input-1)).getSpecificItem();
    }

}