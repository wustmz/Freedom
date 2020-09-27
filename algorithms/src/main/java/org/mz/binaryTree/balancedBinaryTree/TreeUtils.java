package org.mz.binaryTree.balancedBinaryTree;


import org.mz.binaryTree.TreeNode;

public class TreeUtils {

    /**
     * 平衡二叉树
     * 110.balanced-binary-tree
     * 分治法
     * 左边平衡 && 右边平衡 && 左右两边高度 <= 1，
     * 因为需要返回是否平衡及高度，要么返回两个数据，要么合并两个数据，
     * 所以用-1 表示不平衡，>0 表示树高度（二义性：一个变量有两种含义）。
     *
     * @param root
     * @return
     */
    public Boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        //二义性：-1代表不是平衡的，>0代表是平衡的且树高度为此值
        if (left == -1 || right == -1 || left - right > 1 || right - left > 1) {
            return -1;
        }
        if (left > right) {
            return left + 1;
        }
        return right + 1;
    }
}
