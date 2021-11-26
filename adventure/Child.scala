package o1.adventure

class Child (val name: String, world: Adventure) {


  private val initialLocation = world.childroom
  var location = initialLocation         // gatherer: changes in relation to the old location
  var cPrevLoc = initialLocation



  /** Returns a square that neighbors the NPC's current location in the given direction. */
  def neighbors = this.location.neighbors

  val routine: Vector[Area] = Vector(world.childroom, world.playroom)


  override def toString: String = "You see a small child. Presumably the target's. Getting caught by him is quite troublesome."

}

/** Defines the non-interactable NPC:s to the game. */

class People (val areas: Vector[Area], val keyItem: String)
