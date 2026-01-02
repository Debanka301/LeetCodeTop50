package Stack;

/*
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is
the number of days you have to wait after the ith day to get a warmer temperature.
If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100

Solution : I solved the Daily Temperatures problem using a monotonic decreasing stack.
I traversed the array from right to left, storing indices in the stack. For each day, I popped indices from the stack while their temperatures
were less than or equal to the current day’s temperature, ensuring the stack always represented warmer future days.
After popping, if the stack was empty, it meant no warmer day existed, so the answer was 0.
Otherwise, the difference between the stack’s top index and the current index gave the number of days to wait.
Each index was pushed once, ensuring O(n) time complexity.
 */

import java.util.Stack;

class SolutionLC739 {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int ans[] = new int[temperatures.length];
        int k=temperatures.length -1;
        for(int i=temperatures.length-1;i>=0;i--){
            if(stack.isEmpty()){
                stack.push(i);
                ans[k--] = 0;
            }
            else{
                while(!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]){
                    stack.pop();
                }
                if(stack.isEmpty()){
                    stack.push(i);
                    ans[k--] = 0;
                }else{
                    ans[k--] = stack.peek() - i;
                    stack.push(i);
                }
            }
            System.out.println(stack);
        }
        return ans;
    }
}

public class LC739 {

    public static void main(String[] args) {
        SolutionLC739 solution = new SolutionLC739();
        int temperatures[] = {89,62,70,58,47,47,46,76,100,70};
        int arr[] = solution.dailyTemperatures(temperatures);
        for(int i =0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

}
