A ScapeGoat tree is a self-balancing Binary Search Tree. The amortized
complexity per INSERT or DELETE is O(log n) while the worst-case
cost of a SEARCH is O(log n).The algorithm works like each node in
the tree contains only a key value and pointers to its two children left
and right. Associated with the root of the whole tree are the only two
extra values needed by the scapegoat scheme: the number of nodes in
the whole tree as in our project denoted by NS, and the maximum
number of nodes in the tree since the tree was last completely rebuilt
denoted by M. In a scapegoat tree a typical rebalancing operation
begins at a leaf, and successively examines higher ancestors until a
node (the “scapegoat”) is found that is so unbalanced that the entire
subtree rooted at the scapegoat can be rebuilt at zero cost, in an
amortized sense. Use of parent is done for simplicity of implementation
and can be avoided. The balancing idea is to make sure that nodes are α
size balanced. Α size balanced means sizes of left and right subtrees are
at most α * (Size of node). The idea is based on the fact that if a node is
Α weight balanced, then it is also height balanced: height &lt;=
log 1/&amp;aplpha; (size) + 1. we simply convert the subtree to the most possible
balanced BST.
 We first store inorder traversal of BST in an array, then
we build a new BST from array by recursively dividing it into two
halves so that rebuilding can be done.
