package solution.Model;

import scotlandyard.*;
import solution.Model.GamePlayer.GamePlayer;
import solution.Model.GamePlayer.GamePlayerMoveUtilities;

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
    private List<Spectator> spectators;


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
        spectators = new ArrayList<Spectator>();
    }


    @Override
    protected Move getPlayerMove(Colour colour) {
        GamePlayer gamePlayer = gamePlayerMoveUtilities.findPlayer(colour);
        return gamePlayer.getPlayer().notify(gamePlayer.getLocation(), validMoves(colour));
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
    }

    @Override//removes ticket and adds to mr x, location is updated
    protected void play(MoveTicket move) {
        //todo doubleMove tocken removal
        colourGamePlayerMap.get(move.colour).removeOrAddTicket(move.ticket, -1);
        colourGamePlayerMap.get(move.colour).setLocation(move.target);
        if(Colour.Black ==  move.colour) {
            roundCount++;
            gamePlayerMoveUtilities.mrXLocationUpdateCheck(gamePlayerMoveUtilities.findPlayer(Colour.Black).getLocation());
            notifyAllSpectators(new MoveTicket(Colour.Black,getPlayerLocation(Colour.Black),move.ticket));
        }
        else {
            colourGamePlayerMap.get(Colour.Black).removeOrAddTicket(move.ticket, +1);
            notifyAllSpectators(move);
        }
    }

    @Override
    protected void play(MoveDouble move) {
        notifyAllSpectators(move);
        colourGamePlayerMap.get(move.colour).removeOrAddTicket(Ticket.DoubleMove,-1);
        play(move.moves.get(0));
        play(move.moves.get(1));
    }

    @Override
    protected void play(MovePass move) {
        notifyAllSpectators(move);
    }


    @Override
    protected List<Move> validMoves(Colour player) {
        return gamePlayerMoveUtilities.getMoves(player,gamePlayerMoveUtilities.findPlayer(player).getLocation());
    }

    @Override
    public void spectate(Spectator spectator) {
        spectators.add(spectator);
    }

    public void notifyAllSpectators(Move move){
        for(Spectator s : spectators){
            s.notify(move);
        }
    }

    @Override
    public boolean join(Player player, Colour colour, int location, Map<Ticket,Integer> tickets) {

        if (colourGamePlayerMap.containsKey(colour))
            return false;
        else {
            colourGamePlayerMap.put(colour, new GamePlayer(player, colour, location, tickets));
            if (colourGamePlayerMap.size() == numberOfDetectives + 1) {
                colourGamePlayerMap = gamePlayerMoveUtilities.getSortedMap();
                gamePlayerMoveUtilities.mrXLocationUpdateCheck(gamePlayerMoveUtilities.findPlayer(Colour.Black).getLocation());
            }
            return true;
        }
    }

    @Override
    public List<Colour> getPlayers() {
        return new ArrayList<Colour>(colourGamePlayerMap.keySet());
    }

    @Override
    public Set<Colour> getWinningPlayers() {
        Set<Colour> winningPlayers = new HashSet<Colour>();
        if((roundCount + 1 == rounds.size() && currentPlayer == Colour.Black) || areAllDetectivesStuck()) {
            winningPlayers.add(Colour.Black);
        }
        if(getPlayerMove(Colour.Black) == null || isMrXCaught()){
            winningPlayers.addAll(colourGamePlayerMap.keySet());
            winningPlayers.remove(Colour.Black);
        }
        return winningPlayers;
    }

    @Override
    public int getPlayerLocation(Colour colour) {
        if(colour.equals(Colour.Black)){
            return getMrXLocations().get(getMrXLocations().size() - 1);
            }
        else {
            return colourGamePlayerMap.get(colour).getLocation();
        }
    }

    @Override
    public int getPlayerTickets(Colour colour, Ticket ticket) {
        return colourGamePlayerMap.get(colour).getNumberOfTicket(ticket);
    }

    @Override
    public boolean isGameOver() {
        if(!isReady()) return false;

        if(areTicketMapsEmpty()) return true;
        if(roundCount + 1 == rounds.size() && currentPlayer == Colour.Black) return true;
        if(getPlayerMove(Colour.Black) == null) return true;
        if(isMrXCaught()) return true;

        return false;
    }

    @Override
    public boolean isReady() {
        return 1 + numberOfDetectives == colourGamePlayerMap.size();
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

    private boolean areTicketMapsEmpty(){
        for(Colour colour : colourGamePlayerMap.keySet()){
            if(!isMapEmpty(colour) && colour != Colour.Black){
                return false;
            }
        }
        return true;
    }

    private boolean isMapEmpty(Colour colour){
        Map<Ticket,Integer> map = colourGamePlayerMap.get(colour).getTickets();
        for(Ticket ticket: map.keySet()){
            if(map.get(ticket) != 0) return false;
        }
        return true;
    }

    private boolean isMrXCaught(){
        int mrX = colourGamePlayerMap.get(Colour.Black).getLocation();
        for(GamePlayer gamePlayer: colourGamePlayerMap.values()){
            if(gamePlayer.getLocation() == mrX && gamePlayer.getColour() != Colour.Black) return true;
        }
        return false;
    }

    private boolean areAllDetectivesStuck(){
        for(Colour colour : colourGamePlayerMap.keySet()){
            if(colour != Colour.Black && getPlayerMove(colour) != null) return false;
        }
        return true;
    }

}
