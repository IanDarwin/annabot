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

program:   Import * ( CLAIM NAME '{' stmt+ '}' {
	System.out.println("G"+$stmt.tree.toStringTree());} 
	)+ ;

Import:	IMPORT IMPORTNAME ';';

stmt:	ifClause verb '{' core error? '}' ';' ;

core:	check |
		( check OR check ) |
		( check AND check );

ifClause: IF '(' check ')';

verb:	REQUIRE | ATMOSTONE;

check:	classAnnotated | methodAnnotated | fieldAnnotated;

classAnnotated:		CLASS_ANNOTATED '(' NAME ')';
methodAnnotated:	METHOD_ANNOTATED '(' NAME ')';
fieldAnnotated:		FIELD_ANNOTATED '(' NAME ')';

error:	'{' ERROR QSTRING '}' ;

// TOKENS

IMPORT:				'import';
CLAIM:				'claim';
REQUIRE:			'require';
ATMOSTONE:			'atMostOne';
CLASS_ANNOTATED:	'class.annotated';
METHOD_ANNOTATED:	'method.annotated';
FIELD_ANNOTATED:	'field.annotated';
IF:					'if';
AND:				'and';
OR:					'or';
ERROR:				'error';
NAME:   ('a'..'z'|'A'..'Z')+;
IMPORTNAME:	'a'..'z' ('a'..'z'|'.')+ ;
QSTRING:	'"' ( options {greedy=false;} : . )* '"' ;
NEWLINE:'\r'? '\n' ;
WS  :   (' '|'\t')+ {skip();} ;
COMMENT: '#' ( options {greedy=false;} : . )* NEWLINE+ {skip();};

