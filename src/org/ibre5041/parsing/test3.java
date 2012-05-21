/*******************************************************************************
DESCRIPTION:
		Grammar for Sybase's PowerBuilder PowerScript						
		see: http://manuals.sybase.com/onlinebooks/group-pb/pbg0900e/psref/@Generic__BookTextView/222
				
AUTHOR:
		Ivan.Brezina (ibre5041@ibrezina.net)
DATE:
		DEC 2011
NOTES:
		target language Java
		antlr version 3.4
*******************************************************************************/

package org.ibre5041.parsing;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;

import org.ibre5041.parsing.utils.ANTLRNoCaseFileStream;

public class test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
 
		for (File file: listDirectory(args[0]))
		{
			parse(file.getPath());
		}
	}
	
	private static final List<String> suffixes;
		
	static {
		suffixes = Arrays.asList(".sra", ".srd", ".srf", ".srj", ".srm", ".srq", ".srs", ".sru", ".srw");
	}

	public static List<File> listDirectory(String dir)
	{
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		List<File> retval = new ArrayList<File>();

		for (File file : listOfFiles)
			for (String suffix : suffixes)
				if (file.getPath().endsWith(suffix) == true) {
					retval.add(file);
					break;
				}					
													
		    return retval;
	}
	
	public static void parse(String file) {
		try {
			PBMLexer lex = new PBMLexer(new ANTLRNoCaseFileStream(file));
			CommonTokenStream tokens = new CommonTokenStream(lex);
			PBMParser parser = new PBMParser(tokens);

			PBMParser.start_rule_return AST = parser.start_rule();

			System.err.println(file +": " + parser.getNumberOfSyntaxErrors());
			
			if(parser.getNumberOfSyntaxErrors() != 0)
			{
				System.exit(1);
			}
			
			Tree t = (Tree)AST.getTree();
			CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
			nodes.setTokenStream(tokens);
			
			Object o = nodes.nextElement();
			while(!nodes.isEOF(o))
			{
				o = nodes.nextElement();
				Tree tt = (Tree)o;
				if(tt.getType() == PBMParser.SQLSTATEMENT)
				{
					System.out.println(tt.toStringTree());
				}
			}
			
			//System.out.println(t.toStringTree());
			
		} catch (RecognitionException e) {
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println(e.toString());
		} catch (java.lang.OutOfMemoryError e) {
			System.err.println(file + ":");
			System.err.println(e.toString());
		}
	}
}
