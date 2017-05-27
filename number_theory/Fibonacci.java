package number_theory;

/**
 *
 * @author ayoubfalah
 */
public class Fibonacci 
{
    public static long fib(int n) {
        if (n <= 1) return n;
        return fib(n-1) + fib(n-2);
    }
    
    public static long fibFaster(int n) {
        if (n <= 1) return n;
        if (n % 2 == 0){
           int k = n / 2;
           return (2 * fibFaster(k-1) + fibFaster(k)) * fibFaster(k);
        }else
        {
            int k = (n + 1) / 2;
            return fibFaster(k)*fibFaster(k) + fibFaster(k-1)*fibFaster(k-1);
        }
    }

}
