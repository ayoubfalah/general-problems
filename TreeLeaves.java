import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class TreeLeaves 
{
    public static void main(String[] args) 
    {
        int n = 100000;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i - 1;
        
        System.out.println(TreeLeaves.computeNumOfLeaves(parent));
        System.out.println(TreeLeaves.computeNumOfLeavesFaster1(parent));
        System.out.println(TreeLeaves.computeNumOfLeavesFaster2(parent));
    }
    
    /**
     * 
     * @param parent the description of a rooted tree
     * @precondition The array parent must represent a tree
     *               The tree must have exactly one root
     *               Let n = parent.length, so 1<= n <= 10^5
     * @return the number of leaves in the tree
     */
    public static int computeNumOfLeaves(int[] parent) 
    {
        int counter = 0;
        for (int v = 0; v < parent.length; v++) 
        {
            if (!isParent(v, parent))
                counter++;
        }
        return counter;
    }
    
    private static boolean isParent(int v, int[] parent) 
    {
        for (int i = 0; i < parent.length; i++) 
        {
            // v is a parent
            if (parent[i] == v)
                return true;            
        }
        return false;
    }
    
    public static int computeNumOfLeavesFaster1(int[] parent) 
    {
        Arrays.sort(parent);
        int numOfLeaves = 0;
        for (int v = 0; v < parent.length; v++) 
        {
            boolean isParent = Arrays.binarySearch(parent, v) >= 0;
            if (!isParent)
                numOfLeaves++;            
        }
        return numOfLeaves;
    }
    
    public static int computeNumOfLeavesFaster2(int[] parent) 
    {
        int n = parent.length;
        Set<Integer> set = new TreeSet();
        for (int i = 0; i < n; i++) 
        {
            set.add(parent[i]);            
        }
        int numOfParents = set.size();
        int numOfLeaves = n - (numOfParents - 1);
        return numOfLeaves;
    }
}
