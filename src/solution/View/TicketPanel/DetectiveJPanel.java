package solution.View.TicketPanel;

import solution.Controller.Interfaces.ControllerButtonListener;

import java.awt.event.ActionEvent;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class DetectiveJPanel extends TemplatePlayerJPanel {

    protected ControllerButtonListener controllerButtonListener;

    public DetectiveJPanel(String playerName) {
        super(playerName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == TaxiTicket){
            setTaxiTicketValue("" + (Integer.parseInt(TaxiTicketValue.getText())-1));
            controllerButtonListener.taxiTicketUsed();
        }
        if(source == BusTicket){
            setBusTicketValue("" + (Integer.parseInt(BusTicketValue.getText())-1));
            controllerButtonListener.busTicketUsed();
        }
        if(source == UndergroundTicket){
            setUndergroundTicketValue("" + (Integer.parseInt(UndergroundTicketValue.getText())-1));
            controllerButtonListener.UndergroundTicketUsed();
        }
    }

    //pass in controller, allows controller to tell view when model has changed, therefore view should also change.
    public void buttonListener(ControllerButtonListener controllerButtonListener){
        this.controllerButtonListener = controllerButtonListener;
    }

}
