package Deliverable1;

public class main{

    public static void main(String[] args){
        // Create new display instance.
        display currentDisplay = new display();
        currentDisplay.displayMenu(); // Display menu

        // Main execution block: keep programming running until exit boolean is true.
        while(!(currentDisplay.exit)){
            // Check character of instance variable option and display relevant page.
            switch (currentDisplay.option){
                case ('A'): case ('a'): currentDisplay.displayAbout(); break;
                case ('C'): case ('c'): currentDisplay.displayCreateProject(); break;
                case ('V'): case ('v'): currentDisplay.displayEnterVotes(); break;
                case ('S'): case ('s'): currentDisplay.displayShowProjects(); break;
                case ('M'): case ('m'): currentDisplay.displayMenu(); break;
                case ('Q'): case ('q'): currentDisplay.exit = true; return; // Set exit boolean to true
            }
        }
    }

}
