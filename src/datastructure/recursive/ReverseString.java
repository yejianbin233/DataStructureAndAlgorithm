package datastructure.recursive;/**
 * Created by Administrator on 2019/9/18.
 */

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName ReverseString
 * @Description Leetcode 递归练习 - 以相反的顺序打印字符串。
 * @Date 2019/9/18 21:41
 **/
public class ReverseString {

    public static void reverseString(String str){
        reverseString(str,0);
    }
    private static void reverseString(String str,int index){
        // 当递归到字符串的最后一个字符时，才打印输出第一个字符，然后 return，结束继续调用自身的递归操作
        if (index == str.length()-1) {
            System.out.println(str.charAt(index));
            return;
        }
        // 两种递归基都可以得出正确的结果
//        if (index == str.length()) {
//            return;
//        }

        // 递归
        reverseString(str,index+1);
        // 反转打印
        System.out.println(str.charAt(index));
    }

    public static void main(String[] args) {
//        String s = "123456789";
        char[] s = {'1','2','3','4','5','6','7','8','9'};
        new ReverseString().reverseString(s);
        for(int i=0;i<s.length;i++){
            System.out.println(s[i]);
        }
    }

    // Leetcode - 反转字符串
    public void reverseString(char[] s) {
        // 测试一下 Leetcode 是否会辨别没有使用递归方式（运行成功...）
//        for(int i=s.length-1; i>=0;i--){
//            System.out.println(s[i]);
//        }

        // 解题方式与 reverseString(String str,int index) 一致，使用递归方式
        reverseString(s,0);
    }

    private void reverseString(char[] s,int index){
        if (index >= s.length) {
            return;
        }
        char temp = s[s.length-1-index];
        reverseString(s,index+1);
        s[index] = temp;
    }
}
