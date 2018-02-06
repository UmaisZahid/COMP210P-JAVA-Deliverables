package Deliverable1;

import java.util.*;

public class display {

    // Initialise display/menu related fields.
    private static Scanner scan = new Scanner(System.in); // Static scanner instance for input
    private final static List<Character> menuOptions = Arrays.asList('A', 'a', 'V', 'v', 'C', 'c', 'S', 's', 'Q', 'q','M', 'm'); // List of valid input characters
    public char option; // Variable containing current menu option choice
    public boolean exit = false; // Boolean to determine whether program should exit

    // Strings of paragraphs used in displayAbout() method. Formatted by formatAndPrint method to fit particular number of words per line.
    private static String aboutTextPara1 = "Split-it was designed to help teams allocate credit for a project fairly. " +
            "It works by collecting votes for a project, and allocating marks between team members.";
    private static String aboutTextPara2 = "This idea was originally inspired by the work of Ariel Procaccia and Jonathan Goldman's programmed called " +
            "Spliddit, which offers fair solutions for numerous division problems, including rent payments, restaurant" +
            " bills and shared tasks. To run this programme, one needs to create and register a project, collect votes " +
            "from each individual team member and the programme will calculate and display the allocated votes for the project. ";


    // Displays menu and requests user to input option.
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


    // Requests user to input option, while validating input to ensure it is a valid input character
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

    // Displays about page and requests input
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

    // Displays create project page
    public void displayCreateProject(){
        System.out.println("\t\t\t\tCREATE PROJECT\n");

        // Request and scan project name
        System.out.print("\tPlease provide a project name: ");
        scan.next(); // Consume new line character (\n)
        String projectName = scan.nextLine();
        System.out.print("\tPlease provide the number of members: ");
        int noMembers = 0;

        // If input is not integer, catch mismatch exception and request input once more
        boolean inputValid = false;
        while(!(inputValid)){
            try{
                noMembers = scan.nextInt();
                inputValid = true;
            } catch(InputMismatchException ime) {
                System.out.print("\tInvalid input, please enter an integer: ");
                scan.next();
            }
        }

        // ArrayList of member names.
        // Of type ArrayList to ensure that it has variable length and more members can be added if need be at a later point.
        ArrayList<String> memberNames = new ArrayList<>();

        // Iterate over number of members, and request members names.
        for(int i = 0; i<noMembers; i++){
            System.out.print("\tPlease provide the name of member " + (i+1) + ": ");
            scan.next(); // Consume new line character ('/n')
            memberNames.add(scan.nextLine());
        }

        // Create project instance, and provide necessary constructor arguments
        project newProject = new project(projectName,noMembers,memberNames);
        System.out.println("\n\t\t\t\tPROJECT CREATED!\n");
        pickOption(); // Request option
    }

    // Display enter votes page. Currently a dummy method for future deliverables, redirects to main menu.
    public void displayEnterVotes(){
        System.out.println("\t\tThis is the enter votes pages");
        option = 'M'; // Set option to main menu, (redirects automatically)
    }


    // Display show projects page. Currently a dummy method for future deliverables, redirects to main menu.
    public void displayShowProjects(){
        System.out.println("\t\tThis is the show project page");
        option = 'M'; // Set option to main menu, (redirects automatically)
    }

    // Formats and prints an input String so that it has a particular number of words per line.
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

}
