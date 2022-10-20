package SCALA

import PART_1.Message


case class FilterVisitor(b : Message => Boolean) extends Visitor{

  var messages: List[Message] = Nil
  override def visit(domain: Domain): Unit = {

          //DOES NOTHING
  }

  override def visit(account: Account): Unit = {
     messages=messages.concat(account.getMail().filter(b))
  }
}
