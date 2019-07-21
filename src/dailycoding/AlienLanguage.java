package dailycoding;

import java.util.*;

public class AlienLanguage {
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            deriveOrder(words[i], words[i + 1], graph);
        }
        LinkedList<Character> rtop = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        for (Character c : graph.keySet()) {
            dfs(c, graph, visited, rtop);
        }

        String result = "";
        for (char c : rtop) {
            result += c;
        }
        return result;
    }

    private void dfs(char c, Map<Character, List<Character>> relationships, Set<Character> visited, LinkedList<Character> rtopsort) {
        if (visited.contains(c)) {
            return;
        }
        if (relationships.containsKey(c)) {
            for (char l : relationships.get(c)) {
                dfs(l, relationships, visited, rtopsort);
            }
        }
        visited.add(c);
        rtopsort.addFirst(c);
    }

    private void deriveOrder(String larger, String smaller, Map<Character, List<Character>> relationships) {
        int i = 0, j = 0;
        while (i < larger.length() && j < smaller.length()) {
            if (larger.charAt(i) == smaller.charAt(j)) {
                i++;
                j++;
            } else {
                if (!relationships.containsKey(larger.charAt(i))) {
                    relationships.put(larger.charAt(i), new ArrayList<>());
                }
                relationships.get(larger.charAt(i)).add(smaller.charAt(j));
                break;
            }
        }
    }

/*
    public static void main(String[] args) {
        String[] dict = {"wrt","wrf","er","ett","rftt"};
        System.out.println(new AlienLanguage().alienOrder(dict));
    }
*/


}
