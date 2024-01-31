package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class Graph {
    HashMap<String, Set<String>> map;
    private final boolean directed;

    public Graph(boolean directed) {
        map = new HashMap<>();
        this.directed = directed;
    }

    public void createNode(String value) {
        map.put(value, new HashSet<>());
    }

    public void setEdge(String a, String b) {
        if (!map.containsKey(a) || !map.containsKey(b)) {
            throw new IllegalArgumentException("Nodes does not exist in graph");
        }
        map.get(a).add(b);
        if (!directed) {
            map.get(b).add(a);
        }
    }

    public boolean isEdge(String a, String b) {
        try {
            return map.get(a).contains(b);
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<String> breathFirstTraversel(String root) {
        if (!map.containsKey(root)) {
            throw new RuntimeException("\" " + root + " \" " + " is not a node in graph");
        }
        ArrayList<String> output = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(root);
        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            String toVisit = queue.removeFirst();
            output.add(toVisit);
            visited.add(toVisit);
            for (String child : map.get(toVisit)) {
                if (!visited.contains(child)) {
                    queue.addLast(child);
                }
            }
        }
        return output;
    }

    public ArrayList<String> depthFirstTraversel(String root) {
        if (!map.containsKey(root)) {
            throw new RuntimeException("\" " + root + " \" " + " is not a node in graph");
        }

        ArrayList<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push(root);
        Set<String> visited = new HashSet<>();

        while (!stack.isEmpty()) {
            String toVisit = stack.pop();
            output.add(toVisit);
            visited.add(toVisit);
            for (String child : map.get(toVisit)) {
                if (!visited.contains(child)) {
                    stack.push(child);
                }
            }
        }
        return output;
    }

    private void recursiveDepthFirstTraversel(String node, Set<String> visited, ArrayList<String> output) {
        visited.add(node);
        output.add(node);
        for (String child : map.get(node)) {
            if (!visited.contains(child)) {
                recursiveDepthFirstTraversel(child, visited, output);
            }
        }
    }

    public ArrayList<String> recursiveDepthFirstTraversel(String root) {
        if (!map.containsKey(root)) {
            throw new RuntimeException("\" " + root + " \" " + " is not a node in graph");
        }
        ArrayList<String> output = new ArrayList<>();
        recursiveDepthFirstTraversel(root, new HashSet<>(), output);
        return output;
    }

    public boolean isPath(String a, String b) {
        if (!map.containsKey(a) || !map.containsKey(b)) {
            throw new IllegalArgumentException("Nodes does not exist in graph");
        }
        if (a.equals(b)) {
            return true;
        }

        LinkedList<String> queue = new LinkedList<>();
        queue.add(a);
        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            String toVisit = queue.removeFirst();
            visited.add(toVisit);
            for (String child : map.get(toVisit)) {
                if (child.equals(b)) {
                    return true;
                }
                if (!visited.contains(child)) {
                    queue.addLast(child);
                }
            }
        }
        return false;
    }
}
