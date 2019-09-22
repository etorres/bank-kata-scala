package es.eriktorr.katas

import java.time.format.DateTimeFormatter

import scala.annotation.tailrec

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
    statementPrinter.printLine("Date || Amount || Balance")
    val snapshot = statementRepository.snapshot()
    printStatementWithBalance(snapshot._1, snapshot._2)
  }

  @tailrec
  private def printStatementWithBalance(statements: List[Statement], balance: Int): Unit = {
    statements match {
      case Nil =>
      case allExceptLast :+ last =>
        statementPrinter.printLine(lineFrom(last, balance))
        printStatementWithBalance(allExceptLast, balance - last.amount)
    }
  }

  private def lineFrom(statement: Statement, balance: Int): String = {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    s"${dateTimeFormatter.format(statement.date)} || ${statement.amount} || ${balance}"
  }

}
