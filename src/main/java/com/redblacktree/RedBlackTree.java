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

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(15);

        tree.print(tree.getRoot());
        System.out.println("Tree properties valid: " + tree.checkProperties());
        tree.delete(10);
        System.out.println("After deleting 10:");
        tree.print(tree.getRoot());
        System.out.println("Tree properties valid: " + tree.checkProperties());
        
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
        Node parent = NIL;

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

        while (Z.getParent() != null && Z.getParent().getColor() == Node.RED) {  // While the parent of Z is red and its not the root
            if (Z.getParent().getParent() == null) {   // case 1: Z's parent is root
                break;
            }
            if (Z.getParent() == Z.getParent().getParent().getLeft()) { // If the parent is a left child
                Node y = Z.getParent().getParent().getRight();  // y is the uncle of Z
                if (y.getColor() == Node.RED) {     // case 3
                    Z.getParent().setColor(Node.BLACK);
                    y.setColor(Node.BLACK);
                    Z.getParent().getParent().setColor(Node.RED);
                    Z = Z.getParent().getParent();  // Move Z up the tree
                } else {        
                    if (Z == Z.getParent().getRight()) {    // case 4
                        Z = Z.getParent();
                        Node rotated = rotateLeft(Z);
                        if (rotated.getParent() == null) root = rotated;
                    }                                      // case 5
                    Z.getParent().setColor(Node.BLACK);
                    Z.getParent().getParent().setColor(Node.RED);
                    Node rotated = rotateRight(Z.getParent().getParent());
                    if (rotated.getParent() == null) root = rotated;
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
                        Node rotated = rotateRight(Z);
                        if (rotated.getParent() == null) root = rotated;
                    }
                    Z.getParent().setColor(Node.BLACK);
                    Z.getParent().getParent().setColor(Node.RED);
                    Node rotated = rotateLeft(Z.getParent().getParent());
                    if (rotated.getParent() == null) root = rotated;
                }
            }
        }
        root.setColor(Node.BLACK);
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

    public boolean checkProperties() {
        /**
         * Check if the tree satisfies the red-black properties
         */
        //property 1: Every node is either red or black is always true with our data structure
        //property 2: the root is black        
        if (root.getColor() != Node.BLACK) {
            return false;
        }
        //property 3: a red node cannot have red children
        if (!checkProperty3(root)) {
            return false;
        }
        //property 4: every path from a node to its descendant NIL nodes must have the same number of black nodes
        int blackCount = 0;
        Node curr = root;
        while (curr != NIL) {
            if (curr.getColor() == Node.BLACK) {
                blackCount++;
            }
            curr = curr.getLeft();
        }
        if (!checkProperty4(root, blackCount, 0)) { 
            return false;
        }
        //property 5: all leaves (NIL nodes) are black is always true with our data structure
        return true;
    }

    public boolean checkProperty4(Node node, int blackCount, int pathBlackCount) {
        if (node == NIL) {
            return blackCount == pathBlackCount;
        }
        if (node.getColor() == Node.BLACK) {
            pathBlackCount++;
        }
        return checkProperty4(node.getLeft(), blackCount, pathBlackCount) &&
               checkProperty4(node.getRight(), blackCount, pathBlackCount);
    }

    public boolean checkProperty3(Node node) {
        boolean colour = node.getColor();
        if (node == NIL) {
            return true;
        }

        else if (colour == Node.RED) {
            if (node.getLeft().getColor() == Node.RED || node.getRight().getColor() == Node.RED) {
                return false;
            }
            else {
                return this.checkProperty3(node.getLeft()) && this.checkProperty3(node.getRight());
            }
        }
        else {
            return this.checkProperty3(node.getLeft()) && this.checkProperty3(node.getRight());
        }
    }
    
    public void print(Node node) {
        printHelper(node, "", true);
    }

    private void printHelper(Node node, String prefix, boolean isTail) {
        if (node == NIL) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + "NIL");
            return;
        }
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getData() + (node.getColor() == Node.RED ? " (R)" : " (B)"));
        if (node.getLeft() != NIL || node.getRight() != NIL) {
            printHelper(node.getLeft(), prefix + (isTail ? "    " : "│   "), false);
            printHelper(node.getRight(), prefix + (isTail ? "    " : "│   "), true);
        }
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