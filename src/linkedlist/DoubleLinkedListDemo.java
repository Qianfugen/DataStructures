package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode2 hero1 = new HeroNode2(1, "刘备", "老大");
        HeroNode2 hero2 = new HeroNode2(2, "关羽", "老二");
        HeroNode2 hero3 = new HeroNode2(3, "张飞", "老三");
        HeroNode2 hero4 = new HeroNode2(4, "诸葛亮", "老四");

        //添加测试
//        System.out.println("添加测试");
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
//        doubleLinkedList.list();

        //有序添加
        System.out.println("有序添加测试");
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.list();

        //修改测试
        System.out.println("修改测试");
        HeroNode2 hero = new HeroNode2(4, "赵云", "常山赵子龙");
        doubleLinkedList.update(hero);
        doubleLinkedList.list();

        //删除测试
        System.out.println("删除测试");
        doubleLinkedList.delete(2);
        doubleLinkedList.list();

    }
}

class DoubleLinkedList {
    //头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * ]
     * 添加
     *
     * @param hero
     */
    public void add(HeroNode2 hero) {
        HeroNode2 temp = head;
        while (true) {
            if (null == temp.next) {
                //到链表尾部了
                break;
            }
            temp = temp.next;
        }
        temp.next = hero;
        hero.pre = temp;
    }

    /**
     * 有序添加
     *
     * @param hero
     */
    public void addByOrder(HeroNode2 hero) {
        HeroNode2 temp = head;
        //标记是否在链表尾部插入还是中间插入
        boolean flag = false;
        while (true) {
            if (null == temp.next) {
                break;
            }
            if (temp.next.no > hero.no) {
                flag = true;
                break;
            }
            if (temp.next.no == hero.no) {
                System.out.printf("已存在编号为%d的节点!\n", hero.no);
                return;
            }
            temp = temp.next;
        }
        if (flag) {
            //链表中间插入
            hero.next = temp.next;
            hero.pre = temp;
            temp.next.pre = hero;
            temp.next = hero;
        } else {
            //链表尾部插入
            hero.pre = temp;
            temp.next = hero;
        }

    }

    /**
     * 修改
     *
     * @param hero
     */
    public void update(HeroNode2 hero) {
        HeroNode2 temp = head;
        //标记是否找到
        boolean flag = false;
        while (true) {
            if (temp.no == hero.no) {
                flag = true;
                break;
            }
            if (null == temp.next) {
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = hero.name;
            temp.nickname = hero.nickname;
        } else {
            System.out.printf("没有找到编号为%d的节点!\n", hero.no);
        }

    }

    /**
     * 删除
     *
     * @param no
     */
    public void delete(int no) {
        HeroNode2 temp = head;
        //标记是否找到
        boolean flag = false;
        while (true) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            if (null == temp.next) {
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //判断是否是最后一个
            if (null == temp.next) {
                temp.pre.next = null;
            } else {
                temp.pre.next = temp.next;
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到编号为%d节点!\n", no);
        }
    }

    /**
     * 打印
     */
    public void list() {
        HeroNode2 temp = head;
        while (true) {
            if (null == temp.next) {
                break;
            }
            temp = temp.next;
            System.out.println(temp);
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    //指向下一个节点
    public HeroNode2 next;
    //指向前一个节点
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
