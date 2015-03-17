package solution.View.TicketPanel;

import solution.Controller.Interfaces.ControllerButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class MrXJPanel extends TemplatePlayerJPanel {

        protected ControllerButtonListener controllerButtonListener;

        private JButton SecretMoveTicket = new JButton("Secret Move");
        private JButton DoubleMoveTicket = new JButton("DoubleMove");
        private JLabel SecretMoveTicketValue = new JLabel("5");
        private JLabel DoubleMoveTicketValue = new JLabel("5");
        private JLabel SpecialMovesLabel = new JLabel("Special Moves (MrX)");

    public MrXJPanel(String playerName) {
        super(playerName);
        setJPanelLayout();
        SecretMoveTicket.addActionListener(this);
        DoubleMoveTicket.addActionListener(this);

    }

    protected void setJPanelLayout() {

        setGridLayout(3,0, SpecialMovesLabel,0);
        setGridLayout(3,1, DoubleMoveTicket,0);
        setGridLayout(3,2, SecretMoveTicket,0);

        int LeftInsetsWeighting = 4;
        setGridLayout(4,1, DoubleMoveTicketValue, LeftInsetsWeighting);
        setGridLayout(4,2, SecretMoveTicketValue, LeftInsetsWeighting);

        SpecialMovesLabel.setHorizontalAlignment(JLabel.CENTER);
        SpecialMovesLabel.setFont(new Font("Courier New", Font.ITALIC, 8));
        //todo auto scaling
    }

    public void setSecretMoveTicketValue (String value){
        SecretMoveTicketValue.setText(value);
    }

    public void setDoubleMoveTicketValue (String value){
        DoubleMoveTicketValue.setText(value);
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
        if(source == SecretMoveTicket){
            setSecretMoveTicketValue("" + (Integer.parseInt(SecretMoveTicketValue.getText())-1));
            controllerButtonListener.secretMoveTicketUsed();
        }
        if(source == DoubleMoveTicket){
            setDoubleMoveTicketValue("" + (Integer.parseInt(DoubleMoveTicketValue.getText())-1));
            controllerButtonListener.doubleMoveTicketUsed();
        }
    }


    public void buttonListener(ControllerButtonListener controllerButtonListener){
        this.controllerButtonListener = controllerButtonListener;
    }
}
