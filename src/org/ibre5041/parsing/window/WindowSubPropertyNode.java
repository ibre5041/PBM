package org.ibre5041.parsing.window;

public class WindowSubPropertyNode extends BaseNode{
	private static NodeTypeEnum _me = NodeTypeEnum.WINDOWSUBPROP;

	@Override
	public NodeTypeEnum getNodetype() { return _me;  }
	
	@Override
	public void accept(BaseVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);		
	}
}
