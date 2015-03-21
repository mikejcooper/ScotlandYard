package solution.View.MapPanel;

import javax.swing.*;
import java.awt.*;

/**
* Created by gebruiker on 18/03/2015.
*/
public class ButtonHolder extends JButton {
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
