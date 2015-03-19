package solution.Controller;

import scotlandyard.*;
import solution.Model.ScotlandYardModel;
import solution.View.View;

import java.util.ArrayList;
import java.util.List;

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
        updateTicketValues();
        checkTurn();
    }

    public void checkTurn() {
        theView.setCurrentPlayer(theModel.getCurrentPlayer());
    }

    public void showCurrentValidMoves () {
        theModel.getValidMoves(theModel.getCurrentPlayer());
    }

    public void showTicketValidMoves (Ticket ticket) {
        List<Move> moveList = new ArrayList<Move>();

        for (Move move : theModel.getValidMoves(theModel.getCurrentPlayer())) {
            if (move instanceof MoveTicket){
                if (((MoveTicket) move).ticket.equals(ticket)) {
                    moveList.add(move);
                }
            }
        }
    }

    public void playMove (Move move){
        theModel.makeMove(move);
    }

    public void updateTicketValues () {
        for (Colour colour : theModel.getPlayers()) {
            theView.updateTicketValues(colour, theModel.getColourGamePlayerMap().get(colour).getTickets());

        }
    }




}
