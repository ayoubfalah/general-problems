package computational_geometry.convexHull;

import java.util.Arrays;
import java.util.Stack;

public class GrahamScan {

    /**
     * Computing the convex hull of a point set in the plane
     *
     * @param A array representing the point set
     * @return stack that contains the vertices of the convex hull
     */
    public static Stack<Point> computeConvexHull(Point A[]) {
        Arrays.sort(A, 1, A.length);

        Stack<Point> stack = new Stack();
        stack.push(A[0]);
        stack.push(A[1]);

        int i = 2;
        while (i < A.length) {

            int size = stack.size();
            Point leftVertex = stack.elementAt(size - 2);
            Point middleVertex = stack.elementAt(size - 1);
            Point rightVertex = A[i];

            if (Vector.isNegativeAngle(leftVertex, middleVertex, rightVertex)) {
                stack.push(A[i]);
                i++;
            } else {
                stack.pop();
            }
        }

        return stack;
    }

    public static void main(String[] args) {
        Point A[] = {new Point(0, 30) , new Point(50, 40), new Point(30, 60),
                     new Point(20, 0) , new Point(15, 25), new Point(55, 20),
                     new Point(50, 10), new Point(70, 30)
                    };

        Stack<Point> convexHull = computeConvexHull(A);
        int numberOfVerticesInConvexHull = convexHull.size();
        
        for (int i = 0; i < numberOfVerticesInConvexHull; i++) {
            Point convexHullVertex = convexHull.get(i);
            System.out.print((i < numberOfVerticesInConvexHull - 1)?
                    convexHullVertex + ", " : convexHullVertex + "\n");
        }
    }
}
