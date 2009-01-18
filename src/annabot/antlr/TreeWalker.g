tree grammar TreeWalker;

options {
    tokenVocab=Grammar;
    ASTLabelType=CommonTree;
}

@header {
package annabot.antlr;

import java.util.Map;
import java.util.HashMap;
}

@members {
/** Map variable SIMPLENAME to Integer value */
Map<String,Integer> variables = new HashMap<String,Integer>();
}

program:   import_stmt * ( CLAIM SIMPLENAME '{' stmt+ '}' {System.out.println("W:STMT");} )+ ;

import_stmt: IMPORT IMPORTNAME ';';

stmt:	ifClause verb '{' core error? '}' ';' ;

core:	check
		| NOT check
		| ( check OR check )
		| ( check AND check )
		| ( check ',' check)
		;

ifClause: IF '(' check ')';

verb:	REQUIRE | ATMOSTONE;

check:	classAnnotated | methodAnnotated | fieldAnnotated;

classAnnotated:		CLASS_ANNOTATED '(' SIMPLENAME ')';
methodAnnotated:	METHOD_ANNOTATED '(' SIMPLENAME ')';
fieldAnnotated:		FIELD_ANNOTATED '(' SIMPLENAME ')';

error:	'{' ERROR QSTRING '}' ;
