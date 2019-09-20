package es.eriktorr.katas

import java.time.LocalDate

class Statement(val date: LocalDate, val amount: Int) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Statement]

  override def equals(other: Any): Boolean = other match {
    case that: Statement =>
      (that canEqual this) &&
        date == that.date &&
        amount == that.amount
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(date, amount)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

}
