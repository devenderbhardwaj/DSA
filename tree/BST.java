package tree;

public class BST {
    private class Node {
        int value;
        Node left, right;

        Node(int e) {
            this.value = e;
            left = right = null;
        }
    }

    Node root;

    public BST() {
        root = null;
    }

    public BST(int e) {
        root = new Node(e);
    }

    public void insert(int e) {
        root = insert (e, this.root);
    }
    public boolean search(int e) {
        return search(this.root, e);
    }
    private boolean search(Node node, int e) {
        if (node == null) {
            return false;
        }
        if (e > node.value) {
            return search(node.right, e);
        } else if (e < node.value) {
            return search(node.left, e);
        }
        return true;
    }
    private Node insert(int e, Node node) {
        if (node == null) {
            node = new Node(e);
            return node;
        }
        if (node.value > e) {
            node.left = insert(e, node.left);
            
        } else if (node.value < e) {
            node.right = insert(e, node.right);
        }
        return node;
    }

    public void inOrder() {
        inOrder(this.root);
    }
    private void inOrder(Node node) {
        if (node == null) {
            return ;
        }
        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }
    public void postOrder() {
        postOrder(this.root);
    }
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }
    public void preOrder() {
        preOrder(this.root);
    }
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }
    public void delete(int e) {
        this.root = delete(this.root, e);
    }
    private Node delete(Node node, int e) {
        if (node == null) {
            return node;
        }
        if (e < node.value) {
            node.left = delete(node.left, e) ;
            return node;
        } else if (e > node.value) {
            node.right = delete(node.right, e);
            return node;
        }
        if (node.right == null) {
            return node.left;
        } else if  (node.left == null) {
            return node.right;
        }
        node.value = inOrderSuccessor(node.right).value;
        node.right = delete(node.right, node.value);
        node.right = node.right;
        return node ;
    }
    private Node inOrderSuccessor(Node node) {
        if (node.left == null) {
            return node;
        }
        return inOrderSuccessor(node.left);
    }
}
