package tree;

import java.util.ArrayList;
import java.util.LinkedList;


public class BST {
    
    private BTNode root;

    public BST() {
        root = null;
    }

    public BST(int e) {
        root = new BTNode(e);
    }
    public BTNode getRoot() {
        return root;
    }
    public void insert(int e) {
        root = insert (e, this.root);
    }
    public boolean search(int e) {
        return search(this.root, e);
    }
    private boolean search(BTNode node, int e) {
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
    private BTNode insert(int e, BTNode node) {
        if (node == null) {
            node = new BTNode(e);
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
    private void inOrder(BTNode node) {
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
    private void postOrder(BTNode node) {
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
    private void preOrder(BTNode node) {
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
    private BTNode delete(BTNode node, int e) {
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
    private BTNode inOrderSuccessor(BTNode node) {
        if (node.left == null) {
            return node;
        }
        return inOrderSuccessor(node.left);
    }

    public ArrayList<Integer> depthFirstTraverse() {
        ArrayList<Integer> dArray = new ArrayList<>();

        LinkedList<BTNode> stack = new LinkedList<>();
        stack.push(root);
        while (stack.size() != 0) {
            BTNode node = stack.pop();
            if (node != null) {
                dArray.add(node.value);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return dArray;
    }

    public ArrayList<Integer> breadthFirstTraverse() {
        ArrayList<Integer> dArray = new ArrayList<>();

        LinkedList<BTNode> queue = new LinkedList<>();
        queue.push(root);
        while (queue.size() != 0) {
            BTNode node = queue.removeFirst();
            if (node != null) {
                dArray.add(node.value);
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        return dArray;

    }
}
