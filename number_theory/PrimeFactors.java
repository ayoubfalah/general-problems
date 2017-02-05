package number_theory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ayoubfalah
 */
public class PrimeFactors 
{
    
    public static void main(String... args)
    {
        int n = 100;
        List result = computePrimeFactors(n);        
        Map simplifiedResult = Simplifier.simplify(result);
        System.out.print(n + "  = ");
        Simplifier.writeResult(simplifiedResult);
    }

    private static List computePrimeFactors(int n) {
        
        List result = new ArrayList();
        int d = computeSmallestDivisor(n);
        boolean isPrime = d == n;
        while (n > 1 && !isPrime)
        {
            result.add(d);
            n = n / d;
            
            d = computeSmallestDivisor(n);
            isPrime = d == n;
        }
        result.add(n);
        return result;
    }

    private static int computeSmallestDivisor(int n) 
    {
        if (n % 2 == 0)
            return 2;
        int d = n;
        int from = 3; 
        double to = Math.floor(Math.sqrt(n)); 
        for (int i = from; i <= to; i+= 2) 
        {
            if (n % i == 0)
            {
                d = i;
                break;                
            }            
        }
        return d;
    }
}
