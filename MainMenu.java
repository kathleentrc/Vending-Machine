import java.util.Scanner;

/**
 * 	This MainMenu class displays and executes the features of a vending machine
 */
public class MainMenu {
    private Scanner sc;
    private VendingMachine vendingMachine;
    private SpecialVendingMachine specialVendingMachine;

    /**
     * Constructs a main menu object of the vending machine, which is set to null
     */
    public MainMenu() {
        this.sc = new Scanner(System.in);
        this.vendingMachine = new VendingMachine();
        this.specialVendingMachine = new SpecialVendingMachine();
    }

    /**
     * Displays the main menu
     */
    public void displayMainMenu() {
        int input;

        System.out.println();
        System.out.println("=============================================");
        System.out.println("\tV E N  D I N G   M A C H I N E");
        System.out.println("---------------------------------------------");
        System.out.println("             Factory Simulator");
        System.out.println("=============================================");
        System.out.println();
        System.out.println("[1] Create a Regular Vending Machine");
        System.out.println("[2] Create a Special Vending Machine");
        System.out.println("[3] Exit \n");
        System.out.print("Input Choice: ");

        input = getUserInput(1, 3);

        switch (input) {
            case 1:
                createRegularVendingMachine();
                break;
            case 2:
                createSpecialVendingMachine();
                break;
            case 3:
                System.out.println("\nExiting program...\n");
                System.exit(0);
                break;
        }
    }

    /**
     * Creates a Regular Vending Machine
     */
    public void createRegularVendingMachine() {
        System.out.println("\nRegular Vending Machine is successfully created!");
        vendingMachine.displayMenu();
        vendingMachine.testVendingMachine();
    }

    /**
     * Creates a Special Vending Machine
     */
    public void createSpecialVendingMachine() {
        System.out.println("\nSpecial Vending Machine is successfully created!");
        specialVendingMachine.displayMenu();
        specialVendingMachine.testVendingMachine();
    }
 
 
    /**
     * Gets and validates the user input by checking if the input is among the choices
     * 
     * @param min        Minimum value to be inputted
     * @param max        Maximum value to be inputted
     * @return           Valid Input
     */
    private int getUserInput(int min, int max) {
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
}
