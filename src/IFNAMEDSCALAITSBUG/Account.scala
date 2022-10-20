package IFNAMEDSCALAITSBUG

import scala.jdk.CollectionConverters._
import PART1.{MailBox, MailSystem, Message, StoreInMemory}

object MailSys {
  val mailStore = new StoreInMemory()
  val mailSystem = new MailSystem(mailStore)
}

/**
 * Class for diferent Files
 * @param name
 */
class Account(val name:String) extends AComponent{

  var mail:MailBox= MailSys.mailSystem.createNewUser(name,"asggf",3245)

  override def printTree(profunditat :Int): Unit = print("└@"+name+"\n")

  override def getMail(): List[Message] = {
    mail.updateMail()
    mail.getMessages.asScala.toList
  }

   override def accept(a : Visitor): Unit = {
      a.visit(this)
  }
}
