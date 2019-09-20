package es.eriktorr.katas

class SimpleAccountService(private val clock: Clock, private val statementRepository: StatementRepository)
  extends AccountService {

  override def deposit(amount: Int): Unit = {
    statementRepository.save(new Statement(clock.now, amount))
  }

  override def withdraw(amount: Int): Unit = ???

  override def printStatement(): Unit = ???

}
