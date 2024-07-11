/**
 * 用二根堆实现优先级队列
 * <p>
 * 上浮操作
 * 插入元素：当新元素插入到堆的末尾时，通过上浮操作将其移动到正确的位置。
 * 增加某个元素的值（对于最大堆）：当某个元素的值增加时，如果增加后的值大于其父节点的值，则需要上浮该元素。
 * <p>
 * 下沉操作
 * 删除堆顶元素：通常将堆顶元素与堆的最后一个元素交换，然后移除堆的最后一个元素。为了恢复堆的性质，需要对新的堆顶元素进行下沉操作。
 * 减少某个元素的值（对于最小堆）：当某个元素的值减少时，如果减少后的值小于其子节点的值，则需要下沉该元素。
 */

class SimpleMinPQ {
    private final int[] heap;
    private int size;

    public SimpleMinPQ(int capacity) {
        // 索引 0 空着不用，所以多分配一个空间
        heap = new int[capacity + 1];
        size = 0;
    }

    public int size() {
        return size;
    }

    // 父节点的索引
    int parent(int node) {
        return node / 2;
    }

    // 左子节点的索引
    int left(int node) {
        return node * 2;
    }

    // 右子节点的索引
    int right(int node) {
        return node * 2 + 1;
    }

    // 交换数组的两个元素
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 查，返回堆顶元素，时间复杂度 O(1)
    public int peek() {
        // 索引 0 空着不用，所以堆顶元素是索引 1
        return heap[1];
    }


    // 增，向堆中插入一个元素，时间复杂度 O(logN)
    public void push(int x) {
        // 把新元素放到最后
        heap[++size] = x;
        // 然后上浮到正确位置
        swim(size);
    }

    // 删，删除堆顶元素，时间复杂度 O(logN)
    public int pop() {
        int res = heap[1];
        // 把堆底元素放到堆顶
        heap[1] = heap[size--];
        // 然后下沉到正确位置
        sink(1);
        return res;
    }

    // 上浮操作，时间复杂度是树高 O(logN)
    private void swim(int x) {
        while (x > 1 && heap[parent(x)] > heap[x]) {
            swap(parent(x), x);
            x = parent(x);
        }
    }

    // 下沉操作，时间复杂度是树高 O(logN)
    private void sink(int x) {
        //循环条件，如果当前节点有子节点就继续循环
        while (left(x) <= size || right(x) <= size) {
            int min = x;
            if (left(x) <= size && heap[left(x)] < heap[min]) {
                min = left(x);
            }
            if (right(x) <= size && heap[right(x)] < heap[min]) {
                min = right(x);
            }
            if (min == x) {
                break;
            }
            swap(x, min);
            x = min;
        }
    }
}
