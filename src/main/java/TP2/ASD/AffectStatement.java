package TP2.ASD;

import TP2.Llvm;
import TP2.SymbolTable;
import TP2.TypeException;
import TP2.Utils;

/**
 * Concrete class for Expression: div case
 */
  public class AffectStatement extends Statement {

    Expression ident;

    Expression affectValue;

    SymbolTable table;

    public AffectStatement(String ident, Expression affectValue) {
      this.ident = new IdentExpression(ident);
      this.affectValue = affectValue;
      this.table = null;
    }

    public void updateSymbolTable(SymbolTable table) {
      if (table != null) {
        this.table = table;
      }
      else {
        throw new NullPointerException("The table cannot be null");
      }
    }

    /**
     * Pretty-printer
     */
    public String pp() {
      return ident + " = " + affectValue.pp();
    }

  @Override
  public RetExpression toIR() throws TypeException {

    Expression.RetExpression affectValue = this.affectValue.toIR();

    String result = "";

    switch (affectValue.type.pp()) {
      case "INT":
      case "STRING" :
        result = Utils.newglob(ident.pp());
        break;
      case "IDENT" :
        result = ident.pp(); //table.get(ident).name
        break;
    }

    // new add instruction result = left / right
    Llvm.Instruction affect = new Llvm.Affect(ident.pp(), affectValue.result);

    // append this instruction
    affectValue.ir.appendCode(affect);

    // return the generated IR, plus the type of this expression
    // and where to find its result
    return new Statement.RetExpression(affectValue.ir, affectValue.type, result);
  }
}