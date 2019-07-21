package linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    int maxSize;
    int curSize;

    public LRU(int maxSize) {
        this.maxSize = maxSize;
    }

    Map<String, DNode<Data>> table = new HashMap<>();

    DList<Data> dlist = new DList<Data>();

    public String get(String key) {
        String value = null;
        if (key != null && table.containsKey(key)) {
            DNode<Data> node = table.get(key);
            dlist.moveToTail(node);
            value = node.data.val;
        }
        System.out.println("After get");
        dlist.print();
        System.out.println();
        return value;
    }

    public void put(String key, String val) {
        if (curSize == maxSize) {
            DNode<Data> nodeToRemove = dlist.removeHead();
            if (nodeToRemove != null) {
                String keyToRemove = nodeToRemove.data.key;
                table.remove(keyToRemove);
            }
            curSize --;
        }

        DNode<Data> newNode = dlist.append(new Data(key, val));
        table.put(key, newNode);
        System.out.println("After put");
        dlist.print();
        System.out.println();

        curSize++;
    }

    class Data {
        String key;
        String val;

        public Data(String key, String val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "key='" + key + '\'' +
                    ", data='" + val + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        LRU lru = new LRU(3);
        lru.put("a", "aritra");
        lru.put("b", "boni");
        lru.put("c", "chiro");
        lru.put("s", "sadhana");
        lru.get("b");
        lru.put("r", "rita");
    }
}
