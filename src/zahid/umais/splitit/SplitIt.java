package zahid.umais.splitit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SplitIt{

    //----------------------------------------------------------------------
    // Initialise program related fields. Includes: static list of projects and the JSON serializer.
    //----------------------------------------------------------------------
    private static Gson jsonSerializer = new GsonBuilder().setPrettyPrinting().create();
    public static ArrayList<Project> projectList = new ArrayList<Project>();
    private static final String PROJECT_LIST_FILE_NAME = "ListOfProjects.json";
    public static Scanner scan = new Scanner(System.in); // Static scanner instance for input

    //----------------------------------------------------------------------
    // This static function stores the array list of projects in JSON format in a .json file.
    //----------------------------------------------------------------------
    public static void storeProjectsToFile() {
        if (!projectList.isEmpty()) {
            // If project list is not empty, serialize project list to JSON file
            try (FileWriter fileWriter = new FileWriter(PROJECT_LIST_FILE_NAME)) {
                jsonSerializer.toJson(projectList.toArray(), fileWriter);
            } catch (IOException ioe) {
                System.out.println("There was an error storing the projects. :(");
            }
        }
    }

    //----------------------------------------------------------------------
    // This static function reads the array list of projects in JSON format from a .json file.
    //----------------------------------------------------------------------
    private static void readProjectsFromFile(){
        //Deserialize projects from file, if no file exists, catch exception and do nothing.
        try(FileReader fileReader = new FileReader(PROJECT_LIST_FILE_NAME)){
            projectList = new ArrayList<Project>(Arrays.asList(jsonSerializer.fromJson(fileReader, Project[].class)));
        } catch (IOException ioe) {
            // No need to do anything
        }
    }

    //-----------------------------------------------------------------------------------------------------
    // Scans input and validates scanned input as being an integer within certain provided ranges.
    // If not integer, requests input until it is.
    // @param minVal, maxVal: Integers to determine the range which integer should lie within
    // @param errorMessage: custom String which is outputted whenever incorrect input is provided
    // @param marginSize: integer number of tabs to add to the margin of the error messages to ensure they align properly
    // @return Scanned and validated input integer
    //-----------------------------------------------------------------------------------------------------
    public static int integerInputValidation(int minVal, int maxVal, String errorMessage, int marginSize){
        boolean inputValid = false;


        int inputInt = 0;
        String marginTabs = "";

        for (int i = 0; i < marginSize; i++){marginTabs += "\t";}

        while(!inputValid){
            try{
                inputInt =  scan.nextInt();
                scan.nextLine(); // Scan any remaining new line characters in cache.
                if (inputInt >= minVal && inputInt <= maxVal){
                    inputValid = true;
                } else {
                    System.out.print(marginTabs);
                    System.out.print(errorMessage);
                }
            } catch(InputMismatchException ime) {
                scan.nextLine(); // Scan any remaining new line characters in cache.
                System.out.print(marginTabs);
                System.out.print("Invalid input, please enter an integer: ");
            }
        }
        return inputInt;
    }

    //-----------------------------------------------------------------------------------------------------
    // Scans input and validates scanned input as being a String with certain properties. (not empty, only letters etc.)
    // If invalid, requests input until it is.
    // @param acceptOnlyLetters: boolean to determine whether method should enforce only letters in string input
    // @param checkEmpty: boolean to determine whether method should prevent empty strings
    // @param marginSize: integer number of tabs to add to the margin of the error messages to ensure they align properly
    // @return String containing scanned and validated input. Input is also trimmed to remove encasing whitespaces.
    //-----------------------------------------------------------------------------------------------------
    public static String stringInputValidation(boolean acceptOnlyLetters, boolean checkEmpty, int marginSize){

        // This boolean stores whether the input is valid or not
        boolean inputValid = false;
        String inputString = "";
        String marginTabs = "";
        // Create margin string composed of tabs
        for (int i = 0; i < marginSize; i++){marginTabs += "\t";}

        // While the input is invalid, request input until it is
        while(!inputValid){
            inputValid = true; // Set String to be valid by default. If it does not match any criteria, it is set to false.
            try{
                inputString =  scan.nextLine().trim(); // Scan input String and trim whitespace on either side.

                // Check String is not empty
                if (checkEmpty) {
                    if (inputString.isEmpty()) {
                        inputValid = false;
                        System.out.print(marginTabs);
                        System.out.print("Input empty, please provide a non-empty string. Try again: ");
                    }
                }

                // Check String only uses letters.
                if (acceptOnlyLetters){
                    if (!(inputString.matches("[a-zA-Z ]+|^$"))){
                        inputValid = false;
                        System.out.print(marginTabs);
                        System.out.print("Please ensure input contains only letters. Try again: ");
                    }
                }

            // If scanning fails for some other reason
            } catch(NoSuchElementException nsee) {
                inputValid = false;
                System.out.print(marginTabs);
                System.out.print("Invalid input, unable to scan input!");
            }
        }

        return inputString;
    }

    //-----------------------------------------------------------------------------------------------------
    // Main Method
    //-----------------------------------------------------------------------------------------------------
    public static void main(String[] args){

        //---------------------------------------------------------
        // Create new Display instance, display menu and populate project array list from JSON file.
        //---------------------------------------------------------
        Display currentDisplay = new Display();
        currentDisplay.displayMenu();
        readProjectsFromFile();

        //-------------------------------------------------------------------------
        // Main execution block: keeps program running until exit boolean is true.
        //-------------------------------------------------------------------------
        while(!(currentDisplay.exit)){
            // Check what currentDisplay variable option is currently set to and Display relevant page.
            switch (currentDisplay.option){
                case ('A'): case ('a'): currentDisplay.displayAbout(); break;
                case ('C'): case ('c'): currentDisplay.displayCreateProject(); break;
                case ('V'): case ('v'): currentDisplay.displayEnterVotes(); break;
                case ('H'): case ('h'): currentDisplay.displayChangeVotes(); break;
                case ('S'): case ('s'): currentDisplay.displayShowProjects(); break;
                case ('M'): case ('m'): currentDisplay.displayMenu(); break;
                case ('Q'): case ('q'): currentDisplay.exit(); return; // Set exit boolean to true if input = 'q'
            }
        }
    }

}
