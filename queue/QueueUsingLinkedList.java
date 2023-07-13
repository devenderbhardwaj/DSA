package queue;

import java.util.NoSuchElementException;

import linkedList.SimpleLinkedList;

public class QueueUsingLinkedList implements Queue{
    SimpleLinkedList queue ;

    public QueueUsingLinkedList() {
        queue = new SimpleLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return queue.getSize() == 0;
    }

    public int size() {
        return queue.getSize();
    }

    @Override
    public int front() {
        if (queue.getSize() == 0) {
            throw new NoSuchElementException("Queue is Empty");
        }
        return queue.getFirst();
    }

    @Override
    public int rear() {
        if (queue.getSize() == 0) {
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
        if (queue.getSize() == 0) {
            throw new IllegalStateException("List is Empty");
        }
        int temp  = queue.getFirst();
        queue.deleteFirst();
        return temp;
    }
    
    @Override
    public String toString() {
        return queue.toString();
    }
}
