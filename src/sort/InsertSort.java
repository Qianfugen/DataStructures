package sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1,-1,89};
        insertSort(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //待插入的数
            int insertValue = arr[i];
            //待插入的位置下标,往前插
            int insertIndex = i - 1;
            //规定从小到大排列
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertValue;
//            System.out.printf("第%d趟排序: %s\n", i, Arrays.toString(arr));
        }
    }

}
