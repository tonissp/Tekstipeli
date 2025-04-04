package o1.adventure
/** The class `Item` represents items in a text adventure game. Each item has a name
  * and a  *  longer description. (In later versions of the adventure game, items may
  * have other features as well.)
  *
  * N.B. It is assumed, but not enforced by this class, that items have unique names.
  * That is, no two items in a game world have the same name.
  *
  * @param name         the item's name
  * @param description  the item's description */
class Item(val name: String, val description: String) {
  /** Returns a short textual representation of the item (its name, that is). */
  override def toString = this.name
}
  /** Defines an object that can be examined but not picked up. */
class Examinable(val name: String, val description: String) {
  override def toString: String = this.name
}
  /** Defines an object you can listen in on. */
class Eavesroppable(val targets: String, val speech: String) {
  override def toString: String = this.targets
}