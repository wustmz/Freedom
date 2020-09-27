package org.mz.binaryTree.binaryTreeMaximumPathSum;


import org.mz.binaryTree.TreeNode;

public class TreeUtils {

    int maxSum = Integer.MIN_VALUE;

    /**
     * 二叉树中的最大路径和
     * 124.binary-tree-maximum-path-sum
     * 分治法
     *
     * @param node
     * @return
     */
    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        //递归计算左右子节点的最大贡献值
        //只有最大贡献值大于0，才会选取对应子节点
        int left = Math.max(maxGain(node.left), 0);
        int right = Math.max(maxGain(node.right), 0);

        //节点的最大路径和取决于该节点的值与该节点左右子节点的最大贡献值
        int priceNewPath = node.val + left + right;

        maxSum = Math.max(maxSum, priceNewPath);

        //返回节点的最大贡献值
        return node.val + Math.max(left, right);
    }

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }
}
