package queue;

import java.util.NoSuchElementException;

import linkedList.SimpleLinkedList;

public class QueueUsingLinkedList implements Queue{
    private static final int DEFAULT_QUEUE_LENGTH = 10;
    SimpleLinkedList queue ;
    int capacity ;


    public QueueUsingLinkedList() {
        queue = new SimpleLinkedList();
        capacity = DEFAULT_QUEUE_LENGTH;
    }

    public QueueUsingLinkedList(int capacity) {
        queue = new SimpleLinkedList();
        this.capacity = capacity;
    }

    @Override
    public boolean isFull() {
        return queue.getSize() == capacity;
    }

    @Override
    public boolean isEmpty() {
        return queue.getSize() == 0;
    }

    @Override
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
        if (queue.getSize() == capacity) {
            throw new IllegalStateException("List is full");
        }
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
