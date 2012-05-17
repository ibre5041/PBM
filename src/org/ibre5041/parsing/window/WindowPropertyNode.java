package org.ibre5041.parsing.window;

public class WindowPropertyNode extends BaseNode {
	private static NodeTypeEnum _me = NodeTypeEnum.WINDOWPROP;

	@Override
	public NodeTypeEnum getNodetype() { return _me;  }
	
	@Override
	public
	void accept(BaseVisitor v) {
		v.visit(this);		
	}
}
