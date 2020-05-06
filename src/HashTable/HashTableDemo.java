package HashTable;

import java.util.Scanner;

/**
 * 哈希表
 */
public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //创建菜单
        Scanner scanner = new Scanner(System.in);
        String key = "";
        while (true) {
            System.out.println("add:  添加员工");
            System.out.println("list: 显示员工");
            System.out.println("find: 查找员工");
            System.out.println("exit: 退出系统");
            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.println("请输入员工id:");
                    int id = scanner.nextInt();
                    System.out.println("请输入员工姓名:");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入需要查找的员工id:");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.size = size;
        //创建链表数组
        empLinkedListArray = new EmpLinkedList[size];
        //一次性把每个链表初始化
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //编写散列函数,根据id指定往哪个链表插入
    public int hashFun(int id) {
        return id % size;
    }


    //添加源=员工
    public void add(Emp emp) {
        //现获取要添加的是那条链表
        int empLinkedListNO = hashFun(emp.id);
        //往指定链表添加员工
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //显示所有链表信息
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据员工id获员工信息
    public void findEmpById(int id) {
        int empLinkedNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedNO].findEmpById(id);
        if (emp != null) {
            System.out.printf("在%d链表中找到的员工信息: id=%d name=%s \n", (empLinkedNO + 1), emp.id, emp.name);
        } else {
            System.out.printf("哈希表中没有找到编号为%d的员工!\n", id);
        }
    }


}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//表示一个链表
class EmpLinkedList {
    //头节点
    private Emp head;

    //给链表添加员工
    public void add(Emp emp) {
        if (head == null) {
            //链表为空,直接添加
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (null == curEmp.next) {
                //到链表尾部了
                break;
            }
            curEmp = curEmp.next;
        }
        //在尾部添加emp
        curEmp.next = emp;
    }

    //显示链表信息
    public void list(int no) {
        if (head == null) {
            System.out.printf("第%d链表为空\n", (no + 1));
            return;
        }
        Emp curEmp = head;
        System.out.printf("第%d链表的信息为: ", (no + 1));
        while (true) {
            System.out.printf("==> id=%d name=%s ", curEmp.id, curEmp.name);
            if (null == curEmp.next) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //通过id获取员工信息
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                //找到了
                break;
            }
            if (null == curEmp.next) {
                //到达链表尾部,没有找到
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
