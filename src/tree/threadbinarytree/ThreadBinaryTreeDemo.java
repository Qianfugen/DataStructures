package tree.threadbinarytree;

public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        //测试线中序索化二叉树的功能
        Hero root = new Hero(1, "tom");
        Hero node2 = new Hero(3, "jack");
        Hero node3 = new Hero(6, "smith");
        Hero node4 = new Hero(8, "mary");
        Hero node5 = new Hero(10, "king");
        Hero node6 = new Hero(14, "dim");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadNodes();

        //测试:以10号节点测试(node5)
        Hero leftNode = node5.getLeft();
        System.out.println("10号节点的前驱节点是: " + leftNode);
        Hero rightNode = node5.getRight();
        System.out.println("10号节点的后继节点是: " + rightNode);

        threadedBinaryTree.threadList();


    }
}

//二叉树
class ThreadedBinaryTree {
    private Hero root;

    //为了实现线索化,需要创建指向当前节点的前驱节点的指针
    //在递归进行线索化时,pre保留前一个节点
    private Hero pre = null;

    public void setRoot(Hero root) {
        this.root = root;
    }

    //遍历线索化二叉树
    public void threadList() {
        Hero node = root;
        while (node != null) {
            //找到叶子节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印节点
            System.out.println(node);
            //如果当前节点的有志者指向的是后继节点,一直输出
            if (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    //重载线索化的方法
    public void threadNodes() {
        this.threadNodes(root);
    }

    //编写对二叉树进行中序线索化的方法

    /**
     * @param node 当前需要线索化的节点
     */
    public void threadNodes(Hero node) {
        //如果node为空,无法线索化
        if (node == null) {
            return;
        }

        //1.先线索化左子树
        threadNodes(node.getLeft());

        //2.线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型,指向前驱节点
            node.setLeftType(1);
        }

        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }

        //每处理一个节点后,让当前节点是下一个节点的前驱节点
        pre = node;

        //3.线索化右子树
        threadNodes(node.getRight());
    }


    public void delNode(int no) {
        if (this.root != null) {
            if (this.root.getId() == no) {
                //root节点如果是要删除节点,直接置空并返回
                this.root = null;
                return;
            } else {
                this.root.delNode(no);
            }
        } else {
            System.out.println("空树,无法删除!");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历!");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历!");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历!");
        }
    }

    //前序查找
    public Hero preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public Hero infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public Hero postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//节点
class Hero {
    private int id;
    private String name;
    //左子节点
    private Hero left;
    //右子节点
    private Hero right;

    //leftType 0 左子树, 1 前驱节点
    private int leftType;
    //rightType 0 右子树,1 后继节点
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    //删除节点(如果是叶子结点,删除叶子结点;如果是非叶子节点,删除该子树)
    public void delNode(int no) {
        //判断当前节点的左子节点是否是要删除节点,是的话,置空并返回
        if (this.left != null && this.left.getId() == no) {
            this.left = null;
            return;
        }
        //判断当前节点的右子节点是否是要删除节点,是的话,置空并返回
        if (this.right != null && this.right.getId() == no) {
            this.right = null;
            return;
        }

        //左子节点递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }

        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //前序遍历
    public void preOrder() {
        //先打印当前节点
        System.out.println(this);
        //递归打印左子节点
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归打印右子节点
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归打印左子节点
        if (this.left != null) {
            this.left.infixOrder();
        }
        //打印当前节点
        System.out.println(this);
        //递归打印右子节点
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后续遍历
    public void postOrder() {
        //递归打印左子节点
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归打印右子节点
        if (this.right != null) {
            this.right.postOrder();
        }
        //打印当前节点
        System.out.println(this);
    }

    //前序遍历查找
    public Hero preOrderSearch(int no) {
        System.out.println("前序查找~~");
        //先找当前节点,如果是,直接返回
        if (this.getId() == no) {
            return this;
        }

        //返回的结果节点
        Hero resNode = null;
        //左节点递归
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        //resNode不为空,左节点递归找到了
        if (resNode != null) {
            return resNode;
        }

        //右节点递归
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        //无论是否找到,直接返回
        return resNode;
    }

    //中序遍历查找
    public Hero infixOrderSearch(int no) {
        Hero resNode = null;
        //左节点递归
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        //resNode不为空,左节点递归找到了
        if (resNode != null) {
            return resNode;
        }
        System.out.println("中序查找~~");
        //当前节点查找
        if (this.getId() == no) {
            return this;
        }

        //右节点递归
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        //无论是否找到,直接返回
        return resNode;
    }

    //后序遍历查找
    public Hero postOrderSearch(int no) {
        Hero resNode = null;
        //左节点递归
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        //resNode不为空,左节点递归找到了
        if (resNode != null) {
            return resNode;
        }

        //右节点递归
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        //resNode不为空,右节点递归找到了
        if (resNode != null) {
            return resNode;
        }

        System.out.println("后序查找~~");
        if (this.getId() == no) {
            return this;
        }

        return resNode;
    }


    public Hero(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hero getLeft() {
        return left;
    }

    public void setLeft(Hero left) {
        this.left = left;
    }

    public Hero getRight() {
        return right;
    }

    public void setRight(Hero right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
