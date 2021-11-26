  package o1.adventure

  import o1.adventure.ui.AdventureTextUI.game

  import scala.language.postfixOps


  /** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of "hard-coded" information which pertain to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure {

  /** The title of the adventure game. */
  val title = "HitTeekkari"

    /**  These commands define all the areas the player can visit. */

  val car = new Area("Car", "Your very own nice and comfortable car, filled with many useful tools.", Vector())
  val frontGate = new Area("Front Gate", "You stand in front of the entrance to the estate. The gates are open, however there are armed guards checking each every guest going through.\nYou'll clearly need an invitation to pass.", Vector(Some("suit and invitation", "west")))
  val northFence = new Area("North Fence", "You are surrounded by trees. The only thing that stands out is a drainpipe.\nIt looks like you could use it to climb up the wall of the building.",Vector())
  val westFence = new Area("West Fence", "There is nothing but forest here, but when you look towards the fence you notice a small hole in it.\n\nSmall, but big enough for a person to fit through.",Vector())
  val southFence = new Area("South Fence", "You are surrounded by trees, but a small shack amongst them catches your eye.",Vector())
  val yardEntrance = new Area("Front Yard", "On one side stands the main gate, on the other is the entire premise of the building.\nThe area is bustling as guests keep flowing in.",Vector())
  val eastWall = new Area("Eastern Wall of the Building", "There is nothing for you down here. The target's bedroom is directly above, but that doesn't help you at all.",Vector())
  val frontYard = new Area("Front Yard", "The front yard of the building. The main door stands before you and you are surrounded by a beautiful garden.",Vector())
  val thickGardenEast = new Area("Thick Garden", "You are surrounded by thick foliage and feel as though nobody would notice you here as you sneak around.", Vector(Some("suit and invitation", "north")))
  val thickGardenWest = new Area("Thick Garden", "You are surrounded by thick foliage and can barely make out a storage shed on the southern end of the yard.\nYou feel as though nobody will notice you here as you sneak around.", Vector(Some("suit and invitation", "northeast")))
  val storageShed = new Area("Storage Shed", "A shed for storing tools. A crowbar and rat poison catch your eyes.\nYou also notice a trapdoor on the floor with a ladder behind it.",Vector())
  val shack = new Area("Run down Shack", "A dilapidated cabin. You see a hole in the floor and a ladder leading down.",Vector())
  val underground1 = new Area("Underground Tunnel", "You see a ladder and a neverending tunnel which has no end in sight.",Vector())
  val underground2 = new Area("Underground Tunnel", "You are standing in a tunnel which streches on and on in two directions. There is a ladder leading up from where you're standing.",Vector())
  val underground3 = new Area("Underground Tunnel", "An underground tunnel with a locked door on one side and infinite darkness on the other.", Vector(Some("fancy key","forward")))
  val westernEnd = new Area("Western End of the Yard", "A simple looking area at the far-western end of the yard. Luckily there is nobody nearby.", Vector(Some("suit and invitation", "east")))
  val backEntrance = new Area("Back Entrance", "A back entrance with delivery men and other workers unloading boxes. There is no end in sight for their workload.", Vector(Some("chef's outfit", "east")))
  val storageRoom = new Area("Storage Room", "A room with plenty of shelves and boxes. There appears to be a separate corner for refrigerated content.",Vector())
  val kitchen = new Area("Kitchen", "There are chefs noisily shuffling about.",Vector())
  val bar = new Area("Bar", "A counter behind which are two bartenders and dozens of bottles.", Vector())
  val wc = new Area("WC", "A nice looking area with five stalls and sinks.", Vector())
  val toiletRoom = new Area("Toilet Room", "A simple looking stall. Nobody will disturb you here.",Vector())
  val ballroomNorth = new Area("Ballroom", "The end of the ballroom. On one side is a bar, on the other the WC. There are guests all around you.",Vector())
  val ballroomSouth = new Area("Ballroom", "An enormous ballroom which continues on and on. There are guests all around you.",Vector())
  val entrance = new Area("Entrance", "The main entrance of the building. To the west is a locked door, to the east the ballroom and main party.", Vector(Some("guard key", "west")))
  val hallway = new Area("Hallway", "A hallway leading to a staircase, the kitchen, and the main entrance. You best hurry up or hide yourself before anybody notices you.\nAlthough you can hear some chefs talking about something interesting in the next room over.", Vector(Some("suit and invitation", "east"),Some("chef's outfit","north")))
  val stairs = new Area("Staircase", "A cicrular stairway leading up and down.",Vector())
  val wineCellar = new Area("Wine Cellar", "You see wine barrels all around you. Behind some of the barrels appears to be a door.",Vector(Some("fancy key","south")))
  val guestRoom = new Area("Guestroom", "A fancy looking room with couches, a fireplace and a painting of some old dude. You don't know who he is supposed to be.\n There is a staircase leading down.",Vector(Some("crowbar","east")))
  val hallway2nd = new Area("Hallway", "To the west is a toilet and to the east a children's playroom.",Vector())
  val toilet2nd = new Area("Toilet", "A simple looking room with a toilet and a sink.",Vector())
  val playroom = new Area("Playroom", "A children's playroom. Presumably for any children visitors may have.",Vector())
  val childroom = new Area("Bedroom", "This bedroom appears to belong to a small child. Presumably the target's son. You see two doors in opposing directions and a pipe next to the window leading down.\n\nYou tremble at the thought of using that pipe.",Vector())
  val bathroom = new Area("Bathroom", "The bathroom has a shower, a bath, a sink and a toilet. Everything in here looks fairly new.",Vector())
  val bedroom = new Area("Bedroom", "The target's bedroom. It has surprisingly simple furnishing and looks quite plain. All in all, an average looking bedroom where nothing really stands out.",Vector())
  val study = new Area("Study", "A nice looking room with basic office tools and a computer.", Vector())
  val pipe = new Area("Pipe", "The pipe you are clinging to is making noises and feels unstable. Down below is the ground, up above is a window leading to the second floor. \n Not a good idea going into the spy business when you're scared of heights hugh?", Vector())

    /** These commands define the relations between the various areas */

  car.setNeighbors(Vector( "out" -> frontGate))
  frontGate.setNeighbors(Vector( "north" -> northFence, "south" -> southFence, "west" -> yardEntrance, "to the car" -> car))
  northFence.setNeighbors(Vector( "east" -> frontGate, "west" -> westFence, "up" -> pipe))
  westFence.setNeighbors(Vector( "north" -> northFence, "south" -> southFence, "east" -> westernEnd))
  southFence.setNeighbors(Vector( "east" -> frontGate, "west" -> westFence, "inside" -> shack))
  yardEntrance.setNeighbors(Vector( "north" -> eastWall, "east" -> frontGate, "south" -> thickGardenEast, "west" -> frontYard))
  eastWall.setNeighbors(Vector( "south" -> yardEntrance))
  frontYard.setNeighbors(Vector( "north" -> entrance, "east" -> yardEntrance, "southeast" -> thickGardenEast, "southwest" -> thickGardenWest, "west" -> westernEnd ))
  thickGardenEast.setNeighbors(Vector( "north" -> yardEntrance, "west" -> thickGardenWest))
  thickGardenWest.setNeighbors(Vector( "northeast" -> frontYard, "northwest" -> westernEnd, "east" -> thickGardenEast, "inside" -> storageShed))
  storageShed.setNeighbors(Vector( "outside" -> thickGardenWest, "down" -> underground2))
  shack.setNeighbors(Vector( "outside" -> southFence, "down" -> underground1))
  underground1.setNeighbors(Vector( "up" -> shack, "forward" -> underground2))
  underground2.setNeighbors(Vector( "up" -> storageShed, "forward" -> underground3, "backward" -> underground1))
  underground3.setNeighbors(Vector( "forward" -> wineCellar, "backward" -> underground2))
  westernEnd.setNeighbors(Vector( "north" -> backEntrance, "east" -> frontYard, "south" -> thickGardenWest, "west" -> westFence ))
  backEntrance.setNeighbors(Vector( "east" -> storageRoom, "south" -> westernEnd))
  storageRoom.setNeighbors(Vector( "east" -> kitchen, "west" -> backEntrance))
  kitchen.setNeighbors(Vector("south" -> hallway, "west" -> storageRoom))
  bar.setNeighbors(Vector( "east" -> ballroomNorth))
  wc.setNeighbors(Vector( "outside" -> ballroomNorth, "inside" -> toiletRoom))
  toiletRoom.setNeighbors(Vector( "outside" -> wc))
  ballroomNorth.setNeighbors(Vector( "east" -> wc, "south" -> ballroomSouth, "west" -> bar))
  ballroomSouth.setNeighbors(Vector( "north" -> ballroomNorth, "west" -> entrance))
  entrance.setNeighbors(Vector( "east" -> ballroomSouth, "south" -> frontYard, "west" -> hallway))
  hallway.setNeighbors(Vector( "north" -> kitchen, "east" -> entrance, "west" -> stairs))
  stairs.setNeighbors(Vector( "up" -> guestRoom, "down" -> wineCellar, "east" ->hallway))
  wineCellar.setNeighbors(Vector( "up" -> stairs, "south" -> underground3))
  guestRoom.setNeighbors(Vector( "northwest" -> hallway2nd, "northeast" -> playroom, "east" -> study, "down" -> stairs))
  hallway2nd.setNeighbors(Vector( "west" -> toilet2nd, "east" -> playroom, "south" -> guestRoom))
  toilet2nd.setNeighbors(Vector( "east" -> hallway2nd))
  playroom.setNeighbors(Vector( "east" -> childroom, "south" -> guestRoom, "west" -> hallway2nd))
  childroom.setNeighbors(Vector( "east" -> bathroom, "west" -> playroom, "down"->pipe))
  bathroom.setNeighbors(Vector( "south" -> bedroom, "west" -> childroom))
  bedroom.setNeighbors(Vector( "north" -> bathroom, "west" -> study))
  study.setNeighbors(Vector( "east" -> bedroom, "west" -> guestRoom))
  pipe.setNeighbors(Vector( "up" -> childroom, "down" -> northFence))

    /** These lines add all the items to their various locations */

  car.addItem(new Item("suit and invitation", "A useful set which will let you blend in and sneak into the party."))
  car.addItem(new Item("chef's outfit", "Sometimes you just got to work behind the scenes."))
  car.addItem(new Item("memory stick", "An empty memory stick. It might be useful."))
  car.addItem(new Item("lockpick", "A lockpick which would be quite useful in the right situation."))
  storageShed.addItem(new Item("poison", "Quite a large amount of rat poison. Enough to kill a man."))
  storageShed.addItem(new Item("crowbar", "It's a small battery cell. Looks new."))
  wineCellar.addItem(new Item("wine glass", "useful"))
  hallway.addEavesdroppable(new Eavesroppable("chefs", "'No, no, no. You don't have to pour any wine for the master. He's got his own favourite wine down in the cellar.\nIt's the only thing he ever drinks.\n\nAnd don't even think about touching it. He'll cut off your hands if you do.'"))
  ballroomNorth.addEavesdroppable(new Eavesroppable("guests", "'Apparently the owner is going to come down here somewhere between 9 and 10PM.'"))
  wineCellar.addExaminable(new Examinable("barrel", "An expensive looking barrel of wine. This is not the kind of thing a good man can afford."))

    /** These lines define the NPC:s the player may encounter. */

  val headGuard = new Guard("Head Guard", this, new Item("guard key", "A key carried by the head guard, you don't know what door it is for."))
  val target = new Target("Target", this, new Item("fancy key", "An engraved key. What could this be for?"))
  val child = new Child("Child", this)
  val guests = new People(Vector(yardEntrance, frontYard, eastWall, entrance, ballroomSouth, ballroomNorth, wc), "suit and invitation")

    /** This line signals to the program wether or not the player has crossed paths with the target of the child.
      * That is if one of these NPC:s moves to the square the player inhabited on the last turn,
      * and the player moves to the square the NPC inhabited on the last turn. */
  var crossPath = false



  /** The character that the player controls in the game. */
    val player = new Player(car, this)

    /** Determines if the child has seen the player. This will lose the game for the player. */
    def busted = (child.location == player.location)

    /** Returns the direction to an NPC that is currently in a neighbouring area in realtion to the player. */
    def neighboringDirection(playerLoc: Area, npcLoc: Area) = playerLoc.neighbors.filter(_._2 == npcLoc).keys.toVector(0)

    /** Returns the areas that are directly connected to the one the player is in. */
    def playerNeighbors = player.location.neighboringAreas

  /** The number of turns that have passed since the start of the game. */
    var currentTime = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
    val timeLimit = 49

    def currenTime = s"The current time is ${19+this.currentTime/12}:" + "%02d".format((this.currentTime*5)%60) + "\n"


  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = (this.player.location == this.car && !target.isFine) || (this.player.location == this.car && this.player.has("loaded memory stick"))

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.currentTime == this.timeLimit || busted

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "Hello agent Teekkari. You are our most skilled assassin. Sad, I know. I wouldn't rely on you if things were better in our agency, but alas." +
    "\n\nAnyways, I digress. The situation is simple: You have a target and your mission is to eliminate him. Your deadline is 23:00. Get to work." +
    "\n\nAvailable commands: go (direction), wait, quit, inventory, get (item), drop (item), examine (item), incapacitate, eliminate, drown, overhear(people), inspect(something), poison, download, lockpick" +
    "\nFeel free to use any of the tools in the car, but try to play through the game multiple times and find different methods to complete your mission." +
    "\nIt is 100% possible to beat the game without using any of the starting items, but there are certain approaches which are only available to you with these items." +
    "\nTry different approaches to get a highscore."

  var score = 0

  /** Returns a message that is to be displayed to the player at the end of the game. The message
  * will be different depending on whether or not the player has completed their quest. */

  def goodbyeMessage = {
    if (this.isComplete)
    s"Good job. You have successfully completed your mission.\n\nYour score: $score points."
    else if (this.currentTime == this.timeLimit)
    "Time's up. You failed to kill your target tonight."
    else if (target.playerInSameSquare && target.isFine)
    "You were caught by your target. You should have killed him as soon as he noticed you."
    else if (busted)
    "You were caught! You might be an assassin, but it's not like you can just kill a child to silence them."
    else if(crossPath)
    "You crossed path with someone you shouldn't have in a place where you shouldn't be. Better luck next time."
    else
    "Quitter!"
  }

    /** Returns a description of an NPC near the player. If none are present in the player's area or neighboring ones, returns "Nobody nearby catches your eye.".  */

  def NPCdesc = if(player.location == headGuard.location) headGuard.toString
  else if(player.location == target.location) target.toString
  else if(player.location == child.location) child.toString
  else if(player.location.neighboringAreas.contains(target.location)) s"You see somebody interesting when looking ${neighboringDirection(player.location, target.location)}"
  else if(player.location.neighboringAreas.contains(child.location)) s"You see somebody interesting when looking ${neighboringDirection(player.location, child.location)}"
  else if(player.location.neighboringAreas.contains(headGuard.location)) s"You see somebody interesting when looking ${neighboringDirection(player.location, headGuard.location)}"
  else "Nobody nearby catches your eye."



  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
  * report of what happened, or an error message if the command was unknown. In the latter
  * case, no turns elapse. */

  def playTurn(command: String) = {
    player.pPrevLoc = player.location
    target.tPrevLoc = target.location
    child.cPrevLoc = child.location
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    if (outcomeReport.isDefined) {
      this.currentTime += 1
      if(headGuard.isFine) headGuard.location = headGuard.routine(currentTime%4)
      if(target.isFine) {target.location = if(currentTime < 15) {
        target.routine1(currentTime%3)}
        else if(currentTime<27) {
        target.routine2(currentTime-15)}
        else if(currentTime<32) {
        target.routine3(currentTime-27)}
        else if(currentTime<40) {
        target.routine4(currentTime-32)}
        else bedroom }
      if(target.isFine) {
        if(target.location == this.wineCellar && wineCellar.contains("poisoned wine")) target.targetGet("poisoned wine")
        else if(target.location == this.wineCellar && wineCellar.contains("wine glass")) target.targetGet("wine glass") }
      if(currentTime == 26 && target.targetItems.contains("poisoned wine")) {
        target.publicDeath() }
      child.location = child.routine((currentTime/2)%2)
    }
  if ((target.tPrevLoc == player.location && player.pPrevLoc == target.location && !guests.areas.contains(target.location)) || (child.cPrevLoc == player.location && player.pPrevLoc == child.location)) {
    crossPath = true
    player.quit()
  }
  score -= 10
  outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }

}
