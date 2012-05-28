package org.ibre5041.parsing.window;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.node.BaseNode;
import org.ibre5041.parsing.node.NodeTypeEnum;
import org.ibre5041.parsing.visitor.BaseVisitor;

public class WindowSubPropertyNode extends BaseNode{
	public static NodeTypeEnum TYPE = NodeTypeEnum.WINDOWSUBPROP;

	@Override
	public void accept(BaseVisitor v) {
		v.visit(this);		
	}

	@Override
	public void unaccept(BaseVisitor v) {
		v.unvisit(this);		
	}
	
	@Override
	protected void init(Tree astNode) {
		this._astNode = astNode;		
	}
	
	public static WindowSubPropertyNode create(Tree astNode)
	{
		return new WindowSubPropertyNode(astNode);
	}
	
	WindowSubPropertyNode(Tree astNode)
	{
		super(astNode);
	}
}
