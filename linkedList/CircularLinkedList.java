package linkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList implements LinkedList {

    private class Node {
        private int value;
        private Node nextNode;

        Node(int value, Node nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        int getValue() {
            return value;
        }

        Node getNextNode() {
            return nextNode;
        }

        void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return "Node [value=" + value + ", nextNode=" + nextNode + "]";
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

    private Node head;
    private Node tail;
    private int size = 0;

    public CircularLinkedList() {
        this.head = this.tail = null;
    }

    @Override
    public void add(int value) {
        if (this.tail == null) {
            this.addFirst(value);
            return;
        }
        Node node = new Node(value, this.head);
        this.tail.setNextNode(node);
        this.tail = node;
        ++size;
    }

    @Override
    public void addFirst(int value) {
        Node node = new Node(value, this.head);
        if (head == null) {
            node.setNextNode(node);
            this.head = this.tail = node;
            ++size;
            return;
        }
        this.head = node;
        this.tail.setNextNode(this.head);
        ++size;
    }

    @Override
    public void addAt(int value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }
        if (index == size) {
            this.add(value);
        } else if (index == 0) {
            this.addFirst(value);
        } else {
            Node previosNode = this.head;
            for (int i = 0; i < index - 1; ++i) {
                previosNode = previosNode.getNextNode();
            }
            Node newNode = new Node(value, previosNode.getNextNode());
            previosNode.setNextNode(newNode);
            ++size;
        }
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        } else if (index == 0) {
            this.deleteFirst();
            return;
        } else if (index == size - 1) {
            this.deleteLast();
            return;
        } else {
            Node prev = this.head;
            for (int i = 1; i < index; ++i) {
                prev = prev.getNextNode();
            }
            prev.setNextNode(prev.getNextNode().getNextNode());
            --size;
        }
    }

    @Override
    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("Cannot delete head. Head is already null");
        } else if (head.getNextNode() == this.head) {
            this.head = this.tail = null;
            --size;
            return;
        }
        this.head = this.head.getNextNode();
        this.tail.setNextNode(this.head);
        --size;
    }

    @Override
    public void deleteLast() {
        if (this.tail == null) {
            throw new NoSuchElementException("Cannot Delete from empty list");
        } else if (this.tail.getNextNode() == this.tail) {
            this.head = this.tail = null;
            --size;
            return;
        }
        Node beforeTail = this.head;
        while (beforeTail.getNextNode() != this.tail) {
            beforeTail = beforeTail.getNextNode();
        }
        beforeTail.setNextNode(this.head);
        this.tail = beforeTail;
        --size;
    }

    @Override
    public void update(int value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }
        Node toUpdate = this.head;

        for (int i = 0; i < index; ++i) {
            toUpdate = toUpdate.getNextNode();
        }
        toUpdate.setValue(value);
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }

        Node node = this.head;
        for (int i = 0; i < index; ++i) {
            node = node.getNextNode();
        }
        return node.getValue();
    }

    @Override
    public int getFirst() throws IllegalAccessError {
        if (this.head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return this.head.getValue();
    }

    @Override
    public int getLast() throws IllegalAccessError {
        if (this.tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return this.tail.getValue();
    }

    @Override
    public void traverse() {
        Node node = head;
        if (node == null) {
            System.out.println("List is empty");
        }
        while (node != this.tail) {
            System.out.println(node.getValue());
            node = node.getNextNode();
        }
        System.out.println(this.tail.getValue());
        System.out.println();
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void clear() {
        this.head = this.tail = null;
        this.size = 0;
    }

    @Override
    public void reverse() {
        if (this.head == this.tail) {
            return;
        }
        Node prev = this.tail;
        Node current = this.head;
        while (current != this.tail) {
            Node next = current.getNextNode();
            current.setNextNode(prev);
            prev = current;
            current = next;
        }
        current.setNextNode(prev);
        this.tail = this.head;
        this.head = current;
    }

    @Override
    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        Node node = this.head;
        String str = "[";
        while (node != this.tail) {
            str += node.getValue() + ", ";
            node = node.getNextNode();
        }
        str += node.getValue() + "]";
        return str;
    }

    @Override
    public int search(int value) {
        Node node = this.head;
        for (int i = 0; i < this.size; ++i) {
            if (node.getValue() == value) {
                return i;
            }
        }
        return -1;
    }


    //As this is cicular LinkedList if it contains atleast one element then iteration never stops
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node node = CircularLinkedList.this.head;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Integer next() {
                int temp = node.getValue();
                node = node.getNextNode();
                return temp;
            }
        };
    }
}
