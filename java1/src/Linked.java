import java.util.List;
import java.util.PriorityQueue;

/**
 * 跟链表有关的题目
 */
public class Linked {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 合并两个有序的链表
     * 21
     */

    ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //创建一个虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;

            } else {
                p.next = p1;
                p1 = p1.next;
            }
            //指针还是要继续前进
            p = p.next;
        }
        //如果提前结束了
        if (p1 == null) {
            p.next = p2;
        }
        if (p2 == null) {
            p.next = p1;
        }
        return dummy.next;
    }

    /**
     * 分隔链表
     * 86
     * 思路，先分成两个链表，一个比x小，一个比x大，让后再把这两个链表合并
     */

    ListNode partition(ListNode head, int x) {
        //套路两个dummyNode，还有两个p指针
        ListNode dummyNode1 = new ListNode(-1);
        ListNode dummyNode2 = new ListNode(-1);
        ListNode p1 = dummyNode1, p2 = dummyNode2, p = head;

        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }

            //不能直接p=p.next，因为可能会出现环，因为这不是有序的
            ListNode temp = p;
            p = p.next;
            temp.next = null;
        }
        //在这里连接两个链表
        //todo：连接的是dummyNode2.next而不是dummyNode
        p1.next = dummyNode2.next;
        return dummyNode1.next;


    }

    /**
     * 合并 K 个升序链表
     * 23 hard
     */
    ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        //创建一个虚拟头结点，因为涉及到新的链表
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        //优先级队列，最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b) -> (a.val - b.val));
        //将K个链表的头结点加入最小堆，
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }
        while (!pq.isEmpty()) {
            // 获取最小节点，添加到结果链表中
            ListNode temp = pq.poll();
            p.next = temp;
            if (temp.next != null) {
                pq.add(temp.next);
            }
            p = p.next;

        }
        return dummy.next;

    }

    /**
     * 删除倒数第k个节点
     * 19
     * 采用一遍遍历的方式
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy, cur = head;

        for (int i = 1; i <= n; i++) {
            p.next = cur;
            cur = cur.next;
            p = p.next;
        }

        //一定要找到前驱节点
        ListNode prev = dummy;

        while (p.next != null) {
            prev = prev.next;
            p = p.next;
        }
        prev.next = prev.next.next;

        return dummy.next;


    }

    /**
     * 找到链表中点
     * 876
     */
    ListNode middleNode(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        // 慢指针指向中点
        return slow;
    }


    /**
     * 判断链表是否有环
     */

    static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode quick = head;

        boolean result = false;

        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;

            if (quick == slow) {
                result = true;
                break;
            }

        }
        return result;

    }

    /**
     * 判断链表是否有环以及返回第一个点
     * 142 medium
     */


    /**
     * 206. 反转链表
     */

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(-1);

        ListNode p = head;
        ListNode third = head;
        //设置一个前置节点
        ListNode prev = dummyNode;
        ListNode result = null;

        //这里不能用p.next！=null，因为p.next会导致最后一个p节点不能执行next = prev；所以一定要注意循环条件!!!
        //比方这里根据我们的思路动画，p是一定要把自己的next设置为prev的！！！
        while (p != null) {
            ListNode temp = p.next;
            p.next = prev;
            //更新这两个指针
            prev = p;
            p = temp;

        }
        //特殊处理前置节点
        third.next = null;
        return prev;

    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        node4.next=node2;
        System.out.println(hasCycle(node1));
    }


}
