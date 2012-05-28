package org.ibre5041.parsing.visitor;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.node.WindowPropertyNode;
import org.ibre5041.parsing.window.WindowSubPropertyNode;

public abstract class BaseVisitor {
	public abstract void visit(WindowPropertyNode n);
	public abstract void unvisit(WindowPropertyNode n);
	public abstract void visit(WindowSubPropertyNode n);
	public abstract void unvisit(WindowSubPropertyNode n);
	
	public static String nodeToString(Tree astNode) {
		StringBuffer sb = new StringBuffer(32);
		for (int i = 0; i < astNode.getChildCount(); i++) {
			sb.append(astNode.getChild(i).getText());
		}
		return sb.toString();
	}
}
