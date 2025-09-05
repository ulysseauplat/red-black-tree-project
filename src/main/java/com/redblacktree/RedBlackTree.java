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

        /** 
         * rotate left around x and then return the new root
         * */

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
        /** 
         * rotate right around x and then return the new root
         * */

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

    
    public Node search(int data) {      
        /** 
         * return the node if found, otherwise return the NIL node
         * */
        Node curr = this.root;
        while (curr.getData() != data && curr != NIL) {

            if (curr.getData() < data) {
                curr = curr.getRight();
            }

            else {
                curr = curr.getLeft();
            }
            
        }
        return curr;        

    }

    public void insert(int data) {

        Node newNode = new Node(data);
        newNode.setLeft(NIL);
        newNode.setRight(NIL);

        if (root == NIL) {
            newNode.setColor(Node.BLACK);
            root = newNode;
            return;
        }

        Node curr = root;
        Node parent = null;

        while (curr != NIL) {
            parent = curr;
            if (newNode.getData() < curr.getData()) {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }

        newNode.setParent(parent);
        if (newNode.getData() < parent.getData()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        // Fix the tree
        fixInsert(newNode);

    }

    public void fixInsert(Node Z) {
        while (Z.getParent().getColor() == Node.RED) {
            if (Z == this.root) {
                Z.setColor(Node.BLACK);
            }
            else if (Z.getParent() == Z.getParent().getParent().getLeft()) {
                Node y = Z.getParent().getParent().getRight();
                if (y.getColor() == Node.RED) {
                    Z.getParent().setColor(Node.BLACK);
                    y.setColor(Node.BLACK);
                    Z.getParent().getParent().setColor(Node.RED);
                    Z = Z.getParent().getParent();
                } else {
                    if (Z == Z.getParent().getRight()) {
                        Z = Z.getParent();
                        rotateLeft(Z);
                    }
                    Z.getParent().setColor(Node.BLACK);
                    Z.getParent().getParent().setColor(Node.RED);
                    rotateRight(Z.getParent().getParent());
                }
            } else {
                Node y = Z.getParent().getParent().getLeft();
                if (y.getColor() == Node.RED) {
                    Z.getParent().setColor(Node.BLACK);
                    y.setColor(Node.BLACK);
                    Z.getParent().getParent().setColor(Node.RED);
                    Z = Z.getParent().getParent();
                } else {
                    if (Z == Z.getParent().getLeft()) {
                        Z = Z.getParent();
                        rotateRight(Z);
                    }
                    Z.getParent().setColor(Node.BLACK);
                    Z.getParent().getParent().setColor(Node.RED);
                    rotateLeft(Z.getParent().getParent());
                }
            }
            


            

        }
    }

    public void delete(int data) {

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