package solution.View.TicketPanel;



import com.sun.org.apache.xpath.internal.operations.Bool;
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
        protected JToggleButton TaxiTicket = new JToggleButton("Taxi");
        protected JToggleButton BusTicket = new JToggleButton("Bus");
        protected JToggleButton UndergroundTicket = new JToggleButton("Underground");
        protected JLabel TaxiTicketValue = new JLabel("-1");
        protected JLabel BusTicketValue = new JLabel("-1");
        protected JLabel UndergroundTicketValue = new JLabel("-1");

        protected GridBagConstraints gbc;
        protected ControllerButtonListener controllerButtonListener;


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

    public void setTicketValue(Map<Ticket,Integer> tickets) {
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
        if(Integer.parseInt(value) == 0){
            activateDeactivateSpecificButtons("TaxiTicket",false);
        }
    }

    public void setBusTicketValue (String value){
        BusTicketValue.setText(value);
        if(Integer.parseInt(value) == 0){
            activateDeactivateSpecificButtons("BusTicket",false);
        }
    }

    public void setUndergroundTicketValue (String value){
        UndergroundTicketValue.setText(value);
        if(Integer.parseInt(value) == 0){
            activateDeactivateSpecificButtons("Underground",false);
        }
    }

    //pass in controller, allows controller to tell view when model has changed, therefore view should also change.
    public void buttonListener(ControllerButtonListener controllerButtonListener){
        this.controllerButtonListener = controllerButtonListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == TaxiTicket){
            if (TaxiTicket.isSelected()){
                controllerButtonListener.taxiTicketPressed(PlayerName.getText());
            }
            else {
                controllerButtonListener.taxiTicketUnpressed(PlayerName.getText());
            }
        }
        if(source == BusTicket){
            if (BusTicket.isSelected()){
                controllerButtonListener.busTicketPressed(PlayerName.getText());
            }
            else {
                controllerButtonListener.busTicketUnpressed(PlayerName.getText());
            }
        }
        if(source == UndergroundTicket){
            if (UndergroundTicket.isSelected()){
                controllerButtonListener.undergroundTicketPressed(PlayerName.getText());
            }
            else {
                controllerButtonListener.undergroundTicketUnpressed(PlayerName.getText());
            }
        }
    }

    public void activateDeactivateButtons (Boolean b) {
            TaxiTicket.setEnabled(b);
            BusTicket.setEnabled(b);
            UndergroundTicket.setEnabled(b);
    }

    public void activateDeactivateSpecificButtonsException (String buttonName, Boolean b){
        if(!buttonName.equals(TaxiTicket.getText())){
            TaxiTicket.setEnabled(b);
        }
        if (!buttonName.equals(BusTicket.getText())){
            BusTicket.setEnabled(b);
        }
        if (!buttonName.equals(UndergroundTicket.getText())){
            UndergroundTicket.setEnabled(b);
        }
    }

    public void activateDeactivateSpecificButtons (String buttonName, Boolean b){
        if(buttonName.equals(TaxiTicket.getText())){
            TaxiTicket.setEnabled(b);
        }
        if (buttonName.equals(BusTicket.getText())){
            BusTicket.setEnabled(b);
        }
        if (buttonName.equals(UndergroundTicket.getText())){
            UndergroundTicket.setEnabled(b);
        }
    }





}


