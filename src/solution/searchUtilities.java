package solution;

import scotlandyard.*;

import java.util.*;

/**
 * Created by MikeCooper on 10/03/15.
 */
public class SearchUtilities {

    private final ScotlandYardModel mScotlandYard;
    private List<Move> moveList2;

    public SearchUtilities(ScotlandYardModel scotlandYard){
        mScotlandYard = scotlandYard;
        moveList2 = new ArrayList<Move>();
    }



    protected List<Move> getMoves (Colour colour) {//todo we still need to implement double moves, and the checks if players are on the same spot,and the fact a player CAN stand on mrX location
        int location = findPlayer(colour).getLocation();
        Map<Ticket,Integer> ticketMap = findPlayer(colour).getTickets();

        List<Move> moveList2 = new ArrayList<Move>(getMovesAroundNode (colour,location,ticketMap));
        //todo double moves should only be calculated for mrX
        List<Move> moveList3 = new ArrayList<Move>(calculateDoubleMoves(ticketMap,colour,moveList2));

        List<Move> moveList = new ArrayList<Move>();
        moveList.addAll(moveList2);
        moveList.addAll(moveList3);

        //todo if movelist is empty we need to add a movepass, delete this comment when you read it mike :)
        //todo also i want to go over this function together, i think we can make it a lot smaller
        if(moveList.isEmpty()) moveList.add(new MovePass(colour));

        return moveList;
    }

    protected List<Move> getMovesAroundNode (Colour colour, int location, Map<Ticket,Integer> tickets) {
        List<Move> moveList = new ArrayList<Move>();

        allPossibleMoves(colour, location, moveList);
        removeCurrentPLayersPosition(moveList);
        filterMovesByTickets(tickets, moveList);
        return moveList;
    }

    private List<Move> calculateDoubleMoves(Map<Ticket,Integer> ticketMap, Colour colour, List<Move> moveList) {//todo look at the removevaluefromticket statement,removal of tickets is handled in the play function
        List<MoveTicket> moveTicketList = new ArrayList<MoveTicket>(filterMoveTickets(moveList));

        List<Move> fullNodeMoveList = new ArrayList<Move>();

        for (MoveTicket moveTicket : moveTicketList) {
            Map<Ticket,Integer> ticketMapTemp = new HashMap<Ticket, Integer>(ticketMap);

            findPlayer(colour).removeValueFromTicket(moveTicket.ticket);
            //Null??
//            ticketMapTemp.replace(moveTicket.ticket, ticketMapTemp.get(colour), ticketMapTemp.get(colour)-1);
            List<Move> currentNodeMoveList = getMovesAroundNode(colour,moveTicket.target,ticketMapTemp);
            fullNodeMoveList.addAll(currentNodeMoveList);
        }
        return fullNodeMoveList;

    }

    private  void removeCurrentPLayersPosition (List<Move> moveList){
        List<Colour> currentPlayers = mScotlandYard.getPlayers();
        currentPlayers.remove(Colour.Black);
        List<MoveTicket> moveTicketList = new ArrayList<MoveTicket>(filterMoveTickets(moveList));

        for (Colour player : currentPlayers) {
            int location = findPlayer(player).getLocation();
            for (MoveTicket moveTicket : moveTicketList) {
                if (moveTicket.target == location) {
                 moveList.remove(moveTicket);
                }
            }
        }

    }

    protected void filterMovesByTickets (Map<Ticket,Integer> ticketMap, List<Move> moveList) {
        List<MoveTicket> moveTicketList = new ArrayList<MoveTicket>(filterMoveTickets(moveList));

        List<Ticket> availibleTickets = new ArrayList<Ticket>(getAvailibleTicketsList(ticketMap));

        for (MoveTicket move : moveTicketList) {
            if (!availibleTickets.contains(move.ticket)) {
                moveList.remove(move);
            }
        }
    }

    protected void allPossibleMoves (Colour colour, int location, List<Move> moveList) { //todo this was buggy so i changed it, delete this comment when read
        for (Edge edge : getConnectedEdges(location)) {
            if(location == getSource(edge)) moveList.add(new MoveTicket(colour,getTarget(edge),getTicket(edge)));
            moveList.add(new MoveTicket(colour,getSource(edge),getTicket(edge)));
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

    private List<MoveTicket> filterMoveTickets (List<Move> listOfMoves){
        List<MoveTicket> moveTicketList = new ArrayList<MoveTicket>();
        //finds MoveTickets in moveList
        for (Move move : listOfMoves) {
            if (move instanceof MoveTicket){
                moveTicketList.add((MoveTicket) move);
            }
        }
        return moveTicketList;
    }

    protected int getTarget (Edge edge){
        return Integer.parseInt(edge.target().toString());
    }

    protected int getSource (Edge edge){
        return Integer.parseInt(edge.source().toString());
    }

    private List<Move> checkNull (List<Move> moveList) {

        if(moveList.size() == 0) {
            return new ArrayList<Move>();
        }
        else {
            return moveList;
        }

    }

    private List<Ticket> getAvailibleTicketsList (Map<Ticket,Integer> ticketMap) {
        List<Ticket> availibleTickets = new ArrayList<Ticket>();

        for (Ticket ticket : ticketMap.keySet()) {
            if (!(ticketMap.get(ticket) == 0))
            {
                availibleTickets.add(ticket);
            }
        }
        return availibleTickets;
    }


    //other functions
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
}
