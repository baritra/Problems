package linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Communities {
    List<Member> members = new ArrayList<>();


    public Communities(int numMembers) {
        members.add(new Member(0));
        for (int i = 1; i <= numMembers; i++) {
            members.add(new Member(i));
        }
    }

    public void merge(int i, int j) {
        members.get(i).merge(members.get(j));

    }

    public int getSize(int i) {
        return members.get(i).getCommunitySize();
    }


    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Scanner lineScanner = new Scanner(s);
        lineScanner.useDelimiter(" ");
        int numMembers = lineScanner.nextInt();
        int numQueries = lineScanner.nextInt();
        Communities communities = new Communities(numMembers);

        List<Integer> answers = new ArrayList<>();

        for (int i = 0; i < numQueries; i ++) {
            String line = scanner.nextLine();
            lineScanner = new Scanner(line);
            String command = lineScanner.next();
            if ("Q".equals(command)) {
                int member = lineScanner.nextInt();
                int communitySize = communities.getSize(member);
                System.out.println(communitySize);
                //answers.add(communitySize);
            } else if ("M".equals(command)) {
                communities.merge(lineScanner.nextInt(), lineScanner.nextInt());
            }
        }
        /*for (int answer : answers) {
            System.out.println(answer);
        }*/

    }
}

class Member {
    Member parent;
    int val;
    Member lastFurthestParent;

    public Member(int val) {
        this.val = val;
        this.lastFurthestParent = this;
    }
    int size = 1;

    public void merge(Member child) {
        Member parent1 = this.getFurthestParent();
        Member parent2 = child.getFurthestParent();
        if (parent1 != parent2) {
            if (parent1.size > parent2.size) {
                parent2.parent = parent1;
                parent1.size += parent2.size;
            } else {
                parent1.parent = parent2;
                parent2.size += parent1.size;
            }
        }
    }

    public Member getFurthestParent() {
        int i = 0;
        Member furthestParent = this;
        while (furthestParent.parent != null) {
            i++;
            furthestParent = furthestParent.parent;
        }
        //System.out.println("levels = " + i);
        //reparent
        if (furthestParent != this) {
            this.parent = furthestParent;
        }
        return furthestParent;
    }

    public int getCommunitySize() {
        return getFurthestParent().size;
    }

    @Override
    public String toString() {
        return ""+ val;
    }
}

