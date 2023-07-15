package stack;

import java.util.NoSuchElementException;

public class SimpleStack implements Stack {
    private int[] stack;
    private int top;

    public int getLength() {
        return top + 1;
    }

    public int getCapacity() {
        return this.stack.length;
    }
    private int DEFAULT_CAPACITY = 10;

    public SimpleStack() {
        stack = new int[DEFAULT_CAPACITY];
        top = -1;
    }

    public SimpleStack(int capacity) {
        stack = new int[capacity];
        top = -1;
    }

    @Override
    public void push(int value) {
        if (top == stack.length - 1) {
            throw new IllegalStateException("Cannot add, stack is full");
        }
        ++top;
        stack[top] = value;
    }

    @Override
    public int pop() {
        if (top == -1) {
            throw new NoSuchElementException("Cannot pop, stack is empty");
        }
        --top;
        return stack[top + 1];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void clear() {
        top = -1;
    }
    @Override
    public int peek() {
        if (top == -1) {
            throw new IllegalStateException("Nothing to see here");
        }
        return stack[top];
    }
    
    @Override
    public String toString() {
        if (top == -1) {
            return "[]";
        } 
        String str = "[";
        for (int i = 0; i < top; ++i) {
            str += stack[i] + ", ";
        }
        str += stack[top] + "]";
        return str;
    }

    public boolean isFull() {
        return stack.length -1 == top ;
    }
}
