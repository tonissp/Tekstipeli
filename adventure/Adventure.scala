package o1.adventure


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
val title = "Hitteekkari"

val frontGate = new Area("Front Gate", "You stand in front of the entrance to the estate. The gates are open, however there are armed guards checking each every guest going through.\nYou'll clearly need an invitation to pass.",None)
val northFence = new Area("North Fence", "You are surrounded by trees. The only thing that stands out is a drainpipe leading down from the roof of the building.",None)
val westFence = new Area("West Fence", "There is nothing but forest here, but when you look towards the fence you notice a small hole in it.\n\nSmall, but big enough for a person to fit through.",None)
val southFence = new Area("South Fence", "You are surrounded by trees, but a small shack amongst them catches your eye.",None)
val yardEntrance = new Area("Front Yard", "On one side stands the main gate, on the other is the entire premise of the building.\nThe area is bustling as guests keep flowing in.",None)
val eastWall = new Area("East Wall of the Building", "There is nothing for you down here. The only thing that stands out is a porch roof that you can't reach from below.",None)
val frontYard = new Area("Front Yard", "The front yard of the building. The main door stand before you and you are surrounded by a beautiful garden.",None)
val thickGardenEast = new Area("Thick Garden", "You are surrounded by thick foliage and feel as though nobody would notice you if you were to sneak around.",None)
val thickGardenWest = new Area("Thick Garden", "You are surrounded by thick foliage and can barely make out a storage shed on the southern end of the yard.",None)
val storageShed = new Area("Storage Shed", "A shed for storing tools. A crowbar and rat poison catch your eyes.\nYou also notice a door on the floor with a ladder behind it.",None)
val shack = new Area("Run Down Shack", "A dilapidated cabin. You see a hole in the floor and a ladder leading down.",None)
val underground1 = new Area("Underground Tunnel", "You see a ladder and a neverending tunnel which has no end in sight.",None)
val underground2 = new Area("Underground Tunnel", "You are standing in a tunnel which streches on and on in two directions. There is a ladder leading up from where you're standing.",None)
val underground3 = new Area("Underground Tunnel", "An underground tunnel with a door on one side and infinite darkness on the other.",Some(("lockpick","forward")))
val westernEnd = new Area("Western End of the Yard", "A simple looking area at the far-western end of the yard.",None)
val backEntrance = new Area("Back Entrance", "A back entrance with delivery men and other workers unloading boxes. There is no end in sight for their workload.",None)
val storageRoom = new Area("Storage Room", "A room with plenty of shelves and boxes. There appears to be a separate corner for refrigerated content.",None)
val kitchen = new Area("Kitchen", "There are chefs noisily shuffling about.",None)
val bar = new Area("Bar", "A counter behind which are two bartenders and dozens of bottles.",None)
val wc = new Area("WC", "A nice looking area with five stalls and sinks.",None)
val toiletRoom = new Area("Toilet Room", "A simple looking stall. Nobody will disturb you here.",None)
val ballroomNorth = new Area("Ballroom", "The end of the ballroom. On one side is a bar, on the other the WC. There are guests all around you.",None)
val ballroomSouth = new Area("Ballroom", "An enormous ballroom which continues on and on. There are guests all around you.",None)
val entrance = new Area("Entrance", "The main entrance of the building. To the west is a guarded door, to the east the ballroom and main party.",None)
val hallway = new Area("Hallway", "A hallway leaging to a staircase, the kitchen, and the main entrance. You best hurry up before anybody notices you.",None)
val stairs = new Area("Staircase", "A cicrular stairway leading up and down.",None)
val wineCellar = new Area("Wine Cellar", "You see wine barrels all around you. Behind some of the barrels appears to be a door.",Some("lockpick","south"))
val guestRoom = new Area("Guestroom", "A fancy looking room with sofas, a fireplace and a painting of some old dude. You don't know who he is supposed to be.",Some("crowbar","east"))
val hallway2nd = new Area("Hallway", "To the west is a toilet and to the east a children's playroom.",None)
val toilet2nd = new Area("Toilet", "A simple looking room with a toilet and sink.",None)
val playroom = new Area("Playroom", "A children's playroom. Presumably for any children visitors may have.",None)
val childroom = new Area("Bedroom", "This bedroom appears to belong to small child. Presumably the target's son. You see a window to the north and two doors.",None)
val bathroom = new Area("Bathroom", "The bathroom has a shower, a bath, a sink and a toilet. Everything in here looks fairly new.",None)
val bedroom = new Area("Bedroom", "The target's bedroom. It has surprisingly simple furnishing and looks quite plain. All in all, an average looking bedroom where nothing stand out.",None)
val workroom = new Area("Workroom", "A nice looking room with basic office tools and a computer.", None)
val pipe = new Area("Pipe", "The pipe you are clinging to is making noises and feels unstable. Down below is the ground, up above is a window leading to the second floor.",Some("crowbar","west"))
val escape = new Area("Somewhere Far, Far Away", "You might be wondering where you are? Nobody knows.\n\nAnd it should remain that way.",None)

