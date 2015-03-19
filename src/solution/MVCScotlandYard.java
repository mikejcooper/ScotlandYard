package solution;

import scotlandyard.Player;
import scotlandyard.ScotlandYard;
import scotlandyard.Ticket;
import solution.Controller.Controller;
import solution.View.View;
import solution.Model.ScotlandYardModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class MVCScotlandYard {


    public static void main(String[] args) throws IOException {

        //Initial game variables
        int numberOfDetectives = 5;
        String graphFileName = "graph.txt";

        ScotlandYardModel theModel = (ScotlandYardModel) new InitialiseGame(numberOfDetectives,graphFileName).game;


        View theView = new View();
        Controller theController = new Controller(theModel, theView);
        theView.setControllerPrivileges(theController);




    }



    public void setUpGame() {


    }
}
