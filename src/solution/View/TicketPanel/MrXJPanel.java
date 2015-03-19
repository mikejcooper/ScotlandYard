package solution.View.TicketPanel;

import scotlandyard.Colour;
import scotlandyard.Ticket;
import solution.Controller.Interfaces.ControllerButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class MrXJPanel extends TemplatePlayerJPanel {

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

    @Override
    public void setTicketValue(Map<Ticket, Integer> tickets) {
        super.setTicketValue(tickets);
        setDoubleMoveTicketValue(tickets.get(Ticket.DoubleMove).toString());
        setSecretMoveTicketValue(tickets.get(Ticket.SecretMove).toString());
    }

    protected void setJPanelLayout() {

        setGridLayout(3,0, SpecialMovesLabel,0);
        setGridLayout(3,1, DoubleMoveTicket,0);
        setGridLayout(3,2, SecretMoveTicket,0);

        int LeftInsetsWeighting = 7;
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
        super.actionPerformed(e);

        Object source = e.getSource();

        if(source == SecretMoveTicket){
            setSecretMoveTicketValue("" + (Integer.parseInt(SecretMoveTicketValue.getText())-1));
            controllerButtonListener.secretMoveTicketUsed(PlayerName.getText());
        }
        if(source == DoubleMoveTicket){
            setDoubleMoveTicketValue("" + (Integer.parseInt(DoubleMoveTicketValue.getText())-1));
            controllerButtonListener.doubleMoveTicketUsed(PlayerName.getText());
        }
    }

    @Override
    public void activateDeactivateButtons (Boolean b) {
        super.activateDeactivateButtons(b);

        if (b) {
            DoubleMoveTicket.setEnabled(b);
            SecretMoveTicket.setEnabled(b);
        }
        else {
            DoubleMoveTicket.setEnabled(b);
            SecretMoveTicket.setEnabled(b);
        }

    }

}
