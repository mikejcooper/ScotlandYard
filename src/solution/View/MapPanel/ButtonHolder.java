package solution.View.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Created by gebruiker on 18/03/2015.
*/

public class ButtonHolder extends JToggleButton {
    private double xCoordinate;
    private double yCoordinate;

    public ButtonHolder(int id,double x, double y){
        this.setText("" + id);
        this.setFont(new Font("Courier New", Font.BOLD, 10));
        xCoordinate = x;
        yCoordinate = y;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }
}
