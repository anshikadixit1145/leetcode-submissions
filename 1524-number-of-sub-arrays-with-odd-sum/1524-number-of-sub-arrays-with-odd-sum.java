class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int evenCount = 1;
        int oddCount = 0;
        int prefix = 0;
        long result = 0;

        for(int x : arr){
            prefix = (prefix + x) % 2;

            if(prefix == 1){
                result += evenCount;
                oddCount++;
            }
            else{
                result += oddCount;
                evenCount++;
            }
            result %= MOD;
        }
        return (int) result;
    }
}