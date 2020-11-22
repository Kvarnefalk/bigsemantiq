package bigsemantiq.scala

import bigsemantiq.scala.HelloWorld.createSimpleColumn

import scala.collection.JavaConverters._
import com.google.zetasql.ZetaSQLType.TypeKind
import com.google.zetasql.resolvedast.ResolvedNodes
import com.google.zetasql.{
  Analyzer,
  AnalyzerOptions,
  SimpleCatalog,
  SimpleColumn,
  SimpleTable,
  SimpleType,
  TypeFactory
}

object HelloWorld {
  def main(args: Array[String]) = {
    val tableName = "user_table";
    val sqlStatement = s"SELECT user_id FROM ${tableName}"
    val simpleCatalog = new SimpleCatalog("global")
    simpleCatalog.addSimpleTable(
      createSimpleTable(
        tableName,
        List(
          createSimpleColumn(
            tableName,
            "user_id",
            TypeFactory.createSimpleType(TypeKind.TYPE_INT64)
          )
        )
      )
    )
    val statement: ResolvedNodes.ResolvedStatement = Analyzer.analyzeStatement(
      sqlStatement,
      new AnalyzerOptions(),
      simpleCatalog
    )
    val a = 5
  }

  def createSimpleColumn(tableName: String, name: String, cType: SimpleType) =
    new SimpleColumn(tableName, name, cType)

  def createSimpleTable(name: String,
                        columns: List[SimpleColumn]): SimpleTable =
    new SimpleTable(name, columns.asJava)
}
