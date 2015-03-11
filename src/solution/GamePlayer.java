package solution;

import scotlandyard.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by gebruiker on 09/03/2015.
 */
public class GamePlayer implements Player{
    protected Player player;
    protected Colour colour;
    protected int location;
    protected Map<Ticket,Integer> tickets;

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

    public void setLocation(int location) { this.location = location; }

    public Map<Ticket, Integer> getTickets(){
        return tickets;
    }

    public int getNumberOfTicket(Ticket ticket){
        return tickets.get(ticket);
    }


    //assuming earlier check if ticket is available
    public void removeOrAddTicket(Ticket ticket,int n){
        int value = tickets.remove(ticket);
        tickets.put(ticket,value + n);
    }


    public Player getPlayer(){ return player; }



    @Override //todo
    public Move notify(int location, List<Move> list){
        return null;
    }
}
