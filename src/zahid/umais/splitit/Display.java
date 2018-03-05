package zahid.umais.splitit;

import java.util.*; // Import java utility classes
// Import static scanner instance, and utility functions
import static zahid.umais.splitit.SplitIt.scan;

public class Display {
    //----------------------------------------------------------------------
    // Initialise Display/menu related fields.
    //----------------------------------------------------------------------
    private final static List<Character> menuOptions = Arrays.asList('A', 'a', 'V', 'v', 'C', 'c', 'H','h','S','s','Q','q','M','m'); // List of valid input characters
    public char option; // Variable containing current menu option choice
    public boolean exit = false; // Boolean to determine whether program should exit

    //----------------------------------------------------------------------
    // Displays menu and requests user to input option.
    //----------------------------------------------------------------------
    public void displayMenu(){

        System.out.println();
        System.out.println("\t---------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\tWELCOME TO SPLIT-IT!");
        System.out.println("\t---------------------------------------------------------------------\n");
        System.out.println("\t\tAbout (A)");
        System.out.println("\t\tCreate Project (C)");
        System.out.println("\t\tEnter Votes (V)");
        System.out.println("\t\tChange Your Votes (H)");
        System.out.println("\t\tShow Project (S)");
        System.out.println("\t\tQuit (Q)");
        System.out.println();
        pickOption(); // requests user to pick option

    }

    //------------------------------------------------------------------------------------------------
    // Requests user to input option, while validating input to ensure it is a valid input character
    // Set option variable to the option they have provided
    //------------------------------------------------------------------------------------------------
    private void pickOption(){
        System.out.print("\t***Please choose an option, or enter m to see the menu again: ***: ");

        option = scan.next().charAt(0); // Scan first character of input

        // While input is invalid, request for valid input.
        while (!(menuOptions.contains(option))){
            System.out.println();
            System.out.print("\tInvalid Input, please enter again: ");
            option = scan.next().charAt(0);
        }

    }

    //---------------------------------------------------------
    // Displays about page and requests input
    //---------------------------------------------------------
    public void displayAbout(){
        // These are Strings of paragraphs used in the About page.
        // They are then Formatted by formatAndPrint method to fit a
        // particular number of words per line.
        String ABOUTTEXTPARAGRAPH1 = "Split-it was designed to help teams allocate credit for a Project fairly. " +
                "It works by collecting votes for a Project, and allocating marks between team members.";
        String ABOUTTEXTPARAGRAPH2 = "This idea was originally inspired by the work of Ariel Procaccia and Jonathan Goldman's programmed called " +
                "Spliddit, which offers fair solutions for numerous division problems, including rent payments, restaurant" +
                " bills and shared tasks. To run this programme, one needs to create and register a Project, collect votes " +
                "from each individual team member and the programme will calculate and Display the allocated votes for the Project. ";
        // Format and print
        System.out.println();
        System.out.println("\t---------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\tABOUT: Split-It");
        System.out.println("\t---------------------------------------------------------------------\n");
        formatAndPrint(ABOUTTEXTPARAGRAPH1, 7);
        System.out.println();
        formatAndPrint(ABOUTTEXTPARAGRAPH2, 7);
        System.out.println();
        System.out.println();

        pickOption(); // Request input option
    }

    //---------------------------------------------------------
    // Displays create Project page
    //---------------------------------------------------------
    public void displayCreateProject(){
        System.out.println();
        System.out.println("\t---------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\tCREATE PROJECT!");
        System.out.println("\t---------------------------------------------------------------------\n");

        // Consume any new line characters (\n) left in input buffer
        scan.nextLine();

        // Request and scan Project name
        System.out.print("\tPlease provide a Project name: ");

        // Scan project name, ensure it is not empty
        String projectName = stringInputValidation(false, true,1);

        // Request number of members
        System.out.print("\n\tPlease provide the number of members (Min: 3, Max: 50): ");
        // Scan the next integer while ensuring it's valid input
        int noMembers = integerInputValidation(3,50, "Please enter a number between 3 and 50: ",1);

        System.out.println(); // Spacing

        // Initialise array of member names
        String[] memberNames = new String[noMembers];

        // Iterate over number of members, and request members names.
        for(int i = 0; i<noMembers; i++){
            System.out.print("\tPlease provide the name of member " + (i+1) + ": ");
            memberNames[i] = stringInputValidation(true,true,1);
        }

        // Create Project instance, and save project to file.
        Project newProject = new Project(projectName,noMembers,memberNames);
        SplitIt.projectList.add(newProject); // Add project to project list arraylist.

        System.out.println("\n\t\t\t\tPROJECT CREATED AND SAVED SUCCESSFULLY!\n");

        pickOption(); // Request menu option
    }

