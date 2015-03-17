package solution.Controller;

import solution.Controller.Interfaces.MrXButtonListener;
import solution.Controller.Interfaces.PlayerButtonListener;

/**
 * Created by MikeCooper on 17/03/15.
 */
public class Controller implements PlayerButtonListener,MrXButtonListener {

    @Override
    public void taxiTicketUsed() {
        System.out.print("Received at Controller");
    }

    @Override
    public void busTicketUsed() {
        System.out.print("Received at Controller");
    }

    @Override
    public void UndergroundTicketUsed() {
        System.out.print("Received at Controller");
    }

    @Override
    public void doubleMoveTicketUsed() {
        System.out.print("Received at Controller");
    }

    @Override
    public void secretMoveTicketUsed() {
        System.out.print("Received at Controller");
    }


}
