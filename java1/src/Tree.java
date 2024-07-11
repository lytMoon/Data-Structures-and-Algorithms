import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树相关
 */
public class Tree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }


    /**
     * 104 二叉树的最大深度
     */

    int depth = 0;
    int result = 0;

    int maxDepth(TreeNode root) {
        traverse(root);
        return result;
    }

    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        //前序的位置
        depth++;
        if (root.left == null && root.right == null) {
            result =Math.max(result,depth);

        }

        traverse(root.left);
        traverse(root.right);


    }


    public static void main(String[] args) {


    }
}
