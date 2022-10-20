package IFNAMEDSCALAITSBUG

import PART1.Message

/**
 * Filter that returns all messages that follows the specified condition
 * @param b
 */
case class FilterVisitor(b : Message => Boolean) extends Visitor{

  var messages: List[Message] = Nil
  override def visit(domain: Domain): Unit = {

          //DOES NOTHING
  }

  override def visit(account: Account): Unit = {
     messages=messages.concat(account.getMail().filter(b))
  }
}
