package Stack;

import java.util.Arrays;
import java.util.Stack;

/*
Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to
find its next greater number. If it doesn't exist, return -1 for this number.



Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number.
The second 1's next greater number needs to search circularly, which is also 2.
Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]


Constraints:

1 <= nums.length <= 104
-109 <= nums[i] <= 109

 */
class Solution503 {

    /* My Implementation
    =======================================================================================================================================================
    My Algorithm : I solved LeetCode 503 by explicitly handling the circular nature of the array through duplication.
    I created a temporary array of size 2n−1 by appending the original array (excluding the last element) to itself.
    Then, I applied a monotonic decreasing stack while traversing from right to left to compute the next greater element for each index.
    For every element, smaller or equal values were popped from the stack, and the top of the stack represented the next greater element if it existed.
    Finally, I returned only the first n results corresponding to the original array.

    Problem : Space is not optimised
    ======================================================================================================================================================

    public int[] nextGreaterElements(int[] nums) {
        int temp[] = new int[(2*nums.length)-1];
        int k=0;
        while(k<nums.length){
            temp[k] = nums[k];
            k++;
        }
        int p = k;
        k=0;
        while(k<nums.length-1){
            temp[p++] = nums[k];
            k++;
        }
        int ans[] = new int[temp.length];
        Stack<Integer> stack = new Stack<>();
        int n= ans.length-1;
        for(int i=temp.length-1;i>=0;i--){
            if(stack.isEmpty()){
                stack.push(i);
                ans[n--] = -1;
            }else{
                while(!stack.isEmpty() && temp[stack.peek()] <= temp[i]){
                    stack.pop();
                }
                if(stack.isEmpty()){
                    stack.push(i);
                    ans[n--] = -1;
                }else{
                    ans[n--] = temp[stack.peek()];
                    stack.push(i);
                }
            }
        }
        return Arrays.copyOfRange(ans,0,nums.length);
    }



    Optimal Approach using less memory:-
    ======================================================================================================================================================================
    Optimised Algorithm : The optimised solution avoids array duplication by simulating circular traversal using modulo indexing.
    It iterates from 2n−1 to 0, accessing elements with i % n. A monotonic decreasing stack stores potential next greater elements.
    During the first pass, the stack is built, and during the second pass (i < n), results are populated.
    This ensures each element finds its next greater element in circular order.
    The approach achieves O(n) time and O(n) space complexity while improving readability, reducing memory usage, and following the standard interview-preferred pattern.
    ======================================================================================================================================================================

     */

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * n - 1; i >= 0; i--) {  //1 2 1
            int idx = i % n;

            while (!stack.isEmpty() && stack.peek() <= nums[idx]) {
                stack.pop();
            }

            if (i < n) {
                ans[idx] = stack.isEmpty() ? -1 : stack.peek();
            }

            stack.push(nums[idx]);
        }

        return ans;
    }
}
public class LC503 {
    public static void main(String[] args) {
        Solution503 solution503 = new Solution503();
        int nums[] = {1,2,3,4,3};
        System.out.println(Arrays.toString(solution503.nextGreaterElements(nums)));
    }
}
