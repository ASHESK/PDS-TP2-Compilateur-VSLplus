package TP2.ASD;

import TP2.Llvm;
import TP2.SymbolTable;
import TP2.TypeException;

import java.util.ArrayList;
import java.util.List;

public class Program {
    List<Block> bl; // What a program contains. TODO : change when you extend the language

    SymbolTable table;

    public Program(List<Block> bl) {
      this.bl = bl;
      this.table = new SymbolTable();
    }

    /**
     * Pretty-printer
     */
    public String pp() {
        String pp = "";

        for (Block b : bl) {
            pp += b.pp() + "\n";
        }

        return pp;
    }

    /**
     * IR generation
     */
    public Llvm.IR toIR() throws TypeException {
      // TODO : change when you extend the language

        Statement.RetExpression retExpr = new Statement.RetExpression(new Llvm.IR(new ArrayList<>(), new ArrayList<>()), new Int(),"");

        for (Block b: bl) {

            retExpr.ir.append(b.toIR());
        }

        return retExpr.ir;
    }
  }