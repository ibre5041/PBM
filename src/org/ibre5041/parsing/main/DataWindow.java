package org.ibre5041.parsing.main;

import java.util.HashMap;
import org.antlr.runtime.tree.Tree;

public class DataWindow extends AbstractWindow implements Window {
		
	@Override
	public void setAST(Tree ast) {
		super.setAST(ast);
		walk(ast);		
	}
	
	void walk(Tree ast) {
		walk(ast, 0);
	}

	void walk(Tree ast, int depth) {
		if (ast == null)
			return;
		StringBuffer sb = new StringBuffer(depth);
		for ( int i = 0; i < depth; i++ )
			sb = sb.append("   ");
		for ( int i = 0; i < ast.getChildCount(); i++ ) {
			System.out.println(sb.toString() + ast.getChild(i).toString() + " " + ast.getChild(i).getType()); 
			walk(ast.getChild(i), depth+1);
		}		
	}
	
	private HashMap<PropertyTableType, PropertyTable> _properties; 
}
