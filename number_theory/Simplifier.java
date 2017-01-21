package number_theory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ayoubfalah
 */
public class Simplifier 
{
    public static void main(String[] args)
    {
        List primeFactors = Arrays.asList(2, 2, 2, 3, 3, 5, 4);
        writeResult(simplify(primeFactors));        
    }
    
    private static void writeResult(Map<Integer, Integer> result)
    {
        StringBuilder sb = new StringBuilder();
        
        Set<Integer> primes = result.keySet();
        Iterator<Integer> primesItr = primes.iterator();
        while(primesItr.hasNext())
        {
            Integer prime = primesItr.next();
            Integer power = result.get(prime);
            if (Integer.compare(power, 1) > 0)
            {
                sb.append(prime);
                sb.append(" ^ ");
                sb.append(power);
                sb.append(" * ");                
            }else {
                sb.append(prime);
                sb.append(" * "); 
            }
        }
        // The last " * " is superfluous
        sb.delete(sb.length() - 3, sb.length() - 1);
        System.out.println(sb);
    }
    
    
    public static Map simplify(List primeFactors) 
    {
        int n = primeFactors.size();
        Map result = new HashMap<Integer, Integer>();
        int i = 0;
        while (i < n) 
        {
            int counter = 0;
            while (((i + 1) + counter < n) && 
                    (primeFactors.get(i + counter) == 
                     primeFactors.get(i + counter + 1))) 
            { 
                counter++;                
            }
            result.put(primeFactors.get(i), (counter + 1));
            
            i += counter + 1;            
        }
        return result;
    }
}