//solved using the sorting technique, the most optimal TC achievable via sorting is O(nlogn), which can be implemented using either the Quick Sort or the Merge Sort(the one used down below)

import java.util.*;
class Solution {

    public int majorityElement(int[] nums){
        return majorityElement(nums, 0, nums.length-1);
    }
    public static int majorityElement(int[] nums, int si, int ei) {
        
        //base case
        if(si == ei){
            return nums[si];
        }
        //recursive calls
        int mid = si+(ei-si)/2;
        int leftMajority = majorityElement(nums, si, mid);
        int rightMajority = majorityElement(nums, mid+1, ei);

        if(leftMajority == rightMajority){
            return leftMajority;
        }

        int leftCount = countInRange(nums, si, ei, leftMajority);
        int rightCount = countInRange(nums, si, ei, rightMajority);

        int n = ei-si+1;
        if(leftCount > n/2){
            return leftMajority;
        }
        else{
            return rightMajority;
        }
    }

    public static int countInRange(int[] nums, int si, int ei, int target){

        int count=0;
        for(int i=si; i<=ei; i++){
            if(nums[i] == target){
                count++;
            }
        }
        return count;
    }
}