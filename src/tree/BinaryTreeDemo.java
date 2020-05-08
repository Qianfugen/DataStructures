package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建节点
        Hero root = new Hero(1, "宋江");
        Hero node2 = new Hero(2, "吴用");
        Hero node3 = new Hero(3, "卢俊义");
        Hero node4 = new Hero(4, "林冲");
        Hero node5 = new Hero(5, "关胜");

        //手动设置节点
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        //设置根节点
        binaryTree.setRoot(root);

        //前序遍历
//        System.out.println("前序遍历");
//        binaryTree.preOrder();

        //中序遍历
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();

        //后序遍历
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

        //前序查找
//        Hero resNode = binaryTree.preOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了,信息为: id=%d name=%s\n", resNode.getId(), resNode.getName());
//        } else {
//            System.out.printf("没有找到编号为%d的节点!\n", 5);
//        }

        //中序查找
//        Hero resNode = binaryTree.infixOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了,信息为: id=%d name=%s\n", resNode.getId(), resNode.getName());
//        } else {
//            System.out.printf("没有找到编号为%d的节点!\n", 5);
//        }

        //后序查找
//        Hero resNode = binaryTree.postOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了,信息为: id=%d name=%s\n", resNode.getId(), resNode.getName());
//        } else {
//            System.out.printf("没有找到编号为%d的节点!\n", 5);
//        }

        //删除节点测试
        System.out.println("删除节点前:");
        binaryTree.preOrder();
        binaryTree.delNode(5);
        System.out.println("删除节点后:");
        binaryTree.preOrder();

    }
}

//二叉树
class BinaryTree {
    private Hero root;

    public void setRoot(Hero root) {
        this.root = root;
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
