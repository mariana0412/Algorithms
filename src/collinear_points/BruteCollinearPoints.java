package collinear_points;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;

public class BruteCollinearPoints {
    Point[] points;

    public BruteCollinearPoints(Point[] points) {
        this.points = points;
    }

    public static void main(String[] args) {
        StdDraw.setXscale(0,32768);
        StdDraw.setYscale(0,32768);

        Point[] points = readPoints("src/collinear_points/tests/input8.txt");
        Arrays.sort(points);
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        brute.drawSegments();
    }

    private static Point[] readPoints(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }
        return points;
    }

    public void drawSegments() {
        for(Point point : points)
            if(point == null)
                throw new IllegalArgumentException();

        int n = points.length;
        for(int p = 0; p < (n - 3); p++) {
            for(int q = p + 1; q < (n - 2); q++) {
                for(int r = q + 1; r < (n - 1); r++) {
                    for(int s = r + 1; s < n; s++) {
                        Point p1 = points[p], q1 = points[q], r1 = points[r], s1 = points[s];
                        if (p1.slopeTo(q1) == q1.slopeTo(r1) && q1.slopeTo(r1) == r1.slopeTo(s1)) {
                            System.out.println(p1 + " -> " + q1 + " -> " + r1 + " -> " + s1);
                            p1.drawTo(s1);
                        }
                    }
                }
            }
        }
    }
}
