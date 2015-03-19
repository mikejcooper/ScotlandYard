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

    public GraphDisplay() {

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


        // read the input positions
        File file = new File("pos.txt");
        Scanner in = null;
        try
        {
            in = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }

        Map<String, List<Integer>> coordinateMap = new HashMap<String, List<Integer>>();

        // get the number of nodes
        String topLine = in.nextLine();
        int numberOfNodes = Integer.parseInt(topLine);

        //width and height are initialised here so coordinates of nodes are right
        double widthOfImage = 1018;
        double heightOfImage = 600;


        for(int i = 0; i < numberOfNodes; i++)
        {
            String line = in.nextLine();

            String[] parts = line.split(" ");
            List<Integer> pos = new ArrayList<Integer>();
            double x = Double.parseDouble(parts[1]) * (widthOfImage/1018);
            double y = Double.parseDouble(parts[2]) * (heightOfImage / 809);

            pos.add((int) x);
            pos.add((int) y);

            String key = parts[0];
            coordinateMap.put(key, pos);
        }




        assert img != null;
        Image scaleImage = img.getScaledInstance((int) widthOfImage, (int)heightOfImage, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(scaleImage);
        map = new JLabel(icon1);





    }

    public JLabel getMap(){
        return map;
    }

}