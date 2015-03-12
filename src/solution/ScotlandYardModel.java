package solution;

import scotlandyard.*;
import solution.GamePlayer.GamePlayer;
import solution.GamePlayer.GamePlayerMoveUtilities;

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
    GamePlayerMoveUtilities gamePlayerMoveUtilities;





    public ScotlandYardModel(int numberOfDetectives, List<Boolean> rounds, String graphFileName) throws IOException {
        super(numberOfDetectives, rounds, graphFileName);

        this.numberOfDetectives = numberOfDetectives;
        this.rounds = rounds;

        colourGamePlayerMap = new LinkedHashMap<Colour, GamePlayer>();
        graph = new ScotlandYardGraphReader().readGraph(graphFileName);
        gamePlayerMoveUtilities = new GamePlayerMoveUtilities(this);
        currentPlayer = Colour.Black;
        roundCount = 0;
        mrXLocations = new ArrayList<Integer>();
        mrXLocations.add(0);
    }


    @Override
    protected Move getPlayerMove(Colour colour) {
        GamePlayer gamePlayer = gamePlayerMoveUtilities.findPlayer(colour);
        List<Move> validMoves = validMoves(colour);
        Move move = gamePlayer.getPlayer().notify(getPlayerLocation(colour),validMoves);
        if(validMoves.contains(move)) return move;
        else return new MovePass(colour); //todo look back at specifications this might be wrong
    }

    @Override
    protected void nextPlayer() {
        List<Colour> playerColours = new ArrayList<Colour>(colourGamePlayerMap.keySet());
        int previousIndex = playerColours.indexOf(currentPlayer);

        if (gamePlayerMoveUtilities.findPlayer(currentPlayer).getColour().equals(Colour.Black)) {
            //roundCount++;
            gamePlayerMoveUtilities.mrXLocationUpdateCheck(gamePlayerMoveUtilities.findPlayer(currentPlayer).getLocation());
        }
        if (previousIndex + 1 == numberOfDetectives + 1) {
            currentPlayer = playerColours.get(0);
        }
        else{
            currentPlayer = playerColours.get(previousIndex + 1);
        }
    }

    @Override//removes ticket and adds to mr x, location is updated
    protected void play(MoveTicket move) {
        colourGamePlayerMap.get(move.colour).removeOrAddTicket(move.ticket, -1);
        colourGamePlayerMap.get(move.colour).setLocation(move.target);
        if(Colour.Black !=  move.colour) {
            colourGamePlayerMap.get(Colour.Black).removeOrAddTicket(move.ticket, +1);
        }
        else {
            roundCount++;//todo check this in rulebook,tests say roundcount should go up twice if doublemove is used
        }
    }

    @Override
    protected void play(MoveDouble move) {
        for(int i=0;i <2;i++){
            play(move.moves.get(i));
        }
    }

    @Override
    protected void play(MovePass move) {//todo no idea what this is


    }


    @Override
    protected List<Move> validMoves(Colour player) {
        return gamePlayerMoveUtilities.getMoves(player);
    }

    @Override
    public void spectate(Spectator spectator) {

    }

    @Override
    public boolean join(Player player, Colour colour, int location, Map<Ticket,Integer> tickets) {

        if (colourGamePlayerMap.containsKey(colour))
            return false;
        else if (colourGamePlayerMap.size() == numberOfDetectives + 1) {
            colourGamePlayerMap = gamePlayerMoveUtilities.getSortedMap();
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
    public int getPlayerTickets(Colour colour, Ticket ticket) {
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

    //Hello testing 123 hello world.

}
