import java.util.ArrayDeque;
import java.util.Queue;

public class ServiceRequestQueue {
    private final Queue<String> queue = new ArrayDeque<>();

    public void addRequest(String request) {
        if (request != null && !request.isBlank()) {
            queue.offer(request.trim());
        }
    }

    public String processNext() {
        return queue.poll();
    }

    public String peek() {
        return queue.peek();
    }

}    