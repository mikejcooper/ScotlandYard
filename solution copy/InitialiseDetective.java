package solution;

import scotlandyard.Colour;
import scotlandyard.Player;

import java.util.Random;

/**
 * Created by MikeCooper on 08/03/15.
 */
public class InitialiseDetective {



    protected void InitialiseDetective() {
//        Player.class.notify();
//        Colour.Black;
        randomStartLocation();


    }



    protected int randomStartLocation() {
        Random rn = new Random();
        return rn.nextInt(ScotlandYardModel.graph.getNodes().size()) + 1;
    }

//    protected Player player() {
//        return ;
//    }

    protected Colour Colour() {
        return Colour.Black;
    }

}
