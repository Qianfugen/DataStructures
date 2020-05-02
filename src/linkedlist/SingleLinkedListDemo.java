package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "刘备", "老大");
        HeroNode hero2 = new HeroNode(2, "关羽", "老二");
        HeroNode hero3 = new HeroNode(3, "张飞", "老三");
        HeroNode hero4 = new HeroNode(4, "诸葛亮", "老四");


        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //无序添加
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero2);
//        singleLinkedList.list();

        //有序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();

        //修改后
        System.out.println("修改后链表");
        HeroNode newHeroNode = new HeroNode(4, "赵云", "常山赵子龙");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.list();

        //删除节点
        System.out.println("删除节点");
        singleLinkedList.del(1);
//        singleLinkedList.del(2);
//        singleLinkedList.del(3);
//        singleLinkedList.del(4);
        singleLinkedList.list();

        System.out.println("链表节点个数：" + singleLinkedList.getLength(singleLinkedList.getHead()));

        //查看倒数第一个
        HeroNode res = singleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 1);
        System.out.printf("res=" + res);

        System.out.println();

//        System.out.println("反转前的链表");
//        singleLinkedList.list();
//        singleLinkedList.reverseList(singleLinkedList.getHead());
//        System.out.println("反转后的链表");
//        singleLinkedList.list();

        System.out.println("逆序打印");
        singleLinkedList.reversePrint(singleLinkedList.getHead());

    }

}


/**
 * 定义SingleLinedList管理我们的英雄
 */
class SingleLinkedList {
    //先初始化一个头结点，不能动,不存数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向列表
     */
    public void add(HeroNode heroNode) {
        //因为head不能动，需要一个辅助节点temp
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //没找到，将temp后移
            temp = temp.next;
        }
        //当退出循环时，temp指向链表最后
        temp.next = heroNode;
    }

    /**
     * 有序添加
     */
    public void addByOrder(HeroNode heroNode) {
        //因为head不能动，需要一个辅助节点temp
        HeroNode temp = head;
        //标志插入节点是否存在，false 不存在，true 已存在
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                //到达链表尾部
                break;
            }
            if (temp.next.no == heroNode.no) {
                //已存在
                flag = true;
                break;
            } else if (temp.next.no > heroNode.no) {
                //找到节点的插入点
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            System.out.printf("编号%d的节点已存在！\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改链表
     */
    public void update(HeroNode newHeroNode) {
        //判断是否空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }

        //因为head不能动，需要一个辅助节点temp
        HeroNode temp = head;
        //标志插入节点是否存在，false 不存在，true 存在
        boolean flag = false;

        //遍历列表
        while (true) {
            if (temp == null) {
                //节点为空
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            //找到节点
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("编号%d的节点不存在！\n", newHeroNode.no);
        }

    }

    /**
     * 删除节点
     */
    public void del(int no) {
        HeroNode temp = head;
        //标志是否找到，false 没找到，true 找到
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                //已到链表最后
                break;
            }
            if (temp.next.no == no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("编号%d节点不存在！", no);
        }
    }

    /**
     * 反转链表
     */
    public void reverseList(HeroNode head) {
        //定义反转链表的头节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        HeroNode temp = head.next;
        HeroNode next = null;
        //没有节点或只有一个节点，不必反转
        if (temp == null || temp.next == null) {
            return;
        }

        while (temp != null) {
            //先暂时保存当前节点的下一个节点，因为后面需要使用
            next = temp.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            //temp后移
            temp = next;
        }
        head.next = reverseHead.next;

    }

    /**
     * 逆序打印
     */
    public void reversePrint(HeroNode head) {
        HeroNode temp = head.next;
        if (temp == null) {
            //链表为空
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 显示链表
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 计算单链表节点个数（不统计头节点）
     */
    public int getLength(HeroNode head) {
        int length = 0;
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 寻到倒数第K节点
     */
    public HeroNode findLastIndexNode(HeroNode head, int index) {
        //从第一个有效节点开始
        HeroNode temp = head.next;

        if (temp == null) {
            //没有有效节点，返回null
            return null;
        }
        int length = getLength(head);
        //对index进行校验
        if (index > length || index <= 0) {
            return null;
        }

        //遍历到倒数第index个节点
        for (int i = 0; i < length - index; i++) {
            temp = temp.next;
        }
        return temp;

    }

    public void showHero() {
    }
}


/**
 * 定义HeroNode,每个HeroNode对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    //指向下一个节点
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
