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
  TypeFactory,
  ZetaSQLBuiltinFunctionOptions
}

object HelloWorld {
  def main(args: Array[String]) = {
    val tableName = "table1";
    val table2 = "table2";
    val sqlStatement = s"SELECT MAX(user_id) FROM ${tableName}"
    val sql =
      "SELECT table1.random_stuff \nFROM table1\nINNER JOIN table2\nON table1.column_name = table2.column_name;"
    val simpleCatalog = new SimpleCatalog("global")
    simpleCatalog.addZetaSQLFunctions(new ZetaSQLBuiltinFunctionOptions());
    simpleCatalog.addSimpleTable(
      createSimpleTable(
        tableName,
        List(
          createSimpleColumn(
            tableName,
            "column_name",
            TypeFactory.createSimpleType(TypeKind.TYPE_INT64)
          ),
          createSimpleColumn(
            tableName,
            "random_stuff",
            TypeFactory.createSimpleType(TypeKind.TYPE_BOOL)
          )
        )
      )
    )
    simpleCatalog.addSimpleTable(
      createSimpleTable(
        table2,
        List(
          createSimpleColumn(
            table2,
            "column_name",
            TypeFactory.createSimpleType(TypeKind.TYPE_INT64)
          ),
          createSimpleColumn(
            table2,
            "other_name",
            TypeFactory.createSimpleType(TypeKind.TYPE_STRING)
          )
        )
      )
    )
    val statement: ResolvedNodes.ResolvedStatement =
      Analyzer.analyzeStatement(sql, new AnalyzerOptions(), simpleCatalog)
    val a = 5
  }

  def createSimpleColumn(tableName: String, name: String, cType: SimpleType) =
    new SimpleColumn(tableName, name, cType)

  def createSimpleTable(name: String,
                        columns: List[SimpleColumn]): SimpleTable =
    new SimpleTable(name, columns.asJava)
}
