package pricers

/**
  * base trait for all single item offers
  */
trait ItemPricer {
  def getTotalPrice(quantityOfItem : Int, singleItemPrice : BigDecimal) : BigDecimal
}
