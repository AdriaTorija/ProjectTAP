package IFNAMEDSCALAITSBUG

import PART1.Message

/**
 * FoldFilterImplementation
 * @param acc
 * @param op
 * @param expresion
 * @tparam A
 */
case class FoldFilter[A](acc: A, op: (A,Message)=> A, expresion: Account => Boolean) extends Visitor{

  var users:Map[String, A] = Map()

  override def visit(domain: Domain): Unit = {

    //DOES NOTHING
  }

  override def visit(account: Account): Unit = {
      //users= Map()
      if(expresion(account)){
        account.mail.updateMail()
        users += (account.name -> account.getMail().foldLeft(acc)(op))
      }

  }
}
