package solution.Controller;
import scotlandyard.Colour;
import scotlandyard.MoveTicket;
import scotlandyard.ScotlandYard;
import scotlandyard.Ticket;
import solution.Controller.Interfaces.ControllerButtonListener;
import solution.Model.ScotlandYardModel;
import solution.View.View;

/**
 * Created by MikeCooper on 17/03/15.
 */
public class Controller implements ControllerButtonListener {


    ScotlandYardModel theModel;
    View theView;
    ModelController theModelController;

    //input the model
    public Controller (ScotlandYardModel theModel, View theView){
        this.theModel = theModel;
        this.theView = theView;
        this.theModelController = new ModelController(theModel,theView);
        theView.setControllerPrivileges(this);
    }


    @Override
    public void taxiTicketUsed(String playerName) {
        if (playerName.equals("Black")){

        }
        else {
            //validMoves for current player
            //wait - Ticket select
            //validMovesForTicket
            //wait - map button.
            //play (move ticket)
            //update current location
            //update current player
        }
        MoveTicket move = new MoveTicket(Colour.Blue,2, Ticket.Bus);
        theModelController.playMove(move);
        theModelController.updateTicketValues();
        theModel.getNextPlayer();
        theModelController.checkTurn();

        System.out.println("Received at Controller taxi " + playerName);
    }

    @Override
    public void busTicketUsed(String playerName) {
        System.out.println("Received at Controller bus " + playerName);
    }

    @Override
    public void UndergroundTicketUsed(String playerName) { System.out.println("Received at Controller underground " + playerName); }

    @Override
    public void doubleMoveTicketUsed(String playerName) {
        System.out.println("Received at Controller doublemove " + playerName);
    }

    @Override
    public void secretMoveTicketUsed(String playerName) {
        System.out.println("Received at Controller secret " + playerName);
    }


}
