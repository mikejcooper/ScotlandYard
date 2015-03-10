package solution;

import scotlandyard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by MikeCooper on 10/03/15.
 */
public class GameBoardMoves {

    List<Move> moveList = new ArrayList<Move>();
    SearchUtilities searchUtilities;

    public GameBoardMoves () {
        moveList = new ArrayList<Move>();
        searchUtilities = new SearchUtilities();
    }


    protected List<Move> getMoves (Colour colour, int location, Map<Ticket,Integer> tickets) {
        allPossibleMoves(colour, location);
        if(moveList.size() == 0 || tickets.size() == 0) {
            return new ArrayList<Move>();
        }
        return filterMovesByTickets(tickets);
    }

    protected List<Move> filterMovesByTickets (Map<Ticket,Integer> tickets) {
        List<Move> moveListTemp = moveList;
        List<Ticket> list = new ArrayList<Ticket>(tickets.keySet());

        for (Move move : moveListTemp) {
            if (!list.contains(move)){
                moveList.remove(move);
            }
        }
        return moveList;
    }

    protected List<Move> allPossibleMoves (Colour colour, int location) {
        for (Edge edge : fullyConnectedEdges(location)) {
            MoveTicket newMoveTicket = new MoveTicket(colour,getTarget(edge),getTicket(edge));
            moveList.add(newMoveTicket);
        }
        return moveList;
    }

    protected List<Edge> fullyConnectedEdges (int location){
        List<Edge> fullyConnectedEdges = searchUtilities.getConnectedEdges(location);
        return fullyConnectedEdges;
    }

    protected Ticket getTicket (Edge currentEdge){
        return Ticket.fromRoute((Route) currentEdge.data());
    }

    protected int getTarget (Edge edge){
        return Integer.parseInt(edge.target().toString());
    }
}
