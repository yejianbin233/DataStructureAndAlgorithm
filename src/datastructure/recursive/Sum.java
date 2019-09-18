package datastructure.recursive;/**
 * Created by Administrator on 2019/9/17.
 */

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Sum
 * @Description 以迭代的方式来计算数组求和
 * @Date 2019/9/17 21:44
 **/
public class Sum {
    public static int sum(int[] arr){
        return sum(arr,0);
    }

    /*
    * 递归 - 将一个大的问题，转化成更小的"同一"问题。
    *
    * 递归的要点：
    * 1. 要处理的数据不断减少
    * 2. 处理递归基
    * */

    // 数组求和的递归算法
    /*
    * [1,2,3,4,5,6,7,8,...,n]
    *
    * 数组的求和为数组内的全部元素一个个相加，可以看做:
     *      arr[0] + sum(arr[1,n]) -> (再将 sum(arr[1,n]) 分解)
     *      arr[0] + arr[1] + sum(arr[1,n]) ->
     *      ...（以此类推）
     *
    * */
    private static int sum(int[] arr,int l){
        // 递归基，当 l == arr.length 则说明已经处理完数组内的全部元素
        if (l == arr.length) {
            // return -> 结束递归
            return 0;
        }

        int sum = arr[l] + sum(arr,l+1);
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(sum(arr));
    }

}
