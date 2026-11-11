class Solution{
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2){
        long pairs = Math.min(need1, need2);
        long extra = Math.abs((long) need1 - need2);

        long pairCost = Math.min((long) costBoth, (long) cost1 + cost2);

        long singleCost = (need1 > need2) ? cost1 : cost2;
        long extraCost = Math.min((long) costBoth, singleCost);

        return pairs * pairCost + extra * extraCost;
    }
}