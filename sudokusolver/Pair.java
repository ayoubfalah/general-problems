package sudokusolver;

/**
 *
 * @author ayoubfalah
 */
public class Pair {

    private final int a;
    private final int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public String toString() {
        return "(" + a + "," + b + ")";
    }
}
