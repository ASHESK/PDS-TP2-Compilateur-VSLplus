package TP2.ASD;

import TP2.Llvm;
import TP2.TypeException;
import TP2.Utils;

/**
 * Concrete class for Expression: div case
 */
  public class AffectStatement extends Statement {

    String ident;

    Expression affectValue;

    public AffectStatement(String ident, Expression affectValue) {
      this.ident = ident;
      this.affectValue = affectValue;
    }

    /**
     * Pretty-printer
     */
    public String pp() {
      return ident + " = " + affectValue.pp();
    }

  @Override
  public RetExpression toIR() throws TypeException {

    String ident = this.ident;
    Expression.RetExpression affectValue = this.affectValue.toIR();

    String result = Utils.newglob(ident);

    // new add instruction result = left / right
    Llvm.Instruction affect = new Llvm.Affect(result, affectValue.result);

    // append this instruction
    affectValue.ir.appendCode(affect);

    // return the generated IR, plus the type of this expression
    // and where to find its result
    return new Statement.RetExpression(affectValue.ir, affectValue.type, result);
  }
}