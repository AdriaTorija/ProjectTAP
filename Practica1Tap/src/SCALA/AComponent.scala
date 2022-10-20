package SCALA

import PART_1.Message

trait AComponent {
  def printTree(profunditat: Int): Unit
  def getMail(): List[Message]
  def accept(a : Visitor)
}
