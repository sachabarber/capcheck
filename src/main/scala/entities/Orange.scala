package entities

/**
  * Apple item, this would have more methos/field long term
  * but having case class allows pattern matching
  */
class Orange (name: String) extends Item {
  val itemName = "orange"
}
