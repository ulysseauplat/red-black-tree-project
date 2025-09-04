package com.redblacktree;

/**
 * The node classe representing a node with his data, color, left child, right child and parent
 */
public class Node 
 {
        private int data;
        private boolean color;
        private Node left, right, parent;
        public static final boolean RED = true;
        public static final boolean BLACK = false;


        public Node(int data) {                 // the automatic color of a new node is red
            this.data = data;
            this.color = RED;
            left = right = parent = null;
        }
        

        public int getData() {
            return data;
        }     
        public boolean getColor() {
            return color;
        }
        public Node getLeft() {
            return left;
        }
        public Node getRight() {
            return right;
        }
        public Node getParent() {
            return parent;
        }
        public void setData(int data) {
            this.data = data;
        }
        public void setColor(boolean color) {
            this.color = color;
        }
        public void setLeft(Node left) {
            this.left = left;
        }
        public void setRight(Node right) {
            this.right = right;
        }
        public void setParent(Node parent) {
            this.parent = parent;
        }


}
