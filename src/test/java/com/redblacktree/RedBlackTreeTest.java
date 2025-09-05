package com.redblacktree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreeTest {

    @Test
    void testInsertRoot() {
        // Case: Z is the root
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);

        Node root = tree.getRoot();
        assertEquals(10, root.getData());
        assertEquals(Node.BLACK, root.getColor());
        assertTrue(tree.checkProperties());
    }

    @Test
    void testParentBlack() {
        // Case: Z's parent is black → nothing changes
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10); // root (black)
        tree.insert(5);  // parent is black

        Node z = tree.search(5);
        assertEquals(Node.RED, z.getColor());
        assertTrue(tree.checkProperties());
    }

    @Test
    void testParentRedUncleRed() {
        // Case: Z's parent is red, Z's uncle is red → recolor parent, uncle, grandparent
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10); // root
        tree.insert(5);  // left child
        tree.insert(15); // right child → uncle
        tree.insert(1);  // Z

        Node root = tree.getRoot();
        Node parent = root.getLeft();
        Node uncle = root.getRight();
        Node z = parent.getLeft();

        assertEquals(Node.BLACK, root.getColor());
        assertEquals(Node.BLACK, parent.getColor());
        assertEquals(Node.BLACK, uncle.getColor());
        assertEquals(Node.RED, z.getColor());
        assertTrue(tree.checkProperties());
    }

    @Test
    void testParentBlackUncleBlackAligned() {
        // Case: parent black, uncle black, Z and parent aligned → single rotation
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10); // root
        tree.insert(5);  // left child
        tree.insert(2);  // Z → left-left alignment triggers right rotation

        Node root = tree.getRoot();
        Node z = tree.search(2);

        // After rotation, root should remain black
        assertEquals(Node.BLACK, root.getColor());
        assertEquals(Node.RED, z.getColor());
        assertTrue(tree.checkProperties());
    }

    @Test
    void testParentBlackUncleBlackTriangle() {
        // Case: parent black, uncle black, Z forms triangle → double rotation
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10); // root
        tree.insert(5);  // left child
        tree.insert(7);  // Z → left-right triangle triggers left-right rotation

        Node root = tree.getRoot();
        Node z = tree.search(7);

        // After double rotation, root remains black, Z is red or root? depending on rotation
        assertTrue(tree.checkProperties());
    }

     @Test
    void testCaseWRed() {
        // Case 1: w is red → recolor + rotation
        RedBlackTree tree = new RedBlackTree();
        // Build tree where right sibling of x (w) is red
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);

        // Delete node 5 → x will be NIL, w = 20 (red)
        tree.delete(5);

        // After deletion, RB properties must hold
        assertTrue(tree.checkProperties());
    }

    @Test
    void testCaseWBlackBothChildrenBlack() {
        // Case 2: w is black, w.left black, w.right black → recolor w, move x up
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        // Delete node 5 → x will be NIL, w = 15 (black, both children NIL black)
        tree.delete(5);

        assertTrue(tree.checkProperties());
    }

    @Test
    void testCaseWBlackLeftRedRightBlack() {
        // Case 3: w is black, w.left is red, w.right is black → rotation + recolor
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(15); // left child of w

        // Delete node 5 → x is NIL, w = 20 (black), w.left red
        tree.delete(5);

        assertTrue(tree.checkProperties());
    }

    @Test
    void testCaseWBlackRightRed() {
        // Case 4: w is black, w.right is red → rotation + recolor
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(25); // right child of w

        // Delete node 5 → x is NIL, w = 20 (black), w.right red
        tree.delete(5);

        assertTrue(tree.checkProperties());
    }

    @Test
    void testDeletionComplex() {
        // Test a more complex sequence triggering multiple deletion cases
        RedBlackTree tree = new RedBlackTree();
        int[] values = {10, 5, 15, 1, 7, 12, 20, 17, 25};
        for (int v : values) tree.insert(v);

        // Delete nodes in different positions to trigger all cases
        tree.delete(1);  // leaf
        tree.delete(7);  // leaf
        tree.delete(15); // node with two children
        tree.delete(10); // root

        assertTrue(tree.checkProperties());
    }

 @Test
    void testDeleteFromEmptyTree() {
        RedBlackTree tree = new RedBlackTree();
        // Should not throw any exception
        assertDoesNotThrow(() -> tree.delete(10));
        // Root should remain NIL
        assertEquals(tree.getNIL(), tree.getRoot());
        assertTrue(tree.checkProperties());
    }

    @Test
    void testDeleteNonExistingElement() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        // Delete non-existing node
        assertDoesNotThrow(() -> tree.delete(100));
        // Existing nodes still exist
        assertNotEquals(tree.getNIL(), tree.search(10));
        assertNotEquals(tree.getNIL(), tree.search(5));
        assertNotEquals(tree.getNIL(), tree.search(15));
        assertTrue(tree.checkProperties());
    }


    @Test
    void testCheckProperty3Violation() {
        RedBlackTree tree = new RedBlackTree();
        Node root = new Node(10);
        root.setColor(Node.RED);
        Node left = new Node(5);
        left.setColor(Node.RED);
        Node right = new Node(15);
        right.setColor(Node.BLACK);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);
        tree.setNIL(new Node(0));
        tree.getNIL().setColor(Node.BLACK);

        // left child red with red parent → violates property 3
        assertFalse(tree.checkProperty3(tree.getRoot()));
    }

    @Test
    void testCheckProperty4Violation() {
        RedBlackTree tree = new RedBlackTree();
        Node NIL = new Node(0);
        NIL.setColor(Node.BLACK);
        tree.setNIL(NIL);

        Node root = new Node(10);
        root.setColor(Node.BLACK);
        Node left = new Node(5);
        left.setColor(Node.BLACK);
        Node right = new Node(15);
        right.setColor(Node.BLACK);
        Node leftLeft = new Node(2);
        leftLeft.setColor(Node.BLACK);
        Node rightRight = new Node(20);
        rightRight.setColor(Node.BLACK);

        // Connect children to NIL if they don’t exist
        root.setLeft(left);
        root.setRight(right);
        left.setLeft(leftLeft);
        left.setRight(NIL);
        leftLeft.setLeft(NIL);
        leftLeft.setRight(NIL);
        right.setLeft(NIL);
        right.setRight(rightRight);
        rightRight.setLeft(NIL);
        rightRight.setRight(NIL);

        // Set parents
        left.setParent(root);
        right.setParent(root);
        leftLeft.setParent(left);
        rightRight.setParent(right);

        // Set the root
        tree.setRoot(root);

        // Now test property 4 violation
        assertFalse(tree.checkProperty4(root, 2, 0));

    }

}
