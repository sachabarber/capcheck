package formatters

object OutputFormatter {

  val formatter = java.text.NumberFormat.getInstance

  def formatOutput(result: BigDecimal) : String = {

    if(result >= 100) {
      s"£ ${formatter.format(result / 100)}"
    } else {
      s"${formatter.format(result)} p"
    }
  }
}