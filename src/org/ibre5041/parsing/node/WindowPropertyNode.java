package org.ibre5041.parsing.node;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.visitor.BaseVisitor;

public class WindowPropertyNode extends BaseNode {
	public static NodeTypeEnum TYPE = NodeTypeEnum.WINDOWPROP;
		
	@Override
	public
	void accept(BaseVisitor v) {
		v.visit(this);		
	}

	@Override
	protected void init(Tree astNode) {
		this._astNode = astNode;
	}
	
	public static WindowPropertyNode create(Tree astNode)
	{
		return new WindowPropertyNode(astNode);
	}
	
	WindowPropertyNode(Tree astNode)
	{
		super(astNode);
	}
	
	public static String propertyNodeName(Tree astNode) {
		StringBuffer sb = new StringBuffer(16);
		for( int i=0; i < astNode.getChild(0).getChildCount(); i++) 
		{
			sb.append(astNode.getChild(0).getChild(i));
		}
		return sb.toString();
	}	
}
