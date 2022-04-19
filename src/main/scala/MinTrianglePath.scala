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

    def traverseQueue(unvisited: Queue[T]): LazyList[T] = {
      if (unvisited.isEmpty)
        LazyList.empty
      else
        unvisited.dequeue match {
          case (node, tail) =>
            node #:: traverseQueue(tail ++ getChildren(node))
        }
    }

    tree #:: traverseQueue(Queue.empty ++ getChildren(tree))
  }
}