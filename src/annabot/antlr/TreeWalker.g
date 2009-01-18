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
/** Map variable name to Integer value */
Map<String,Integer> variables = new HashMap<String,Integer>();
}

program:   import_stmt * ( CLAIM NAME '{' stmt+ '}' {System.out.println("W:STMT");} )+ ;

import_stmt: IMPORT IMPORTNAME ';';

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
