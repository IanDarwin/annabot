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

program:   import_stmt * ( CLAIM SIMPLENAME '{' stmt+ '}' {
	System.out.println("G"+$stmt.tree.toStringTree());} 
	)+ ;

import_stmt:	IMPORT FULLPACKAGENAME ';';

stmt:	ifClause verb '{' core ( ';' | error ) '}' ';' ;

core:	check
		| NOT check
		| ( check OR check )
		| ( check AND check )
		| ( check ',' check)
		;

ifClause: IF '(' check ')';

verb:	REQUIRE | ATMOSTONE;

check:	classAnnotated | methodAnnotated | fieldAnnotated;

classAnnotated:		CLASS_ANNOTATED '(' FULLPACKAGENAME ')';
methodAnnotated:	METHOD_ANNOTATED '(' FULLPACKAGENAME ')';
fieldAnnotated:		FIELD_ANNOTATED '(' FULLPACKAGENAME ')';

error:	'{' ERROR QSTRING '}' ;

// Lexical Tokens

IMPORT:				'import';
CLAIM:				'claim';
REQUIRE:			'require';
ATMOSTONE:			'atMostOne';
CLASS_ANNOTATED:	'class.annotated';
METHOD_ANNOTATED:	'method.annotated';
FIELD_ANNOTATED:	'field.annotated';
IF:					'if';
AND:				'&&';
OR:					'||';
NOT:				'!';
ERROR:				'error';
SIMPLENAME:   		('a'..'z'|'A'..'Z')+;
FULLPACKAGENAME:	'a'..'z' ('a'..'z'|'.'|'*')+ ;
QSTRING:			'"' ( options {greedy=false;} : . )* '"' ;
WS  :   (' '|'\t'|'\n'|'\r')+ {skip();} ;
COMMENT: '#' ( options {greedy=false;} : . )* NEWLINE+ {skip();};

