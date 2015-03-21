package solution.View.MapPanel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
* Created by gebruiker on 18/03/2015.
*/
public class NodePosition {

    Set<ButtonHolder> buttonSet = new HashSet<ButtonHolder>();

    public void read(Map<String, List<Integer>> coordinateMap, double widthOfImage, double heightOfImage) throws IOException {

        int nodeCount = 0;
        for (Map.Entry<String, List<Integer>>  entry : coordinateMap.entrySet()) {
            nodeCount++;
            double x = entry.getValue().get(0) * (widthOfImage/1018);
            double y = entry.getValue().get(1) * (heightOfImage / 809);
            buttonSet.add(new ButtonHolder(nodeCount,x,y));
        }
    }

    public Set<ButtonHolder> getButtonSet(){
        return buttonSet;
    }
}