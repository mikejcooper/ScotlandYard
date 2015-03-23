package solution.View.MapPanel;

import solution.Controller.Interfaces.ControllerButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
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


        this.buttons = graphDisplay.getButtonSet();

        this.setOpaque(true);
        this.setBackground(new Color(0,0,0,0));

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
                if (button.isSelected()){
                    controllerButtonListener.mapButtonPressed(button.getText());
                }
                else {
                    controllerButtonListener.mapButtonUnpressed(button.getText());
                }
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

    public Set<ButtonHolder> findSelectedButtons() {
        Set<ButtonHolder> buttonList = new HashSet<ButtonHolder>();
        for (ButtonHolder button : buttons) {
            if(button.isSelected()) {
                buttonList.add(button);
            }
        }
        return buttonList;
    }
}
