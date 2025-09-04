package com.redblacktree;

/**
 * Hello world!
 */
public class RedBlackTree {
    private Node root;
    private Node NIL;  // The NIL node represent the leaves of the tree (the null nodes) to avoid problems with null

    public RedBlackTree() {
        NIL = new Node(0);  // The data of the NIL node is not important
        NIL.setColor(Node.BLACK);
        root = NIL;
    }

    public Node rotateLeft(Node x) {
        Node y = x.getRight();
        y.setParent(x.getParent());
        x.setRight(y.getLeft());
        if (y.getLeft() != NIL) {
            y.getLeft().setParent(x);
        }
        y.setLeft(x);
        x.setParent(y);
        return y;
    }

    public Node rotateRight(Node x) {
        Node y = x.getLeft();
        y.setParent(x.getParent());
        x.setLeft(y.getRight());
        if (y.getRight() != NIL) {
            y.getRight().setParent(x);
        }
        y.setRight(x);
        x.setParent(y);
        return y;
    }

    
    public void search(int date) {

    }

    public void insert(int date) {

    }

    public void delete(int date) {

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

    
    



}