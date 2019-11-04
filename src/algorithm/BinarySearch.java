package algorithm;

import java.util.Arrays;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName BinarySearch
 * @Description 二分查找
 * @Date 2019/11/4 14:36
 **/
/*
*
* 二分查找的思路：通过比较有序数组的中位数，判断目标的所在区间，不断缩小（二分）查找的范围直到找到对应的元素。
*
* 时间复杂度：O(log n)
*
* 步骤：
* 1. 将有序数组的中点下标 array[mid] 与 key 比较，存在三种情况：
*    1. array[mid] == key，找到目标元素，返回该数组中的该元素 array[mid]
*    2. array[mid] > key，说明 key 应该去有序数组的左区间 [lo,mid) 查找
*    3. array[mid] < key，说明 key 应该去有序数组的右区间 [mid+1,hi] 查找
*
* 2. 如果没有查找到对应的目标元素，则返回 -1
* **/
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
