package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println("快速排序: " + Arrays.toString(arr));

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
        quickSort(arr, 0, arr.length - 1);

        Date date2 = new Date();
        String date2Str = sdf.format(date2);
        System.out.println("排序后时间: " + date2Str);
    }

    public static void quickSort(int[] arr, int left, int right) {
        //左下标
        int l = left;
        //右下标
        int r = right;
        //pivot表示中轴值
        int pivot = arr[(left + right) / 2];
        //临时变量
        int temp = 0;
        while (l < r) {
            //在pivot左边查找,大于等于pivot的值才退出
            while (arr[l] < pivot) {
                l++;
            }
            //在pivot右边查找,小于等于pivot的值才退出
            while (arr[r] > pivot) {
                r--;
            }
            //l>=r,说明pivot左边的值已经全部小于pivot,pivot右边的值已经全部大于pivot
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换后arr[l]==pivot,r--,前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        //l==r,必须l++,r--,否则会栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
