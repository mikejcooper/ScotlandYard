package solution;

import scotlandyard.Colour;
import scotlandyard.Move;
import scotlandyard.Player;
import scotlandyard.Ticket;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by MikeCooper on 09/03/15.
 */
public class GamePlayer implements Player {

    Colour colour;
    int location;
    Map<Ticket,Integer> ticket;

    public GamePlayer (Colour colour2, int location2,  Map<Ticket,Integer> ticket2){
        this.colour = colour2;
        this.location = location2;
        this.ticket = ticket2;
    }

    protected void randomStartLocation() {
        Random rn = new Random();
        location = rn.nextInt(ScotlandYardModel.graph.getNodes().size()) + 1;
    }


    protected Colour Colour() {return null;}







    @Override
    public Move notify(int location, List<Move> list) {
        return null;
    }
}