frontGate.setNeighbors(Vector( "north" -> northFence, "south" -> southFence, "west" -> yardEntrance, "escape" -> escape))
northFence.setNeighbors(Vector( "east" -> frontGate, "west" -> westernEnd, "south" -> southFence, "escape" -> escape))
westFence.setNeighbors(Vector( "north" -> northFence, "south" -> southFence, "east" -> westernEnd, "escape" -> escape ))
southFence.setNeighbors(Vector( "east" -> frontGate, "west" -> westFence, "inside" -> shack, "escape" -> escape))
yardEntrance.setNeighbors(Vector( "north" -> eastWall, "east" -> frontGate, "south" -> thickGardenEast, "west" -> frontYard))
eastWall.setNeighbors(Vector( "south" -> yardEntrance))
frontYard.setNeighbors(Vector( "north" -> entrance, "east" -> yardEntrance, "southeast" -> thickGardenEast, "southwest" -> thickGardenWest, "west" -> westernEnd ))
thickGardenEast.setNeighbors(Vector( "northeast" -> yardEntrance, "northwest" -> frontYard, "west" -> thickGardenWest))
thickGardenWest.setNeighbors(Vector( "northeast" -> frontYard, "northwest" -> westernEnd, "east" -> thickGardenEast, "inside" -> storageShed))
storageShed.setNeighbors(Vector( "outside" -> thickGardenWest, "down" -> underground2))
shack.setNeighbors(Vector( "outside" -> southFence, "down" -> underground1))
underground1.setNeighbors(Vector( "up" -> shack, "forward" -> underground2))
underground2.setNeighbors(Vector( "up" -> storageShed, "forward" -> underground3, "backward" -> underground3))
underground3.setNeighbors(Vector( "forward" -> wineCellar, "backward" -> underground2))
westernEnd.setNeighbors(Vector( "north" -> backEntrance, "east" -> frontYard, "south" -> thickGardenWest, "west" -> westFence ))
backEntrance.setNeighbors(Vector( "east" -> storageRoom, "south" -> westernEnd))
storageRoom.setNeighbors(Vector( "east" -> kitchen, "west" -> backEntrance))
kitchen.setNeighbors(Vector( "east" -> bar, "south" -> hallway, "west" -> storageRoom))
bar.setNeighbors(Vector( "east" -> ballroomNorth, "west" -> kitchen))
wc.setNeighbors(Vector( "out" -> ballroomNorth, "in" -> toiletRoom))
toiletRoom.setNeighbors(Vector( "out" -> wc))
ballroomNorth.setNeighbors(Vector( "east" -> wc, "south" -> ballroomSouth, "west" -> bar))
ballroomSouth.setNeighbors(Vector( "north" -> eastWall, "east" -> frontGate, "south" -> thickGardenEast, "west" -> entrance))
entrance.setNeighbors(Vector( "east" -> hallway, "south" -> frontYard, "west" -> stairs))
hallway.setNeighbors(Vector( "north" -> kitchen, "east" -> entrance, "west" -> stairs))
stairs.setNeighbors(Vector( "up" -> guestRoom, "down" -> wineCellar, "east" ->hallway))
wineCellar.setNeighbors(Vector( "up" -> stairs, "south" -> underground3))
guestRoom.setNeighbors(Vector( "northwest" -> hallway2nd, "northeast" -> playroom, "east" -> workroom, "down" -> stairs))
hallway2nd.setNeighbors(Vector( "west" -> toilet2nd, "east" -> playroom, "south" -> guestRoom))
toilet2nd.setNeighbors(Vector( "east" -> hallway2nd))
playroom.setNeighbors(Vector( "east" -> childroom, "south" -> guestRoom, "west" -> hallway2nd))
childroom.setNeighbors(Vector( "east" -> bathroom, "west" -> playroom, "down" -> pipe))
bathroom.setNeighbors(Vector( "south" -> bedroom, "west" -> childroom))
bedroom.setNeighbors(Vector( "north" -> bathroom, "east" -> eastWall, "west" -> workroom))
workroom.setNeighbors(Vector( "east" -> bedroom, "west" -> guestRoom))
pipe.setNeighbors(Vector( "up" -> childroom, "down" -> northFence))



