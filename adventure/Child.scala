package o1.adventure

class Child (val name: String, world: Adventure) {


  private val initialLocation = world.childroom
  var location = initialLocation         // gatherer: changes in relation to the old location



  /** Returns a square that neighbors the robot's current location in the given direction. */
  def neighbors = this.location.neighbors


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
  val routine: Vector[Area] = Vector(world.childroom, world.playroom)


  override def toString: String = "You see a small child. Presumably the target's. Getting caught by him is quite troublesome."

}

class People (val areas: Vector[Area], val keyItem: String)