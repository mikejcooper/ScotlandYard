package solution.Controller;
import scotlandyard.*;
import solution.Controller.Interfaces.ControllerButtonListener;
import solution.InitialiseGame;
import solution.Model.ScotlandYardModel;
import solution.View.GameFrame;
import solution.View.InitFrame;
import solution.View.MapPanel.ButtonHolder;
import solution.View.View;

import java.util.List;
import java.util.Set;

/**
 * Created by MikeCooper on 17/03/15.
 */
public class Controller implements ControllerButtonListener {

    GameFrame gameFrame;
    ScotlandYardModel theModel;
    View theView;
    InitFrame initFrame;
    ControllerUtilities theControllerUtilities;
    int doubleMoveToggle = 0;
    int moveToggle = 0;

    Ticket ticket1 = Ticket.DoubleMove;
    Ticket ticket2 = Ticket.DoubleMove;
    Ticket ticket3 = Ticket.DoubleMove;

    String currentNode1 = "0";
    String currentNode2 = "0";


    //input the model
    public Controller (/*ScotlandYardModel theModel,*/ View theView,InitFrame initFrame,GameFrame gameFrame){
        this.gameFrame = gameFrame;
        this.initFrame = initFrame;
        //this.theModel = theModel;
        this.theView = theView;
        //this.theControllerUtilities = new ControllerUtilities(theModel,theView);
        //theView.setControllerPrivileges(this);
        //selectedNode = theModel.getPlayerLocation(theModel.getCurrentPlayer());
        initFrame.addListener(this);
    }



    @Override
    public void taxiTicketPressed(String playerName) {
        pressedButtonAction(playerName,Ticket.Taxi);
    }

    @Override
    public void busTicketPressed(String playerName) {
        pressedButtonAction(playerName,Ticket.Bus);
    }

    @Override
    public void undergroundTicketPressed(String playerName) {
        pressedButtonAction(playerName,Ticket.Underground);
    }

    @Override
    public void doubleMoveTicketPressed(String playerName) {
        doubleMoveToggle = 1;
        pressedButtonAction(playerName,Ticket.DoubleMove);
    }

    @Override
    public void secretMoveTicketPressed(String playerName) {
        pressedButtonAction(playerName,Ticket.SecretMove);
    }

    @Override
    public void taxiTicketUnpressed(String playerName) {
        unpressedButtonAction(playerName, Ticket.Taxi);
    }

    @Override
    public void busTicketUnpressed(String playerName) {
        unpressedButtonAction(playerName,Ticket.Bus);
    }

    @Override
    public void undergroundTicketUnpressed(String playerName) {
        unpressedButtonAction(playerName,Ticket.Underground);
    }

    @Override
    public void doubleMoveTicketUnpressed(String playerName) {
        doubleMoveToggle = 0;
        theView.unpressAllButtons();
        unpressedButtonAction(playerName, Ticket.DoubleMove);
    }

    @Override
    public void secretMoveTicketUnpressed(String playerName) {
        unpressedButtonAction(playerName,Ticket.SecretMove);
    }


    //todo
    @Override
    public void plusButtonPressed(){
        if(initFrame.getNumberOfPlayers() != 6) initFrame.setNumberOfPlayers(initFrame.getNumberOfPlayers() + 1);
    }
    @Override
    public void playButtonPressed(){
        initFrame.setInvisible();
        theView.setVisible();
        theModel = (ScotlandYardModel) new InitialiseGame(initFrame.getNumberOfPlayers() - 1,"graph.txt").game;
        this.theControllerUtilities = new ControllerUtilities(theModel,theView);
        theView.setControllerPrivileges(this);
        theView.setControllerPrivileges(this);
        theView.getMapJPanel().circleButtons(theModel.getColourToLocationMap());
    }
    @Override
    public void minusButtonPressed(){
        if(initFrame.getNumberOfPlayers() != 2) initFrame.setNumberOfPlayers(initFrame.getNumberOfPlayers() - 1);
    }




    public void pressedButtonAction(String playerName, Ticket currentTicket){
        System.out.println("button recieved at controller" + playerName);
        theView.unpressAllButtonsMap();
        currentNode1 = "0";
        currentNode2 = "0";

        if(doubleMoveToggle == 1){
            doubleMovePressed(currentTicket);
        }
        else {
            if (moveToggle == 1){
                moveToggle = 0;
            }
            else{
                moveToggle = 1;
            }
            List<Move> moves = theControllerUtilities.findMoves(currentTicket);
            theControllerUtilities.displayCurrentMoves(moves);
            theView.activateSpecificButtonsPanelException(currentTicket.name(), false, theModel.getCurrentPlayer());
            toggleGoButton();
            ticket3 = currentTicket;

            if(moves.size() == 0){
                //theView.printConsole("No Taxi Tickets availible");
            }
        }
    }

