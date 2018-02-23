package Deliverable1;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class Display {
    //----------------------------------------------------------------------
    // Initialise Display/menu related fields.
    //----------------------------------------------------------------------
    private static Scanner scan = new Scanner(System.in); // Static scanner instance for input
    private final static List<Character> menuOptions = Arrays.asList('A', 'a', 'V', 'v', 'C', 'c', 'S', 's', 'Q', 'q','M', 'm'); // List of valid input characters
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
        // Strings of paragraphs used in displayAbout() method. Formatted by formatAndPrint method to fit particular number of words per line.
        String ABOUTTEXTPARAGRAPH1 = "Split-it was designed to help teams allocate credit for a Project fairly. " +
                "It works by collecting votes for a Project, and allocating marks between team members.";
        String ABOUTTEXTPARAGRAPH2 = "This idea was originally inspired by the work of Ariel Procaccia and Jonathan Goldman's programmed called " +
                "Spliddit, which offers fair solutions for numerous division problems, including rent payments, restaurant" +
                " bills and shared tasks. To run this programme, one needs to create and register a Project, collect votes " +
                "from each individual team member and the programme will calculate and Display the allocated votes for the Project. ";
        System.out.println();
        System.out.println("\t\t\t\tABOUT: Split-It");
        System.out.println();
        formatAndPrint(ABOUTTEXTPARAGRAPH1, 7);
        System.out.println();
        formatAndPrint(ABOUTTEXTPARAGRAPH1, 7);
        System.out.println();
        System.out.println();

        pickOption(); // Request input option
    }

    //---------------------------------------------------------
    // Displays create Project page
    //---------------------------------------------------------
    public void displayCreateProject(){

        // Begins create project prompt
        System.out.println("\t\t\t\tCREATE PROJECT\n");

        // Request and scan Project name
        System.out.print("\tPlease provide a Project name: ");
        scan.nextLine(); // Consume new line character (\n)
        String projectName = scan.nextLine();
        System.out.print("\tPlease provide the number of members (Maximum of 50 members per project)s: ");
        int noMembers = 0;

        // Scan the next integer while ensuring it's valid input
        noMembers = integerInputValidation(0,50, "");

        // Initialise array of member names
        String[] memberNames = new String[noMembers];

        // Iterate over number of members, and request members names.
        for(int i = 0; i<noMembers; i++){
            System.out.print("\tPlease provide the name of member " + (i+1) + ": ");
            memberNames[i] = (scan.nextLine());
        }

        // Create Project instance, and provide necessary constructor arguments
        Project newProject = new Project(projectName,noMembers,memberNames);
        SplitIt.projectList.add(newProject); // Add project to project list arraylist.
        SplitIt.storeProjectsToFile();
        System.out.println("\n\t\t\t\tPROJECT CREATED AND SAVED SUCCESSFULLY!\n");
        pickOption(); // Request menu option
    }

    //-----------------------------------------------------------------------------------------------------
    // Display enter votes page. Currently a dummy method for future deliverables, redirects to main menu.
    //-----------------------------------------------------------------------------------------------------
    public void displayEnterVotes(){

        // Request they select a project to enter votes for, check that they have created projects.
        System.out.println("\tPlease choose a project from the projects list: \n");
        if (SplitIt.projectList == null || SplitIt.projectList.size() == 0){System.out.println("\tI'm afraid you haven't entered any projects yet. :(");
        } else {
            // Iterate over projects in the list of projects.
            for (int i = 0; i < SplitIt.projectList.size(); i++){
                System.out.println("\t\t" + (i+1) + ") " + SplitIt.projectList.get(i).returnName());
            }
        }
        System.out.print("\n\tYour choice: "); // They enter their choice after this prommpt
        int choice = integerInputValidation(1,SplitIt.projectList.size(), ""); // Scan next integer while checking it's valid input, see method definition for more information
        Project chosenProject = SplitIt.projectList.get(choice-1);
        System.out.println("\tThere are " + chosenProject.returnNoOfMembers() + " members in this group.");

        // Get project data. (Member names and number of members)
        int noOfMembers = chosenProject.returnNoOfMembers();
        String memberNames[] = chosenProject.returnMemberNames();
        int[][] projectsVotes = chosenProject.returnProjectVotes();

        for (int votingMember = 0; votingMember < noOfMembers; votingMember++){

            System.out.println("\n\tEnter " + memberNames[votingMember] + "'s  votes, points must add up to 100: ");

            int noOfMembersVotedOn = 0; // Keeps track of the number of members the current member has voted on. (Used to ensure the final vote adds up to 100)
            int remainingPoints = 100; // Keeps track of the remaining points to ensure user can not enter more than 100.

            // Iterate over number of members and obtain their votes.
            for (int memberVotedOn = 0; memberVotedOn < noOfMembers; memberVotedOn++){
                // Prevent the member from voting on themselves.
                if (memberVotedOn != votingMember){
                    // If this is the last member to be voted on, ensure that the entered value is the remaining points.
                    noOfMembersVotedOn++;
                    if (noOfMembersVotedOn == (noOfMembers-1)){
                        // If there is only one vote remaining, automatically set it to the remaining points.
                        projectsVotes[votingMember][memberVotedOn] = remainingPoints;
                        System.out.print("\t\tEnter " + memberNames[votingMember] + "'s vote for " + memberNames[memberVotedOn] + ": " + remainingPoints);
                        System.out.println(" (Vote set automatically to remaining points!)");
                    } else {
                        // Request for vote
                        System.out.print("\t\tEnter " + memberNames[votingMember] + "'s vote for " + memberNames[memberVotedOn] + ": ");
                        int currentVote = integerInputValidation(0,remainingPoints, "\t\tPlease enter a vote between " + 0 + " and " + remainingPoints + ": ");
                        projectsVotes[votingMember][memberVotedOn] = currentVote; // Set the vote the user selects to the appropriate point in the array.
                        remainingPoints  -= currentVote; // Update the number of remaining points.
                    }
                }
            }
        }

        System.out.println();
        SplitIt.storeProjectsToFile();
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
    // Validates scanned input as being integer. If not valid, requests input until it is.
    //-----------------------------------------------------------------------------------------------------
    private int integerInputValidation(int minVal, int maxVal, String errorMessage){
        boolean inputValid = false;
        int inputInt = 0;
        while(!inputValid){
            try{
                inputInt =  scan.nextInt();
                scan.nextLine(); // Scan any remaining new line characters in cache.
                if (inputInt >= minVal && inputInt <= maxVal){
                    inputValid = true;
                } else {
                    System.out.print(errorMessage);
                }
            } catch(InputMismatchException ime) {
                scan.nextLine(); // Scan any remaining new line characters in cache.
                System.out.print("\t\tInvalid input, please enter an integer: ");
            }
        }
        return inputInt;
    }

    //-----------------------------------------------------------------------------------------------------
    // Runs exit sequence. Saves projects array to JSON file and sets exit boolean to true.
    //-----------------------------------------------------------------------------------------------------
    public void exit(){
        SplitIt.storeProjectsToFile();
        this.exit = true;
    }

}
