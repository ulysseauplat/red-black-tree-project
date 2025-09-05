package com.redblacktree;

public class BSTTree {
    private Node root;
    private Node NIL;

    public BSTTree() {
        NIL = new Node(0); // Sentinel node
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
    

    public void insert(int data) {
        Node newNode = new Node(data);
        newNode.setLeft(NIL);
        newNode.setRight(NIL);

        Node parent = NIL;
        Node current = root;

        while (current != NIL) {
            parent = current;
            if (newNode.getData() < current.getData()) {
                current = current.getLeft();
            } 
            else if (newNode.getData() == current.getData()) {
                // Value already exists in the tree, do not insert duplicates
                return;
            }
            else {
                current = current.getRight();
            }
        }

        newNode.setParent(parent);
        if (parent == NIL) {
            root = newNode; // Tree was empty
        } else if (newNode.getData() < parent.getData()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
    
    }
    
    public void inorderTraversal(Node node) {
        if (node != NIL) {
            inorderTraversal(node.getLeft());
            System.out.print(node.getData() + " ");
            inorderTraversal(node.getRight());
        }
    }

    public void delete(int data) {
        Node nodeToDelete = searchNode(root, data);
        if (nodeToDelete == NIL) {
            System.out.println("Value " + data + " not found in the tree.");
            return;
        }

        if (nodeToDelete.getLeft() == NIL) {
            transplant(nodeToDelete, nodeToDelete.getRight());
        } else if (nodeToDelete.getRight() == NIL) {
            transplant(nodeToDelete, nodeToDelete.getLeft());
        } else {
            Node successor = minimum(nodeToDelete.getRight());
            if (successor.getParent() != nodeToDelete) {
                transplant(successor, successor.getRight());
                successor.setRight(nodeToDelete.getRight());
                successor.getRight().setParent(successor);
            }
            transplant(nodeToDelete, successor);
            successor.setLeft(nodeToDelete.getLeft());
            successor.getLeft().setParent(successor);
        }
    }

    private void transplant(Node u, Node v) {
        if (u.getParent() == NIL) {
            root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        if (v != NIL) {
            v.setParent(u.getParent());
        }
    }

    private Node minimum(Node node) {
        while (node.getLeft() != NIL) {
            node = node.getLeft();
        }
        return node;
    }
    private Node searchNode(Node node, int data) {
        if (node == NIL || data == node.getData()) {
            return node;
        }
        if (data < node.getData()) {
            return searchNode(node.getLeft(), data);
        } else {
            return searchNode(node.getRight(), data);
        }
    }
    
    public void print(Node node) {
        printHelper(node, "", true);
    }

    public void printHelper(Node node, String indent, boolean last) {
        if (node != NIL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }
            String sColor = node.getColor() == Node.RED ? "RED" : "BLACK";
            System.out.println(node.getData() + "(" + sColor + ")");
            printHelper(node.getLeft(), indent, false);
            printHelper(node.getRight(), indent, true);
        }
    }

    public Node search(int data) {
        return searchNode(root, data);
    }

}