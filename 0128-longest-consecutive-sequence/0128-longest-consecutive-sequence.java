/* array -> unsorted -> sorting / hashing 
* fast existence chech -> HashSet -> O(n) needed
*consecutive numbers -> sequence detection
numbers -> not necessarily need to be adjacent.
* longest conitnuous 'subsequence' not 'subarray'

* TRICK: when we repeatedly want to check whether a particualr value exists in an unsorted array -> HashSet (collection that stores unique elements)

*step 1: build a HashSet
*step 2: identify the sequence beginnings
*step 3: count the consecutive elements
*/

import java.util.*;

public class Solution{
    public int longestConsecutive(int[] nums){
        HashSet<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }
        int maxLength = 0;

        for(int num : set){
            if(!set.contains(num-1)){
                int currentNum = num;
                int count = 1;

                while(set.contains(currentNum + 1)){
                    currentNum++;
                    count++;
                } 
                maxLength = Math.max(maxLength, count);
            }
        }
        return maxLength;
    }
}