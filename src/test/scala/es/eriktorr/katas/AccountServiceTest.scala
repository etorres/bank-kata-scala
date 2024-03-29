package es.eriktorr.katas

import java.time.LocalDate

import es.eriktorr.katas.DateFormatter.dateFrom
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers, OneInstancePerTest}

class AccountServiceTest extends FlatSpec with Matchers with OneInstancePerTest with MockFactory {

  private val date: LocalDate = dateFrom("01/01/2020")

  private val clock = mock[Clock]
  private val statementRepository = mock[StatementRepository]
  private val statementPrinter = mock[StatementPrinter]

  private val accountService = new SimpleAccountService(clock, statementRepository, statementPrinter)

  "Account service" should "record a statement when a deposit is made" in {
    (clock.now _).expects().returning(date)
    (statementRepository.save _).expects(new Statement(date, 1000))

    accountService.deposit(1000)
  }

  "Account service" should "record a statement when a withdraw is made" in {
    (clock.now _).expects().returning(date)
    (statementRepository.save _).expects(new Statement(date, -500))

    accountService.withdraw(500)
  }

  "Account service" should "print statements and balance" in {
    (statementRepository.snapshot _).expects().returning(
      List(
        new Statement(dateFrom("10/01/2012"), 1000),
        new Statement(dateFrom("13/01/2012"), 2000),
        new Statement(dateFrom("14/01/2012"), -500)
      ), 2500)

    inSequence {
      (statementPrinter.printLine _).expects("Date || Amount || Balance")
      (statementPrinter.printLine _).expects("14/01/2012 || -500 || 2500")
      (statementPrinter.printLine _).expects("13/01/2012 || 2000 || 3000")
      (statementPrinter.printLine _).expects("10/01/2012 || 1000 || 1000")
    }

    accountService.printStatement()
  }

}
