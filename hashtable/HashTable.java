package hashtable;

import java.util.Iterator;
/**
 * This is not a very good implementation because:
 * <ol>
 *  <li>Not enough tested</li>
 *  <li>It does not grow array size so not efficient for sizes larget then 
 *  default capacity</li>
 * </ol>
 * Any suggestions are welcome to improve
 */
public class HashTable{
    private class SimpleLinkedList implements Iterable<Integer>{

        private class Node {
            private String key;
            private int value;
            private Node nextNode;

            Node(String key, int value, Node nextNode) {
                this.key = key;
                this.value = value;
                this.nextNode = nextNode;
            }

            public String getKey() {
                return key;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
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
        private int size = 0;

        public SimpleLinkedList() {
            head = tail = null;
        }

        public boolean add(String key, int value) {
            if (update(key, value)) {
                return true;
            }
            
            Node node = new Node(key, value, null);
            if (head == null) {
                head = tail = node;
            } else {
                this.tail.setNextNode(node);
                this.tail = node;
            }
            ++size;
            return true;
        }

        public int getSize() {
            return this.size;
        }

        public boolean update(String key, int value) {
            Node toUpdate = this.head;
            for (int i = 0; i < this.getSize(); ++i) {
                if (toUpdate.getKey() == key) {
                    toUpdate.setValue(value);
                    return true;
                }
                toUpdate = toUpdate.getNextNode();
            }
            return false;
        }

        public boolean delete(String key) {
            Node node = this.head;
            if (node.getKey() == key ) {
                this.head = node.getNextNode();
                --size;
                return true;
            }
            for (int i = 0; i < this.getSize()-1; i++) {
                if (node.getNextNode().getKey() == key) {
                    node.setNextNode(node.getNextNode().getNextNode());
                    --size;
                    return true;
                }
            }
            return false;
        }

        

        public Integer get(String key) {
            Node node = this.head;
            while (node != null && !(node.key == key)) {
                node = node.getNextNode();
            }
            return node==null?null:node.getValue();
        }

        
        public int search(String key) {
            Node node = this.head;
            for (int i = 0; i < this.size; ++i) {
                if (node.getKey() == key) {
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
                str += "{";
                str += node.getKey() + ":";
                str += node.getValue() + "}, ";
            }
            str += "{";
            str += node.getKey() + ":";
            str += node.getValue() + "} ] ";
            return str;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                Node node = SimpleLinkedList.this.head;
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

    SimpleLinkedList[] array ;

    public HashTable() {
        array = new SimpleLinkedList[100];
    }

    public  boolean put(String key, int value) {
        int code = key.hashCode();
        if (array[code%100] == null) {
            array[code%100] = new SimpleLinkedList();
        }
        return array[code%100].add(key, value);
    }

    public Integer getValue(String key) {
        int code = key.hashCode();
        if (array[code%100] == null) {
            return null;
        }
        return array[code%100].get(key);
    }

    public boolean remove(String key) {
        int code = key.hashCode();
        if (array[code%100] == null) {
            return false;
        }
        return array[code%100].delete(key);
    }
    
    public boolean containsKey(String key) {
        int code = key.hashCode();
        if (array[code%100] == null) {
            return false;
        }
        return array[code%100].search(key) != -1;
    }

    public void traverse() {
        for (int i = 0; i < array.length; i++) {
            SimpleLinkedList sl = array[i];
            if (sl != null) {
                for (Integer integer : sl) {
                    System.out.println(integer);
                }
            }
        }
    }
}
