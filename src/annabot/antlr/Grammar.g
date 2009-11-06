grammar Grammar;

options {
	output=AST;
	ASTLabelType=CommonTree; // type of $stat.tree ref etc...
}

@header {
package annabot.antlr;
}
@lexer::header {
package annabot.antlr;
}

program:	import_stmt*
			CLAIM IDENTIFIER '{'
				stmt+
			'}'
			;

import_stmt:	IMPORT NAMEINPACKAGE ';' {
			System.out.println("IMPORT " + $NAMEINPACKAGE.text);
			};

// Statement, with or without if ... { stmt } around.
stmt:	IF '(' checks ')' '{' phrase+ '}' ';' {
			System.out.println("STMT-WITH-IF: " + $phrase.tree.toStringTree());
			}
		| phrase {
			System.out.println("SIMPLE-STMT: " + $phrase.tree.toStringTree());
			}
		;
phrase:	verb checks error? ';' 
		;

verb:	REQUIRE | ATMOSTONE | NONEOF;

checks:	check
		| NOT check
		| ( check OR check )
		| ( check AND check )
		| ( check ',' check)
		;

check:	classAnnotated | methodAnnotated | fieldAnnotated;

classAnnotated:		CLASS_ANNOTATED '(' NAMEINPACKAGE ')';
methodAnnotated:	METHOD_ANNOTATED '(' NAMEINPACKAGE ')';
fieldAnnotated:		FIELD_ANNOTATED '(' NAMEINPACKAGE ( ',' MEMBERNAME )? ')';

error:	ERROR QSTRING;

// Keywords
IMPORT:				'import';
CLAIM:				'claim';
REQUIRE:			'require';
ATMOSTONE:			'atMostOne';
NONEOF:				'noneof';
CLASS_ANNOTATED:	'class.annotated';
METHOD_ANNOTATED:	'method.annotated';
FIELD_ANNOTATED:	'field.annotated';
IF:					'if';
AND:				'&&' | 'and';
OR:					'||' | 'or';
NOT:				'!'  | 'not';
ERROR:				'error';

// stuff for identiers etc.
LETTER:				'a'..'z'|'A'..'Z';
DIGIT:				'0'..'9';
IDENTIFIERCHAR:		LETTER | DIGIT | '_';
IDENTIFIER:			LETTER IDENTIFIERCHAR*;
NAMEINPACKAGE:		IDENTIFIER ( '.' IDENTIFIER )*;
IMPORTNAME:		NAMEINPACKAGE  ('.' '*')*;
MEMBERNAME:		(IDENTIFIER|'*');

// Lexical miscellany
QSTRING:			'"' ( options {greedy=false;} : . )* '"' ;
fragment NEWLINE:	'\r'? '\n';	// allow for ms-dos files.
WHITESPACE:	(' '|'\t'|NEWLINE)+ {skip();} ;
COMMENT:	'#' ( options {greedy=false;} : . )* NEWLINE+ {
				skip();
			};