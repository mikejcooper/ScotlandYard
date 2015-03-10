package solution;

import scotlandyard.Colour;
import scotlandyard.Player;
import scotlandyard.ScotlandYard;
import scotlandyard.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by MikeCooper on 10/03/15.
 */
public class MrX extends GamePlayer {

    private List travelLog;
    public int visibleLocation;





    public MrX(Player player, Colour colour, int location, Map<Ticket,Integer> tickets) {
        super(player, Colour.Black, location, tickets);
        checkVisibleLocation();
    }

    public void addToTravelLog (int location){
        travelLog.add(location);
    }

    public void checkVisibleLocation () {
        if(ScotlandYardModel.rounds.get(ScotlandYardModel.roundCount)){
            visibleLocation = this.location;
        }
        addToTravelLog(this.location);
    }

    @Override
    public int getLocation() {
        return visibleLocation;
    }

    public void removeTicket (Ticket ticket) {

    }

}

