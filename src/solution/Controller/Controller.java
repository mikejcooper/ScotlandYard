package solution.Controller;
import scotlandyard.*;
import solution.Controller.Interfaces.ControllerButtonListener;
import solution.Model.ScotlandYardModel;
import solution.View.View;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
    ModelController theModelController;
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
        this.theModelController = new ModelController(theModel,theView);
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
            theView.clearConsole();
            currentTransportTicket = Ticket.Taxi;

            List<Move> moves = theModelController.findMoves(Ticket.Taxi);

            if (moves.size() == 0) {
                theView.printConsole("No Taxi Tickets availible");
            } else {
                theView.currentMoves(moves);
            }
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
            theView.clearConsole();
            currentTransportTicket = Ticket.Bus;

            List<Move> moves = theModelController.findMoves(Ticket.Bus);
            if (moves.size() == 0) {
                theView.printConsole("No Bus Tickets availible");
            } else {
                theView.currentMoves(moves);
            }
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
            theView.clearConsole();
            currentTransportTicket = Ticket.Underground;

            List<Move> moves = theModelController.findMoves(Ticket.Underground);

            if (moves.size() == 0) {
                theView.printConsole("No Underground Tickets availible");
            } else {
                theView.currentMoves(moves);
            }
        }
    }

    @Override
    public void doubleMoveTicketUsed(String playerName) {
        System.out.println("Received at Controller doublemove " + playerName);

            goButtonLatch = 1;
            theView.clearConsole();
            currentTransportTicket = Ticket.DoubleMove;

            List<Move> moves = theModelController.findMoves(Ticket.DoubleMove);

            if (moves.size() == 0) {
                theView.printConsole("No Taxi Tickets availible");
            } else {
                theView.currentMoves(moves);
            }
    }

    @Override
    public void secretMoveTicketUsed(String playerName) {
        System.out.println("Received at Controller secret " + playerName);

        if(currentTransportTicket.equals(Ticket.DoubleMove) && goButtonLatch != 3){
            doubleMoveException(Ticket.SecretMove);
        }
        else {
            goButtonLatch = 1;
            theView.clearConsole();
            currentTransportTicket = Ticket.SecretMove;


            List<Move> moves = theModelController.findMoves(Ticket.SecretMove);

            if(moves.size() == 0){
                theView.printConsole("No Taxi Tickets availible");
            }
            else{
                theView.currentMoves(moves);
            }

        }



    }

    @Override
    public void goButtonUsed(String playerName) {
        System.out.println("button recieved at controller");

        if (goButtonLatch == 0 || goButtonLatch == 2) {
            theModel.makeMove(currentMove);
            theModelController.nextPlayer();
        }
    }

//Node input
    @Override
    public void textInput(String input) {
        if(currentTransportTicket.equals(Ticket.DoubleMove)){
            if (goButtonLatch == 2){
                selectedNode2 = Integer.parseInt(input);
            }
            else if (goButtonLatch == 3){
                selectedNode = Integer.parseInt(input);
            }
        }
        else {
            selectedNode = Integer.parseInt(input);
            currentMove = findMove(selectedNode);
            goButtonLatch = 0;
        }

    }


    public Move findMove(int node1, int node2) {
        return theModelController.findDoubleMoveTicket(node1, node2, currentTransportTicket, currentTransportTicket);
    }

    public Move findMove (int node1) {
        return theModelController.findMoveTicket(node1,currentTransportTicket);
    }

    public void doubleMoveException(Ticket ticket) {
        if (goButtonLatch == 1){
            currentTransportTicket2 = ticket;
            goButtonLatch = 2;
            List<MoveDouble> movesSortedByTicketsNode1 = theModelController.sortMoveDoubleByTicket(0,currentTransportTicket2,theModelController.moveDoubles);
            displayDoubleMoves(movesSortedByTicketsNode1);
        }
        else if (goButtonLatch == 2 || goButtonLatch == 3){
            //CTT1 = first node, CTT2 = second node
            currentTransportTicket3 = ticket;
            currentMove = findMove(selectedNode,selectedNode2);
            List<MoveDouble> movesSortedByTicketsNode1 = theModelController.sortMoveDoubleByTicket(0,currentTransportTicket2,theModelController.moveDoubles);
            List<MoveDouble> movesSortedByTicketsNode1AND2 = theModelController.sortMoveDoubleByTicket(1, currentTransportTicket3, movesSortedByTicketsNode1);
            displayDoubleMoves(movesSortedByTicketsNode1AND2);
            goButtonLatch = 3;
        }
    }


    public void displayDoubleMoves(List<MoveDouble> moves){
            theView.clearConsole();

            if(moves.size() == 0){
                theView.printConsole("No Taxi Tickets availible");
            }
            else{
                theView.currentMovesDoubles(moves);
            }
    }



}


