package solution.Controller.TicketPanel;

import View.TicketPanel.DetectiveJPanel;
import View.TicketPanel.MrXJPanel;
import View.View;
import solution.Model.ScotlandYardModel;

import javax.swing.*;

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