storageShed.addItem(new Item("crowbar", "It's a small battery cell. Looks new."))
workroom.addItem(new Item("loaded memory stick", "It's the remote control for your TV.\nWhat it was doing in the forest, you have no idea.\nProblem is, there's no battery."))
  frontGate.addItem(new Item("suit and invitation", "useful"))

val headGuard = new Guard("Head Guard", this)
val target = new Target("Target", this)
val child = new Child("Child", this)

  val guests = new People(Vector(yardEntrance, frontYard, eastWall, entrance, ballroomSouth, ballroomNorth, toiletRoom), "Suit and invitation")



/** The character that the player controls in the game. */
val player = new Player(frontGate)

  def gg = guests.areas.contains(player.location) && !player.has(guests.keyItem)

/** The number of turns that have passed since the start of the game. */
var currentTime = 0
/** The maximum number of turns that this adventure game allows before time runs out. */
val timeLimit = 48


/** Determines if the adventure is complete, that is, if the player has won. */
def isComplete = {this.player.location == this.escape && player.has("battery") && player.has("remote")}

/** Determines whether the player has won, lost, or quit, thereby ending the game. */
def isOver = this.isComplete || this.player.hasQuit || this.currentTime == this.timeLimit

/** Returns a message that is to be displayed to the player at the beginning of the game. */
def welcomeMessage = "You are lost in the woods. Find your way back home.\n\nBetter hurry, 'cause Scalatut elämät is on real soon now. And you can't miss Scalkkarit, right?"


/** Returns a message that is to be displayed to the player at the end of the game. The message
* will be different depending on whether or not the player has completed their quest. */
def goodbyeMessage = {
  if (this.isComplete)
  "Home at last... and phew, just in time! Well done!"
  else if (this.currentTime == this.timeLimit)
  "Oh no! Time's up. Starved of entertainment, you collapse and weep like a child.\nGame over!"
  else // game over due to player quitting
  "Quitter!"
}

  def NPCdesc = if(player.location == headGuard.location) headGuard.toString
  else if(player.location == target.location) target.toString
  else "You don't see anyone who catches your eye."


/** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
* report of what happened, or an error message if the command was unknown. In the latter
* case, no turns elapse. */
def playTurn(command: String) = {
  val action = new Action(command)
  val outcomeReport = action.execute(this.player)
  if (outcomeReport.isDefined) {
  this.currentTime += 1
  headGuard.location = headGuard.routine(currentTime%3)
  target.location = if(currentTime < 10) {
    target.routine1(currentTime%3)}
    else if(currentTime>20) {
    target.routine2(currentTime-10)}
    else if(currentTime>25) {
    target.routine3(currentTime-20)}
    else if(currentTime>30) {
    target.routine4(currentTime-25)}
    else if(currentTime>38) {
    target.routine5(currentTime-30)}
    else bedroom
  child.location = child.routine((currentTime/2)%3)
  }
outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
}



}
