package sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {1, 34, 119, 101};
        selectSort(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //最小值
            int min = arr[i];
            //最小值下标
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    //min不是最小,重置
                    min = arr[j];
                    //重置minIndex
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                //当前值不是最小,进行替换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.printf("第%d趟排序: %s\n", i + 1, Arrays.toString(arr));
        }
    }
}
