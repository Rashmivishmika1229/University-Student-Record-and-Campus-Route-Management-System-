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

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void display() {
        if (queue.isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }

        System.out.println("Pending Service Requests:");
        int number = 1;
        for (String request : queue) {
            System.out.println(number++ + ". " + request);
        }
    }

}    