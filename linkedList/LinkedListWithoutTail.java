package linkedList;

public class LinkedListWithoutTail implements LinkedList {
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
    private int size = 0;

    public LinkedListWithoutTail() {
        head = null;
    }

    @Override
    public void add(int value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = node;
        } else {
            Node tail = this.head;
            while (tail.getNextNode() != null) {
                tail = tail.getNextNode();
            }
            tail.setNextNode(node);
        }
        ++size;
    }

    @Override
    public void addFirst(int value) {
        Node node = new Node(value, this.head);
        this.head = node;
        ++this.size;
    }

    @Override
    public void addAt(int value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bound for size " + this.size);
        }
        if (index == 0) {
            addFirst(value);
        } else {
            Node previous = this.head;
            for (int i = 0; i < index - 1; ++i) {
                previous = previous.getNextNode();
            }
            Node newNode = new Node(value, previous.getNextNode());
            previous.setNextNode(newNode);
            ++size;
        }
    }

    @Override
    public void traverse() {
        Node node = head;
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNextNode();
        }
        System.out.println();
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void update(int value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bound for size " + this.size);
        }
        Node toUpdate = this.head;
        for (int i = 0; i < index; i++) {
            toUpdate = toUpdate.getNextNode();
        }

        toUpdate.setValue(value);
    }

    @Override
    public void deleteFirst() {
        if (head == null) {
            throw new IllegalAccessError("List is empty");
        }
        this.head = this.head.getNextNode();
        --size;
    }

    public void deleteLast() {
        if (head == null) {
            throw new IllegalAccessError("List is empty");
        } else if (head.getNextNode() == null) {
            this.head = null;
            --size;
            return;
        }

        Node node = this.head;
        for (int i=0; i < size-2; ++i) {
            node = node.getNextNode();
        }

        node.setNextNode(null);
        --size;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }
        if (index == 0) {
            deleteFirst();
            return;
        } else {
            Node previousNode = this.head;
            for (int i = 0; i < index-1; ++i) {
                previousNode = previousNode.getNextNode();
            }
            previousNode.setNextNode(previousNode.getNextNode().getNextNode());
            --size;
        }
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }
        Node node = this.head;
        for (int i=0; i < index; ++i) {
            node = node.getNextNode();
        }
        return node.getValue();
    }

    public int getFirst() throws IllegalAccessError {
        if (head == null) {
            throw new IllegalAccessError("List is empty");
        }
        return this.head.getValue();
    }

    public int getLast() throws IllegalAccessError {
        if (head == null) {
            throw new IllegalAccessError("List is empty");
        }
        Node node = this.head;
        while (node.getNextNode() != null) {
            node = node.getNextNode();
        }
        return node.getValue();
    }

    @Override
    public void clear() {
        this.head = null;
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
        this.head = prev;
    }

    @Override
    public int search(int value) {
        if (this.head == null) {
            return -1;
        }
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
    public java.util.Iterator<Integer> iterator() {
        return new java.util.Iterator<Integer>() {
            Node node = LinkedListWithoutTail.this.head;
            public boolean hasNext() {
                return node != null;
            }

            public Integer next() {
                int value = node.getValue();
                node = node.getNextNode();
                return value;
            }
        };
    }
}
