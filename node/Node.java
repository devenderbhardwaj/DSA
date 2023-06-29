package node;
public class Node<T> {
    T value;
    Node<T> nextNode;
    
    public Node(T value) {
        this.value = value;
        nextNode = null;
    }
    public Node(T value, Node<T> nextNode) {
        this.value = value;
        this.nextNode = nextNode;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public Node<T> getNextNode() {
        return nextNode;
    }
    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }
    @Override
    public String toString() {
        return "Node [value=" + value + ", nextNode=" + nextNode + "]";
    }
    
}
