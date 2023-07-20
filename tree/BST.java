package tree;

import java.util.LinkedList;

import array.DynamicArray;

public class BST {
    
    private TreeNode root;

    public BST() {
        root = null;
    }

    public BST(int e) {
        root = new TreeNode(e);
    }
    public TreeNode getRoot() {
        return root;
    }
    public void insert(int e) {
        root = insert (e, this.root);
    }
    public boolean search(int e) {
        return search(this.root, e);
    }
    private boolean search(TreeNode node, int e) {
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
    private TreeNode insert(int e, TreeNode node) {
        if (node == null) {
            node = new TreeNode(e);
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
    private void inOrder(TreeNode node) {
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
    private void postOrder(TreeNode node) {
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
    private void preOrder(TreeNode node) {
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
    private TreeNode delete(TreeNode node, int e) {
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
    private TreeNode inOrderSuccessor(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return inOrderSuccessor(node.left);
    }

    public DynamicArray depthFirstTraverse() {
        DynamicArray dArray = new DynamicArray();

        // We don't have generic stack so using java.util.LinkedList
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (stack.size() != 0) {
            TreeNode node = stack.pop();
            if (node != null) {
                dArray.add(node.value);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return dArray;
    }

    public DynamicArray breadthFirstTraverse() {
        DynamicArray dArray = new DynamicArray();

        // We don't have generic linkedlist so using java.util.LinkedList
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (queue.size() != 0) {
            TreeNode node = queue.removeFirst();
            if (node != null) {
                dArray.add(node.value);
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        return dArray;

    }
}
