package solution;

import scotlandyard.*;

import java.util.*;

/**
 * Created by MikeCooper on 10/03/15.
 */
public class SearchUtilities {

    private final ScotlandYardModel mScotlandYard;
    private List<Move> moveList;

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

    protected List<Move> getMoves (Colour colour) {//todo we still need to implement double moves, and the checks if players are on the same spot,and the fact a player CAN stand on mrX location
        int location = findPlayer(colour).getLocation();
        Map<Ticket,Integer> tickets = findPlayer(colour).getTickets();

        allPossibleMoves(colour, location);

        if(moveList.size() == 0 || tickets.size() == 0) {
            return new ArrayList<Move>();
        }
        filterMovesByTickets(tickets);
        return moveList;
    }

    protected void filterMovesByTickets (Map<Ticket,Integer> ticketMap) {
        List<MoveTicket> moveTicketList = new ArrayList<MoveTicket>();
        //finds MoveTickets in moveList
        for (Move move : moveList) {
            if (move instanceof MoveTicket){
                moveTicketList.add((MoveTicket) move);
            }
        }

        //remove Tickets -> 0 (that map to)
        for (Iterator<Map.Entry<Ticket, Integer>> ticketMap2 = ticketMap.entrySet().iterator(); ticketMap2.hasNext(); ) {
            Map.Entry<Ticket, Integer> entry = ticketMap2.next();
            if (entry.getValue() == 0) {
                ticketMap.remove(entry.getValue(), entry.getKey());
            }
        }

        List<Ticket> availibleTickets = new ArrayList<Ticket>(ticketMap.keySet());

        for (MoveTicket move : moveTicketList) {
            if (!availibleTickets.contains(move.ticket)) {
                moveList.remove(move);
            }
        }
    }

    protected void allPossibleMoves (Colour colour, int location) {
        for (Edge edge : getConnectedEdges(location)) {
            MoveTicket newMoveTicket = new MoveTicket(colour,getTarget(edge),getTicket(edge));
            moveList.add(newMoveTicket);
        }
    }

    public List<Edge> getConnectedEdges(int node){
        List<Edge> connectedEdges = new ArrayList<Edge>();

        for (Edge<Integer, Route> edge : mScotlandYard.getGraph().getEdges()) {
            if (node == edge.source() || node == edge.target()) {
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
