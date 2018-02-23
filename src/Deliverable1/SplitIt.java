package Deliverable1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SplitIt{

    //----------------------------------------------------------------------
    // Initialise program related fields. Includes: static list of projects and the JSON serializer.
    //----------------------------------------------------------------------
    private static Gson jsonSerializer = new GsonBuilder().setPrettyPrinting().create();
    public static ArrayList<Project> projectList = new ArrayList<Project>();

    //----------------------------------------------------------------------
    // Static function to store array list of projects in JSON format in .json file.
    //----------------------------------------------------------------------
    public static void storeProjectsToFile(){
        if (projectList == null){
            // Nothing to store
        } else {
            // Serialize projects to file
            try(FileWriter fileWriter = new FileWriter("ListOfProjects.json")){
                jsonSerializer.toJson(projectList.toArray(), fileWriter);
            } catch (IOException ioe) {
                System.out.println("There was an error storing the projects. :(");
            }
        }
    }

    //----------------------------------------------------------------------
    // Static function to create array list of projects from JSON format in .json file.
    //----------------------------------------------------------------------
    public static void readProjectsFromFile(){
        // Deserialize projects from file
        try(FileReader fileReader = new FileReader("ListOfProjects.json")){
            projectList = new ArrayList<Project>(Arrays.asList(jsonSerializer.fromJson(fileReader, Project[].class)));
        } catch (IOException ioe) {
        }
    }


    public static void main(String[] args){
        //---------------------------------------------------------
        // Create new Display instance, display menu and populate project array list.
        //---------------------------------------------------------
        Display currentDisplay = new Display();
        currentDisplay.displayMenu();
        readProjectsFromFile();

        //-------------------------------------------------------------------------
        // Main execution block: keeps program running until exit boolean is true.
        //-------------------------------------------------------------------------
        while(!(currentDisplay.exit)){
            // Check what currentDisplay variable option is currently set to and Display relevant page.
            switch (currentDisplay.option){
                case ('A'): case ('a'): currentDisplay.displayAbout(); break;
                case ('C'): case ('c'): currentDisplay.displayCreateProject(); break;
                case ('V'): case ('v'): currentDisplay.displayEnterVotes(); break;
                case ('S'): case ('s'): currentDisplay.displayShowProjects(); break;
                case ('M'): case ('m'): currentDisplay.displayMenu(); break;
                case ('Q'): case ('q'): currentDisplay.exit(); return; // Set exit boolean to true if input = 'q'
            }
        }
    }

}
