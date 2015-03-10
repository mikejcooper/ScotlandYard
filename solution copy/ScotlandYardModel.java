package solution;

import scotlandyard.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.lang.String;

public class ScotlandYardModel extends ScotlandYard {

   public Map<Colour, GamePlayer> mGameMap;

    public static void main(String[] args) throws IOException {

        List<Boolean> X = null;
            ScotlandYardModel x = new ScotlandYardModel(5,X, "graph.txt");
    }





    public static Graph<Integer,Route> graph;

    public ScotlandYardModel(int numberOfDetectives, List<Boolean> rounds, String graphFileName) throws IOException {

        super(numberOfDetectives, rounds, graphFileName);

        graph = new ScotlandYardGraphReader().readGraph(graphFileName);


        for (int i = 0; i<numberOfDetectives; i++) {

        }
    }



    protected GamePlayer createMrX () {
        

        return null;
    }

    @Override
    protected Move getPlayerMove(Colour colour) {
        return null;
    }

    @Override
    protected void nextPlayer() {

    }

    @Override
    protected void play(MoveTicket move) {

    }

    @Override
    protected void play(MoveDouble move) {

    }

    @Override
    protected void play(MovePass move) {

    }

    @Override
    protected List<Move> validMoves(Colour player) {
        PlayerMoves playerMoves = new PlayerMoves(1,player);
        return playerMoves.getMoves();
    }


    @Override
    public void spectate(Spectator spectator) {

    }

    @Override
    public boolean join(Player player, Colour colour, int location, Map<Ticket,Integer> ticket) {
        mGameMap.put(colour, new GamePlayer(colour,location,ticket));
        return true;
    }

    @Override
    public List<Colour> getPlayers() {
        return null;
    }

    @Override
    public Set<Colour> getWinningPlayers() {
        return null;
    }

    @Override
    public int getPlayerLocation(Colour colour) {
        return 0;
    }

    @Override
    public int getPlayerTickets(Colour colour, Ticket ticket) {
        return 0;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public Colour getCurrentPlayer() {
        return null;
    }

    @Override
    public int getRound() {
        return 0;
    }

    @Override
    public List<Boolean> getRounds() {
        return null;
    }
}
