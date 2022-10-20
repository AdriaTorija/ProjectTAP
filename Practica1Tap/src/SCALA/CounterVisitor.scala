package SCALA

import PART_1.Message


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