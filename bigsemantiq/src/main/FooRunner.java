package bigsemantiq.src.main;
// Your First Program

import com.google.zetasql.*;
import com.google.zetasql.ZetaSQLType.ArrayTypeProto;
import com.google.zetasql.ZetaSQLType.TypeKind;
import com.google.zetasql.ZetaSQLType.TypeProto;
import com.google.zetasql.resolvedast.ResolvedNodes;
import com.google.zetasql.resolvedast.ResolvedNodes.ResolvedStatement;

import java.util.ArrayList;
import java.util.List;

class FooRunner {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        String userTableName = "user_table";

        ZetaSQLStrings.toIdentifierLiteral("Heh");
        SimpleColumn userIdColumn = new SimpleColumn(userTableName, "user_id", TypeFactory.createSimpleType(TypeKind.TYPE_INT32));
        SimpleColumn userNameColumn = new SimpleColumn(userTableName, "name", TypeFactory.createSimpleType(TypeKind.TYPE_STRING));
        SimpleColumn userEmailColumn = new SimpleColumn(userTableName, "email", TypeFactory.createSimpleType(TypeKind.TYPE_STRING));
        SimpleColumn userBirthDate = new SimpleColumn(userTableName, "birth_date", TypeFactory.createSimpleType(TypeKind.TYPE_DATE));
        List<SimpleColumn> userColumns = new ArrayList();
        userColumns.add(userIdColumn);
        userColumns.add(userNameColumn);
        userColumns.add(userEmailColumn);
        userColumns.add(userBirthDate);

        SimpleTable userTable = new SimpleTable(userTableName, userColumns);
        SimpleCatalog catalog = new SimpleCatalog("global");
        catalog.addSimpleTable(userTable);
        AnalyzerOptions options = new AnalyzerOptions();
        Analyzer analyzer = new Analyzer(options, catalog);

        String sql = "SELECT * FROM user_table u1 INNER JOIN other_table u2 ON u1.user_id = u2.user_id;";
        ResolvedStatement statement = analyzer.analyzeStatement(sql);
        System.out.println(statement.debugString());
        //List<List<String>> table_names = Analyzer.extractTableNamesFromStatement(sql);
     //   System.out.println(table_names);

        int a = 2;
    }
}