package queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;


public class QueueUsingLinkedList implements Queue{
    LinkedList<Integer> queue ;

    public QueueUsingLinkedList() {
        queue = new LinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public int size() {
        return queue.size();
    }

    @Override
    public int front() {
        if (queue.size() == 0) {
            throw new NoSuchElementException("Queue is Empty");
        }
        return queue.getFirst();
    }

    @Override
    public int rear() {
        if (queue.size() == 0) {
            throw new NoSuchElementException("Queue is Empty");
        }
        return queue.getLast();
    }

    @Override
    public void enqueue(int e) {
        queue.add(e);
    }

    @Override
    public int dequeue() {
        if (queue.size() == 0) {
            throw new IllegalStateException("List is Empty");
        }
        return queue.removeFirst();
    }
    
    @Override
    public String toString() {
        return queue.toString();
    }
}
