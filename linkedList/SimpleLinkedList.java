package linkedList;

import java.util.Iterator;

public class SimpleLinkedList implements LinkedList {

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

    public SimpleLinkedList() {
        head = tail = null;
    }

    @Override
    public void add(int value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = tail = node;
        } else {
            this.tail.setNextNode(node);
            this.tail = node;
        }
        ++size;
    }

    @Override
    public void addFirst(int value) {
        Node node = new Node(value, head);
        if (head == null) {
            this.head = this.tail = node;
            ++this.size;
            return;
        }
        this.head = node;
        ++this.size;
    }

    @Override
    public void addAt(int value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }
        if (index == 0) {
            addFirst(value);
        } else if (index == this.size) {
            this.add(value);
        } else {
            Node previosNode = this.head;
            for (int i = 0; i < index-1; ++i) {
                previosNode = previosNode.getNextNode();
            }
            Node newNode = new Node(value, previosNode.getNextNode());
            previosNode.setNextNode(newNode);
            ++this.size;
        }
    }

    @Override
    public void traverse() {
        Node node = head;
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNextNode();
        }
    }

    @Override
    public int getSize() {
        return this.size;
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
    public void deleteFirst() {
        if (head == null) {
            throw new IllegalStateException("Cannot delete head because list is empty");
        }
        this.head = this.head.getNextNode();
        --size;
    }

    @Override
    public void deleteLast() {
        if (head == null) {
            throw new Error("Cannot delete tail. Tail is already null");
        } else if (head == tail) {
            this.head = this.tail = null;
            --size;
            return;
        }

        Node node = this.head;
        while (node.getNextNode() != this.tail) {
            node = node.getNextNode();
        }
        this.tail = node;
        node.setNextNode(null);
        --size;
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }
        if (index == 0) {
            deleteFirst();
            return;
        } else if (index == size - 1) {
            deleteLast();
            return;
        } else {
            Node node = this.head;
            for (int i = 0; i < index-1; ++i) {
                node = node.getNextNode();
            }
            node.setNextNode(node.getNextNode().getNextNode());
            --size;
        }
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }
        Node node = this.head;
        int count = 0;
        if (count < index) {
            node = node.getNextNode();
        }
        return node.getValue();
    }

    @Override
    public int getFirst() {
        if (head == null) {
            throw new IllegalAccessError("List is empty");
        }
        return this.head.getValue();
    }

    @Override
    public int getLast() {
        if (head == null) {
            throw new IllegalAccessError("List is empty");
        }
        return this.tail.getValue();
    }

    @Override
    public void clear() {
        this.head = this.tail = null;
        this.size = 0;
    }

    @Override
    public void reverse() {
        if (this.head == null || this.head.getNextNode() == null) {
            return;
        }
        Node prev = null;
        Node current = this.head;
        while (current != null) {
            Node next = current.getNextNode();
            current.setNextNode(prev);
            prev = current;
            current = next;
        }
        this.tail = this.head;
        this.head = prev;
    }

    @Override
    public int search(int value) {
        Node node = this.head;
        for (int i = 0; i < this.size; ++i) {
            if (node.getValue() == value) {
                return i;
            }
            node = node.getNextNode();
        }
        return -1;
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return "[]";
        }
        Node node = this.head;
        String str = "[";
        while (node.getNextNode() != null) {
            str += node.getValue() + ", ";
            node = node.getNextNode();
        }
        str += node.getValue() + "]";
        return str;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private Node node = SimpleLinkedList.this.head;

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
