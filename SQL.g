
grammar SQL;

@header {

  import java.util.ArrayList;
  import java.util.HashMap;
  import java.util.Map;

}

@parser::members {

  // this is the list of expressions specified in the query's SELECT clause
  // that we need to return to the user
  private ArrayList <Expression> exprsToSelect = new ArrayList <Expression> ();

  // this is a map where for each entry in the map, the key is an identifier
  // that was found in the FROM clause, and the value is a table name that
  // was found there... for example, if we have FROM myTable AS a, then the pair
  // ("a", "myTable") would be put into this map
  private Map <String, String> tables = new HashMap <String, String> ();

  // this is the boolean expression in the WHERE clause
  private Expression whereClause = null;

  // this is the attribute (or attributes) that we are grouping by
  private ArrayList <String> groupingAtts = new ArrayList <String> ();

  // returns the list of expressions from the SELECT statement
  public ArrayList <Expression> getSELECT () {
    return exprsToSelect;
  }

  // returns the map representing the FROM clause
  public Map <String, String> getFROM () {
    return tables;
  }

  // returns the expression in the WHERE clause
  public Expression getWHERE () {
    return whereClause;
  }

  // returns the grouping attributes
  public ArrayList <String> getGROUPBY () {
    return groupingAtts;
  }
}

parse returns [boolean value]
	:	selectClause fromClause whereClause groupingClause ';' {$value = true;}
	|	'quit' ';'                                             {$value = false;}
	;

selectClause
	:	'select' exprList
	;

fromClause
	:	'from' fromList
	;

whereClause
	:	'where' c=cnfExp {whereClause = $c.value;}
	|
	;

groupingClause
	:	'group' 'by' i1=Identifier '.' i2=Identifier {groupingAtts.add ($i1.text + "." + $i2.text);}
		( ',' i3=Identifier '.' i4=Identifier {groupingAtts.add ($i3.text + "." + $i4.text);}
		)*
	|
	;

exprList
	:	e1=exp       {exprsToSelect.add ($e1.value);}
		( ',' e2=exp {exprsToSelect.add ($e2.value);}
		)*
	;	

fromList
	:	i1=Identifier 'as' i2=Identifier       {tables.put ($i2.text, $i1.text);}
		( ',' i3=Identifier 'as' i4=Identifier {tables.put ($i4.text, $i3.text);}
		)*
	;

cnfExp returns [Expression value] 
	:	d1=disjunction 		{$value = d1;}
		( 'and' d2=disjunction	{Expression temp = value;
					 $value = new Expression ("and");
					 $value.setSubexpression (temp, $d2.value);}
		)*
	;

disjunction returns [Expression value]
	:	'(' c1=comparison 	{$value = c1;}
		( 'or' c2=comparison	{Expression temp = value;
					 $value = new Expression ("or");
					 $value.setSubexpression (temp, $c2.value);}
		)* ')'
	;

comparison returns [Expression value]
	:	e1=addExp 	{$value = e1;}
		( '=' e2=addExp {Expression temp = $value;
				 $value = new Expression ("equals");
				 $value.setSubexpression (temp, $e2.value);}
		| '<' e2=addExp {Expression temp = $value;
				 $value = new Expression ("less than");
				 $value.setSubexpression (temp, $e2.value);}
		| '>' e2=addExp {Expression temp = $value;
				 $value = new Expression ("greater than");
				 $value.setSubexpression (temp, $e2.value);}
		)
	| 	'not' c=comparison {$value = new Expression ("not");
				    $value.setSubexpression ($c.value);}
	;

exp returns [Expression value]
	:	'sum' '(' e1=addExp ')' {$value = new Expression ("sum");
				         $value.setSubexpression ($e1.value);}
	|	'avg' '(' e1=addExp ')' {$value = new Expression ("avg");
				         $value.setSubexpression ($e1.value);}
	|	'count' '(' e1=addExp ')' {$value = new Expression ("count");
				         $value.setSubexpression ($e1.value);}
	|	'min' '(' e1=addExp ')' {$value = new Expression ("min");
				         $value.setSubexpression ($e1.value);}
	|	'max' '(' e1=addExp ')' {$value = new Expression ("max");
				         $value.setSubexpression ($e1.value);}
	|	e1=addExp               {$value = $e1.value;}
	;

addExp returns [Expression value]
	:	m1=multExp          {$value = $m1.value;}
		( '+' m2=multExp    {Expression temp = $value;
				     $value = new Expression ("plus");
				     $value.setSubexpression (temp, $m2.value);}
		| '-' m2=multExp    {Expression temp = $value;
				     $value = new Expression ("minus");
				     $value.setSubexpression (temp, $m2.value);}
		)*
	;

multExp returns [Expression value]
	:	m1=atomExp          {$value = $m1.value;}
		( '*' m2=atomExp    {Expression temp = $value;
				     $value = new Expression ("times");
				     $value.setSubexpression (temp, $m2.value);}
		| '/' m2=atomExp    {Expression temp = $value;
				     $value = new Expression ("divided by");
				     $value.setSubexpression (temp, $m2.value);}
		)*
	;

atomExp returns [Expression value]
	:	n=Int   	    {$value = new Expression ("literal int");
				     $value.setValue ($n.text);}
	|	i1=Identifier '.' i2=Identifier 	    {$value = new Expression ("identifier");
				     			     $value.setValue ($i1.text + "." + $i2.text);}
	|	c=CharString	    {$value = new Expression ("literal string");
				     $value.setValue ($c.text);}
	|	c=Float		    {$value = new Expression ("literal float");
				     $value.setValue ($c.text);}
	|	'(' e1=addExp ')'   {$value = $e1.value;}
	| 	'-' a1=atomExp	    {Expression temp = $a1.value;
				     $value = new Expression ("unary minus");
				     $value.setSubexpression (temp);}
	;

Float
	:	('0'..'9')+ '.' ('0'..'9')+ ('e' '-'? ('0'..'9')+)?
	;

Identifier
	:	(('a'..'z') | ('A'..'Z') | '_') (('a'..'z') | ('A'..'Z') | '_' | ('0'..'9'))*
	;
 
Int
	:	('0'..'9')+
	;

CharString
	:	'"' (('a'..'z') | ('A'..'Z') | ' ' | '-' | '(' | ')' | ('0'..'9'))* '"'	
	;

WS
	:	(' ' | '\t' | '\r'| '\n')* {$channel=HIDDEN;}
	;
