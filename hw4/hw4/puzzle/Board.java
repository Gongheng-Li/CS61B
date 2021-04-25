package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.HashSet;
import java.util.Set;

public class Board implements WorldState {

    private int[][] tiles;
    private int emptyIndexX, emptyIndexY;

    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                this.tiles[i][j] = tiles[i][j];
                if (tileAt(i, j) == 0) {
                    emptyIndexX = i;
                    emptyIndexY = j;
                }
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return tiles.length;
    }


    public Iterable<WorldState> neighbors() {
        Set<WorldState> neighborSet = new HashSet<>();
        if (emptyIndexX > 0) {
            neighborSet.add(neighbor(emptyIndexX, emptyIndexY, emptyIndexX - 1, emptyIndexY));
        }
        if (emptyIndexX < size() - 1) {
            neighborSet.add(neighbor(emptyIndexX, emptyIndexY, emptyIndexX + 1, emptyIndexY));
        }
        if (emptyIndexY > 0) {
            neighborSet.add(neighbor(emptyIndexX, emptyIndexY, emptyIndexX, emptyIndexY - 1));
        }
        if (emptyIndexY < size() - 1) {
            neighborSet.add(neighbor(emptyIndexX, emptyIndexY, emptyIndexX, emptyIndexY + 1));
        }
        return neighborSet;
    }

    private WorldState neighbor(int indexX1, int indexY1, int indexX2, int indexY2) {
        int temp = tiles[indexX1][indexY1];
        tiles[indexX1][indexY1] = tiles[indexX2][indexY2];
        tiles[indexX2][indexY2] = temp;
        Board neighbor = new Board(tiles);
        temp = tiles[indexX1][indexY1];
        tiles[indexX1][indexY1] = tiles[indexX2][indexY2];
        tiles[indexX2][indexY2] = temp;
        return neighbor;
    }


    public int hamming() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != 0 && tileAt(i, j) != i * size() + j + 1) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != 0) {
                    sum += Math.abs((tileAt(i, j) - 1) / size() - i) + Math.abs((tileAt(i, j) - 1) % size() - j);
                }
            }
        }
        return sum;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Board that = (Board) obj;
        if (size() != (that.size())) {
            return false;
        }
        return tiles.equals(that.tiles);
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
