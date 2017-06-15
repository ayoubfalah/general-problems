package computational_geometry.convexHull;

public class Point implements Comparable<Point> {

    public int x;
    public int y;

    public Point(int x_passing, int y_passing) {
        x = x_passing;
        y = y_passing;
    }

    /**
     * Sorting the remaining Points A[1], A[2], ..., A[n-1] by the angle ((0,1),
     * A[0]A[i]), i= 1,2, ..., (n-1)
     *
     * @param point a point in the plane
     */
    @Override
    public int compareTo(Point point) {
        return Integer.compare(point.y, this.y);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
