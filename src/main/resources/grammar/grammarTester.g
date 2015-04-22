grammar grammarTester;

options {
  language = Java;
}

@header {
  package main.resources.grammar;
}

@lexer::header {
  package main.resources.grammar;
}

rule: ABC+ DEF+;

ABC: 'a'..'c';
DEF: 'd'..'f';

WS : ' '+|'\n'+ { skip(); };