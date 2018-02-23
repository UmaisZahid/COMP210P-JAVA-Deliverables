package Deliverable1;

public class Project {

    //---------------------------------------------------------
    // Initialise Project related fields
    //---------------------------------------------------------
    private String name;
    private int noOfMembers;
    private String[] memberNames;
    private int[][] projectVotes;

    //---------------------------------------------------------
    // Constructor:- Create Project instance.
    //---------------------------------------------------------
    public Project(String name, int noOfMembers, String[] memberNames) {
        this.name = name;
        this.noOfMembers = noOfMembers;
        this.memberNames = memberNames;
        this.projectVotes = new int[noOfMembers][noOfMembers];
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
    public String[] returnMemberNames(){
        return this.memberNames;
    }

    //---------------------------------------------------------
    // Returns array of arrays containing member votes. First index: voting member, second index: member voted on. (getter method)
    //---------------------------------------------------------
    public int[][] returnProjectVotes(){
        return this.projectVotes;
    }



}
