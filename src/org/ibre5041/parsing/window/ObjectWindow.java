package org.ibre5041.parsing.window;

import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.visitor.BaseVisitor;
import org.ibre5041.parsing.visitor.PropertyTableVisitor;

import com.trolltech.qt.gui.QPainter;

public class ObjectWindow extends AbstractWindow implements Window {

	@Override
	public void setAST(Tree ast) {
		super.setAST(ast);
		// Visitors this window cares about
		List<BaseVisitor> visitors = new LinkedList<BaseVisitor>();
//		BaseVisitor v = new PropertyTableVisitor(this);
//		visitors.add(v);
		walk(ast, visitors);
	}

	@Override
	public void paint(QPainter painter) {
		// TODO Auto-generated method stub
		
	}
}
