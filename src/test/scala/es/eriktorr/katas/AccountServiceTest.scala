package es.eriktorr.katas

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

class AccountServiceTest extends FlatSpec with Matchers with MockFactory {

  private val DATE: LocalDate = dateFrom("01/01/2020")

  "Account service" should "record a statement when a deposit is made" in {
    val clock = mock[Clock]
    val statementRepository = mock[StatementRepository]

    (clock.now _).expects().returning(DATE)
    (statementRepository.save _).expects(new Statement(DATE, 1000))

    val accountService = new SimpleAccountService(clock, statementRepository)
    accountService.deposit(1000)
  }

  "Account service" should "record a statement when a withdraw is made" in {
    val clock = mock[Clock]
    val statementRepository = mock[StatementRepository]

    (clock.now _).expects().returning(DATE)
    (statementRepository.save _).expects(new Statement(DATE, -500))

    val accountService = new SimpleAccountService(clock, statementRepository)
    accountService.withdraw(500)
  }

  private def dateFrom(date: String): LocalDate = {
    LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
  }

}
