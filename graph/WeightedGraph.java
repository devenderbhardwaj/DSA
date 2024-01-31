package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class WeightedGraph {
    int[][] matrix;
    boolean directed;

    public WeightedGraph(int n, boolean directed) {
        this.directed = directed;
        matrix = new int[n][n];
    }

    public void setEdge(int i, int j, int w) {
        matrix[i][j] = w;
        if (!directed) {
            matrix[j][i] = w;
        }
    }

    public boolean isEdge(int i, int j) {
        return matrix[i][j] >= 1;
    }

    public ArrayList<Integer> breathFirstTraversel() {
        ArrayList<Integer> output = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(0);
        int[] visited = new int[matrix.length];
        while (!queue.isEmpty()) {
            int vertex = queue.removeFirst();
            output.add(vertex);
            visited[vertex] = 1;
            for (int i = 0; i < matrix[vertex].length; i++) {
                if (isEdge(vertex, i) && visited[i] == 0) {
                    queue.addLast(i);
                }
            }
        }
        return output;
    }

    public ArrayList<Integer> depthFirstTraversel() {
        ArrayList<Integer> output = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int[] visited = new int[matrix.length];
        while (!stack.isEmpty()) {
            int vertext = stack.pop();
            output.add(vertext);
            visited[vertext] = 1;
            for (int i = matrix.length - 1; i >= 0; i--) {
                if (isEdge(vertext, i) && visited[i] == 0) {
                    stack.push(i);
                }
            }
        }
        return output;
    }

    private void recursiveDepthFirstTraversel(int n, int[] visited, ArrayList<Integer> output) {
        visited[n] = 1;
        output.add(n);
        for (int i = 0; i < matrix[n].length; i++) {
            if (isEdge(n, i) && visited[i] == 0) {
                recursiveDepthFirstTraversel(i, visited, output);
            }
        }
    }

    public ArrayList<Integer> recursiveDepthFirstTraversel() {
        ArrayList<Integer> output = new ArrayList<>();
        recursiveDepthFirstTraversel(0, new int[matrix.length], output);
        return output;
    }

    public WeightedGraph primsGraph() {
        if (directed) {
            throw new UnsupportedOperationException("Minimum spaning tree is not defined for directed graph");
        }

        WeightedGraph graph = new WeightedGraph(this.matrix.length, false);

        HashSet<Integer> selectected = new HashSet<>();
        selectected.add(0);

        while (selectected.size() < matrix.length) {
            int min = Integer.MAX_VALUE;
            int row = 0;
            int col = 0;

            for (Integer i : selectected) {
                for (int j = 0; j < matrix.length; j++) {
                    if (!selectected.contains(j) && isEdge(i, j)) {
                        if (min > matrix[i][j]) {
                            min = matrix[i][j];
                            row = i;
                            col = j;
                        }
                    }
                }
            }

            if (min != Integer.MAX_VALUE) {
                selectected.add(col);
                graph.setEdge(row, col, min);
            }
        }

        return graph;
    }

    public WeightedGraph kruskals() {
        if (directed) {
            throw new UnsupportedOperationException("Minimum spaning tree is defined for directed graph");
        }
        return (new KruskalsMST()).getMST();
    }

    public void display() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + "--> ");
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0) {
                    System.out.printf(j + "{%d} ", matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    class KruskalsMST {
        int[] parent;

        KruskalsMST() {
            parent = new int[matrix.length];

            for (int i = 0; i < matrix.length; i++) {
                parent[i] = i;
            }
        }

        private void setParent(int i, int j) {
            int iParent = find(i);
            int jParent = find(j);
            parent[jParent] = iParent;
        }

        private int find(int i) {
            if (parent[i] != i) {
                return find(parent[i]);
            }
            return i;
        }

        private WeightedGraph getMST() {
            WeightedGraph g = new WeightedGraph(matrix.length, false);
            int edges = 0;

            while (edges < matrix.length - 1) {
                int min = Integer.MAX_VALUE;
                int r = 0, c = 0;

                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix.length; j++) {
                        if (matrix[i][j] > 0 && (matrix[i][j] < min) && (find(i) != find(j))) {
                            min = matrix[i][j];
                            r = i;
                            c = j;
                        }
                    }
                }

                if (min != Integer.MAX_VALUE) {
                    g.setEdge(r, c, min);
                    setParent(r, c);
                    ++edges;
                }
            }
            return g;
        }

    }
}
