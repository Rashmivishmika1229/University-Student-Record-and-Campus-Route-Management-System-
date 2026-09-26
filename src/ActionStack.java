import java.util.ArrayDeque;
import java.util.Deque;

public class ActionStack {
    private final Deque<String> stack = new ArrayDeque<>();

    public void push(String action) {
    if (action != null && !action.isBlank()) {
        stack.push(action);
        }
    }   

    public String pop() {
        return stack.isEmpty() ? null : stack.pop();
    }

    public String peek() {
        return stack.isEmpty() ? null : stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void display() {
        if (stack.isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }

        System.out.println("Recent Actions (latest first):");
        for (String action : stack) {
            System.out.println("- " + action);
        }
    }
    
}