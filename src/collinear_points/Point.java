package collinear_points;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new SOrder();

    private final int x;
    private final int y;

    /**
     * creates a point (x; y)
     * @param x - x-coordinate
     * @param y - y-coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * draws this point
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * draws the line segment between this point and that point
     * @param that - another point
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     * @param that - another point
     * @return slope between this point and that point
     */
    public double slopeTo(Point that) {
        if(this.x == that.x && this.y == that.y)
            return Double.NEGATIVE_INFINITY;
        else if(this.y == that.y)
            return 0;
        else if(this.x == that.x)
            return Double.POSITIVE_INFINITY;
        else
            return (double)(that.y - this.y) / (that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     * @param that - another point
     * @return 1 if this point is lexicographically larger than that point
     *         -1 if this point is lexicographically smaller than that point
     *         0 if they are equal
     */
    public int compareTo(Point that) {
        if(this.y == that.y && this.x == that.x)
            return 0;
        else if(this.y < that.y || (this.y == that.y && this.x < that.x))
            return -1;
        else
            return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     */
    private class SOrder implements Comparator<Point> {
        public int compare(Point p, Point q){
            return Double.compare(slopeTo(p), slopeTo(q));
        }
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}