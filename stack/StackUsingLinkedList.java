package stack;

import linkedList.SimpleLinkedList;

public class StackUsingLinkedList implements Stack{
    private SimpleLinkedList stack ;

    public StackUsingLinkedList() {
        stack = new SimpleLinkedList();
    }

    public int getLength() {
        return stack.getSize();
    }

    @Override
    public void push(int value) {
        
        stack.add(value);
    }

    @Override
    public int pop() {
        if (stack.getSize() == 0) {
            throw new Error("Cannot pop, stack is empty");
        }
        int value = stack.getLast();
        stack.deleteLast();
        return value;
    }

    @Override
    public boolean isEmpty() {
        return stack.getSize() == 0;
    }

    @Override
    public void clear() {
        stack.clear();;
    }

    @Override
    public int peek() {
        return stack.getLast();
    }

    
    
    @Override
    public String toString() {
        return stack.toString();
    }

}
