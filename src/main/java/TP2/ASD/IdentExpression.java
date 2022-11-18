package TP2.ASD;

import TP2.Llvm;

/**
 * Concrete class for Expression: constant (integer) case
 */
public class IdentExpression extends Expression {
  String ident;

  public IdentExpression(String ident) {
    this.ident = ident;
  }

  public String pp() {
    return "" + ident;
  }

  public RetExpression toIR() {
    // Here we simply return an empty IR
    // the `result' of this expression is the integer itself (as string)
    return new RetExpression(new Llvm.IR(Llvm.empty(), Llvm.empty()), new Int(), "" + ident);
  }
}