package pricers

import data.ItemRepository

/**
  * base trait for all step pricers
  */
trait StepPricer {
  val repo:ItemRepository
  def getTotalPrice(itemsToPrice : Map[String,Int]) : BigDecimal
}
