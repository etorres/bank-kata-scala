package es.eriktorr.katas

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

class AccountServiceAcceptance extends FlatSpec with Matchers with MockFactory {

  "Account service" should "print all statements and balance in the reverse order of how they happened" in {
    val clock = mock[Clock]
    val statementPrinter = mock[StatementPrinter]

    val accountService = new SimpleAccountService(clock, new StatementRepository)
    accountService.deposit(1000)
    accountService.deposit(2000)
    accountService.withdraw(500)
    accountService.printStatement()

    inSequence {
      (statementPrinter.printLine _).expects("Date || Amount || Balance")
      (statementPrinter.printLine _).expects("14/01/2012 || -500 || 2500")
      (statementPrinter.printLine _).expects("13/01/2012 || 2000 || 3000")
      (statementPrinter.printLine _).expects("10/01/2012 || 1000 || 1000")
    }
  }

}
