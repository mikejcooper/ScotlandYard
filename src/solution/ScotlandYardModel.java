package solution;

import scotlandyard.*;

import java.io.IOException;
import java.util.*;

public class ScotlandYardModel extends ScotlandYard {

    private List<Boolean> rounds;
    private Graph graph;
    private Map<Colour, GamePlayer> colourGamePlayerMap = new HashMap<Colour, GamePlayer>();
    private int numberOfDetectives;


    public ScotlandYardModel(int numberOfDetectives, List<Boolean> rounds, String graphFileName) throws IOException {
        super(numberOfDetectives, rounds, graphFileName);
        this.numberOfDetectives = numberOfDetectives;
        this.rounds = rounds;
        ScotlandYardGraphReader graphReader = new ScotlandYardGraphReader();
        this.graph = graphReader.readGraph(graphFileName);



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
    protected List<Move> validMoves(Colour player) { //todo this is new
        return colourGamePlayerMap.get(player).getMoves(graph);
    }

    @Override
    public void spectate(Spectator spectator) {

    }

    @Override
    public boolean join(Player player, Colour colour, int location, Map<Ticket, Integer> tickets) {
        if(colourGamePlayerMap.containsKey(colour)) return false; //todo might cause bugs, or is unnecissary
        colourGamePlayerMap.put(colour, new GamePlayer(player, colour, location, tickets));
        return true;
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
        return null;
    }

    @Override
    public int getRound() {
        return 0;
    }

    @Override
    public List<Boolean> getRounds() { return rounds; }//todo not sure if this is right
}
