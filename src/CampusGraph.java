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
}