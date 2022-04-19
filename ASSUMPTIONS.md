# Assumptions

* [11:37]
Let's assume the trinagle is a binary tree. In a binary tree every node(parent) has left and right child node, and cannot contain cycles, the edges of the binary tree is not directed.
The Breath First or Depth First search algorithm can be used to find the minimal path in the binary tree.
The shape of the triangle indicates the tree has to be balanced, which mean the level of all the leafs are equal, therefore the  length of minimal path
* [12:19]
The triangle can be converted to a binary tree, by doubling all non-edge nodes of the triangle, and finding minimal path on the binary tree will result to the same output and finding minimal path in the triangle.
* [lunch break] :)
* [13:33]
First of all the Breath First and Depth Fisrt algorithms fundamental concept is to loop over the nodes and do work on the adjacent nodes in a graph recursively. This recursive approach aligns with FP paradigm. However the goal of the search algorithms is to find a particular node where the node fullfills a predicate. We have to find all leaf nodes in the triangle, but here we have to take into account the cost of the path.
* [13:42]
We can further modify the input data structure in a way that the edges has cost. The cost of each edges(both parent-to-left and parent-to-right) should have a value of the parent node. i.e: top node is: 7 it has two childs 6 and 3, this would mean if traverse the tree then we can get to child 6 with cost 7 and we can get to child 3 with cost 7 also.
* [14:23]
See what we have in the internet, search for minimal cost path or shortest path tree search algorithms.
* [14:32]
Found several algorithms for finding the lowest cost path in the graph. So, I decide to transform the data structure in a way that better serve the algorithms, in order to find the minimal cost path(s).
The transformations:
* edge cost changes from 1 to the value of the node where you can get into
* the nodes value equals to the level where they can be found nn the tree (triangle)
* top node considered a constant, means if we found all the lowest cost path it does not change the order if we add the constant to all of it

Assume the data structure has it's is functionally equivalent pair in this problem domain:
```
               0                                              7
               +                                              +
              ┌──┐                                           ┌──┐
              │7 │                                           │0 │
              └──┘                                           └──┘
               / \                                            / \
              1   1                                          6   3
             /     \                                        /     \
           ┌──┐    ┌──┐                                   ┌──┐    ┌──┐
           │6 │    │3 │                                   │1 │    │1 │
           └──┘    └──┘                                   └──┘    └──┘
          /   \    /   \                                 /   \    /   \
         1     1  1     1           ────────►           3     8  8     5
        /       \/       \                             /       \/       \
     ┌──┐      ┌──┐      ┌──┐                       ┌──┐      ┌──┐      ┌──┐
     │3 │      │8 │      │5 │                       │2 │      │2 │      │2 │
     └──┘      └──┘      └──┘                       └──┘      └──┘      └──┘
    /    \    /    \    /    \                     /    \    /    \    /    \
   1      1  1      1  1      1                   11     2  2      8  10     9
  /        \/        \/        \                 /        \/        \/        \
┌──┐      ┌──┐      ┌──┐      ┌──┐             ┌──┐      ┌──┐      ┌──┐      ┌──┐
│11│      │2 │      │10│      │9 │             │3 │      │3 │      │3 │      │3 │
└──┘      └──┘      └──┘      └──┘             └──┘      └──┘      └──┘      └──┘
```


