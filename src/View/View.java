package View;

import View.MapPanel.MapJPanel;
import View.TicketPanel.TicketJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class View extends JFrame {

    private GridBagConstraints gbc;

    protected MapJPanel mapJPanel;
    protected TicketJPanel ticketJPanel;

    public View (String[] args) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 800);

        ticketJPanel = new TicketJPanel(args);
        mapJPanel = new MapJPanel();

        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setJFrameLayout();

        pack();

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
}
