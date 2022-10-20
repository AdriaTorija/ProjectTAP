package SCALA

import PART_1.Message

import scala.collection.mutable.ListBuffer


case class Domain(val name:String) extends AComponent {
  private var children: ListBuffer[AComponent] = new ListBuffer[AComponent]()

  def addChild(child: AComponent): Unit = {
    children += child
  }

  def removeChild(child: AComponent): Unit = {
    children -= child
  }

  override def printTree(profunditat: Int) {
    var aux = profunditat
    println("└"+name)
    for (child <- children) {
      aux = profunditat
      while(aux > 0){
        print("\t")
        aux = aux - 1
      }
      child.printTree(profunditat+1)

    }
  }

  override def getMail(): List[Message] = {
    var list:List[Message] = List[Message]()
    for (child <- children) {
      list = list.concat(child.getMail())
    }
    list
  }

  override def accept( a : Visitor): Unit= {
    for (child <- children) {
      child.accept(a)
    }
    a.visit(this)
  }
}