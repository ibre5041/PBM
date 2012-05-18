package org.ibre5041.parsing.window;

import java.util.LinkedList;
import java.util.List;
import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.node.BaseNode;
import org.ibre5041.parsing.node.NodeTypeRegistry;
import org.ibre5041.parsing.visitor.BaseVisitor;

public abstract class AbstractWindow {
	
	public String getFilename() {
		return _filename;
	}
	
	public void setAST(Tree ast) {
		this._ast =  ast;
	}
	
	public Tree getAST() {
		return _ast;
	}
	
	protected void walk(Tree ast, List<BaseVisitor> visitors) {
		walk(ast, visitors, 0);
	}

	//protected abstract void walk(Tree ast, List<BaseVisitor> visitors, int depth);
	public void walk(Tree ast, List<BaseVisitor> visitors, int depth) {
		if (ast == null)
			return;

		if (NodeTypeRegistry.getInstance().isRegistered(ast.getType())) {
			BaseNode n = NodeTypeRegistry.getInstance().create(ast);
			for (BaseVisitor baseVisitor : visitors) {
				n.accept(baseVisitor);
			}
		}

		for (int i = 0; i < ast.getChildCount(); i++) {
			walk(ast.getChild(i), visitors, depth + 1);
		}
	}
		
	private String _filename;
	private Tree _ast;
}
