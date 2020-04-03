import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Question:
 * A unsorted array of integers is given; you need to find the max product
 * formed my multiplying three numbers.
 * (You cannot sort the array, watch out when there are negative numbers)
 *
 * see: https://www.geeksforgeeks.org/top-25-interview-questions/
 *
 */
public class Product3 {

    // 2,3,5,1,7,1,-9,4,-3

    public static void main(String[] args) {
        final int[] arr = new int[] {2,3,5,1,7,1,-9,4,-3};

        Product3 testClass = new Product3();

        testClass.solveUsingSort(arr);

        int maxProduct = testClass.findMaxProduct3(arr);
        System.out.println(String.format("maxProduct is: %d", maxProduct));
    }

    private int findMaxProduct3(final int[] arr) {

        // 2,3,5,1,7,1,-9,4,-3



        // TOOD:
        return -1;
    }

    // for test-reference only (sorting is not allowed)
    private void solveUsingSort(final int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(list);

        final int size = list.size();

        final int maxFirst2 = list.get(0) * list.get(1);
        final int maxLast2 = list.get(size-2) * list.get(size-3);

        System.out.println(String.format("maxFirst2=%d, maxLast2=%d", maxFirst2, maxLast2));

        final int maxProduct = list.get(size - 1) * Math.max(maxFirst2, maxLast2);

        System.out.println(maxProduct);
    }
}
