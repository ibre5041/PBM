package org.ibre5041.parsing.main;

import java.util.EnumSet;
import java.util.HashMap;

import org.antlr.runtime.tree.Tree;

public class DataWindow extends AbstractWindow implements Window {
		
	@Override
	public void setAST(Tree ast) {
		super.setAST(ast);
		
		
	}
	
	private HashMap<PropertyTableType, PropertyTable> _properties; 
}
