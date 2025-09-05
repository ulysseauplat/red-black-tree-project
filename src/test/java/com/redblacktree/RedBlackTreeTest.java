package com.redblacktree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RedBlackTreeTest {

    // Helper to create a RedBlackTree
    private RedBlackTree createTree() {
        return new RedBlackTree();
    }

    @Test
    public void testInsertAndSearchSingleNode() {
        RedBlackTree tree = createTree();
        tree.insert(10);
        Node result = tree.search(10);
        assertNotNull(result);
        assertEquals(10, result.getData());
        assertEquals(Node.BLACK, result.getColor());
    }

    @Test
    public void testInsertMultipleNodesAndSearch() {
        RedBlackTree tree = createTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);

        assertEquals(20, tree.getRoot().getData());
        assertEquals(10, tree.search(10).getData());
        assertEquals(30, tree.search(30).getData());
        assertEquals(tree.getNIL(), tree.search(99));
    }


    @Test
    public void testInsertRootIsBlack() {
        RedBlackTree tree = createTree();
        tree.insert(42);
        assertEquals(Node.BLACK, tree.getRoot().getColor());
    }

    @Test
    public void testInsertMaintainsNILLeaves() {
        RedBlackTree tree = createTree();
        tree.insert(5);
        Node root = tree.getRoot();
        assertEquals(tree.getNIL(), root.getLeft());
        assertEquals(tree.getNIL(), root.getRight());
    }

}