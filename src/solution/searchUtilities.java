package solution;

import scotlandyard.Colour;
import scotlandyard.Edge;
import scotlandyard.Route;
import scotlandyard.ScotlandYard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by MikeCooper on 10/03/15.
 */
public class SearchUtilities {

    public void mrXLocationUpdateCheck (int location){
        if(ScotlandYardModel.rounds.get(ScotlandYardModel.roundCount)) {
            ScotlandYardModel.mrXLocations.add(location);
        }
    }

    public GamePlayer findPlayer (Colour colour) {
        return ScotlandYardModel.colourGamePlayerMap.get(colour);
    }

    public void removePlayer (Colour colour) {
        ScotlandYardModel.colourGamePlayerMap.remove(colour,ScotlandYardModel.colourGamePlayerMap.get(colour));
    }




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
