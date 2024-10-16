import java.util.Arrays;

/**
 * 双指针快排的java代码实现
 * 快排
 * <p>
 * 快排采用了一个重要的分治思想
 * <p>
 * 分治策略的基本步骤
 * 分解（Divide）：
 * <p>
 * 将原问题分解成若干个规模较小的子问题。这些子问题的形式与原问题相似，但规模更小。
 * 解决（Conquer）：
 * <p>
 * 递归地解决这些子问题。若子问题规模足够小，则直接解决（通常称为基本情况或递归终止条件）。
 * 合并（Combine）：
 * <p>
 * 将子问题的解合并成原问题的解。合并操作通常涉及将子问题的结果结合成一个完整的解决方案。
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {2, 4, 6, 1, 3, 7, 9, 8, 5};
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));

    }

    static void quickSort(int[] nums, int left, int right) {
        //快排需要左右指针
        //这里我们需要注意，left只能++ right只能-- ，也就是交换元素的时候 指针数值不变！
        if (left < right) {
            int pi = partition(nums, left, right);
            quickSort(nums, left, pi - 1);
            quickSort(nums, pi + 1, right);
        }


    }

    /**
     * partition 有分割的意思
     *
     * @param nums
     * @param left
     * @param right
     */
    static int partition(int[] nums, int left, int right) {
//        int[] a = {2, 4, 6, 1, 3, 7, 9, 8, 5};
        int pivot = nums[left];
        int l = left;
        int r = right;
        while (true) {
            /**
             * 这里需要注意一个小细节，因为我们是left指针先+的那么可能会出现一种情况那就是left+了后>right
             * left 在循环结束时实际上已经超出或者与 right 重合，它并不是当前小于 pivot 的元素，而是下一个可能不符合条件的元素。
             * 所以如果让left先加那么最后返回的就是right
             * 反之，如果让right先-那么久的让left作为最后的partition
             */
            while (left <= right && nums[left] <= pivot) {
                left++;
            }
            while (left <= right && nums[right] >= pivot) {
                right--;
            }
            if (left >= right) {
                break;
            }
            swap(nums, left, right);

        }

        swap(nums, l, right);


        return right;


    }

    static void swap(int[] nums, int left, int right) {
        int temp = nums[right];
        nums[right] = nums[left];
        nums[left] = temp;
    }


    /**
     * 下面来实现一个单链表快排
     *
     */
}
