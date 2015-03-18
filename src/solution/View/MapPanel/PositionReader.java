package solution.View.MapPanel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by gebruiker on 18/03/2015.
 */
public class PositionReader extends JPanel{

    Set<ButtonHolder> buttonSet = new HashSet<ButtonHolder>();

    public Set<ButtonHolder> getButtonSet(){
        return buttonSet;
    }


    void read() throws IOException {

        // load the file
        File file = new File("pos.txt");
        Scanner in = new Scanner(file );

        in.nextLine();

        while(in.hasNextLine()){
            String[] coordinates = in.nextLine().split(" ");
            buttonSet.add(new ButtonHolder(new JButton(coordinates[0]),Integer.parseInt(coordinates[0]),Integer.parseInt(coordinates[1]),Integer.parseInt(coordinates[2])));
        }

    }
}