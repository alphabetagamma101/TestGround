import java.util.Stack;

public class QueueUsingStack<T> {

    private Stack<T> primary = new Stack();
    private Stack<T> secondary = new Stack();

    private boolean lastOperationAdd = true;

    public void offer(final T element) {
        if (!lastOperationAdd) {
            transferAndSwap();
            lastOperationAdd = true;
        }
        primary.add(element);
    }

    public T poll() {
        if (lastOperationAdd) {
            transferAndSwap();
            lastOperationAdd = false;
        }
        if (primary.isEmpty()) {
            throw new RuntimeException("polling .... but no element is present");
        }
        return primary.pop();
    }

    private void transferAndSwap() {
        transfer(primary, secondary);
        swapPrimary();
    }

    private void transfer(final Stack<T> from,
                          final Stack<T> to) {
        while (!from.isEmpty()) {
            to.push(from.pop());
        }
    }

    private void swapPrimary() {
        final Stack<T> tmp = primary;
        primary = secondary;
        secondary = tmp;
    }

    public static void main(String[] args) {
        QueueUsingStack testClass = new QueueUsingStack();

        // 1,2,3,pop,4,pop,pop,5,6,7,pop

        testClass.offer(1);
        testClass.offer(2);
        testClass.offer(3);
        System.out.println(testClass.poll());
        testClass.offer(4);
        System.out.println(testClass.poll());
        System.out.println(testClass.poll());
        testClass.offer(5);
        testClass.offer(6);
        testClass.offer(7);
        System.out.println(testClass.poll());
    }
}
