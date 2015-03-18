package solution.View.MapPanel;

import javax.swing.*;

/**
 * Created by gebruiker on 18/03/2015.
 */
public class ButtonHolder {
    private JButton button;
    private int id;
    private int xCoordinate;
    private int yCoordinate;


    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public ButtonHolder(JButton button,int id,int x, int y){
        this.button = button;
        this.id = id;
        xCoordinate = x;
        yCoordinate = y;
    }


}
