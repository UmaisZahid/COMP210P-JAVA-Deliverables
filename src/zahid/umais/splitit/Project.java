package zahid.umais.splitit;

import java.util.HashMap;
import static zahid.umais.splitit.Display.integerInputValidation; // Import static instance of integer input validation


public class Project {

    //---------------------------------------------------------
    // Initialise Project related fields
    //---------------------------------------------------------
    private String name;
    private int noOfMembers;
    private String[] memberNames;
    // Stores map of Voter Name to (Votee Name: Vote), Key Value pairs.
    // Example for three members: John, Adam, and Smith
    // {
    // {John: {Adam: Vote}, {Smith: Vote}}
    // {Adam: {John: Vote}, {Smith: Vote}}
    // {Smith: {John: Vote}, {Adam: Vote}}
    // }
    private Votes votes;


    //---------------------------------------------------------
    // Constructor:- Create Project instance.
    //---------------------------------------------------------
    Project(String name, int noOfMembers, String[] memberNames) {
        // Set instance variables
        this.name = name;
        this.noOfMembers = noOfMembers;
        this.memberNames = memberNames;
        this.votes = new Votes(noOfMembers, memberNames);

    }

    //---------------------------------------------------------
    // Getter:- Returns the number of members.
    //---------------------------------------------------------
    public int getNoOfMembers(){
        return this.noOfMembers;
    }

    //---------------------------------------------------------
    // Getter:- Returns name of Project.
    //---------------------------------------------------------
    public String getName(){
        return this.name;
    }

    //---------------------------------------------------------
    // Getter:- Returns votes intialised boolean.
    //---------------------------------------------------------
    public boolean areVotesInitialised(){
        return this.votes.areVotesInitialised();
    }

    //---------------------------------------------------------
    // Getter:- Returns array list of members names.
    //---------------------------------------------------------
    public String[] getMemberNames(){
        return this.memberNames;
    }
    //---------------------------------------------------------
    // Getter:- Returns members votes in the form of a HashMap of Strings and HashMaps. (VoterName: (VoteeName: Vote))
    //---------------------------------------------------------
    public HashMap<String,HashMap<String,Integer>> getProjectVotes(){
        return this.votes.getProjectVotes();
    }

    //---------------------------------------------------------
    // Getter:- Calculate and allocates final votes
    //---------------------------------------------------------
    public HashMap<String,Integer> getAllocatedVotes(){
        return this.votes.getAllocatedVotes();
    }


    //---------------------------------------------------------
    // Requests and sets votes for a single member. Method separated to reduce repeated code
    //---------------------------------------------------------
    public void setSingleMemberVotes(String votingMember){
        int remainingPoints = 100; // Keeps track of the number of points left to be assigned
        int noOfMembersVotedOn = 0; // Used to automatically enter the remaining points if there is only one member remaining

        for (int memberVotedOn = 0; memberVotedOn < noOfMembers; memberVotedOn++){
            // Prevent the member from voting on themselves.
            if (!memberNames[memberVotedOn].equals(votingMember)){

                // If this is the last member to be voted on, ensure that the entered value is the remaining points.
                noOfMembersVotedOn++;
                if (noOfMembersVotedOn == (noOfMembers-1)){
                    // If there is only one vote remaining, automatically set it to the remaining points.
                    // The line below sets the vote from the votingMember to the memberVotedOn with the remaining points.
                    this.votes.setVote(votingMember,memberNames[memberVotedOn],remainingPoints);
                    System.out.print("\t\tEnter " + votingMember + "'s vote for " + memberNames[memberVotedOn] + ": " + remainingPoints);
                    System.out.println(" (Vote set automatically to remaining points!)");
                } else {
                    // Otherwise request for vote
                    System.out.print("\t\tEnter " + votingMember + "'s vote for " + memberNames[memberVotedOn] + ": ");
                    int currentVote = integerInputValidation(0,remainingPoints, "Please enter a vote between " + 0 + " and " + remainingPoints + ": ",2);

                    // The line below sets the vote from the votingMember to the memberVotedOn with the vote provided
                    this.votes.setVote(votingMember,memberNames[memberVotedOn],currentVote); // Get votes array from hashmap and update vote
                    remainingPoints  -= currentVote; // Update the number of remaining points.
                }
            }
        }
    }


    //---------------------------------------------------------
    // Setter:- Request and set votes for this project instance
    //---------------------------------------------------------
    public void requestVotes(){
        System.out.println("\tYou are assigning for " + this.name + "!");
        System.out.println("\tThere are " + this.noOfMembers + " members in this group.");

        // Iterate over the members and request votes for each of them
        for (int votingMember = 0; votingMember < this.noOfMembers; votingMember++){

            System.out.println("\n\tEnter " + this.memberNames[votingMember] + "'s  votes, points must add up to 100: ");

            this.setSingleMemberVotes(this.memberNames[votingMember]);
        }

        this.votes.setVotesInitialised(); // Set votes initialised boolean to true
        this.votes.calculateAllocatedVotes();
        System.out.println("\n\t\t\t\t\tVOTES SUCCESSFULLY SET!");

    }

    //---------------------------------------------------------
    // Setter:- Change the votes from a particular individual
    //---------------------------------------------------------
    public void changeVotes(){
        // Check if votes have been entered for this project
        if (!areVotesInitialised()){
            System.out.println("\tYou have not entered any votes for this project!");
            System.out.println("\tPlease enter votes from the main menu.");
            return; // Return if no votes entered
        } else {
            System.out.println("\tThere are " + this.noOfMembers + " members in this group.");
            System.out.println("\tPlease select which member you are:\n ");
        }

        // Print member names
        for (int i = 0; i < noOfMembers; i++){
            System.out.println("\t\t" + (i+1) + ") " + this.memberNames[i]);
        }

        // Scan their choice of member and validate the input
        System.out.print("\n\tYour choice: ");
        int choice = integerInputValidation(1,noOfMembers, "Please enter a value between " + 1 + " and " + noOfMembers,1);
        String votingMember = memberNames[choice-1]; // Arrays start at 0

        // Set the vote of the member chosen
        setSingleMemberVotes(votingMember);

        // Recalculate Allocated Votes
        this.votes.calculateAllocatedVotes();
        System.out.println("\n\t\t\t\t\tVOTES SUCCESSFULLY UPDATED!");

    }

}
