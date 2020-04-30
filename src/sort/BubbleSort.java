package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, -2};
//        int[] arr = {1, 2, 3, 4, 5, 6};
//        System.out.println("排序前:"+Arrays.toString(arr));
//        bubbleSort(arr);
//        System.out.println("排序后:"+ Arrays.toString(arr));

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
        bubbleSort(arr);

        Date date2 = new Date();
        String date2Str = sdf.format(date2);
        System.out.println("排序后时间: " + date2Str);


    }

    public static void bubbleSort(int[] arr) {
        //临时交换变量
        int temp = 0;
        //标识是否发生交换
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //从小到大排序,如果前面的数比后面的大,则交换位置
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.printf("第%d趟排序: %s \n", i + 1, Arrays.toString(arr));
            if (!flag) {
                //没有进行交换,说明已排好序,直接退出
                break;
            } else {
                //每次循环前重置flag
                flag = false;
            }
        }
    }
}
