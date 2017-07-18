import data.RealItemRepository
import formatters.OutputFormatter
import org.scalatest._
import org.scalatest.exceptions.TestFailedException
import pricers.Step1Pricer

class Pricer1AppTests extends FlatSpec {

  // by using an Implicit for the StepPricer we could easily
  // supply a real implementation or a Mock (Mockito)
  // saved us messing around with Cake pattern
  implicit val pricer = new Step1Pricer(new RealItemRepository)

  "A empty list of command line args" should "throw Exception" in {
    assertThrows[IllegalStateException] { // Result type: Assertion
      val app = GeminiApp.main(List[String]().toArray)
    }
  }

  "An item that is not 'apple' Or 'orange' arg" should "throw Exception" in {
    assertThrows[IllegalStateException] {
      val app = GeminiApp.main(List[String]("apple","cat").toArray)
    }
  }

  "valid set of items" should "calculate ok" in {
    val finalPrice1 = GeminiApp.main(List[String]("apple", "orange").toArray)
    val totalApplePrice1 : BigDecimal = 1 * 60
    val totalOrangePrice1 : BigDecimal = 1 * 25
    assert(finalPrice1 === OutputFormatter.formatOutput(totalApplePrice1 + totalOrangePrice1))


    val finalPrice2 = GeminiApp.main(List[String]("apple", "orange","apple").toArray)
    val totalApplePrice2 : BigDecimal = 2 * 60
    val totalOrangePrice2 : BigDecimal = 1 * 25
    assert(finalPrice2 === OutputFormatter.formatOutput(totalApplePrice2 + totalOrangePrice2))
  }

  "Too many applies, and bad test assumtion" should "result in faied test ok" in {
    val finalPrice = GeminiApp.main(List[String]("apple","apple", "orange").toArray)
    val totalApplePrice : BigDecimal = 1 * 60
    val totalOrangePrice : BigDecimal = 1 * 25
    assertThrows[TestFailedException] {
      assert(finalPrice === OutputFormatter.formatOutput(totalApplePrice + totalOrangePrice))
    }

  }
}