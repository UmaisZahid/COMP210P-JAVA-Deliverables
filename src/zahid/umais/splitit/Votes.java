package zahid.umais.splitit;

import java.util.HashMap;

public class Votes {
    private boolean votesInitialised = false; // Set to true when votes are entered for the first time
    // projectVotes Stores map of Voter Name to (Votee Name: Vote), Key Value pairs.
    // Example for three members: John, Adam, and Smith
    // {
    // {John: {Adam: Vote}, {Smith: Vote}}
    // {Adam: {John: Vote}, {Smith: Vote}}
    // {Smith: {John: Vote}, {Adam: Vote}}
    // }
    private HashMap<String, HashMap<String,Integer>> projectVotes;
    private HashMap<String,Integer> allocatedVotes; //HashMap of allocated votes
    private int noOfMembers;
    private String[] memberNames;

    //---------------------------------------------------------
    // Constructor:- Create Votes instance.
    //---------------------------------------------------------
    Votes (int noOfMembers, String[] memberNames){

        this.noOfMembers = noOfMembers;
        this.memberNames = memberNames;
        this.allocatedVotes = new HashMap<String,Integer>();
        // Our project votes container is a hashmap containing a hashmap of String and Integer pairs.
        // This corresponds to voter Names, votee Names, and votes.
        this.projectVotes = new HashMap<String, HashMap<String,Integer>>();

        // Iterate over voter names and create an entry in the HashMap
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
    // Setter:- Sets single vote
    //---------------------------------------------------------
    public void setVote(String voterName, String voteeName, int vote){
        this.projectVotes.get(voterName).put(voteeName,vote);
    }

    //---------------------------------------------------------
    // Getter:- Returns single vote
    //---------------------------------------------------------
    public int getVote(String voterName, String voteeName){
        return this.projectVotes.get(voterName).get(voteeName);
    }

    //---------------------------------------------------------
    // Setter:- Sets votesInitialised boolean to true.
    //---------------------------------------------------------
    public void setVotesInitialised(){
        this.votesInitialised = true;
    }

    //---------------------------------------------------------
    // Getter:- Returns votesInitialised boolean
    //---------------------------------------------------------
    public boolean areVotesInitialised(){
        return this.votesInitialised;
    }

    //---------------------------------------------------------
    // Getter:- Returns project votes HashMap.
    //---------------------------------------------------------
    public HashMap<String, HashMap<String,Integer>> getProjectVotes(){
        return this.projectVotes;
    }

    //---------------------------------------------------------
    // Getter:- Returns allocatedVotes array
    //---------------------------------------------------------
    public HashMap<String, Integer> getAllocatedVotes() {
        return this.allocatedVotes;
    }

    //---------------------------------------------------------
    // Setter:- Calculate and allocates final votes
    //---------------------------------------------------------
    public void calculateAllocatedVotes(){
        // Iterate over each member and calculate their associated vote
        for (int i = 0; i < this.noOfMembers; i++){
            float calculatedVote = 0; // Store calculated vote
            for (int j = 0; j < this.noOfMembers; j++){
                // Find the ratio: r^(j)((3-i-j)/j)
               // The 3-i-j, ensures that we pick the right individual to do the ratio. 3 comes from the sum of 0 + 1 + 2.
                if (i!=j){
                    calculatedVote += ((float)getVote(memberNames[j],memberNames[3-i-j]))/((float)getVote(memberNames[j],memberNames[i]));
                }
            }
            // Complete calculation of fraction: 1/(1 + ratios)
            // Multiply decimal by 100 to get percentage
            calculatedVote = Math.round(100*(1/(1+calculatedVote)));

            // Assign the allocated vote to the correct member
            this.allocatedVotes.put(memberNames[i],(int) calculatedVote);
        }

    }
}
