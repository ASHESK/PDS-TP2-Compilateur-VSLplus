lexer grammar VSLLexer;

options {
  language = Java;
}

@header {
  package TP2;
}

WS : (' '|'\n'|'\t') -> skip
   ;

COMMENT : '//' (~'\n')* -> skip
        ;

fragment LETTER : 'a'..'z' ;
fragment DIGIT  : '0'..'9' ;
fragment ASCII  : ~('\n'|'"');

// keywords
RET     : 'RETURN' ;
LP      : '(' ; // Left parenthesis
RP      : ')' ;
LB      : '{' ; // Left parenthesis
RB      : '}' ;
BL      : '\r';
EQUAL   : '=' ;
DIV     : '/' ;
MULT    : '*' ;
PLUS    : '+' ;
MINUS   : '-' ;

// TODO : other keywords

// other tokens (no conflict with keywords in VSL)
IDENT   : LETTER (LETTER|DIGIT)*;
TEXT    : '"' (ASCII)* '"' { setText(getText().substring(1, getText().length() - 1)); };
INTEGER : (DIGIT)+;
