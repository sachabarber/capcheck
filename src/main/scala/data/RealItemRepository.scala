package data

/**
  * Semi-real item repo
  */
class RealItemRepository extends ItemRepository {

  //NOTES :
  //1. This would come from database
  //2. We could of use enums for key, but for this simple example string is ok I feel
  private val theItems = Map("apple" -> 60, "orange" -> 25)


  def getItemCost(itemName: String): BigDecimal = {
    theItems(itemName)
  }
}

