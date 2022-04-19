# min-triangle-path
Small CLI program that find minimal path through a triangle of numbers

## Overview

This repository contains a command-line program that reads a text-format triangle from standard input and outputs a minimal path to the standard output.
 * A path through the triangle is a sequence of adjacent nodes, one from each row, starting from the top.
 * A minimal path is defined as one whose sum of values in its nodes is no greater than for any other path through the triangle.

### Example
```
$ cat << EOF | java MinTrianglePath 
> 7
> 63
> 385
> 11 2 10 9
> EOF
Minimal path is: 7 + 6 + 3 + 2 = 18
```

