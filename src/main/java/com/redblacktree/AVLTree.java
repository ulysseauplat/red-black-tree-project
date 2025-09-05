package com.redblacktree;

public class AVLTree {
    private NodeAVL root;
    private NodeAVL NIL;

    public AVLTree() {
        NIL = new NodeAVL(0); // Sentinel NodeAVL
        NIL.setLeft(NIL);
        NIL.setRight(NIL);
        NIL.setHeight(0);
        root = NIL;
    }

    public NodeAVL getRoot() {
        return root;
    }

    public void setRoot(NodeAVL root) {
        this.root = root;
    }

    public NodeAVL search(int data) {
        NodeAVL current = root;
        while (current != NIL) {
            if (data == current.getData()) {
                return current;
            } else if (data < current.getData()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return NIL; // Not found
    }
    public void rotateLeft(NodeAVL x) {
        NodeAVL y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != NIL) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == NIL) {
            root = y;
        } else if (x == x.getParent().getLeft()) {          
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y); 

        // Update heights after rotation
        updateHeight(x);
        updateHeight(y);
    }

    public void rotateRight(NodeAVL y) {
        NodeAVL x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != NIL) {
            x.getRight().setParent(y);
        }
        x.setParent(y.getParent());
        if (y.getParent() == NIL) {
            root = x;
        } else if (y == y.getParent().getRight()) {
            y.getParent().setRight(x);
        } else {
            y.getParent().setLeft(x);
        }
        x.setRight(y);
        y.setParent(x);

        // Update heights after rotation
        updateHeight(y);
        updateHeight(x);
    }

    public void insertAVL(int data) {
        // Standard BST insertion
        NodeAVL newNodeAVL = new NodeAVL(data);
        newNodeAVL.setLeft(NIL);
        newNodeAVL.setRight(NIL);
        newNodeAVL.setHeight(1); // Initialize height for new node

        NodeAVL parent = NIL;
        NodeAVL current = root;

        while (current != NIL) {
            parent = current;
            if (newNodeAVL.getData() < current.getData()) {
                current = current.getLeft();
            } 
            else if (newNodeAVL.getData() == current.getData()) {
                // Duplicate value, do not insert
                return;
            }
            else {
                current = current.getRight();
            }
        }

        newNodeAVL.setParent(parent);
        if (parent == NIL) {
            root = newNodeAVL; // Tree was empty
        } else if (newNodeAVL.getData() < parent.getData()) {
            parent.setLeft(newNodeAVL);
        } else {
            parent.setRight(newNodeAVL);
        }

        // Rebalance the tree
        rebalanceAfterInsert(newNodeAVL);
    }

    private void rebalanceAfterInsert(NodeAVL node) {
        while (node != NIL) {
            updateHeight(node);
            int balance = getBalance(node);

            if (balance > 1) {
                if (getBalance(node.getLeft()) < 0) {
                    rotateLeft(node.getLeft());
                    updateHeight(node.getLeft());
                    updateHeight(node);
                }
                rotateRight(node);
                updateHeight(node);
            } else if (balance < -1) {
                if (getBalance(node.getRight()) > 0) {
                    rotateRight(node.getRight());
                    updateHeight(node.getRight());
                    updateHeight(node);
                }
                rotateLeft(node);
                updateHeight(node);
            }
            node = node.getParent();
        }
    }
    private void updateHeight(NodeAVL node) {
        if (node == NIL) return;
        int leftHeight = (node.getLeft() != NIL) ? node.getLeft().getHeight() : 0;
        int rightHeight = (node.getRight() != NIL) ? node.getRight().getHeight() : 0;
        node.setHeight(1 + Math.max(leftHeight, rightHeight));
    }
    public int getBalance(NodeAVL node) {
        if (node == NIL) return 0;
        int leftHeight = (node.getLeft() != NIL) ? node.getLeft().getHeight() : 0;
        int rightHeight = (node.getRight() != NIL) ? node.getRight().getHeight() : 0;
        return leftHeight - rightHeight;
    }

    public void delete(int data) {
        NodeAVL nodeToDelete = search(data);
        if (nodeToDelete == NIL) {
            System.out.println("Value " + data + " not found in the tree.");
            return;
        }

        NodeAVL nodeToRebalance;
        if (nodeToDelete.getLeft() == NIL) {
            nodeToRebalance = nodeToDelete.getParent();
            transplant(nodeToDelete, nodeToDelete.getRight());
        } else if (nodeToDelete.getRight() == NIL) {
            nodeToRebalance = nodeToDelete.getParent();
            transplant(nodeToDelete, nodeToDelete.getLeft());
        } else {
            NodeAVL successor = minimum(nodeToDelete.getRight());
            NodeAVL successorParent = successor.getParent();
            if (successorParent != nodeToDelete) {
                transplant(successor, successor.getRight());
                successor.setRight(nodeToDelete.getRight());
                if (successor.getRight() != NIL) {
                    successor.getRight().setParent(successor);
                }
            }
            transplant(nodeToDelete, successor);
            successor.setLeft(nodeToDelete.getLeft());
            if (successor.getLeft() != NIL) {
                successor.getLeft().setParent(successor);
            }
            updateHeight(successor);
            nodeToRebalance = successorParent;
        }

        // Rebalance the tree
        while (nodeToRebalance != NIL) {
            updateHeight(nodeToRebalance);
            rebalanceAfterDelete(nodeToRebalance);
            nodeToRebalance = nodeToRebalance.getParent();
        }
    }

    private void transplant(NodeAVL u, NodeAVL v) {
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

    private NodeAVL minimum(NodeAVL node) {
        while (node.getLeft() != NIL) {
            node = node.getLeft();
        }
        return node;
    }

    private void rebalanceAfterDelete(NodeAVL node) {
        while (node != NIL) {
            updateHeight(node);
            int balance = getBalance(node);

            if (balance > 1) {
                if (getBalance(node.getLeft()) < 0) {
                    rotateLeft(node.getLeft());
                    updateHeight(node.getLeft());
                    updateHeight(node);
                }
                rotateRight(node);
                updateHeight(node);
            } else if (balance < -1) {
                if (getBalance(node.getRight()) > 0) {
                    rotateRight(node.getRight());
                    updateHeight(node.getRight());
                    updateHeight(node);
                }
                rotateLeft(node);
                updateHeight(node);
            }
            node = node.getParent();
        }
    }

    public void inorderTraversal(NodeAVL node) {
        if (node != NIL) {
            inorderTraversal(node.getLeft());
            System.out.print(node.getData() + " ");
            inorderTraversal(node.getRight());
        }
    }

    public NodeAVL getNIL() {
        return NIL;
    }




}