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

            /**
            * Insert a new node with the given data
            * */

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
        
        /**
         * Fix the tree after insertion of a node Z
         */

        while (Z.getParent().getColor() == Node.RED) {  // While the parent of Z is red, if the parent is black its case 2 we have nothing to do
            if (Z == this.root) {   // case 1
                Z.setColor(Node.BLACK);
            }
            else if (Z.getParent() == Z.getParent().getParent().getLeft()) { // If the parent is a left child
                Node y = Z.getParent().getParent().getRight();  // y is the uncle of Z
                if (y.getColor() == Node.RED) {     // case 3
                    Z.getParent().setColor(Node.BLACK);
                    y.setColor(Node.BLACK);
                    Z.getParent().getParent().setColor(Node.RED);
                    Z = Z.getParent().getParent();  // Move Z up the tree
                } else {        
                    if (Z == Z.getParent().getRight()) {    // case 4
                        Z = Z.getParent();
                        rotateLeft(Z);
                    }                                      // case 5, we don't use else because if case 4 is true, we need to do case 5 afterwards
                    Z.getParent().setColor(Node.BLACK);
                    Z.getParent().getParent().setColor(Node.RED);
                    rotateRight(Z.getParent().getParent());
                }
            } else {        // If the parent is a right child
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
        Node z = search(data);
        if (z == NIL) {
            return; // Node not found
        }

        Node y = z;
        Node x;
        boolean yOriginalColor = y.getColor();

        // Standard BST delete with the color change

        if (z.getLeft() == NIL) {
            x = z.getRight();
            transplant(z, z.getRight());
        } else if (z.getRight() == NIL) {
            x = z.getLeft();
            transplant(z, z.getLeft());
        } else {
            y = minimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }

        if (yOriginalColor == Node.BLACK) {
            delete_fixup(x);
        }
    }

    public void delete_fixup(Node x) {
        while (x != root && x.getColor() == Node.BLACK) {   // While x is not the root and x is black
            if (x == x.getParent().getLeft()) {             // If x is a left child 
                Node w = x.getParent().getRight();
                if (w.getColor() == Node.RED) {              // case 1
                    w.setColor(Node.BLACK);
                    x.getParent().setColor(Node.RED);
                    rotateLeft(x.getParent());
                    w = x.getParent().getRight();
                }
                if (w.getLeft().getColor() == Node.BLACK && w.getRight().getColor() == Node.BLACK) {    // case 2
                    w.setColor(Node.RED);
                    x = x.getParent();
                } else {                                                                
                    if (w.getRight().getColor() == Node.BLACK) {    // case 3
                        w.getLeft().setColor(Node.BLACK);
                        w.setColor(Node.RED);
                        rotateRight(w);
                        w = x.getParent().getRight();
                    }
                    w.setColor(x.getParent().getColor());   // case 4
                    x.getParent().setColor(Node.BLACK);
                    w.getRight().setColor(Node.BLACK);
                    rotateLeft(x.getParent());
                    x = root;
                }
            } else {    // same thing on the other side
                Node w = x.getParent().getLeft();
                if (w.getColor() == Node.RED) {
                    w.setColor(Node.BLACK);
                    x.getParent().setColor(Node.RED);
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }
                if (w.getRight().getColor() == Node.BLACK && w.getLeft().getColor() == Node.BLACK) {
                    w.setColor(Node.RED);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == Node.BLACK) {
                        w.getRight().setColor(Node.BLACK);
                        w.setColor(Node.RED);
                        rotateLeft(w);
                        w = x.getParent().getLeft();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Node.BLACK);
                    w.getLeft().setColor(Node.BLACK);
                    rotateRight(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(Node.BLACK);
    }

    private void transplant(Node u, Node v) {
        /**
         * Replaces the subtree rooted at node u with the subtree rooted at node v
         */
        if (u.getParent() == null) {
            root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    private Node minimum(Node node) {
        /**
         * Return the node with the minimum key in the subtree rooted at the given node
         */
        
        while (node.getLeft() != NIL) {
            node = node.getLeft();
        }
        return node;
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