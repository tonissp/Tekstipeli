package o1.adventure

class Target(val name: String, world: Adventure) {


  private val initialLocation = world.bedroom
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


  /**val routine: Vector[Area] = {
    if(world.currentTime < 10) {
    Vector(world.bedroom, world.workroom, world.bedroom)}
    else if(world.currentTime>20) {
    Vector(world.workroom, world.guestRoom, world.stairs, world.wineCellar, world.stairs, world.hallway, world.entrance, world.ballroomSouth, world.ballroomNorth, world.ballroomSouth)}
    else if(world.currentTime>25) {
    Vector(world.ballroomNorth)}
    else if(world.currentTime>30) {
    Vector(world.wc, world.toiletRoom, world.toiletRoom, world.toiletRoom, world.wc)}
    else if(world.currentTime>38) {
    Vector(world.ballroomSouth, world.ballroomNorth, world.ballroomSouth, world. entrance, world.hallway, world.stairs, world.guestRoom, world.workroom)}
    else Vector(world.bedroom, world.workroom, world.bedroom)
  }*/

  val routine1 = Vector(world.bedroom, world.workroom, world.bedroom)
  val routine2 = Vector(world.workroom, world.guestRoom, world.stairs, world.wineCellar, world.stairs, world.hallway, world.entrance, world.ballroomSouth, world.ballroomNorth, world.ballroomSouth)
  val routine3 = Vector(world.ballroomNorth)
  val routine4 = Vector(world.wc, world.toiletRoom, world.toiletRoom, world.toiletRoom, world.wc)
  val routine5 = Vector(world.ballroomSouth, world.ballroomNorth, world.ballroomSouth, world. entrance, world.hallway, world.stairs, world.guestRoom, world.workroom)



  override def toString: String = "You see your target. All that you need now is a moment alone with him."

}
