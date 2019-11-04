package algorithm;/**
 * Created by Administrator on 2019/11/4.
 */

import java.util.Arrays;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName BinarySearch
 * @Description 二分查找
 * @Date 2019/11/4 14:36
 **/
public class BinarySearch {
    // 需要传递一个有序的数组
    // 递归方式
    public static int rank(int[] array, int key) {
        return rank(array, key, 0, array.length - 1);
    }

    private static int rank(int[] array, int key, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (array[mid] > key)
            return rank(array, key, lo, mid);
        else if (array[mid] < key)
            return rank(array, key, mid + 1, hi);
        else
            return array[mid];
    }

    // 迭代方式
    public static int rankIterator(int[] array, int key) {
        int lo = 0;
        int hi = array.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (array[mid] > key)
                hi = mid;
            else if (array[mid] < key)
                lo = mid+1;
            else
                return array[mid];
        }
        return -1;
    }

    public static void main(String[] args) {
        // 模拟有序数组
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        System.out.println(rank(arr, 1));
        System.out.println(rankIterator(arr,8));
    }
}
