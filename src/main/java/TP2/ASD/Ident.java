package TP2.ASD;

import TP2.Llvm;

public class Ident extends Type {

    public String pp() {
      return "IDENT";
    }

    @Override public boolean equals(Object obj) {
      return obj instanceof Ident;
    }

    public Llvm.Type toLlvmType() {
      return new Llvm.Ident();
    }
  }