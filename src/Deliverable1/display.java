package Deliverable1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class display {
    // Initialise display/menu related fields.
    private static Scanner scan = new Scanner(System.in);
    private static List<Character> menuOptions = Arrays.asList('A', 'a', 'V', 'v', 'C', 'c', 'S', 's', 'Q', 'q','m','M');
    public char option; // Variable containing current menu option choice
    public boolean exit = false; // Boolean to determine whether program should exit
    private static String aboutTextPara1 = "Split-it was designed to help teams allocate credit for a project fairly. " +
            "It works by collecting votes for a project, and allocating marks between team members.";
    private static String aboutTextPara2 = "This idea was originally inspired by the work of Ariel Procaccia and Jonathan Goldman's programmed called " +
            "Spliddit, which offers fair solutions for numerous division problems, including rent payments, restaurant" +
            " bills and shared tasks. To run this programme, one needs to create and register a project, collect votes " +
            "from each individual team member and the programme will calculate and display the allocated votes for the project. ";

    public void displayMenu(){

        System.out.println("\tWelcome to Split-it");
        System.out.println();
        System.out.println("\t\tAbout (A)");
        System.out.println("\t\tCreate Project (C)");
        System.out.println("\t\tEnter Votes (V)");
        System.out.println("\t\tShow Project (S)");
        System.out.println("\t\tQuit (Q)");
        System.out.println();
        pickOption();

    }

    private void pickOption(){
        System.out.print("\tPlease choose an option, or enter m to return to the menu: ");
        option = scan.next().charAt(0);

        while (!(menuOptions.contains(option))){
            System.out.println();
            System.out.print("\t\tInvalid Input, please enter again: ");
            option = scan.next().charAt(0);
        }
        System.out.println();
    }

    public void displayAbout(){
        System.out.println();
        System.out.println("\t\t\t\tABOUT: Split-It");
        System.out.println();
        formatAndPrint(aboutTextPara1, 7);
        System.out.println();
        formatAndPrint(aboutTextPara2, 7);
        System.out.println();
        System.out.println();

        pickOption();
    }

    public void displayCreateProject(){
        System.out.println("\t\t\t\tCREATE PROJECT\n");
        System.out.print("\tPlease provide a project name: ");
        scan.next();
        String projectName = scan.nextLine();
        System.out.print("\tPlease provide the number of members: ");
        int noMembers = scan.nextInt();
        ArrayList<String> memberNames = new ArrayList<>();

        for(int i = 0; i<noMembers; i++){
            System.out.print("\tPlease provide the name of member " + (i+1) + ": ");
            scan.next();
            memberNames.add(scan.nextLine());
        }

        project newProject = new project(projectName,noMembers,memberNames);
        System.out.println("\n\t\t\t\tPROJECT CREATED!\n");
        pickOption();
    }

    public void displayEnterVotes(){
        System.out.println("\t\tThis is the enter votes pages");
        option = 'M';
    }

    public void displayShowProjects(){
        System.out.println("\t\tThis is the show project page");
        option = 'M';
    }

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
