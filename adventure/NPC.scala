package o1.adventure

abstract class NPC(val name: String, initialLocation: Area, world: Adventure) {


  private var location = initialLocation         // gatherer: changes in relation to the old location
  private var isOoC = false                      // flag: can be broken or intact

  /** Returns the coordinates that indicate the robot's current location in the robot world. */
  def currentLocation = this.location


  /** Returns a square that neighbors the robot's current location in the given direction. */
  def neighbors = this.location.neighbors


  /** Causes the robot to malfunction (typically as the result of a collision).
    * A broken robot does not do anything during its turns.
    * @see [[fix]] */
  def destroy() = {
    this.isOoC = true
  }

  /** Determines whether the robot is currently intact or not. A robot is intact
    * unless it has been broken with the `destroy` method and not fixed since. */
  def isFine = !this.isOoC


  /** Relocates the robot within its current world to the square next to the robot's
    * current location, in the given direction. The direction does not necessarily have
    * to be the same one that the robot is originally facing in.
    *
    * This method turns the robot to face in the direction it moves in.
    *
    * Two robots can never be in the same square; neither can a robot and a wall. If the
    * robot's would-be location is not empty, a collision occurs instead and the robot
    * does not change locations (but still turns to face whatever it collided with).
    *
    * If the moving robot collides with a wall, the robot itself breaks. If a moving robot
    * collides with another robot, the other robot breaks and the moving robot stays intact.
    *
    * @return `true` if the robot successfully changed locations, `false` if it
    *         did not (even if it changed facing) */
  def routine

  def move

  override def toString: String = this.name
}

class Guard(val name: String, world: Adventure) {


  private val initialLocation = world.yardEntrance
  var location = initialLocation         // gatherer: changes in relation to the old location
  private var isOoC = false                      // flag: can be broken or intact



  /** Returns a square that neighbors the robot's current location in the given direction. */
  def neighbors = this.location.neighbors



  /** Causes the robot to malfunction (typically as the result of a collision).
    * A broken robot does not do anything during its turns.
    * @see [[fix]] */
  def destroy() = {
    this.isOoC = true
  }

  /** Determines whether the robot is currently intact or not. A robot is intact
    * unless it has been broken with the `destroy` method and not fixed since. */
  def isFine = !this.isOoC


  /** Relocates the robot within its current world to the square next to the robot's
    * current location, in the given direction. The direction does not necessarily have
    * to be the same one that the robot is originally facing in.
    *
    * This method turns the robot to face in the direction it moves in.
    *
    * Two robots can never be in the same square; neither can a robot and a wall. If the
    * robot's would-be location is not empty, a collision occurs instead and the robot
    * does not change locations (but still turns to face whatever it collided with).
    *
    * If the moving robot collides with a wall, the robot itself breaks. If a moving robot
    * collides with another robot, the other robot breaks and the moving robot stays intact.
    *
    * @return `true` if the robot successfully changed locations, `false` if it
    *         did not (even if it changed facing) */
  val routine: Vector[Area] = Vector(world.yardEntrance, world.thickGardenEast, world.frontYard)

  override def toString: String = this.name
}