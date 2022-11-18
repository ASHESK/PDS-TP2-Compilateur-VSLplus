parser grammar VSLParser;

options {
  language = Java;
  tokenVocab = VSLLexer;
}

@header {
  package TP2;

  import java.util.stream.Collectors;
  import java.util.Arrays;
}


// TODO : other rules

program returns [TP2.ASD.Program out]
    : s=statement EOF { $out = new TP2.ASD.Program($s.out); } // TODO : change when you extend the language
    ;

statement returns [TP2.ASD.Statement out]
    : i=IDENT EQUAL r=expression  { $out = new TP2.ASD.AffectStatement($i.text, $r.out); }
    ;

expression returns [TP2.ASD.Expression out]
    : l=mult PLUS r=expression  { $out = new TP2.ASD.AddExpression($l.out, $r.out); }
    | l=mult MINUS r=expression  { $out = new TP2.ASD.MinusExpression($l.out, $r.out); }
    | m=mult { $out = $m.out; }
    ;

mult returns [TP2.ASD.Expression out]
    : l=factor MULT r=expression  { $out = new TP2.ASD.MultExpression($l.out, $r.out); }
    | l=factor DIV r=expression  { $out = new TP2.ASD.DivExpression($l.out, $r.out); }
    | f=factor { $out = $f.out; }
    ;

factor returns [TP2.ASD.Expression out]
    : p=primary { $out = $p.out; }
    // TODO : that's all?
    ;

primary returns [TP2.ASD.Expression out]
    : INTEGER { $out = new TP2.ASD.IntegerExpression($INTEGER.int); }
    // TODO : that's all?
    ;
