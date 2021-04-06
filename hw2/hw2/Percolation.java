package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int BLOCKED = 0;
    private static final int OPEN = 1;

    private int[][] grid;
    private WeightedQuickUnionUF disjointSet;
    private int numberOfOpenSites;
    private int[] checkFull;
    private boolean percolationFlag;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Size of the gird cannot be negative!");
        }
        grid = new int[N][N];
        disjointSet = new WeightedQuickUnionUF(N*N);
        checkFull = new int[N*N];
        numberOfOpenSites = 0;
        percolationFlag = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = BLOCKED;
            }
        }
    }

    public void open(int row, int col) {
        checkValidation(row, col);
        grid[row][col] = OPEN;
        if (col != 0) {
            adjacentUnion(row, col, 0, -1);
        }
        if (col != grid[0].length-1) {
            adjacentUnion(row, col, 0, 1);
        }
        if (row == 0) {
            if (checkFull[disjointSet.find(col)] < 2) {
                checkFull[disjointSet.find(col)] += 2;
            }
        } else {
            adjacentUnion(row, col, -1, 0);
        }
        if (row == grid.length-1) {
            if (checkFull[disjointSet.find(row*grid[0].length+col)] == 0 || checkFull[disjointSet.find(row*grid[0].length+col)] == 2) {
                checkFull[disjointSet.find(row * grid[0].length + col)] += 1;
            }
        } else {
            adjacentUnion(row, col, 1, 0);
        }
        if (checkFull[disjointSet.find(row*grid[0].length+col)] == 3) {
            percolationFlag = true;
        }
        numberOfOpenSites += 1;
    }

    private void adjacentUnion(int row, int col, int deltaRow, int deltaCol) {
        if (grid[row+deltaRow][col+deltaCol] == OPEN) {
            int index = row * grid[0].length + col;
            int indexDelta = (row + deltaRow) * grid[0].length + (col + deltaCol);
            int isFullAfterUnion = 0;
            if (checkFull[disjointSet.find(index)] >=2 || checkFull[disjointSet.find(indexDelta)] >= 2) {
                isFullAfterUnion += 2;
            }
            if (checkFull[disjointSet.find(index)] % 2 == 1 || checkFull[disjointSet.find(indexDelta)] % 2 == 1) {
                isFullAfterUnion += 1;
            }
            disjointSet.union(row * grid[0].length + col, (row + deltaRow) * grid[0].length + (col + deltaCol));
            checkFull[disjointSet.find(row * grid[0].length + col)] = isFullAfterUnion;
        }
    }

    public boolean isOpen(int row, int col) {
        checkValidation(row, col);
        return grid[row][col] == OPEN;
    }

    public boolean isFull(int row, int col) {
        checkValidation(row, col);
        return checkFull[disjointSet.find(row*grid[0].length + col)] >= 2;
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return percolationFlag;
    }

    private void checkValidation(int row, int col) {
        if (row*col < 0 || row > grid.length || col > grid[0].length) {
            throw new IndexOutOfBoundsException("Given Index (row: " + row + ", col: " + col + ") is out of bound!");
        }
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(8);
        percolation.open(1, 2);
    }

}
