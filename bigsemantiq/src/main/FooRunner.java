package bigsemantiq.src.main;
// Your First Program

import com.google.zetasql.*;
import com.google.zetasql.resolvedast.ResolvedNodes.ResolvedStatement;

class FooRunner {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        AnalyzerOptions options = new AnalyzerOptions();
        SimpleCatalog catalog = new SimpleCatalog("MainCatalog");
        Analyzer analyzer = new Analyzer(options, catalog);
        ResolvedStatement resolvedStatement = analyzer.analyzeStatement("SELECT * FROM user_table");
        System.out.println(resolvedStatement.debugString());
    }
}