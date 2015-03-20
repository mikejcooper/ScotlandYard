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

    List<MoveTicket> moveTickets;
    List<MoveDouble> moveDoubles;
    List<MovePass> movePasses;

    ScotlandYardModel theModel;
    View theView;

    //input the model
    public ModelController (ScotlandYardModel theModel, View theView){
        this.theModel = theModel;
        this.theView = theView;
        initialiseView();
    }

    private void initialiseView () {
        addPlayersToView();
        loadTicketValues();
        checkTurn();
        sortMoves();
        showCurrentValidMoves();

    }


    public void addPlayersToView() {
        for (Colour player : theModel.getPlayers()) {
            theView.addPlayer(player, theModel.getColourGamePlayerMap().get(player).getTickets());
        }
        theView.setPlayerPanel();
    }

    public void loadTicketValues () {
        for (Colour colour : theModel.getPlayers()) {
            theView.updateTicketValues(colour, theModel.getColourGamePlayerMap().get(colour).getTickets());

        }
    }

    public void checkTurn() {
        theView.setCurrentPlayer(theModel.getCurrentPlayer());
    }

    public void showCurrentValidMoves () {
        theView.currentMoves(theModel.getValidMoves(theModel.getCurrentPlayer()));

    }


    public List<Move> findMoves (Ticket ticket) {
        List<Move> moves = new ArrayList<Move>();
        if(ticket.name().equals("DoubleMove")) {
            moves.addAll(moveDoubles);
        }
        else {
            for (MoveTicket moveTicket : moveTickets) {
                if (moveTicket.ticket.equals(ticket)){
                    moves.add(moveTicket);
                }
            }
        }

        if(moves.size() == 0){
            moves.addAll(movePasses);
        }
        return moves;
    }

    public void playMove (Move move){
        theModel.makeMove(move);
    }

    public void nextPlayer(){
        loadTicketValues();
        theModel.getNextPlayer();
        checkTurn();
        sortMoves();
        showCurrentValidMoves();
    }



    public void sortMoves () {
        moveTickets = new ArrayList<MoveTicket>();
        moveDoubles = new ArrayList<MoveDouble>();
        movePasses = new ArrayList<MovePass>();
        for (Move move : theModel.getValidMoves(theModel.getCurrentPlayer())) {
            if (move instanceof MoveTicket) {
                moveTickets.add((MoveTicket) move);
            }
            if (move instanceof MoveDouble){
                moveDoubles.add((MoveDouble) move);
            }
            if (move instanceof MovePass){
                movePasses.add((MovePass) move);
            }
        }
    }


    //finds MOVE
    public Move findDoubleMoveTicket (int node1, int node2, Ticket currentTransportTicket, Ticket currentTransportTicket2) {
        Move move = null;
            for (MoveDouble moveDouble : moveDoubles) {
                if (findMoveDouble(moveDouble, node1, node2,currentTransportTicket,currentTransportTicket2)) {
                    move = moveDouble;
                }
            }
        return move;
    }

    private Boolean findMoveDouble(MoveDouble moveDouble, int node1, int node2, Ticket currentTransportTicket, Ticket currentTransportTicket2) {
        int check = 0;
        for (Move move : moveDouble.moves) {
            if(move instanceof MoveTicket){
                if (matchMoveTicketToNode((MoveTicket) move, node1, currentTransportTicket)) check++;
                if (matchMoveTicketToNode((MoveTicket) move, node2, currentTransportTicket)) check++;
                if (matchMoveTicketToNode((MoveTicket) move, node1, currentTransportTicket2)) check++;
                if (matchMoveTicketToNode((MoveTicket) move, node2, currentTransportTicket2)) check++;
            }
        }
        if (check == 2) return true;
        else return false;
    }

    public Move findMoveTicket(int node1, Ticket currentTransportTicket) {
        Move move = null;
        for (MoveTicket moveTicket : moveTickets) {
            if (matchMoveTicketToNode(moveTicket, node1, currentTransportTicket)) {
                move = moveTicket;
            }
        }
        return move;
    }

    private Boolean matchMoveTicketToNode(MoveTicket moveTicket,int node1, Ticket currentTransportTicket) {
            if(moveTicket.target == node1 && moveTicket.ticket == currentTransportTicket){
                 return true;
            }
            return false;
    }



    //could cause rare bug.
    public List<MoveDouble> sortMoveDoubleByTicket(int firstOrSendMoveTicket,Ticket methodOfTransport, List<MoveDouble> inputDoublesList) {
        List<MoveDouble> moves = new ArrayList<MoveDouble>();
        for (MoveDouble moveDouble : inputDoublesList) {
            MoveTicket move = (MoveTicket) moveDouble.moves.get(firstOrSendMoveTicket);
            if (((MoveTicket) move).ticket == methodOfTransport) {
                moves.add(moveDouble);
            }
        }
        return moves;
    }


}
