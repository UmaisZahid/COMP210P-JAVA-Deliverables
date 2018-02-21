package Deliverable1;

import java.util.*;

import static Deliverable1.SplitIt.projectList;

public class Display {
    //----------------------------------------------------------------------
    // Initialise Display/menu related fields.
    //----------------------------------------------------------------------
    private static Scanner scan = new Scanner(System.in); // Static scanner instance for input
    private final static List<Character> menuOptions = Arrays.asList('A', 'a', 'V', 'v', 'C', 'c', 'S', 's', 'Q', 'q','M', 'm'); // List of valid input characters
    public char option; // Variable containing current menu option choice
    public boolean exit = false; // Boolean to determine whether program should exit

    // Strings of paragraphs used in displayAbout() method. Formatted by formatAndPrint method to fit particular number of words per line.
    final private static String aboutTextPara1 = "Split-it was designed to help teams allocate credit for a Project fairly. " +
            "It works by collecting votes for a Project, and allocating marks between team members.";
    final private static String aboutTextPara2 = "This idea was originally inspired by the work of Ariel Procaccia and Jonathan Goldman's programmed called " +
            "Spliddit, which offers fair solutions for numerous division problems, including rent payments, restaurant" +
            " bills and shared tasks. To run this programme, one needs to create and register a Project, collect votes " +
            "from each individual team member and the programme will calculate and Display the allocated votes for the Project. ";

    //----------------------------------------------------------------------
    // Displays menu and requests user to input option.
    //----------------------------------------------------------------------
    public void displayMenu(){

        System.out.println("\tWelcome to Split-it");
        System.out.println();
        System.out.println("\t\tAbout (A)");
        System.out.println("\t\tCreate Project (C)");
        System.out.println("\t\tEnter Votes (V)");
        System.out.println("\t\tShow Project (S)");
        System.out.println("\t\tQuit (Q)");
        System.out.println();
        pickOption(); // requests user to pick option

    }

    //------------------------------------------------------------------------------------------------
    // Requests user to input option, while validating input to ensure it is a valid input character
    //------------------------------------------------------------------------------------------------
    private void pickOption(){

        System.out.print("\tPlease choose an option, or enter m to return to the menu: ");
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
        System.out.println();
        System.out.println("\t\t\t\tABOUT: Split-It");
        System.out.println();
        formatAndPrint(aboutTextPara1, 7);
        System.out.println();
        formatAndPrint(aboutTextPara2, 7);
        System.out.println();
        System.out.println();

        pickOption(); // Request input option
    }

    //---------------------------------------------------------
    // Displays create Project page
    //---------------------------------------------------------
    public void displayCreateProject(){
        System.out.println("\t\t\t\tCREATE PROJECT\n");

        // Request and scan Project name
        System.out.print("\tPlease provide a Project name: ");
        scan.nextLine(); // Consume new line character (\n)
        String projectName = scan.nextLine();
        System.out.print("\tPlease provide the number of members: ");
        int noMembers = 0;

        // Scan the next integer while ensuring it's valid input
        noMembers = scanIntInputValidation();

        // ArrayList of member names.
        // Of type ArrayList to ensure that it has variable length and more members can be added if need be at a later point.
        ArrayList<String> memberNames = new ArrayList<>();

        // Iterate over number of members, and request members names.
        for(int i = 0; i<noMembers; i++){
            System.out.print("\tPlease provide the name of member " + (i+1) + ": ");
            memberNames.add(scan.nextLine());
        }

        // Create Project instance, and provide necessary constructor arguments
        projectList.add(new Project(projectName,noMembers,memberNames));

        System.out.println("\n\t\t\t\tPROJECT CREATED!\n");
        pickOption(); // Request menu option
    }

    //-----------------------------------------------------------------------------------------------------
    // Display enter votes page. Currently a dummy method for future deliverables, redirects to main menu.
    //-----------------------------------------------------------------------------------------------------
    public void displayEnterVotes(){
        System.out.println("\tPlease choose a project from the projects list: ");
        if (projectList == null){System.out.println("\tI'm afraid you havene't entered any projects yet. :(");
        } else {
            for (int i = 0; i < projectList.size(); i++){
                System.out.println("\t\t" + (i+1) + ") " + projectList.get(i).returnName());
            }
        }
        System.out.print("\n\t\tYour choice: ");
        int choice = scanIntInputValidation(); // Scan next integer while checking it's valid input
        Project chosenProject;
        if ((choice > 0) && (choice < projectList.size())){
            chosenProject = projectList.get(choice-1);
        }

        pickOption();
    }

    //-----------------------------------------------------------------------------------------------------
    // Display show projects page. Currently a dummy method for future deliverables, redirects to main menu.
    //-----------------------------------------------------------------------------------------------------
    public void displayShowProjects(){
        System.out.println("\t\tHere is a list of your projects!");
        SplitIt.readProjectsFromFile();

        if (projectList != null){
            for (int i = 0; i < projectList.size(); i++){
                System.out.println("\t\t" + (i+1) + ") " + projectList.get(i).returnName());
            }
        }

        pickOption();
    }

    //-----------------------------------------------------------------------------------------------------
    // Formats and prints an input String so that it has a particular number of words per line.
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
    // Validates scanned input as being integer. If not valid, requests input until it is.
    //-----------------------------------------------------------------------------------------------------
    public int scanIntInputValidation(){
        boolean inputValid = false;
        int inputInt;
        while(true){
            try{
                inputInt =  scan.nextInt();
                scan.nextLine(); // Scan any new line characters in cache
                return inputInt;
            } catch(InputMismatchException ime) {
                System.out.print("\tInvalid input, please enter an integer: ");
                scan.nextLine(); // Scan any remaining characters
            }
        }

    }

}
