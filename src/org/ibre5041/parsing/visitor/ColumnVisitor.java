package org.ibre5041.parsing.visitor;

import org.ibre5041.parsing.window.WindowPropertyNode;
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
	public void visit(WindowSubPropertyNode n) {
		StringBuffer sb1 =new StringBuffer(16);
		for(int i=0; i < n.getTree().getChild(0).getChildCount(); i++) {
			sb1.append(n.getTree().getChild(0).getChild(i).getText());
		}			
		String propName = sb1.toString();
		
		StringBuffer sb2 =new StringBuffer(16);
		for(int i=0; i < n.getTree().getChild(0).getChildCount(); i++) {
			sb2.append(n.getTree().getChild(0).getChild(i).getText());
		}			
		String propVal= sb2.toString();
		
		_ref.put(propName, propVal);
	}

	private Column _ref;
}
