package solution.View.TicketPanel;

import solution.Controller.Interfaces.PlayerButtonListener;

import java.awt.event.ActionEvent;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class DetectiveJPanel extends TemplatePlayerJPanel {

    public DetectiveJPanel(String playerName) {
        super(playerName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == TaxiTicket){
            setTaxiTicketValue("" + (Integer.parseInt(TaxiTicketValue.getText())+1));
        }
        if(source == BusTicket){
            setBusTicketValue("" + (Integer.parseInt(BusTicketValue.getText())+1));
        }
        if(source == UndergroundTicket){
            setUndergroundTicketValue("" + (Integer.parseInt(UndergroundTicketValue.getText())+1));
        }

        if(playerButtonListener != null){
            playerButtonListener.taxiTicketUsed();
        }
    }

    //pass in controller, allows controller to tell view when model has changed, therefore view should also change.
    public void buttonListener(PlayerButtonListener playerButtonListener){
        this.playerButtonListener = playerButtonListener;
    }

}
