package org.mz.algorithms.binaryTree.maximumDepthOfBinaryTree;

import org.mz.algorithms.binaryTree.TreeNode;

public class TreeUtils {

    /**
     * 二叉树的最大深度
     * 104.maximum_depth_of_binary_tree
     * 分治法
     *
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        //divide: 分左右子树分别计算
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        //conquer: 合并左右子树结果
        if (left > right) {
            return left + 1;
        }
        return right + 1;
    }
}
