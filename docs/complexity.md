## Complexity

One of the main advantages of red-black trees is that they are "almost" balanced, resulting in lower cost for basic operations.

Let $bh(x)$ be the black height of the tree at node $x$, meaning the number of black nodes on the path from $x$ to its leaves. The black height of a red-black tree is the black height of its root.

First, let's prove this property: **The subtree rooted at any node $x$ contains at least $2^{bh(x)} - 1$ internal nodes.** We'll do so by induction on the height of $x$.

#### Base Case

If $x$ has height $0$, then $x$ must be a null node, so $x$ doesn't have any internal nodes.  
On the other hand, $2^{bh(x)} - 1 = 2^0 - 1 = 1 - 1 = 0$.

#### Induction Hypothesis

Assume that any subtree of height less than $k - 1$ has at least $2^{bh(x)} - 1$ internal nodes.  
Let $x$ be the root of a red-black tree of height $k$. $x$ has two children, $u$ and $v$.  
We know with the induction hypothesis that the number of internal nodes in $u$ is at least $2^{bh(u)} - 1$, and in $v$ is at least $2^{bh(v)} - 1$.  
The black height of $u$ and $v$ is either $bh(x)$ or $bh(x)-1$, depending on whether $u$ and $v$ are black (first case) or red (second case).

Therefore, $u$ and $v$ have at least $2^{bh(x)-1} - 1$ nodes each.  
The number of internal nodes in the subtree rooted at $x$ is the sum of the nodes in the subtrees rooted at $u$ and $v$, plus one for the node $x$ itself:

$$
2^{bh(x)-1} - 1 + 2^{bh(x)-1} - 1 + 1 = 2 \cdot 2^{bh(x)-1} - 1 = 2^{bh(x)} - 1
$$

By induction, we get our result.

---

Let $n$ be the number of internal nodes of a tree and $h$ its height.  
As we can't have red nodes linked to red nodes, the tree has at least $h/2$ black nodes on each path, which means the black height of the tree is at least $h/2$.  
By the property above, we know that $2^{bh(x)} - 1 \le n$, so  

$$
2^{h/2} - 1 \le n \quad \Rightarrow \quad h \le 2 \log(n + 1)
$$

Therefore, the height of the tree is bounded above logarithmically by the number of nodes.

---

This implies that **search complexity** is $O(\log n)$ because search requires traversing a path from the root to a leaf, which is at most the height of the tree (because our tree is still a binary search tree).  
For **insertion**, we need $O(\log n)$ to reach the position to insert, and at most $O(\log n)$ to rebalance the tree while moving up to the root (while the rotations have a constant cost).  
For **deletion**, it is the same as insertion, so it is also $O(\log n)$.
