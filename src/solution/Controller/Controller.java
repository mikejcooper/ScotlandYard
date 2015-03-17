package solution.Controller;
import solution.Controller.Interfaces.ControllerButtonListener;
import solution.Model.ScotlandYardModel;

/**
 * Created by MikeCooper on 17/03/15.
 */
public class Controller implements ControllerButtonListener {


    ScotlandYardModel theModel;

    //input the model
    public Controller (){
        this.theModel = theModel;
        //todo make model be accepted in constructor
    }


    @Override
    public void taxiTicketUsed(String playerName) {
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
