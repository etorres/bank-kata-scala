package es.eriktorr.katas

class SimpleAccountService(private val clock: Clock,
                           private val statementRepository: StatementRepository,
                           private val statementPrinter: StatementPrinter) extends AccountService {

  override def deposit(amount: Int): Unit = {
    statementRepository.save(new Statement(clock.now, amount))
  }

  override def withdraw(amount: Int): Unit = {
    statementRepository.save(new Statement(clock.now, -amount))
  }

  override def printStatement(): Unit = {
    for (statement <- statementRepository.statements) {
      statementPrinter.printLine(lineFrom(statement))
    }
  }

  private def lineFrom(statement: Statement): String = {
    ""
  }

}
