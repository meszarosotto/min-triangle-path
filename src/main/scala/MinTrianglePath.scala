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

    def expand(tree: Tree): List[Tree] = {
      tree match {
        case t @ Branch(left, leftCost, value, right, rightCost) =>
          val leftVal = if(value + leftCost < left.value)
            value + leftCost
          else
            left.value
          val rightVal = if(value + rightCost < right.value)
            value + rightCost
          else
            right.value

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

    def traverseQueue(cost: Int, queue: Queue[T]): LazyList[T] = {
      if (queue.isEmpty)
        LazyList.empty
      else
        queue.dequeue match {
          case (node, tail) =>

            node #:: traverseQueue(0,tail ++ getChildren(node))
        }
    }

    tree #:: traverseQueue(7,Queue.empty ++ getChildren(tree))
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