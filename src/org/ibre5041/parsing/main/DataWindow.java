package org.ibre5041.parsing.main;

import java.util.HashMap;
import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.PBMParser;
import org.ibre5041.parsing.PBMParser.throw_stat_return;
import org.ibre5041.parsing.window.BaseNode;
import org.ibre5041.parsing.window.BaseVisitor;
import org.ibre5041.parsing.window.NodeTypeEnum;
import org.ibre5041.parsing.window.NodeTypeRegistry;
import org.ibre5041.parsing.window.PropertyTableVisitor;

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
		
		switch(ast.getType())
		{
		case PBMParser.WINDOWPROP:
			// Okay we've found some window property, is this one listed in the enum PropertyTableType?
			String propName = ast.getChild(0).getText();
			PropertyTableTypeEnum currentProperty = PropertyTableTypeEnum.enumFromName(propName);
			if (currentProperty != null) {
				if (_properties.containsKey(currentProperty)) {
					throw new RuntimeException("Parser error: duplicate window property: " + propName);
				}
				PropertyTable pt = new PropertyTable(currentProperty.toString());
				pt.walk(ast, depth);
				_properties.put(currentProperty, pt);
			}
			break;
		}
		 
		if( NodeTypeRegistry.getInstance().containsKey(NodeTypeEnum.fromId(ast.getType()))) {
			BaseNode n = NodeTypeRegistry.getInstance().get(NodeTypeEnum.fromId(ast.getType()));
			BaseVisitor v = new PropertyTableVisitor();
			n.accept(v);
		}
		//NodeTypeRegistry.getInstance().
		
		StringBuffer sb = new StringBuffer(depth);
		for ( int i = 0; i < depth; i++ )
			sb = sb.append("   ");
		
		for ( int i = 0; i < ast.getChildCount(); i++ ) {
			System.out.println(sb.toString() + ast.getChild(i).toString() + " " + ast.getChild(i).getType()); 
			walk(ast.getChild(i), depth+1);
		}		
	}
	
	private HashMap<PropertyTableTypeEnum, PropertyTable> _properties = new HashMap<PropertyTableTypeEnum, PropertyTable>(); 
}
