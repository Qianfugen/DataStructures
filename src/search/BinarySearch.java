package search;

/**
 * 二分查找,一定要是有序序列(这里是从小到大)
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        int resIndex = binarySearch(arr, 0, arr.length, 5);
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
}
