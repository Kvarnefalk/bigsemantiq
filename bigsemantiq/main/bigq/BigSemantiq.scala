package bigsemantiq

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

object BigSemantiq {
  def main(args: Array[String]) = {
    val tableName = "table1";
    val table2 = "table2";
    val sql =
      "SELECT MAX(table1.random_stuff) \nFROM table1\nINNER JOIN table2\nON table1.column_name = table2.column_name;"
    val simpleCatalog = SimpleCatalog("global")
    simpleCatalog.addZetaSQLFunctions(new ZetaSQLBuiltinFunctionOptions());
    simpleCatalog.addSimpleTable(
      BSQSimpleTable(
        tableName,
        List(
          SimpleColumn(
            tableName,
            "column_name",
            TypeFactory.createSimpleType(TypeKind.TYPE_INT64)
          ),
          SimpleColumn(
            tableName,
            "random_stuff",
            TypeFactory.createSimpleType(TypeKind.TYPE_INT32)
          )
        )
      )
    )
    simpleCatalog.addSimpleTable(
      BSQSimpleTable(
        table2,
        List(
          SimpleColumn(
            table2,
            "column_name",
            TypeFactory.createSimpleType(TypeKind.TYPE_INT64)
          ),
          SimpleColumn(
            table2,
            "other_name",
            TypeFactory.createSimpleType(TypeKind.TYPE_STRING)
          )
        )
      )
    )
    val statement: ResolvedNodes.ResolvedStatement =
      Analyzer.analyzeStatement(sql, new AnalyzerOptions(), simpleCatalog)
    println(statement.debugString())
    val a = 5
  }

  def createSimpleColumn(tableName: String, name: String, cType: SimpleType) =
    new SimpleColumn(tableName, name, cType)

  def createSimpleTable(name: String,
                        columns: List[SimpleColumn]): SimpleTable =
    new SimpleTable(name, columns.asJava)
}

object SimpleCatalog {
  def apply(name: String) = new SimpleCatalog(name)
}

object SimpleColumn {
  def apply(tableName: String, name: String, columnType: SimpleType) =
    new SimpleColumn(tableName, name, columnType)
}

object BSQSimpleTable {
  def apply(name: String, columns: List[SimpleColumn]) =
    new SimpleTable(name, columns.asJava)

  def apply(name: String) = new SimpleTable(name)

  def apply(name: String, rowType: SimpleType) = new SimpleTable(name, rowType)
}
