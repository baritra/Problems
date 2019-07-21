package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassSchedule {
    /**
     https://leetcode.com/problems/course-schedule-ii/submissions/
     **/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            matrix[prerequisites[i][0]][prerequisites[i][1]] = 1;
        }

        int[] order = new int[numCourses];
        List<Integer> dfsOrder = new ArrayList<>();
        Map<Integer, Integer> visitStatus = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            visitStatus.put(i, 0);
        }
        for (int i = 0; i < numCourses; i++) {
            if (visitStatus.get(i) == 0) {
                if (!dfs(i, matrix, visitStatus, dfsOrder))  {
                    return new int[0];
                }
            }
        }
        for (int i = 0; i < dfsOrder.size(); i++) {
            order[i] = dfsOrder.get(i);
        }
        return order;
    }

    private boolean dfs(int course, int[][] matrix, Map<Integer, Integer> visitStatus, List<Integer> order) {
        if (visitStatus.get(course) == 1) {
            return false;
        }
        if (visitStatus.get(course) == 2) {
            return true;
        }

        visitStatus.put(course, 1);
        for (int j = 0; j < matrix[course].length; j++) {
            if (matrix[course][j] == 1) {
                //dependency
                if (!dfs(j, matrix, visitStatus, order))  {
                    return false;
                }
            }
        }

        visitStatus.put(course, 2);
        order.add(course);
        return true;

    }
}
