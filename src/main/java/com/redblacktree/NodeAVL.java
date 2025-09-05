package com.redblacktree;

/**
 * The NodeAVL classe representing a NodeAVL for AVL trees with his data, height, left child, right child and parent
 */
public class NodeAVL
 {
        private int data;
        private NodeAVL left, right, parent;
        private int height;  // Height attribute for AVL tree
        


        public NodeAVL(int data) {                 // the automatic color of a new NodeAVL is red
            this.data = data;
            left = right = parent = null;
        }
        

        public int getData() {
            return data;
        }     
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
        public NodeAVL getLeft() {
            return left;
        }
        public NodeAVL getRight() {
            return right;
        }
        public NodeAVL getParent() {
            return parent;
        }
        public void setData(int data) {
            this.data = data;
        }
        
        public void setLeft(NodeAVL left) {
            this.left = left;
        }
        public void setRight(NodeAVL right) {
            this.right = right;
        }
        public void setParent(NodeAVL parent) {
            this.parent = parent;

        }
        }




