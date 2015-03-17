package solution.View.TicketPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class MrXJPanel extends TemplatePlayerJPanel {

        private JButton SecretMoveTicket = new JButton("Secret Move");
        private JButton DoubleMoveTicket = new JButton("DoubleMove");
        private JLabel SecretMoveTicketValue = new JLabel("0");
        private JLabel DoubleMoveTicketValue = new JLabel("0");
        private JLabel SpecialMovesLabel = new JLabel("Special Moves (MrX)");

    public MrXJPanel(String playerName) {
        super(playerName);
        setJPanelLayout();
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

    public void addSecretMoveAL (ActionListener actionListener){
        SecretMoveTicket.addActionListener(actionListener);
    }

    public void addDoubleMoveAL (ActionListener actionListener){
        DoubleMoveTicket.addActionListener(actionListener);
    }
}
