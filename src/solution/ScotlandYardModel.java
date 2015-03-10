package solution;

import scotlandyard.*;

import java.io.IOException;
import java.util.*;

public class ScotlandYardModel extends ScotlandYard {

    public static Graph<Integer,Route> graph;
    public static List<Boolean> rounds;
    public static LinkedHashMap<Colour, GamePlayer> colourGamePlayerMap;
    //Linked Hash maps keeps in input order. Map does not.
    private int numberOfDetectives;
    private Colour currentPlayer;
    public static int roundCount;
    SearchUtilities searchUtilities;





    public ScotlandYardModel(int numberOfDetectives, List<Boolean> rounds, String graphFileName) throws IOException {
        super(numberOfDetectives, rounds, graphFileName);

        this.numberOfDetectives = numberOfDetectives;
        this.rounds = rounds;

        colourGamePlayerMap = new  LinkedHashMap<Colour,GamePlayer>();
        graph = new ScotlandYardGraphReader().readGraph(graphFileName);
        searchUtilities = new SearchUtilities();
        currentPlayer = Colour.Black;
        roundCount = 0;
    }



    @Override
    protected Move getPlayerMove(Colour colour) {
        GamePlayer player = searchUtilities.findPlayer(colour);

        return player.notify(getPlayerLocation(colour),validMoves(colour));
    }

    @Override
    protected void nextPlayer() {
        List<Colour> playerColours = new ArrayList<Colour>(colourGamePlayerMap.keySet());

        int previousIndex = playerColours.indexOf(currentPlayer);

        if (previousIndex == playerColours.size()) {
            currentPlayer = playerColours.get(0);
            roundCount++;
            for (GamePlayer gamePlayer : colourGamePlayerMap.values()) {
                if(gamePlayer instanceof MrX){
                    ((MrX) gamePlayer).checkVisibleLocation();
                }
            }
        }
        else{
            currentPlayer = playerColours.get(previousIndex + 1);
        }
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
        return colourGamePlayerMap.get(player).getMoves();
    }

    @Override
    public void spectate(Spectator spectator) {

    }

    @Override
    public boolean join(Player player, Colour colour, int location, Map<Ticket,Integer> tickets) {

        if (colourGamePlayerMap.containsKey(colour)) return false;
        else if (colour == Colour.Black) {
            colourGamePlayerMap.put(colour, new MrX(player, colour, location, tickets));
            return true;
        }
        //ensure MrX is on top of Map 'Stack'
        else if (colourGamePlayerMap.size() == numberOfDetectives + 1) {
            GamePlayer temp = searchUtilities.findPlayer(Colour.Black);
            searchUtilities.removePlayer(Colour.Black);
            colourGamePlayerMap.put(Colour.Black, temp);
            return true;
        }
        else {
            colourGamePlayerMap.put(colour, new GamePlayer(player, colour, location, tickets));
            return true;
        }
    }

    @Override
    public List<Colour> getPlayers() {
        return new ArrayList<Colour>(colourGamePlayerMap.keySet());
    }

    @Override
    public Set<Colour> getWinningPlayers() {
        return null;
    }

    @Override
    public int getPlayerLocation(Colour colour) {
        return colourGamePlayerMap.get(colour).getLocation();
    }

    @Override
    public int getPlayerTickets(Colour colour, Ticket ticket) {//todo this is new
        return colourGamePlayerMap.get(colour).getNumberOfTicket(ticket);

    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public boolean isReady() {
        if(1 + numberOfDetectives > colourGamePlayerMap.size()) return false;
        else return true;
    }

    @Override
    public Colour getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public int getRound() {
        return roundCount;
    }

    @Override
    public List<Boolean> getRounds() {
        return rounds; }
}
