/**
 * 	The Denominations class is a representation of the different coins and bills to be
 *  inserted and dispensed in the vending machine. 
 */
public class Denominations {
    private int onePesoCount;
    private int fivePesoCount;
    private int tenPesoCount;
    private int twentyPesoCount;
    private int fiftyPesoCount;
    private int oneHundredPesoCount;
    private int twoHundredPesoCount;
    private int fiveHundredPesoCount;
    private int oneThousandPesoCount;

    /**
     * Constructs a Denominations object with each quantity initially set to 50
     */
    public Denominations() {
        this.onePesoCount = 50;
        this.fivePesoCount = 50;
        this.tenPesoCount = 50;
        this.twentyPesoCount = 50;
        this.fiftyPesoCount = 50;
        this.oneHundredPesoCount = 50;
        this.twoHundredPesoCount = 50;
        this.fiveHundredPesoCount = 50;
        this.oneThousandPesoCount = 50;
    }
    
    /**
     * Inserts a specified number of one peso coin(s)
     * 
     * @param quantity      Amount of coin(s) to be inserted
     * @return              Total amount of inserted one peso coin(s)
     */
    public int insertOnePesoCoin(int quantity) {
        this.onePesoCount += quantity;
        return quantity; 
    }

    /**
     * Inserts a specified number of five peso coin(s)
     * 
     * @param quantity      Amount of coin(s) to be inserted
     * @return              Total amount of inserted five peso coin(s)
     */
    public int insertFivePesoCoin(int quantity) {
        this.fivePesoCount += quantity;
        return 5 * quantity;
    }

    /**
     * Inserts a specified number of ten peso coin(s)
     * 
     * @param quantity      Amount of coin(s) to be inserted
     * @return              Total amount of inserted ten peso coin(s)
     */
    public int insertTenPesoCoin(int quantity) {
        this.tenPesoCount += quantity;
        return 10 * quantity;
    }

    /**
     * Inserts a specified number of twenty peso bill(s)
     * 
     * @param quantity      Amount of bill(s) to be inserted
     * @return              Total amount of inserted twenty peso bill(s)
     */
    public int insertTwentyPesoBill(int quantity) {
        this.twentyPesoCount += quantity;
        return 20 * quantity; 
    }

    /**
     * Inserts a specified number of fifty peso bill(s)
     * 
     * @param quantity      Amount of bill(s) to be inserted
     * @return              Total amount of inserted fifty peso bill(s)
     */
    public int insertFiftyPesoBill(int quantity) {
        this.fiftyPesoCount += quantity;
        return 50 * quantity;
    }

    /**
     * Inserts a specified number of one hundred peso bill(s)
     * 
     * @param quantity      Amount of bill(s) to be inserted
     * @return              Total amount of inserted one hundred peso bill(s)
     */
    public int insertOneHundredPesoBill(int quantity) {
        this.oneHundredPesoCount += quantity;
        return 100 * quantity; 
    }

    /**
     * Inserts a specified number of two hundred peso bill(s)
     * 
     * @param quantity      Amount of bill(s) to be inserted
     * @return              Total amount of inserted two hundred peso bill(s)
     */
    public int insertTwoHundredPesoBill(int quantity) {
        this.twoHundredPesoCount += quantity;
        return 200 * quantity; 
    }

    /**
     *Inserts a specified number of five hundred peso bill(s)
     * 
     * @param quantity      Amount of bill(s) to be inserted
     * @return              Total amount of inserted five hundred peso bill(s)
     */
    public int insertFiveHundredPesoBill(int quantity) {
        this.fiveHundredPesoCount += quantity;
        return 500 * quantity; 
    }

    /**
     * Inserts a specified number of one thousand peso bill(s)
     * 
     * @param quantity      Amount of bill(s) to be inserted
     * @return              Total amount of inserted one thousand peso bill(s)
     */
    public int insertOneThousandPesoBill(int quantity) {
        this.oneThousandPesoCount += quantity;
        return 1000 * quantity; 
    }

