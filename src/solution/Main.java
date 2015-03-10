package solution;

import scotlandyard.Colour;
import scotlandyard.Move;
import scotlandyard.Player;
import scotlandyard.Ticket;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by MikeCooper on 10/03/15.
 */
public class Main {

        public static void main(String[] args) throws IOException {
            List<Boolean> list = null;
            ScotlandYardModel scotlandYardModel = new ScotlandYardModel(5, list, "graph.txt");

            Player x = new Player() {
                @Override
                public Move notify(int location, List<Move> list) {
                    return null;
                }
            };
            Colour y = Colour.Black;
            int location = 5;
            Map<Ticket,Integer> z = new Map<Ticket, Integer>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean containsKey(Object key) {
                    return false;
                }

                @Override
                public boolean containsValue(Object value) {
                    return false;
                }

                @Override
                public Integer get(Object key) {
                    return null;
                }

                @Override
                public Integer put(Ticket key, Integer value) {
                    return null;
                }

                @Override
                public Integer remove(Object key) {
                    return null;
                }

                @Override
                public void putAll(Map<? extends Ticket, ? extends Integer> m) {

                }

                @Override
                public void clear() {

                }

                @Override
                public Set<Ticket> keySet() {
                    return null;
                }

                @Override
                public Collection<Integer> values() {
                    return null;
                }

                @Override
                public Set<Entry<Ticket, Integer>> entrySet() {
                    return null;
                }
            };
            List<Move> Mart = new GamePlayer(x,y,location,z).getMoves();


            List<Move> r = new GameBoardMoves().getMoves(Colour.Black,7,z);





        }

}
