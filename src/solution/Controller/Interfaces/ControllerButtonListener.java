package solution.Controller.Interfaces;

/**
 * Created by MikeCooper on 17/03/15.
 */
public interface ControllerButtonListener {

    public void taxiTicketUsed(String playerName);
    public void busTicketUsed(String playerName);
    public void UndergroundTicketUsed(String playerName);
    public void doubleMoveTicketUsed(String playerName);
    public void secretMoveTicketUsed(String playerName);
    public void goButtonUsed(String playerName);


    public void textInput(String input);



}
