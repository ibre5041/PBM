package org.ibre5041.parsing;

import java.io.IOException;

import org.ibre5041.parsing.PBMParser.start_rule_return;
import org.ibre5041.parsing.PBMParser.atom_return;
import org.ibre5041.parsing.PBMParser.statement_return;

import org.ibre5041.parsing.utils.ANTLRNoCaseFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

public class test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PBMLexer lex = new PBMLexer(new ANTLRNoCaseFileStream(args[0]));
			
			Token token;
			while(true)
		    {
		        token = lex.nextToken();
		        if(token.getType() == Token.EOF)
		        {
		            break;
		        }
		        if(token.getChannel() == Token.HIDDEN_CHANNEL 
//		        		|| token.getType() == PBMLexer.DELIM
//		        		|| token.getType() == PBMLexer.NEWLINE
		        		)
		        {
		        	continue;
		        }
		        System.out.println("Token:" + token.getType() + "`" + token.getText() + "’");
		    }
			lex.reset();
						
			CommonTokenStream tokens = new CommonTokenStream(lex);
			PBMParser parser = new PBMParser(tokens);

			PBMParser.start_rule_return AST = parser.start_rule();
			//PBMParser.atom_return AST = parser.atom();
			System.out.println(AST.getTree().toString());

			if(parser.getNumberOfSyntaxErrors() != 0)
			{
				System.err.println("failed");				
				System.exit(1);
			} else
				System.err.println("parsed");

		} catch (RecognitionException e) {
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}
}
