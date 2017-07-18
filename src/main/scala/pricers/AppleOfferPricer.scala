package pricers

class AppleOfferPricer extends ItemPricer {
  def getTotalPrice(quantityOfItem : Int, singleItemPrice : BigDecimal) : BigDecimal = {

    if(quantityOfItem < 2)
      quantityOfItem * singleItemPrice

    val withinOffer = Math.floor(quantityOfItem/2).toInt
    val outsideOffer = quantityOfItem - (withinOffer * 2)
    (withinOffer * singleItemPrice) + (outsideOffer * singleItemPrice)
  }
}
