import java.util.NoSuchElementException;

/**
 * 自定义链表类，类比LinkedList
 * 707 题「设计链表」 leetCode
 * 双链表
 */
public class MyLinkedList<E> {

    // 虚拟头尾节点
    final private Node<E> head, tail;
    private int size;

    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    public MyLinkedList() {
        this.head = new Node<E>(null);
        this.tail = new Node<E>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;

    }

    //    增
    public void addLast(E e) {
        Node<E> x = new Node<E>(e);
        Node<E> temp = tail.prev;
        //这几步比较关键
        temp.next = x;
        x.prev = temp;
        x.next = tail;
        tail.prev = x;
        //跟ArrayList相同
        size++;
    }

    public void addFirst(E e) {
        Node x = new Node<E>(e);
        Node temp = head.next;

        x.next = temp;
        temp.prev = x;
        x.prev = head;
        head.next = x;

        size++;


    }

    public void add(int index, E e) {
        //检查位置是否合法
        checkPositionIndex(index);


        if (index == size) {
            addLast(e);
            return;
        }

        //找到指定位置的node
        Node<E> p = getNode(index);
        //一定要拿到前驱结点
        Node temp = p.prev;

        Node x = new Node<E>(e);

        //链表操作经典2+4操作

        temp.next = x;
        p.prev = x;
        x.next = p;
        x.prev = temp;

        size++;

    }

    //删除
    public E removeFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        //虚拟节点的存在让我们不用考虑空指针问题
        Node<E> x = head.next;
        Node<E> temp = x.next;
        head.next = temp;
        temp.prev = head;

        x.prev = null;
        x.next = null;

        size--;
        return x.val;
    }

    public E removeLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> x = tail.prev;
        Node<E> temp = x.prev;
        temp.next = tail;
        tail.prev = temp;
        x.prev = null;
        x.next = null;
        size--;
        return x.val;
    }

    public E remove(int index) {
        checkElementIndex(index);
        //找到index对应的Node
        Node<E> x = getNode(index);
        Node temp = x.prev;

        temp.next = x.next;
        x.next.prev = temp;

        x.prev = null;
        x.next = null;
        size--;
        return x.val;

    }

    /***** 查 *****/

    public E get(int index) {
        checkElementIndex(index);
        // 找到 index 对应的 Node
        Node<E> p = getNode(index);

        return p.val;
    }

    public E getFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        return head.next.val;
    }

    public E getLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        return tail.prev.val;
    }

    /***** 改 *****/

    public E set(int index, E val) {
        checkElementIndex(index);
        // 找到 index 对应的 Node
        Node<E> p = getNode(index);

        E oldVal = p.val;
        p.val = val;

        return oldVal;
    }

    /***** 其他工具函数 *****/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> p = head.next;

        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    private boolean isElementIndex(int index) {
        return index >= 0;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0;
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void display() {
        System.out.println("size = " + size);
        for (Node<E> p = head.next; p != tail; p = p.next) {
            System.out.print(p.val + " -> ");
        }
        System.out.println("null");
        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList<Integer>();
        myLinkedList.add(0, 1);
        myLinkedList.add(1, 2);
        System.out.println(myLinkedList.get(1));


    }
}
