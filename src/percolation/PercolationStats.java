package percolation;

import java.util.concurrent.ThreadLocalRandom;

public class PercolationStats {

    private final double[] results;
    private final int T;

    /**
     * conducting T independent experiments on NxN matrix
     * @param N - size of NxN matrix
     * @param T - number of trials (independent experiments)
     */
    public PercolationStats(int N, int T) {
        this.T = T;
        if (N < 1 || this.T < 1)
            throw new IllegalArgumentException();

        results = new double[this.T];

        for (int t = 0; t < this.T; t++) {
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int i = ThreadLocalRandom.current().nextInt(1, N + 1);
                int j = ThreadLocalRandom.current().nextInt(1, N + 1);
                if (!perc.isOpened(i, j))
                    perc.open(i, j);
            }
            results[t] = (double) perc.getOpenedCount() / (double) (N * N);
        }
    }

    /**
     * calculates mean by adding all
     * @return mean
     */
    public double mean() {
        double sum = 0;
        for(double t : results)
            sum += t;
        return sum / (double) T;
    }

    /**
     * calculates standard deviation
     * @return standard deviation
     */
    public double stddev() {
        double sum = 0;
        double mean = mean();
        for(double t : results)
            sum += Math.pow((t - mean), 2);
        return sum / (double) (T - 1);
    }

    /**
     * calculates low endpoint of 95% confidence interval for percolation threshold
     * @return low endpoint
     */
    public double lowConfidence() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /**
     * calculates high endpoint of 95% confidence interval for percolation threshold
     * @return high endpoint
     */
    public double highConfidence()    {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        int N = 200;
        int T = 100;

        PercolationStats percolationStats = new PercolationStats(N, T);

        System.out.println("% java PercolationStats " + N + " " + T);
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("Stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + percolationStats.lowConfidence() + ", " + percolationStats.highConfidence());
    }
}