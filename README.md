# Tree Data Structures Project

This project implements three types of binary search trees in Java:

1. **Binary Search Tree (BST)**
2. **AVL Tree**
3. **Red-Black Tree**

It includes JUnit tests and a real-life comparison between them. Documentation on Red-Black Tree theory is also included.

---

## Project Structure

- **Main Classes (`src/main/java`)**  
  - `AVLTree.java` — Implementation of an AVL self-balancing binary search tree.  
  - `NodeAVL.java` — Node class for AVL Tree.  
  - `RedBlackTree.java` — Implementation of a Red-Black Tree with insertion, deletion, and rebalancing.  
  - `NodeRB.java` — Node class for Red-Black Tree.  
  - `BSTTree.java` — Standard binary search tree implementation.  
  - `Node.java` — Node class for BST.

- **Tests (`src/test/java`)**  
  - `AVLTreeTest.java` — JUnit tests for AVL Tree operations, including stress tests and balance checks.  
  - `RedBlackTreeTest.java` — JUnit tests for Red-Black Tree operations, ensuring Red-Black properties are maintained.  
  - `BSTTreeTest.java` — JUnit tests for standard BST operations and comparisons with other trees.

- **Documentation (`docs/`)**  
  - `redblacktree_explanation.md` — Detailed explanation of Red-Black Trees: how they work, their properties, and comparison to other data structures.  
  - `insertion.md` — Step-by-step explanation of insertion operations in Red-Black Trees.  
  - `deletion.md` — Step-by-step explanation of deletion operations in Red-Black Trees.


---


## Maven Setup

The project is structured as a Maven project. To build and test:

```bash
# Compile the project
mvn compile

# Run tests
mvn test
