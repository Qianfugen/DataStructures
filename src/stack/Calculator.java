package stack;

public class Calculator {
    public static void main(String[] args) {
        //数栈
        ArrayStack2 numStack = new ArrayStack2(10);
        //符号栈
        ArrayStack2 operStack = new ArrayStack2(10);
        //运算表达式
        String expression = "1+12*3-21";

        //用于扫描的指针
        int index = 0;
        //数字1
        int num1 = 0;
        //数字2
        int num2 = 0;
        //运算符
        char oper;
        //结果
        int res = 0;
        //每次扫描的char保存到ch
        char ch;
        //用于拼接多位数
        String keepNum = "";

        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                //若为操作符
                if (!operStack.isEmpty()) {
                    //符号栈不为空
                    if (operStack.priority(ch) <= operStack.priority((char) operStack.peek())) {
                        //当前oper优先级<=顶栈的运算符,取数运算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char) operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        //把运算结果压入数栈
                        numStack.push(res);
                        //把当前oper压入符号栈
                        operStack.push(ch);
                    } else {
                        //当前oper优先级>顶栈的运算符,直接压栈
                        operStack.push(ch);
                    }
                } else {
                    //符号栈为空,直接压栈
                    operStack.push(ch);
                }
            } else {

                //若为数字,截取的是字符,存放的是数字,需要ascii转换,仅仅适用于一位数
                //numStack.push(ch - 48);
                //多位数操作
                keepNum += ch;
                if (index == expression.length() - 1) {
                    //到达最后一位,直接入栈
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //后一位是操作数,则停止拼接字符串
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum清空
                        keepNum = "";
                    }
                }
            }

            index++;
            if (index >= expression.length()) {
                //扫描表达式完毕,退出循环
                break;
            }
        }

        while (true) {
            //顺序从数栈取数,符号栈取操作数进行运算
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = (char) operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
            if (operStack.isEmpty()) {
                break;
            }
        }
        res = numStack.pop();
        System.out.printf("表达式 %s = %d\n", expression, res);


    }
}

class ArrayStack2 {
    //栈的大小
    private int maxSize;
    //装数据的数组
    private int[] stack;
    //栈顶
    private int top;

    public ArrayStack2(int maxSize) {
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

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空!");
        }
        return stack[top];
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

    /**
     * 优先级判断
     * 自定义的优先级,数字越大,优先级越高
     *
     * @param oper
     * @return
     */
    public int priority(char oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是否为操作符
     *
     * @param value
     * @return
     */
    public boolean isOper(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    /**
     * 运算
     *
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, char oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}

