package es.eriktorr.katas

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

class AccountServiceTest extends FlatSpec with Matchers with MockFactory {

  "An account service" should "print all statements and balance in the reverse order of how they happened" in {
    val accountService = new SimpleAccountService

    accountService.deposit(1000)
    accountService.deposit(2000)
    accountService.withdraw(500)
    accountService.printStatement()


  }

}
