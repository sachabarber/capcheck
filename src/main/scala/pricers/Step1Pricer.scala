package pricers

import data.ItemRepository

/**
  * Step1 pricer
  */
class Step1Pricer(val repo : ItemRepository) extends StepPricer {

  def getTotalPrice(itemsToPrice : Map[String,Int]) : BigDecimal = {
    itemsToPrice.map(x => getPriceForItem(x._1) * x._2).sum
  }

  private def getPriceForItem(itemName:String) : BigDecimal = {
    repo.getItemCost(itemName)
  }
}
