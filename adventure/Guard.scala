package o1.adventure

class Guard(val name: String, world: Adventure, item: Item) {


  private val initialLocation = world.yardEntrance
  var location = initialLocation         // gatherer: changes in relation to the old location
  private var isOoC = false                      // flag: can be broken or intact



  /** Returns a square that neighbors the NPC's current location in the given direction. */
  def neighbors = this.location.neighbors



  /** Used to incapacitate the guard */
  def incapacitateG() = {
    this.location.addItem(item)
    this.isOoC = true
  }

  /** Determines whether the guard is currently incapacitated or not.*/
  def isFine = !this.isOoC

  /** Used to define the walking pattern of the guard */
  
  val routine: Vector[Area] = Vector(world.yardEntrance, world.thickGardenEast, world.thickGardenWest, world.frontYard)


  override def toString: String = {
    if(this.isFine) "There is a guard walking around. Based on his appearance he's most likely the head guard."
    else "The guard lays unconscious on the floor."
  }
}
