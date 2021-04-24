package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private Stack<WorldState> solutionPath;
    private Map<WorldState, Integer> distTo;
    private Map<WorldState, WorldState> edgeTo;
    private WorldState goalState;
    private int moves;

    private class Comp implements Comparator<WorldState> {
        @Override
        public int compare(WorldState state1, WorldState state2) {
            return (distTo.get(state1) + state1.estimatedDistanceToGoal())
                    - (distTo.get(state2) + state2.estimatedDistanceToGoal());
        }
    }

    public Solver(WorldState initial) {
        solutionPath = new Stack<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        distTo.put(initial, 0);
        edgeTo.put(initial, null);
        Comp comparator = new Comp();
        MinPQ<WorldState> fringe = new MinPQ<>(comparator);
        fringe.insert(initial);
        while (!fringe.isEmpty()) {
            WorldState presentState = fringe.delMin();
            if (presentState.isGoal()) {
                goalState = presentState;
                break;
            }
            for (WorldState adjState : presentState.neighbors()) {
                if (!adjState.equals(edgeTo.get(presentState))) {
                    if (distTo.containsKey(adjState) && distTo.get(adjState) < distTo.get(presentState) + 1) {
                        continue;
                    }
                    edgeTo.put(adjState, presentState);
                    distTo.put(adjState, distTo.get(presentState) + 1);
                    fringe.insert(adjState);
                }
            }
        }
        moves = distTo.get(goalState);
        WorldState tempState = goalState;
        while (tempState != null) {
            solutionPath.push(tempState);
            tempState = edgeTo.get(tempState);
        }
    }

    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        return solutionPath;
    }
}