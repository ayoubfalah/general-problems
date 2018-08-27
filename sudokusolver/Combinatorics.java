package sudokusolver;

import utility.classes.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author ayoubfalah
 */
public class Combinatorics 
{
    /**
     * 
     * @param alphabet a finite set
     * @param k a natural number k
     * @return all words of length k over the given alphabet. This is equivalent
     *         to the n-fold cartesian product of the set alphabet with itself
     */
    public static List<Stack> product(int[] alphabet, int k) 
    {
        List<Stack> c = new ArrayList(); // The set of sequences of order k
        Stack s = new Stack(); // A sequence of elements
        product(alphabet, k, c, s);
        
        return c;
    }
    
    private static void product(int[] alphabet, int k, List c, Stack s)
    {
        if (k == 0) c.add(s.clone());
        else
        {
            for (int item : alphabet) 
            {
                s.push(item);
                product(alphabet, k - 1, c, s);
                s.pop();                
            }
        }
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
    
    public static List<Integer[]> findAllPermutations(Integer[] word)
    {
        // out starts as empty ArrayList
        List<Integer[]> out = new ArrayList();
        // for i from 0 to (word.length - 1):
        for (int i = 0; i <= word.length - 1; i++) 
        {
            //transposition = transpose(word, 1, i)
            Integer[] transposition = transpose(word, 0, i);
            // Add transposition to out
            out.add(transposition);
            
            // for i from 1 to (word.length - 2):
            for (int j = 1; j <= word.length - 2; j++)
            {
                // transposition = transpose(word, 1, i)
                transposition = transpose(transposition, j, j+1);
                // Add transposition to out
                out.add(transposition);                
            }
        }                
        // out is the answer
        return out;
        
        
    }

    private static Integer[] transpose(Integer[] word, int i, int j) 
    {
        Integer[] transosition = word.clone();
        int temp = word[i];
        transosition[i] = transosition[j];
        transosition[j] = temp;
        
        return transosition;
    }

}