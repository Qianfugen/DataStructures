package recursion;

public class Queue8Demo {
    public static void main(String[] args) {
        Queue8 queue = new Queue8();
        queue.check(0);
        System.out.printf("共有%d种解法!\n", queue.getCount());
        System.out.printf("共判断了%d次!\n", queue.getJudgeCount());
    }
}

class Queue8 {
    //大小
    int max = 8;
    //解法个数
    int count = 0;
    //判断次数
    int judgeCount = 0;
    //下标为行,值为列
    int[] arr;

    public Queue8() {
        arr = new int[max];
    }

    /**
     * 摆放第 n 个皇后是否可行(从0开始)
     *
     * @param n
     */
    public void check(int n) {
        if (n == max) {
            //说明前面8个都通过了,此法可行
            print();
            return;
        }
        //测试第n行的每一列
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n,放在该行第一列
            arr[n] = i;
            if (judge(n)) {
                //该点可行,判断下一点
                check(n + 1);
            }
            //如果冲突,则将皇后n在本行后移一位
        }
    }


    /**
     * arr[i]==arr[n] 判断是否在同一列
     * Math.abs(n-i)==Math.abs(arr[i]=arr[n]) 判断是否在同一斜线
     * 不用判断是否在同一行,因为 n 是递增的
     *
     * @param n 代表放置第几个皇后(从0开始)
     * @return
     */
    public boolean judge(int n) {
        //记录判断次数
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[i] - arr[n])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印解法
     */
    public void print() {
        //解法个数
        count++;
        for (int i = 0; i < max; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public int getCount() {
        return count;
    }

    public int getJudgeCount() {
        return judgeCount;
    }
}
