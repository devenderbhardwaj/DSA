package stack;

import linkedList.SimpleLinkedList;

public class StackUsingLinkedList implements Stack{
    private static final int DEFAULT_CAPACITY = 10;
    private SimpleLinkedList stack ;
    private int capacity ;

    public StackUsingLinkedList(int capacity) {
        this.capacity = capacity;
        stack = new SimpleLinkedList();
    }

    public StackUsingLinkedList() {
        stack = new SimpleLinkedList();
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public int getLength() {
        return stack.getSize();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void push(int value) {
        if (stack.getSize() == capacity) {
            throw new IllegalStateException("Cannot add, stack is full"); 
        }
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
    public void empty() {
        stack.clear();;
    }

    @Override
    public int peek() {
        return stack.getLast();
    }

    @Override
    public int search(int value) {
        return stack.search(value);
    }
    
    @Override
    public String toString() {
        return stack.toString();
    }

    @Override
    public boolean isFull() {
        return stack.getSize() == capacity;
    }
}
