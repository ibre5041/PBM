package org.ibre5041.parsing.window.util;

import java.util.HashMap;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.PBMParser;

public class PropertyTable extends HashMap<String, String> {
	private static final long serialVersionUID = 6522402886786349437L;

	public PropertyTable(String propName) {
		this._name = propName;
	}

	@Override
	public String toString() {
		StringBuffer b = new StringBuffer(128);
		b.append("[").append(this.getClass().getName()).append(" ");
		 
		for (java.util.Map.Entry<String, String> entry : this.entrySet()) {
		       b.append(entry.getKey());
		       b.append("=");
		       b.append(entry.getValue());
		       b.append(" ");
		   }
		b.append("]");
		return b.toString();
	}
	
	
	String _name;

//	public void walk(Tree ast, int depth) {
//		if (ast == null)
//			return;
//		
//		switch(ast.getType())
//		{
//		case PBMParser.WINDOWSUBPROP:
//			Tree name = ast.getChild(0);
//			Tree value = ast.getChild(1);
//			if ( name.getType() != PBMParser.WINDOWSUBPROPNAME )
//				throw new RuntimeException("Invalid token(" + name.getText() + ") under (" + _name + ")");
//			if ( value.getType() != PBMParser.WINDOWSUBPROPVAL)
//				throw new RuntimeException("Invalid token(" + value.getText() + ") under (" + _name + ")");
//			this.put(name.getChild(0).getText(), value.getChild(0).getText());			
//			break;
//		}			
//		for ( int i = 0; i < ast.getChildCount(); i++ ) { 
//			walk(ast.getChild(i), depth+1);
//		}
//	}
}
