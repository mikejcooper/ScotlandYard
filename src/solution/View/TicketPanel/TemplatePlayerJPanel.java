package solution.View.TicketPanel;



import scotlandyard.Ticket;
import solution.Controller.Interfaces.ControllerButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Created by MikeCooper on 16/03/15.
 */
public abstract class TemplatePlayerJPanel extends JPanel implements ActionListener {

        //todo added string playername, to be able to identify object easily

        protected JLabel PlayerName = new JLabel("Initialise in Constructor");
        protected JButton TaxiTicket = new JButton("Taxi");
        protected JButton BusTicket = new JButton("Bus");
        protected JButton UndergroundTicket = new JButton("Underground");
        protected JLabel TaxiTicketValue = new JLabel("-1");
        protected JLabel BusTicketValue = new JLabel("-1");
        protected JLabel UndergroundTicketValue = new JLabel("-1");

        protected GridBagConstraints gbc;
        protected ControllerButtonListener controllerButtonListener;


    public TemplatePlayerJPanel(String playerName, Map<Ticket,Integer> tickets) {
        //setBorder(BorderFactory.createTitledBorder(playerName));
        PlayerName = new JLabel(playerName);

        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setJPanelLayout();
        setTicketValue(tickets);

        TaxiTicket.addActionListener(this);
        BusTicket.addActionListener(this);
        UndergroundTicket.addActionListener(this);
    }

    private void setTicketValue(Map<Ticket, Integer> tickets) {
        setTaxiTicketValue(tickets.get(Ticket.Taxi).toString());
        setBusTicketValue(tickets.get(Ticket.Bus).toString());
        setUndergroundTicketValue(tickets.get(Ticket.Underground).toString());
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
        if(component instanceof JLabel) { gbc.fill = GridBagConstraints.NORTH;}
        else {gbc.fill = GridBagConstraints.HORIZONTAL;}
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

    //pass in controller, allows controller to tell view when model has changed, therefore view should also change.
    public void buttonListener(ControllerButtonListener controllerButtonListener){
        this.controllerButtonListener = controllerButtonListener;
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

    public void activateDeactivateButtons (Boolean b) {
        if (b) {
            TaxiTicket.setEnabled(b);
            BusTicket.setEnabled(b);
            UndergroundTicket.setEnabled(b);
        }
        else {
            TaxiTicket.setEnabled(b);
            BusTicket.setEnabled(b);
            UndergroundTicket.setEnabled(b);
        }

    }




}


