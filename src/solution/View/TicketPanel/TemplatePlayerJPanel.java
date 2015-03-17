package solution.View.TicketPanel;



import solution.Controller.Interfaces.ControllerButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by MikeCooper on 16/03/15.
 */
public abstract class TemplatePlayerJPanel extends JPanel implements ActionListener {


        protected GridBagConstraints gbc;
        protected JLabel PlayerName = new JLabel("Initialise in Constructor");
        protected JButton TaxiTicket = new JButton("Taxi");
        protected JButton BusTicket = new JButton("Bus");
        protected JButton UndergroundTicket = new JButton("Underground");
        protected JLabel TaxiTicketValue = new JLabel("5");
        protected JLabel BusTicketValue = new JLabel("5");
        protected JLabel UndergroundTicketValue = new JLabel("5");

    public TemplatePlayerJPanel(String playerName) {
        //setBorder(BorderFactory.createTitledBorder(playerName));

        PlayerName = new JLabel(playerName);

        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setJPanelLayout();


        TaxiTicket.addActionListener(this);
        BusTicket.addActionListener(this);
        UndergroundTicket.addActionListener(this);
    }

    private void setJPanelLayout() {
        setGridLayout(0, 0, PlayerName, 0);
        setGridLayout(0, 1, TaxiTicket, 0);
        setGridLayout(0, 2, BusTicket, 0);
        setGridLayout(0, 3, UndergroundTicket, 0);

        int LeftInsetsWeighting = 4;
        setGridLayout(1, 1, TaxiTicketValue, LeftInsetsWeighting);
        setGridLayout(1, 2, BusTicketValue, LeftInsetsWeighting);
        setGridLayout(1, 3, UndergroundTicketValue, LeftInsetsWeighting);

        PlayerName.setHorizontalAlignment(JLabel.CENTER);
    }

    protected void setGridLayout(int x, int y, Component component, int buffer) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(0,buffer,0,buffer);

        add(component, gbc);
    }

    public void setTaxiTicketValue (String value){
        TaxiTicketValue.setText(value);
    }

    public void setBusTicketValue (String value){
        BusTicketValue.setText(value);
    }

    public void setUndergroundTicketValue (String value){
        UndergroundTicketValue.setText(value);
    }

}


