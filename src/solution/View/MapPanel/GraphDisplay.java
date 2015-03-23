package solution.View.MapPanel;

import scotlandyard.Edge;
import scotlandyard.Graph;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.List;

import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GraphDisplay {

    private JLabel map = new JLabel();
    Map<String, List<Integer>> coordinateMap;
    double widthOfImage;
    double heightOfImage;

    public GraphDisplay() {
        coordinateMap = new LinkedHashMap<String, List<Integer>>();

        //width and height are initialised here so coordinates of nodes are right
        this.widthOfImage = 1018;
        this.heightOfImage = 809;

        loadMapImage();
        fillCoordinateMap();
    }


    public void loadMapImage() {
        // load up the image and the graph
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File("map.jpg"));
        }
        catch( IOException e )
        {
            System.out.println(e);
        }

        assert img != null;
        Image scaleImage = img.getScaledInstance((int) widthOfImage, (int) heightOfImage, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(scaleImage);
        map = new JLabel(icon1);

    }

    public void fillCoordinateMap() {
        // read the input positions
        File file = new File("pos.txt");
        Scanner in = null;
        try {in = new Scanner(file);}
        catch (FileNotFoundException e) {System.out.println(e);}

        // get the number of nodes
        String topLine = in.nextLine();

        //todo scale each node!
        for(int i = 0; i < Integer.parseInt(topLine) ; i++)
        {
            String line = in.nextLine();
            String[] parts = line.split(" ");

            double x = Double.parseDouble(parts[1]) * (widthOfImage/1018);
            double y = Double.parseDouble(parts[2]) * (heightOfImage / 809);

            // x' = x * (width'/width)

            List<Integer> xy = new ArrayList<Integer>();
            xy.add((int) x - 11);
            xy.add((int) y - 5);

            coordinateMap.put(parts[0],xy);
        }
    }


    public double getHeightOfImage() {
        return heightOfImage;
    }

    public double getWidthOfImage() {
        return widthOfImage;
    }

    public Map<String, List<Integer>> getCoordinateMap() {
        return coordinateMap;
    }
    public JLabel getMap(){
        return map;
    }

}