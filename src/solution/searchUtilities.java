package solution;

import scotlandyard.*;

import java.util.*;

/**
 * Created by MikeCooper on 10/03/15.
 */
public class SearchUtilities {

    private final ScotlandYardModel mScotlandYard;
    List<Move> moveList = new ArrayList<Move>();

    public SearchUtilities(ScotlandYardModel scotlandYard){
        mScotlandYard = scotlandYard;
        moveList = new ArrayList<Move>();


    }

    public void mrXLocationUpdateCheck (int location){
        if(mScotlandYard.getRounds().get(mScotlandYard.getRoundCount())) {
            mScotlandYard.getMrXLocations().add(location);
        }
    }

    public GamePlayer findPlayer (Colour colour) {
        return mScotlandYard.getColourGamePlayerMap().get(colour);
    }

    public void removePlayer (Colour colour) {
        mScotlandYard.getColourGamePlayerMap().remove(colour, mScotlandYard.getColourGamePlayerMap().get(colour));
    }

    protected List<Move> getMoves (Colour colour) {
        int location = findPlayer(colour).getLocation();
        Map<Ticket,Integer> tickets = findPlayer(colour).getTickets();

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
        List<Edge> fullyConnectedEdges = getConnectedEdges(location);
        return fullyConnectedEdges;
    }

    public List<Edge> getConnectedEdges(int node){
        List<Edge> connectedEdges = new ArrayList<Edge>();

        for (Edge<Integer, Route> edge : mScotlandYard.getGraph().getEdges()) {
            if (node == edge.source()) {
                connectedEdges.add(edge);
            }
        }
        return connectedEdges;
    }

    protected Ticket getTicket (Edge currentEdge){
        return Ticket.fromRoute((Route) currentEdge.data());
    }

    protected int getTarget (Edge edge){
        return Integer.parseInt(edge.target().toString());
    }

    protected Map<Colour, GamePlayer> getSortedMap (){
        Map<Colour, GamePlayer> sortedColourGamePlayerMap = new LinkedHashMap<Colour, GamePlayer>();

        GamePlayer mrX = mScotlandYard.getColourGamePlayerMap().get(Colour.Black);
        sortedColourGamePlayerMap.put(Colour.Black, mrX);
        mScotlandYard.getColourGamePlayerMap().remove(Colour.Black, mrX);

        for (Colour colour : mScotlandYard.getColourGamePlayerMap().keySet()) {
            sortedColourGamePlayerMap.put(colour, mScotlandYard.getColourGamePlayerMap().get(colour));
        }
        return sortedColourGamePlayerMap;
    }
}
