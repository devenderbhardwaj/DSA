package queue;

import java.util.NoSuchElementException;

public class QueueImplementation implements Queue {
    class Node {
        int value; 
        Node next;
    
        Node(int e) {
            value = e;
        }
    }

    Node front;
    Node rear;

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public int front() {
        if (front == null) {
            throw new NoSuchElementException("Queue is Empty");
        }
        return front.value;
    }

    @Override
    public int rear() {
        if (rear == null) {
            throw new NoSuchElementException("Queue is Empty");
        }
        return rear.value;
    }

    @Override
    public void enqueue(int e) {
        Node node = new Node(e);
        if (front == null) {
            front = rear = node;
            return;
        }
        rear.next = node;
        rear = node;
    }

    @Override
    public int dequeue() {
        if (front == null) {
            throw new IllegalStateException("Stack is empty");
        }
        int temp = front.value;
        front = front.next;
        if (front == null) {
            rear = front;
        }
        return temp;
    }

    
}
