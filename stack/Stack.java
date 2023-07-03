package stack;

public interface Stack {

    int getLength();

    int getCapacity();

    void push(int value);

    int pop();

    boolean isEmpty();

    boolean isFull() ;
    
    void empty();

    int peek();

    int search(int value);

    String toString();

}