// $ANTLR 3.5.2 C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g 2015-04-22 14:22:23

  package main.resources.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class grammarTesterLexer extends Lexer {
	public static final int EOF=-1;
	public static final int ABC=4;
	public static final int DEF=5;
	public static final int WS=6;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public grammarTesterLexer() {} 
	public grammarTesterLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public grammarTesterLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g"; }

	// $ANTLR start "ABC"
	public final void mABC() throws RecognitionException {
		try {
			int _type = ABC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:17:4: ( 'a' .. 'c' )
			// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:
			{
			if ( (input.LA(1) >= 'a' && input.LA(1) <= 'c') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ABC"

	// $ANTLR start "DEF"
	public final void mDEF() throws RecognitionException {
		try {
			int _type = DEF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:18:4: ( 'd' .. 'f' )
			// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:
			{
			if ( (input.LA(1) >= 'd' && input.LA(1) <= 'f') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DEF"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:20:4: ( ( ' ' )+ | ( '\\n' )+ )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==' ') ) {
				alt3=1;
			}
			else if ( (LA3_0=='\n') ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:20:6: ( ' ' )+
					{
					// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:20:6: ( ' ' )+
					int cnt1=0;
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( (LA1_0==' ') ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:20:6: ' '
							{
							match(' '); 
							}
							break;

						default :
							if ( cnt1 >= 1 ) break loop1;
							EarlyExitException eee = new EarlyExitException(1, input);
							throw eee;
						}
						cnt1++;
					}

					}
					break;
				case 2 :
					// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:20:11: ( '\\n' )+
					{
					// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:20:11: ( '\\n' )+
					int cnt2=0;
					loop2:
					while (true) {
						int alt2=2;
						int LA2_0 = input.LA(1);
						if ( (LA2_0=='\n') ) {
							alt2=1;
						}

						switch (alt2) {
						case 1 :
							// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:20:11: '\\n'
							{
							match('\n'); 
							}
							break;

						default :
							if ( cnt2 >= 1 ) break loop2;
							EarlyExitException eee = new EarlyExitException(2, input);
							throw eee;
						}
						cnt2++;
					}

					 skip(); 
					}
					break;

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
		// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:1:8: ( ABC | DEF | WS )
		int alt4=3;
		switch ( input.LA(1) ) {
		case 'a':
		case 'b':
		case 'c':
			{
			alt4=1;
			}
			break;
		case 'd':
		case 'e':
		case 'f':
			{
			alt4=2;
			}
			break;
		case '\n':
		case ' ':
			{
			alt4=3;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 4, 0, input);
			throw nvae;
		}
		switch (alt4) {
			case 1 :
				// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:1:10: ABC
				{
				mABC(); 

				}
				break;
			case 2 :
				// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:1:14: DEF
				{
				mDEF(); 

				}
				break;
			case 3 :
				// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:1:18: WS
				{
				mWS(); 

				}
				break;

		}
	}



}
