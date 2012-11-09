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
import java.util.Collections;
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
 
		List<File> files = listDirectory(args[0]);
		Collections.sort(files);		
		for (File file: listDirectory(args[0]))
		{
			String name = file.getName();
//			if(name.startsWith("r_"))
//			{
				parse(file.getPath());
//			}
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

			System.out.println(file +": " + parser.getNumberOfSyntaxErrors());
			
			if(parser.getNumberOfSyntaxErrors() != 0)
			{
				System.exit(1);
			}
			
			Tree t = (Tree)AST.getTree();
			CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
			nodes.setTokenStream(tokens);
			
			Tree subTree = (Tree)nodes.nextElement();
			while(!nodes.isEOF(subTree))
			{
				subTree = (Tree) nodes.nextElement();
				if(subTree.getType() == PBMParser.SQLSTATEMENT)
				{
					System.out.println(subTree.toStringTree());
				}
				
				if(subTree.getType() == PBMParser.WINDOWSUBPROP && subTree.getChildCount() == 2)
				{
					Tree tPropName = subTree.getChild(0);
					Tree tPropValue = subTree.getChild(1); 
					
					if(! (tPropName.getChildCount() == 1) || !tPropName.getChild(0).toString().equals("retrieve"))
						continue;
					String sPropName = tPropName.getChild(0).getText();
					String sPropValue = tPropValue.getChild(0).getText();
					System.out.println("Propname:" + sPropName + sPropValue);
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
