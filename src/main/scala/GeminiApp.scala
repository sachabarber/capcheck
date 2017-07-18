import data.{ItemRepository, RealItemRepository}
import entities.{Apple, Orange}
import formatters.OutputFormatter
import pricers.{Step1Pricer, Step2Pricer, StepPricer}

object GeminiApp {


  /** Takes an array of strings, and will verify that there is at least one item, and that
    * the values are either 'apple' or 'orange'. Then it will group the items and run them
    * through a pricer
    *
    *  @param args the array of strings
    *  @return  a String representing the cost
    */
  def main(args: Array[String]): Unit  = {
    run(args)(new Step2Pricer(new RealItemRepository))
  }


  /** Takes an array of strings, and will verify that there is at least one item, and that
    * the values are either 'apple' or 'orange'. Then it will group the items and run them
    * through a pricer
    *
    *  @param args the array of strings
    *  @param stepPricer this implicit is the StepPricer which is the current pricer that can be
    *                    used to obtain final item prices
    *  @return  a String representing the cost
    */
  def run(args: Array[String])(implicit stepPricer:StepPricer) : String  = {

    if(args.length ==0)
      throw new IllegalStateException("Must have some command line args")

    val convertedItems = args.map(_.toLowerCase()).map {
      case "apple" => new Apple()
      case "orange" => new Orange()
      case _ => throw new IllegalStateException("Only 'apple' and 'orange' is allowed as argument")
    }.toList


    val mappedItems = convertedItems.groupBy(t => t.itemName).map(x => (x._1, x._2.length))
    val finalPrice = OutputFormatter.formatOutput(stepPricer.getTotalPrice(mappedItems))

    mappedItems.foreach(x => {
      val (key,numItems) = x
      println(s"Saw (${numItems}) (${key}) items")
    })

    println(s"Total price is  (${finalPrice})")
    finalPrice
  }
}