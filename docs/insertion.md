# Insertion

When we add an element to a Red-Black Tree, we then need to check if the properties of the Red-Black Tree are still satisfied and make changes if needed.

First, let's introduce rotations:

### Rotations

A left rotation is an operation that looks like a rotation of our tree.  
Here is an example:

<div style="display: flex; gap: 20px; align-items: flex-start;">
  <figure style="margin: 0;">
    <img src="img/leftrotate1.jpg" alt="Initial tree" width="250"/>
    <figcaption style="text-align: center;">Initial tree</figcaption>
  </figure>

  <figure style="margin: 0;">
    <img src="img/leftrotate2.jpg" alt="Rotate 90° left" width="250"/>
    <figcaption style="text-align: center;">After 90° left rotation</figcaption>
  </figure>

  <figure style="margin: 0;">
    <img src="img/leftrotate3.jpg" alt="Fix the child" width="250"/>
    <figcaption style="text-align: center;">Final tree after adjustment</figcaption>
  </figure>
</div>

Starting with image 1, we rotate the tree just as if we were rotating our paper sheet 90° to the left.

In image 2, we see that node `10` has three children, so we need to remove one.  
We need to let `12` remain as the right child of `10` because it is greater than 10, and we move the old left child of `10` to become the right child of `5` since it is greater than 5.
After this adjustment, we end up with the tree shown in image 3.

Right rotation is the same process but in the opposite direction: you just need to read the diagrams in reverse order, starting with a 90° rotation to the right.

With this example we can write this simplified pseudo code for left rotation :

```text
# Variables

X = node_to_rotate              # `5` in our example
Y = X.right                     # `10` in our example
Z = Y.left                      # `8` in our example   

# Perform rotation

Y takes the place of X
Y.left = X                     
X.right = Z
```

In practice we would need to take care not to lose information while exchanging variables and do everything cleanly.




