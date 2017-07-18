package data

/**
  * A real repo/mock could extend this trait
  */
trait ItemRepository {
  def getItemCost(itemName : String) : Int
}

