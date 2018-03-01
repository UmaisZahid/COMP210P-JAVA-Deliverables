package Deliverable1;

import java.util.HashMap;

public class Project {

    //---------------------------------------------------------
    // Initialise Project related fields
    //---------------------------------------------------------
    private String name;
    private int noOfMembers;
    private String[] memberNames;
    private HashMap<String, HashMap<String,Integer>> projectVotes;

    //---------------------------------------------------------
    // Constructor:- Create Project instance.
    //---------------------------------------------------------
    Project(String name, int noOfMembers, String[] memberNames) {
        this.name = name;
        this.noOfMembers = noOfMembers;
        this.memberNames = memberNames;

        // Our project votes container is a hashmap containing a hashmap of String and Integer pairs.
        // This corresponds to voter Names, votee Names, and votes.
        this.projectVotes = new HashMap<String, HashMap<String,Integer>>();
        for (String voterName: memberNames){
            projectVotes.put(voterName, new HashMap<String,Integer>()); // Create array of votes for each member
            for (String voteeName: memberNames){
                if(!voteeName.equals(voterName)){ // THey can not vote on themselves
                    projectVotes.get(voterName).put(voteeName, 0); // Initially all votes are set to 0
                }

            }
        }
    }

    //---------------------------------------------------------
    // Getter:- Returns the number of members.
    //---------------------------------------------------------
    public int returnNoOfMembers(){
        return this.noOfMembers;
    }

    //---------------------------------------------------------
    // Getter:- returns name of Project.
    //---------------------------------------------------------
    public String returnName(){
        return this.name;
    }

    //---------------------------------------------------------
    // Getter:- Returns array list of members names.
    //---------------------------------------------------------
    public String[] returnMemberNames(){
        return this.memberNames;
    }

    //---------------------------------------------------------
    // Getter:- Returns members votes in the form of a HashMap of Strings and HashMaps. (VoterName: (VoteeName: Vote))
    //---------------------------------------------------------
    public HashMap<String,HashMap<String,Integer>> returnProjectVotes(){
        return this.projectVotes;
    }



}
