import java.util.ArrayDeque;
import java.util.Deque;

public class ActionStack {
    private final Deque<String> stack = new ArrayDeque<>();

    public void push(String action) {
    if (action != null && !action.isBlank()) {
        stack.push(action);
    }
}
}