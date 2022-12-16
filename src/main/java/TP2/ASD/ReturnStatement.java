package TP2.ASD;

import TP2.Llvm;
import TP2.SymbolTable;
import TP2.TypeException;
import TP2.Utils;

/**
 * Concrete class for Expression: div case
 */
public class ReturnStatement extends Statement {

  Expression returnExpr;

  public ReturnStatement(Expression returnExpr) {
    this.returnExpr = returnExpr;
  }

  /**
   * Pretty-printer
   */
  public String pp() {
    Expression.RetExpression ret = null;
    try {
      ret = returnExpr.toIR();
    } catch (TypeException e) {
      System.out.println("Ops, type problem");
    }

    return "ret " + ret.type + " " + ret.result;
  }

  @Override
  public RetExpression toIR() throws TypeException {

    // computes the IR of the expression
    Expression.RetExpression thisExpr = returnExpr.toIR();

    // add a return instruction
    Llvm.Instruction ret = new Llvm.Return(thisExpr.type.toLlvmType(), thisExpr.result);

    // append this instruction
    thisExpr.ir.appendCode(ret);

    // return the generated IR, plus the type of this expression
    // and where to find its result
    return new Statement.RetExpression(thisExpr.ir, thisExpr.type, thisExpr.result);
  }
}