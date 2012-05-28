package org.ibre5041.parsing.visitor;

import org.ibre5041.parsing.node.WindowPropertyNode;
import org.ibre5041.parsing.window.WindowSubPropertyNode;
import org.ibre5041.parsing.window.util.Column;

public class ColumnVisitor extends BaseVisitor {

	public ColumnVisitor(Column ref) {
		this._ref = ref;
	}

	@Override
	public void visit(WindowPropertyNode n) {	
	}

	@Override
	public void unvisit(WindowPropertyNode n) {	
	}

	@Override
	public void visit(WindowSubPropertyNode n) {
		String propName = nodeToString(n.getTree().getChild(0));
		String propVal = nodeToString(n.getTree().getChild(1));
		
		_ref.put(propName, propVal);
	}

	@Override
	public void unvisit(WindowSubPropertyNode n) {
	}
	
	private Column _ref;
}
