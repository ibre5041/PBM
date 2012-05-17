package org.ibre5041.parsing.window;

public abstract class BaseNode {
	public abstract void accept(BaseVisitor v);
	public abstract NodeTypeEnum getNodetype();
}
