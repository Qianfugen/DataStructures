package search;

/**
 * 插入查找法,针对于有序序列
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int resIndex = insertValueSearch(arr, 0, arr.length - 1, 78);
        System.out.println("resIndex = " + resIndex);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("查找次数~~");
        //如果查找的值不在序列内,或者遍历完毕,直接返回-1
        if (left > right || findVal < arr[0] || findVal > arr[right]) {
            return -1;
        }
        //插入查找,自适应定位
        int mid = left + (findVal - arr[left]) * (right - left) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal < midVal) {
            //向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else if (findVal > midVal) {
            //向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else {
            //找到数值findVal
            return mid;
        }
    }
}
