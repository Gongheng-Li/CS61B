package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private Stack<WorldState> solutionPath;
    private Map<Node, Integer> distTo;
    private Map<Node, Node> edgeTo;
    private Node goalNode;
    private int moves;

    private class Comp implements Comparator<Node> {
        @Override
        public int compare(Node state1, Node state2) {
            return (distTo.get(state1) + state1.world.estimatedDistanceToGoal())
                    - (distTo.get(state2) + state2.world.estimatedDistanceToGoal());
        }
    }

    private class Node {
        private WorldState world;
        private Node(WorldState inputWorld) {
            world = inputWorld;
        }
    }

    public Solver(WorldState initial) {
        solutionPath = new Stack<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        Node initialNode = new Node(initial);
        distTo.put(initialNode, 0);
        edgeTo.put(initialNode, null);
        Comp comparator = new Comp();
        MinPQ<Node> fringe = new MinPQ<>(comparator);
        fringe.insert(initialNode);
        while (!fringe.isEmpty()) {
            Node presentNode = fringe.delMin();
            if (presentNode.world.isGoal()) {
                goalNode = presentNode;
                break;
            }
            for (WorldState adjState : presentNode.world.neighbors()) {
                if (edgeTo.get(presentNode) == null || !adjState.equals(edgeTo.get(presentNode).world)) {
                    Node adjNode = new Node(adjState);
                    edgeTo.put(adjNode, presentNode);
                    distTo.put(adjNode, distTo.get(presentNode) + 1);
                    fringe.insert(adjNode);
                }
            }
        }
        moves = distTo.get(goalNode);
        Node tempNode = goalNode;
        while (tempNode != null) {
            solutionPath.push(tempNode.world);
            tempNode = edgeTo.get(tempNode);
        }
    }

    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        return solutionPath;
    }
}