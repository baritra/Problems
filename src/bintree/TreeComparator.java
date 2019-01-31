package bintree;

public class TreeComparator {

    public boolean IsTargetSubtreeOfSource(BinTree t, BinTree s) {
        return false;

    }

    public boolean areTreesIdentical(BNode n1, BNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 != null && n2 != null && n1.getValue() == n2.getValue()) {
            return areTreesIdentical(n1.getLeft(), n2.getLeft()) && areTreesIdentical(n1.getRight(), n2.getRight());
        } else return false;

    }
}
