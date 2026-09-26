import java.util.*;

public class CampusGraph {
    private final Map<String, Set<String>> adjacencyList = new LinkedHashMap<>();

    public boolean addLocation(String location) {
        String key = normalize(location);
        if (key == null || adjacencyList.containsKey(key)) {
            return false;
        }

        adjacencyList.put(key, new LinkedHashSet<>());
        return true;
    }

    public boolean removeLocation(String location) {
        String key = normalize(location);
        if (key == null || !adjacencyList.containsKey(key)) {
            return false;
        }

        adjacencyList.remove(key);

        for (Set<String> neighbours : adjacencyList.values()) {
            neighbours.remove(key);
        }

        return true;
    }

    public boolean addConnection(String from, String to) {
        String a = normalize(from);
        String b = normalize(to);

        if (a == null || b == null || a.equals(b)
                || !adjacencyList.containsKey(a)
                || !adjacencyList.containsKey(b)) {
            return false;
        }

        adjacencyList.get(a).add(b);
        adjacencyList.get(b).add(a);
        return true;
    }

    public boolean removeConnection(String from, String to) {
        String a = normalize(from);
        String b = normalize(to);

        if (a == null || b == null
                || !adjacencyList.containsKey(a)
                || !adjacencyList.containsKey(b)) {
            return false;
        }

        boolean removed = adjacencyList.get(a).remove(b);
        adjacencyList.get(b).remove(a);
        return removed;
    }

    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations found.");
            return;
        }

        System.out.println("Campus Network:");
        for (Map.Entry<String, Set<String>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> "
                    + (entry.getValue().isEmpty()
                    ? "No direct connections"
                    : String.join(", ", entry.getValue())));
        }
    }

    public void bfs(String start) {
        String source = normalize(start);

        if (source == null || !adjacencyList.containsKey(source)) {
            System.out.println("Starting location not found.");
            return;
        }

        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        visited.add(source);
        queue.offer(source);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current);

            for (String neighbour : adjacencyList.get(current)) {
                if (visited.add(neighbour)) {
                    queue.offer(neighbour);
                }
            }

            if (!queue.isEmpty()) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }

    public void dfs(String start) {
        String source = normalize(start);

        if (source == null || !adjacencyList.containsKey(source)) {
            System.out.println("Starting location not found.");
            return;
        }

        Set<String> visited = new LinkedHashSet<>();
        System.out.print("DFS Traversal: ");
        dfsRecursive(source, visited, true);
        System.out.println();
    }

    private void dfsRecursive(String current, Set<String> visited, boolean first) {
        visited.add(current);

        if (!first) {
            System.out.print(" -> ");
        }
        System.out.print(current);

        for (String neighbour : adjacencyList.get(current)) {
            if (!visited.contains(neighbour)) {
                dfsRecursive(neighbour, visited, false);
            }
        }
    }
}