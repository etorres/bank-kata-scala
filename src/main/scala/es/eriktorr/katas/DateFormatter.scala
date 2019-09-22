package es.eriktorr.katas

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter {

  private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  def dateFrom(date: String): LocalDate = {
    LocalDate.parse(date, dateTimeFormatter)
  }

  def dateToString(date: LocalDate): String = {
    dateTimeFormatter.format(date)
  }

}
