package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int N;
    private final boolean[][] opened;
    private int openedCount;

    private final int TOP_INDEX;
    private final int BOTTOM_INDEX;

    private final WeightedQuickUnionUF perc;

    public Percolation(int N) {
        if (N < 1)
            throw new IllegalArgumentException();
        this.N = N;
        opened = new boolean[N][N];
        int gridSizeWithTopAndBottom = N * N + 2;
        perc = new WeightedQuickUnionUF(gridSizeWithTopAndBottom);
        TOP_INDEX = gridSizeWithTopAndBottom - 2;
        BOTTOM_INDEX = gridSizeWithTopAndBottom - 1;
        openedCount = 0;
    }

    /**
     * number of opened objects is calculated with the help of "openedCount" variable
     * @return openedCount
     */
    public int getOpenedCount() {
        return openedCount;
    }

    /**
     * opens obj in row i and column j if it is not opened yet
     * @param i - row
     * @param j - column
     */
    public void open(int i, int j) {
        //throws an exception if i/j does not satisfy
        checkIndices(i, j);

        if (isOpened(i, j))
            return;

        opened[i - 1][j - 1] = true;
        openedCount++;

        if (i == 1)
            perc.union(j - 1, TOP_INDEX);

        if (i == N)
            perc.union((i - 1) * N + j -1, BOTTOM_INDEX);

        if (i > 1 && isOpened(i - 1, j))
            perc.union((i - 1) * N + j - 1, (i - 2) * N + j - 1);

        if (i < N && isOpened(i + 1, j))
            perc.union((i - 1) * N + j - 1, i * N + j - 1);

        if (j > 1 && isOpened(i, j - 1))
            perc.union((i - 1) * N + j - 1, (i - 1) * N + j - 2);

        if (j < N && isOpened(i, j + 1))
            perc.union((i - 1) * N + j - 1, (i - 1) * N + j);
    }

    /**
     *
     * @param i - row
     * @param j - column
     * @return true if obj is opened
     *         false if not
     */
    public boolean isOpened(int i, int j) {
        checkIndices(i, j);
        return opened[i - 1][j - 1];
    }

    /**
     * the main task
     * @return true if system percolates
     *         false if not
     */
    public boolean percolates() {
        return perc.connected(TOP_INDEX, BOTTOM_INDEX);
    }

    /**
     * checks whether indices i and j are in bounds
     * if they are not, throws new IndexOutOfBoundsException()
     * @param i - row
     * @param j - column
     */
    private void checkIndices(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N)
            throw new IndexOutOfBoundsException();
    }
}