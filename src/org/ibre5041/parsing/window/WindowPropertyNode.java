package org.ibre5041.parsing.window;

import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.node.BaseNode;
import org.ibre5041.parsing.node.NodeTypeEnum;
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
}
