package solution;

import scotlandyard.*;

import java.io.IOException;
import java.util.*;

public class ScotlandYardModel extends ScotlandYard {

    private Graph<Integer,Route> graph;
    private List<Boolean> rounds;
    private Map<Colour, GamePlayer> colourGamePlayerMap;
    private int numberOfDetectives;
    private Colour currentPlayer;
    private int roundCount;
    private List<Integer> mrXLocations;
    SearchUtilities searchUtilities;





    public ScotlandYardModel(int numberOfDetectives, List<Boolean> rounds, String graphFileName) throws IOException {
        super(numberOfDetectives, rounds, graphFileName);

        this.numberOfDetectives = numberOfDetectives;
        this.rounds = rounds;

        colourGamePlayerMap = new LinkedHashMap<Colour, GamePlayer>();
        graph = new ScotlandYardGraphReader().readGraph(graphFileName);
        searchUtilities = new SearchUtilities(this);
        currentPlayer = Colour.Black;
        roundCount = 0;
        mrXLocations = new ArrayList<Integer>();
    }


    @Override
    protected Move getPlayerMove(Colour colour) {
        GamePlayer gamePlayer = searchUtilities.findPlayer(colour);

        return gamePlayer.getPlayer().notify(getPlayerLocation(colour),validMoves(colour));
    }

    @Override
    protected void nextPlayer() {
        List<Colour> playerColours = new ArrayList<Colour>(colourGamePlayerMap.keySet());

        int previousIndex = playerColours.indexOf(currentPlayer);

        if (previousIndex + 1 == numberOfDetectives + 1) {
            currentPlayer = playerColours.get(0);
        }
        else{
            currentPlayer = playerColours.get(previousIndex + 1);
        }


        if (searchUtilities.findPlayer(currentPlayer).equals(Colour.Black)) {
            roundCount++;
            searchUtilities.mrXLocationUpdateCheck(searchUtilities.findPlayer(currentPlayer).getLocation());
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
        return searchUtilities.getMoves(player);
    }

    @Override
    public void spectate(Spectator spectator) {

    }

    @Override
    public boolean join(Player player, Colour colour, int location, Map<Ticket,Integer> tickets) {

        if (colourGamePlayerMap.containsKey(colour))
            return false;
        else if (colourGamePlayerMap.size() == numberOfDetectives + 1) {
            colourGamePlayerMap = searchUtilities.getSortedMap();
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

    public Map<Colour, GamePlayer> getColourGamePlayerMap() {
        return colourGamePlayerMap; }

    public Graph<Integer,Route> getGraph() {
        return graph; }

    public int getRoundCount() {
        return roundCount; }

    public List<Integer> getMrXLocations(){
        return mrXLocations;
    }


}
