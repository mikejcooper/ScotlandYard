package solution.View.TicketPanel;

import scotlandyard.Colour;
import scotlandyard.Ticket;
import solution.Controller.Controller;
import solution.Model.GamePlayer.GamePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by MikeCooper on 17/03/15.
 */
public class TicketJPanel extends JPanel {


    private GridBagConstraints gbc;

    protected Set<DetectiveJPanel> detectives;
    protected MrXJPanel mrX;

    public TicketJPanel() {

        detectives = new HashSet<DetectiveJPanel>();
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
    }

    public void addPlayer(Colour player, Map<Ticket,Integer> tickets){
        if(player.name().equals("Black")) {
            mrX = new MrXJPanel("Black",tickets);
        }
        else {
            detectives.add(new DetectiveJPanel(player.name(), tickets));
        }
        addToJPanel();
    }

    private void addToJPanel() {
        int x = 0;
        for (DetectiveJPanel detective : detectives) {
                setGridLayout(x, 0, detective, 0);
                x++;
        }
        if(mrX != null){
            setGridLayout(x,0,mrX,0);
        }
    }

    protected void setGridLayout(int x, int y, Component component, int buffer) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(0,buffer,0,buffer);

        add(component, gbc);
    }

    public void setControllerPrivileges(Controller controller) {
        for (DetectiveJPanel detective : detectives) {
                detective.buttonListener(controller);
        }
        if(mrX != null){
            mrX.buttonListener(controller);
        }
    }

    public void setCurrentPlayer(Colour currentPlayer) {
        for (DetectiveJPanel detective : detectives) {
            buttonCheck(detective, currentPlayer);
        }
        buttonCheck(mrX, currentPlayer);
    }

    public void buttonCheck (TemplatePlayerJPanel player, Colour currentPlayer) {
        if (currentPlayer.name().equals(player.PlayerName.getText())){
            player.activateDeactivateButtons(true);
        }
        else {
            player.activateDeactivateButtons(false);
        }
    }
}
