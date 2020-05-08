package tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        System.out.println("前序遍历");
        arrayBinaryTree.preOrder();
        System.out.println();
        System.out.println("中序遍历");
        arrayBinaryTree.infixOrder();
        System.out.println();
        System.out.println("后续遍历");
        arrayBinaryTree.postOrder();
    }
}

class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //preOrder方法重载
    public void preOrder() {
        preOrder(0);
    }

    //infixOrder方法重载
    public void infixOrder() {
        infixOrder(0);
    }

    //postOrder方法重载
    public void postOrder() {
        postOrder(0);
    }

    //前序遍历
    public void preOrder(int index) {
        //先判断数组是否为空
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,无法遍历!");
            return;
        }
        //先打印自己
        if (index >= 0 && index < arr.length) {
            System.out.print(arr[index]+"\t");
        }
        //左节点递归遍历,左节点索引 2*index+1
        if ((2 * index + 1) >= 0 && (2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //右节点递归遍历,右节点索引 2*index+2
        if ((2 * index + 1) >= 0 && (2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //中序遍历
    public void infixOrder(int index) {
        //先判断数组是否为空
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,无法遍历!");
            return;
        }
        //左节点递归遍历,左节点索引 2*index+1
        if ((2 * index + 1) >= 0 && (2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        //打印自己
        if (index >= 0 && index < arr.length) {
            System.out.print(arr[index]+"\t");
        }
        //右节点递归遍历,右节点索引 2*index+2
        if ((2 * index + 1) >= 0 && (2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    //中序遍历
    public void postOrder(int index) {
        //先判断数组是否为空
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,无法遍历!");
            return;
        }
        //左节点递归遍历,左节点索引 2*index+1
        if ((2 * index + 1) >= 0 && (2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        //右节点递归遍历,右节点索引 2*index+2
        if ((2 * index + 1) >= 0 && (2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        //打印自己
        if (index >= 0 && index < arr.length) {
            System.out.print(arr[index]+"\t");
        }
    }

}