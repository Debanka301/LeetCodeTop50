package Binary_Search;
/*
Given an array of integers nums sorted in non-decreasing order,
find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109

Algorithm : Search till the first index on LHS to find First Occurrence,
            Search till the last index on RHS to find Last Occurrence.

*/

import java.util.Arrays;

class SolutionLC34 {
    public int findFirst(int[] nums, int target){
        int left = 0;
        int right= nums.length-1;
        int result = -1;
        while(left <= right){
            int mid = (left+right)/2;
            if(nums[mid] == target){
                result = mid;
                right = mid-1;
            }else if(target > nums[mid]){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return result;
    }

    public int findLast(int[] nums, int target){
        int left = 0;
        int right= nums.length-1;
        int result = -1;
        while(left <= right){
            int mid = (left+right)/2;
            if(nums[mid] == target){
                result = mid;
                left = mid+1;
            }else if(target > nums[mid]){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return result;
    }

    public int[] searchRange(int[] nums, int target) {
        int ans[] ={-1,-1};
        ans[0] = findFirst(nums,target);
        ans[1] = findLast(nums,target);

        return ans;
    }
}

public class LC34 {
    public static void main(String[] args) {
        int nums[] = {5,7,7,8,8,10};
        int target = 8;
        SolutionLC34 solutionLC34 = new SolutionLC34();
        System.out.println(Arrays.asList(solutionLC34.searchRange(nums,target)));
    }
}
