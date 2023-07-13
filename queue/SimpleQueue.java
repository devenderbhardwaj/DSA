package queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class SimpleQueue implements Queue {
    private int[] queue;
    private int front;
    private int rear;

    public SimpleQueue() {
        queue = new int[10];
        front = rear = -1;
    }
    public SimpleQueue(int capacity) {
        queue = new int[capacity];
        front = rear = -1;
    }
    @Override
    public int dequeue() {
        if (front == -1) {
            throw new NoSuchElementException("Queue is empty");
        }
        int temp = queue[front];
        if (rear == front) {
            front = rear = -1;
        } else {
            front = (front+1) % queue.length;
        }
        return temp;
    }

    @Override
    public void enqueue(int e) {
        if (isFull()) {
            throw new IllegalStateException("Queue is Full");
        }      
        if (front == -1) {
            ++front;
        }
        rear = (rear+1) % queue.length;
        queue[rear] = e;
    }

    @Override
    public int front() {
        if (front == -1) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue[front];
    }

    @Override
    public int rear() {
        if (rear == -1) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue[rear];
    }

    @Override
    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (rear + 1) % queue.length == front;
    }

    public int size() {
        if (front <= rear) {
            return rear - front + 1;
        }
        return rear + queue.length - front + 1;
    }

    @Override
    public String toString() {
        return Arrays.toString(queue) + "; Front Index: " + front + "; Rear Index: " + rear;
    }
}
