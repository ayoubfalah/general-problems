package number_theory;

import java.math.BigInteger;

/**
 *
 * @author ayoubfalah
 */
public class Factorial 
{
    public static BigInteger computeIteratively(BigInteger n)
    {
        BigInteger result = BigInteger.ONE;
        BigInteger i = BigInteger.valueOf(2);
        while (i.compareTo(n) <= 0) 
        {
            result = result.multiply(i); 
            i = i.add(BigInteger.ONE);            
        }
        return result;
    }
    
    public static BigInteger computeRecursively(BigInteger n)
    {
        if (n.compareTo(BigInteger.ONE) <= 0)
            return BigInteger.ONE;
        return n.multiply(computeIteratively(n.subtract(BigInteger.ONE)));
    }
    
    public static void main(String[] args) 
    {
        BigInteger n = new BigInteger("3");
        System.out.println(computeRecursively(n));        
    }
}