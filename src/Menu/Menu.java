package Menu;
import jdk.dynalink.beans.StaticClass;
import static java.util.Comparator.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.lang.Thread;


// to handle possible mistakes and errors in class later

public class Menu {

    // An inner class Status which contains Status names as const's
    public class Status{
        protected static final int ADD = 1;
        protected static final int ADD_AT = 2;
        protected static final int REMOVE = 3;
        protected static final int SORT = 4;
        protected static final int SIZE = 5;
        protected static final int CLEAR = 6;
        protected static final int PRINT = 7;
        protected static final int COUNT_ELEMENTS = 8;
        protected static final int REPLACE_ALL = 9;
        protected static final int EXIT = 10;
        protected static final int MIN_VAL = 1;
        protected static final int MAX_VAL = 10;
        public Status(){}
    }

    // An inner class Errors which contains Errors names as const's
    public class Errors{
        protected static final String LIST_IS_FULL = "The list is full" ;
        protected static final String ELEMENT_NOT_FOUND = "Element is not found in the list";
        protected static final String INPUT_IS_WRONG = "Wrong input, your choice should be +" +
                                                       "["+Status.MIN_VAL+"-"+Status.MAX_VAL+"]"+":";
        protected static final String INVALID_SIZE = "Size is invalid -> [size > 0]";
        protected static final String EMPTY_LIST = "List is empty";
        protected static final String OUT_OF_RANGE = "Index out of range";
        public Errors(){}
    }

    private ArrayList <Integer> list;
    private Scanner in;
    private int capacity;
    private int choice;
    private int CountElement;

    public Menu(){
        init();
    }

    /**
     * This method prints a symbol as a box around the printed list
     */

    private void printSymbol(String symbol){
        int numberOfStars = (get_capacity() * 6);
        for(int i = 0;i < numberOfStars;i++){
            System.out.print(symbol);
        }
    }

    /**
     * Get-method of the attribute list
     * @return the list as an ArrayList
     */
    public ArrayList<Integer> getList(){
        return list;
    }

    /**
     *  Get-method of the attribute CountElement
     * @return the number of the added elements to the list
     */
    public int getCountElement(){
        return CountElement;
    }

    /**
     * This Method prints the list
     */
    public void printList(){
        System.out.println("\n");
        //printSymbol("-");
        System.out.println("\n"+getList());
        //printSymbol("-");

    }

    /**
     * This method selects which logic to be made on a list
     * e.g. [ADD,REMOVE,PRINT,EXIT]
     */

    private void doChoice(){
        if(this.choice == Status.ADD){
            add();
        }

        else if(this.choice == Status.ADD_AT){
            addAt();
        }

        else if(this.choice == Status.REMOVE){
            remove();
        }
        else if(this.choice == Status.SORT){
            sortList();
        }
        else if(this.choice == Status.SIZE){
            getSize();
        }

        else if(this.choice == Status.CLEAR){
            clear();
        }

          else if(this.choice == Status.PRINT){
            printList();
        }

        else if(this.choice == Status.COUNT_ELEMENTS){
            System.out.println(getCountElement());
        }

        else if(this.choice == Status.REPLACE_ALL){
            replaceElements();
        }

        else if(this.choice == Status.EXIT){
            return;
        }
    }

    /**
     * This method adds an element at the beginning of a list
     */
    public void add(){
        int element = in.nextInt();
        check((list.size() < get_capacity()),Errors.LIST_IS_FULL);
        list.add(element);
        CountElement++;
    }

    /**
     * This method removes a given element from the list
     */
    public void remove(){
        int element = in.nextInt();
        check(list.contains(element),Errors.ELEMENT_NOT_FOUND);
        list.remove((Integer) element);

    }

    /**
     * This method reads what choice the user gives as an Integer Number
     */
    public void readChoice() {
        System.out.print("Enter your choice ["+Status.MIN_VAL+"-"+Status.MAX_VAL+"]:");
        this.choice = in.nextInt();
        boolean condition =   this.choice >= Status.MIN_VAL && this.choice <= Status.MAX_VAL;

        check(condition,Errors.INPUT_IS_WRONG );
    }

    /**
     * Get-method to receive the given choice back
     * @return the choice as an Integer number
     */
    public int getChoice() {
        return choice;
    }

    /**
     * Set-method to read the capacity of the list
     */
    public void set_capacity(){
        System.out.print("[Enter the capacity of the list]:");
        this.capacity = in.nextInt();
        check(capacity > 0,Errors.INVALID_SIZE);
    }

