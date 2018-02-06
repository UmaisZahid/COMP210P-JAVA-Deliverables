package Deliverable1;

import javax.naming.Name;
import java.util.ArrayList;

public class project {
    // Initialise fields
    private String name;
    private int noOfMembers;
    private ArrayList<String> memberNames;

    // Constructor method
    public project(String name, int noOfMembers, ArrayList<String> memberNames) {
        this.name = name;
        this.noOfMembers = noOfMembers;
        this.memberNames = memberNames;
    }

    // Returns the number of members. (getter method)
    public int returnNoOfMembers(){
        return this.noOfMembers;
    }

    // Returns name of project. (getter method)
    public String returnName(){
        return this.name;
    }

    // Returns array list of members names. (getter method)
    public ArrayList<String> returnMemberNames(){
        return this.memberNames;
    }
}
