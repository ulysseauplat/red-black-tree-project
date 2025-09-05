package com.redblacktree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class AVLTreeTest {

    private AVLTree tree;

    @BeforeEach
    void setUp() {
        tree = new AVLTree();
    }

    @Test
    void testInsertSingleNode() {
        tree.insertAVL(10);
        NodeAVL root = tree.getRoot();
        assertEquals(10, root.getData());
        assertEquals(0, tree.getBalance(root));
    }

    @Test
    void testInsertMultipleNodes() {
        tree.insertAVL(10);
        tree.insertAVL(20);
        tree.insertAVL(5);
        tree.insertAVL(15);

        checkBalance(tree.getRoot());
    }

    @Test
    void testInsertDuplicateValue() {
        tree.insertAVL(10);
        tree.insertAVL(10); // duplicate ignored

        NodeAVL root = tree.getRoot();
        assertEquals(10, root.getData());
        assertNullOrNil(root.getLeft());
        assertNullOrNil(root.getRight());
    }

    @Test
    void testDeleteLeaf() {
        tree.insertAVL(10);
        tree.insertAVL(5);
        tree.insertAVL(15);

        tree.delete(5);
        NodeAVL node = tree.search(5);
        assertEquals(tree.getNIL(), node); // should be NIL
        checkBalance(tree.getRoot());
    }

    @Test
    void testDeleteNodeWithOneChild() {
        tree.insertAVL(10);
        tree.insertAVL(5);
        tree.insertAVL(15);
        tree.insertAVL(12);

        tree.delete(15);
        NodeAVL node = tree.search(15);
        assertEquals(tree.getNIL(), node);
        checkBalance(tree.getRoot());
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        tree.insertAVL(10);
        tree.insertAVL(5);
        tree.insertAVL(20);
        tree.insertAVL(15);
        tree.insertAVL(25);

        tree.delete(20);
        NodeAVL node = tree.search(20);
        assertEquals(tree.getNIL(), node);
        checkBalance(tree.getRoot());
    }

    @Test
    void testInorderTraversal() {
        tree.insertAVL(10);
        tree.insertAVL(5);
        tree.insertAVL(15);

        tree.inorderTraversal(tree.getRoot());
        System.out.println(); // expected output: 5 10 15
    }

    private void checkBalance(NodeAVL node) {
        if (node == tree.getNIL()) {
            return;
        }
        int balance = tree.getBalance(node);
        assertTrue(balance >= -1 && balance <= 1, "Node " + node.getData() + " is unbalanced with balance factor " + balance);
        checkBalance(node.getLeft());
        checkBalance(node.getRight());
    }

    private void assertNullOrNil(NodeAVL node) {
        assertTrue(node == null || node == tree.getNIL());
    }

    // ================= STRESS TEST =================
    @Test
    void stressTestInsertAndDelete() {
        int numElements = 10000;
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < numElements; i++) {
            values.add(i);
        }
        Collections.shuffle(values, new Random());

        // Insert all values
        for (int val : values) {
            tree.insertAVL(val);
        }

        // Shuffle again for deletion
        Collections.shuffle(values, new Random());

        // Delete all values
        for (int val : values) {
            tree.delete(val);
        }

        // Check tree is empty
        assertEquals(tree.getNIL(), tree.getRoot(), "Tree should be empty after all deletions");
    }
}
