class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        int turns = 0;

        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            turns++;
        }

        // Alice plays on odd turns (1st, 3rd, 5th...)
        return turns % 2 == 1;
    }
}
