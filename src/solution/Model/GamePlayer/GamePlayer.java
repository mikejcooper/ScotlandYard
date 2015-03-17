package solution.Model.GamePlayer;

import scotlandyard.*;

import java.util.Map;

/**
 * Created by gebruiker on 09/03/2015.
 */
public class GamePlayer{
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


    public void removeOrAddTicket(Ticket ticket,int n){
        int value = tickets.remove(ticket);
        tickets.put(ticket,value + n);
    }


    public Player getPlayer(){ return player; }

}
