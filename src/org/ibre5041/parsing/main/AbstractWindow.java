package org.ibre5041.parsing.main;

import org.antlr.runtime.tree.Tree;

public class AbstractWindow {
	
	public String getFilename() {
		return _filename;
	}
	
	public void setAST(Tree ast) {
		this._ast =  ast;
	}
	
	public Tree getAST() {
		return _ast;
	}
	
	private String _filename;
	private Tree _ast;
}
