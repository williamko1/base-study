package me.qinzc.algorithm.tree;

import java.util.StringJoiner;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 10:03 2023/3/3
 * modifier:
 * modifyTime:
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(2, "吴用");
        HeroNode node2 = new HeroNode(3, "卢俊义");
        HeroNode node3 = new HeroNode(4, "林冲");
        HeroNode node4 = new HeroNode(5, "关胜");
        root.setLeft(node1);
        root.setRight(node2);
        node2.setLeft(node4);
        node2.setRight(node3);
        binaryTree.setRoot(root);
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();

        System.out.println("前序查找 5");
        System.out.println(binaryTree.preOrderSearch(5));
        System.out.println("中序查找 5");
        System.out.println(binaryTree.infixOrderSearch(5));
        System.out.println("后序查找 5");
        System.out.println(binaryTree.postOrderSearch(5));

    }
}
// 定义binaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("无法遍历");
        }
    }
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("无法遍历");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("无法遍历");
        }
    }

    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

}

// 节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HeroNode.class.getSimpleName() + "[", "]")
                .add("no=" + no)
                .add("name='" + name + "'")
                .toString();
    }

    /**
     * 前序遍历
     * 先输出当前节点
     * 如果左子节点不为空，则递归继续前序遍历
     * 如果右子节点不为空，则递归继续前序遍历
     */
    public void preOrder() {
        // 打印当前节点
        System.out.println(this);
        // 递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     * 如果左子节点不为空，则递归继续前序遍历
     * 先输出当前节点
     * 如果右子节点不为空，则递归继续前序遍历
     */
    public void infixOrder() {

        // 递归左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        // 递归右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     * 如果左子节点不为空，则递归继续前序遍历
     * 如果右子节点不为空，则递归继续前序遍历
     * 先输出当前节点
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     * 1. 判断当前节点是否与查找节点相等
     * 2. 如果相等则返回
     * 3. 不相等，判断左子节点是否为空，不为空则进行递归前序查找
     * 4. 为空，判断右子节点是否为空，不为空则进行递归前序查找
     * @param no 需要查找的值
     * @return 返回HeroNode，没找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找");
        // 比较当前节点
        if (this.no == no) {
            return this;
        }
        // 判断左子节点是否为空，不为空则递归查找
        // 如果左子节点查找找到则返回
        HeroNode result = null;
        if (this.left != null) {
            result = this.left.preOrderSearch(no);
        }
        // 说明在左子树找到
        if (result != null) {
            return result;
        }
        // 当前节点的右子树是否为空，不为空就进行递归查找
        if (this.right != null) {
            result = this.right.preOrderSearch(no);
        }
        return result;
    }

    /**
     * 中序遍历
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode result = null;
        if (this.left != null) {
            result = this.left.infixOrderSearch(no);
        }
        if (result != null) {
            return result;
        }
        System.out.println("进入中序遍历查找");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            result = this.right.infixOrderSearch(no);
        }
        return result;
    }

    /**
     * 后序遍历
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {

        HeroNode result = null;
        if (this.left != null) {
            result = this.left.postOrderSearch(no);
        }
        if (result != null) {
            return result;
        }

        if (this.right != null) {
            result = this.right.postOrderSearch(no);
        }
        if (result != null) {
            return result;
        }
        System.out.println("进入后序遍历查找");
        if (this.no == no) {
            return this;
        }
        return null;
    }

}
