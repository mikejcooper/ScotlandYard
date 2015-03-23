package solution.View.MapPanel;

import scotlandyard.Colour;
import solution.Controller.Interfaces.ControllerButtonListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

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

    public void unpressAllButtonsMap() {
        for (ButtonHolder button : buttons) {
            button.setSelected(false);
        }

    }

    public void circleButtons(Map<Colour,Integer> map){
        for(Colour colour: map.keySet()) {
            for (ButtonHolder button : buttons) {
                if (String.valueOf(map.get(colour)).equals(button.getText())) {
                    button.setBorder(new LineBorder(findColour(colour), 5));
                }
            }
        }
    }

    public Color findColour(Colour colour){

        switch(colour){
            case Black: return new Color(0,0,0, 255);
            case Blue:return new Color(0, 25, 255, 255);
            case Green:return new Color(5, 255,0, 255);
            case Red:return new Color(227, 22, 32, 255);
            case Yellow:return new Color(253, 237,0, 255);
            case White:return new Color(255, 255, 255, 255);



        }
        return new Color(0,0,0,0);
    }

    public void getRidOfAllBorders(){
        for(ButtonHolder button : buttons){
            button.setBorder(UIManager.getBorder("Button.border"));
        }
    }




}
