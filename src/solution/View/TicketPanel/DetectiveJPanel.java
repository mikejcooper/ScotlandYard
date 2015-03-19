package solution.View.TicketPanel;

import scotlandyard.Ticket;
import solution.Controller.Interfaces.ControllerButtonListener;

import java.awt.event.ActionEvent;
import java.util.Map;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class DetectiveJPanel extends TemplatePlayerJPanel {



    public DetectiveJPanel(String playerName, Map<Ticket,Integer> tickets) {
        super(playerName, tickets);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == TaxiTicket){
            setTaxiTicketValue("" + (Integer.parseInt(TaxiTicketValue.getText())-1));
            controllerButtonListener.taxiTicketUsed(PlayerName.getText());
        }
        if(source == BusTicket){
            setBusTicketValue("" + (Integer.parseInt(BusTicketValue.getText())-1));
            controllerButtonListener.busTicketUsed(PlayerName.getText());
        }
        if(source == UndergroundTicket){
            setUndergroundTicketValue("" + (Integer.parseInt(UndergroundTicketValue.getText())-1));
            controllerButtonListener.UndergroundTicketUsed(PlayerName.getText());
        }
    }


}
