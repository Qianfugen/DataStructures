package linkedlist;

/**
 * Josephu问题: 设编号为1,2,...,n 的 n 个人围坐一圈,约定编号为 k(1<=k<=n) 的人从1开始报数,数到 m 的那个人出列,他的下一位又是从1
 * 开始报数,数到 m 的那个人出列,以此类推,直到所有人出列,由此产生一个出队编号的序列.
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
//        circleSingleLinkedList.add(3);
//        circleSingleLinkedList.list();

        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

class CircleSingleLinkedList {
    //第一个小伙伴
    private Boy first = null;

    //辅助指针
    private Boy temp = null;

    /**
     * 添加小伙伴
     *
     * @param nums
     */
    public void add(int nums) {
        //数据校验
        if (nums < 0) {
            System.out.println("输入参数有误!");
        }

        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                //构成环形
                first.next = first;
                temp = first;
            } else {
                temp.next = boy;
                boy.next = first;
                //指针往下移一位
                temp = boy;
            }

        }
    }

    /**
     * @param startNo  从第几个开始数数
     * @param countNum 数几个数
     * @param nums     小伙伴个数
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //数据校验
        if (nums < 0 || startNo > nums || countNum > nums) {
            System.out.println("输入参数有误!");
        }
        //添加小伙伴
        add(nums);
        temp = first;

        //先把temp移到最后,移动 n-1 位
        for (int i = 0; i < nums - 1; i++) {
            temp = temp.next;
        }

        //从第几个人开始数数,first 和 temp 都移动 k-1 位
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            temp = temp.next;
        }

        while (true) {
            if (temp == first) {
                //只剩一个小伙伴
                System.out.printf("编号为%d的小伙伴坚持到了最后!\n", first.no);
                break;
            }
            //报数
            for (int i = 0; i < countNum - 1; i++) {
                first = first.next;
                temp = temp.next;
            }
            System.out.printf("编号为%d的小伙伴出列!\n", first.no);
            //出席
            first = first.next;
            temp.next = first;
        }

    }

    public void list() {
        if (null == first) {
            System.out.println("链表为空");
            return;
        }
        temp = first;
        while (true) {
            System.out.println("小孩编号为:" + temp.no);
            if (temp.next == first) {
                //遍历完毕
                break;
            }
            temp = temp.next;
        }
    }

}

class Boy {
    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }
}

