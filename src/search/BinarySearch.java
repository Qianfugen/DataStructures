package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找,一定要是有序序列(这里是从小到大)
 */
public class BinarySearch {
    public static void main(String[] args) {
        //只返回一个结果
//        int[] arr = {1, 8, 10, 89, 1000, 1234};
//        int resIndex = binarySearch(arr, 0, arr.length, 89);
//        System.out.println("resIndex = " + resIndex);

        //返回集合
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> resIndex = binarySearch2(arr, 0, arr.length, 1000);
        System.out.println("resIndex = " + resIndex);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //如果left>right,说明已经遍历完毕,没有找到
        if (left > right) {
            return -1;
        }

        //中间值索引
        int mid = (left + right) / 2;
        //中间值
        int midVal = arr[mid];

        if (findVal < midVal) {
            //往左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (findVal > midVal) {
            //往右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else {
            //找到了,返回mid
            return mid;
        }
    }

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //用来接收结果集
        List<Integer> list = null;
        //如果left>right,说明已经遍历完毕,没有找到
        if (left > right) {
            return list;
        }

        //中间值索引
        int mid = (left + right) / 2;
        //中间值
        int midVal = arr[mid];

        if (findVal < midVal) {
            //往左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else if (findVal > midVal) {
            //往右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else {
            //如果有多个相同的值
            list = new ArrayList<>();

            //向左扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp--;
            }

            //把自己加进去
            list.add(mid);

            //向右扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp++;
            }
        }
        return list;
    }
}