    public void unpressedButtonAction(String playerName, Ticket currentTicket) {

        if (doubleMoveToggle == 1){
            doubleMoveUnpressed(currentTicket);
        }
        else {
            theControllerUtilities.displayCurrentMoves(theModel.getValidMoves(theModel.getCurrentPlayer()));
            theControllerUtilities.showValidTickets(theModel.getValidMoves(theModel.getCurrentPlayer()));

            if (moveToggle == 1){
                moveToggle = 0;
            }
            else{
                moveToggle = 1;
            }
            toggleGoButton();
        }

    }

    @Override
    public void goButtonUsed(String playerName) {
        System.out.println("button recieved at controller");
            if (doubleMoveToggle == 1){
                Move move = theControllerUtilities.findDoubleMoveTicket(Integer.parseInt(currentNode1), Integer.parseInt(currentNode2), ticket1, ticket2);
                theControllerUtilities.playMove(move);
                theView.getMapJPanel().getRidOfAllBorders();
                theView.getMapJPanel().circleButtons(theModel.getColourToLocationMap());
                theControllerUtilities.nextPlayer();
            }
            else {
                Move move = theControllerUtilities.findMoveTicket(Integer.parseInt(currentNode1), ticket3);
                theControllerUtilities.playMove(move);
                theView.getMapJPanel().getRidOfAllBorders();
                theView.getMapJPanel().circleButtons(theModel.getColourToLocationMap());
                theControllerUtilities.nextPlayer();
            }
            moveToggle = 0;
            doubleMoveToggle = 0;
        }

    public void toggleGoButton() {
        Set<ButtonHolder> buttons = theView.findSelectedButtons();
        if(buttons.size() == 2 && doubleMoveToggle == 2) {
            theView.goButtonToggle(true);
            doubleMoveToggle = 1;
        }
        else if (buttons.size() == 1 && doubleMoveToggle == 0 && moveToggle == 1){
            theView.goButtonToggle(true);
        }
        else{
            theView.goButtonToggle(false);
        }
    }

    @Override
    public void mapButtonPressed(String nodeNumber) {
        System.out.println("controller PR "+nodeNumber);
        toggleGoButton();
        if(currentNode1.equals("0")){
            currentNode1 = nodeNumber;
        }
        else{
            currentNode2 = nodeNumber;
        }

    }

    @Override
    public void mapButtonUnpressed(String nodeNumber) {
        System.out.println("controller UN "+nodeNumber);

        if(nodeNumber.equals(currentNode1)){
            currentNode1 = "0";
            if(!currentNode2.equals("0")){
                currentNode1 = currentNode2;
            }
        }
        else {
            currentNode2 = "0";
        }
    }

    public void doubleMovePressed(Ticket currentTicket) {
        if(currentTicket.equals(Ticket.DoubleMove)) {
            doubleMoveSortMapTickets();
        }
        else {
            if(ticket1.equals(Ticket.DoubleMove)){
                ticket1 = currentTicket;
                doubleMoveSortMapTickets();
            }
            else if(ticket2.equals(Ticket.DoubleMove)) {
                ticket2 = currentTicket;
                doubleMoveSortMapTickets();
            }
        }
    }

    public void doubleMoveUnpressed(Ticket currentTicket) {
        if (currentTicket.equals(ticket1)) {
            ticket1 = ticket2;
            ticket2 = Ticket.DoubleMove;
            doubleMoveSortMapTickets();
        }
        else {
            ticket2 = Ticket.DoubleMove;
            doubleMoveSortMapTickets();
        }
    }




    public void doubleMoveSortMapTickets() {
        if (ticket1.equals(Ticket.DoubleMove) && ticket2.equals(Ticket.DoubleMove)){
            theControllerUtilities.displayCurrentMoves(theControllerUtilities.moveDoubles);
            theView.activateSpecificButtonsPanelException("DoubleMove", false, Colour.Black);
            theControllerUtilities.showValidDoubleMoveTicketsFirstMove(theControllerUtilities.moveDoubles);
        }
        else if (ticket2.equals(Ticket.DoubleMove)){
            List<Move> movesSortedByTicketsNode1 = theControllerUtilities.sortMoveDoubleByTicket(0,ticket1, theControllerUtilities.moveDoubles);
            theControllerUtilities.displayCurrentMoves(movesSortedByTicketsNode1);
            theView.activateSpecificButtonsPanelException("DoubleMove", false, Colour.Black);
            theControllerUtilities.showValidDoubleMoveTicketsSecondMove(movesSortedByTicketsNode1);
        }
        else {
            List<Move> movesSortedByTicketsNode1 = theControllerUtilities.sortMoveDoubleByTicket(0,ticket1, theControllerUtilities.moveDoubles);
            List<Move> movesSortedByTicketsNode1AND2 = theControllerUtilities.sortMoveDoubleByTicket(1, ticket2, movesSortedByTicketsNode1);
            theControllerUtilities.displayCurrentMoves(movesSortedByTicketsNode1AND2);
            doubleMoveToggle = 2;
            toggleGoButton();
        }
    }





    public void setTheModel(ScotlandYardModel theModel){
        this.theModel = theModel;
    }

}


