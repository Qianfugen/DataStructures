package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1,-1,89};
//        insertSort(arr);
//        System.out.println("排序后: " + Arrays.toString(arr));

        //测试80000数据
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            //产生[0,80000)的随机数,填充数组
            arr[i] = (int) (Math.random() * 80000);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String date1Str = sdf.format(date1);
        System.out.println("排序前时间: " + date1Str);

        //开始排序
        insertSort(arr);

        Date date2 = new Date();
        String date2Str = sdf.format(date2);
        System.out.println("排序后时间: " + date2Str);
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
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
//            System.out.printf("第%d趟排序: %s\n", i, Arrays.toString(arr));
        }
    }

}
