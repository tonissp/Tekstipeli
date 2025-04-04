package o1.adventure
import scala.collection.mutable.Map
/** A `Player` object represents a player character controlled by the real-life user of the program.
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(startingArea: Area, adventure: Adventure) {
  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private val possessions = Map[String, Item]()
  // container of all the items that the player has
  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  /** Returns the current location of the player. */
  def location = this.currentLocation

  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player's current location towards the direction name and
    * the player has the possibly required items to go in that direction. Returns
    * a description of the result:
    * "You go DIRECTION (using the ITEM)." or "You can't go DIRECTION (without a ITEM)." */
  def go(direction: String):String = {
    val reqsUsed = this.location.requirements.find(_.getOrElse("", "")._2 == direction).getOrElse(Some("","")).get
    val msg = if(reqsUsed._1 != ""&&this.has(reqsUsed._1)) " using the "+ reqsUsed._1 else if(reqsUsed._1 != ""&&(!this.has(reqsUsed._1))) " without a "+ reqsUsed._1 else ""
    val destination = if(reqsUsed==("","")||(reqsUsed._2 ==direction&&this.has(reqsUsed._1)))this.location.neighbor(direction) else None
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if (destination.isDefined) "You go " + direction +msg+ "." else "You can't go " + direction +msg+ "." }

    var pPrevLoc = startingArea

  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def waits() = {
    adventure.score += 10
    "You wait and observe your surroundings."
  }
  /** Signals that the player wants to quit the game. Returns a description of what happened
    * within the game as a result (which is the empty string, in this case). */
  def quit() = {
    this.quitCommandGiven = true
    ""
  }
  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name
  /** Tries to pick up an item of the given name. This is successful if such an item is
    * located in the player's current location. If so, the item is added to the player's
    * inventory. Returns a description of the result: "You pick up the ITEM." or
    * "There is no ITEM here to pick up." */
  def get(itemName: String) = {
    val received = this.location.removeItem(itemName)
    for (newItem <- received) {
      this.possessions.put(newItem.name, newItem)
    }
    adventure.score -= 30
    if (received.isDefined) "You pick up the " + itemName + "." else "There is no " + itemName + " here to pick up."
  }
  /** Determines whether the player is carrying an item of the given name. */
  def has(itemName: String) = this.possessions.contains(itemName)
  /** Tries to drop an item of the given name. This is successful if such an item is
    * currently in the player's possession. If so, the item is removed from the
    * player's inventory and placed in the area. Returns a description of the result
    * of the attempt: "You drop the ITEM." or "You don't have that!". */
  def drop(itemName: String) = {
    val removed = this.possessions.remove(itemName)
    for (oldItem <- removed) {
      this.location.addItem(oldItem)
    }
    if (removed.isDefined) "You drop the " + itemName + "." else "You don't have that!"
  }
  /** Causes the player to examine the item of the given name. This is successful if such
    * an item is currently in the player's possession. Returns a description of the result,
    * which, if the attempt is successful, includes a description of the item. The description
    * has the form: "You look closely at the ITEM.\nDESCRIPTION" or "If you want
    * to examine something, you need to pick it up first." */
  def examine(itemName: String) = {
    def lookText(item: Item) = "You look closely at the " + item.name + ".\n" + item.description
    val failText = "If you want to examine something, you need to pick it up first."
    this.possessions.get(itemName).map(lookText).getOrElse(failText)
  }


  /** Causes the player to list what they are carrying. Returns a listing of the player's
    * possessions or a statement indicating that the player is carrying nothing. The return
    * value has the form "You are carrying:\nITEMS ON SEPARATE LINES" or "You are empty-handed."
    * The items are listed in an arbitrary order. */
  def inventory = {
    if (this.possessions.isEmpty)
      "You are empty-handed."
    else
      "You are carrying:\n" + this.possessions.keys.mkString("\n")
  }
  /** Attempts to pick a lock Successfull if the player has a lockpick
    * and there is a door to lockpick in the players area. */
  def pickLock = if(this.possessions.contains("lockpick") && (this.location == adventure.wineCellar || this.location == adventure.underground3)){
    adventure.score += 50
    adventure.underground3.requirements=Vector(None)
    adventure.wineCellar.requirements=Vector(None)
    this.possessions.remove("lockpick")
    "You have successfully picked the lock and the door is now open. Sadly your lockpick breaks after you're done with it."}
    else if(!this.possessions.contains("lockpick")) {
    "You don't have a lockpick in your inventory." }
    else "You can't use this on anything here."

  /** Attempts to poison the wine. Successfull if the player has poison
    * and there is something to poison in the players area. */
  def poisonWine = {
    if (this.possessions.contains("poison") && this.location.contains("wine glass")) {
      adventure.score += 40
      this.location.removeItem("wine glass")
      this.possessions.remove("poison")
      this.location.addItem(new Item("poisoned wine", "Quite lethal."))
      "You have posioned the wine. Now to wait for someone to drink it."
    }
    else if (this.possessions.contains("poison") && this.possessions.contains("wine glass")) {
      adventure.score += 40
      this.possessions.remove("wine glass")
      this.possessions.remove("poison")
      this.location.addItem(new Item("poisoned wine", "Quite lethal."))
      this.get("poisoned wine")
      "You have posioned the wine. Now to wait for someone to drink it."
    }
    else if (this.possessions.contains("poison") && (this.location == adventure.bar || this.location == adventure.kitchen)) {
      adventure.score -= 300
      this.possessions.remove("poison")
      "You've poisoned a random wine bottle. Hopefully the target will choose to drink it."
    }
    else if(!this.possessions.contains("poison")) {
      "You don't have any poison in your inventory."
    }
    else {
      "There is nothing here for you to poison."
    }
  }
  /** Attempts to download all the useful data from the targets computer.
    * Successful if player is in the study and has a memory stick. */
  def loadMemory = {
    if (this.possessions.contains("memory stick") && this.location == adventure.study) {
      adventure.score += 800
      this.possessions.remove("memory stick")
      this.possessions.put("loaded memory stick", new Item("loaded memory stick", "Useful."))
      "You have found quite a bit of useful data on his computer. With all of this in your posession killing him is now unnecessary."
    }
    else if(!this.possessions.contains("memory stick")) {
      "You don't have a memory stick on you."
    }
    else {
      "There is nothing here for you to use it on."
    }
  }

  /** Attempts to eavesdrop. Successfull if there is something to hear by eavesdropping. */
    def eavesdrop(target: String): String = {
    if(this.location.eavesdroppables.nonEmpty) adventure.score += 10
    def eavesDText(eavesroppable: Eavesroppable) = eavesroppable.speech
    val failText = "There is nothing interesting to listen in on here."
    this.location.eavesdroppables.get(target).map(eavesDText).getOrElse(failText)
  }
  /** Examines an examinable object */
    def inspect(itemName: String): String = {
    if(this.location.examinables.nonEmpty) adventure.score += 10
    def inspectText(examinable: Examinable) = examinable.description
    val failText = "There is nothing interesting to inspect here."
    this.location.examinables.get(itemName).map(inspectText).getOrElse(failText)
  }
  /** Incapacitates the guard */
  def incaP: String = {
    adventure.score -= 100
    adventure.headGuard.incapacitateG()
    "You sneak up on the head guard and choke him out. He seems to drop a key as he falls."
  }
  /** Tries to incapacitate the guard but fails if other people are around */
  def incapacitat: String = if(!this.adventure.guests.areas.contains(this.location)) incaP else "You don't want to do this with all the people around."

  /** Calls the above function if there is someone to incapacitate. */
  def incapacitate: String = if(this.location == adventure.headGuard.location && adventure.headGuard.isFine) incapacitat else "You have no reason to hurt anybody here."


    /** The below functions are layered functions that act in a very similiar  way.
      * Drown is basically kill but in a more stylish way.
      * Drown has the requirements that you are in a bathroom and the target is there with you and he is alive
      * Kill has the requirements that you are alone with the target and he is alive.*/

  def waterRooms = this.location==adventure.toiletRoom || this.location==adventure.toilet2nd || this.location==adventure.bathroom

  def drown: String = {
  adventure.score += 600
  adventure.target.killT()
  "You push the target's head down into the toilet and wait for him to drown. What a way to go!"
  }

  def killlll: String = {
    if(waterRooms) drown else {
    adventure.score += 400
    adventure.target.killT()
    "You walk up the bastard and snap his neck. Nobody will ever hear form him again.\nA fancy looking key falls out of his pocket as he drops dead."
    }
  }

  def killll = if(!this.adventure.guests.areas.contains(this.location)) killlll else "You don't want to do this with all the people around."

  def killl = if(adventure.target.isFine) killll else "Your target is already dead. Get out of here instead of playing around. I know we made a good game and you don't want this to end but just stop. \nI recommend trying another playthrough if you want to kill some more."

  def kill = if(this.location == adventure.target.location) killl else "You don't see your target here."

}