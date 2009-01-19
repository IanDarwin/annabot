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
/** Map variable CLASSNAME to Integer value */
Map<String,Integer> variables = new HashMap<String,Integer>();
}

program:	
			stmt+ {
				System.out.println("TreeWalker:STMT"); 
			};

import_stmt:	IMPORT FULLPACKAGENAME ';';

stmt:	ifClause* verb '{' core ( ';' | error ) '}' ';' ;

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
