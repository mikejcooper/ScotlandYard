package solution.Controller;

import scotlandyard.*;
import solution.Model.ScotlandYardModel;
import solution.View.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static scotlandyard.Colour.*;

/**
 * Created by MikeCooper on 19/03/15.
 */
public class ControllerUtilities {

    List<MoveTicket> moveTickets;
    List<Move> moveDoubles;
    List<MovePass> movePasses;

    ScotlandYardModel theModel;
    View theView;

    //input the model
    public ControllerUtilities(ScotlandYardModel theModel, View theView){
        this.theModel = theModel;
        this.theView = theView;
        initialiseView();
    }

    private void initialiseView () {
        addPlayersToView();
        checkTurn();
    }

    //initialise Players
    public void addPlayersToView() {
        for (Colour player : theModel.getPlayers()) {
            theView.addPlayer(player, theModel.getColourGamePlayerMap().get(player).getTickets());
        }
        theView.setPlayerPanel();
    }

    //Updates ticket values.
    public void loadTicketValues () {
        for (Colour colour : theModel.getPlayers()) {
            theView.updateTicketValues(colour, theModel.getColourGamePlayerMap().get(colour).getTickets());

        }
    }

    public void playMove (Move move){
        theModel.makeMove(move);
    }

    public void checkTurn() {
        //Ghosts correct tickets based on current player.
        theView.setCurrentPlayer(theModel.getCurrentPlayer());
        ////Updates ticket values.
        loadTicketValues();
        sortMoves();
        showValidTickets(theModel.getValidMoves(theModel.getCurrentPlayer()));
        showCurrentValidMoves(theModel.getValidMoves(theModel.getCurrentPlayer()));
        displayCurrentMoves(theModel.getValidMoves(theModel.getCurrentPlayer()));
        theView.unpressAllButtonsMap();
        theView.goButtonToggle(false);
        theView.unpressAllButtons();

        if(theModel.getValidMoves(theModel.getCurrentPlayer()).iterator().next() instanceof MovePass){
            JOptionPane.showMessageDialog(null, "you can't move please click ok", "InfoBox: " + "Movepass", JOptionPane.INFORMATION_MESSAGE);
            //todo
            //nextPlayer();
        }
        if(theModel.isGameOver()){
            //winners
            Set<Colour> winner = theModel.getWinningPlayers();
            String winners = "";
            for(Colour colour: winner){
                winners = winners + colourToString(colour) + ",";
            }
            JOptionPane.showMessageDialog(null, "the winners are: " + winners, "InfoBox: " + "WINNERS", JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }
    }


    public String colourToString(Colour colour){
        switch(colour){
            case Black: return "Black";

            case Blue: return "Blue";

            case Green: return "Green";

            case Red: return "Red";

            case White: return "White";

            case Yellow: return "Yellow";
        }
        return "that is not a colour";
    }

    public void nextPlayer(){
        theModel.getNextPlayer();
        checkTurn();
    }

    public void showCurrentValidMoves (List<Move> moves) {
        theView.activateAllButtonsMap(false);

        List<MoveTicket> moveTicketsTemp = new ArrayList<MoveTicket>();
        List<MoveDouble> moveDoublesTemp = new ArrayList<MoveDouble>();

        for (Move move : moves) {
            if (move instanceof MoveTicket) {
                moveTicketsTemp.add((MoveTicket) move);
            }
            else if (move instanceof MoveDouble){
                moveDoublesTemp.add((MoveDouble) move);
            }
        }

            showMoveTickets(moveTicketsTemp);
            showMoveDoubles(moveDoublesTemp);
    }

    private void showMoveDoubles(List<MoveDouble> moveDoublesTemp) {
        for (MoveDouble moveDouble : moveDoublesTemp) {
            for (Move move : moveDouble.moves) {
                if (move instanceof MoveTicket){
                    theView.activateSpecificButtonsMap(String.valueOf(((MoveTicket) move).target),true);
                }
            }
        }
    }

    private void showMoveTickets(List<MoveTicket> moveTicketsTemp) {
        for (MoveTicket moveTicket : moveTicketsTemp) {
            theView.activateSpecificButtonsMap(String.valueOf(moveTicket.target),true);
        }
    }


//Functions for searching based on UI.

    //finds all possible moves allowed using this.ticket
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

        if (moves.size() == 0){
            moves.addAll(movePasses);
        }

        return moves;
    }

