import MinTrianglePath.Tree.Branch

/**
 * meszaros.otto@gmail.com
 */
object MinTrianglePath extends App {
  sealed trait Tree {
    def value: Int
  }

  object Tree {
    def children(tree: Tree): List[Tree] = {
      tree match {
        case Branch(left, _, _, right, _) =>
          List(left, right)
        case _ =>
          Nil
      }
    }

    final case class Branch(left: Tree, leftCost: Int, value: Int, right: Tree, rightCost: Int) extends Tree
    final case class Leaf(value: Int) extends Tree
  }

  def traverseTreeBreadthFirst[T](tree: T)(getChildren: T => List[T]): LazyList[T] = {
    import scala.collection.immutable.Queue

    def traverseQueue(closed: Queue[T], opened: Queue[T], min: Int): LazyList[T] = {
      if (closed.isEmpty)
        LazyList.empty
      else
        closed.dequeue match {
          case (node, tail) =>

            val updatedNode: T = node match {

              case current: Tree.Branch => // update the cost of the left and right branch
                val leftChild = if(current.value + current.leftCost < current.left.value)
                  current.left match {
                    case t: Tree.Leaf => t.copy(value = current.value + current.leftCost)
                    case t: Tree.Branch => t.copy(value = current.value + current.leftCost)
                  } else current.left
                val rightChild = if(current.value + current.rightCost < current.right.value)
                  current.right match {
                    case t: Tree.Leaf => t.copy(value = current.value + current.rightCost)
                    case t: Tree.Branch => t.copy(value = current.value + current.rightCost)
                  } else current.right
                current.copy(left = leftChild, right = rightChild).asInstanceOf[T]

              case current: Tree.Leaf => current.asInstanceOf[T] // cost unchanged
            }

            node #:: traverseQueue(tail, opened ++ List(updatedNode), min: Int)
        }
    }

    tree #:: traverseQueue(Queue.empty.enqueue(tree),Queue.empty,0)
  }

  def traverseTreeDepthFirst[T](t: T)(getChildren: T => List[T]): LazyList[T] = {
    def recurse(remainingNodes: List[T]): LazyList[T] = remainingNodes match {
      case Nil =>
        LazyList.empty
      case h :: t =>
        h #:: recurse(getChildren(h) ++ t)
    }
    recurse(List(t))
  }
}