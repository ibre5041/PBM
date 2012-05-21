package org.ibre5041.parsing.window;

import org.antlr.runtime.tree.Tree;

import com.trolltech.qt.gui.QPainter;

public interface Window
{ 
	public String getFilename();

	public void setAST(Tree ast);
	public Tree getAST();

	public void paint(QPainter painter);	
}
