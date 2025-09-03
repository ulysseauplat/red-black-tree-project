# Insertion

When we add an element to a Red-Black Tree, we then need to check if the properties of the Red-Black Tree are still verified and make changes if needed.

First, let's introduce rotations:

### Left Rotation

We rotate the tree just as if we were rotating our paper sheet 90° to the left.

![Red-Black Tree Example](img/leftrotate1.png)

Then we see that `10` has three children, so we need to fix one. We fix that by taking its left child and putting it as `5`'s right child.

![Red-Black Tree Example](img/leftrotate2.png)

We end up with this tree:

![Red-Black Tree Example](img/leftrotate2.png)

Rotation is the same process but the other way around; you just need to read the diagrams in reverse order, starting with a 90° rotation to the right.
