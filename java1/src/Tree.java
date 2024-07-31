import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树相关
 * <p>
 * 二叉树解题的思维模式分两类：
 * <p>
 * 1、是否可以通过遍历一遍二叉树得到答案？如果可以，用一个 traverse 函数配合外部变量来实现，这叫「遍历」的思维模式。
 * <p>
 * 2、是否可以定义一个递归函数，通过子问题（子树）的答案推导出原问题的答案？如果可以，写出这个递归函数的定义，并充分利用这个函数的返回值，这叫「分解问题」的思维模式。
 * <p>
 * 无论使用哪种思维模式，你都需要思考：
 * <p>
 * 如果单独抽出一个二叉树节点，它需要做什么事情？需要在什么时候（前/中/后序位置）做？其他的节点不用你操心，递归函数会帮你在所有节点上执行相同的操作。
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
            result = Math.max(result, depth);

        }

        traverse(root.left);
        traverse(root.right);


    }

    /**
     * 543 二叉树的直径
     * <p>
     * 这道题的关键是经过一个节点的最大直径是左子树和右子树的最大深度之和
     * <p/>
     */

    int maxResult = 0;

    // 记录最大直径的长度
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepthCalculate(root);
        return maxResult;
    }

    int maxDepthCalculate(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMaxDepth = maxDepthCalculate(root.left);
        int rightMaxDepth = maxDepthCalculate(root.right);
        maxResult = Math.max(maxResult, leftMaxDepth + rightMaxDepth);
        /*
         * 这里的返回值 1 + Math.max(leftMax, rightMax)
         * 有点难理解
         * 因为后序遍历的顺序，是在遍历完左右节点后才去该节点的后序操作的
         * 也就是说，后序遍历的关键是可以获取它的左右子树的返回值的，也就是这个时候我们是知道leftDepth和rightDepth这两个数值的，那么同时
         * 我们要求的是当前节点的最大深度，所以要1 + Math.max(leftMaxDepth, rightMaxDepth)
         * 因为这个节点也是其它父类节点的左右节点，也要为后面所用
         */
        return 1 + Math.max(leftMaxDepth, rightMaxDepth);
    }


    /**
     * 层序遍历！！！
     * 字节当时挂掉的题
     * 一般写法,
     */
    static void levelTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //当维护的队列不为空的时候才循环
        while (!queue.isEmpty()) {
            //拿到出队的节点
            TreeNode temp = queue.poll();
            //打印队列中的信息这里可以
            System.out.println(temp.val);
            if (temp.left != null) queue.offer(temp.left);
            if (temp.right != null) queue.offer(temp.right);

        }

    }

    /**
     * 升级一下的层序遍历,使用队列
     * 要把一个层级的元素显示出来
     * 这里我们便需要一个for循环
     */
    static void levelTraverse2(TreeNode root) {
        List<List<TreeNode>> resultList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //当维护的队列不为空的时候才循环
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> tempList = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                //拿到出队的节点
                TreeNode temp = queue.poll();
                tempList.add(temp);
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            resultList.add(tempList);
        }
        for (List<TreeNode> level : resultList) {
            for (TreeNode node : level) {
                System.out.print(node.val + " ");
            }
            System.out.println();
        }

    }

    /**
     * 下面是是一种层序遍历不适用队列使用dfs的方法
     */

    static List<List<TreeNode>> resultList = new LinkedList<>();

    static void levelTraverse3(TreeNode root) {

        //规定root节点为第0层，方便size计算
        traverseForLevel(root, 0);

        for (List<TreeNode> level : resultList) {
            for (TreeNode node : level) {
                System.out.print(node.val + " ");
            }
            System.out.println();
        }

    }


    static void traverseForLevel(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth <= resultList.size()) {
            //第一次进入当前层
            resultList.add(new LinkedList<TreeNode>());
        }
        resultList.get(depth).add(root);
        traverseForLevel(root.left, depth + 1);
        traverseForLevel(root.right, depth + 1);


    }

    /**
     * 226. 翻转二叉树
     * 下面是另外的一种方式，采用分解的思想，也就是获取到函数的返回值
     */

    TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        //分解思想要求我们获取的是反转完左子树和右子树以根节点的子树
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        //获取后再交换节点
        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 这里其实有一个隐形的节点，也就是在每次遍历的时候要把left.right和right。left传进去
     */


    /**
     * 114. 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * <p>
     * <p>
     * 思路
     * 这里可以通过遍历的方式解决，
     * 我们可以根据后续遍历来解决问题
     */

    void flatter(TreeNode root) {
        //一般情况
        if (root == null) return;

        flatter(root.left);
        flatter(root.right);
        //后续遍历位置，这里是自下而上的，而且可以理解为需要做的操作
        //下面是把左边放在右边，相当于捋直，自下而上
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        //把原来右边的拼接在现在右边的最后一个节点后面
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;

    }

    /**
     * LCR 124. 推理二叉树
     */
//    public TreeNode deduceTree(int[] preorder, int[] inorder) {
//
//
//    }


    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        levelTraverse3(node1);


    }
}
