package solution.View.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class MapJPanel extends JPanel {

    private Set<ButtonHolder> buttons;
    GraphDisplay graphDisplay;

    public MapJPanel() {
        this.graphDisplay = new GraphDisplay();
        NodePosition nodePosition = new NodePosition();

        try{
            nodePosition.read(graphDisplay.getCoordinateMap(),graphDisplay.getWidthOfImage(),graphDisplay.getHeightOfImage());
        }
        catch(IOException e) {System.exit(1);}
        this.buttons = nodePosition.getButtonSet();

        add(graphDisplay.getMap());

        positionButtons();


    }



    public void positionButtons () {
        for (ButtonHolder button : buttons) {
            button.setSize(25, 10);
            button.setLocation((int) button.getxCoordinate(), (int) button.getyCoordinate());
            graphDisplay.getMap().add(button);
        }

    }

}