    //-----------------------------------------------------------------------------------------------------
    // Display enter votes page. Currently a dummy method for future deliverables, redirects to main menu.
    //-----------------------------------------------------------------------------------------------------
    public void displayEnterVotes(){

        System.out.println();
        System.out.println("\t---------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\tENTER VOTES!");
        System.out.println("\t---------------------------------------------------------------------\n");
        // Request they select a project to enter votes for
        System.out.println("\tPlease choose a project from the projects list: \n");

        // Check if project list is empty or uninitialised. If not, output project names in a list with index.
        if (SplitIt.projectList == null || SplitIt.projectList.size() == 0){
            System.out.println("\tI'm afraid you haven't entered any projects yet. :(");
        } else {

            // Print list of project names
            for (int i = 0; i < SplitIt.projectList.size(); i++){
                System.out.println("\t\t" + (i+1) + ") " + SplitIt.projectList.get(i).returnName());
            }

            // Scan their choice of project and validate the input
            System.out.print("\n\tYour choice: ");
            int choice = integerInputValidation(1,SplitIt.projectList.size(), "Please enter a value between " + 1 + " and " + SplitIt.projectList.size(),1);

            // Return the project name
            Project chosenProject = SplitIt.projectList.get(choice-1);

            // Request votes for the project
            chosenProject.requestVotes();
        }

        System.out.println(); // Spacing

        // Request menu option
        pickOption();
    }

    //-----------------------------------------------------------------------------------------------------
    // Displays change votes prompt. Let's user change votes from a particular individual for a particular project
    //-----------------------------------------------------------------------------------------------------
    public void displayChangeVotes(){

        System.out.println();
        System.out.println("\t---------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\tCHANGE YOUR VOTES!");
        System.out.println("\t---------------------------------------------------------------------");

        // Check if project list is empty or uninitialised. If not, output project names in a list with index.
        if (SplitIt.projectList == null || SplitIt.projectList.size() == 0){
            System.out.println("\tI'm afraid you haven't entered any projects yet. :(\n");
            System.out.println("\tFeel free to create some however!");
        } else {
            // Request they select a project to change votes for
            System.out.println("\tPlease choose a project from the projects list: \n");

            // Print list of project names
            for (int i = 0; i < SplitIt.projectList.size(); i++){
                System.out.println("\t\t" + (i+1) + ") " + SplitIt.projectList.get(i).returnName());
            }

            // Scan their choice of project and validate the input
            System.out.print("\n\tYour choice: ");
            int choice = integerInputValidation(1,SplitIt.projectList.size(), "Please enter a value between " + 1 + " and " + SplitIt.projectList.size(),1);

            // Return the project name
            Project chosenProject = SplitIt.projectList.get(choice-1);

            // Request votes for the project
            chosenProject.changeVotes();
        }

        System.out.println(); // Spacing

        // Request menu option
        pickOption();
    }

    //-----------------------------------------------------------------------------------------------------
    // Display show projects page. Currently a dummy method for future deliverables, redirects to main menu.
    //-----------------------------------------------------------------------------------------------------
    public void displayShowProjects(){
        System.out.println();
        System.out.println("\t---------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\tSHOW PROJECTS!");
        System.out.println("\t---------------------------------------------------------------------\n");



        if (SplitIt.projectList != null && !SplitIt.projectList.isEmpty()){
            System.out.println("\tHere is a list of your projects!\n");
            for (int i = 0; i < SplitIt.projectList.size(); i++){
                System.out.println("\t\t" + (i+1) + ") " + SplitIt.projectList.get(i).returnName());
            }
            System.out.println();
        } else {
            System.out.println("\tI'm afraid you don't have any projects. :(\n");
            System.out.println("\tFeel free to create some however ");
        }

        System.out.println(); // Spacing

        pickOption();
    }

    //-----------------------------------------------------------------------------------------------------
    // Formats and prints an input String so that it has a particular number of words per line.
    // @param text: String that is to be formatted
    // @param wordsPerLine: Integer of the number of words per line that text should be formatted to
    // @return void: Prints formatted string
    //-----------------------------------------------------------------------------------------------------
    private void formatAndPrint(String text, int wordsPerLine){
        String[] textSplit = text.split(" ");
        for (int i=0; i<(textSplit.length); i++){
            if ((i % wordsPerLine) == 0){
                System.out.println();
                System.out.print("\t\t");
            }
            System.out.print(textSplit[i] + " ");
        }
    }

    //-----------------------------------------------------------------------------------------------------
    // Runs exit sequence. Saves projects array to JSON file and sets exit boolean to true.
    //-----------------------------------------------------------------------------------------------------
    public void exit(){
        SplitIt.storeProjectsToFile();
        this.exit = true;
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


}
