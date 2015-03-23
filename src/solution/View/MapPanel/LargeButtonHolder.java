package solution.View.MapPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MikeCooper on 22/03/15.
 */
public class LargeButtonHolder extends JButton {

    public ButtonHolder buttonHolder;

    public LargeButtonHolder (int id, double x, double y) {
        //this.setOpaque(false);
        //this.setContentAreaFilled(false);
        //this.setBorderPainted(false);

        buttonHolder = new ButtonHolder(id,x,y);
        buttonHolder.setSize(25, 10);
        this.add(buttonHolder);
    }
}
