package solution;

import scotlandyard.*;

import java.io.IOException;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Created by MikeCooper on 09/03/15.
 */
public class PlayerMoves {

    int location;
    Colour colour;

    public PlayerMoves (int currentLocation, Colour player){
        this.location = currentLocation;
        this.colour = player;
    }


    protected List<Move> getMoves () {
        List<Move> moveList = new ArrayList<Move>();
        for (Edge edge : fullyConnectedEdges()) {
            MoveTicket newMoveTicket = new MoveTicket(colour,getTarget(edge),getTicket(edge));
            moveList.add(newMoveTicket);
        }
        return moveList;
    }

    protected List<Edge> fullyConnectedEdges (){
        List<Edge> fullyConnectedEdges = new ConnectedEdges().getConnectedEdges(location);
        return fullyConnectedEdges;
    }

    protected Ticket getTicket (Edge currentEdge){
        return Ticket.fromRoute((Route) currentEdge.data());
    }

    protected int getTarget (Edge edge){
        return Integer.parseInt(edge.target().toString());
    }



//
//    protected Player getPlayer () {
//
//    }
}
