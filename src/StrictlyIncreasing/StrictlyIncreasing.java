package StrictlyIncreasing;

import java.util.*;

public class StrictlyIncreasing {

    //Returns all the subsequences that occur in an array
    public static List<int[]> strictlyIncreasing(int[] arr) {
        List<int[]> result = new ArrayList<>();
        int n = arr.length;
        if (n == 0) return result;

        int start = 0;
        for (int i = 1; i <= n; i++) {
            if (i == n || arr[i] <= arr[i - 1]) {
                if (i - start > 1) {
                    int[] subArray = Arrays.copyOfRange(arr, start, i);
                    result.add(subArray);
                }
                start = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 2};
        List<int[]> res = strictlyIncreasing(arr);

        System.out.println("The ascending subsequences:");
        for (int[] sub : res) {
            System.out.println(Arrays.toString(sub));
        }
    }
}
