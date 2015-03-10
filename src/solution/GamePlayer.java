package solution;

import scotlandyard.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by gebruiker on 09/03/2015.
 */
public class GamePlayer implements Player{
    private Player player;
    private Colour colour;
    private int location;
    private Map<Ticket,Integer> tickets;

    public GamePlayer(Player player, Colour colour, int location, Map<Ticket,Integer> tickets){
        this.player = player;
        this.colour = colour;
        this.location = location;
        this.tickets = tickets;
    }

    public Colour getColour(){
        return colour;
    }

    public int getLocation(){
        return location;
    }

    public Map<Ticket, Integer> getTickets(){
        return tickets;
    }

    public int getNumberOfTicket(Ticket ticket){
        return tickets.get(ticket);
    }

    public List<Move> getMoves(Graph graph){//todo this is new
        List<Move> list = new LinkedList<Move>();
        List<Edge> edges = graph.getEdges();

        for(Edge edge : edges){
            int i = Integer.valueOf((String) edge.source());
            int j = Integer.valueOf((String) edge.target());
            if( i == location) list.add(new MoveTicket(colour,j,Ticket.valueOf((String) edge.data())));
            if( j == location) list.add(new MoveTicket(colour,i,Ticket.valueOf((String) edge.data())));
        }
        return list;
    }



    @Override //todo
    public Move notify(int location, List<Move> list){
        return null;
    }
}
