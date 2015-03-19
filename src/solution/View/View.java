package solution.View;

import scotlandyard.Colour;
import scotlandyard.Ticket;
import solution.Controller.Controller;
import solution.View.MapPanel.MapJPanel;
import solution.View.TicketPanel.TicketJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class View extends JFrame {

    private GridBagConstraints gbc;

    private MapJPanel mapJPanel;
    private TicketJPanel ticketJPanel;
    private TextEditJFrame textEditJFrame;

    public View () {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 800);

        ticketJPanel = new TicketJPanel();
        mapJPanel = new MapJPanel();
        //textEditJFrame = new TextEditJFrame();

        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        setJFrameLayout();

        //pack();

        setVisible(true);
    }


    private void setJFrameLayout () {
        setMapPanel();
        setTicketPanel();
    }

    private void setMapPanel() {
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(mapJPanel, gbc);
    }

    private void setTicketPanel() {
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(ticketJPanel, gbc);
    }

    public void setControllerPrivileges(Controller controller) {
        //allow controller to access certain parts -
        ticketJPanel.setControllerPrivileges(controller);
    }

    public void addPlayer(Colour player, Map<Ticket,Integer> tickets) {
        ticketJPanel.addPlayer(player, tickets);
    }

    public void setCurrentPlayer(Colour player){
        ticketJPanel.setCurrentPlayer(player);

    }

    public void updateTicketValues(Colour player, Map<Ticket,Integer> tickets) {
        ticketJPanel.updateTickets(player, tickets);
    }




}