    /**
     * Get-method of the attribut capacity
     * @return the capacity as an integer number
     */
    public int get_capacity(){
        return capacity;
    }

    /**
     * This method to display the menu as String
     */
    public void displayMenu(){
        System.out.println(
                "1. Add Element\n"+
                "2. Add at index\n"+
                "3. remove Element\n"+
                "4. sort List\n"+
                "5. List size\n"+
                "6. clear list\n"+
                "7. Print List\n"+
                "8. Count added Elements\n"+
                "9. replace some elements\n"+
                "10. Exit Program\n"
        );
    }

    /**
     * This method displays the selected status to run its logic by receiving a choice from the method @getChoice()
     *
     */
    public void whichStatus(){
        switch (getChoice()) {
            case Status.ADD -> printOutput("[Enter number]:", "Element added");
            case Status.REMOVE -> printOutput("[Enter an Element to remove]:", "Element removed");
            case Status.SORT -> printOutput("[Sorted List] -> ", "Soretd List printed");
            case Status.SIZE -> printOutput("[List size] -> ", "Size printed");
            case Status.CLEAR -> printOutput("[List cleared] -> ", "Elements removed");
            case Status.ADD_AT -> printOutput("", "");
            case Status.PRINT -> printOutput("", "");
            case Status.COUNT_ELEMENTS -> printOutput("[Counted Elements]:", "");
            case Status.REPLACE_ALL -> printOutput("", "");
            case Status.EXIT -> printOutput("All work was saved !\n", "Program exited");
        }
    }

    /**
     * This method to initialise the class components
     */
    public void init(){
        CountElement = 0;
        in = new Scanner(System.in);
        set_capacity();
        list = new ArrayList<Integer>(capacity);
    }

    /**
     * This method to print and do the logic of the selected choice
     * @param argument String given to the user as an input text
     * @param action String given to the user as a reminder of the chosen option
     */
    public void printOutput(String argument ,String action){
        System.out.print(argument);
        doChoice();
        System.out.println(action+"\n");
    }

    /**
     * This method to run the program
     */
    public void run() throws InterruptedException {
        do
        {
            clrscr();
            displayMenu();
            readChoice();
            whichStatus();
            if(getChoice() != Status.PRINT)
                printList();
            Thread.sleep(1000);
        }
        while ((choice != Status.EXIT));
    }


    /**
     * This method sorts a list
     */
    public void sortList(){
        check(!list.isEmpty(),Errors.EMPTY_LIST);
        list.sort(naturalOrder());
    }


    /**
     * This method clear and reset the screen of the printed list
     */
    public static void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {}
    }

    /**
     * This Method deletes all the elements of the given list
     */
    public void clear(){
        check(!list.isEmpty(),Errors.EMPTY_LIST);
        list.clear();
    }


    /**
     * This method returns the size of a given list
     */
    public void getSize(){
        System.out.println(list.size());
    }

    /**
     * This method adds one element at a certain position
     */
    public void addAt(){
        int[] elements = new int[2];
        for(int i = 0; i < 2; i++) {
            if(i == 0){
                System.out.print("Enter Index:");
            }
            else{
              System.out.print("Enter Element:");
            }
            elements[i] = in.nextInt();
        }
        int index = elements[0];
        int number = elements[1];


        check(!(index < 0 && index >= list.size()-1),Errors.OUT_OF_RANGE);
        list.remove((Integer) list.get(index));
        list.add(index,(Integer) number);
        CountElement++;
    }

    /**
     * This method replace set of elements to new added elements by the user
     */
    public void replaceElements(){
        int size;
        int startIndex;

        check(!list.isEmpty(),Errors.EMPTY_LIST);

        System.out.print("How many numbers do you want to replace:");
        size = in.nextInt();

        check(size > 0,Errors.INVALID_SIZE);

        System.out.print("Enter the start Index:");
        startIndex = in.nextInt();

        check(!((startIndex <= 0 ) && (startIndex >= list.size())) ,Errors.OUT_OF_RANGE);
        int element = 0;

        for(int j = 1,i = startIndex;i < size;i++){
            System.out.print("Number["+ (j) +"]:");
            element = in.nextInt();
            list.set(i,element);
            j++;
        }
    }

    /**
     * Method to check a specific condition and throw errors if needed
     * @param condition the condition to be checked
     * @param msg the thrown message as a String by possible Illegal cases
     * @return the result as boolean datatype
     */
    public boolean check(boolean condition,final String msg){
        if(!condition)
            throw new IllegalArgumentException(msg);
        else
            return true;
    }
}