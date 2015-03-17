package solution.Controller.TicketPanel;

import solution.View.TicketPanel.DetectiveJPanel;
import solution.View.View;
import solution.Model.ScotlandYardModel;

import java.awt.event.ActionListener;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class TicketPanelController {

    private ScotlandYardModel theModel;
    private View theView;
    private DetectiveJPanel playerJPanel;


    public TicketPanelController(ScotlandYardModel theModel, View theView) {
        this.theModel = theModel;
        this.theView = theView;
    }


}