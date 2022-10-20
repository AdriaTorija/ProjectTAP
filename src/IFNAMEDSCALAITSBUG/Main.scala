package IFNAMEDSCALAITSBUG

import PART1.Message

import java.util.concurrent.Executors.DelegatedExecutorService

//IMPLEMENTAT FINS EL PUNT 7 DEGUT A PROBLEMES TÈCNICS QUE HAN DERIVAT EN FALTA DE TEMPS PER ACABAR LA PRÀCTICA
//FUN FACT: SI EL PAQUET ES DIU SCALA COMPILA EL PRIMER COP I LA RESTA NO. SI ES CANVIA DE VERSIÓ DE LA SDK D'ESCALA TORNA
// A COMPILAR UN COP MÉS PER DEXAR DE FER-HO FINS CANVIAR-NE ALTRE COP LA VERSIÓ.

/**
 * Main to test all part4 methods
 */
object Main extends scala.App {

  val root = new Domain("")
  val cat = new Domain("cat")
  val urv = new Domain("urv")
  val etse = new Domain("etse")
  val estudiants = new Domain("estudiants")

  val user1 = new Account("user1")
  val user2 = new Account("user2")
  val user3 = new Account("user3")
  val user4 = new Account("user4")

  root.addChild(cat)
  cat.addChild(urv)
  urv.addChild(etse)
  urv.addChild(estudiants)

  cat.addChild(user1)
  urv.addChild(user2)
  etse.addChild(user3)
  estudiants.addChild(user4)

  user1.mail.sendMail("hello", "Hello user2, this is user1!", "user2")
  user1.mail.sendMail("hello", "Hello user1, this is you!", "user1")
  user1.mail.sendMail("greetings", "Regardsgreetings", "user4")
  user1.mail.sendMail("spam", "spam spam", "user3")
  user2.mail.sendMail("spam", "spam spam", "user1")

  root.printTree(0)

  root.getMail().foreach(Message => println(Message))
  println("-----------------------")
  estudiants.getMail().foreach(Message => println(Message))


  println("2nd Phase, Visitor      -----------------------")

  println("FilterVisitor")
  val v = new FilterVisitor(m => !m.getText.contains("spam"))
  root.accept(v)
  println("Filtered: " + v.messages)

  println("CounterVisitor")
  val c = new CounterVisitor()
  root.accept(c)
  val d = new CounterVisitor()
  estudiants.accept(d)

  println("Users: " + c.users + " Domains: " + c.domains)
  println("Users: " + d.users + " Domains: " + d.domains)

  println("FoldFilterVisitor")
  val f = new FoldFilter[Int](0, (acc, m) => acc + m.getText.length,
    account => account.name.contains("user"))
  root.accept(f)
  println("Character count per user: " + f.users)


}
