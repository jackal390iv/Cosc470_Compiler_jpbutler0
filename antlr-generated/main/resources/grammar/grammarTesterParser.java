// $ANTLR 3.5.2 C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g 2015-04-22 14:22:22

  package main.resources.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class grammarTesterParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABC", "DEF", "WS"
	};
	public static final int EOF=-1;
	public static final int ABC=4;
	public static final int DEF=5;
	public static final int WS=6;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public grammarTesterParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public grammarTesterParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return grammarTesterParser.tokenNames; }
	@Override public String getGrammarFileName() { return "C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g"; }



	// $ANTLR start "rule"
	// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:15:1: rule : ( ABC )+ ( DEF )+ ;
	public final void rule() throws RecognitionException {
		try {
			// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:15:5: ( ( ABC )+ ( DEF )+ )
			// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:15:7: ( ABC )+ ( DEF )+
			{
			// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:15:7: ( ABC )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==ABC) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:15:7: ABC
					{
					match(input,ABC,FOLLOW_ABC_in_rule38); 
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:15:12: ( DEF )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==DEF) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// C:\\Users\\Reaper\\Documents\\NetBeansProjects\\Cosc470_Compiler_jpbutler0\\src\\main\\resources\\grammar\\grammarTester.g:15:12: DEF
					{
					match(input,DEF,FOLLOW_DEF_in_rule41); 
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
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
	// $ANTLR end "rule"

	// Delegated rules



	public static final BitSet FOLLOW_ABC_in_rule38 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_DEF_in_rule41 = new BitSet(new long[]{0x0000000000000022L});
}
