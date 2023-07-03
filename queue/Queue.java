package queue;

public interface Queue {
    boolean isFull() ;

    boolean isEmpty() ;

    int size() ;

    int front() ;

    int rear() ;

    void enqueue(int e) ;

    int dequeue() ;
}
