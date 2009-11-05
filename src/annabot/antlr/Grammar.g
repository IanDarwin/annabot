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
			}
			EOF 				-> stmt
			;

import_stmt:	IMPORT FULLPACKAGENAME ';' {
			System.out.println("IMPORT " + $FULLPACKAGENAME.text);
			};

// Statement, with or without if ... { stmt } around.
stmt:	ifClause '{' verb '{' core error? ';' '}'
		'}'
		| verb '{' core error? ';' '}' 
		;

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
fieldAnnotated:		FIELD_ANNOTATED '('
					FULLPACKAGENAME ( ',' MEMBERNAME )? ')';

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
FULLPACKAGENAME:	'a'..'z' ('a'..'z'|'A'..'Z'|'.'|'*')+ ;
CLASSNAME:   		'A'..'Z' ('a'..'z'|'A'..'Z')+;
MEMBERNAME:			('a'..'z'|'A'..'Z'|'*')+ ;
QSTRING:			'"' ( options {greedy=false;} : . )* '"' ;
fragment NEWLINE:	'\r'? '\n';	// allow for dog files.
WHITESPACE:	(' '|'\t'|NEWLINE)+ {skip();} ;
COMMENT:	'#' ( options {greedy=false;} : . )* NEWLINE+ {
				skip();
			};

