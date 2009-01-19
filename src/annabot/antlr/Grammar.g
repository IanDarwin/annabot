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
			CLAIM CLASSNAME '{'
				stmt+ 
			'}' {
				System.out.println(
				"Grammar: "+$stmt.tree.toStringTree()); 
			};

import_stmt:	IMPORT FULLPACKAGENAME ';';

stmt:	ifClause* '{' verb
			'{' core ( ';' | error ) '}' 
		'}' ';' ;

verb:	REQUIRE | ATMOSTONE;

core:	check
		| NOT check
		| ( check OR check )
		| ( check AND check )
		| ( check ',' check)
		;

ifClause: IF '(' check ')';

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
FULLPACKAGENAME:	'a'..'z' ('a'..'z'|'A'.'Z'|'.'|'*')+ ;
CLASSNAME:   		'A'..'Z' ('a'..'z'|'A'..'Z')+;
QSTRING:			'"' ( options {greedy=false;} : . )* '"' ;
NEWLINE:	'\r'? '\n';	// allow for dog files.
WHITESPACE:	(' '|'\t'|NEWLINE)+ {skip();} ;
COMMENT:	'#' ( options {greedy=false;} : . )* NEWLINE+ {
				skip();
			};

