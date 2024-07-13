import java.util.ArrayList;

/**
 * 	This SpecialVendingMachine class inherits the Regular VendingMachine class.
 *  It allows for creating slots, dispensing an item, collecting payment, producing change, 
 *  and performing maintenance. This class also allows dispensing customized items.
 */
public class SpecialVendingMachine extends VendingMachine {
    private ArrayList<Item> itemCombo;

    /**
     * Constructs a special vending machine object
     */
    public SpecialVendingMachine() {
        super();
        this.itemCombo = new ArrayList<>();
    }

    /**
     * Overrides the displayMenu method from the superclass and it modifies it
     * such that the item can be dispensed alone or not.
     */
    @Override
    public void displayMenu() {
        int slots;
        int i;

        System.out.print("\nEnter the number of slots for the items: ");
        slots = sc.nextInt();

        if (slots > 12) {
            System.out.println();
            System.out.println("Error: The machine should have at most 12 slots. Try Again.");
            displayMenu();
            return;
        }

        if (slots < 8) { 
            System.out.println();
            System.out.println("Error: The machine should have at least 8 slots. Try Again.");
            displayMenu();
            return;
        }

        for (i = 0; i < slots; i++) {
            String name;
            double calories;
            double price;
            int quantity;
            int isValid = 0;
            int isSellable;

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

            System.out.println("Can be sold alone?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");
            System.out.print("Input: ");
            isSellable = sc.nextInt();
            
            Item item = new Item(name, calories, price, quantity);
            
            if(isSellable == 1) {          // isSellable means the item can be sold alone
                item.setIsSellable(true);  // can be sold alone
            } else if(isSellable == 2) {
                item.setIsSellable(false); // cannot be sold alone
            }
                
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
        super.displaySlots();
    }

    /**
     * Tests the vending features of the machine
     */
    @Override
    public void testVendingFeatures() {
        int i;
        int payment = 0;
        int input;
        int itemChoice;
        double price;
        Item dispense;
        ArrayList<Item> itemCombo = new ArrayList<>();

        System.out.println();
        System.out.println("=============================================");
        System.out.println("\t\tW E L C O M E");
        System.out.println("\t\t    T O");
        System.out.println(" \tG R E E N S  &  G R A I N S !");
        System.out.println("---------------------------------------------");
        System.out.println("         - A Salad Vending Machine -");
        System.out.println("=============================================");

        System.out.println("[1] Dispense An Item");
        System.out.println("[2] Dispense a Combo");
        
        input = super.getUserInput(1,2);
        System.out.println();

        if(input == 1) {    // Dispense an item

            do{
                System.out.println();

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

                // check if item is dispensable as an individual item
                if(slotList.get(itemChoice-1).getSpecificItem().getIsSellable() == false)
                    System.out.println("This item cannot be dispensed alone. Please choose another item");
            
            } while(slotList.get(itemChoice-1).getSpecificItem().getIsSellable() == false);
            
            // get price of item
            price = slotList.get(itemChoice-1).getSpecificItem().getPrice();

            super.displayPaymentOptions(price);

            input = super.getUserInput(1, 3);
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

            // Redirect
            if(input == 1) {
                System.out.println();
                testVendingFeatures();
            } else {
                super.testVendingMachine();
            }

        } else if (input == 2) {    // Dispense a combo 
            price = 0;

            // Combo choices
            System.out.println("[1] Hail Caesar Salad");
            System.out.println("[2] Greek Salad");
            System.out.println("[3] Nicoise Salad");
            System.out.println("[4] Cobb Salad");
            System.out.println("[5] Chicken Salad");
            System.out.println("Input: ");
            itemChoice = super.getUserInput(1, 5); 
            
            // Items in a combo
            switch(itemChoice) {
                case 1: itemCombo = dispenseItems("Lettuce", "Dressing", "Croutons", "Roasted Chicken", "Fresh Herbs"); break;
                case 2: itemCombo = dispenseItems("Lettuce", "Dressing", "Boiled Egg", "Tomato", "Grapes"); break;
                case 3: itemCombo = dispenseItems("Lettuce", "Dressing", "Cucumber", "Roasted Chicken", "Spinach"); break;
                case 4: itemCombo = dispenseItems("Lettuce", "Dressing", "Crushed Peanuts", "Tomato", "Grapes"); break;
                case 5: itemCombo = dispenseItems("Lettuce", "Dressing", "Fresh Herbs", "Spinach", "Roasted Chicken"); break;
            }

            // Get the price for each item in the chosen combo
            for(i = 0; i < itemCombo.size(); i++) {
                price += itemCombo.get(i).getPrice();
            }

            super.displayPaymentOptions(price);

            input = super.getUserInput(1, 3);
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
                    displayDispensedItems(itemCombo);
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

            // Ask the user again for input
            System.out.println();
            System.out.println("[1] Dispense Again");
            System.out.println("[2] Exit");
            System.out.print("\nInput Choice: ");
            input = getUserInput(1, 2);

            // Redirect
            if(input == 1) {
                System.out.println();
                testVendingFeatures();
            } else {
                testVendingMachine();
            }
        }
    }

    /**
     * Dispenses the list of items in a combo
     * 
     * @param item1   First ingredient in the combo
     * @param item2   Second ingredient in the combo
     * @param item3   Third ingredient in the combo
     * @param item4   Fourth ingredient in the combo
     * @param item5   Fifth ingredient in the combo
     * @return        List of items in the combo
     */
    public ArrayList<Item> dispenseItems(String item1, String item2, String item3, String item4, String item5) {
        clearItemCombo(); // current itemCombo list is cleared each time a user chooses to dispense a combo
        int i;

        // check if the chosen item matches with the item in the slot list,
        // if it matches, dispense the specific item and add it to the list of combos
        for(i = 0; i < slotList.size(); i++) {
            // Dispense each ingredient
            if(slotList.get(i).getSpecificItem().getName().compareTo(item1) == 0) {
                itemCombo.add(slotList.get(i).getSpecificItem());
                super.dispenseItem(i+1);
            }

            if(slotList.get(i).getSpecificItem().getName().compareTo(item2) == 0) {
                itemCombo.add(slotList.get(i).getSpecificItem());
                super.dispenseItem(i+1);
            }

            if(slotList.get(i).getSpecificItem().getName().compareTo(item3) == 0) {
                itemCombo.add(slotList.get(i).getSpecificItem());
                super.dispenseItem(i+1);
            }

            if(slotList.get(i).getSpecificItem().getName().compareTo(item4) == 0) {
                itemCombo.add(slotList.get(i).getSpecificItem());
                super.dispenseItem(i+1);
            }

            if(slotList.get(i).getSpecificItem().getName().compareTo(item5) == 0) {
                itemCombo.add(slotList.get(i).getSpecificItem());
                super.dispenseItem(i+1);
            }
        }

        return itemCombo;
    }

    /**
     * Displays the preparation of the customized item
     * 
     * @param comboList     combo products that consists a ist of items
     */
    public void displayDispensedItems(ArrayList<Item> comboList) {
        System.out.println("Preparing " + comboList.get(0).getName() + "...");
        System.out.println("Mixing " + comboList.get(1).getName() + "...");
        System.out.println("Adding " + comboList.get(2).getName() + "...");
        System.out.println("Combining " + comboList.get(3).getName() + "...");
        System.out.println("Tossing " + comboList.get(4).getName() + "...");
    }
                 
    /**
     * Removes all items in a combo list
     */
    public void clearItemCombo() {
        this.itemCombo.clear();     // this is used each time the user chooses to dispense
                                    // a new combo
    }
}
