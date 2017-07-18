package pricers

class OrangeOfferPricer extends ItemPricer {
  def getTotalPrice(quantityOfItem : Int, singleItemPrice : BigDecimal) : BigDecimal = {

    if(quantityOfItem < 3)
      quantityOfItem * singleItemPrice

    val offerNumber = 3
    val withinOffer = Math.floor(quantityOfItem/offerNumber).toInt * offerNumber
    val outsideOffer = quantityOfItem - withinOffer
    val ratio = singleItemPrice * 0.66
    (withinOffer * ratio) + (outsideOffer * singleItemPrice)
  }
}
