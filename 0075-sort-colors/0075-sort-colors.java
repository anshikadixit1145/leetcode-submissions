/* *Dtuch National Flag Algorithm
* pattern-> 2 pointers
* 0 -> red, 1-> white, 2-> blue
*sort -n-place -> think of 'swapping' instead of creating another array
* single traversal? -> multiple pointers

* when an array contains three categories and needs to be partitioned in place -> Dutch national flag algo

 */

 class Solution{
    public void sortColors(int[] nums){
        int low = 0;
        int mid = 0;
        int high = nums.length-1;
        while(mid <= high){
            if(nums[mid] == 0){
                swap(nums, low, mid);
                low++;
                mid++;
            }
            else if(nums[mid] == 1){
                mid++;
            }
            else{
                swap(nums, mid, high);
                high--;
            }
        }
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
 }