    public void sortMoves () {
        moveTickets = new ArrayList<MoveTicket>();
        moveDoubles = new ArrayList<Move>();
        movePasses = new ArrayList<MovePass>();
        List<Move> a = theModel.getValidMoves(theModel.getCurrentPlayer());
        for (Move move : theModel.getValidMoves(theModel.getCurrentPlayer())) {
            if (move instanceof MoveTicket) {
                moveTickets.add((MoveTicket) move);
            }
            if (move instanceof MoveDouble){
                moveDoubles.add(move);
            }
            if (move instanceof MovePass){
                movePasses.add((MovePass) move);
            }
        }
    }
    //finds MOVE
    public Move findDoubleMoveTicket (int node1, int node2, Ticket currentTransportTicket, Ticket currentTransportTicket2) {
        Move move = null;
            for (Move moveDouble : moveDoubles) {
                    if (findMoveDouble((MoveDouble) moveDouble, node1, node2, currentTransportTicket, currentTransportTicket2)) {
                        move = moveDouble;
                    }
                }
        return move;
    }


    public void showValidDoubleMoveTicketsFirstMove(List<Move> moves){
        for (Move move : moves) {
            if(move instanceof MoveDouble){
                theView.activateSpecificButtonsPanel(((MoveTicket)((MoveDouble) move).moves.get(0)).ticket.toString(),true,theModel.getCurrentPlayer());
            }
        }
    }

    public void showValidDoubleMoveTicketsSecondMove(List<Move> moves){
        for (Move move : moves) {
            if(move instanceof MoveDouble){
                theView.activateSpecificButtonsPanel(((MoveTicket)((MoveDouble) move).moves.get(1)).ticket.toString(),true,theModel.getCurrentPlayer());
            }
        }
    }

    public void showValidTickets(List<Move> moves){
        theView.activateSpecificButtonsPanelException("nothing",false,theModel.getCurrentPlayer());
        for (Move move : moves) {
            if(move instanceof MoveTicket){
                theView.activateSpecificButtonsPanel(((MoveTicket) move).ticket.toString(),true,theModel.getCurrentPlayer());
            }
            if(move instanceof MoveDouble){
                theView.activateSpecificButtonsPanel("DoubleMove",true,theModel.getCurrentPlayer());
            }
        }
    }



    private Boolean findMoveDouble(MoveDouble moveDouble, int node1, int node2, Ticket currentTransportTicket, Ticket currentTransportTicket2) {
        int check = 0;
        for (Move move : moveDouble.moves) {
            if(move instanceof MoveTicket){
                if (matchMoveTicketToNode((MoveTicket) move, node1, currentTransportTicket)) check++;
                //if (matchMoveTicketToNode((MoveTicket) move, node2, currentTransportTicket)) check++;
                //if (matchMoveTicketToNode((MoveTicket) move, node1, currentTransportTicket2)) check++;
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
    public List<Move> sortMoveDoubleByTicket(int firstOrSendMoveTicket,Ticket methodOfTransport, List<Move> inputDoublesList) {
        List<Move> moves = new ArrayList<Move>();
        for (Move moveDouble : inputDoublesList) {
            if(moveDouble instanceof MoveDouble) {
                MoveTicket move = (MoveTicket) ((MoveDouble) moveDouble).moves.get(firstOrSendMoveTicket);
                if (((MoveTicket) move).ticket == methodOfTransport) {
                    moves.add(moveDouble);
                }
            }
        }
        return moves;
    }



    public void displayCurrentMoves(List<Move> moves) {
        //reset all button to off
        theView.activateAllButtonsMap(false);

        if(moves.size() == 0){
            //"ERROR";
        }
        //activate buttons valid for move
        for (Move move : moves) {
            displayMove(move);
        }
    }


    public void displayMove(Move move) {
        if(move instanceof MoveTicket){
            theView.activateSpecificButtonsMap((String.valueOf(((MoveTicket) move).target)),true);
        }
        else if (move instanceof MoveDouble){
            for (Move move1 : ((MoveDouble) move).moves) {
                if (move1 instanceof MoveTicket){
                    theView.activateSpecificButtonsMap((String.valueOf(((MoveTicket) move1).target)),true);
                }
            }
        }
    }


    public  List<Move> findAllDoubleMoveTicketsWithXNode(String node) {
        List<Move> moves = new ArrayList<Move>();
        for (Move moveDouble : moveDoubles) {
            if (moveDouble instanceof MoveDouble){
                for (Move move : ((MoveDouble) moveDouble).moves) {
                    if (move instanceof MoveTicket){
                        if (((MoveTicket) move).target == Integer.parseInt(node)){
                            moves.add(moveDouble);
                        }
                    }
                }
            }
        }
        return moves;
    }

}