    /**
     * Checks if there is enough change in the vending machine
     * 
     * @return      True if there is enough change, otherwise false
     */
    public boolean isEnoughChange() {
        boolean value = false;
        int isEnoughChange = 0;
        
        // check all attributes if their quantity is not less than 0
        if(this.onePesoCount > 0)
            isEnoughChange++;
        if(this.fivePesoCount > 0)
            isEnoughChange++;  
        if(this.tenPesoCount > 0)
            isEnoughChange++;
        if(this.twentyPesoCount > 0)
            isEnoughChange++;          
        if(this.fiftyPesoCount > 0)
            isEnoughChange++;     
        if(this.oneHundredPesoCount > 0)
            isEnoughChange++;        
        if(this.twoHundredPesoCount > 0)
            isEnoughChange++;       
        if(this.fiveHundredPesoCount > 0)
            isEnoughChange++;     
        if(this.oneThousandPesoCount > 0)
            isEnoughChange++;

        // all attributes must be enough in order for the method to return true
        if(isEnoughChange == 9)
            value = true;
        
        return value;
    }

    /**
     * Counts the digits of a number
     * 
     * @return          Total number of digits a number has
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
     * @param price     Price of the item
     * @param payment   Inputted payment from the user
     */
    public void produceChange(double price, double payment) {
        int i;
        double digit;
        double temp = 0;
        double change;
        int digits;

        change = payment - price; 
        digits = countDigits((int)change); // count number of digits
        
        // this condition will execute if digit count is more than 1
        if(digits >= 1) { 
            // get last digit
            digit = change % 10;
            temp = digit;  

            if (digit >= 5 && digit <= 9) { 
                this.fivePesoCount--;               
                displayChange(5);
            } 

            if(digit != 0) {
                for(i = 0; i < digit; i++) {
                    this.onePesoCount--;
                    displayChange(1);
                }
            }
        }

        // this condition will execute if digit count is more than 2
        if(digits >= 2) {
            // get last digit
            digit = change % 100 - temp;
            temp = digit;

            if(digit >= 50 && digit <= 90) {
                this.fiftyPesoCount--;
                digit -= 50;
                displayChange(50);
            } else if(digit >= 20 && digit <= 40) {
                this.twentyPesoCount--;
                digit -= 20;
                displayChange(20);
            } 

            if(digit != 0) {
                for(i = 0; i < digit; i+=10) {
                    this.tenPesoCount--;
                    displayChange(10);
                }
            }
        }
        
        // this condition will execute if digit count is more than 3
        if(digits >= 3) {
            // get last digit
            digit = change % 1000 - temp;
            temp = digit;

            if(digit >= 500 && digit <= 900) {
                this.fiveHundredPesoCount--;
                digit -= 500;
                displayChange(500);
            } else if(digit >= 200 && digit <= 400) {
                this.twoHundredPesoCount--;
                digit -= 200;
                displayChange(200);
            } 

            if(digit != 0) {
                for(i = 0; i < digit; i+=100) {
                    this.oneHundredPesoCount--;
                    displayChange(100);
                }
            }
        }
        
        // this condition will execute if digit count is more than 4
        if(digits >= 4) {
            // get last digit
            digit = change % 10000 - temp;
            temp = digit;

            if(digit == 1000) {
                this.oneThousandPesoCount--;
                digit -= 1000;
                displayChange(1000);
            }
        }
        
        if(change == 0)
            System.out.println("\n[ Thank you for inserting the exact amount! ]\n");
        else
            System.out.println("\n[ Change dispensed successfully! Your total change is PHP " + change + " ]");

        System.out.println();
    }

    /**
     * Displays the change of the user
     * 
     * @param       denomination
     */
    private void displayChange(int denomination) {
        System.out.println();
        System.out.println("Dispensing Change: PHP " + denomination + ".00...");
    }

    /**
     * Gets the number of count of the one peso coin has
     * 
     * @return      the total number of count of the one peso coin
     */
    public int getOnePesoCount() {
        return this.onePesoCount;
    }

    /**
     * Gets the number of count of the five peso coin has
     * 
     * @return      the total number of count of the five peso coin
     */
    public int getFivePesoCount() {
        return this.fivePesoCount;
    }

