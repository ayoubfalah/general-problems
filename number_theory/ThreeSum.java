package number_theory;

import java.util.Arrays;

/**
 *
 * @author ayoubfalah
 */
public class ThreeSum 
{
    public static void main(String[] args) 
    {
        System.out.println(threeSum(new int[]{0, 2, -2, -1, 1,}));
        
    }
    /**
     * 
     * @param a set of integers
     * @return true iff there are i, j, and k such that ai + aj + ak = 0
     * The case where ai = aj = ak = 0 is excluded.
     */
    private static boolean threeSum(int[] a)
    {
        Arrays.sort(a);
        if (Arrays.binarySearch(a, 0) >= 0) return twoSum(a);
        else return strictThreeSum(a);
    }
    
    public static boolean twoSum(int a[])
    {
        for (int i = 0; i < a.length; i++) 
        {
            if (Arrays.binarySearch(a, (-1)*a[i]) >= 0)
                return true;            
        }
        return false;
    }
    
    public static boolean strictThreeSum(int[] a)
    {
        int n = a.length;
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j <= i; j++) 
            {
                for (int k = 0; k < n; k++) 
                {
                    if (a[i] + a[j] + a[k] == 0)
                    {
                        return true;
                    }                    
                }                
            }            
        }
        return false;
    }
}
