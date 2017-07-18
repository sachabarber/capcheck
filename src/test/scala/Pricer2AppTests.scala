import data.RealItemRepository
import formatters.OutputFormatter
import org.scalatest._
import org.scalatest.exceptions.TestFailedException
import pricers.{Step2Pricer}

class Pricer2AppTests extends FlatSpec {

  // by using an Implicit for the StepPricer we could easily
  // supply a real implementation or a Mock (Mockito)
  // saved us messing around with Cake pattern
  implicit val pricer = new Step2Pricer(new RealItemRepository)
  val ratio = 2.0/3.0

  "valid set of 3 for 2 oranges" should "calculate ok" in {
    val finalPrice = GeminiApp.run(List[String]("orange", "orange", "orange").toArray)
    val totalOrangePrice : BigDecimal = ((3 * 25) * ratio)
    assert(finalPrice === OutputFormatter.formatOutput(totalOrangePrice))
  }

  "valid set of 3 for 2 oranges + 1 extra orange" should "calculate ok" in {
    val finalPrice = GeminiApp.run(List[String]("orange", "orange", "orange", "orange").toArray)
    val totalOrangePrice : BigDecimal = ((3 * 25) * ratio) + 25
    assert(finalPrice === OutputFormatter.formatOutput(totalOrangePrice))
  }

  "buy one get one free on apples + 1 extra apple" should "calculate ok" in {
    val finalPrice = GeminiApp.run(List[String]("apple", "apple", "apple").toArray)
    val totalApplePrice : BigDecimal = (((2 * 60) * 0.5) + 60)
    assert(finalPrice === OutputFormatter.formatOutput(totalApplePrice))
  }

  "mixed bag of offers" should "calculate ok" in {
    val finalPrice = GeminiApp.run(List[String]("apple", "apple", "orange", "orange", "orange").toArray)
    val totalApplePrice : BigDecimal = ((2 * 60) * 0.5)
    val totalOrangePrice : BigDecimal = ((3 * 25) * ratio)
    assert(finalPrice === OutputFormatter.formatOutput(totalApplePrice + totalOrangePrice))
  }


}