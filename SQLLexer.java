// $ANTLR 3.5.2 SQL.g 2015-05-03 15:56:39

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class SQLLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public SQLLexer() {} 
	public SQLLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public SQLLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "SQL.g"; }

	// $ANTLR start "T__9"
	public final void mT__9() throws RecognitionException {
		try {
			int _type = T__9;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:2:6: ( '(' )
			// SQL.g:2:8: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__9"

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:3:7: ( ')' )
			// SQL.g:3:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:4:7: ( '*' )
			// SQL.g:4:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:5:7: ( '+' )
			// SQL.g:5:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:6:7: ( ',' )
			// SQL.g:6:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:7:7: ( '-' )
			// SQL.g:7:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:8:7: ( '.' )
			// SQL.g:8:9: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:9:7: ( '/' )
			// SQL.g:9:9: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:10:7: ( ';' )
			// SQL.g:10:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:11:7: ( '<' )
			// SQL.g:11:9: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:12:7: ( '=' )
			// SQL.g:12:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:13:7: ( '>' )
			// SQL.g:13:9: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:14:7: ( 'and' )
			// SQL.g:14:9: 'and'
			{
			match("and"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:15:7: ( 'as' )
			// SQL.g:15:9: 'as'
			{
			match("as"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:16:7: ( 'avg' )
			// SQL.g:16:9: 'avg'
			{
			match("avg"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:17:7: ( 'by' )
			// SQL.g:17:9: 'by'
			{
			match("by"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:18:7: ( 'count' )
			// SQL.g:18:9: 'count'
			{
			match("count"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:19:7: ( 'from' )
			// SQL.g:19:9: 'from'
			{
			match("from"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:20:7: ( 'group' )
			// SQL.g:20:9: 'group'
			{
			match("group"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:21:7: ( 'max' )
			// SQL.g:21:9: 'max'
			{
			match("max"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:22:7: ( 'min' )
			// SQL.g:22:9: 'min'
			{
			match("min"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:23:7: ( 'not' )
			// SQL.g:23:9: 'not'
			{
			match("not"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:24:7: ( 'or' )
			// SQL.g:24:9: 'or'
			{
			match("or"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:25:7: ( 'quit' )
			// SQL.g:25:9: 'quit'
			{
			match("quit"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:26:7: ( 'select' )
			// SQL.g:26:9: 'select'
			{
			match("select"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:27:7: ( 'sum' )
			// SQL.g:27:9: 'sum'
			{
			match("sum"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:28:7: ( 'where' )
			// SQL.g:28:9: 'where'
			{
			match("where"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "Float"
	public final void mFloat() throws RecognitionException {
		try {
			int _type = Float;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:172:2: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )+ ( 'e' ( '-' )? ( '0' .. '9' )+ )? )
			// SQL.g:172:4: ( '0' .. '9' )+ '.' ( '0' .. '9' )+ ( 'e' ( '-' )? ( '0' .. '9' )+ )?
			{
			// SQL.g:172:4: ( '0' .. '9' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// SQL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			match('.'); 
			// SQL.g:172:20: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// SQL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			// SQL.g:172:32: ( 'e' ( '-' )? ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='e') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// SQL.g:172:33: 'e' ( '-' )? ( '0' .. '9' )+
					{
					match('e'); 
					// SQL.g:172:37: ( '-' )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0=='-') ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// SQL.g:172:37: '-'
							{
							match('-'); 
							}
							break;

					}

					// SQL.g:172:42: ( '0' .. '9' )+
					int cnt4=0;
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// SQL.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt4 >= 1 ) break loop4;
							EarlyExitException eee = new EarlyExitException(4, input);
							throw eee;
						}
						cnt4++;
					}

					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Float"

	// $ANTLR start "Identifier"
	public final void mIdentifier() throws RecognitionException {
		try {
			int _type = Identifier;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:176:2: ( ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' ) ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' | ( '0' .. '9' ) )* )
			// SQL.g:176:4: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' ) ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' | ( '0' .. '9' ) )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// SQL.g:176:36: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' | ( '0' .. '9' ) )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( ((LA6_0 >= '0' && LA6_0 <= '9')||(LA6_0 >= 'A' && LA6_0 <= 'Z')||LA6_0=='_'||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// SQL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop6;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Identifier"

	// $ANTLR start "Int"
	public final void mInt() throws RecognitionException {
		try {
			int _type = Int;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:180:2: ( ( '0' .. '9' )+ )
			// SQL.g:180:4: ( '0' .. '9' )+
			{
			// SQL.g:180:4: ( '0' .. '9' )+
			int cnt7=0;
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// SQL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt7 >= 1 ) break loop7;
					EarlyExitException eee = new EarlyExitException(7, input);
					throw eee;
				}
				cnt7++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Int"

	// $ANTLR start "CharString"
	public final void mCharString() throws RecognitionException {
		try {
			int _type = CharString;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:184:2: ( '\"' ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | ' ' | '-' | '(' | ')' | ( '0' .. '9' ) )* '\"' )
			// SQL.g:184:4: '\"' ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | ' ' | '-' | '(' | ')' | ( '0' .. '9' ) )* '\"'
			{
			match('\"'); 
			// SQL.g:184:8: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | ' ' | '-' | '(' | ')' | ( '0' .. '9' ) )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==' '||(LA8_0 >= '(' && LA8_0 <= ')')||LA8_0=='-'||(LA8_0 >= '0' && LA8_0 <= '9')||(LA8_0 >= 'A' && LA8_0 <= 'Z')||(LA8_0 >= 'a' && LA8_0 <= 'z')) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// SQL.g:
					{
					if ( input.LA(1)==' '||(input.LA(1) >= '(' && input.LA(1) <= ')')||input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop8;
				}
			}

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CharString"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQL.g:188:2: ( ( ' ' | '\\t' | '\\r' | '\\n' )* )
			// SQL.g:188:4: ( ' ' | '\\t' | '\\r' | '\\n' )*
			{
			// SQL.g:188:4: ( ' ' | '\\t' | '\\r' | '\\n' )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( ((LA9_0 >= '\t' && LA9_0 <= '\n')||LA9_0=='\r'||LA9_0==' ') ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// SQL.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop9;
				}
			}

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// SQL.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | Float | Identifier | Int | CharString | WS )
		int alt10=32;
		alt10 = dfa10.predict(input);
		switch (alt10) {
			case 1 :
				// SQL.g:1:10: T__9
				{
				mT__9(); 

				}
				break;
			case 2 :
				// SQL.g:1:15: T__10
				{
				mT__10(); 

				}
				break;
			case 3 :
				// SQL.g:1:21: T__11
				{
				mT__11(); 

				}
				break;
			case 4 :
				// SQL.g:1:27: T__12
				{
				mT__12(); 

				}
				break;
			case 5 :
				// SQL.g:1:33: T__13
				{
				mT__13(); 

				}
				break;
			case 6 :
				// SQL.g:1:39: T__14
				{
				mT__14(); 

				}
				break;
			case 7 :
				// SQL.g:1:45: T__15
				{
				mT__15(); 

				}
				break;
			case 8 :
				// SQL.g:1:51: T__16
				{
				mT__16(); 

				}
				break;
			case 9 :
				// SQL.g:1:57: T__17
				{
				mT__17(); 

				}
				break;
			case 10 :
				// SQL.g:1:63: T__18
				{
				mT__18(); 

				}
				break;
			case 11 :
				// SQL.g:1:69: T__19
				{
				mT__19(); 

				}
				break;
			case 12 :
				// SQL.g:1:75: T__20
				{
				mT__20(); 

				}
				break;
			case 13 :
				// SQL.g:1:81: T__21
				{
				mT__21(); 

				}
				break;
			case 14 :
				// SQL.g:1:87: T__22
				{
				mT__22(); 

				}
				break;
			case 15 :
				// SQL.g:1:93: T__23
				{
				mT__23(); 

				}
				break;
			case 16 :
				// SQL.g:1:99: T__24
				{
				mT__24(); 

				}
				break;
			case 17 :
				// SQL.g:1:105: T__25
				{
				mT__25(); 

				}
				break;
			case 18 :
				// SQL.g:1:111: T__26
				{
				mT__26(); 

				}
				break;
			case 19 :
				// SQL.g:1:117: T__27
				{
				mT__27(); 

				}
				break;
			case 20 :
				// SQL.g:1:123: T__28
				{
				mT__28(); 

				}
				break;
			case 21 :
				// SQL.g:1:129: T__29
				{
				mT__29(); 

				}
				break;
			case 22 :
				// SQL.g:1:135: T__30
				{
				mT__30(); 

				}
				break;
			case 23 :
				// SQL.g:1:141: T__31
				{
				mT__31(); 

				}
				break;
			case 24 :
				// SQL.g:1:147: T__32
				{
				mT__32(); 

				}
				break;
			case 25 :
				// SQL.g:1:153: T__33
				{
				mT__33(); 

				}
				break;
			case 26 :
				// SQL.g:1:159: T__34
				{
				mT__34(); 

				}
				break;
			case 27 :
				// SQL.g:1:165: T__35
				{
				mT__35(); 

				}
				break;
			case 28 :
				// SQL.g:1:171: Float
				{
				mFloat(); 

				}
				break;
			case 29 :
				// SQL.g:1:177: Identifier
				{
				mIdentifier(); 

				}
				break;
			case 30 :
				// SQL.g:1:188: Int
				{
				mInt(); 

				}
				break;
			case 31 :
				// SQL.g:1:192: CharString
				{
				mCharString(); 

				}
				break;
			case 32 :
				// SQL.g:1:203: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA10 dfa10 = new DFA10(this);
	static final String DFA10_eotS =
		"\1\33\14\uffff\13\31\1\54\3\uffff\1\31\1\56\1\31\1\60\6\31\1\67\4\31\2"+
		"\uffff\1\74\1\uffff\1\75\1\uffff\3\31\1\101\1\102\1\103\1\uffff\2\31\1"+
		"\106\1\31\2\uffff\1\31\1\111\1\31\3\uffff\1\113\1\31\1\uffff\1\31\1\116"+
		"\1\uffff\1\117\1\uffff\1\31\1\121\2\uffff\1\122\2\uffff";
	static final String DFA10_eofS =
		"\123\uffff";
	static final String DFA10_minS =
		"\1\42\14\uffff\1\156\1\171\1\157\2\162\1\141\1\157\1\162\1\165\1\145\1"+
		"\150\1\56\3\uffff\1\144\1\60\1\147\1\60\1\165\2\157\1\170\1\156\1\164"+
		"\1\60\1\151\1\154\1\155\1\145\2\uffff\1\60\1\uffff\1\60\1\uffff\1\156"+
		"\1\155\1\165\3\60\1\uffff\1\164\1\145\1\60\1\162\2\uffff\1\164\1\60\1"+
		"\160\3\uffff\1\60\1\143\1\uffff\1\145\1\60\1\uffff\1\60\1\uffff\1\164"+
		"\1\60\2\uffff\1\60\2\uffff";
	static final String DFA10_maxS =
		"\1\172\14\uffff\1\166\1\171\1\157\2\162\1\151\1\157\1\162\2\165\1\150"+
		"\1\71\3\uffff\1\144\1\172\1\147\1\172\1\165\2\157\1\170\1\156\1\164\1"+
		"\172\1\151\1\154\1\155\1\145\2\uffff\1\172\1\uffff\1\172\1\uffff\1\156"+
		"\1\155\1\165\3\172\1\uffff\1\164\1\145\1\172\1\162\2\uffff\1\164\1\172"+
		"\1\160\3\uffff\1\172\1\143\1\uffff\1\145\1\172\1\uffff\1\172\1\uffff\1"+
		"\164\1\172\2\uffff\1\172\2\uffff";
	static final String DFA10_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\14\uffff"+
		"\1\35\1\37\1\40\17\uffff\1\34\1\36\1\uffff\1\16\1\uffff\1\20\6\uffff\1"+
		"\27\4\uffff\1\15\1\17\3\uffff\1\24\1\25\1\26\2\uffff\1\32\2\uffff\1\22"+
		"\1\uffff\1\30\2\uffff\1\21\1\23\1\uffff\1\33\1\31";
	static final String DFA10_specialS =
		"\123\uffff}>";
	static final String[] DFA10_transitionS = {
			"\1\32\5\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\12\30\1\uffff\1\11\1\12"+
			"\1\13\1\14\2\uffff\32\31\4\uffff\1\31\1\uffff\1\15\1\16\1\17\2\31\1\20"+
			"\1\21\5\31\1\22\1\23\1\24\1\31\1\25\1\31\1\26\3\31\1\27\3\31",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\34\4\uffff\1\35\2\uffff\1\36",
			"\1\37",
			"\1\40",
			"\1\41",
			"\1\42",
			"\1\43\7\uffff\1\44",
			"\1\45",
			"\1\46",
			"\1\47",
			"\1\50\17\uffff\1\51",
			"\1\52",
			"\1\53\1\uffff\12\30",
			"",
			"",
			"",
			"\1\55",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\57",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\61",
			"\1\62",
			"\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\70",
			"\1\71",
			"\1\72",
			"\1\73",
			"",
			"",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			"\1\76",
			"\1\77",
			"\1\100",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			"\1\104",
			"\1\105",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\107",
			"",
			"",
			"\1\110",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\112",
			"",
			"",
			"",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\114",
			"",
			"\1\115",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			"\1\120",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			"",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			""
	};

	static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
	static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
	static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
	static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
	static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
	static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
	static final short[][] DFA10_transition;

	static {
		int numStates = DFA10_transitionS.length;
		DFA10_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
		}
	}

	protected class DFA10 extends DFA {

		public DFA10(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 10;
			this.eot = DFA10_eot;
			this.eof = DFA10_eof;
			this.min = DFA10_min;
			this.max = DFA10_max;
			this.accept = DFA10_accept;
			this.special = DFA10_special;
			this.transition = DFA10_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | Float | Identifier | Int | CharString | WS );";
		}
	}

}
