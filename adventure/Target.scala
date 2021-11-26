package o1.adventure

import scala.collection.mutable.Map

class Target(val name: String, world: Adventure, item: Item) {

    /** Initial value definitions for the class. */

  private val initialLocation = world.bedroom
  private val targetPossessions = Map[String, Item]()
  private var isOoC = false                      // flag: can be broken or intact
  private var publicDeathh = false
  var playerInSameSquare = false
  var tPrevLoc = initialLocation
  var location = initialLocation         // gatherer: changes in relation to the old location

  /**Returns the items held by the NPC.  */
  def targetItems = targetPossessions

  /** Returns a square that neighbors the robot's current location in the given direction. */
  def neighbors = this.location.neighbors



  /** Causes the robot to malfunction (typically as the result of a collision).
    * A broken robot does not do anything during its turns.
    * @see [[fix]] */
  def killT() = {
    this.location.addItem(item)
    this.isOoC = true
  }

  /** If the target is poisoned this function will be activated. */
  def publicDeath() = {
    world.score += 800
    this.publicDeathh = true
    this.isOoC = true
  }


  /** Determines whether the robot is currently intact or not. A robot is intact
    * unless it has been broken with the `destroy` method and not fixed since. */
  def isFine = !this.isOoC
  def isPublic = this.publicDeathh

  /** These lines define the possible routes the target walks along. */

  val routine1 = Vector(world.bedroom, world.study, world.bedroom)
  val routine2 = Vector(world.study, world.guestRoom, world.stairs, world.wineCellar, world.wineCellar, world.stairs, world.hallway, world.entrance, world.ballroomSouth, world.ballroomNorth, world.ballroomSouth, world.ballroomNorth)
  val routine3 = Vector(world.wc, world.toiletRoom, world.toiletRoom, world.toiletRoom, world.wc)
  val routine4 = Vector(world.ballroomSouth, world.ballroomNorth, world.ballroomSouth, world. entrance, world.hallway, world.stairs, world.guestRoom, world.study)


  /** Makes the target pick up something. */
  def targetGet(itemName: String) = {
    val received = this.location.removeItem(itemName)
    for (newItem <- received) {
      this.targetPossessions.put(newItem.name, newItem)
    }
  }

  /** Returns the message displayed when in the same area as the target. */
  def sameSpot = if(!this.world.guests.areas.contains(this.location) && this.isFine) {
  playerInSameSquare = true
  "'What the hell are you doing here!'"
  } else if(!this.isFine) "There's your target laying dead. Good job. Now you just need to get out of here." else "You see your target. All that you need now is a moment alone with him."

  override def toString: String = sameSpot

}
