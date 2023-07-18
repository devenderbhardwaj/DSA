package tree;

public abstract class Tree {
    /**
     * 
     * @param node
     * @return Depth of node in tree
     */
    public static int depth(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int d = 0;
        while (node.left != null) {
            d++;
            node = node.left;
        }
        return d;
    }

    /**
     * 
     * @param node
     * @return numbers of nodes in tree with root <b>node</b>
     */
    public static int numberOfNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + numberOfNodes(node.left) + numberOfNodes(node.right);
    }
    public static boolean isFullBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if (root.left != null && root.right != null) {
            return isFullBinaryTree(root.left) && isFullBinaryTree(root.right);
        }
        return false;
    }

    public static boolean isPerfectBinaryTree(TreeNode root) {
        return isPerfectBinaryTree(root, depth(root), 0);
    }
    private static boolean isPerfectBinaryTree(TreeNode node, int depth , int level) {
        if (node == null) {
            return true;
        }
        if (node.left == null && node.right == null) {
            return (depth == level) ;
        }
        if (node.left == null || node.right == null) {
            return false;
        }
        return isPerfectBinaryTree(node.left, depth, level+1) && isPerfectBinaryTree(node.right, depth, level+1);
    }

    public static boolean isCompleteBinaryTree(TreeNode root) {
        return isCompleteBinaryTree(root, 0, numberOfNodes(root));
    }
    private static boolean isCompleteBinaryTree(TreeNode node, int index, int numberOfNodes) {
        if (node == null ) {
            return true;
        }
        if (index >= numberOfNodes) {
            return false;
        }
        return isCompleteBinaryTree(node.left, 2*index + 1, numberOfNodes) && isCompleteBinaryTree(node.right, 2*index + 2, numberOfNodes)  ;
    }
}