package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private boolean targetFound = false;
    private int cycleNode;
    private boolean connect = false;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
    public void solve() {
        dfs(0, -1);
    }

    // Helper methods go here
    private void dfs(int v, int origin) {
        marked[v] = true;
        announce();
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                dfs(w, v);
                if (connect) {
                    if (w == cycleNode) {
                        connect = false;
                        announce();
                    } else {
                        edgeTo[w] = v;
                    }
                }
            } else if (w != origin){
                edgeTo[w] = v;
                cycleNode = w;
                connect = true;
                targetFound = true;
            }
            if (targetFound) {
                return;
            }
        }
    }
}

