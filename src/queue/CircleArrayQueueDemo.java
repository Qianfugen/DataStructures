package queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CircleArrayQueue queue = new CircleArrayQueue(4);
        char option = ' ';
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            option = scanner.next().charAt(0);
            switch (option) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入需要添加的数据：");
                    int vaule = scanner.nextInt();
                    queue.addQueue(vaule);
                    break;
                case 'g':
                    try {
                        int value = queue.getQueue();
                        System.out.println("取出的数据为：" + value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int value = queue.headQueue();
                        System.out.println("队列头数据为：" + value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

class CircleArrayQueue {
    //最大容量
    private int maxSize;
    //队列头,指向第一个元素位置,初始值为0
    private int front;
    //队列尾，指向最后一个元素的后一个位置,初始值为0
    private int rear;
    //用于存放数据
    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    /**
     * 判断是否满了
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加队列
     *
     * @param n
     */
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满，无法添加！");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列
     *
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据！");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 显示队列
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 获取有效元素个数
     *
     * @return
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 获取头数据,不是取出数据
     *
     * @return
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据！");
        }
        return arr[front];
    }
}

