package org.mz.algorithms.binaryTree.lruCache;

import lombok.Data;

/**
 * 双向链表
 */
@Data
public class DLinkedNode {

    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;

    public DLinkedNode() {

    }

    public DLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
