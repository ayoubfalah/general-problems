package computational_geometry.convexHull;

public class Vector {

    private int x;
    private int y;

    public Vector(int x_passing, int y_passing) {
        x = x_passing;
        y = y_passing;
    }

    public static boolean isNegativeAngle(Point a, Point a_1, Point a_2) {

        Vector u = new Vector(a_1.x - a.x, a_1.y - a.y);
        Vector v = new Vector(a_2.x - a_1.x, a_2.y - a_1.y);

        int crossProduct = u.x * v.y - u.y * v.x;

        return crossProduct < 0;
    }

}
