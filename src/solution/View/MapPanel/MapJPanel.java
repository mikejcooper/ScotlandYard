package solution.View.MapPanel;

import scotlandyard.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.*;
import java.util.Set;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class MapJPanel extends JPanel {


    private BufferedImage image;
//    private Set<ButtonHolder> buttons;

    public MapJPanel() {
//        PositionReader positionReader = new PositionReader();
//        try{positionReader.read();}
//        catch(IOException e){
//            System.exit(1);
//        }
//        this.buttons = positionReader.getButtonSet();
//        setPreferredSize(new Dimension(500,400));
//        URL u = this.getClass().getResource("map.jpg");
//        ImageIcon icon = new ImageIcon(u);
//        add(new JLabel(icon));
        GraphDisplay graphDisplay = new GraphDisplay();
        add(graphDisplay.getMap());


    }

//    public void paintComponent(Graphics g0){
//        super.paintComponent(g0);
//        Graphics2D g = (Graphics2D) g0;
//        //g.drawImage(image,0,0,null);
//        for(ButtonHolder buttonHolder:buttons){
//            g.fillOval(buttonHolder.getxCoordinate(),buttonHolder.getyCoordinate(),10,10);
//        }
//    }

}
