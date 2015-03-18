package solution.View.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.net.*;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class MapJPanel extends JPanel {


    //private JLabel SuperCoolInteractiveMap = new JLabel("Insert Super Cool Interactive Map Here");
    private Image image;

    public MapJPanel() {
    //    add(SuperCoolInteractiveMap);
        setPreferredSize(new Dimension(1018,809));
        URL u = this.getClass().getResource("map.jpg");
        ImageIcon icon = new ImageIcon(u);
        image = icon.getImage();
        add(new JLabel(icon));
    }

//    public void paintComponent(Graphics g0){
//        super.paintComponent(g0);
//        Graphics2D g = (Graphics2D) g0;
//        g.drawImage(image, 0, 0, null);
//        RenderingHints rh = new RenderingHints(
//                RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setRenderingHints(rh);
//    }
}
