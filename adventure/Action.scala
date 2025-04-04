package o1.adventure


/** The class `Action` represents actions that a player may take in a text adventure game.
  * `Action` objects are constructed on the basis of textual commands and are, in effect,
  * parsers for such commands. An action object is immutable after creation.
  * @param input  a textual in-game command such as "go east" or "rest" */
class Action(input: String) {

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim


  /** Causes the given player to take the action represented by this object, assuming
    * that the command was understood. Returns a description of what happened as a result
    * of the action (such as "You go west."). The description is returned in an `Option`
    * wrapper; if the command was not recognized, `None` is returned. */
  def execute(actor: Player) = this.verb match {
    case "head"        => Some(actor.go(this.modifiers))
    case "wait"      => Some(actor.waits())
    case "inventory" => Some(actor.inventory)
    case "take"       => Some(actor.get(this.modifiers))
    case "drop"      => Some(actor.drop(this.modifiers))
    case "examine"   => Some(actor.examine(this.modifiers))
    case "incapacitate" => Some(actor.incapacitate)
    case "eliminate" => Some(actor.kill)
    case "overhear" => Some(actor.eavesdrop(this.modifiers))
    case "inspect" => Some(actor.inspect(this.modifiers))
    case "poison" => Some(actor.poisonWine)
    case "download" => Some(actor.loadMemory)
    case "lockpick" => Some(actor.pickLock)
    case "quit"      => Some(actor.quit())
    case other       => None
  }
}