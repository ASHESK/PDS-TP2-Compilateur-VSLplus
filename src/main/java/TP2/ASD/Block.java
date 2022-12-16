package TP2.ASD;

import TP2.Llvm;
import TP2.SymbolTable;
import TP2.TypeException;
import TP2.Utils;

import java.util.ArrayList;
import java.util.List;

public class Block {

    List<Statement> statementList; // What a program contains. TODO : change when you extend the language

    SymbolTable table;

    public Block(List<Statement> statementList) {
        this.statementList = statementList;
        this.table = new SymbolTable();
    }

    /**
     * Pretty-printer
     */
    public String pp() {
        String pp = "";

        for (Statement s : statementList) {
            pp += s.pp() + "\n";
        }

        return pp;
    }

    /**
     * IR generation
     */
    public Llvm.IR toIR() throws TypeException {
        // TODO : change when you extend the language

        Statement.RetExpression retExpr = new Statement.RetExpression(new Llvm.IR(new ArrayList<>(), new ArrayList<>()), new Int(),"");

        for (Statement s : statementList) {

            retExpr.ir.append(s.toIR().ir);
        }

        return retExpr.ir;
    }
}