// $ANTLR 3.5.2 SQL.g 2015-05-03 15:56:39


  import java.util.ArrayList;
  import java.util.HashMap;
  import java.util.Map;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class SQLParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "CharString", "Float", "Identifier", 
		"Int", "WS", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'", "';'", 
		"'<'", "'='", "'>'", "'and'", "'as'", "'avg'", "'by'", "'count'", "'from'", 
		"'group'", "'max'", "'min'", "'not'", "'or'", "'quit'", "'select'", "'sum'", 
		"'where'"
	};
	public static final int EOF=-1;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int CharString=4;
	public static final int Float=5;
	public static final int Identifier=6;
	public static final int Int=7;
	public static final int WS=8;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public SQLParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public SQLParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return SQLParser.tokenNames; }
	@Override public String getGrammarFileName() { return "SQL.g"; }



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



	// $ANTLR start "parse"
	// SQL.g:51:1: parse returns [boolean value] : ( selectClause fromClause whereClause groupingClause ';' | 'quit' ';' );
	public final boolean parse() throws RecognitionException {
		boolean value = false;


		try {
			// SQL.g:52:2: ( selectClause fromClause whereClause groupingClause ';' | 'quit' ';' )
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==33) ) {
				alt1=1;
			}
			else if ( (LA1_0==32) ) {
				alt1=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// SQL.g:52:4: selectClause fromClause whereClause groupingClause ';'
					{
					pushFollow(FOLLOW_selectClause_in_parse31);
					selectClause();
					state._fsp--;

					pushFollow(FOLLOW_fromClause_in_parse33);
					fromClause();
					state._fsp--;

					pushFollow(FOLLOW_whereClause_in_parse35);
					whereClause();
					state._fsp--;

					pushFollow(FOLLOW_groupingClause_in_parse37);
					groupingClause();
					state._fsp--;

					match(input,17,FOLLOW_17_in_parse39); 
					value = true;
					}
					break;
				case 2 :
					// SQL.g:53:4: 'quit' ';'
					{
					match(input,32,FOLLOW_32_in_parse46); 
					match(input,17,FOLLOW_17_in_parse48); 
					value = false;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "parse"



	// $ANTLR start "selectClause"
	// SQL.g:56:1: selectClause : 'select' exprList ;
	public final void selectClause() throws RecognitionException {
		try {
			// SQL.g:57:2: ( 'select' exprList )
			// SQL.g:57:4: 'select' exprList
			{
			match(input,33,FOLLOW_33_in_selectClause105); 
			pushFollow(FOLLOW_exprList_in_selectClause107);
			exprList();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "selectClause"



	// $ANTLR start "fromClause"
	// SQL.g:60:1: fromClause : 'from' fromList ;
	public final void fromClause() throws RecognitionException {
		try {
			// SQL.g:61:2: ( 'from' fromList )
			// SQL.g:61:4: 'from' fromList
			{
			match(input,26,FOLLOW_26_in_fromClause118); 
			pushFollow(FOLLOW_fromList_in_fromClause120);
			fromList();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "fromClause"



	// $ANTLR start "whereClause"
	// SQL.g:64:1: whereClause : ( 'where' c= cnfExp |);
	public final void whereClause() throws RecognitionException {
		Expression c =null;

		try {
			// SQL.g:65:2: ( 'where' c= cnfExp |)
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==35) ) {
				alt2=1;
			}
			else if ( (LA2_0==17||LA2_0==27) ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// SQL.g:65:4: 'where' c= cnfExp
					{
					match(input,35,FOLLOW_35_in_whereClause131); 
					pushFollow(FOLLOW_cnfExp_in_whereClause135);
					c=cnfExp();
					state._fsp--;

					whereClause = c;
					}
					break;
				case 2 :
					// SQL.g:67:2: 
					{
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "whereClause"



	// $ANTLR start "groupingClause"
	// SQL.g:69:1: groupingClause : ( 'group' 'by' i1= Identifier '.' i2= Identifier ( ',' i3= Identifier '.' i4= Identifier )* |);
	public final void groupingClause() throws RecognitionException {
		Token i1=null;
		Token i2=null;
		Token i3=null;
		Token i4=null;

		try {
			// SQL.g:70:2: ( 'group' 'by' i1= Identifier '.' i2= Identifier ( ',' i3= Identifier '.' i4= Identifier )* |)
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==27) ) {
				alt4=1;
			}
			else if ( (LA4_0==17) ) {
				alt4=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// SQL.g:70:4: 'group' 'by' i1= Identifier '.' i2= Identifier ( ',' i3= Identifier '.' i4= Identifier )*
					{
					match(input,27,FOLLOW_27_in_groupingClause151); 
					match(input,24,FOLLOW_24_in_groupingClause153); 
					i1=(Token)match(input,Identifier,FOLLOW_Identifier_in_groupingClause157); 
					match(input,15,FOLLOW_15_in_groupingClause159); 
					i2=(Token)match(input,Identifier,FOLLOW_Identifier_in_groupingClause163); 
					groupingAtts.add ((i1!=null?i1.getText():null) + "." + (i2!=null?i2.getText():null));
					// SQL.g:71:3: ( ',' i3= Identifier '.' i4= Identifier )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==13) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// SQL.g:71:5: ',' i3= Identifier '.' i4= Identifier
							{
							match(input,13,FOLLOW_13_in_groupingClause171); 
							i3=(Token)match(input,Identifier,FOLLOW_Identifier_in_groupingClause175); 
							match(input,15,FOLLOW_15_in_groupingClause177); 
							i4=(Token)match(input,Identifier,FOLLOW_Identifier_in_groupingClause181); 
							groupingAtts.add ((i3!=null?i3.getText():null) + "." + (i4!=null?i4.getText():null));
							}
							break;

						default :
							break loop3;
						}
					}

					}
					break;
				case 2 :
					// SQL.g:74:2: 
					{
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "groupingClause"



	// $ANTLR start "exprList"
	// SQL.g:76:1: exprList : e1= exp ( ',' e2= exp )* ;
	public final void exprList() throws RecognitionException {
		Expression e1 =null;
		Expression e2 =null;

		try {
			// SQL.g:77:2: (e1= exp ( ',' e2= exp )* )
			// SQL.g:77:4: e1= exp ( ',' e2= exp )*
			{
			pushFollow(FOLLOW_exp_in_exprList204);
			e1=exp();
			state._fsp--;

			exprsToSelect.add (e1);
			// SQL.g:78:3: ( ',' e2= exp )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==13) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// SQL.g:78:5: ',' e2= exp
					{
					match(input,13,FOLLOW_13_in_exprList218); 
					pushFollow(FOLLOW_exp_in_exprList222);
					e2=exp();
					state._fsp--;

					exprsToSelect.add (e2);
					}
					break;

				default :
					break loop5;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "exprList"



	// $ANTLR start "fromList"
	// SQL.g:82:1: fromList : i1= Identifier 'as' i2= Identifier ( ',' i3= Identifier 'as' i4= Identifier )* ;
	public final void fromList() throws RecognitionException {
		Token i1=null;
		Token i2=null;
		Token i3=null;
		Token i4=null;

		try {
			// SQL.g:83:2: (i1= Identifier 'as' i2= Identifier ( ',' i3= Identifier 'as' i4= Identifier )* )
			// SQL.g:83:4: i1= Identifier 'as' i2= Identifier ( ',' i3= Identifier 'as' i4= Identifier )*
			{
			i1=(Token)match(input,Identifier,FOLLOW_Identifier_in_fromList243); 
			match(input,22,FOLLOW_22_in_fromList245); 
			i2=(Token)match(input,Identifier,FOLLOW_Identifier_in_fromList249); 
			tables.put ((i2!=null?i2.getText():null), (i1!=null?i1.getText():null));
			// SQL.g:84:3: ( ',' i3= Identifier 'as' i4= Identifier )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==13) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// SQL.g:84:5: ',' i3= Identifier 'as' i4= Identifier
					{
					match(input,13,FOLLOW_13_in_fromList263); 
					i3=(Token)match(input,Identifier,FOLLOW_Identifier_in_fromList267); 
					match(input,22,FOLLOW_22_in_fromList269); 
					i4=(Token)match(input,Identifier,FOLLOW_Identifier_in_fromList273); 
					tables.put ((i4!=null?i4.getText():null), (i3!=null?i3.getText():null));
					}
					break;

				default :
					break loop6;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "fromList"



	// $ANTLR start "cnfExp"
	// SQL.g:88:1: cnfExp returns [Expression value] : d1= disjunction ( 'and' d2= disjunction )* ;
	public final Expression cnfExp() throws RecognitionException {
		Expression value = null;


		Expression d1 =null;
		Expression d2 =null;

		try {
			// SQL.g:89:2: (d1= disjunction ( 'and' d2= disjunction )* )
			// SQL.g:89:4: d1= disjunction ( 'and' d2= disjunction )*
			{
			pushFollow(FOLLOW_disjunction_in_cnfExp298);
			d1=disjunction();
			state._fsp--;

			value = d1;
			// SQL.g:90:3: ( 'and' d2= disjunction )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==21) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// SQL.g:90:5: 'and' d2= disjunction
					{
					match(input,21,FOLLOW_21_in_cnfExp308); 
					pushFollow(FOLLOW_disjunction_in_cnfExp312);
					d2=disjunction();
					state._fsp--;

					Expression temp = value;
										 value = new Expression ("and");
										 value.setSubexpression (temp, d2);
					}
					break;

				default :
					break loop7;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "cnfExp"



	// $ANTLR start "disjunction"
	// SQL.g:96:1: disjunction returns [Expression value] : '(' c1= comparison ( 'or' c2= comparison )* ')' ;
	public final Expression disjunction() throws RecognitionException {
		Expression value = null;


		Expression c1 =null;
		Expression c2 =null;

		try {
			// SQL.g:97:2: ( '(' c1= comparison ( 'or' c2= comparison )* ')' )
			// SQL.g:97:4: '(' c1= comparison ( 'or' c2= comparison )* ')'
			{
			match(input,9,FOLLOW_9_in_disjunction334); 
			pushFollow(FOLLOW_comparison_in_disjunction338);
			c1=comparison();
			state._fsp--;

			value = c1;
			// SQL.g:98:3: ( 'or' c2= comparison )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==31) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// SQL.g:98:5: 'or' c2= comparison
					{
					match(input,31,FOLLOW_31_in_disjunction347); 
					pushFollow(FOLLOW_comparison_in_disjunction351);
					c2=comparison();
					state._fsp--;

					Expression temp = value;
										 value = new Expression ("or");
										 value.setSubexpression (temp, c2);
					}
					break;

				default :
					break loop8;
				}
			}

			match(input,10,FOLLOW_10_in_disjunction360); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "disjunction"



	// $ANTLR start "comparison"
	// SQL.g:104:1: comparison returns [Expression value] : (e1= addExp ( '=' e2= addExp | '<' e2= addExp | '>' e2= addExp ) | 'not' c= comparison );
	public final Expression comparison() throws RecognitionException {
		Expression value = null;


		Expression e1 =null;
		Expression e2 =null;
		Expression c =null;

		try {
			// SQL.g:105:2: (e1= addExp ( '=' e2= addExp | '<' e2= addExp | '>' e2= addExp ) | 'not' c= comparison )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( ((LA10_0 >= CharString && LA10_0 <= Int)||LA10_0==9||LA10_0==14) ) {
				alt10=1;
			}
			else if ( (LA10_0==30) ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// SQL.g:105:4: e1= addExp ( '=' e2= addExp | '<' e2= addExp | '>' e2= addExp )
					{
					pushFollow(FOLLOW_addExp_in_comparison377);
					e1=addExp();
					state._fsp--;

					value = e1;
					// SQL.g:106:3: ( '=' e2= addExp | '<' e2= addExp | '>' e2= addExp )
					int alt9=3;
					switch ( input.LA(1) ) {
					case 19:
						{
						alt9=1;
						}
						break;
					case 18:
						{
						alt9=2;
						}
						break;
					case 20:
						{
						alt9=3;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 9, 0, input);
						throw nvae;
					}
					switch (alt9) {
						case 1 :
							// SQL.g:106:5: '=' e2= addExp
							{
							match(input,19,FOLLOW_19_in_comparison386); 
							pushFollow(FOLLOW_addExp_in_comparison390);
							e2=addExp();
							state._fsp--;

							Expression temp = value;
											 value = new Expression ("equals");
											 value.setSubexpression (temp, e2);
							}
							break;
						case 2 :
							// SQL.g:109:5: '<' e2= addExp
							{
							match(input,18,FOLLOW_18_in_comparison398); 
							pushFollow(FOLLOW_addExp_in_comparison402);
							e2=addExp();
							state._fsp--;

							Expression temp = value;
											 value = new Expression ("less than");
											 value.setSubexpression (temp, e2);
							}
							break;
						case 3 :
							// SQL.g:112:5: '>' e2= addExp
							{
							match(input,20,FOLLOW_20_in_comparison410); 
							pushFollow(FOLLOW_addExp_in_comparison414);
							e2=addExp();
							state._fsp--;

							Expression temp = value;
											 value = new Expression ("greater than");
											 value.setSubexpression (temp, e2);
							}
							break;

					}

					}
					break;
				case 2 :
					// SQL.g:116:5: 'not' c= comparison
					{
					match(input,30,FOLLOW_30_in_comparison426); 
					pushFollow(FOLLOW_comparison_in_comparison430);
					c=comparison();
					state._fsp--;

					value = new Expression ("not");
									    value.setSubexpression (c);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "comparison"



	// $ANTLR start "exp"
	// SQL.g:120:1: exp returns [Expression value] : ( 'sum' '(' e1= addExp ')' | 'avg' '(' e1= addExp ')' | 'count' '(' e1= addExp ')' | 'min' '(' e1= addExp ')' | 'max' '(' e1= addExp ')' |e1= addExp );
	public final Expression exp() throws RecognitionException {
		Expression value = null;


		Expression e1 =null;

		try {
			// SQL.g:121:2: ( 'sum' '(' e1= addExp ')' | 'avg' '(' e1= addExp ')' | 'count' '(' e1= addExp ')' | 'min' '(' e1= addExp ')' | 'max' '(' e1= addExp ')' |e1= addExp )
			int alt11=6;
			switch ( input.LA(1) ) {
			case 34:
				{
				alt11=1;
				}
				break;
			case 23:
				{
				alt11=2;
				}
				break;
			case 25:
				{
				alt11=3;
				}
				break;
			case 29:
				{
				alt11=4;
				}
				break;
			case 28:
				{
				alt11=5;
				}
				break;
			case CharString:
			case Float:
			case Identifier:
			case Int:
			case 9:
			case 14:
				{
				alt11=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// SQL.g:121:4: 'sum' '(' e1= addExp ')'
					{
					match(input,34,FOLLOW_34_in_exp447); 
					match(input,9,FOLLOW_9_in_exp449); 
					pushFollow(FOLLOW_addExp_in_exp453);
					e1=addExp();
					state._fsp--;

					match(input,10,FOLLOW_10_in_exp455); 
					value = new Expression ("sum");
									         value.setSubexpression (e1);
					}
					break;
				case 2 :
					// SQL.g:123:4: 'avg' '(' e1= addExp ')'
					{
					match(input,23,FOLLOW_23_in_exp462); 
					match(input,9,FOLLOW_9_in_exp464); 
					pushFollow(FOLLOW_addExp_in_exp468);
					e1=addExp();
					state._fsp--;

					match(input,10,FOLLOW_10_in_exp470); 
					value = new Expression ("avg");
									         value.setSubexpression (e1);
					}
					break;
				case 3 :
					// SQL.g:125:4: 'count' '(' e1= addExp ')'
					{
					match(input,25,FOLLOW_25_in_exp477); 
					match(input,9,FOLLOW_9_in_exp479); 
					pushFollow(FOLLOW_addExp_in_exp483);
					e1=addExp();
					state._fsp--;

					match(input,10,FOLLOW_10_in_exp485); 
					value = new Expression ("count");
									         value.setSubexpression (e1);
					}
					break;
				case 4 :
					// SQL.g:127:4: 'min' '(' e1= addExp ')'
					{
					match(input,29,FOLLOW_29_in_exp492); 
					match(input,9,FOLLOW_9_in_exp494); 
					pushFollow(FOLLOW_addExp_in_exp498);
					e1=addExp();
					state._fsp--;

					match(input,10,FOLLOW_10_in_exp500); 
					value = new Expression ("min");
									         value.setSubexpression (e1);
					}
					break;
				case 5 :
					// SQL.g:129:4: 'max' '(' e1= addExp ')'
					{
					match(input,28,FOLLOW_28_in_exp507); 
					match(input,9,FOLLOW_9_in_exp509); 
					pushFollow(FOLLOW_addExp_in_exp513);
					e1=addExp();
					state._fsp--;

					match(input,10,FOLLOW_10_in_exp515); 
					value = new Expression ("max");
									         value.setSubexpression (e1);
					}
					break;
				case 6 :
					// SQL.g:131:4: e1= addExp
					{
					pushFollow(FOLLOW_addExp_in_exp524);
					e1=addExp();
					state._fsp--;

					value = e1;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "exp"



	// $ANTLR start "addExp"
	// SQL.g:134:1: addExp returns [Expression value] : m1= multExp ( '+' m2= multExp | '-' m2= multExp )* ;
	public final Expression addExp() throws RecognitionException {
		Expression value = null;


		Expression m1 =null;
		Expression m2 =null;

		try {
			// SQL.g:135:2: (m1= multExp ( '+' m2= multExp | '-' m2= multExp )* )
			// SQL.g:135:4: m1= multExp ( '+' m2= multExp | '-' m2= multExp )*
			{
			pushFollow(FOLLOW_multExp_in_addExp557);
			m1=multExp();
			state._fsp--;

			value = m1;
			// SQL.g:136:3: ( '+' m2= multExp | '-' m2= multExp )*
			loop12:
			while (true) {
				int alt12=3;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==12) ) {
					alt12=1;
				}
				else if ( (LA12_0==14) ) {
					alt12=2;
				}

				switch (alt12) {
				case 1 :
					// SQL.g:136:5: '+' m2= multExp
					{
					match(input,12,FOLLOW_12_in_addExp574); 
					pushFollow(FOLLOW_multExp_in_addExp578);
					m2=multExp();
					state._fsp--;

					Expression temp = value;
									     value = new Expression ("plus");
									     value.setSubexpression (temp, m2);
					}
					break;
				case 2 :
					// SQL.g:139:5: '-' m2= multExp
					{
					match(input,14,FOLLOW_14_in_addExp589); 
					pushFollow(FOLLOW_multExp_in_addExp593);
					m2=multExp();
					state._fsp--;

					Expression temp = value;
									     value = new Expression ("minus");
									     value.setSubexpression (temp, m2);
					}
					break;

				default :
					break loop12;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "addExp"



	// $ANTLR start "multExp"
	// SQL.g:145:1: multExp returns [Expression value] : m1= atomExp ( '*' m2= atomExp | '/' m2= atomExp )* ;
	public final Expression multExp() throws RecognitionException {
		Expression value = null;


		Expression m1 =null;
		Expression m2 =null;

		try {
			// SQL.g:146:2: (m1= atomExp ( '*' m2= atomExp | '/' m2= atomExp )* )
			// SQL.g:146:4: m1= atomExp ( '*' m2= atomExp | '/' m2= atomExp )*
			{
			pushFollow(FOLLOW_atomExp_in_multExp620);
			m1=atomExp();
			state._fsp--;

			value = m1;
			// SQL.g:147:3: ( '*' m2= atomExp | '/' m2= atomExp )*
			loop13:
			while (true) {
				int alt13=3;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==11) ) {
					alt13=1;
				}
				else if ( (LA13_0==16) ) {
					alt13=2;
				}

				switch (alt13) {
				case 1 :
					// SQL.g:147:5: '*' m2= atomExp
					{
					match(input,11,FOLLOW_11_in_multExp637); 
					pushFollow(FOLLOW_atomExp_in_multExp641);
					m2=atomExp();
					state._fsp--;

					Expression temp = value;
									     value = new Expression ("times");
									     value.setSubexpression (temp, m2);
					}
					break;
				case 2 :
					// SQL.g:150:5: '/' m2= atomExp
					{
					match(input,16,FOLLOW_16_in_multExp652); 
					pushFollow(FOLLOW_atomExp_in_multExp656);
					m2=atomExp();
					state._fsp--;

					Expression temp = value;
									     value = new Expression ("divided by");
									     value.setSubexpression (temp, m2);
					}
					break;

				default :
					break loop13;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "multExp"



	// $ANTLR start "atomExp"
	// SQL.g:156:1: atomExp returns [Expression value] : (n= Int |i1= Identifier '.' i2= Identifier |c= CharString |c= Float | '(' e1= addExp ')' | '-' a1= atomExp );
	public final Expression atomExp() throws RecognitionException {
		Expression value = null;


		Token n=null;
		Token i1=null;
		Token i2=null;
		Token c=null;
		Expression e1 =null;
		Expression a1 =null;

		try {
			// SQL.g:157:2: (n= Int |i1= Identifier '.' i2= Identifier |c= CharString |c= Float | '(' e1= addExp ')' | '-' a1= atomExp )
			int alt14=6;
			switch ( input.LA(1) ) {
			case Int:
				{
				alt14=1;
				}
				break;
			case Identifier:
				{
				alt14=2;
				}
				break;
			case CharString:
				{
				alt14=3;
				}
				break;
			case Float:
				{
				alt14=4;
				}
				break;
			case 9:
				{
				alt14=5;
				}
				break;
			case 14:
				{
				alt14=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}
			switch (alt14) {
				case 1 :
					// SQL.g:157:4: n= Int
					{
					n=(Token)match(input,Int,FOLLOW_Int_in_atomExp683); 
					value = new Expression ("literal int");
									     value.setValue ((n!=null?n.getText():null));
					}
					break;
				case 2 :
					// SQL.g:159:4: i1= Identifier '.' i2= Identifier
					{
					i1=(Token)match(input,Identifier,FOLLOW_Identifier_in_atomExp699); 
					match(input,15,FOLLOW_15_in_atomExp701); 
					i2=(Token)match(input,Identifier,FOLLOW_Identifier_in_atomExp705); 
					value = new Expression ("identifier");
									     			     value.setValue ((i1!=null?i1.getText():null) + "." + (i2!=null?i2.getText():null));
					}
					break;
				case 3 :
					// SQL.g:161:4: c= CharString
					{
					c=(Token)match(input,CharString,FOLLOW_CharString_in_atomExp719); 
					value = new Expression ("literal string");
									     value.setValue ((c!=null?c.getText():null));
					}
					break;
				case 4 :
					// SQL.g:163:4: c= Float
					{
					c=(Token)match(input,Float,FOLLOW_Float_in_atomExp732); 
					value = new Expression ("literal float");
									     value.setValue ((c!=null?c.getText():null));
					}
					break;
				case 5 :
					// SQL.g:165:4: '(' e1= addExp ')'
					{
					match(input,9,FOLLOW_9_in_atomExp744); 
					pushFollow(FOLLOW_addExp_in_atomExp748);
					e1=addExp();
					state._fsp--;

					match(input,10,FOLLOW_10_in_atomExp750); 
					value = e1;
					}
					break;
				case 6 :
					// SQL.g:166:5: '-' a1= atomExp
					{
					match(input,14,FOLLOW_14_in_atomExp760); 
					pushFollow(FOLLOW_atomExp_in_atomExp764);
					a1=atomExp();
					state._fsp--;

					Expression temp = a1;
									     value = new Expression ("unary minus");
									     value.setSubexpression (temp);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "atomExp"

	// Delegated rules



	public static final BitSet FOLLOW_selectClause_in_parse31 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_fromClause_in_parse33 = new BitSet(new long[]{0x0000000808020000L});
	public static final BitSet FOLLOW_whereClause_in_parse35 = new BitSet(new long[]{0x0000000008020000L});
	public static final BitSet FOLLOW_groupingClause_in_parse37 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_parse39 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_32_in_parse46 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_parse48 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_33_in_selectClause105 = new BitSet(new long[]{0x00000004328042F0L});
	public static final BitSet FOLLOW_exprList_in_selectClause107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_fromClause118 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_fromList_in_fromClause120 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_35_in_whereClause131 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_cnfExp_in_whereClause135 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_groupingClause151 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_groupingClause153 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_Identifier_in_groupingClause157 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_groupingClause159 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_Identifier_in_groupingClause163 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_13_in_groupingClause171 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_Identifier_in_groupingClause175 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_groupingClause177 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_Identifier_in_groupingClause181 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_exp_in_exprList204 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_13_in_exprList218 = new BitSet(new long[]{0x00000004328042F0L});
	public static final BitSet FOLLOW_exp_in_exprList222 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_Identifier_in_fromList243 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_fromList245 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_Identifier_in_fromList249 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_13_in_fromList263 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_Identifier_in_fromList267 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_fromList269 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_Identifier_in_fromList273 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_disjunction_in_cnfExp298 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_21_in_cnfExp308 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_disjunction_in_cnfExp312 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_9_in_disjunction334 = new BitSet(new long[]{0x00000000400042F0L});
	public static final BitSet FOLLOW_comparison_in_disjunction338 = new BitSet(new long[]{0x0000000080000400L});
	public static final BitSet FOLLOW_31_in_disjunction347 = new BitSet(new long[]{0x00000000400042F0L});
	public static final BitSet FOLLOW_comparison_in_disjunction351 = new BitSet(new long[]{0x0000000080000400L});
	public static final BitSet FOLLOW_10_in_disjunction360 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_addExp_in_comparison377 = new BitSet(new long[]{0x00000000001C0000L});
	public static final BitSet FOLLOW_19_in_comparison386 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_addExp_in_comparison390 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_18_in_comparison398 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_addExp_in_comparison402 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_20_in_comparison410 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_addExp_in_comparison414 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_30_in_comparison426 = new BitSet(new long[]{0x00000000400042F0L});
	public static final BitSet FOLLOW_comparison_in_comparison430 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_34_in_exp447 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_exp449 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_addExp_in_exp453 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_exp455 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_23_in_exp462 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_exp464 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_addExp_in_exp468 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_exp470 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_25_in_exp477 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_exp479 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_addExp_in_exp483 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_exp485 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_29_in_exp492 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_exp494 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_addExp_in_exp498 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_exp500 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_exp507 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_exp509 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_addExp_in_exp513 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_exp515 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_addExp_in_exp524 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExp_in_addExp557 = new BitSet(new long[]{0x0000000000005002L});
	public static final BitSet FOLLOW_12_in_addExp574 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_multExp_in_addExp578 = new BitSet(new long[]{0x0000000000005002L});
	public static final BitSet FOLLOW_14_in_addExp589 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_multExp_in_addExp593 = new BitSet(new long[]{0x0000000000005002L});
	public static final BitSet FOLLOW_atomExp_in_multExp620 = new BitSet(new long[]{0x0000000000010802L});
	public static final BitSet FOLLOW_11_in_multExp637 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_atomExp_in_multExp641 = new BitSet(new long[]{0x0000000000010802L});
	public static final BitSet FOLLOW_16_in_multExp652 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_atomExp_in_multExp656 = new BitSet(new long[]{0x0000000000010802L});
	public static final BitSet FOLLOW_Int_in_atomExp683 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_atomExp699 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_atomExp701 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_Identifier_in_atomExp705 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CharString_in_atomExp719 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Float_in_atomExp732 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_atomExp744 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_addExp_in_atomExp748 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_atomExp750 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_14_in_atomExp760 = new BitSet(new long[]{0x00000000000042F0L});
	public static final BitSet FOLLOW_atomExp_in_atomExp764 = new BitSet(new long[]{0x0000000000000002L});
}
