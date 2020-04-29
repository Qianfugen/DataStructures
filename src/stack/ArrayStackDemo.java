package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayStack stack = new ArrayStack(4);
        boolean flag = true;
        while (flag) {
            System.out.println("show: 打印栈");
            System.out.println("push: 入栈");
            System.out.println("pop: 出栈");
            System.out.println("exit: 退出");
            String choice = scanner.next();
            switch (choice) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    flag = false;
                    scanner.close();
                    break;
                default:
                    break;
            }

        }
    }
}

class ArrayStack {
    //栈的大小
    private int maxSize;
    //装数据的数组
    private int[] stack;
    //栈顶
    private int top;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        this.top = -1;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断是否满了
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 入栈
     *
     * @param n
     */
    public void push(int n) {
        if (isFull()) {
            System.out.println("栈已满!");
            return;
        }
        top++;
        stack[top] = n;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空!");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}
