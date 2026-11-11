import java.util.*;

class Solution{
    public List<Integer> majorityElement(int[] nums){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i]) +1);
            }
            else{
                map.put(nums[i], 1);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int x:map.keySet()){
            if(map.get(x) > (nums.length/3)){
                    list.add(x);
            }
        }
        return list;
    }
}