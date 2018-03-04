package zahid.umais.splitit;

import java.util.*; // Import java utility classes
// Import static scanner instance, and utility functions
import static zahid.umais.splitit.SplitIt.stringInputValidation;
import static zahid.umais.splitit.SplitIt.integerInputValidation;
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

        System.out.println("\tWelcome to Split-it");
        System.out.println();
        System.out.println("\t\tAbout (A)");
        System.out.println("\t\tCreate Project (C)");
        System.out.println("\t\tEnter Votes (V)");
        System.out.println("\t\tChange Votes (H)");
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

        System.out.print("\tPlease choose an option, or enter m to see the menu again: ");

        option = scan.next().charAt(0); // Scan first character of input

        // While input is invalid, request for valid input.
        while (!(menuOptions.contains(option))){
            System.out.println();
            System.out.print("\t\tInvalid Input, please enter again: ");
            option = scan.next().charAt(0);
        }

        System.out.println(); // Add spacing
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
        System.out.println("\t\t\t\tABOUT: Split-It");
        System.out.println();
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

        System.out.println("\t\t\t\tCREATE PROJECT\n");

        // Consume any new line characters (\n) left in input buffer
        scan.nextLine();

        // Request and scan Project name
        System.out.print("\tPlease provide a Project name: ");

        // Scan project name, ensure it is not empty
        String projectName = stringInputValidation(false, true,1);

        // Request number of members
        System.out.print("\tPlease provide the number of members (Min: 3, Max: 50): ");
        // Scan the next integer while ensuring it's valid input
        int noMembers = integerInputValidation(3,50, "Please enter a number between 3 and 50: ",1);

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

        // Request they select a project to change votes for
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
        System.out.println("\t\tHere is a list of your projects!");

        if (SplitIt.projectList != null){
            for (int i = 0; i < SplitIt.projectList.size(); i++){
                System.out.println("\t\t" + (i+1) + ") " + SplitIt.projectList.get(i).returnName());
            }
            System.out.println();
        }

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

}
