package IFNAMEDSCALAITSBUG

import PART1.Message

/**
 * Visitor to count how many files and directories there are from start
 */
case class CounterVisitor() extends Visitor{

  var users = 0
  var domains=0
  override def visit(domain: Domain): Unit = {
    domains+=1
  }

  override def visit(account: Account): Unit = {
    users+=1
  }
}