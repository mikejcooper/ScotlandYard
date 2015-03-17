package solution.View.TicketPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MikeCooper on 17/03/15.
 */
public class TicketJPanel extends JPanel {


    private GridBagConstraints gbc;

    protected DetectiveJPanel[] detectives;
    protected MrXJPanel mrX;

    public TicketJPanel(String[] args) {

        detectives = new DetectiveJPanel[5];
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        readInput(args);
        addToJPanel();


    }

    //todo better way to assure mrX is the last person to be added?
    private void readInput(String[] args) {
        int x = 0;
        for (String player : args) {
            if(player.equals("Black")) {
               mrX = new MrXJPanel("Black");
            }
            else {
                detectives[x] = new DetectiveJPanel(player);
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


}
