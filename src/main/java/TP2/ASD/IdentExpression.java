package TP2.ASD;

import TP2.Llvm;

/**
 * Concrete class for Expression: constant (integer) case
 */
public class IdentExpression extends Expression {
  String ident;

  Type type;

  public IdentExpression(String ident) {
    this.ident = ident;
  }

  public String pp() {
    return ident;
  }

  public RetExpression toIR() {
    // Here we simply return an empty IR
    // the `result' of this expression is the integer itself (as string)
    // TODO : Get ident type from symbTable
    return new RetExpression(new Llvm.IR(Llvm.empty(), Llvm.empty()), new Ident(), "" + ident);
  }
}