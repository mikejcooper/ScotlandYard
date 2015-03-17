package solution;

import scotlandyard.ScotlandYard;
import solution.Controller.Controller;
import solution.View.View;
import solution.Model.ScotlandYardModel;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class MVCScotlandYard {


    public static void main(String[] args) {

        //this.theModel = ScotlandYardModel();
        View theView = new View();


        Controller theController = new Controller();
        theView.setControllerPrivileges(theController);

    }



    public void runApp() {



    }
}
