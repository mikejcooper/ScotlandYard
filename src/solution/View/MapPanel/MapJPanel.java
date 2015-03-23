package solution.View.MapPanel;

import solution.Controller.Interfaces.ControllerButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class MapJPanel extends JPanel implements ActionListener {

    private Set<ButtonHolder> buttons;
    GraphDisplay graphDisplay;
    ControllerButtonListener controllerButtonListener;

    public MapJPanel() {
        this.graphDisplay = new GraphDisplay();
        NodePosition nodePosition = new NodePosition();

        try {
            nodePosition.read(graphDisplay.getCoordinateMap(), graphDisplay.getWidthOfImage(), graphDisplay.getHeightOfImage());
        } catch (IOException e) {
            System.exit(1);
        }
        this.buttons = nodePosition.getButtonSet();

        add(graphDisplay.getMap());

        positionButtons();
    }


    public void positionButtons() {
        for (ButtonHolder button : buttons) {
            button.setSize(25, 10);
            button.setLocation((int) button.getxCoordinate(), (int) button.getyCoordinate());
            graphDisplay.getMap().add(button);
            button.addActionListener(this);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        for (ButtonHolder button : buttons) {
            if (source == button) {
                System.out.println("map button" + button.getText());
                controllerButtonListener.mapButton(button.getText());
            }
        }
    }

    public void buttonListener(ControllerButtonListener controllerButtonListener){
        this.controllerButtonListener = controllerButtonListener;
    }

    public void activateDeactivateButtons (Boolean b) {
        for (ButtonHolder button : buttons) {
            button.setEnabled(b);
        }
    }

    public void activateDeactivateSpecificButtons (String buttonName, Boolean b){
        for (ButtonHolder button : buttons) {
            if (buttonName.equals(button.getText())){
                button.setEnabled(b);
                break;
            }
        }
    }

}
