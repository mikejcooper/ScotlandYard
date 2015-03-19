package solution.View.TicketPanel;

import scotlandyard.Colour;
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

    protected DetectiveJPanel[] detectives;
    protected MrXJPanel mrX;

    public TicketJPanel() {

        detectives = new DetectiveJPanel[5];
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
    }

    public void initialisePanel(List<Colour> players, Map<Colour, GamePlayer> colourGamePlayerMap){
        readInput(players);
        addToJPanel();
    }

    //todo better way to assure mrX is the last person to be added?
    private void readInput(List<Colour> players) {
        int x = 0;
        for (Colour player : players) {
            if(player.name().equals("Black")) {
               mrX = new MrXJPanel("Black");
            }
            else {
                detectives[x] = new DetectiveJPanel(player.name());
                x++;
            }
        }
    }

    private void addToJPanel() {
        int x = 0;
        for (DetectiveJPanel detective : detectives) {
            if (detective != null) {
                setGridLayout(x, 0, detective, 0);
                x++;
            }
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
            if (detective != null) {
                detective.buttonListener(controller);
            }
        }
        if(mrX != null){
            mrX.buttonListener(controller);
        }
    }
}
