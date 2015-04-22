grammar grammarV2;

options {
  language = Java;
}

@header {
  package main.resources.grammar;
}

@lexer::header {
  package main.resources.grammar;
}

//DECLAREBEGINNULL;END;\\
//DECLAREidCHARBEGINNULL;END;\\

block: declarations compound_statement end_program| compound_statement end_program; 

//WS : ' '+|'\n'+ { skip(); }; 
stringliteral: 'stringliteral';
c: 'c';
identifier: 'id';
end_program: '\\';
end_char: (';');
equals: (':=');
num: ('0'|'1'|'2'|'3'|'4'|'5'|'6'|'7'|'8'|'9')+;
relop: '>'|'>='|'=='|'<='|'<'|'<>';
addop:'+'|'-';
mulop: '*'|'/'|'%';
DECLARE:'DECLARE';
BOOLEAN:'BOOLEAN';
CHAR:'CHAR';
VARCHAR2: 'VARCHAR2';
NUMBER:'NUMBER';
SMALLINT:'SMALLINT';
POSITIVE:'POSITIVE';
NULL:'NULL';

declarations: DECLARE declare_rest|DECLARE;

declare_rest:identifier type| identifier type end_char declare_rest;

type: data_type| data_type default;

default: equals righthandside;

data_type: BOOLEAN|characters|numbers;

characters:CHAR| VARCHAR2| VARCHAR2 size;

size: '(' num ')';

numbers: 'NUMBER' size| 'SMALLINT' size| 'POSITIVE' size;

compound_statement: 'BEGIN' optional_statements 'END' end_char; 

optional_statements: 'NULL' end_char| statement_list;

statement_list: statement| statement_list end_char statement;

statement: lefthandside| compound_statement| 'DBMS_OUTPUT.PUT_LINE' '(' identifier ')' end_char| 'DBMS_OUTPUT.PUT' '(' identifier ')' end_char| 'DBMS_OUTPUT.NEW_LINE' end_char| '&' identifier end_char| 'IF BEGIN' expression 'THEN' statement 'END IF' end_char| 'WHILE' expression 'LOOP' statement 'END LOOP' end_char;

lefthandside: identifier equals righthandside;

righthandside: expression| stringliteral| c| casting '(' expression ')';

casting: data_type;

expression: simple_expression| relop simple_expression;

simple_expression: term| simple_expression addop term;

term: factor| term mulop factor;

factor: identifier| num| 'TRUE'| 'FULSE'| 'NULL'| 'NOT' factor;
