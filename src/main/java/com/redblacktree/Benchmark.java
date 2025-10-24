package com.redblacktree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Benchmark {

    public static void main(String[] args) {
        executionTimeLookup();

    }

    static public void executionTimeInsertion() {
        AVLTree avlTree = new AVLTree();
        RedBlackTree rbTree = new RedBlackTree();
        BSTTree bsTree = new BSTTree();
        int numElements = 100000;
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < numElements; i++) {
            values.add(i);
        }
        Collections.shuffle(values, new Random());

        // Insert all values
        long startTimeAVL = System.currentTimeMillis();
        for (int val : values) {
            avlTree.insertAVL(val);
        }
        long endTimeAVL = System.currentTimeMillis();
        long executionTimeAVL = endTimeAVL - startTimeAVL;
        System.out.println("Execution time for AVL tree : " + executionTimeAVL + " ms.");
        
        long startTimeRB = System.currentTimeMillis();
        for (int val : values) {
            rbTree.insert(val);
        }
        long endTimeRB = System.currentTimeMillis();
        long executionTimeRB = endTimeRB - startTimeRB;
        System.out.println("Execution time for RB tree : " + executionTimeRB + " ms.");

        long startTimeBS = System.currentTimeMillis();
        for (int val : values) {
            bsTree.insert(val);
        }
        long endTimeBS = System.currentTimeMillis();
        long executionTimeBS = endTimeBS - startTimeBS;
        System.out.println("Execution time for BS tree : " + executionTimeBS + " ms.");
    }

    static public void executionTimeDeletion() {
        AVLTree avlTree = new AVLTree();
        RedBlackTree rbTree = new RedBlackTree();
        BSTTree bsTree = new BSTTree();
        int numElements = 100000;
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < numElements; i++) {
            values.add(i);
        }
        Collections.shuffle(values, new Random());

        // Insert all values
        for (int val : values) {
            avlTree.insertAVL(val);
        }
        long startTimeAVL = System.currentTimeMillis();
        for (int val : values) {
            avlTree.delete(val);
        }

        long endTimeAVL = System.currentTimeMillis();
        long executionTimeAVL = endTimeAVL - startTimeAVL;
        System.out.println("Deletion time for AVL tree : " + executionTimeAVL + " ms.");
        
        for (int val : values) {
            rbTree.insert(val);
        }

        long startTimeRB = System.currentTimeMillis();
        for (int val : values) {
            avlTree.delete(val);
        }
        long endTimeRB = System.currentTimeMillis();
        long executionTimeRB = endTimeRB - startTimeRB;
        System.out.println("Deletion time for RB tree : " + executionTimeRB + " ms.");

        for (int val : values) {
            bsTree.insert(val);
        }

        long startTimeBS = System.currentTimeMillis();
        for (int val : values) {
            avlTree.delete(val);
        }
        long endTimeBS = System.currentTimeMillis();
        long executionTimeBS = endTimeBS - startTimeBS;
        System.out.println("Deletion time for BS tree : " + executionTimeBS + " ms.");
    }

    static public void executionTimeLookup() {
    AVLTree avlTree = new AVLTree();
    RedBlackTree rbTree = new RedBlackTree();
    BSTTree bsTree = new BSTTree();
    int numElements = 100000;
    List<Integer> values = new ArrayList<>();
    for (int i = 0; i < numElements; i++) {
        values.add(i);
    }
    Collections.shuffle(values, new Random());

    // Insert all values first
    for (int val : values) {
        avlTree.insertAVL(val);
        rbTree.insert(val);
        bsTree.insert(val);
    }

    // Measure lookup time in AVL tree
    long startTimeAVL = System.currentTimeMillis();
    for (int val : values) {
        avlTree.search(val);
    }
    long endTimeAVL = System.currentTimeMillis();
    System.out.println("Lookup time for AVL tree: " + (endTimeAVL - startTimeAVL) + " ms.");

    // Measure lookup time in Red-Black tree
    long startTimeRB = System.currentTimeMillis();
    for (int val : values) {
        rbTree.search(val);
    }
    long endTimeRB = System.currentTimeMillis();
    System.out.println("Lookup time for RB tree: " + (endTimeRB - startTimeRB) + " ms.");

    // Measure lookup time in BST
    long startTimeBS = System.currentTimeMillis();
    for (int val : values) {
        bsTree.search(val);
    }
    long endTimeBS = System.currentTimeMillis();
    System.out.println("Lookup time for BST tree: " + (endTimeBS - startTimeBS) + " ms.");
}

}



