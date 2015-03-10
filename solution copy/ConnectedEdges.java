package solution;

import scotlandyard.Edge;
import scotlandyard.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by MikeCooper on 09/03/15.
 */
public class ConnectedEdges {

    public List<Edge> getConnectedEdges(int node){
        List<Edge> connectedEdges = new ArrayList<Edge>();

        for (Edge<Integer, Route> edge : ScotlandYardModel.graph.getEdges()) {
            if (node == edge.source()) {
                connectedEdges.add(edge);
            }
        }
        return connectedEdges;
    }


}
