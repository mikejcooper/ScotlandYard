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
    public void taxiTicketUsed() {
        System.out.println("Received at Controller taxi");
    }

    @Override
    public void busTicketUsed() {
        System.out.println("Received at Controller bus");
    }

    @Override
    public void UndergroundTicketUsed() {
        System.out.println("Received at Controller underground");
    }

    @Override
    public void doubleMoveTicketUsed() {
        System.out.println("Received at Controller doublemove");
    }

    @Override
    public void secretMoveTicketUsed() {
        System.out.println("Received at Controller secret");
    }


}
