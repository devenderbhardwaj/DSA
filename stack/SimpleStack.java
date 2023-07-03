package stack;

import java.util.NoSuchElementException;

public class SimpleStack implements Stack {
    private int[] stack;
    private int top;

    @Override
    public int getLength() {
        return top + 1;
    }

    @Override
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
    public void empty() {
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
    public int search(int value) {
        for (int i = 0; i <= top; ++i) {
            if (stack[i] == value) {
                return i;
            }
        }
        return -1;
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

    @Override
    public boolean isFull() {
        return stack.length == top - 1;
    }
}
