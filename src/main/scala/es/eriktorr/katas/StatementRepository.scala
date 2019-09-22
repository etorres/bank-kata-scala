package es.eriktorr.katas

import java.util.concurrent.atomic.LongAdder

import scala.collection.mutable.ListBuffer

class StatementRepository {

  private val statements: ListBuffer[Statement] = ListBuffer.empty[Statement]

  private val balance = new LongAdder

  def snapshot(): (List[Statement], Int) = {
    (statements.toList, balance.intValue())
  }

  def save(statement: Statement): Unit = {
    statements += statement
    balance.add(statement.amount)
  }

}
