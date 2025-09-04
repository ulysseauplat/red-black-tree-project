package com.redblacktree;

/**
 * Hello world!
 */
public class RedBlackTree {
    private Node root;
    private Node NIL;  // The NIL node represent the leaves of the tree (the null nodes)

    public RedBlackTree() {
        NIL = new Node(0);  // The data of the NIL node is not important
        NIL.setColor(Node.BLACK);
        root = NIL;
    }

    public Node getRoot() {
        return root;
    }
    public Node getNIL() {
        return NIL;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setNIL(Node NIL) {
        this.NIL = NIL;
    }

    public void search(int date) {

    }

    public void insert(int date) {

    }

    public void delete(int date) {

    }

    public void rotateLeft(Node x) {

    }

    public void rotateRight(Node x) {

    }

    

}