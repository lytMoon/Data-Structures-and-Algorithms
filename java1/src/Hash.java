import java.util.HashMap;
import java.util.PriorityQueue;

public class Hash {

    /* 基本的 N 叉树节点 */
    static class TreeNode {
        int val;
        TreeNode[] children;
    }

    static void traverse(TreeNode root) {
        for (TreeNode child : root.children)
            traverse(child);
    }

    public static void main(String[] args) {
        traverse(new TreeNode());



    }
}
