package solution.Controller;
import scotlandyard.*;
import solution.Controller.Interfaces.ControllerButtonListener;
import solution.Model.ScotlandYardModel;
import solution.View.View;

import java.util.List;

/**
 * Created by MikeCooper on 17/03/15.
 */
public class Controller implements ControllerButtonListener {

    //initial:
        //addPlayers from model. addPlayersToView();
        //loadTicketValues
        //checkTurn = blank other players
        //show all possible moves showCurrentValidMoves
        //sort all possible moves into seperate components  sortMoves();

    //Turn:
        //sort by ticket using ticket buttons (unlimited clicks) - maybe add ShowAll button?
            //process clicking:
                //MoveTicket
                    //Click on ticket - options on board decrease.
                    //Select Node
                    //Go
                //MoveDouble
                    //Click on MoveDouble
                    //Shows Double Moves
                    //Select Ticket to specifiy first move.
                    //Select Node bases on ticket type
                    //show all possible moves from that node.
                    //Select Ticket to specifiy second move.
                    //Go to execute or MoveDouble to reset.

        //Go = Play move
        //Check GAMEOVER?
                //FIND WINNING PLAYER/s

    //Next Player:
        //Model.getnextplayer
        //checkTurn();
        //loadTicketValues();
        //showCurrentValidMoves();
        //sortMoves();


    //CTT != Double
        //currentMove = findMove
        //if Go = play
    //CTT == double
        //displayDouble
        //Select ticket to specify
        //show MoveDouble tickets with move1 = specific ticket



    ScotlandYardModel theModel;
    View theView;
    ControllerUtilities theControllerUtilities;
    private Ticket currentTransportTicket = Ticket.Bus;
    private Ticket currentTransportTicket2 = Ticket.Bus;
    private Ticket currentTransportTicket3 = Ticket.Bus;
    private Move currentMove;
    private Move currentMove2;
    int goButtonLatch = 1; // 0 = Move is ready to 'play', 1 = 'normal views displayed to user', 2 = '1st step in doubleMove, 2 = 2nd step.
    int selectedNode;
    int selectedNode2;

    //input the model
    public Controller (ScotlandYardModel theModel, View theView){
        this.theModel = theModel;
        this.theView = theView;
        this.theControllerUtilities = new ControllerUtilities(theModel,theView);
        theView.setControllerPrivileges(this);
        selectedNode = theModel.getPlayerLocation(theModel.getCurrentPlayer());
    }


    //when clicked, each ticket sorts the list of availble moves by that specific ticket.

    @Override
    public void taxiTicketUsed(String playerName) {
        System.out.println("Received at Controller taxi " + playerName);

        if(currentTransportTicket.equals(Ticket.DoubleMove) && goButtonLatch != 3){
            doubleMoveException(Ticket.Taxi);
        }
        else {
            goButtonLatch = 1;
            currentTransportTicket = Ticket.Taxi;

            List<Move> moves = theControllerUtilities.findMoves(Ticket.Taxi);

            if (moves.size() == 0) {
                //"No Taxi Tickets availible");
            }

            displayCurrentMoves(moves);
        }
    }

    @Override
    public void busTicketUsed(String playerName) {
        System.out.println("Received at Controller bus " + playerName);

        if(currentTransportTicket.equals(Ticket.DoubleMove) && goButtonLatch != 3){
            doubleMoveException(Ticket.Bus);
        }
        else {
            goButtonLatch = 1;
            currentTransportTicket = Ticket.Bus;

            List<Move> moves = theControllerUtilities.findMoves(Ticket.Bus);
            if (moves.size() == 0) {
                //theView.printConsole("No Bus Tickets availible");
            }
            displayCurrentMoves(moves);

        }
    }

    @Override
    public void UndergroundTicketUsed(String playerName) {
        System.out.println("Received at Controller underground " + playerName);

        if(currentTransportTicket.equals(Ticket.DoubleMove) && goButtonLatch != 3){
            doubleMoveException(Ticket.Underground);
        }
        else {
            goButtonLatch = 1;
            currentTransportTicket = Ticket.Underground;

            List<Move> moves = theControllerUtilities.findMoves(Ticket.Underground);

            if (moves.size() == 0) {
                //theView.printConsole("No Underground Tickets availible");
            }
                displayCurrentMoves(moves);

        }
    }

    @Override
    public void doubleMoveTicketUsed(String playerName) {
        System.out.println("Received at Controller doublemove " + playerName);

        goButtonLatch = 1;
        currentTransportTicket = Ticket.DoubleMove;

        List<Move> moves = theControllerUtilities.findMoves(Ticket.DoubleMove);

        if (moves.size() == 0) {
            //theView.printConsole("No Taxi Tickets availible");
        }
        displayCurrentMoves(moves);
    }

    @Override
    public void secretMoveTicketUsed(String playerName) {
        System.out.println("Received at Controller secret " + playerName);

        if(currentTransportTicket.equals(Ticket.DoubleMove) && goButtonLatch != 3){
            doubleMoveException(Ticket.SecretMove);
        }
        else {
            goButtonLatch = 1;
            currentTransportTicket = Ticket.SecretMove;


            List<Move> moves = theControllerUtilities.findMoves(Ticket.SecretMove);

            if(moves.size() == 0){
                //theView.printConsole("No Taxi Tickets availible");
            }

            displayCurrentMoves(moves);
        }



    }

    @Override
    public void goButtonUsed(String playerName) {
        System.out.println("button recieved at controller");

        if (goButtonLatch == 0 || goButtonLatch == 2) {
            theModel.makeMove(currentMove);
            theControllerUtilities.nextPlayer();
        }
    }

    @Override
    public void mapButton(String nodeNumber) {
        System.out.println("controller"+nodeNumber);

        if(currentTransportTicket.equals(Ticket.DoubleMove)){
            if (goButtonLatch == 2){
                selectedNode2 = Integer.parseInt(nodeNumber);
            }
            else if (goButtonLatch == 3){
                selectedNode = Integer.parseInt(nodeNumber);
            }
        }
        else {
            selectedNode = Integer.parseInt(nodeNumber);
            currentMove = findMove(selectedNode);
            goButtonLatch = 0;
        }
    }

    public Move findMove(int node1, int node2) {
        return theControllerUtilities.findDoubleMoveTicket(node1, node2, currentTransportTicket, currentTransportTicket);
    }

    public Move findMove (int node1) {
        return theControllerUtilities.findMoveTicket(node1,currentTransportTicket);
    }

    public void doubleMoveException(Ticket ticket) {
        if (goButtonLatch == 1){
            currentTransportTicket2 = ticket;
            goButtonLatch = 2;
            List<Move> movesSortedByTicketsNode1 = theControllerUtilities.sortMoveDoubleByTicket(0,currentTransportTicket2, theControllerUtilities.moveDoubles);
            displayCurrentMoves(movesSortedByTicketsNode1);
        }
        else if (goButtonLatch == 2 || goButtonLatch == 3){
            //CTT1 = first node, CTT2 = second node
            currentTransportTicket3 = ticket;
            currentMove = findMove(selectedNode,selectedNode2);
            List<Move> movesSortedByTicketsNode1 = theControllerUtilities.sortMoveDoubleByTicket(0,currentTransportTicket2, theControllerUtilities.moveDoubles);
            List<Move> movesSortedByTicketsNode1AND2 = theControllerUtilities.sortMoveDoubleByTicket(1, currentTransportTicket3, movesSortedByTicketsNode1);
            displayCurrentMoves(movesSortedByTicketsNode1AND2);
            goButtonLatch = 3;
        }
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

}


