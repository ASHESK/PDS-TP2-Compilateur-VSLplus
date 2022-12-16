parser grammar VSLParser;

options {
  language = Java;
  tokenVocab = VSLLexer;
}

@header {
  package TP2;

  import java.util.stream.Collectors;
  import java.util.Arrays;
  import java.util.stream.Stream;
  import TP2.ASD.*;
}

// TODO : other rules

program returns [TP2.ASD.Program out]
    : bl=blocks EOF { $out = new TP2.ASD.Program($bl.out); } // TODO : change when you extend the language
    ;

blocks returns [List<TP2.ASD.Block> out]
    : { $out = new ArrayList<>(); } b=block { $out.add($b.out); } (BL+ bl=blocks { $out.addAll($bl.out); })?
    ;

block returns [TP2.ASD.Block out]
    : BL? LB BL+ sl=statements { $out = new TP2.ASD.Block($sl.out); } BL? RB BL?
    ;

statements returns [List<Statement> out]
    : { $out = new ArrayList<>(); } s=statement { $out.add($s.out); } (BL+ sl=statements { $out.addAll($sl.out); })?
    ;

statement returns [TP2.ASD.Statement out]
    : i=IDENT EQUAL e=expression  { $out = new TP2.ASD.AffectStatement($i.text, $e.out); }
    | RET e=expression  { $out = new TP2.ASD.ReturnStatement($e.out); }
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
    | IDENT { $out = new TP2.ASD.IdentExpression($IDENT.text); }
    // TODO : that's all?
    ;
