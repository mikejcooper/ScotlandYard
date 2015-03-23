package solution.Controller.Interfaces;

/**
 * Created by MikeCooper on 17/03/15.
 */
public interface ControllerButtonListener {

    public void taxiTicketPressed(String playerName);
    public void busTicketPressed(String playerName);
    public void undergroundTicketPressed(String playerName);
    public void doubleMoveTicketPressed(String playerName);
    public void secretMoveTicketPressed(String playerName);

    public void taxiTicketUnpressed(String playerName);
    public void busTicketUnpressed(String playerName);
    public void undergroundTicketUnpressed(String playerName);
    public void doubleMoveTicketUnpressed(String playerName);
    public void secretMoveTicketUnpressed(String playerName);

    public void plusButtonPressed();
    public void minusButtonPressed();
    public void playButtonPressed();


    public void goButtonUsed(String playerName);

    public void mapButtonPressed (String nodeNumber);
    public void mapButtonUnpressed (String nodeNumber);




}
