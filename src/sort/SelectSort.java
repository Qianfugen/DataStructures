package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {1, 34, 119, 101};
//        selectSort(arr);
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
        selectSort(arr);

        Date date2 = new Date();
        String date2Str = sdf.format(date2);
        System.out.println("排序后时间: " + date2Str);
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
