package IFNAMEDSCALAITSBUG

/**
 * Visitor interface
 */
trait Visitor {
  def visit(domain:Domain):Unit

  def visit(account: Account): Unit

}
