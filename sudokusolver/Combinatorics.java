package sudokusolver;

import java.util.ArrayList;

/**
 *
 * @author ayoubfalah
 */
public class Combinatorics 
{
    /**
     * 
     * @param alphabet a finite set
     * @return all words of length 2 over the given alphabet. This is equivalent
     *         to the cartesian product of the set alphabet with itself
     */
    public static ArrayList<Pair> product(int[] alphabet) 
    {
        ArrayList<Pair> pairs = new ArrayList();
        int n = alphabet.length;
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++)
            {
                Pair pair = new Pair(alphabet[i], alphabet[j]);
                pairs.add(pair);                
            }            
        }        
        return pairs;
    }
    
    /**
     * 
     * @param literals a finite set
     * @return all combinations of length 2 over the alphabet literals.
     */
    public static ArrayList<Pair> combinations(ArrayList<Integer> literals) 
    {
        ArrayList<Pair> pairs = new ArrayList();
        int n = literals.size();
        for (int i = 0; i < n; i++) 
        {
            for (int j = (i + 1); j < n; j++)
            {
                Pair pair = new Pair(literals.get(i), literals.get(j));
                pairs.add(pair);                
            }            
        }        
        return pairs;
    }

}
