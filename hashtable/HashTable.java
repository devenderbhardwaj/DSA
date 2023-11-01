package hashtable;

import java.util.Iterator;

/**
 * This is not a very good implementation because:
 * <ol>
 * <li>Not enough tested</li>
 * <li>It does not grow array size so not efficient for sizes larget then
 * default capacity</li>
 * </ol>
 * Any suggestions are welcome to improve
 */
public class HashTable {
    private class LinkedList implements Iterable<Integer> {

        private class Node {
            String key;
            int value;
            Node nextNode;

            Node(String key, int value, Node nextNode) {
                this.key = key;
                this.value = value;
                this.nextNode = nextNode;
            }
        }

        private Node head;

        public LinkedList() {
            head = null;
        }

        public void put(String key, int value) {
            if (this.head == null) {
                this.head = new Node(key, value, null);
                return ;
            }
            Node node = this.head;
            if (node.key.equals(key)) {
                node.value = value;
                return ;
            }
            while (node.nextNode != null) {
                if (node.nextNode.key.equals(key)) {
                    node.nextNode.value = value;
                    return ;
                }
                node = node.nextNode;
            }
            node.nextNode = new Node(key, value, null);
        }

        public boolean delete(String key) {
            Node node = this.head;
            if (node == null) {
                return false;
            }
            if (node.key.equals(key)) {
                this.head = node.nextNode;
                return true;
            }
            while (node.nextNode != null) {
                if (node.nextNode.key.equals(key)) {
                    node.nextNode = node.nextNode.nextNode;
                    return true;
                }
                node = node.nextNode;
            }
            return false;
        }

        public Integer get(String key) {
            Node node = this.head;
            while (node != null && !(node.key.equals(key))) {
                node = node.nextNode;
            }
            return node == null ? null : node.value;
        }

        public int search(String key) {
            Node node = this.head;
            int i = 0;
            while (node != null) {
                if (node.key.equals(key)) {
                    return i;
                }
                ++i;
            }
            return -1;
        }

        @Override
        public String toString() {
            if (this.head == null) {
                return "[]";
            }
            Node node = this.head;
            StringBuilder sb = new StringBuilder("]");
            while (node.nextNode != null) {
                sb.append("{").append(node.key).append(" : ").append(node.value).append("}, ");
                node = node.nextNode;
            }
            sb.append("{").append(node.key).append(" : ").append(node.value).append("}]");
            return sb.toString();
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                Node node = LinkedList.this.head;

                @Override
                public boolean hasNext() {
                    return node != null;
                }

                @Override
                public Integer next() {
                    int temp = node.value;
                    node = node.nextNode;
                    return temp;
                }

            };
        }

    }

    LinkedList[] array;
    private final int LEN = 100;

    public HashTable() {
        array = new LinkedList[LEN];
    }

    public void put(String key, int value) {
        int code = key.hashCode();
        if (array[Math.abs(code % LEN)] == null) {
            array[Math.abs(code % LEN)] = new LinkedList();
        }
        array[Math.abs(code % LEN)].put(key, value);
    }

    public Integer get(String key) {
        int code = key.hashCode();
        if (array[Math.abs(code % LEN)] == null) {
            return null;
        }
        return array[Math.abs(code % LEN)].get(key);
    }

    public boolean remove(String key) {
        int code = key.hashCode();
        if (array[Math.abs(code % LEN)] == null) {
            return false;
        }
        return array[Math.abs(code % LEN)].delete(key);
    }

    public boolean containsKey(String key) {
        int code = key.hashCode();
        if (array[Math.abs(code % LEN)] == null) {
            return false;
        }
        return array[Math.abs(code % LEN)].search(key) != -1;
    }

    public void traverse() {
        for (int i = 0; i < array.length; i++) {
            LinkedList sl = array[i];
            if (sl != null) {
                for (Integer integer : sl) {
                    System.out.println(integer);
                }
            }
        }
    }
}
