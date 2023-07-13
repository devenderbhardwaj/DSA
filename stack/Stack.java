package stack;

public interface Stack {

    void push(int value);

    int pop();

    boolean isEmpty();
    
    void clear();

    int peek();

}