    /**
     * Gets the number of count of the five peso coin has
     * 
     * @return      the total number of count of the five peso coin
     */
    public int getTenPesoCount() {
        return this.tenPesoCount;
    }

    /**
     * Gets the number of count of the twenty peso bills
     * 
     * @return      the total number of count of the twenty peso bills
     */
    public int getTwentyPesoCount() {
        return this.twentyPesoCount;
    }
    
    /**
     * Gets the number of count of the fifty peso bills
     * 
     * @return      the total number of count of the fifty peso bills
     */
    public int getFiftyPesoCount() {
        return this.fiftyPesoCount;
    }

    /**
     * Gets the number of count of the one hundred peso bills
     * 
     * @return      the total number of count of the one hundred peso bills
     */
    public int getOneHundredPesoCount() {
        return this.oneHundredPesoCount;
    }

    /**
     * Gets the number of count of the two hundred peso bills
     * 
     * @return      the total number of count of the two hundred peso bills
     */
    public int getTwoHundredPesoCount() {
        return this.twoHundredPesoCount;
    }

    /**
     * Gets the number of count of the five hundred peso bills
     * 
     * @return      the total number of count of the five hundred peso bills
     */
    public int getFiveHundredPesoCount() {
        return this.fiveHundredPesoCount;
    }

    /**
     * Gets the number of count of the one thousand peso bills
     * 
     * @return      the total number of count of the one thousand peso bills
     */
    public int getOneThousandPesoCount() {
        return this.oneThousandPesoCount;
    }

    /**
     * Sets the number of count of the one peso coin(s)
     * 
     * @param onePesoCount     Quantity of one peso coin(s)
     */
    public void setOnePesoCoin(int onePesoCount) {
        this.onePesoCount = onePesoCount;
    }

    /**
     * Sets the number of count of the five peso coin(s)
     * 
     * @param fivePesoCount     Quantity of five peso coin(s)
     */
    public void setFivePesoCoin(int fivePesoCount) {
        this.fivePesoCount = fivePesoCount;
    }

    /**
     * Sets the number of count of the ten peso coin(s)
     * 
     * @param tenPesoCount     Quantity of ten peso coin(s)
     */
    public void setTenPesoCoin(int tenPesoCount) {
        this.tenPesoCount = tenPesoCount;
    }

    /**
     * Sets the number of count of the twenty peso bill(s)
     * 
     * @param twentyPesoCount      Quantity of twenty peso bill(s)
     */
    public void setTwentyPesoBill(int twentyPesoCount) {
        this.twentyPesoCount = twentyPesoCount;
    }

    /**
     * Sets the number of count of the fifty peso bill(s)
     * 
     * @param fiftyPesoCount      Quantity of fifty peso bill(s)
     */
    public void setFiftyPesoBill(int fiftyPesoCount) {
        this.fiftyPesoCount = fiftyPesoCount;
    }

    /**
     * Sets the number of count of the one hundred peso bill(s)
     * 
     * @param oneHundredPesoCount      Quantity of one hundred peso bill(s)
     */
    public void setOneHundredPesoBill(int oneHundredPesoCount) {
        this.oneHundredPesoCount = oneHundredPesoCount;
    }

    /**
     * Sets the number of count of the two hundred peso bill(s)
     * 
     * @param twoHundredPesoCount      Quantity of two hundred peso bill(s)
     */
    public void setTwoHundredPesoBill(int twoHundredPesoCount) {
        this.twoHundredPesoCount = twoHundredPesoCount;
    }


    /**
     * Sets the number of count of the five hundred peso bill(s)
     * 
     * @param fiveHundredPesoCount      Quantity of five hundred peso bill(s)
     */
    public void setFiveHundredPesoBill(int fiveHundredPesoCount) {
        this.fiveHundredPesoCount = fiveHundredPesoCount;
    }

    /**
     * Sets the number of count of the one thousand peso bill(s)
     * 
     * @param oneThousandPesoCount      Quantity of one thousand peso bill(s)
     */
    public void setOneThousandPesoBill(int oneThousandPesoCount) {
        this.oneThousandPesoCount = oneThousandPesoCount; 
    }
}