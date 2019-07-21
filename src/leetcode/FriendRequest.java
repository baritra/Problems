package leetcode;

public class FriendRequest {

    public int numFriendRequests(int[] ages) {
        int[][]numEqualOrOlder = new int[121][1];

        for (int i = 0; i < ages.length; i++) {
            numEqualOrOlder[ages[i]][0] = numEqualOrOlder[ages[i]][0] + 1;

        }

        for (int j = 120; j >= 1; j--) {
            int greater = j < 120 ? 0 : numEqualOrOlder[j+1][0];
            numEqualOrOlder[j][0] = numEqualOrOlder[j][0] + greater;
        }

        int friends = 0;
        for (int i = 0; i < ages.length; i++) {
            int age = ages[i];
            int smallestAgeToBeFriend = (int) Math.ceil(age*0.5 + 7);
            friends += numEqualOrOlder[smallestAgeToBeFriend][0] - numEqualOrOlder[age][0];
        }

        return friends;

    }
/*
    public static void main(String[] args) {
        int[] ages = {}
        new FriendRequest().numFriendRequests()
    }*/
}
