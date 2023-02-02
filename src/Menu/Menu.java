package Menu;
import static java.util.Comparator.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.lang.Thread;


public class Menu {
    private ArrayList <Integer> list;
    private Scanner in;
    private int choice;
    private final int ADD = 1;
    private final int REMOVE = 2;
    private final int PRINT = 3;
    private final int SORT = 4;

    private final int SIZE = 5;
    private final int EXIT = 6;


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
        else if(this.choice == SORT){
            sortList();
            System.out.println(list);
        }
        else if(this.choice == SIZE){
            getSize();
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
        System.out.print("Enter your choice [1-6]:");
        this.choice = in.nextInt();
        boolean condition = (this.choice != ADD) || (this.choice != REMOVE) || (this.choice != PRINT) || (this.choice != SORT) || (this.choice != EXIT) || (this.choice != SIZE);
        check(condition, "Wrong input, your choice should be [1-6]");
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
        check(size > 0,"Size should be bigger than 0");
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
                "4. sort List\n"+
                "5. List size\n"+
                "6. Exit Program\n"
        );
    }

    public void logic(){
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
            case SORT:
                printOutput("[Sorted List] -> ","Soretd List printed");
                break;
            case SIZE:
                printOutput("[List size] -> ","Size printed");
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
    public void run() throws InterruptedException {
        init();
        do
        {
            clrscr();
            displayMenu();
            readChoice();
            logic();
            Thread.sleep(1000);
        }
        while ((choice != EXIT));
    }


    /**
     * This methode sorts a list when it is not empty
     */
    public void sortList(){
        check(!list.isEmpty(),"List has no elements to be sorted");
        list.sort(naturalOrder());
    }


    /**
     * This methode clear and reset the screen printed list
     */
    public static void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }


    /**
     * This methode returns the size of a given list
     */
    public void getSize(){
        System.out.println(list.size());
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
