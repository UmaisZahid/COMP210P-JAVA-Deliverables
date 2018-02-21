package Deliverable1;

import java.util.ArrayList;

public class Project {

    //---------------------------------------------------------
    // Initialise Project related fields
    //---------------------------------------------------------
    private String name;
    private int noOfMembers;
    private ArrayList<String> memberNames;
    private ArrayList<ArrayList<Integer>> projectVotes;

    //---------------------------------------------------------
    // Constructor:- Create Project instance.
    //---------------------------------------------------------
    public Project(String name, int noOfMembers, ArrayList<String> memberNames) {
        this.name = name;
        this.noOfMembers = noOfMembers;
        this.memberNames = memberNames;
    }

    //---------------------------------------------------------
    // Returns the number of members. (getter method)
    //---------------------------------------------------------
    public int returnNoOfMembers(){
        return this.noOfMembers;
    }

    //---------------------------------------------------------
    // Returns name of Project. (getter method)
    //---------------------------------------------------------
    public String returnName(){
        return this.name;
    }

    //---------------------------------------------------------
    // Returns array list of members names. (getter method)
    //---------------------------------------------------------
    public ArrayList<String> returnMemberNames(){
        return this.memberNames;
    }



}
