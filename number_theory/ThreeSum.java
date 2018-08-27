package number_theory;

/**
 *
 * @author ayoubfalah
 */
public class ThreeSum 
{
    public static void main(String[] args) 
    {
        System.out.println(threeSumNaive(new int[]{0, 2, -2, -1, 1,}));
        
    }
    /**
     * 
     * @param a set of integers
     * @return true iff there are i, j, and k such that ai + aj + ak = 0
     * The case where ai = aj = ak = 0 is excluded.
     */
    private static boolean threeSumNaive(int[] a)
    {
        int n = a.length;
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j <= i; j++) 
            {
                for (int k = 0; k < n; k++) 
                {
                    if ((a[i] + a[j] + a[k] == 0) && 
                            !((a[i] == a[j]) && (a[j] == a[k]) && (a[k] == 0)))
                    {
                        System.out.println(a[i] + " " + a[j] + " " + a[k]);
                        return true;
                    }                    
                }                
            }            
        }
        return false;
    }
}
