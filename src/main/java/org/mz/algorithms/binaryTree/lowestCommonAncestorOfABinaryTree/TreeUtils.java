package org.mz.algorithms.binaryTree.lowestCommonAncestorOfABinaryTree;

import org.mz.algorithms.binaryTree.TreeNode;

public class TreeUtils {

    /**
     * 二叉树的最近公共祖先
     * 236.lowest-common-ancestor-of-a-binary-tree
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        }

        if (right != null) {
            return right;
        }

        return null;
    }
}
