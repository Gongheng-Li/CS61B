package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;
    private double[] fraction;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0) {
            throw new IllegalArgumentException("Size of the grid N must be positive!");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("Times of computation T must be positive!");
        }

        fraction = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolationModule = pf.make(N);
            int count = 0;
            while (!percolationModule.percolates()) {
                int randomRow = StdRandom.uniform(N);
                int randomCol = StdRandom.uniform(N);
                if (!percolationModule.isOpen(randomRow, randomCol)) {
                    percolationModule.open(randomRow, randomCol);
                    count += 1;
                }
            }
            fraction[i] = count*1.0 / (N*N);
        }
        mean = StdStats.mean(fraction);
        stddev = StdStats.stddev(fraction);
        confidenceLow = mean - 1.96 * stddev / Math.sqrt(T);
        confidenceHigh = mean + 1.96 * stddev / Math.sqrt(T);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLow() {
        return confidenceLow;
    }

    public double confidenceHigh() {
        return confidenceHigh;
    }

//    public static void main(String[] args) {
//        PercolationFactory pf = new PercolationFactory();
//        for (int T = 20; T < 210; T += 20) {
//            PercolationStats testPs = new PercolationStats(20, T, pf);
//            System.out.printf("The threshold is in the interval [%.3f, %.3f] when T equals %d with a confidence of 95%%.\n", testPs.confidenceLow, testPs.confidenceHigh, T);
//        }
//    }

}
