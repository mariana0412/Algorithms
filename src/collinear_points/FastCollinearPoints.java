package collinear_points;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private static Point[] points;
    private static Point[] pointsBySlope;
    private static int N;

    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        readPoints("src/collinear_points/tests/rs1423.txt");

        Arrays.sort(points);
        List<Double> slopesToSkip = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Point curPoint = points[i];
            Arrays.sort(pointsBySlope, curPoint.SLOPE_ORDER);

            slopesToSkip.clear();

            int pos = 1;
            for (int j = 1; j < N; j++) {
                Point point = pointsBySlope[j];
                double curSlope = curPoint.slopeTo(point);
                double prevSlope = curPoint.slopeTo(pointsBySlope[j - 1]);

                if (prevSlope != curSlope) {
                    if (j - pos > 2)
                        printLine(pointsBySlope, pos, j);
                    pos = j;
                }
                if (curPoint.compareTo(point) < 0) {
                    if (!slopesToSkip.contains(curPoint.slopeTo(point)))
                        slopesToSkip.add(curPoint.slopeTo(point));
                    pos = j + 1;
                }
                if (slopesToSkip.contains(curPoint.slopeTo(point)))
                    pos = j + 1;
            }
            if (N - pos > 2)
                printLine(pointsBySlope, pos, N);
        }
    }

    private static void readPoints(String filename) {
        In in = new In(filename);
        N = in.readInt();
        points = new Point[N];
        pointsBySlope = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            pointsBySlope[i] = points[i];
            points[i].draw();
        }
    }

    private static void printLine(Point[] points, int start, int end) {
        Arrays.sort(points, start, end);
        for (int i = start; i < end; i++)
            StdOut.print(points[i] + " -> ");
        StdOut.println(points[0]);
        points[start].drawTo(points[0]);
    }

}