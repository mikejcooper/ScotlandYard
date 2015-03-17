package solution.Model.GamePlayer;

import scotlandyard.*;
import solution.Model.ScotlandYardModel;

import java.util.*;

/**
 * Created by MikeCooper on 12/03/15.
 */
public class GamePlayerMoveUtilities {

    private final ScotlandYardModel mScotlandYard;

    public GamePlayerMoveUtilities(ScotlandYardModel scotlandYard){
        mScotlandYard = scotlandYard;
    }


    public List<Move> getMoves(Colour colour, int location) {
        Map<Ticket,Integer> ticketMap = findPlayer(colour).getTickets();

        List<Move> moveListAll = new ArrayList<Move>();
        List<MoveDouble> moveDoubleList = new ArrayList<MoveDouble>();
        List<Move> moveList = getMovesAroundNode (colour,location,ticketMap);

        if (colour.equals(Colour.Black) && ticketMap.get(Ticket.DoubleMove) != 0) {
            moveDoubleList = calculateDoubleMoves(ticketMap, colour, moveList);
        }
        if(moveList.isEmpty() && !colour.equals(Colour.Black)) {
            moveList.add(new MovePass(colour));
        }

        moveListAll.addAll(moveList);
        moveListAll.addAll(moveDoubleList);
        return moveListAll;
    }

    protected List<Move> getMovesAroundNode (Colour colour, int location, Map<Ticket,Integer> tickets) {
        List<Move> moveList = new ArrayList<Move>();

        moveList = allPossibleMoves(colour, location, moveList, tickets);
        moveList = removeCurrentPLayersPosition(moveList);
        moveList = filterMovesByTickets(tickets, moveList);
        return moveList;
    }

    private List<MoveDouble> calculateDoubleMoves(Map<Ticket,Integer> ticketMap, Colour colour, List<Move> moveList) {
        List<MoveTicket> moveTicketList = new ArrayList<MoveTicket>(filterMoveTickets(moveList));
        List<MoveDouble> moveDoubleList = new ArrayList<MoveDouble>();

        for (MoveTicket moveTicket : moveTicketList) {
            Map<Ticket,Integer> ticketMapTemp = new HashMap<Ticket, Integer>(ticketMap);
            //REMOVED TICKET Value***
            ticketMapTemp.replace(moveTicket.ticket,ticketMapTemp.get(moveTicket.ticket)-1);
            List<Move> fullNodeMoveList = new ArrayList<Move>(getMovesAroundNode(colour,moveTicket.target,ticketMapTemp));
            moveDoubleList.addAll(createMoveDoubleList(fullNodeMoveList,moveTicket));
        }
        return moveDoubleList;
    }

    private List<MoveDouble> createMoveDoubleList (List<Move> moveListAroundCurrentNode, MoveTicket currentNodeTicket){
        List<MoveTicket> moveList = new ArrayList<MoveTicket>(filterMoveTickets(moveListAroundCurrentNode));
        List<MoveDouble> moveDoubleList = new ArrayList<MoveDouble>();

        for (MoveTicket moveTicket : moveList) {
            moveDoubleList.add(new MoveDouble(Colour.Black, currentNodeTicket,moveTicket));
        }

        return moveDoubleList;
    }

    private List<Move> removeCurrentPLayersPosition (List<Move> moveList){
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

        return moveList;

    }

    protected List<Move> filterMovesByTickets (Map<Ticket,Integer> ticketMap, List<Move> moveList) {
        List<MoveTicket> moveTicketList = new ArrayList<MoveTicket>(filterMoveTickets(moveList));

        List<Ticket> availibleTickets = new ArrayList<Ticket>(getAvailibleTicketsList(ticketMap));

        for (MoveTicket move : moveTicketList) {
            if (!availibleTickets.contains(move.ticket)) {
                moveList.remove(move);
            }
        }

        return moveList;
    }

    protected List<Move> allPossibleMoves (Colour colour, int location, List<Move> moveList, Map<Ticket,Integer> ticketMap) { //todo this was buggy so i changed it, delete this comment when read
        for (Edge edge : getConnectedEdges(location)) {
            if(location == getSource(edge)) {
                moveList.add(new MoveTicket(colour,getTarget(edge),getTicket(edge)));
                if (!(edge.data() == Route.Boat) && ticketMap.get(Ticket.SecretMove) != 0){
                    moveList.add(new MoveTicket(colour,getTarget(edge),Ticket.SecretMove));
                }
            }
            else {
                moveList.add(new MoveTicket(colour,getSource(edge),getTicket(edge)));
                if (!(edge.data() == Route.Boat) && ticketMap.get(Ticket.SecretMove) != 0){
                    moveList.add(new MoveTicket(colour,getSource(edge),Ticket.SecretMove));
                }
            }
        }
        return moveList;
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


    /**returns a <Colour -> gamePlayer> map with black as the first colour **/
    public Map<Colour, GamePlayer> getSortedMap(){
        Map<Colour, GamePlayer> sortedColourGamePlayerMap = new LinkedHashMap<Colour, GamePlayer>();

        GamePlayer mrX = mScotlandYard.getColourGamePlayerMap().get(Colour.Black);
        sortedColourGamePlayerMap.put(Colour.Black, mrX);
        mScotlandYard.getColourGamePlayerMap().remove(Colour.Black, mrX);

        for (Colour colour : mScotlandYard.getColourGamePlayerMap().keySet()) {
            sortedColourGamePlayerMap.put(colour, mScotlandYard.getColourGamePlayerMap().get(colour));
        }
        return sortedColourGamePlayerMap;
    }

    /** adds the location to mrXLocations if the current round is true **/
    public void mrXLocationUpdateCheck (int location){
        if(mScotlandYard.getRounds().get(mScotlandYard.getRoundCount())) {
            mScotlandYard.getMrXLocations().add(location);
        }
    }

    /**returns player of this colour**/
    public GamePlayer findPlayer (Colour colour) {
        return mScotlandYard.getColourGamePlayerMap().get(colour);
    }

}