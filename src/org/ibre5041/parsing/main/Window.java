package org.ibre5041.parsing.main;

import org.antlr.runtime.tree.Tree;

public interface Window
{ 
	public String getFilename();

	public void setAST(Tree ast);
	public Tree getAST();
}