/**
 * meszaros.otto@gmail.com
 */
import MinTrianglePath.{Tree, traverseTreeBreadthFirst}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MinTrianglePathSpec extends AnyFlatSpec with Matchers {

  "Breadth First traversal" should "fetch tree values fetched in breadth-first order" in {
    val sampleTree: Tree = Tree.Branch(
      value = 0,
      left = Tree.Branch(
        value = 1,
        left = Tree.Leaf(2),
        leftCost = 0,
        right = Tree.Leaf(2),
        rightCost = 0),
      leftCost = 6,
      right = Tree.Branch(
        value = 1,
        left = Tree.Leaf(2),
        leftCost = 0,
        right = Tree.Leaf(2),
        rightCost = 0),
      rightCost = 3)
    traverseTreeBreadthFirst(sampleTree)(Tree.children).map(_.value).toList shouldBe List(0, 1, 1, 2, 2, 2, 2)
  }

  "Cost update" should "update the values" in {
    val sampleTree: Tree = Tree.Branch(
      value = 0,
      left = Tree.Branch(
        value = 1,
        left = Tree.Leaf(2),
        leftCost = 0,
        right = Tree.Leaf(2),
        rightCost = 0),
      leftCost = 6,
      right = Tree.Branch(
        value = 1,
        left = Tree.Leaf(2),
        leftCost = 0,
        right = Tree.Leaf(2),
        rightCost = 0),
      rightCost = 3)
    traverseTreeBreadthFirst(sampleTree)(Tree.children).map(_.value).toList shouldBe List(0, 6, 3, 9, 14, 11, 8)
  }
}
