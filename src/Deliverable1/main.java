package Deliverable1;

public class main{

    public static void main(String[] args){
        display currentDisplay = new display();
        currentDisplay.displayMenu();

        while(!(currentDisplay.exit)){
            switch (currentDisplay.option){
                case ('A'): case ('a'): currentDisplay.displayAbout(); break;
                case ('C'): case ('c'): currentDisplay.displayCreateProject(); break;
                case ('V'): case ('v'): currentDisplay.displayEnterVotes(); break;
                case ('S'): case ('s'): currentDisplay.displayShowProjects(); break;
                case ('M'): case ('m'): currentDisplay.displayMenu(); break;
                case ('Q'): case ('q'): currentDisplay.exit = true; return;
            }
        }
    }

}
