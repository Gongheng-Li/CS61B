package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Josh Hug
 * @author Li Gongheng
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private boolean targetFound = false;
    private Queue<Integer> fringe;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        fringe = new ArrayDeque<>();
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        if (s == t) {
            targetFound = true;
            return;
        }
        marked[s] = true;
        announce();
        fringe.add(s);
        while (!fringe.isEmpty()) {
            int u = fringe.remove();
            for (int w : maze.adj(u)) {
                if (!marked[w]) {
                    marked[w] = true;
                    distTo[w] = distTo[u] + 1;
                    edgeTo[w] = u;
                    announce();
                    if (w == t) {
                        targetFound = true;
                        return;
                    }
                    fringe.add(w);
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

