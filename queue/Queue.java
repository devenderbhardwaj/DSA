package queue;

public interface Queue {

    boolean isEmpty() ;

    int front() ;

    int rear() ;

    void enqueue(int e) ;

    int dequeue() ;
}
