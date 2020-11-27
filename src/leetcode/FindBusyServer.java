package leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/
 */
public class FindBusyServer {
   /* public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Server> sortedByNextAvailable = new TreeSet<Server>((s1,s2) -> {
            return s1.nextAvailable == s2.nextAvailable ? s1.indentifier - s2.indentifier : s1.nextAvailable - s2.nextAvailable;
        });
        for (int i = 0; i < k; i++) {
            sortedByNextAvailable.add(new Server(i));
        }

        for (int i = 0; i < arrival.length; i++) {
            int time = arrival[i];
            int duration = load[i];

        }
    }*/

    static class Server {
        int indentifier;
        int workDone = 0;
        int nextAvailable = 1;
        public Server(int identifier) {
            this.indentifier = identifier;
        }
        public void assign(int time, int load) {
            this.workDone++;
            this.nextAvailable = time + load;
        }
        public boolean isAvailable(int time) {
            return time >= nextAvailable;
        }
    }
}
