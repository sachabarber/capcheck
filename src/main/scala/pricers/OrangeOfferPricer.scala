package pricers

class OrangeOfferPricer extends ItemPricer {
  def getTotalPrice(quantityOfItem : Int, singleItemPrice : BigDecimal) : BigDecimal = {

    if(quantityOfItem < 3)
      quantityOfItem * singleItemPrice

    val offerNumber = 3
    val withinOffer = Math.floor(quantityOfItem/offerNumber).toInt * offerNumber
    val outsideOffer = quantityOfItem - withinOffer
    val ratio = 2.0/3.0
    val adjusted = singleItemPrice * ratio
    (withinOffer * adjusted) + (outsideOffer * singleItemPrice)
  }
}
