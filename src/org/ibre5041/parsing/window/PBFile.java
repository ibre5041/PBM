package org.ibre5041.parsing.window;

import java.io.File;

import org.antlr.runtime.tree.Tree;
import com.trolltech.qt.gui.QPainter;

public interface PBFile
{ 
	public File getFilename();
	public void setFilename(File filename);

	public void setAST(Tree ast);
	public Tree getAST();

	public void paint(QPainter painter);	
}
