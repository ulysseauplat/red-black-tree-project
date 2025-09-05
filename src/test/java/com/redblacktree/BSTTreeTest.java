package com.redblacktree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BSTTreeTest {

    @Test
    void testInsertAndInOrderTraversal() {
        BSTTree tree = new BSTTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        // Check in-order traversal (should be sorted)
        StringBuilder sb = new StringBuilder();
        appendInOrder(tree.getRoot(), tree.getNIL(), sb);
        assertEquals("20 30 40 50 60 70 80 ", sb.toString());
    }

    @Test
    void testInsertDuplicateValues() {
        BSTTree tree = new BSTTree();
        tree.insert(40);
        tree.insert(40); // duplicate
        StringBuilder sb = new StringBuilder();
        appendInOrder(tree.getRoot(), tree.getNIL(), sb);
        assertEquals("40 ", sb.toString()); // duplicate ignored
    }

    @Test
    void testSearch() {
        BSTTree tree = new BSTTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);

        assertNotEquals(tree.getNIL(), tree.search(10));
        assertNotEquals(tree.getNIL(), tree.search(5));
        assertEquals(tree.getNIL(), tree.search(100));
    }

    @Test
    void testDeleteLeafNode() {
        BSTTree tree = new BSTTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        tree.delete(5); // leaf
        assertEquals(tree.getNIL(), tree.search(5));
    }

    @Test
    void testDeleteNodeWithOneChild() {
        BSTTree tree = new BSTTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(12);

        tree.delete(15); // node with one child (12)
        assertEquals(tree.getNIL(), tree.search(15));
        assertNotEquals(tree.getNIL(), tree.search(12));
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        BSTTree tree = new BSTTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(12);
        tree.insert(20);

        tree.delete(15); // node with two children
        assertEquals(tree.getNIL(), tree.search(15));
        assertNotEquals(tree.getNIL(), tree.search(12));
        assertNotEquals(tree.getNIL(), tree.search(20));
    }

    // Helper to get in-order traversal as a string
    private void appendInOrder(Node node, Node NIL, StringBuilder sb) {
        if (node != NIL) {
            appendInOrder(node.getLeft(), NIL, sb);
            sb.append(node.getData()).append(" ");
            appendInOrder(node.getRight(), NIL, sb);
        }
    }
}
