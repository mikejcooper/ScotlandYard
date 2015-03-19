package solution.Controller;

import scotlandyard.Colour;
import scotlandyard.ScotlandYard;
import solution.Model.ScotlandYardModel;
import solution.View.View;

/**
 * Created by MikeCooper on 19/03/15.
 */
public class ModelController {

    ScotlandYardModel theModel;
    View theView;

    //input the model
    public ModelController (ScotlandYardModel theModel, View theView){
        this.theModel = theModel;
        this.theView = theView;
        initialiseView();
    }

    private void initialiseView () {
        for (Colour player : theModel.getPlayers()) {
            theView.addPlayer(player, theModel.getColourGamePlayerMap().get(player).getTickets());
        }
        checkTurn();
    }

    public void checkTurn() {
        theView.setCurrentPlayer(theModel.getCurrentPlayer());
    }


}
