/* repeatedly a specified operation until a comdition is satisfied - > simultaion pattern.
*keep in mind the 'character to integer conversions'
* we need to construct a new string after every round -> go for 'StringBuilder'*/

import java.util.*;

public class Solution{
    public String digitSum(String s, int k){
        while(s.length() > k){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<s.length(); i+= k){
                int end = Math.min(i+k, s.length());
                String group = s.substring(i, end);
                int sum = 0;
                for(int j=0; j<group.length(); j++){
                    int digit = group.charAt(j) - '0';
                    sum += digit;
                }
                sb.append(sum);
            }
            s = sb.toString();
        }
        return s;
    }
}