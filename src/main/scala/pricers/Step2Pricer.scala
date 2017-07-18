package pricers

import data.ItemRepository

/**
  * Step1 pricer
  */
class Step2Pricer(val repo : ItemRepository) extends StepPricer {

  val pricers =  Map("apple" -> new AppleOfferPricer, "orange" -> new OrangeOfferPricer)

  def getTotalPrice(itemsToPrice : Map[String,Int]) : BigDecimal = {
    itemsToPrice.map(x=> pricers(x._1).getTotalPrice(x._2, repo.getItemCost(x._1))).sum
  }
}
