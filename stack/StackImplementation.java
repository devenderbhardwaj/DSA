package stack;

public class StackImplementation implements Stack{
    class StackNode {
        int value ;
        StackNode next;

        StackNode(int value) {
            this.value = value;
        }
    }

    StackNode top;

    public StackImplementation() {
        this.top = null;
    }

    @Override
    public void push(int value) {
        StackNode node = new StackNode(value);
        node.next = top;
        top = node;
    }

    @Override
    public int pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        int value = top.value;
        top = top.next;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }


    @Override
    public void clear() {
        top = null;
    }

    @Override
    public int peek() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        
        return top.value;
    }

    
    
}
