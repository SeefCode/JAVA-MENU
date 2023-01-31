package Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList <Integer> list;
    private Scanner in;
    private int choice;
    private final int ADD = 1;
    private final int REMOVE = 2;
    private final int PRINT = 3;
    private final int EXIT = 4;


    // Default Constructor
    public Menu(){}

    /**
     * This methode selects which logic to be made on a list
     * e.g. [ADD,REMOVE,PRINT,EXIT]
     */
    private void doChoice(){
        if(this.choice == ADD){
            list.add(in.nextInt());
        }
        else if(this.choice == REMOVE){
            int element = in.nextInt();
            check(list.contains(element),"Element is not found in the list");
            list.remove((Integer) element);
        }
        else if(this.choice == PRINT){
            System.out.println(list);
        }
        else if(this.choice == EXIT){
            return;
        }
    }

    /**
     * This methode reads what choice the user have as an Integer Number
     * The possible choices are [ADD,REMOVE,PRINT,EXIT]
     */
    public void readChoice() {
        System.out.print("Enter your choice [1-4]:");
        this.choice = in.nextInt();
        boolean condition = (this.choice != ADD) || (this.choice != REMOVE) || (this.choice != PRINT) || (this.choice != EXIT);
        check(condition, "Wrong input, your choice should be [1-4]");
    }

    /**
     * Methode to receive the given choice back
     * @return the choice as an Integer number
     */
    public int getChoice() {
        return choice;
    }

    /**
     * Methode to read the capacity of a list
     * @return the size of a given capacity as an Integer number
     */
    public int get_capacity(){
        int size;
        System.out.print("[Enter the capacity of the list]:");
        size = in.nextInt();
        check(size >=0,"Size should be bigger than 0");
        return size;
    }

    /**
     * methode to display the menu and run the chosen case logic
     */
    public void displayMenu(){
        System.out.println(
                "1. Add Element\n"+
                "2. remove Element\n"+
                "3. print List\n"+
                "4. Exit Program\n"
        );

        switch (getChoice()){
            case ADD:
                printOutput("[Enter number]:","Element added");
                break;
            case REMOVE:
                printOutput("[Delete Number]:","Element removed");
                break;
            case PRINT:
                printOutput("[List] -> ","List printed");
                break;
            case EXIT:
                printOutput("All work was saved !\n","Program exited");
                break;
        }
    }

    /**
     * Methode to initialise the class components
     */
    public void init(){
        in = new Scanner(System.in);
        list = new ArrayList<Integer>(get_capacity());
    }

    /**
     * Methode to print and do the logic of the selected choice
     * @param argument String given to the user as an input text
     * @param action String given to the user as a reminder of the chosen option
     */
    public void printOutput(String argument ,String action){
        System.out.print(argument);
        doChoice();
        System.out.println(action+"\n");
    }

    /**
     * Methode to launch the program
     */
    public void run(){
        init();
        do
        {
            displayMenu();
            readChoice();
        }
        while ((choice != EXIT));
    }

    /**
     * Methode to check a specific condition and throw errors if needed
     * @param condition the condition to be checked
     * @param msg the thrown message as a String by possible Illegal cases
     * @return the result as boolean datatype
     */
    public boolean check(boolean condition,String msg){
        if(!condition)
            throw new IllegalArgumentException(msg);
        else
            return true;
    }
}
