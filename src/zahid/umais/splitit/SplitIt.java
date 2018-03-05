package zahid.umais.splitit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SplitIt{

    //----------------------------------------------------------------------
    // Initialise program related fields. Includes: static list of projects and the JSON serializer.
    //----------------------------------------------------------------------
    private static Gson jsonSerializer = new GsonBuilder().setPrettyPrinting().create();
    public static ArrayList<Project> projectList = new ArrayList<Project>();
    private static final String PROJECT_LIST_FILE_NAME = "ListOfProjects.json";
    public static Scanner scan = new Scanner(System.in); // Static scanner instance for input

    //----------------------------------------------------------------------
    // This static function stores the array list of projects in JSON format in a .json file.
    //----------------------------------------------------------------------
    public static void storeProjectsToFile() {
        if (!projectList.isEmpty()) {
            // If project list is not empty, serialize project list to JSON file
            try (FileWriter fileWriter = new FileWriter(PROJECT_LIST_FILE_NAME)) {
                jsonSerializer.toJson(projectList.toArray(), fileWriter);
            } catch (IOException ioe) {
                System.out.println("There was an error storing the projects. :(");
            }
        }
    }

    //----------------------------------------------------------------------
    // This static function reads the array list of projects in JSON format from a .json file.
    //----------------------------------------------------------------------
    private static void readProjectsFromFile(){
        //Deserialize projects from file, if no file exists, catch exception and do nothing.
        try(FileReader fileReader = new FileReader(PROJECT_LIST_FILE_NAME)){
            projectList = new ArrayList<Project>(Arrays.asList(jsonSerializer.fromJson(fileReader, Project[].class)));
        } catch (IOException ioe) {
            // No need to do anything
        }
    }

    //-----------------------------------------------------------------------------------------------------
    // Main Method
    //-----------------------------------------------------------------------------------------------------
    public static void main(String[] args){

        //---------------------------------------------------------
        // Create new Display instance, display menu and populate project array list from JSON file.
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
                case ('H'): case ('h'): currentDisplay.displayChangeVotes(); break;
                case ('S'): case ('s'): currentDisplay.displayShowProjects(); break;
                case ('M'): case ('m'): currentDisplay.displayMenu(); break;
                case ('Q'): case ('q'): currentDisplay.exit(); return; // Set exit boolean to true if input = 'q'
            }
        }
    }

}
