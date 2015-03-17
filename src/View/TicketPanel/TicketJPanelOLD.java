package View.TicketPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class TicketJPanelOLD extends JPanel {


    private GridBagConstraints gbc;

    protected DetectiveJPanel d1;
    protected DetectiveJPanel d2;
    protected DetectiveJPanel d3;
    protected DetectiveJPanel d4;
    protected DetectiveJPanel d5;
    protected MrXJPanel mrX;



    public TicketJPanelOLD(String[] args) {

        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        readInput(args);


    }

    //todo better way to assure mrX is the last person to be added?
    private void readInput(String[] args) {
        int x = 0;
        String mrX = "NotRead";
        for (String player : args) {
            if(player.equals("Black")){
                mrX = "MrX";
            }
            else{
                addToJPanel(player, x);
                x++;
            }
        }

        if(mrX.equals("MrX")){
            addToJPanel(mrX,x);
        }
    }

    private void addToJPanel(String player, int x) {
       if(player.equals("MrX")) {
           setGridLayout(x,0, new MrXJPanel(player),0);
       }
        else {
           setGridLayout(x,0, new DetectiveJPanel(player),0);
       }

}


    protected void setGridLayout(int x, int y, Component component, int buffer) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(0,buffer,0,buffer);

        add(component, gbc);
    }


}
