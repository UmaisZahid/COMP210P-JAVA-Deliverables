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
    private boolean votesInitialised = false; // Set ot true when votes are entered for the first time
    // Stores map of Voter Name to (Votee Name: Vote), Key Value pairs.
    // Example for three members: John, Adam, and Smith
    // {
    // {John: {Adam: Vote}, {Smith: Vote}}
    // {Adam: {John: Vote}, {Smith: Vote}}
    // {Smith: {John: Vote}, {Adam: Vote}}
    // }
    private HashMap<String, HashMap<String,Integer>> projectVotes;
    private HashMap<String,Integer> allocatedVotes;


    //---------------------------------------------------------
    // Constructor:- Create Project instance.
    //---------------------------------------------------------
    Project(String name, int noOfMembers, String[] memberNames) {
        this.name = name;
        this.noOfMembers = noOfMembers;
        this.memberNames = memberNames;
        this.allocatedVotes = new HashMap<String,Integer>();

        // Our project votes container is a hashmap containing a hashmap of String and Integer pairs.
        // This corresponds to voter Names, votee Names, and votes.
        this.projectVotes = new HashMap<String, HashMap<String,Integer>>();
        for (String voterName: memberNames){
            projectVotes.put(voterName, new HashMap<String,Integer>()); // Create map/dictionary of votes for each member
            for (String voteeName: memberNames){
                if(!voteeName.equals(voterName)){ // They can not vote on themselves
                    projectVotes.get(voterName).put(voteeName, 0); // Initially all votes are set to 0
                }

            }
        }
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
        return this.votesInitialised;
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
        return this.projectVotes;
    }

    //---------------------------------------------------------
    // Getter:- Calculate and allocates final votes
    //---------------------------------------------------------
    public HashMap<String,Integer> getAllocatedVotes(){
        return this.allocatedVotes;
    }




    //---------------------------------------------------------
    // Setter:- Request and set votes for this project instance
    //---------------------------------------------------------
    public void requestVotes(){
        System.out.println("\tYou are voting for " + this.name + "!");
        System.out.println("\tThere are " + this.noOfMembers + " members in this group.");

        // Iterate over the members and request votes for each of them
        for (int votingMember = 0; votingMember < this.noOfMembers; votingMember++){

            System.out.println("\n\tEnter " + this.memberNames[votingMember] + "'s  votes, points must add up to 100: ");

            int remainingPoints = 100; // Keeps track of the number of points left to be assigned
            int noOfMembersVotedOn = 0; // Used to automatically enter the remaining points if there is only one member remaining

            // Iterate over members being voted on
            for (int memberVotedOn = 0; memberVotedOn < noOfMembers; memberVotedOn++){
                // Prevent the member from voting on themselves.
                if (memberVotedOn != votingMember){

                    // If this is the last member to be voted on, ensure that the entered value is the remaining points.
                    noOfMembersVotedOn++;
                    if (noOfMembersVotedOn == (noOfMembers-1)){
                        // If there is only one vote remaining, automatically set it to the remaining points.
                        // The line below sets the vote from the votingMember to the memberVotedOn with the remaining points.
                        this.projectVotes.get(memberNames[votingMember]).put(memberNames[memberVotedOn],remainingPoints);
                        System.out.print("\t\tEnter " + memberNames[votingMember] + "'s vote for " + memberNames[memberVotedOn] + ": " + remainingPoints);
                        System.out.println(" (Vote set automatically to remaining points!)");
                    } else {
                        // Otherwise request for vote
                        System.out.print("\t\tEnter " + memberNames[votingMember] + "'s vote for " + memberNames[memberVotedOn] + ": ");
                        int currentVote = integerInputValidation(0,remainingPoints, "Please enter a vote between " + 0 + " and " + remainingPoints + ": ",2);

                        // The line below sets the vote from the votingMember to the memberVotedOn with the vote provided
                        this.projectVotes.get(memberNames[votingMember]).put(memberNames[memberVotedOn],currentVote); // Get votes array from hashmap and update vote
                        remainingPoints  -= currentVote; // Update the number of remaining points.

                    }
                }
            }
        }

        this.votesInitialised = true; // Set votes initialised boolean to true
        calculateAllocatedVotes();
        System.out.println("\n\t\t\t\t\tVOTES SUCCESSFULLY SET!");

    }

       //---------------------------------------------------------
    // Setter:- Change the votes from a particular individual
    //---------------------------------------------------------
    public void changeVotes(){
        // Check if votes have been entered for this project
        if (!votesInitialised){
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

        int remainingPoints = 100;  // Keeps track of the number of points left to be assigned
        int noOfMembersVotedOn = 0; // Keeps track of the number of members voted on, if 1 member remaining set votes automatically
        for (String memberVotedOn : projectVotes.get(votingMember).keySet()){
            noOfMembersVotedOn++;
            if (noOfMembersVotedOn == (noOfMembers-1)){
                // If there is only one vote remaining, automatically set it to the remaining points.
                // The line below sets the vote from the votingMember to the memberVotedOn with the remaining points.
                this.projectVotes.get(votingMember).put(memberVotedOn,remainingPoints);
                System.out.print("\t\tEnter " + votingMember + "'s vote for " + memberVotedOn + ": " + remainingPoints);
                System.out.println(" (Vote set automatically to remaining points!)");
            } else {
                // Otherwise request for vote
                System.out.print("\t\tEnter " + votingMember + "'s vote for " + memberVotedOn + ": ");
                int currentVote = integerInputValidation(0,remainingPoints, "Please enter a vote between " + 0 + " and " + remainingPoints + ": ",2);

                // The line below sets the vote from the votingMember to the memberVotedOn with the vote provided
                this.projectVotes.get(votingMember).put(memberVotedOn,currentVote); // Get votes array from hashmap and update vote
                remainingPoints  -= currentVote; // Update the number of remaining points.

            }
        }

        calculateAllocatedVotes();
        System.out.println("\n\t\t\t\t\tVOTES SUCCESSFULLY UPDATED!");

    }

    //---------------------------------------------------------
    // Setter:- Calculate and allocates final votes
    //---------------------------------------------------------
    private void calculateAllocatedVotes(){

        for (int i = 0; i < noOfMembers; i++){
            float calculatedVote = 0;
            for (int j = 0; j < noOfMembers; j++){
                if (i!=j){
                    calculatedVote += ((float)projectVotes.get(memberNames[j]).get(memberNames[3-i-j]))/((float)projectVotes.get(memberNames[j]).get(memberNames[i]));
                }
            }
            calculatedVote = 100*(1/(1+calculatedVote));

            allocatedVotes.put(memberNames[i],(int) calculatedVote);
        }


    }



}
