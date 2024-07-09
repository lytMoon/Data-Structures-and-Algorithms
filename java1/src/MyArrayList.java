import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 力扣707，支持泛型
 */
public class MyArrayList<E> {

    //真正记录数据的底层数组
    private E[] data;
    //数组当前的元素个数，不是数组容量
    private int size;
    //数组默认初始容量
    private static final int INT_CAP = 1;

    //无参构造就默认数组初始容量
    public MyArrayList() {
        this(INT_CAP);

    }

    public MyArrayList(int intCap) {
        data = (E[]) new Object[intCap];
        size = 0;
    }

    //增加元素
    public void addLast(E e) {
        int cap = data.length;
        if (size == cap) {
            resize(2 * cap);
        }
        //在尾部插入元素
        data[size] = e;
        size++;
    }

    public void add(int index, E e) {
        //检查索引是否越界
        checkPositionIndex(index);

        int cap = data.length;
        if (size == cap) {
            resize(2 * cap);
        }
        //搬移数据
        //todo:注意要>=index
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        //先腾地方，再插入新的元素
        data[index] = e;
        size++;

    }

    public void addFirst(E e) {
        add(0, e);
    }

    //删除操作
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("没有这个元素！");
        }
        int cap = data.length;
        //进行缩容的判断
        if (size == cap / 4) {
            resize(cap / 2);
        }

        E deletedVal = data[size - 1];
        //todo:删除后必须把它设置为null，否则会出现内存泄露
        data[size - 1] = null;
        size--;

        return deletedVal;


    }

    public E remove(int index) {
        //检查索引是否越界
        checkElementIndex(index);

        int cap = data.length;
        //可以缩容，节约时间
        if (size == cap / 4) {
            resize(cap / 2);
        }

        E deletedVal = data[index];
        //搬移数据
        for (int i = index; i <= size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return deletedVal;

    }

    public E removeFirst() {
        return remove(0);
    }

    //查
    public E get(int index) {
        checkElementIndex(index);
        return data[index];
    }

    //改
    public E set(int index, E element) {
        //检查索引边界
        checkElementIndex(index);
        //修改数据
        E oldVal = data[index];
        data[index] = element;
        return oldVal;
    }

    //一些工具方法
    public int size() {
        return size;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 将 data 的容量改为 newCap
    private void resize(int newCap) {
        E[] temp = (E[]) new Object[newCap];

        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }

        data = temp;
    }
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
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
        System.out.println("size = " + size + " cap = " + data.length);
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {
        // 初始容量设置为 3
        MyArrayList<Integer> arr = new MyArrayList<>(3);

        // 添加 5 个元素
        for (int i = 1; i <= 5; i++) {
            arr.addLast(i);
        }

        arr.remove(3);
        arr.add(1, 9);
        arr.addFirst(100);
        int val = arr.removeLast();

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }




}
