package solution;

import scotlandyard.*;

import java.io.IOException;
import java.util.*;

public class ScotlandYardModel extends ScotlandYard {

    public static Graph<Integer,Route> graph;
    public static List<Boolean> rounds;
    public static Map<Colour, GamePlayer> colourGamePlayerMap;
    //Linked Hash maps keeps in input order. Map does not.
    private int numberOfDetectives;
    private Colour currentPlayer;
    public static int roundCount;
    SearchUtilities searchUtilities;
    public static List mrXLocations;





    public ScotlandYardModel(int numberOfDetectives, List<Boolean> rounds, String graphFileName) throws IOException {
        super(numberOfDetectives, rounds, graphFileName);

        this.numberOfDetectives = numberOfDetectives;
        this.rounds = rounds;

        colourGamePlayerMap = new HashMap<Colour, GamePlayer>();
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
            searchUtilities.mrXLocationUpdateCheck(searchUtilities.findPlayer(currentPlayer).getLocation());
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
        int check = 0;

        if (colourGamePlayerMap.containsKey(colour))
            check = 0;
        else {
            colourGamePlayerMap.put(colour, new GamePlayer(player, colour, location, tickets));
            check = 1;
        }

        if (colourGamePlayerMap.size() == numberOfDetectives + 1) {
            GamePlayer temp = searchUtilities.findPlayer(Colour.Black);
            searchUtilities.removePlayer(Colour.Black);
            colourGamePlayerMap.put(Colour.Black, temp);
        }

        if(check == 1) return true;
        else return false;
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
