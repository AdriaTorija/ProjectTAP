package IFNAMEDSCALAITSBUG

import PART1.Message

/**
 * Composite interface
 */

trait AComponent {
  def printTree(profunditat: Int): Unit
  def getMail(): List[Message]
  def accept(a : Visitor)
}
