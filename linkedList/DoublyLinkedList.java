package linkedList;

 
public class DoublyLinkedList implements LinkedList{
    private class Node {
        private int value;
        private Node prevNode;
        private Node nextNode;
        
        private Node(int value, Node prevNode, Node nextNode) {
            this.value = value;
            this.prevNode = prevNode;
            this.nextNode = nextNode;
        }
        
        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }
        public Node getPrevNode() {
            return prevNode;
        }
        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }
        public Node getNextNode() {
            return nextNode;
        }
        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = this.tail = null;
        size = 0;
    }

    @Override
    public void add(int value) {
        if (this.head == null) {
            addFirst(value);
        } else {
            Node node = new Node(value, this.tail, null);
            this.tail.setNextNode(node);
            this.tail = node;
            ++size;
        }
    }

    @Override
    public void addFirst(int value) {
        Node node = new Node(value, null, this.head);
        if (this.head == null) {
            this.head = this.tail = node;
        } else {
            this.head.setPrevNode(node);
            this.head = node;
        }
        ++size;
    }

    @Override
    public void addAt(int value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }
        else if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            add(value);
        } else {
            Node prev = this.head;
            for (int i = 0; i < index; ++i) {
                prev = prev.getNextNode();
            }
            Node node = new Node(value,prev, prev.getNextNode());
            prev.setNextNode(node);
            ++size;
        }
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }
        if (index == 0) {
            deleteFirst();
        } else if (index == size - 1) {
            deleteLast();
        } else {
            Node toDelete = this.head.getNextNode();
            for (int i = 1 ; i < index; ++i) {
                toDelete = toDelete.getNextNode();
            }
            toDelete.getPrevNode().setNextNode(toDelete.getNextNode());
            toDelete.getNextNode().setPrevNode(toDelete.getPrevNode());
            --size;
        }
    }

    @Override
    public void deleteFirst() {
        if (this.head == null) {
            this.head = this.tail = null;
            return ;
        }
        this.head = this.head.getNextNode();
        if (this.head != null) {
            this.head.setPrevNode(null);
        } else {
            this.tail = null;
        }
        --size;
    }

    @Override
    public void deleteLast() {
        if (this.tail == null) {
            this.head = this.tail = null;
            return ;
        }
        this.tail = this.tail.getPrevNode();
        if (this.tail != null) {
            this.tail.setNextNode(null);
        } else {
            this.head = null;
        }
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for size "+this.size);
        }
        Node target = this.head;
        for (int i = 0; i < index; ++i) {
            target = target.getNextNode();
        }
        return target.getValue();
    }

    @Override
    public int getFirst() throws IllegalAccessError {
        if (this.head == null) {
            throw new IllegalAccessError("Empty List");
        } 
        return this.head.getValue();
    }

    @Override
    public int getLast() throws IllegalAccessError {
        if (this.tail == null) {
            throw new IllegalAccessError("Empty List");
        }
        return this.tail.getValue();
    }

    @Override
    public void traverse() {
        Node node = this.head;
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
    public void clear() {
        this.tail = this.head = null;
        size = 0;
    }

    @Override
    public void reverse() {
        if (this.head == null) {
            return;
        }
        Node prev = null;
        Node current = this.head;
        while (current != null) {
            Node next = current.getNextNode();
            current.setNextNode(prev);
            current.setPrevNode(next);
            prev = current;
            current = next;
        }
        this.tail = this.head;
        this.head = prev;
    }

    @Override
    public String toString() {
        if (this.size == 0) {
            return "[]" + " {Size :"+this.size+"}";
        }
        Node node = this.head;
        String str = "[";
        while (node != this.tail) {
            str += node.getValue() + ", ";
            node = node.getNextNode();
        }
        str += node.getValue() + "]" + " {Size :"+this.size+"}";
        return str;
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
    public java.util.Iterator<Integer> iterator() {
        return new java.util.Iterator<Integer>() {
            Node node =DoublyLinkedList.this.head;
            @Override
            public boolean hasNext() {
                return node!=null;
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
