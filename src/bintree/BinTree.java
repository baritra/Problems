package bintree;

import java.util.ArrayList;
import java.util.List;

public class BinTree {

    //Serialize from 1(2,3(4,5))
    public GNode deserialize(String s) {
        return deserializeChildren(s, 0).nodes.get(0);
    }


    private ParseState deserializeChildren(String s, int position) {
       int i = position;
       List<GNode> nodes = new ArrayList<>();
       while (i < s.length()) {
           char c = s.charAt(i);
           GNode lastNode = null;
           if (Character.isDigit(c)) {
               int value = Integer.parseInt(""+c);
               lastNode = new GNode(value);
               nodes.add(lastNode);
               i++;

           }
           else if (c == '(') {
               ParseState state = deserializeChildren(s, i+1);
               for (GNode node : state.nodes) {
                   if (lastNode != null) {
                       lastNode.getChildren().add(node);
                   }
               }
               i = state.lastPosition;
           }
           else if (c == ',') {
               //do nothing
               i++;
           }
           else if (c == ')') {
               ParseState state = new ParseState();
               state.nodes = nodes;
               state.lastPosition = i + 1;
               return state;
           }
       }
        ParseState state = new ParseState();
        state.nodes = nodes;
        state.lastPosition = i;
        return state;
    }

    class ParseState {
        List<GNode> nodes;
        int lastPosition;
    }


    public static void main(String[] args) {
        BinTree binTree = new BinTree();
        GNode node = binTree.deserialize("1(2,3(4,5))");

    }

}
