package TP2.ASD;

import TP2.Llvm;
import TP2.TypeException;

public class Program {
    Statement s; // What a program contains. TODO : change when you extend the language

    public Program(Statement s) {
      this.s = s;
    }

    /**
     * Pretty-printer
     */
    public String pp() {
      return s.pp();
    }

    /**
     * IR generation
     */
    public Llvm.IR toIR() throws TypeException {
      // TODO : change when you extend the language

      // computes the IR of the expression
      Statement.RetExpression retExpr = s.toIR();

      // add a return instruction
      Llvm.Instruction ret = new Llvm.Return(retExpr.type.toLlvmType(), retExpr.result);

      retExpr.ir.appendCode(ret);

      return retExpr.ir;
    }
  }